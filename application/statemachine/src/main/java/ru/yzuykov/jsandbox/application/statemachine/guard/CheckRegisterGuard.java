package ru.yzuykov.jsandbox.application.statemachine.guard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckRegisterGuard implements Guard<States, Events> {

    @Override
    public boolean evaluate(StateContext<States, Events> context) {
        log.info("Start CheckRegisterGuard - {}", context);
        String register = String.valueOf(context.getExtendedState().getVariables().getOrDefault("register", "false"));
        log.info("register - {}", register);
        return Boolean.valueOf(register);
    }
}
