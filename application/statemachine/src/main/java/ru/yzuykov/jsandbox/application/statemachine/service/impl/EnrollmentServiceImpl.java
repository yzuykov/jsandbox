package ru.yzuykov.jsandbox.application.statemachine.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.statemachine.service.api.EnrollmentService;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.service.api.ProcessStateMachineService;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final ProcessStateMachineService processStateMachineService;

    @SneakyThrows
    @Override
    public void execute(String userId) {
        TimeUnit.SECONDS.sleep(3);
        log.info("execute EnrollmentService - {}", userId);
        processStateMachineService.sendMessage(userId, Events.FINISH_ENROLLMENT);
    }
}
