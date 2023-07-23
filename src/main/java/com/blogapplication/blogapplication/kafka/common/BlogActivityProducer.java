package com.blogapplication.blogapplication.kafka.common;


import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogActivityLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogActivityProducer {

    @Autowired
    private KafkaProducer kafkaProducer;



    public void sendBlogActivity(Long blogId,Long userId,String activity){

        BlogActivityLogDto blogActivityLogDto = BlogActivityLogDto.builder()
                .activityBy(userId)
                .blogId(blogId)
                .activity(activity)
                .activityAt(LocalDateTime.now())
                .build();
        kafkaProducer.sendMessage(blogActivityLogDto,"blog-activity-details");
    }


}
