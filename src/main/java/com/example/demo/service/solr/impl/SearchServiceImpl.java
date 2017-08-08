package com.example.demo.service.solr.impl;

import com.example.demo.dao.SearchableEntity;
import com.example.demo.repository.solr.SearchEntityRepository;
import com.example.demo.service.solr.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchEntityRepository searchEntityRepository;

    @Override
    public List<SearchableEntity> searchEntities(String title){

        return searchEntityRepository.findByTitle(title);
    }

    @Override
    public FacetPage<SearchableEntity> FacetSearchEntities(String title, Pageable pageable){
        return searchEntityRepository.findByTitleAndFacetOnContent(title,pageable);
    }


}
