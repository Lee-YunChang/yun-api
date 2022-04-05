package com.yunapi.controller.api;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.MemberDto;
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

        List<ItemDto> itemList = itemService.itemList();
        return  ResponseEntity.ok().body(itemList);
    }

    @GetMapping(value = "/{id}")    //Id로 select
    public ResponseEntity<Item> findById(@PathVariable("id") long id){
        Item item  = itemService.findById(id);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping(value = "/save")   //Save
    public ResponseEntity<?> save(@RequestBody MemberDto memberDto){

        return ResponseEntity.ok().body(itemService.save(memberDto));
    }

    @DeleteMapping(value = "/delete/{id}")  //Delete
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return  ResponseEntity.ok().body(itemService.delete(id));
    }

    @PatchMapping(value = "/update/{id}")    //Update
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody MemberDto memberDto){
        return ResponseEntity.ok().body(itemService.update(id,memberDto));
    }
}
