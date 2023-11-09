package ru.spbsu.linkshortener.kafka.event;

import lombok.Data;

import java.net.URI;

@Data
public class AntivirusChecksFinishedEvent {
    private URI shortUrl;
    private AntivirusChecksStatus status;
}
