package com.yunapi.domain.search;

import com.yunapi.domain.specs.ItemSpecs;
import com.yunapi.domain.entity.Item;
import com.yunapi.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter @Setter
public class ItemSearch {

    private String itemName;
    private String itemNumber;
    private Long priceFrom;
    private Long priceTo;

    public Specification<Item> toSpecification() {
        return  Specification.where(ItemSpecs.ItemNameLike(itemName))
                .and(ItemSpecs.ItemNumberLike(itemNumber))
                .and(ItemSpecs.itemPriceGreaterThanOrEqualTo(priceFrom))
                .and(ItemSpecs.itemPriceLessThanOrEqualTo(priceTo));
    }
}
