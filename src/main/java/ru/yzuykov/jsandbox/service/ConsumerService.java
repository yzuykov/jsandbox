package ru.yzuykov.jsandbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.model.EventDTO;

@Slf4j
@Service
public class ConsumerService {

    @KafkaListener(topics = "messages", groupId = "message_group_id")
    public void consume(EventDTO eventDTO) {
        log.info("Consuming the message: " + eventDTO);
    }
}
