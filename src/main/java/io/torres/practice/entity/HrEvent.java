package io.torres.practice.entity;


import io.torres.practice.ampq.pubsub.HrEventName;
import io.torres.practice.ampq.pubsub.HrEventType;
import io.torres.practice.code.ResultCode;
import io.torres.practice.code.YesOrNoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hr_event")
@Getter
@Setter
public class HrEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, name = "seq")
    private Long seq;    // 이벤트_시퀀

    @Column(length = 20, nullable = false, name = "cmp_id")
    private Long cmpId;    // 회사_ID

    @Column(length = 100, nullable = false, name = "event_name")
    @Enumerated(EnumType.STRING)
    private HrEventName eventName;    // 이벤트_명

    @Column(length = 30, nullable = false, name = "event_type")
    @Enumerated(EnumType.STRING)
    private HrEventType eventType;    // 이벤트_명

    @Column(length = 1, nullable = false, name = "proc_yn")
    @ColumnDefault("N")
    @Enumerated(EnumType.STRING)
    private YesOrNoEnum procYn;    // 처리_여부

    @Column(length = 10, name = "proc_result")
    @Enumerated(EnumType.STRING)
    private ResultCode procResult;    // 처리_결과


}

