package com.buutcamp.config;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class HibernateConfig {

    private Environment env;

    public Configuration getConfiguration(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        env = ctx.getEnvironment();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.username", env.getProperty("hibernate_username"));
        configuration.setProperty("hibernate.connection.password", env.getProperty("hibernate_password"));
        configuration.setProperty("hibernate.connection.url", env.getProperty("hibernate_url"));
        configuration.setProperty("hibernate.connection.driver_class", env.getProperty("hibernate_driver"));
        configuration.setProperty("hibernate.dialect", env.getProperty("hibernate_dialect"));
        configuration.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate_session"));
        return configuration;
    }
}
