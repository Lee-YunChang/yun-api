package com.yunapi.domain.dto;

import com.yunapi.domain.entity.ItemOption;
import com.yunapi.domain.entity.ItemPurchase;
import com.yunapi.domain.entity.User;
import com.yunapi.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemPurchaseDto {

    private Long id;
    private User user;
    private ItemOption itemOption;
    private String delYn;
    private String createTimestamp;
    private String updateTimestamp;

    public ItemPurchaseDto(ItemPurchase a){
        this.id = a.getId();
        this.user = a.getUser();
        this.itemOption = a.getItemOption();
        this.delYn = a.getDelYn();
        if(a.getCreateTimestamp() != null)
            this.createTimestamp = DateUtils.SDF2.format(a.getCreateTimestamp());
        if(a.getUpdateTimestamp() != null)
            this.updateTimestamp = DateUtils.SDF2.format(a.getUpdateTimestamp());
    }
}
