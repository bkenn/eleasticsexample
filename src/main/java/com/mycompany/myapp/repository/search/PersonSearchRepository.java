package com.mycompany.myapp.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mycompany.myapp.domain.Person;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Person} entity.
 */
public interface PersonSearchRepository extends ElasticsearchRepository<Person, Long>, PersonSearchRepositoryInternal {}

interface PersonSearchRepositoryInternal {
    Stream<Person> search(String query);
}

class PersonSearchRepositoryInternalImpl implements PersonSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    PersonSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Stream<Person> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return elasticsearchTemplate.search(nativeSearchQuery, Person.class).map(SearchHit::getContent).stream();
    }
}
