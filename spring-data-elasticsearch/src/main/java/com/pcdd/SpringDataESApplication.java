package com.pcdd;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.IdUtil;
import com.pcdd.entity.Book;
import com.pcdd.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pcdd
 */
@SpringBootApplication
public class SpringDataESApplication {

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataESApplication.class);
    }

    @PostConstruct
    @SneakyThrows
    public void init() {
        FileReader fileReader = new FileReader("book.txt");
        List<String> strings = fileReader.readLines().stream()
                .filter(s -> !s.isEmpty())
                .toList();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < strings.size(); ) {
            books.add(Book.builder()
                    .id(IdUtil.getSnowflakeNextId())
                    .bookName(strings.get(i++))
                    .author(strings.get(i++))
                    .description(strings.get(i++))
                    .build());
        }
        bookRepository.deleteAll();
        bookRepository.saveAll(books);
    }

}
