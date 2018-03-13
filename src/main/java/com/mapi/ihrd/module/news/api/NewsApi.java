package com.mapi.ihrd.module.news.api;


import com.mapi.ihrd.module.news.form.NewsForm;
import com.mapi.ihrd.module.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsApi {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {

        if (limit == null) {
            limit = Integer.valueOf(10);
        }

        if (offset == null) {
            offset = Integer.valueOf(0);
        }

        return ResponseEntity.ok(newsService.find(limit, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetail(@PathVariable String id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody NewsForm form) {
        String id = newsService.add(form);
        form.setId(id);
        return ResponseEntity.ok(form);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestBody NewsForm form) {
        form.setId(id);
        newsService.edit(form);
        return ResponseEntity.ok().build();
    }


}
