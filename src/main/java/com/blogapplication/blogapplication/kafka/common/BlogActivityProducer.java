package com.blogapplication.blogapplication.kafka.common;


import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogActivityLogDto;
import com.blogapplication.blogapplication.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogActivityProducer {

    @Autowired
    private KafkaProducer kafkaProducer;



    public void sendBlogActivity(Long blogId, User user, String activity){

        BlogActivityLogDto blogActivityLogDto = BlogActivityLogDto.builder()
                .activityBy(user.getId())
                .blogId(blogId)
                .activity(activity)
                .activityAt(LocalDateTime.now())
                .activityByName(user.getFirstName()+" "+user.getLastName())
                .build();
        kafkaProducer.sendMessage(blogActivityLogDto,"blog-activity-details");
    }


}
