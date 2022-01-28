package ru.yzuykov.jsandbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.yzuykov.jsandbox.service.api.ContDownService;
import ru.yzuykov.jsandbox.service.api.GreetService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Primary
@Service
public class ContDownServiceImpl implements ContDownService {

    private final GreetService greetService;

    @Autowired
    public ContDownServiceImpl(GreetService greetService) {
        this.greetService = greetService;
    }

    @Override
    public void execute() {
        List<String> stringList = Arrays.asList("Smith", "John", "William");
        List<CompletableFuture<Void>> futuresList = stringList.stream()
                .map(greetService::greetAsync)
                .collect(Collectors.toList());
        CompletableFuture<?>[] futuresArray = new CompletableFuture[futuresList.size()];
        futuresArray = futuresList.toArray(futuresArray);
        CompletableFuture.allOf(futuresArray).thenRun(() -> log.info("All users are greet"));
    }
}
