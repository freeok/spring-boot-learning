package com.pcdd.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.suggest.Completion;

import java.io.Serializable;

/**
 * @author pcdd
 */
@Data
@Builder
@Document(indexName = "spring-data-es-suggester", createIndex = true)
public class BookSuggester implements Serializable {

    @Id
    private Long id;
    @CompletionField(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private Completion bookName;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String author;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;

}
