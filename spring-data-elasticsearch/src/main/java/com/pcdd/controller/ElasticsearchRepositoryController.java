package com.pcdd.controller;

import com.pcdd.entity.Book;
import com.pcdd.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pcdd
 */
@RestController
public class ElasticsearchRepositoryController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> find(@PathVariable String id) {
        return ResponseEntity.ok(bookRepository.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @DeleteMapping("/batchDelete")
    public ResponseEntity.BodyBuilder batchDelete(@RequestBody List<String> ids) {
        bookRepository.deleteAllById(ids);
        return ResponseEntity.ok();
    }

}
