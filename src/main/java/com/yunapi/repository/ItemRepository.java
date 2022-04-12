package com.yunapi.repository;

import com.yunapi.domain.entity.Item;
import com.yunapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemRepository extends JpaRepository<Item, Long> , JpaSpecificationExecutor<Item> {
}
