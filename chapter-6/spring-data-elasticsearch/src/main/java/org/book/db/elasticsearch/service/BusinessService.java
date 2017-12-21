package org.book.db.elasticsearch.service;

import org.book.db.elasticsearch.entity.Article;
import org.book.db.elasticsearch.repository.ArticleRepository;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessService {

    @Autowired
    private ArticleRepository repository;

    public void repositoryDemo() {
        QueryBuilder query = QueryBuilders.queryStringQuery("中").field("content").analyzer("ik_max_word");
        Page<Article> results = repository.search(query, new PageRequest(0, 100));
        for (Article article : results) {
            System.out.println(article.getContent());
        }
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void templateDemo() {
        SearchQuery search = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("中"))
                .withFilter(QueryBuilders.queryStringQuery(""))
                .withPageable(new PageRequest(0, 100))
                .withIndices("book", "book2")
                .build();
        AggregatedPage<Article> results = elasticsearchTemplate.queryForPage(search, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Article> content = new ArrayList<>();
                for (SearchHit hit : response.getHits()) {
                    Article entity = new Article();
                    // 可以在这里过滤搜索到的结果
                    entity.setContent(hit.getSource().get("content").toString());
                    content.add(entity);
                }
                return new AggregatedPageImpl<>((List<T>) content, pageable, response.getHits().getHits().length);
            }
        });

        for (Article article : results) {
            System.out.println(article.getContent());
        }

    }
}
