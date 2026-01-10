package com.bank.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = "com.bank.app")
@PropertySource("classpath:application.properties")
public class BankAppConfig {
}
