package ru.yzuykov.jsandbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.MessageSubscriber;
import ru.yzuykov.jsandbox.model.EventDTO;

import java.util.concurrent.Executor;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.IntStream;

@Service
public class FlowService {

    private final MessageSubscriber subscriber;
    private final Executor executor;

    @Autowired
    public FlowService(MessageSubscriber subscriber, Executor executor) {
        this.subscriber = subscriber;
        this.executor = executor;
    }

    public void execute() {
        SubmissionPublisher<EventDTO> publisher = new SubmissionPublisher<>(executor, 256);
        publisher.subscribe(subscriber);
        IntStream.range(0,9)
                .mapToObj(i -> EventDTO.builder().age(1).text("Text " + i).build())
                .forEach(publisher::submit);
        publisher.close();
    }
}
