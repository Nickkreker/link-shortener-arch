package ru.spbsu.linkshortener.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.spbsu.linkshortener.entity.ShortUrlEntity;

import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Repository
public class InMemoryShortUrlRepository implements ShortUrlRepository {
    private final HashMap<URI, ShortUrlEntity> urlToEntity = new HashMap<>();

    @Override
    public Optional<ShortUrlEntity> findByShortUrl(URI shortUrl) {
        return Optional.ofNullable(urlToEntity.get(shortUrl));
    }

    @Override
    public void save(ShortUrlEntity entity) {
        urlToEntity.put(entity.getShortUrl(), entity);
        log.info("Событие 'short url added to user account': сохранил состояние короткой ссылки {}", entity);
    }

    @Override
    public void delete(URI shortUrl) {
        urlToEntity.remove(shortUrl);
        log.info("Событие 'short url deleted': удалил короткую ссылку {}", shortUrl);
    }
}
