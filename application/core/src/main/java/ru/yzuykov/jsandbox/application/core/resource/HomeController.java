package ru.yzuykov.jsandbox.application.core.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRq;
import ru.yzuykov.jsandbox.application.core.model.dto.EventRs;
import ru.yzuykov.jsandbox.application.core.service.impl.EventRequestService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final EventRequestService eventRequestService;

    @GetMapping("/generate")
    public CompletableFuture<String> generate(@RequestParam String message, @RequestParam Integer age) {
        EventRq build = EventRq.builder().age(age).text(message).build();
        return eventRequestService.produce(build).thenApply(EventRs::toString);
    }
}