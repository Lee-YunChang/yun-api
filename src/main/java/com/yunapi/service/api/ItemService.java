package com.yunapi.service.api;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.MemberDto;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.entity.Item;
import com.yunapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemService {

    ItemRepository itemRepository;

    public long count(ItemSearch search) {
        return 0;
    }

    public List<ItemDto> itemList() {
        return itemRepository.findAll().stream().map(ItemDto::new).collect(Collectors.toList());
    }

    public Item findById(long id) {
        return null;
    }

    public Object save(MemberDto memberDto) {
        return null;
    }

    public Object delete(long id) {
        return null;
    }

    public Object update(long id, MemberDto memberDto) {
        return null;
    }
}
