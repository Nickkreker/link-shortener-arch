package ru.spbsu.linkshortener.entity;

import lombok.Data;

import java.net.URI;

@Data
public class ShortUrlEntity {

    private URI originalUrl;

    private URI shortUrl;
    /**
     * username создателя
     */
    private String owner;

    private ShortUrlStatus status;
}
