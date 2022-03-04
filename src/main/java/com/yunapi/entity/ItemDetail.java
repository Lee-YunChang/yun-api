package com.yunapi.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "item")
@Entity
@DynamicUpdate
@DynamicInsert
public class ItemDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private String size;

    private String color;

    private String status;

    private String delYn;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name ="create_timestamp")
    private Timestamp createTimestamp;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name="update_timestamp")
    private Timestamp updateTimestamp;

    @PrePersist
    protected void onCreate() {
        this.createTimestamp = Timestamp.valueOf(LocalDateTime.now());
        this.delYn = "N";
    }
}
