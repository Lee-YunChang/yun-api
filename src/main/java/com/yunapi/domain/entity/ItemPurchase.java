package com.yunapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
public class ItemPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", nullable = false)
    private User user;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_option_id", nullable = false)
    private ItemOption itemOption;

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

    @PreUpdate
    protected void preUpdate() {
        this.updateTimestamp = Timestamp.valueOf(LocalDateTime.now());
    }


    @Builder
    public ItemPurchase(User user, ItemOption itemOption){
        this.user = user;
        this.itemOption = itemOption;
    }
}
