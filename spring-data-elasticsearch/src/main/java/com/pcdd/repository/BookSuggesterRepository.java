package com.pcdd.repository;

import com.pcdd.entity.BookSuggester;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author pcdd
 */
public interface BookSuggesterRepository extends ElasticsearchRepository<BookSuggester, String> {
}
