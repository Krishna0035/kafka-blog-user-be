package com.blogapplication.blogapplication.kafka.common;

import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogActivityLogDto;
import com.blogapplication.blogapplication.kafka.dto.UserActivityLogDto;
import com.blogapplication.blogapplication.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class UserActivityProducer {

    private final KafkaProducer kafkaProducer;


    public UserActivityProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendUserActivity(User user, String activity){

        UserActivityLogDto userActivityDto = UserActivityLogDto
                .builder()
                .activityBy(user.getId())
                .activity(activity)
                .activityAt(LocalDateTime.now())
                .activityByName(user.getFirstName()+" "+user.getLastName())
                .build();
        kafkaProducer.sendMessage(userActivityDto,"user-activity-details");
    }


}
