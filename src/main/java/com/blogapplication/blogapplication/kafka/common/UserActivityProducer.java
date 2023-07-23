package com.blogapplication.blogapplication.kafka.common;

import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogActivityLogDto;
import com.blogapplication.blogapplication.kafka.dto.UserActivityLogDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class UserActivityProducer {

    private final KafkaProducer kafkaProducer;


    public UserActivityProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendUserActivity(Long userId,String activity){

        UserActivityLogDto userActivityDto = UserActivityLogDto
                .builder()
                .activityBy(userId)
                .activity(activity)
                .activityAt(LocalDateTime.now())
                .build();
        kafkaProducer.sendMessage(userActivityDto,"user-activity-details");
    }


}
