package ru.yzuykov.jsandbox.application.statemachine.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnrollmentAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        log.info("Start EnrollmentAction - {}", context);
        context.getExtendedState().getVariables().put("enrollment", "false");
        log.info("context - {}", context.getExtendedState().getVariables());
    }
}
