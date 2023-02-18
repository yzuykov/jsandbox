package ru.yzuykov.jsandbox.application.statemachine.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;
import ru.yzuykov.jsandbox.application.statemachine.service.api.RegisterService;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterAction implements Action<States, Events> {

    private final RegisterService registerService;
    @Override
    public void execute(StateContext<States, Events> context) {
        log.info("Start RegisterAction - {}", context);
        var userId = context.getStateMachine().getId();
        registerService.execute(userId);
//        context.getExtendedState().getVariables().put("register", "true");
    }
}
