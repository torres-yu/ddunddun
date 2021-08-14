package io.torres.practice.service;

import io.torres.practice.ampq.pubsub.HrEventType;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ReceiverService {



    public void syncHrEvent() {
        LocalDateTime hoursAgo12 = LocalDateTime.now().minusHours(12);
        List<HrEventType> batchEventTypeList = Arrays.asList(HrEventType.COMPANY, HrEventType.DEPARTMENT, HrEventType.USER, HrEventType.MANAGER, HrEventType.HOLIDAY);


    }
}
