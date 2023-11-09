package ru.spbsu.linkshortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.spbsu.linkshortener.client.BillingClient;
import ru.spbsu.linkshortener.controller.dto.CreateShortUrlRequest;
import ru.spbsu.linkshortener.entity.ShortUrlEntity;
import ru.spbsu.linkshortener.entity.ShortUrlStatus;
import ru.spbsu.linkshortener.kafka.AntivirusChecksProducer;
import ru.spbsu.linkshortener.kafka.event.RequiresAntivirusChecksEvent;
import ru.spbsu.linkshortener.repository.ShortUrlRepository;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortUrlService {
    private final ShortUrlGenerator generator;
    private final AntivirusChecksProducer antivirusChecksProducer;
    private final ShortUrlRepository shortUrlRepository;
    private final BillingClient billingClient;

//    @Transactional
    public URI create(CreateShortUrlRequest request) {
        var shortUrl = generator.generateShortUrl(request.getOriginalUrl());
        var entity = new ShortUrlEntity()
                .setShortUrl(shortUrl)
                .setOriginalUrl(request.getOriginalUrl())
                .setOwner(request.getUsername())
                .setStatus(ShortUrlStatus.ANTIVIRUS_CHECKS);

        var event = new RequiresAntivirusChecksEvent()
                .setUrl(request.getOriginalUrl());

        antivirusChecksProducer.produce(event);
        shortUrlRepository.save(entity);
        return shortUrl;
    }

    public void delete(URI shortUrl) {
        shortUrlRepository.delete(shortUrl);
    }

    public URI getOriginalUrl(URI shortUrl) {
        var entity =  shortUrlRepository.findByShortUrl(shortUrl)
                .orElseThrow();
        if (ShortUrlStatus.READY.equals(entity.getStatus())) {
            billingClient.withdrawMoney(entity.getOwner());
            log.info("Событие 'short link redirected': отправляю настоящую ссылку {} для короткой {} на фронт",
                    entity.getOriginalUrl(), entity.getShortUrl());

            return entity.getOriginalUrl();
        }

        log.info("Короткая ссылка не готова, находится в статусе {}", entity.getStatus());
        return null;
    }
}
