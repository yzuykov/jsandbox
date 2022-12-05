package ru.yzuykov.jsandbox.application.statemachine.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.yzuykov.jsandbox.application.dao.config.DbConfig;

@Configuration
@ComponentScan(basePackages = "ru.yzuykov.jsandbox.statemachine")
@Import({DbConfig.class, StateMachineConfig.class})
public class StateMachineTestConfig {
}
