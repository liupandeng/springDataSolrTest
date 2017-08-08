package com.example.demo;

import com.example.demo.dao.SearchableEntity;
import com.example.demo.repository.solr.SearchEntityRepository;
import com.example.demo.service.solr.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.print("++++++++++++++++++++++++title:");
    }

    @Autowired
    private SearchService searchService;
    @Autowired
    private SearchEntityRepository searchEntityRepository;

    @Autowired
    private SolrTemplate solrTemplate;

    public List<SearchableEntity> getSearchableEntities() {
        final List<SearchableEntity> searchableEntities = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            SearchableEntity searchableEntity = new SearchableEntity();
            searchableEntity.setId(i);
            searchableEntity.setTitle("test");
            searchableEntity.setContent("成功");
            searchableEntities.add(searchableEntity);
        }
        for (int i = 4; i <= 6; i++) {
            SearchableEntity searchableEntity = new SearchableEntity();
            searchableEntity.setId(i);
            searchableEntity.setTitle("test");
            searchableEntity.setContent("失败");
            searchableEntities.add(searchableEntity);
        }
        return searchableEntities;
    }

	@Test
	public void testSearch1(){
		List<SearchableEntity> s = getSearchableEntities();
		searchEntityRepository.save(s);
		List<SearchableEntity> searchableEntities = searchService.searchEntities("test");
		for( SearchableEntity searchableEntity:searchableEntities){
			System.out.print("title:"+searchableEntity.getTitle());
			System.out.print("content:"+searchableEntity.getContent());
		}
	}

	@Test
    //相当于分组排序
    public void testFacetSearch(){
        FacetPage<SearchableEntity> fp = searchService.
                FacetSearchEntities("test", new PageRequest(0,2));
        for(Page<? extends FacetEntry> page : fp.getAllFacets()){
            for(FacetEntry facetEntry:page.getContent()){
                String content = facetEntry.getValue();
                long count = facetEntry.getValueCount();
                System.out.println("++++++++++content:"+content);

                System.out.println("++++++++++++++count:"+count);

            }
        }
    }

//    @Test
//    public void addSearchEntity() {
//        SearchableEntity searchableEntity = new SearchableEntity();
//        searchableEntity.setId(1);
//        searchableEntity.setTitle("test");
//        searchableEntity.setContent("成功");
//        searchEntityRepository.save(searchableEntity);
//    }
//
//
//
    @Test
    public void testSearch() {
        searchEntityRepository.deleteAll();
//        List<SearchableEntity> searchableEntities = searchService.searchEntities("test");
//        for (SearchableEntity searchableEntity : searchableEntities) {
//            System.out.print("title:" + searchableEntity.getTitle());
//            System.out.print("content:" + searchableEntity.getContent());
//        }
    }


}
