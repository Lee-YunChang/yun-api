package com.yunapi.service.api;

import com.yunapi.domain.dto.ItemDetailDto;
import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.request.ItemDetailRequest;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.entity.Item;
import com.yunapi.entity.ItemDetail;
import com.yunapi.repository.ItemDetailRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemDetailService {

    private final ItemDetailRepository itemDetailRepository;

    public long count() {
        return 0;
    }

    public List<ItemDetailDto> itemList() {
        return itemDetailRepository.findAll().stream().map(ItemDetailDto::new).collect(Collectors.toList());
    }

    public Optional<ItemDetailDto> findById(long id) {
        return itemDetailRepository.findById(id).map(ItemDetailDto::new);
    }

    @Transactional
    public int save(ItemDetailRequest value) {
        ItemDetail.ItemDetailBuilder builder = ItemDetail.builder();
        if (value.getItem() != null) builder.item(value.getItem());
        if (StringUtils.isNotBlank(value.getColor())) builder.color(value.getColor());
        if (StringUtils.isNotBlank(value.getSize())) builder.size(value.getSize());
        if (value.getItemInventory() != null) builder.itemInventory(value.getItemInventory());
        return itemDetailRepository.save(builder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int delete(long id) {
        ItemDetail itemDetail = itemDetailRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 id값이 존재하지않습니다"));
        itemDetail.setDelYn("Y");
        return itemDetailRepository.save(itemDetail) != null ? 1 : 0;
    }

    @Transactional
    public int update(long id, ItemDetailDto itemDetailDto) {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        itemDetailRepository.findById(id).ifPresent(s -> {
            s.setItem(itemDetailDto.getItem());
            s.setColor(itemDetailDto.getColor());
            s.setSize(itemDetailDto.getSize());
            s.setItemInventory(itemDetailDto.getItemInventory());
            itemDetailRepository.save(s);
            result.set(1);
        });
        return result.get();
    }
}