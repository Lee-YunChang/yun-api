package com.yunapi.service.item;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.MemberDto;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.entity.Item;
import com.yunapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    ItemRepository itemRepository;

    public long count(ItemSearch search) {
        return 0;
    }

    public List<ItemDto> itemList() {
        return null;
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
