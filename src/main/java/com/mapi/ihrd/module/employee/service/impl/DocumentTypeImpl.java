package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.DocumentTypeDao;
import com.mapi.ihrd.module.employee.model.DocumentType;
import com.mapi.ihrd.module.employee.service.DocumentTypeService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DocumentTypeImpl implements DocumentTypeService {

    @Autowired
    DocumentTypeDao documentTypeDao;

    @Override
    public Collection<DocumentType> find() {
        return documentTypeDao.findAll();
    }

    @Override
    public void save(DocumentType documentType) {
        documentType.setCreatedDate(DateUtil.now());
        documentTypeDao.save(documentType);
    }

    @Override
    public DocumentType getById(String id) {
        return documentTypeDao.findOne(id);
    }

    @Override
    public Page<DocumentType> find(PageRequest pageRequest) {
        return documentTypeDao.findAll(pageRequest);
    }
}
