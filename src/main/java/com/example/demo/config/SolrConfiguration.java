package com.example.demo.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(value = "com.example.demo.repository.solr")
public class SolrConfiguration {

    private SolrTemplate solrTemplate;

    @Bean
    @ConditionalOnMissingBean
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        if (solrTemplate == null) {
            this.solrTemplate = new SolrTemplate(solrClient);
        }
        return this.solrTemplate;
    }
}