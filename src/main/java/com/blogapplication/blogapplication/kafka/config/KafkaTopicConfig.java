package com.blogapplication.blogapplication.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic createNewTopic(){
        return TopicBuilder
                .name("user-login")
                .build();
    }

    @Bean
    public NewTopic createUserRegistraionNewTopic(){
        return TopicBuilder
                .name("user-registration")
                .build();
    }

    @Bean
    public NewTopic createCreateBlogTopic(){
        return TopicBuilder
                .name("blog-details")
                .build();
    }
}
