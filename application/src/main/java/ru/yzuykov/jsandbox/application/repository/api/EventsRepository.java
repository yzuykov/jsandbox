package ru.yzuykov.jsandbox.application.repository.api;

import ru.yzuykov.jsandbox.application.model.EventRq;

import java.util.List;

public interface EventsRepository {

    List<EventRq> findAll();
}
