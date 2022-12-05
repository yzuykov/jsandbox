package ru.yzuykov.jsandbox.application.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import ru.yzuykov.jsandbox.application.statemachine.config.StateMachineTestConfig;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;

@SpringBootTest
@ContextConfiguration(classes = {StateMachineTestConfig.class} )
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class StateMachineTest {

    private static final String USER_ID = "Test123";
    private static final String USER_ID_2 = "Test123sdsd";

    @Autowired
    private StateMachineService<States, Events> stateMachineService;

    @Test
    void test_process_with_enrollment() throws Exception {
        StateMachine<States, Events> machine = stateMachineService.acquireStateMachine(USER_ID);
        machine.getExtendedState().getVariables().put("register", "true");
        StateMachineTestPlan<States, Events> plan =
                StateMachineTestPlanBuilder.<States, Events>builder()
                        .defaultAwaitTime(3)
                        .stateMachine(machine, USER_ID)
                        .step()
                        .expectStates(States.START)
                        .expectStateChanged(0)
                        .and()
                        .step()
                        .expectState(States.REGISTER)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .expectState(States.ENROLLMENT)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .sendEvent(Events.FINISH_ENROLLMENT)
                        .expectState(States.FINISH)
                        .expectStateChanged(1)
                        .and()
                        .build();
        plan.test();
    }

    @Test
    void test_process_without_enrollment() throws Exception {
        StateMachine<States, Events> machine = stateMachineService.acquireStateMachine(USER_ID_2);
        machine.getExtendedState().getVariables().put("register", "false");
        StateMachineTestPlan<States, Events> plan =
                StateMachineTestPlanBuilder.<States, Events>builder()
                        .defaultAwaitTime(3)
                        .stateMachine(machine, USER_ID_2)
                        .step()
                        .expectStates(States.START)
                        .expectStateChanged(0)
                        .and()
                        .step()
                        .expectState(States.REGISTER)
                        .expectStateChanged(1)
                        .and()
                        .step()
                        .expectState(States.FINISH)
                        .expectStateChanged(1)
                        .and()
                        .build();
        plan.test();
    }
}
