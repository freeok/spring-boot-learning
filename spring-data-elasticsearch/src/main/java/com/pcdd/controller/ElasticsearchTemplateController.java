package com.pcdd.controller;

import co.elastic.clients.elasticsearch._types.SuggestMode;
import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import com.pcdd.entity.Book;
import com.pcdd.entity.BookSuggester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.HighlightQuery;
import org.springframework.data.elasticsearch.core.query.highlight.Highlight;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.query.highlight.HighlightParameters;
import org.springframework.data.elasticsearch.core.suggest.response.Suggest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pcdd
 */
@RestController
public class ElasticsearchTemplateController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    public static final String BOOK_NAME = "bookName";
    public static final String AUTHOR = "author";
    public static final String DESCRIPTION = "description";

    /**
     * 高亮查询
     */
    @GetMapping("/highlight")
    public ResponseEntity<Object> multiMatch(
            @RequestParam String kw,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(Query.of(builder ->
                        // 分词查询：对关键字进行分词后匹配多个词条。当只匹配一个字段时使用matchQuery
                        builder.multiMatch(MultiMatchQuery.of(builder2 ->
                                builder2.query(kw)
                                        .fields(BOOK_NAME, DESCRIPTION, AUTHOR)
                        ))
                ))
                // 高亮查询
                .withHighlightQuery(
                        new HighlightQuery(new Highlight(
                                HighlightParameters.builder()
                                        .withPreTags("<span style='color:#bf2c24'>")
                                        .withPostTags("<span/>")
                                        .build(),
                                List.of(
                                        new HighlightField(BOOK_NAME),
                                        new HighlightField(DESCRIPTION),
                                        new HighlightField(AUTHOR)
                                )), Book.class)
                )
                .withPageable(PageRequest.of(page, size))
                .build();

        // 得到ES查询结果
        SearchHits<Book> searchHits = elasticsearchTemplate.search(query, Book.class);

        return ResponseEntity.ok(searchHits.getSearchHits());
    }

    /**
     * 相似查询
     */
    @GetMapping("/more-like")
    public List<SearchHit<Book>> moreLike(@RequestParam String keyword) {
        // 匹配的字段, 不填默认_all
        List<String> fields = List.of(BOOK_NAME, DESCRIPTION);
        // 匹配的文本
        List<Like> likeTexts = List.of(
                Like.of(builder -> builder.text(keyword))
        );

        NativeQuery query = NativeQuery.builder()
                .withQuery(builder -> builder.moreLikeThis(builder1 ->
                        builder1.fields(fields)
                                .like(likeTexts)
                                // 一篇文档中一个词语至少出现次数，小于这个值的词将被忽略，默认是2
                                .minTermFreq(1)
                                // 最小的词语长度，默认是0，单字搜索无意义，至少两个字才能组成词语
                                .minWordLength(2)
                ))
                .withPageable(Pageable.ofSize(10))
                .build();

        SearchHits<Book> searchHits = elasticsearchTemplate.search(query, Book.class);

        return searchHits.getSearchHits();
    }

}
