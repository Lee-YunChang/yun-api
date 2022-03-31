package com.yunapi.repository;

import com.yunapi.entity.ExceptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionHistoryRepository extends JpaRepository<ExceptionHistory, Long> {
}
