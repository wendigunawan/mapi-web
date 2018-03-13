package com.mapi.ihrd.module.employee.service;

import com.mapi.ihrd.module.employee.model.Language;
import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface LanguageService {
    Language getById(String id);

    Page<Language> find(PageRequest pageRequest);

    void save(Language language);

    Iterable<Language> findAll();
}
