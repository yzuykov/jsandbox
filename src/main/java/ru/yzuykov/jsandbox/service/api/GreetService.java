package ru.yzuykov.jsandbox.service.api;

import java.util.concurrent.CompletableFuture;

public interface GreetService {

    void greet(String name);

    CompletableFuture<Void> greetAsync(String name);
}
