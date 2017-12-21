package org.book.db.mongo.service;

import org.book.db.mongo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BusinessService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void mongoDemo() {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is("book");
        query.addCriteria(criteria);

        Pageable pageable = new PageRequest(0, 2, Sort.Direction.DESC, "name");
        query.with(pageable);

        List<Article> results = mongoTemplate.find(query, Article.class);
        for (Article article : results) {
            System.out.println(article.getName());
        }
    }
}
