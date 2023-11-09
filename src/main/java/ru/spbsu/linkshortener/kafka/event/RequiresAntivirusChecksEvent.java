package ru.spbsu.linkshortener.kafka.event;

import lombok.Data;

import java.net.URI;

@Data
public class RequiresAntivirusChecksEvent {
    private URI url;
}
