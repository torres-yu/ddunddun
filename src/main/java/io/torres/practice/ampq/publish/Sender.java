package io.torres.practice.ampq.publish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.torres.practice.ampq.pubsub.EventPayload;
import io.torres.practice.ampq.pubsub.RabbitMqConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

public class Sender {

    @Autowired
    private RabbitTemplate template;

    @Scheduled(fixedDelay = 5000, initialDelay = 500)
    public void send() {
        EventPayload eventPayload = new EventPayload();
        eventPayload.setEventName("HR_SYNC_COMPLETE");

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("org_id", 51533);
        eventData.put("spaceId", 45727);
        eventData.put("changed",new String[]{"USER"});
        eventPayload.setData(eventData);

        String message = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            message = mapper.writeValueAsString(eventPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        template.convertAndSend(RabbitMqConstants.EXCHANGE_NAME, RabbitMqConstants.ROUTING_KEY, message); // 모든 Sender는 Queue에 직접 송신하지 않고, exchange를 지정하여 거기로 송신.
        System.out.println("Publisher - Sent '" + message + "'");

    }

}
