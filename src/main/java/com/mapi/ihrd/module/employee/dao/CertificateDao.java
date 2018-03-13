package com.mapi.ihrd.module.employee.dao;

import com.mapi.ihrd.module.employee.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateDao extends JpaRepository<Certificate, String> {
}
