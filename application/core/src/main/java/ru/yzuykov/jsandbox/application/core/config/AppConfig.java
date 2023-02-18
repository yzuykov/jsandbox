package ru.yzuykov.jsandbox.application.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.yzuykov.jsandbox.application.dao","ru.yzuykov.jsandbox.application.statemachine"})
public class AppConfig {
}
