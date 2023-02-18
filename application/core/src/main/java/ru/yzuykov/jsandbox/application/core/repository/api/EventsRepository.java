package ru.yzuykov.jsandbox.application.core.repository.api;

import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;

import java.util.List;

public interface EventsRepository {

    List<EventRq> findAll();
}
