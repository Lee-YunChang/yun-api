package com.yunapi.controller.item;

import com.yunapi.service.item.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "상품")
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;
}
