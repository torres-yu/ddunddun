package io.torres.practice.ampq.pubsub;

// 상수 모음.
public class RabbitMqConstants {

    private RabbitMqConstants(){}

    // FanoutExchange 를 쓰므로 routing Key는 DEFAULT을 사용.
    public final static String EXCHANGE_NAME = "itif_hr.exchange";
    public final static String QUEUE_NAME = "hrScheduleSyncQueue6";
    public final static String ROUTING_KEY = "schedule.activate";

}
