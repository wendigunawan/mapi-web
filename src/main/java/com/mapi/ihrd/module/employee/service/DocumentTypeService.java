package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Bank;
import com.mapi.ihrd.module.employee.model.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface DocumentTypeService {
    Collection<DocumentType> find();

    void save(DocumentType documentType);

    DocumentType getById(String id);


    Page<DocumentType> find(PageRequest pageRequest);
}
