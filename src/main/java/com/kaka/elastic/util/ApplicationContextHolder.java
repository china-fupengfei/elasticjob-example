package com.kaka.elastic.util;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Locale;

@Service
@Lazy(false)
public class ApplicationContextHolder implements BeanFactoryAware, ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    private static BeanFactory beanFactory = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ApplicationContextHolder.beanFactory = beanFactory;
    }

    /**
     * 当关闭应用上下文时，执行此方法。
     */
    @PreDestroy
    public void destroy() {
        applicationContext = null;
        beanFactory = null;
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        try {
            return applicationContext.getBean(requiredType);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }

    public static <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {
        try {
            return applicationContext.getBean(name, requiredType);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> requiredType, String name,
                                Object... args) throws BeansException {
        try {
            return (T) applicationContext.getBean(name, args);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }

    public static String[] getBeanNamesForType(Class<?> requiredType)
            throws BeansException {
        return applicationContext.getBeanNamesForType(requiredType);
    }

    public static <T> Collection<T> getBeansOfType(Class<T> requiredType)
            throws BeansException {
        return applicationContext.getBeansOfType(requiredType).values();
    }

    public static Collection<Object> getBeansWithAnnotation(
            Class<? extends Annotation> annotationType) throws BeansException {
        return applicationContext.getBeansWithAnnotation(annotationType).values();
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String getMessage(String defaultMessage, Locale locale,
                                    String code, Object... args) {
        return applicationContext.getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(Locale locale, String code, Object... args) {
        return applicationContext.getMessage(code, args, null, locale);
    }
}
