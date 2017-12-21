package org.book.db.mongo.repository;

import org.book.db.mongo.entity.Article;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<Article, ObjectId> {

    @Query(value = "{'name':?0}", fields = "{'name':1}")
    Page<Article> myQuery(String name, Pageable pageable);
}
