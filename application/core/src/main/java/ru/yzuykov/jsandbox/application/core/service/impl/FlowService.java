package ru.yzuykov.jsandbox.application.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.application.core.component.MessageSubscriber;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;

import java.util.concurrent.Executor;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

@Slf4j
@Service
public class FlowService {

    private final MessageSubscriber subscriber;
    private final Executor executor;

    @Autowired
    public FlowService(MessageSubscriber subscriber, @Qualifier("taskExecutor") Executor executor) {
        this.subscriber = subscriber;
        this.executor = executor;
    }

    public void execute() {
        SubmissionPublisher<EventRq> publisher = new SubmissionPublisher<>(executor, 256);
        log.info("Start events");
        publisher.subscribe(subscriber);
        IntStream.range(0,9)
                .mapToObj(i -> EventRq.builder().age(1).text("Text " + i).build())
                .forEach(publisher::submit);
        log.info("Before subscribe");
        publisher.close();
    }
}
