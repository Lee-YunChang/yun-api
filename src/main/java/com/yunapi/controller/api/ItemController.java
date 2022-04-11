package com.yunapi.controller.api;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.MemberDto;
import com.yunapi.domain.request.ItemRequest;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.domain.search.UserSearch;
import com.yunapi.entity.Item;
import com.yunapi.service.api.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Item", description = "상품")
@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/count")
    public ResponseEntity<?> count(@ModelAttribute ItemSearch search){
        return ResponseEntity.ok().body(itemService.count(search));
    }

    @GetMapping(value = "")  //List select
    public ResponseEntity<List<ItemDto>> itemList(@ModelAttribute("searchValue") ItemSearch itemSearch,
            @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){

        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.DESC,"id");

        List<ItemDto> itemList = itemService.itemList(itemSearch, pageable);
        return  ResponseEntity.ok().body(itemList);
    }

    @GetMapping(value = "/{id}")    //Id로 select
    public ResponseEntity<ItemDto> findById(@PathVariable("id") long id){
        Optional<ItemDto> opItemDto = itemService.findById(id);
        if(opItemDto.isPresent()) {
            return ResponseEntity.ok().body(opItemDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")   //Save
    public ResponseEntity<?> save(@RequestBody ItemRequest itemRequest){

        return ResponseEntity.ok().body(itemService.save(itemRequest));
    }

    @DeleteMapping(value = "/delete/{id}")  //Delete
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return  ResponseEntity.ok().body(itemService.delete(id));
    }

    @PatchMapping(value = "/update/{id}")    //Update
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody ItemDto itemDto){
        return ResponseEntity.ok().body(itemService.update(id,itemDto));
    }
}
