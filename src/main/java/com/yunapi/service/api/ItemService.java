package com.yunapi.service.api;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.MemberDto;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.entity.Item;
import com.yunapi.repository.ItemRepository;
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
public class ItemService {

    ItemRepository itemRepository;

    public long count(ItemSearch search) {
        return 0;
    }

    public List<ItemDto> itemList(ItemSearch search, Pageable pageable) {
        return itemRepository.findAll(search.toSpecification(), pageable).getContent()
                .stream().map(ItemDto::new).collect(Collectors.toList());
    }

    public Optional<ItemDto> findById(long id) {
        return itemRepository.findById(id).map(ItemDto::new);
    }

    @Transactional
    public int save(ItemDto itemDto) {
        Item.ItemBuilder builder = Item.builder();
        if(StringUtils.isNotBlank(itemDto.getItemName())) builder.itemName(itemDto.getItemName());
        if(itemDto != null) builder.itemPrice(itemDto.getItemPrice());
        if(StringUtils.isNotBlank(itemDto.getItemNumber())) builder.itemNumber(itemDto.getItemNumber());
        return itemRepository.save(builder.build())!= null ? 1 : 0 ;
    }

    @Transactional
    public int delete(long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당하는 id값이 존재하지않습니다"));
        item.setDelYn("Y");
        return itemRepository.save(item) != null ? 1 : 0;
    }

    @Transactional
    public int update(long id, ItemDto itemDto) {
        AtomicReference<Integer> result = new AtomicReference<>(0);

        itemRepository.findById(id).ifPresent(s -> {
            s.setItemName(itemDto.getItemName());
            s.setItemPrice(itemDto.getItemPrice());
            s.setItemNumber(itemDto.getItemNumber());
            itemRepository.save(s);
            result.set(1);
        });
        return result.get();
    }
}
