package com.rest.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.rest.entity.Book;
import com.rest.rest.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository ;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping("/book/search")
    public List<Book> search(@RequestParam String body){
        String searchTerm = body;
        return bookRepository.findByTitleContainingOrAuthorContaining(searchTerm, searchTerm);
    }



    @PutMapping("/book/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}