package ru.yzuykov.jsandbox.application.core.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.service.api.WorkflowService;
import ru.yzuykov.jsandbox.application.statemachine.service.api.ProcessStateMachineService;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private final ProcessStateMachineService processStateMachineService;

    @Override
    public void startProcess(String userId) {
        processStateMachineService.startProcess(userId);
    }
}
