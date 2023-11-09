package ru.spbsu.linkshortener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.net.URI;

@Slf4j
@Component
public class ShortUrlGeneratorStub implements ShortUrlGenerator {
    @Override
    public URI generateShortUrl(URI url) {
        var shortUrlSuffix = RandomStringUtils.randomAlphabetic(5);
        var shortUrl = URI.create("www.сократиурл.ru/" + shortUrlSuffix);
        log.info("Событие 'short url created': сгенерировал короткую ссылку {} для {}", shortUrl, url);
        return shortUrl;
    }
}
