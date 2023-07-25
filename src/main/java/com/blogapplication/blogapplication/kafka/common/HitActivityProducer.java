package com.blogapplication.blogapplication.kafka.common;

import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import com.blogapplication.blogapplication.kafka.dto.BlogActivityLogDto;
import com.blogapplication.blogapplication.kafka.dto.HitLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HitActivityProducer {
    @Autowired
    private KafkaProducer kafkaProducer;



    public void sendHitActivity(String pageInfo){

        HitLogDto hitLogDto = HitLogDto.builder()
                .hitAt(LocalDateTime.now())
                .pageInfo(pageInfo)
                .build();
        kafkaProducer.sendMessage(hitLogDto,"hits-details");
    }
}
