package ru.spbsu.linkshortener.service;

import java.net.URI;

public interface ShortUrlGenerator {
    URI generateShortUrl(URI url);
}
