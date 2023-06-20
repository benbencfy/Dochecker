package com.wetoband.template;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring IOC 容器的配置
 * 用于声明配置Bean
 * @author xiaoliang
 * @date 2022/04/18 19:53
 **/
@ComponentScan("com.wetoband.template")
@Configuration
public class AppConfig {
    public static AnnotationConfigApplicationContext initApplicationContext() {
        // 注意 ：
        /*1. 这里 类AnnotationConfigApplicationContext 类加载器跟下面的 componentsClassLoader 可能不同，要留意这一点 。
        2. 对象 applicationContext 新建时，所使用的用来加载组件bean对象的类加载器，也就是其属性 getClassLoader()
        默认是 类 AnnotationConfigApplicationContext 的类加载器 。
        3. 如果 applicationContext.getClassLoader() 和目标组件bean对象的类加载器不一致，会导致找不到目标组件bean,
                所以一定要将 applicationContext 对象的 classLoader 属性设置成目标组件bean对象的类加载器*/
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 类加载器的设置很重要，如果目标组件类所在包下的类已经被加载，这里一定要设置使用同样的类加载器
        /*前提假设 : 类 AppConfig 是目标bean组件所在的包，并且目标bean组件类,包括 AppConfig 在内都已经被同一类加载器加载，
        这里获取目标bean组件的类加载器并设置到 applicationContext 对象的 classLoader 属性上，这样
        applicationContext 就能正常地发现目标组件了*/
        ClassLoader componentsClassLoader = AppConfig.class.getClassLoader();
        // 该模式下必须要保证这里指定的ClassLoader和AppConfig类的类加载器一致
        applicationContext.setClassLoader(componentsClassLoader);

        // 此时注册组件类 AppConfig.class 到 applicationContext 时，发现组件bean所使用的类加载器会是 componentsClassLoader
        applicationContext.register(AppConfig.class);
        applicationContext.refresh(); // 该方法需要配合 register 方法使用

        return applicationContext;
    }
}
