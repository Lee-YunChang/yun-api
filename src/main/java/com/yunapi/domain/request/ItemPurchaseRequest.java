package com.yunapi.domain.request;

import lombok.Data;

@Data
public class ItemPurchaseRequest {

    private Long userId;
    private Long itemDetailId;

}
