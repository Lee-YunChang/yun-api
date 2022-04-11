package com.yunapi.domain.request;

import lombok.Data;

@Data
public class ItemRequest {

    private String itemName;
    private Long itemPrice;
    private String itemNumber;
}
