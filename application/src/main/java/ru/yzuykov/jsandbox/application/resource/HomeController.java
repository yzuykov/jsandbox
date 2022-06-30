package ru.yzuykov.jsandbox.application.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yzuykov.jsandbox.application.model.EventRq;
import ru.yzuykov.jsandbox.application.model.EventRs;
import ru.yzuykov.jsandbox.application.service.EventRequestService;

import java.util.concurrent.CompletableFuture;

@RestController
public class HomeController {

    private final EventRequestService eventRequestService;

    @Autowired
    public HomeController(EventRequestService eventRequestService) {
        this.eventRequestService = eventRequestService;
    }

    @GetMapping("/generate")
    public CompletableFuture<String> generate(@RequestParam String message, @RequestParam Integer age) {
        EventRq build = EventRq.builder().age(age).text(message).build();
        return eventRequestService.produce(build).thenApply(EventRs::toString);
    }
}