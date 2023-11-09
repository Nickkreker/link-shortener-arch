package ru.spbsu.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbsu.linkshortener.kafka.EventsFromAntivirusConsumer;
import ru.spbsu.linkshortener.kafka.event.AntivirusChecksFinishedEvent;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final EventsFromAntivirusConsumer eventsFromAntivirusConsumer;

    @PostMapping
    public void emulateAntivirusEvent(@RequestBody AntivirusChecksFinishedEvent event) {
        eventsFromAntivirusConsumer.consume(event);
        log.info("--------------------------------");
    }
}
