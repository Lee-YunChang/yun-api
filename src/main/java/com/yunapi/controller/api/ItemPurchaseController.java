package com.yunapi.controller.api;

import com.yunapi.domain.dto.ItemDto;
import com.yunapi.domain.dto.ItemPurchaseDto;
import com.yunapi.domain.request.ItemPurchaseRequest;
import com.yunapi.domain.request.ItemRequest;
import com.yunapi.domain.search.ItemSearch;
import com.yunapi.service.api.ItemPurchaseService;
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
public class ItemPurchaseController {

    private final ItemPurchaseService itemPurchaseService;

    @GetMapping(value = "/count")
    public ResponseEntity<?> count(){
        return ResponseEntity.ok().body(itemPurchaseService.count());
    }

    @GetMapping(value = "")  //List select
    public ResponseEntity<List<ItemPurchaseDto>> itemList(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){

        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.DESC,"id");

        List<ItemPurchaseDto> itemPurchaseList = itemPurchaseService.itemPurchaseList( pageable);
        return  ResponseEntity.ok().body(itemPurchaseList);
    }

    @GetMapping(value = "/{id}")    //Id로 select
    public ResponseEntity<ItemPurchaseDto> findById(@PathVariable("id") long id){
        Optional<ItemPurchaseDto> oItemPurchase = itemPurchaseService.findById(id);
        if(oItemPurchase.isPresent()) {
            return ResponseEntity.ok().body(oItemPurchase.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")   //Save
    public ResponseEntity<?> save(@RequestBody ItemPurchaseRequest itemPurchaseRequest){

        return ResponseEntity.ok().body(itemPurchaseService.save(itemPurchaseRequest));
    }

    @DeleteMapping(value = "/delete/{id}")  //Delete
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return  ResponseEntity.ok().body(itemPurchaseService.delete(id));
    }

    @PatchMapping(value = "/update/{id}")    //Update
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody ItemPurchaseRequest itemPurchaseRequest){
        return ResponseEntity.ok().body(itemPurchaseService.update(id,itemPurchaseRequest));
    }
}
