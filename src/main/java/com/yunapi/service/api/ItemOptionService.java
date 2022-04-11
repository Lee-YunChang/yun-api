package com.yunapi.service.api;

import com.yunapi.domain.dto.ItemOptionDto;
import com.yunapi.domain.request.ItemDetailRequest;
import com.yunapi.entity.ItemOption;
import com.yunapi.repository.ItemOptionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemOptionService {

    private final ItemOptionRepository itemOptionRepository;

    public long count() {
        return 0;
    }

    public List<ItemOptionDto> itemList() {
        return itemOptionRepository.findAll().stream().map(ItemOptionDto::new).collect(Collectors.toList());
    }

    public Optional<ItemOptionDto> findById(long id) {
        return itemOptionRepository.findById(id).map(ItemOptionDto::new);
    }

    @Transactional
    public int save(ItemDetailRequest value) {
        ItemOption. ItemOptionBuilder builder = ItemOption.builder();
        if (value.getItem() != null) builder.item(value.getItem());
        if (StringUtils.isNotBlank(value.getColor())) builder.color(value.getColor());
        if (StringUtils.isNotBlank(value.getSize())) builder.size(value.getSize());
        if (value.getItemInventory() != null) builder.itemInventory(value.getItemInventory());
        return itemOptionRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int delete(long id) {
        ItemOption itemDetail = itemOptionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 id값이 존재하지않습니다"));
        itemDetail.setDelYn("Y");
        return itemOptionRepository.save(itemDetail) != null ? 1 : 0;
    }

    @Transactional
    public int update(long id, ItemOptionDto itemDetailDto) {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        itemOptionRepository.findById(id).ifPresent(s -> {
            s.setItem(itemDetailDto.getItem());
            s.setColor(itemDetailDto.getColor());
            s.setSize(itemDetailDto.getSize());
            s.setItemInventory(itemDetailDto.getItemInventory());
            itemOptionRepository.save(s);
            result.set(1);
        });
        return result.get();
    }
}