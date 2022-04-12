package com.yunapi.domain.request;

import com.yunapi.domain.entity.Item;
import lombok.Data;

@Data
public class ItemDetailRequest {

    private Item item;
    private String size;
    private String color;
    private Integer itemInventory;
    private String status;

}
