package com.yunapi.service.item;

import com.yunapi.repository.ItemPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemPurchaseService {

    private final ItemPurchaseRepository itemPurchaseRepository;
}
