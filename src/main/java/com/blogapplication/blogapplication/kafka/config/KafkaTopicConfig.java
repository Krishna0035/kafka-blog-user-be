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

    @Bean
    public NewTopic createBlogViewTopic(){
        return TopicBuilder
                .name("blog-view-details")
                .build();
    }


    @Bean
    public NewTopic createLikeBlogTopic(){
        return TopicBuilder
                .name("blog-like-details")
                .build();
    }

    @Bean
    public NewTopic createActivityBlogTopic(){
        return TopicBuilder
                .name("blog-activity-details")
                .build();
    }

    @Bean
    public NewTopic createHitTopic(){
        return TopicBuilder
                .name("hits-details")
                .build();
    }

    @Bean
    public NewTopic createPartitionTopic(){
        return TopicBuilder
                .name("email_1")
                .build();
    }
}
