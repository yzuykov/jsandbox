package ru.yzuykov.jsandbox.application.statemachine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import ru.yzuykov.jsandbox.application.statemachine.enums.Events;
import ru.yzuykov.jsandbox.application.statemachine.enums.States;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessStateMachineApplicationListener  implements StateMachineListener<States, Events> {

    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        if (Objects.isNull(from) ) {
            log.info("Переход в статус " + to.getId());
        } else {
            log.info("Переход из статуса " + from.getId() + " в статус " + to.getId());
        }

    }

    @Override
    public void stateEntered(State<States, Events> state) {

    }

    @Override
    public void stateExited(State<States, Events> state) {

    }

    @Override
    public void eventNotAccepted(Message<Events> event) {

    }

    @Override
    public void transition(Transition<States, Events> transition) {

    }

    @Override
    public void transitionStarted(Transition<States, Events> transition) {

    }

    @Override
    public void transitionEnded(Transition<States, Events> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<States, Events> stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine<States, Events> stateMachine) {

    }

    @Override
    public void stateMachineError(StateMachine<States, Events> stateMachine, Exception exception) {

    }

    @Override
    public void extendedStateChanged(Object key, Object value) {

    }

    @Override
    public void stateContext(StateContext<States, Events> stateContext) {

    }
}
