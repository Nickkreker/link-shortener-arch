package ru.spbsu.linkshortener.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.spbsu.linkshortener.kafka.event.RequiresAntivirusChecksEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class AntivirusChecksProducerStub implements AntivirusChecksProducer{
    @Override
    public void produce(RequiresAntivirusChecksEvent event) {
        log.info("Отправляю событие {} в топик, который слушает сервис, проводящий проверку на вирусы", event);
    }
}
