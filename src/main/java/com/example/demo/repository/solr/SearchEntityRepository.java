package com.example.demo.repository.solr;

import com.example.demo.dao.SearchableEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface SearchEntityRepository extends
        SolrCrudRepository<SearchableEntity,Integer>{
    @Query(value = "title:?0")
    List<SearchableEntity> findByTitle(String title);

    @Query("title:?0")
    //输入 title SearchableEntity按content分组排序，并返回6个
    @Facet(fields = {"content"},limit = 6 )
    FacetPage<SearchableEntity> findByTitleAndFacetOnContent
    (String title, Pageable pageable);
}

