package ru.yzuykov.jsandbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.model.EventDTO;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ProducerService {

    private final KafkaTemplate<String, EventDTO> kafkaTemplate;
    private final ReplyingKafkaTemplate<String, EventDTO, String> replyingKafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, EventDTO> kafkaTemplate,
                           ReplyingKafkaTemplate<String, EventDTO, String> replyingKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    @Async
    public CompletableFuture<String> produce(EventDTO eventDTO) {
        log.info("Producing the message: " + eventDTO);
        return kafkaTemplate.send("messages", eventDTO)
                .completable()
                .thenApply(SendResult::toString);
    }
}
