package com.mapi.ihrd.module.training.dao;

import com.mapi.ihrd.module.training.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
}
