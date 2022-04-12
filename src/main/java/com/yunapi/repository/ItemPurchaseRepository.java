package com.yunapi.repository;

import com.yunapi.domain.entity.ItemPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchase, Long> {
}
