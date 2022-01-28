package ru.yzuykov.jsandbox.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yzuykov.jsandbox.model.EventDTO;
import ru.yzuykov.jsandbox.service.ProducerService;

import java.util.concurrent.CompletableFuture;

@RestController
public class HomeController {

    private final ProducerService producerService;

    @Autowired
    public HomeController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/generate")
    public CompletableFuture<String> generate(@RequestParam String message, @RequestParam Integer age) {
        EventDTO build = EventDTO.builder().age(age).text(message).build();
        return producerService.produce(build);
    }
}