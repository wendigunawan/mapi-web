package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.BankDao;
import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.service.BankService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    @Autowired
    BankDao bankDao;

    @Override
    public Collection<Bank> find() {
        return bankDao.findAll();
    }

    @Override
    public void save(Bank bank) {
        bank.setCreatedDate(DateUtil.now());
        bankDao.save(bank);
    }

    @Override
    public Bank getById(String id) {
        return bankDao.findOne(id);
    }

    @Override
    public Page<Bank> find(PageRequest pageRequest) {
        return bankDao.findByDeleted(false, pageRequest);
    }
}
