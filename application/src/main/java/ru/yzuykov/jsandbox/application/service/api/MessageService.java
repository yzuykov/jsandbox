package ru.yzuykov.jsandbox.application.service.api;

import ru.yzuykov.jsandbox.application.model.EventRq;

import java.util.List;

public interface MessageService {

    List<EventRq> getAllMessages(Integer age);

    List<EventRq> getCachedAllMessages(Integer age);
}
