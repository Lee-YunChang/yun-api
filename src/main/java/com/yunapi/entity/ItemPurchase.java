package com.yunapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "item")
@Entity
@DynamicUpdate
@DynamicInsert
public class ItemPurchase {

    @Id
    @GeneratedValue
    private Long id;
    

}
