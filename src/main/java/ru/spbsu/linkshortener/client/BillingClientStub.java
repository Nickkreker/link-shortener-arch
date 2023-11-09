package ru.spbsu.linkshortener.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BillingClientStub implements BillingClient {
    @Override
    public void withdrawMoney(String username) {
        log.info("Иду в сторонний сервис биллинга, который спишет деньги с пользователя {}", username);
    }
}
