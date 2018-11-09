package com.buutcamp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.buutcamp")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public UrlBasedViewResolver urlBasedViewResolver(){
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setPrefix("/WEB-INF/views/");
        urlBasedViewResolver.setSuffix(".jsp");
        urlBasedViewResolver.setViewClass(JstlView.class);
        return urlBasedViewResolver;
    }

    @Bean
    public DataSource secDataSource(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Environment env = ctx.getEnvironment();
        BasicDataSource secDataSource = new BasicDataSource();
        secDataSource.setDriverClassName(env.getProperty("hibernate_driver"));
        secDataSource.setUrl(env.getProperty("security_url"));
        secDataSource.setUsername(env.getProperty("security_username"));
        secDataSource.setPassword(env.getProperty("security_password"));
        secDataSource.setInitialSize(5);
        secDataSource.setMaxConnLifetimeMillis(10000000);
        secDataSource.setMaxTotal(20);
        return secDataSource;
    }
}
