package com.mapi.ihrd.module.training.api;

import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.training.form.BookForm;
import com.mapi.ihrd.module.training.model.Book;
import com.mapi.ihrd.module.training.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookApi {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity index(@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {

        if (limit == null) {
            limit = Integer.valueOf(10);
        }

        if (offset == null) {
            offset = Integer.valueOf(0);
        }

        return ResponseEntity.ok(bookService.find(limit, offset));
    }

    @PostMapping
    public ResponseEntity add(@ModelAttribute BookForm form) {

        Book book = new Book();

        copy(form, book);

        bookService.save(book);

        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable String id, @RequestParam BookForm form) {

        Book book = bookService.getById(id);

        if (book == null) {
            throw new DataNotFoundException("Ebook tidak ditemukan");
        }

        copy(form, book);

        bookService.save(book);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable String id) {

        Book book = bookService.getById(id);

        if (book == null) {
            throw new DataNotFoundException("Ebook tidak ditemukan");
        }

        return ResponseEntity.ok(book);
    }

    private void copy(BookForm form, Book book) {
        book.setAuthor(form.getAuthor());
        book.setDescription(form.getDescription());
        book.setTitle(form.getTitle());
        book.setFileName(form.getFile().getName());
        book.setFileNameOrigin(form.getFile().getOriginalFilename());
    }
}
