package ru.yzuykov.jsandbox.application.statemachine.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;
import ru.yzuykov.jsandbox.application.statemachine.service.api.ProcessStateMachineService;

import java.util.Map;

import static java.util.Collections.emptyMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessStateMachineServiceImpl implements ProcessStateMachineService {

    private final StateMachineService<States, Events> stateMachineService;

    @Override
    public void startProcess(String id) {
        var stateMachine = stateMachineService.acquireStateMachine(id);
        stateMachine.startReactively().block();
    }
    @Override
    public void sendMessage(String id, Events event) {
        sendMessage(id, event, emptyMap());
    }

    @Override
    public void sendMessage(String id, Events event, Map<String, ?> headers) {
        var stateMachine = stateMachineService.acquireStateMachine(id, false);
        var isCompleted = stateMachine.isComplete();
        if (isCompleted) {
            log.warn("Сообщение не будет отправлено - statemachine is completed");
        } else {
            var message = MessageBuilder.withPayload(event).copyHeaders(headers).build();
            stateMachine.sendEvent(Mono.just(message))
                    .subscribe(res -> log.info("Result of sending event {} is {}", res.getMessage(), res.getResultType().name()));
        }
    }
}
