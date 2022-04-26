package com.wildcodeschool.sea8.springRest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wildcodeschool.sea8.springRest.entity.Book;
import com.wildcodeschool.sea8.springRest.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public List<Book> index() {
        return bookRepository.findAll();
    } 

    @GetMapping(value = "/book", params = "search")
    public List<Book> search(@RequestParam("search") String search) {
        List<Book> books = bookRepository.findByTitleContainsOrDescriptionContains(search, search);
        return books;
    } 

    @GetMapping("/book/{id}")
    public Optional<Book> show(@PathVariable String id) {
        Long bookId = Long.parseLong(id);
        return bookRepository.findById(bookId);
    }

    @PostMapping("/book")
    public Book create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String author = body.get("author");
        String description = body.get("description");
        return bookRepository.save(new Book(title, author, description));
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable String id, @RequestBody Map<String, String> body){
        Long bookId = Long.parseLong(id);
        // getting book
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) return null;

        book.get().setTitle(body.get("title"));
        book.get().setAuthor(body.get("author"));
        book.get().setDescription(body.get("description"));
        return bookRepository.save(book.get());
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable String id){
        Long bookId = Long.parseLong(id);
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) return false;

        bookRepository.deleteById(bookId);
        return true;
    }

}
