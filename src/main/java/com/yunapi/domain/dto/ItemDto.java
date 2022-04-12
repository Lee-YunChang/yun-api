package com.yunapi.domain.dto;

import com.yunapi.domain.entity.Item;
import com.yunapi.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

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
            this.createTimestamp = DateUtils.SDF2.format(a.getCreateTimestamp());
        if(a.getUpdateTimestamp() != null)
            this.updateTimestamp = DateUtils.SDF2.format(a.getUpdateTimestamp());
    }
}
