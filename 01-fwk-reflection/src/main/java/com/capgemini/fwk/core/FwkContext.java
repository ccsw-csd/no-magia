package com.capgemini.fwk.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import com.capgemini.fwk.annotations.Autowired;
import com.capgemini.fwk.annotations.Component;

/**
 * @author pajimene
 *
 */
public class FwkContext {

  private static CacheComponents cacheComponents;

  private static Reflections reflections;

  /**
   * Punto de entrada al Fwk init
   * @param mainClass
   * @throws Exception
   */
  public static void run(Class mainClass) throws Exception {

    System.out.println("  ______        _       _____      ______ \n" + " |  ____|      | |     / ____|    |  ____|\n"
        + " | |____      _| | __ | |     ___ | |__   \n" + " |  __\\ \\ /\\ / / |/ / | |    / _ \\|  __|  \n"
        + " | |   \\ V  V /|   <  | |___| (_) | |____ \n" + " |_|    \\_/\\_/ |_|\\_\\  \\_____\\___/|______|\n\n"
        + "\t\tv1.0.0\n\n... Inicializando ...\n");

    cacheComponents = new CacheComponents();

    reflections = new Reflections(mainClass, //
        new FieldAnnotationsScanner(), //
        new TypeAnnotationsScanner(), //
        new SubTypesScanner());

    scanComponents(mainClass);
    autowiredComponents();

    System.out.println("\n... Fwk inicializado ...\n\n");

    initMainClass();

  }

  /**
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   */
  private static void initMainClass() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

    Object mainObject = cacheComponents.getComponent("mainClass");

    Method initMethod = mainObject.getClass().getDeclaredMethod("init", null);

    initMethod.setAccessible(true);
    initMethod.invoke(mainObject, null);
    initMethod.setAccessible(false);
  }

  /**
   * @throws IllegalAccessException
   */
  private static void autowiredComponents() throws IllegalAccessException {

    for (Field field : reflections.getFieldsAnnotatedWith(Autowired.class)) {

      Class componentClass = field.getDeclaringClass();

      Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
      String nameInjectComponent = autowiredAnnotation.value();

      Object component = findComponentByClass(componentClass);
      Object injectComponent = cacheComponents.getComponent(nameInjectComponent);

      boolean originalAccesible = field.isAccessible();
      field.setAccessible(true);
      field.set(component, injectComponent);
      field.setAccessible(originalAccesible);
    }
  }

  private static Object findComponentByClass(Class componentClass) {

    for (Object component : cacheComponents.cache.values()) {
      if (component.getClass().equals(componentClass))
        return component;
    }
    return null;
  }

  /**
   * @param mainClass
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private static void scanComponents(Class mainClass) throws InstantiationException, IllegalAccessException {

    for (Class clazz : reflections.getTypesAnnotatedWith(Component.class)) {

      Component componentAnnotation = (Component) clazz.getAnnotation(Component.class);
      String key = componentAnnotation.value();
      createComponent(clazz, key);
    }

    createComponent(mainClass, "mainClass");
  }

  /**
   * @param clazz
   * @param key
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private static void createComponent(Class clazz, String key) throws InstantiationException, IllegalAccessException {

    Object value = clazz.newInstance();

    cacheComponents.putComponent(key, value);
  }

}
