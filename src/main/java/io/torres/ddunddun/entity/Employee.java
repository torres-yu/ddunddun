package io.torres.ddunddun.entity;

import io.torres.ddunddun.code.YesOrNoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Employee {

    @Id
    @Column(name = "seq")
    private Long seq;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "pass")
    private String pass;

    @Column(name = "user_name")
    private String userName;
}
