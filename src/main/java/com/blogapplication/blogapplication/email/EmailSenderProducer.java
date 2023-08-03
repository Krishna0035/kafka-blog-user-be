package com.blogapplication.blogapplication.email;


import com.blogapplication.blogapplication.kafka.Producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderProducer {

    @Autowired
    private KafkaProducer kafkaProducer;
    public void sentToKafka(List<String> emailIds, String message,String topic){
        kafkaProducer.sendMessage(emailIds,topic);
    }
}
