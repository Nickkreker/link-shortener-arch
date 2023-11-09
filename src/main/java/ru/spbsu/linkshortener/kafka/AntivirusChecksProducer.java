package ru.spbsu.linkshortener.kafka;

import ru.spbsu.linkshortener.kafka.event.RequiresAntivirusChecksEvent;

public interface AntivirusChecksProducer {
    void produce(RequiresAntivirusChecksEvent event);
}
