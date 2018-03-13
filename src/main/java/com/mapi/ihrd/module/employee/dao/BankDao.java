package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDao extends JpaRepository<Bank, String>{

    Page<Bank> findByDeleted(boolean deleted, Pageable pageable);

}
