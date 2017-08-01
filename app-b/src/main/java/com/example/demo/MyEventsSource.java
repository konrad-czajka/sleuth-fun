package com.example.demo;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MyEventsSource {
    String CHANNEL_NAME = "myEventsChannel";

    @Output(CHANNEL_NAME)
    MessageChannel eventsChannel();
}
