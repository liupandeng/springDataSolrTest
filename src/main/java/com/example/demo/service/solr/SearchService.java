package com.example.demo.service.solr;

import com.example.demo.dao.SearchableEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface SearchService {
    List<SearchableEntity> searchEntities(String title);
    FacetPage<SearchableEntity> FacetSearchEntities(String title, Pageable pageable);
}
