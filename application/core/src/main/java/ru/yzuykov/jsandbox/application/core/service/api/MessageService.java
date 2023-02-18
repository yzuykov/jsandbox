package ru.yzuykov.jsandbox.application.core.service.api;

import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;

import java.util.List;

public interface MessageService {

    List<EventRq> getAllMessages(Integer age);

    List<EventRq> getCachedAllMessages(Integer age);
}
