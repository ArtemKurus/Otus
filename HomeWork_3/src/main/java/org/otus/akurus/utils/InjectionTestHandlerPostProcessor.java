package org.otus.akurus.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class InjectionTestHandlerPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<String, Class>();
    private MessageSource messageSource;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        if (aClass.isAnnotationPresent(InjectionTest.class)) {
            map.put(beanName, aClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {

        final Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(messageSource.getMessage("test.start", null, Locale.ENGLISH));
                    Object retVal = method.invoke(bean, args);
                    System.out.println(messageSource.getMessage("test.end", null, Locale.ENGLISH));
                    return retVal;
                }
            });
        }
        return bean;
    }
}
