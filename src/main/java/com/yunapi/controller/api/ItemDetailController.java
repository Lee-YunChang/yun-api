package com.yunapi.controller.api;

import com.yunapi.service.api.ItemDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "상품")
@RequiredArgsConstructor
@RestController
public class ItemDetailController {

    private ItemDetailService itemDetailService;
}
