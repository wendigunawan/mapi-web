package com.mapi.ihrd.module.training.service.impl;

import com.mapi.ihrd.exception.BusinessException;
import com.mapi.ihrd.exception.DataNotFoundException;
import com.mapi.ihrd.module.training.dao.BookDao;
import com.mapi.ihrd.module.training.form.BookForm;
import com.mapi.ihrd.module.training.model.Book;
import com.mapi.ihrd.module.training.service.BookService;
import com.mapi.ihrd.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;


    @Value("${app.path.upload}")
    private String uploadPath;

    @Override
    public Book getById(String id) {
        return bookDao.getOne(id);
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void edit(BookForm bookForm) {
        if (!StringUtils.hasLength(bookForm.getId())) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        Book book = bookDao.findOne(bookForm.getId());

        if (book == null) {
            throw new DataNotFoundException("Data yang akan diubah tidak ditemukan");
        }

        boolean anyChanges = false;
        if (StringUtils.hasLength(bookForm.getTitle())) {
            anyChanges = true;
            book.setTitle(bookForm.getTitle());
        }

        if (StringUtils.hasLength(bookForm.getAuthor())) {
            anyChanges = true;
            book.setAuthor(bookForm.getAuthor());
        }

        if (StringUtils.hasLength(bookForm.getDescription())) {
            anyChanges = true;
            book.setDescription(bookForm.getDescription());
        }

        if(!bookForm.getFile().isEmpty()){
            anyChanges=true;

            try {
                byte[] bytes = bookForm.getFile().getBytes();
                Path path = Paths.get(uploadPath + "/" + bookForm.getFile().getOriginalFilename());

                if(book.getFileNameOrigin() != null){
                    File file = new File(book.getFileNameOrigin());
                    if(file.delete()){
                        Files.write(path, bytes);
                        book.setFileName(bookForm.getFile().getOriginalFilename());
                        book.setFileNameOrigin(path.toString());
                    }
                } else{
                    Files.write(path, bytes);
                    book.setFileName(bookForm.getFile().getOriginalFilename());
                    book.setFileNameOrigin(path.toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (anyChanges == false) {
            throw new BusinessException("Tidak ada perubahan data yang harus disimpan");
        }

        book.setUpdatedDate(DateUtil.now());

        bookDao.save(book);
    }

    @Override
    public String add(BookForm bookForm) {

        Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setDescription(bookForm.getDescription());
        book.setFileName(bookForm.getFile().getOriginalFilename());
        book.setCreatedDate(DateUtil.now());

        try {
            byte[] bytes = bookForm.getFile().getBytes();
            Path path = Paths.get(uploadPath + "/" + bookForm.getFile().getOriginalFilename());
            Files.write(path, bytes);
            book.setFileNameOrigin(path.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


        bookDao.save(book);

        return book.getId();
    }

    @Override
    public Page<Book> find(int limit, int offset) {
        Pageable pageable = new PageRequest(offset, limit, Sort.Direction.DESC, "author");

        return bookDao.findAll(pageable);
    }

    @Override
    public Page<Book> find(PageRequest pageRequest) {
        return bookDao.findAll(pageRequest);
    }
}
