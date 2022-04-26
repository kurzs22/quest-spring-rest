package com.wildcodeschool.sea8.springRest.repository;

import com.wildcodeschool.sea8.springRest.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
