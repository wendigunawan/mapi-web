package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.model.BaseModel;
import com.mapi.ihrd.module.employee.model.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

public interface BankService {
    Collection<Bank> find();

    void save(Bank bank);

    Bank getById(String id);


    Page<Bank> find(PageRequest pageRequest);
}
