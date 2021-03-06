package com.mod.course_service.config;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventConfig {
    @Input
    public SubscribableChannel ModAuth();
    @Output
    public MessageChannel ModCourse();
}
