package ru.spbsu.linkshortener.repository;

import ru.spbsu.linkshortener.entity.ShortUrlEntity;

import java.net.URI;
import java.util.Optional;

public interface ShortUrlRepository {
    Optional<ShortUrlEntity> findByShortUrl(URI shortUrl);
    void save(ShortUrlEntity entity);
    void delete(URI shortUrl);
}
