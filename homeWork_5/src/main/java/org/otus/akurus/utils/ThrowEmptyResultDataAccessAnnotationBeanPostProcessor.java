package org.otus.akurus.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ThrowEmptyResultDataAccessAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<String, Class>();
    private Map<String, List<String>> methodMap = new HashMap<String, List<String>>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        List<String> collect = Stream.of(aClass.getMethods()).filter(method -> method.isAnnotationPresent(ThrowEmptyResultDataAccess.class)).map(Method::getName).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            map.put(beanName, aClass);
            methodMap.put(beanName, collect);
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        final Class beanClass = map.get(beanName);
        final List<String> stringList = methodMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (stringList.stream().filter(f -> f.equals(method.getName())).findFirst().isPresent()) {
                        try {
                            return method.invoke(bean, args);
                        } catch (Exception e) {
                            log.info("EmptyResultDataAccessException :" + e.getMessage());
                            return null;
                        }

                    } else {
                        return method.invoke(bean, args);
                    }
                }
            });
        }
        return bean;
    }
}
