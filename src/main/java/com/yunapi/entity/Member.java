package com.yunapi.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter@Setter
@Table(name = "member")
@Entity
@DynamicUpdate@DynamicInsert
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;


    @Builder
    public  Member (String username, String phone){
        this.username = username;
        this.phone = phone;
    }

}
