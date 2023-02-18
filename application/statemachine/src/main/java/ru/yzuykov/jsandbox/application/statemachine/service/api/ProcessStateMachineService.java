package ru.yzuykov.jsandbox.application.statemachine.service.api;

import ru.yzuykov.jsandbox.application.statemachine.enums.Events;

import java.util.Map;

public interface ProcessStateMachineService {

    /**
     * Запуск процеесса
     * @param id ключ процесса
     */
    void startProcess(String id);

    /**
     * Отправка события
     * @param id ключ процесса
     * @param event событие
     */
    void sendMessage(String id, Events event);

    /**
     * @param id ключ процесса
     * @param event событие
     * @param headers заголовки
     */
    void sendMessage(String id, Events event, Map<String, ?> headers);
}
