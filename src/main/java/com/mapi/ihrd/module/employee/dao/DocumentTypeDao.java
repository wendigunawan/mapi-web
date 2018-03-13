package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeDao extends JpaRepository<DocumentType, String> {
}
