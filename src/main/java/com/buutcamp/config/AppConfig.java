package com.buutcamp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:hibernate.properties", "classpath:strings.properties"}, encoding = "UTF-8")
public class AppConfig {

}
