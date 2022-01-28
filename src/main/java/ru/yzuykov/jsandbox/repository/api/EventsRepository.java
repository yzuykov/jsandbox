package ru.yzuykov.jsandbox.repository.api;

import ru.yzuykov.jsandbox.model.EventDTO;

import java.util.List;

public interface EventsRepository {

    List<EventDTO> findAll();
}
