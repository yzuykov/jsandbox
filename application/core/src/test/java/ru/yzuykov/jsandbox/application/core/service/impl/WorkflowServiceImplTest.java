package ru.yzuykov.jsandbox.application.core.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.yzuykov.jsandbox.application.core.service.api.WorkflowService;
import ru.yzuykov.jsandbox.application.core.service.impl.WorkflowServiceImpl;
import ru.yzuykov.jsandbox.application.statemachine.config.StateMachineTestConfig;
import ru.yzuykov.jsandbox.application.statemachine.service.api.EnrollmentService;

import java.util.concurrent.TimeUnit;


@SpringBootTest
@ContextConfiguration(classes = {StateMachineTestConfig.class, WorkflowServiceImpl.class} )
class WorkflowServiceImplTest {

    private static final String USER_ID = "Test123";

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private WorkflowService workflowService;

    @Test
    void test_start_process() throws Exception {
        workflowService.startProcess(USER_ID);
        enrollmentService.execute(USER_ID);
        TimeUnit.SECONDS.sleep(1);
    }
}