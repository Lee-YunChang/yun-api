package com.yunapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
@Table(name = "item")
@Entity
@DynamicUpdate
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    private String itemName;

    private Long itemPrice;

    private String itemNumber;

    private String delYn;

    @ToString.Exclude
    @JoinColumn(name = "item_id")
    @OneToMany
    private List<ItemOption> itemOptions = new ArrayList<>();


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

    @PreUpdate
    protected void preUpdate() {
        this.updateTimestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    @Builder
    public Item(String itemName, Long itemPrice,String itemNumber){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemNumber = itemNumber;
    }
}
