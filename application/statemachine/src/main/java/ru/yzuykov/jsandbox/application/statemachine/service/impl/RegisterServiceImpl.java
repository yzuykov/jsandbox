package ru.yzuykov.jsandbox.application.statemachine.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.statemachine.service.api.RegisterService;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    @Override
    public void execute(String userId) {
        log.info("execute RegisterService - {}", userId);
    }
}
