package com.mapi.ihrd.module.news.service.impl;

import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.news.dao.NewsDao;
import com.mapi.ihrd.module.news.form.NewsForm;
import com.mapi.ihrd.module.news.model.News;
import com.mapi.ihrd.module.news.service.NewsService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.Collection;


@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public News getById(String id) {
        return newsDao.getOne(id);
    }

    @Override
    public String add(@Valid NewsForm form) {

        News news = new News();
        news.setContent(form.getContent());
        news.setHeadline(form.getHeadline());
        news.setTitle(form.getTitle());
        news.setPostDate(DateUtil.now());
        news.setImageUrl(form.getPicture());
        news.setCreatedDate(DateUtil.now());
        news.setUpdatedDate(DateUtil.now());

        newsDao.save(news);

        return news.getId();
    }

    @Override
    public void edit(NewsForm form) {

        if (!StringUtils.hasLength(form.getId())) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        News news = newsDao.findOne(form.getId());

        if (news == null) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        boolean anyChanges = false;
        if (StringUtils.hasLength(form.getTitle())) {
            anyChanges = true;
            news.setTitle(form.getTitle());
        }

        if (StringUtils.hasLength(form.getContent())) {
            anyChanges = true;
            news.setContent(form.getContent());
        }

        if (StringUtils.hasLength(form.getContent())) {
            anyChanges = true;
            news.setHeadline(form.getHeadline());
        }

        news.setImageUrl(form.getPicture());

        if (anyChanges == false) {
            throw new BusinessException("Tidak ada perubahan data yang harus disimpan");
        }

        news.setUpdatedDate(DateUtil.now());

        newsDao.save(news);

    }


    @Override
    public Page<News> find(int limit, int offset) {

        Pageable pageable = new PageRequest(offset, limit, Sort.Direction.DESC, "createdDate");

        return newsDao.findAll(pageable);
    }

    @Override
    public Collection<News> find() {
        return newsDao.findAll();
    }

    @Override
    public Page<News> find(PageRequest pageRequest) {
        return newsDao.findAll(pageRequest);
    }
}
