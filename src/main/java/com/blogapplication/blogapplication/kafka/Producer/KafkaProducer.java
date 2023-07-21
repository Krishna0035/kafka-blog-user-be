package com.blogapplication.blogapplication.kafka.Producer;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String ,String> kafkaTemplate;


    public void sendMessage(Object user,String topic){

//        Message<Object> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC,"demo").build();

        kafkaTemplate.send(topic,user.toString());

    }
}