package io.torres.practice.ampq.subscribe;

import com.google.gson.Gson;
import io.torres.practice.ampq.pubsub.EventPayload;
import io.torres.practice.ampq.pubsub.HrEventName;
import io.torres.practice.ampq.pubsub.HrEventType;
import io.torres.practice.repository.MemberRepository;
import io.torres.practice.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Receiver {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReceiverService receiverService;

    public void receive(String message) {

        // 기록하기 위한 시간
        Timestamp startTime = new Timestamp(System.currentTimeMillis());

        Gson gson = new Gson();
        EventPayload eventPayload = gson.fromJson(message, EventPayload.class);
        Map<String, Object> dataMap = eventPayload.getData();
        try {
            HrEventName eventName = HrEventName.valueOf(eventPayload.getEventName());
            Long cmpId = Double.valueOf((double)dataMap.get("org_id")).longValue();

            if (eventName.equals(HrEventName.HR_SYNC_COMPLETE)) { // 배치 완료에 대한 배치 (array인 chaged의 갯수만큼 event 테이블에 입력)

                List<String> eventTypeList = (List<String>) dataMap.get("changed");

                eventTypeList.sort(Comparator.naturalOrder());

                eventTypeList.forEach( hrEventType -> {
                    boolean result = false;
                    //HrEvent entity = hrEventService.insertHrEvent(cmpId, eventName, HrEventType.valueOf(hrEventType));
                    try{
                        switch (HrEventType.valueOf(hrEventType)) {
                            case COMPANY:
                                //result = receiverService.syncOneCompany(cmpId, 0);
                                break;
                            default:
                                result = false;
                                break;
                        }
                    } catch (Exception e) {
                        result = false;
                    } finally {
                        //이벤트 성공/실패 여부 업데이트
                        //hrEventService.updateHrEvent(entity.getSeq(), result);
                    }
                });
                Timestamp endTime = new Timestamp(System.currentTimeMillis());
                // 배치 시간 체크를 위한 알림 (5초 이상 건에 대해서만)
                if((endTime.getTime() - startTime.getTime())/1000.000 >= 1) {
                    int cntMembers = 1;
                }

            } else { // 나머지 동기화 : 하나의 event에 대해서 evnet 테이블에 넣기. : 이전에 만들어진 동기화.
                HrEventType eventType = HrEventType.COMPANY_CREATED;
                if (eventName.equals(HrEventName.HR_ORG_INFO_CHANGED)) {
                    eventType = HrEventType.valueOf((String) dataMap.get("changed"));
                }
                //HrEvent entity = hrEventService.insertHrEvent(cmpId, eventName, eventType);

                boolean result = false;
                if (eventName.equals(HrEventName.HR_COMPANY_CREATED)) {
                    // 법인 생성
                    try {
                        //receiverService.syncFirstCompany(cmpId); // 하나의 회사 동기화
                        result = true;
                    } catch (Exception e) {
                        //LogMakerUtil.warnWriter(e.getMessage(),e);
                    }
                } else if (eventName.equals(HrEventName.HR_ORG_INFO_CHANGED)) {
                    // MANAGER(관리자) & SECURE(보안설정) 실시간 처리
                    switch (eventType) {
                        case MANAGER:
                            //result = receiverService.syncManager(cmpId, 0);
                            break;
                        default:
                            result = false;
                            break;
                    }
                }

                //이벤트 성공/실패 여부 업데이트
                //hrEventService.updateHrEvent(entity.getSeq(), result);
            }
        } catch (Exception e){
        }finally {
        }
    }
}
