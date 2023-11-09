package ru.spbsu.linkshortener.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spbsu.linkshortener.entity.ShortUrlStatus;
import ru.spbsu.linkshortener.kafka.event.AntivirusChecksFinishedEvent;
import ru.spbsu.linkshortener.kafka.event.AntivirusChecksStatus;
import ru.spbsu.linkshortener.repository.ShortUrlRepository;

@Component
@RequiredArgsConstructor
public class EventsFromAntivirusConsumer {
    private final ShortUrlRepository repository;

//    @KafkaListener(topics = "${kafka.consuming}")
    public void consume(AntivirusChecksFinishedEvent event) {
        var entity = repository.findByShortUrl(event.getShortUrl());
        entity.ifPresent(e -> {
            if (AntivirusChecksStatus.HAS_VIRUSES.equals(event.getStatus())) {
                e.setStatus(ShortUrlStatus.HAS_VIRUSES);
            } else {
                e.setStatus(ShortUrlStatus.READY);
            }
            repository.save(e);
        });
    }
}
