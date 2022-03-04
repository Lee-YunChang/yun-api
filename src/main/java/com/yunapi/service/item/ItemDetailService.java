package com.yunapi.service.item;

import com.yunapi.repository.ItemDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemDetailService {

    private final ItemDetailRepository itemDetailRepository;
}
