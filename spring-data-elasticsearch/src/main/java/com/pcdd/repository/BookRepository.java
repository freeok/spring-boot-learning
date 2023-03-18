package com.pcdd.repository;

import com.pcdd.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author pcdd
 */
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    /*@Highlight(
            parameters = @HighlightParameters(
                    preTags = PRE_TAG, postTags = POST_TAG
            ),
            fields = {
                    @HighlightField(name = BOOK_NAME),
                    @HighlightField(name = DESCRIPTION),
                    @HighlightField(name = AUTHOR),
            }
    )
    SearchHit<Book> search(String bookName, String description, String author);*/

}
