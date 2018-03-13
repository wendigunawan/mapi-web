package com.mapi.ihrd.module.training.service;

import com.mapi.ihrd.module.training.form.BookForm;
import com.mapi.ihrd.module.training.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BookService {

    Book getById(String id);

    void save(Book book);

    void edit(BookForm bookForm);

    String add(BookForm bookForm);

    Page<Book> find(int limit, int offset);

    Page<Book> find(PageRequest pageRequest);
}
