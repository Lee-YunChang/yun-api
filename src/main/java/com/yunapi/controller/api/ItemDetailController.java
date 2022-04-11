package com.yunapi.controller.api;

import com.yunapi.domain.dto.ItemDetailDto;
import com.yunapi.domain.dto.ItemOptionDto;
import com.yunapi.domain.request.ItemDetailRequest;
import com.yunapi.service.api.ItemOptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "ItemDetail", description = "상품 상세")
@RequiredArgsConstructor
@RestController
@RequestMapping("/itemDetail")
public class ItemDetailController {

    private final ItemOptionService itemDetailService;

    @GetMapping(value = "/count")
    public ResponseEntity<?> count(){
        return ResponseEntity.ok().body(itemDetailService.count());
    }

    @GetMapping(value = "")  //List select
    public ResponseEntity<List<ItemOptionDto>> itemDetailList(){

        List<ItemOptionDto> itemDetailDtoList = itemDetailService.itemList();
        return  ResponseEntity.ok().body(itemDetailDtoList);
    }

    @GetMapping(value = "/{id}")    //Id로 select
    public ResponseEntity<ItemOptionDto> findById(@PathVariable("id") long id){
        Optional<ItemOptionDto> oitemDetailDto = itemDetailService.findById(id);
        if(oitemDetailDto.isPresent()) {
            return ResponseEntity.ok().body(oitemDetailDto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/save")   //Save
    public ResponseEntity<?> save(@RequestBody ItemDetailRequest itemDetailRequest){

        return ResponseEntity.ok().body(itemDetailService.save(itemDetailRequest));
    }

    @DeleteMapping(value = "/delete/{id}")  //Delete
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        return  ResponseEntity.ok().body(itemDetailService.delete(id));
    }

    @PatchMapping(value = "/update/{id}")    //Update
    public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody ItemOptionDto ItemDetailDto){
        return ResponseEntity.ok().body(itemDetailService.update(id,ItemDetailDto));
    }
}
