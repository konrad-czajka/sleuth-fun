package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyEventsHandler {
    @StreamListener(MyEventsSink.CHANNEL_NAME)
    public void process(MyEvent event) {
        log.info("Handling event: {}", event);
    }
}