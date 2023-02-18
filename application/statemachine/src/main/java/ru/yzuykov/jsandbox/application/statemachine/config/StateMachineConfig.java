package ru.yzuykov.jsandbox.application.statemachine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import ru.yzuykov.jsandbox.application.statemachine.ProcessStateMachineApplicationListener;
import ru.yzuykov.jsandbox.application.statemachine.action.EnrollmentAction;
import ru.yzuykov.jsandbox.application.statemachine.action.RegisterAction;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;
import ru.yzuykov.jsandbox.application.statemachine.guard.CheckRegisterGuard;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Autowired
    private RegisterAction registerAction;
    @Autowired
    private CheckRegisterGuard checkRegisterGuard;
    @Autowired
    private EnrollmentAction enrollmentAction;
    @Autowired
    private ProcessStateMachineApplicationListener processStateMachineApplicationListener;
    @Autowired
    private StateMachineRuntimePersister<States, Events, String> stateMachineRuntimePersister;


    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .autoStartup(false)
                .listener(processStateMachineApplicationListener)
                .and()
                .withPersistence()
                .runtimePersister(stateMachineRuntimePersister)
        ;
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.START)
                .state(States.REGISTER, registerAction)
                .choice(States.CHECK_REGISTER)
                .state(States.ENROLLMENT, enrollmentAction)
                .end(States.FINISH);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(States.START)
                .target(States.REGISTER)
                .timerOnce(TimeUnit.SECONDS.toMillis(2))
                .and()
                .withExternal()
                .source(States.REGISTER)
                .target(States.CHECK_REGISTER)
                .and()
                .withChoice()
                .source(States.CHECK_REGISTER)
                .first(States.ENROLLMENT, checkRegisterGuard)
                .last(States.FINISH)
                .and()
                .withExternal()
                .source(States.ENROLLMENT)
                .target(States.FINISH)
                .event(Events.FINISH_ENROLLMENT);
    }

}
