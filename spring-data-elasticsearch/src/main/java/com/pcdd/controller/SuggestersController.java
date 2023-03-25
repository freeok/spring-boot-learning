package com.pcdd.controller;

import co.elastic.clients.elasticsearch._types.SuggestMode;
import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import com.pcdd.entity.BookSuggester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.suggest.response.Suggest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.pcdd.controller.ElasticsearchTemplateController.BOOK_NAME;

/**
 * @author pcdd
 * @date 2023/03/25 14:16
 */
@RestController
@RequestMapping("/suggester")
public class SuggestersController {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * Suggesters API 之自动补全功能，生效前提方式
     * 方式一：搜索字段类型必须为CompletionField、标有@CompletionField
     * 方式二：搜索字段类型任意，但需要在mapping.json中手动设置类型为completion，结合@Mapping
     * eg：修仙 => 修仙女配很无辜
     */
    @GetMapping("/complete")
    public Suggest completeSuggestion(@RequestParam String keyword) {
        NativeQuery query = NativeQuery.builder()
                .withSuggester(Suggester.of(builder -> builder
                        .suggesters("COMPLETE_SUGGESTION", FieldSuggester.of(b -> b
                                .prefix(keyword)
                                .completion(c -> c
                                        .field(BOOK_NAME)
                                        .size(10)
                                        .skipDuplicates(true)
                                )
                        ))
                )).build();

        SearchHits<BookSuggester> searchHits = elasticsearchTemplate.search(query, BookSuggester.class);

        return searchHits.getSuggest();
    }

    /**
     * Suggesters API 之纠错功能
     * eg：数据哇掘 => 数据挖掘
     */
    @GetMapping("/term")
    public Suggest termSuggester(@RequestParam String keyword) {
        NativeQuery query = NativeQuery.builder()
                .withSuggester(Suggester.of(builder -> builder
                        .suggesters("TERM_SUGGESTION", FieldSuggester.of(b -> b
                                .text(keyword)
                                .term(builder1 -> builder1
                                        .field(BOOK_NAME)
                                        // 文本长度最少是2，因为中文一个字没法纠错
                                        .minWordLength(2)
                                        .size(10)
                                        .suggestMode(SuggestMode.Popular)
                                )
                        ))
                )).build();

        SearchHits<BookSuggester> searchHits = elasticsearchTemplate.search(query, BookSuggester.class);

        return searchHits.getSuggest();
    }

}
