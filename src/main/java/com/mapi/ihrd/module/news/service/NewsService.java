package com.mapi.ihrd.module.news.service;

import com.mapi.ihrd.module.news.form.NewsForm;
import com.mapi.ihrd.module.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface NewsService {

    News getById(String id);

    String add(NewsForm form);

    void edit(NewsForm form);

    Page<News> find(int limit, int offset);

    Collection<News> find();

    Page<News> find(PageRequest pageRequest);

}
