package com.pcdd;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.IdUtil;
import com.pcdd.entity.Book;
import com.pcdd.entity.BookSuggester;
import com.pcdd.repository.BookRepository;
import com.pcdd.repository.BookSuggesterRepository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pcdd
 */
@SpringBootApplication
public class SpringDataESApplication {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookSuggesterRepository bookSuggesterRepository;

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
        List<BookSuggester> bookSuggesters = new ArrayList<>();
        for (int i = 0; i < strings.size(); ) {
            String bookName = strings.get(i++);
            Book book = Book.builder()
                    .id(IdUtil.getSnowflakeNextId())
                    .bookName(bookName)
                    .author(strings.get(i++))
                    .description(strings.get(i++))
                    .build();
            books.add(book);

            BookSuggester bookSuggester = BookSuggester.builder().build();
            BeanUtils.copyProperties(book, bookSuggester);
            bookSuggester.setBookName(new Completion(List.of(bookName)));
            bookSuggesters.add(bookSuggester);
        }
        bookRepository.deleteAll();
        bookRepository.saveAll(books);
        bookSuggesterRepository.deleteAll();
        bookSuggesterRepository.saveAll(bookSuggesters);
    }

}
