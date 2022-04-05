package com.yunapi.domain.dto;

import com.yunapi.entity.Item;
import com.yunapi.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class ItemDto {

    private Long id;
    private String itemName;
    private Long itemPrice;
    private String itemNumber;
    private String delYn;
    private String createTimestamp;
    private String updateTimestamp;

    public ItemDto(Item a){
        this.id = a.getId();
        this.itemName = a.getItemName();
        this.itemPrice = a.getItemPrice();
        this.itemNumber = a.getItemNumber();
        this.delYn = a.getDelYn();
        if(a.getCreateTimestamp() != null)
            this.createTimestamp = DateUtils.SDF.format(a.getCreateTimestamp());
        if(a.getUpdateTimestamp() != null)
            this.updateTimestamp = DateUtils.SDF.format(a.getUpdateTimestamp());
    }
}
