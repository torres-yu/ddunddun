package io.torres.practice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
