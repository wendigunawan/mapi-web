package com.mapi.ihrd.module.performance.api;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.module.performance.queryfilter.ComponentQueryFilter;
import com.mapi.ihrd.module.performance.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/component")
public class ComponentApi extends BaseController{

    @Autowired
    ComponentService componentService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {

        if (limit == null) {
            limit = Integer.valueOf(10);
        }

        if (offset == null) {
            offset = Integer.valueOf(0);
        }

        ComponentQueryFilter filter = new ComponentQueryFilter();
        filter.setPageRequest(createPageRequest(offset, limit));
        filter.setSearchText("");

        return ResponseEntity.ok(componentService.findAll(filter).getContent());
    }
}
