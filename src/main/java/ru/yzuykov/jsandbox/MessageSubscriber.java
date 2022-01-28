package ru.yzuykov.jsandbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yzuykov.jsandbox.model.EventDTO;

import java.util.concurrent.Flow;

@Slf4j
@Component
public class MessageSubscriber implements Flow.Subscriber<EventDTO> {

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("subscribe");
        subscription.request(20);
    }

    @Override
    public void onNext(EventDTO item) {
        log.info("on message {}", item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("on message error {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("complete");
    }
}
