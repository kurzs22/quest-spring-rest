package com.wildcodeschool.sea8.springRest.repository;

import java.util.List;

import com.wildcodeschool.sea8.springRest.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByTitleContainsOrDescriptionContains(@Param("title") String title, @Param("description") String description);
    
}
