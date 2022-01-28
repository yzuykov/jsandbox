package ru.yzuykov.jsandbox.service.api;

import ru.yzuykov.jsandbox.model.EventDTO;

import java.util.List;

public interface MessageService {

    List<EventDTO> getAllMessages(Integer age);

    List<EventDTO> getCachedAllMessages(Integer age);
}
