package com.yunapi.domain.specs;

import com.yunapi.domain.entity.Item;
import com.yunapi.domain.entity.User;
import com.yunapi.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@SuppressWarnings("serial")
public class ItemSpecs {


    public static Specification<Item> idEquals(final Long id) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return id == null ? null :
                        criteriaBuilder.equal(root.get("id"), id);
            }
        };
    }

    public static Specification<Item> ItemNameLike(final String itemName) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return StringUtils.isBlank(itemName) ? null :
                        criteriaBuilder.like(root.get("itemName"), '%' + itemName +'%');
            }
        };
    }
    public static Specification<Item>ItemNumberLike(final String itemNumber) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return StringUtils.isBlank(itemNumber) ? null :
                        criteriaBuilder.like(root.get("itemNumber"), '%' + itemNumber +'%');
            }
        };
    }

    public static Specification<Item> itemPriceGreaterThanOrEqualTo(final Long priceFrom) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return priceFrom == null ? null :
                        criteriaBuilder.greaterThanOrEqualTo(root.get("itemPrice"), priceFrom);
            }
        };
    }

    public static Specification<Item> itemPriceLessThanOrEqualTo(final Long priceTo) {
        return new Specification<Item>() {
            @Override
            public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return priceTo == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(root.get("itemPrice"), priceTo);
            }
        };
    }
}
