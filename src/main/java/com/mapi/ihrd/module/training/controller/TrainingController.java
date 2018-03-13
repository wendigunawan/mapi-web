package com.mapi.ihrd.module.training.controller;

import com.mapi.ihrd.controller.BaseController;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.training.form.BookForm;
import com.mapi.ihrd.module.training.model.Book;
import com.mapi.ihrd.module.training.service.BookService;
import com.mapi.ihrd.response.JQueryDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("web/training")
public class TrainingController extends BaseController {

    @Autowired
    BookService bookService;

    @GetMapping
    public String index() {
        return "training/training.list";
    }

    @PostMapping("/datatable")
    @ResponseBody
    public JQueryDataTable dataTable(@RequestParam Integer start,
                                     @RequestParam Integer length,
                                     @RequestParam Integer draw) {

        PageRequest pageRequest = createPageRequest(start, length);

        Page<Book> bookPage = bookService.find(pageRequest);
        Collection<Book> book = bookPage.getContent();

        return new JQueryDataTable(book, bookPage.getTotalElements(), draw);
    }

    @GetMapping("/add")
    public String add(Model model) {
        BookForm form = new BookForm();
        model.addAttribute("training", form);
        return "training/training.form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {

        Book book = bookService.getById(id);

        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setDescription(book.getDescription());

        model.addAttribute("training", form);

        return "training/training.form";
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute BookForm form) {

        if (StringUtils.hasLength(form.getId())) {
            bookService.edit(form);
        } else {
            String id = bookService.add(form);
            form.setId(id);
        }

        return ResponseEntity.ok(form);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        Book book = bookService.getById(id);

        if (book == null) {
            throw new DataNotFoundException("Ebook tidak ditemukan");
        }

        book.setDeleted(true);
        bookService.save(book);

        return "training/training.list";
    }
}
