package com.pcdd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author pcdd
 * @date 2023/03/19 06:24
 */
@Configuration
@EnableElasticsearchRepositories("com.pcdd.**.repository")
public class ElasticsearchConfig {

}
