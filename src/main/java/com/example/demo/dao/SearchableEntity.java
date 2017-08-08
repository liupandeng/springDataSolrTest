package com.example.demo.dao;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by Administrator on 2017/7/20.
 */
@Data
public class SearchableEntity {

    @Field
    public Integer id;

    @Field
    public String title;

    @Field
    public String content;
}
