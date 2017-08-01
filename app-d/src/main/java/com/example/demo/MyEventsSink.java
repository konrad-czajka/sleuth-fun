package com.example.demo;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface MyEventsSink {
    String CHANNEL_NAME = "myEventsChannel";

    @Input(CHANNEL_NAME)
    MessageChannel eventsChannel();
}
