package ru.spbsu.linkshortener.controller.dto;

import lombok.Data;

import java.net.URI;

@Data
public class ShortUrlRequest {
    private URI shortUrl;
}
