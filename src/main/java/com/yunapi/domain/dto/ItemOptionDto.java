package com.yunapi.domain.dto;

import com.yunapi.domain.entity.Item;
import com.yunapi.domain.entity.ItemOption;
import com.yunapi.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemOptionDto {

    private Long id;
    private Item item;
    private String size;
    private String color;
    private Integer itemInventory;
    private String status;
    private String delYn;
    private String createTimestamp;
    private String updateTimestamp;

    public ItemOptionDto(ItemOption a){
        this.id = a.getId();
        this.item = a.getItem();
        this.size = a.getSize();
        this.color = a.getColor();
        this.itemInventory = a.getItemInventory();
        this.status = a.getStatus();
        this.delYn = a.getDelYn();
        if(a.getCreateTimestamp() != null)
            this.createTimestamp = DateUtils.SDF.format(a.getCreateTimestamp());
        if(a.getUpdateTimestamp() != null)
            this.updateTimestamp = DateUtils.SDF.format(a.getUpdateTimestamp());
    }
}
