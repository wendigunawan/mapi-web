package com.mapi.ihrd.module.news.controller;


import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.module.news.form.NewsForm;
import com.mapi.ihrd.module.news.model.News;
import com.mapi.ihrd.module.news.service.NewsService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("/web/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;


    @Value("${app.path.upload}")
    private String uploadPath;

    @GetMapping
    public String index() {
        return "news/news.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(@RequestParam Integer start,
                                     @RequestParam Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = createPageRequest(start, length);

        Page<News> newsPage = newsService.find(pageRequest);
        Collection<News> news = newsPage.getContent();

        return new JQueryDataTable(news, newsPage.getTotalElements(), draw);
    }

    @GetMapping("/add")
    public String add(Model model) {
        NewsForm form = new NewsForm();
        model.addAttribute("news", form);
        return "news/news.form";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        News news = newsService.getById(id);

        NewsForm form = new NewsForm();
        form.setId(news.getId());
        form.setTitle(news.getTitle());
        form.setHeadline(news.getHeadline());
        form.setContent(news.getContent());

        model.addAttribute("news", form);

        return "news/news.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute NewsForm form) {

        File f = new File(uploadPath + "/news");
        if (!f.exists() || (f.exists() && !f.isDirectory())) {
            if (!f.mkdir()) {
                throw new BusinessException("Gagal membuat directory");
            }
        }

        try {
            String path = f.getAbsolutePath() + "/" + form.getFile().getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(path);
            FileCopyUtils.copy(form.getFile().getInputStream(), fos);
            form.setPicture(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (StringUtils.hasLength(form.getId())) {
            newsService.edit(form);
        } else {
            String id = newsService.add(form);
            form.setId(id);
        }

        return ResponseEntity.ok(form);
    }


}
