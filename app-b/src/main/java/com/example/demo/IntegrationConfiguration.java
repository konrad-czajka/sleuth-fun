package com.example.demo;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(MyEventsSource.class)
public class IntegrationConfiguration {
}
