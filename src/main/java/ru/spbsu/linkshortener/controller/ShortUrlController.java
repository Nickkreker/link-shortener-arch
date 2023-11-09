package ru.spbsu.linkshortener.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spbsu.linkshortener.controller.dto.CreateShortUrlRequest;
import ru.spbsu.linkshortener.controller.dto.ShortUrlRequest;
import ru.spbsu.linkshortener.service.ShortUrlService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/short-url/v1")
@RequiredArgsConstructor
public class ShortUrlController {
    private final ShortUrlService shortUrlService;

    @PostMapping("/create")
    public URI create(@RequestBody CreateShortUrlRequest request) {
        var uri = shortUrlService.create(request);
        log.info("--------------------------------");
        return uri;
    }

    @DeleteMapping()
    public void delete(@RequestBody ShortUrlRequest request) {
        shortUrlService.delete(request.getShortUrl());
        log.info("--------------------------------");
    }

    @PutMapping("/forward")
    public URI getOriginalUrl(@RequestBody ShortUrlRequest request) {
        var uri = shortUrlService.getOriginalUrl(request.getShortUrl());
        log.info("--------------------------------");
        return uri;
    }
}
