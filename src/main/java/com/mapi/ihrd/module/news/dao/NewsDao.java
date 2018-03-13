package com.mapi.ihrd.module.news.dao;

import com.mapi.ihrd.module.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface NewsDao extends JpaRepository<News, String> {
}
