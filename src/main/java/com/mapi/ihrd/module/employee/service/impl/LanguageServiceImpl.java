package com.mapi.ihrd.module.employee.service.impl;

import com.mapi.ihrd.module.employee.dao.LanguageDao;
import com.mapi.ihrd.module.employee.model.Language;
import com.mapi.ihrd.module.employee.model.QLanguage;
import com.mapi.ihrd.module.employee.service.LanguageService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    LanguageDao languageDao;

    @Override
    public Language getById(String id) {
        QLanguage language = QLanguage.language;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(language.deleted.eq(false)).and(language.id.eq(id));

        return languageDao.findOne(builder);
    }

    @Override
    public Page<Language> find(PageRequest pageRequest) {
        QLanguage language = QLanguage.language;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(language.deleted.eq(false));
        return languageDao.findAll(builder, pageRequest);
    }

    @Override
    public void save(Language language) {
        languageDao.save(language);
    }

    @Override
    public Iterable<Language> findAll() {
        QLanguage language = QLanguage.language;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(language.deleted.eq(false));

        return languageDao.findAll(builder);
    }
}
