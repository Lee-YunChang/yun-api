package com.yunapi.service.api;

import com.yunapi.domain.dto.ItemPurchaseDto;
import com.yunapi.domain.request.ItemPurchaseRequest;
import com.yunapi.domain.entity.ItemOption;
import com.yunapi.domain.entity.ItemPurchase;
import com.yunapi.domain.entity.User;
import com.yunapi.exception.InvalidInputException;
import com.yunapi.repository.ItemOptionRepository;
import com.yunapi.repository.ItemPurchaseRepository;
import com.yunapi.repository.UserRepository;
import com.yunapi.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemPurchaseService {

    private final UserRepository userRepository;
    private final ItemPurchaseRepository itemPurchaseRepository;
    private final ItemOptionRepository itemOptionRepository;

    public long count() {
        return 0;
    }

    public List<ItemPurchaseDto> itemPurchaseList(Pageable pageable) {
        return itemPurchaseRepository.findAll(pageable).getContent()
                .stream().map(ItemPurchaseDto::new).collect(Collectors.toList());
    }

    public Optional<ItemPurchaseDto> findById(long id) {
        return itemPurchaseRepository.findById(id).map(ItemPurchaseDto::new);
    }

    @Transactional
    public int save(ItemPurchaseRequest itemPurchaseRequest) {

        User user = userRepository.findById(itemPurchaseRequest.getUserId()).orElseThrow(()->  new InvalidInputException(MessageUtils.INVALID_USER_ID));
        ItemOption itemOption = itemOptionRepository.findById(itemPurchaseRequest.getItemDetailId()).orElseThrow(() -> new InvalidInputException("해당하는 id값이 존재하지않습니다"));


        ItemPurchase.ItemPurchaseBuilder builder = ItemPurchase.builder();
        if(user != null) builder.user(user);
        if(itemOption != null) builder.itemOption(itemOption);
        return itemPurchaseRepository.save(builder.build())!= null ? 1 : 0 ;
    }

    @Transactional
    public int delete(long id) {
        ItemPurchase itemPurchase = itemPurchaseRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당하는 id값이 존재하지않습니다"));
        itemPurchase.setDelYn("Y");
        return itemPurchaseRepository.save(itemPurchase) != null ? 1 : 0;
    }

    @Transactional
    public int update(long id, ItemPurchaseRequest value) {
        AtomicReference<Integer> result = new AtomicReference<>(0);
        ItemOption itemOption = itemOptionRepository.findById(value.getItemDetailId()).orElseThrow(() -> new InvalidInputException("해당하는 id값이 존재하지않습니다"));

        itemPurchaseRepository.findById(id).ifPresent(s -> {
            s.setItemOption(itemOption);
            itemPurchaseRepository.save(s);
            result.set(1);
        });
        return result.get();
    }
}
