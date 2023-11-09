package ru.spbsu.linkshortener.controller.dto;

import lombok.Data;

import java.net.URI;

@Data
public class CreateShortUrlRequest {
    private URI originalUrl;
    private String username;
}
