package com.mycompany.myapp.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.mycompany.myapp.domain.Contact;
import java.util.stream.Stream;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Contact} entity.
 */
public interface ContactSearchRepository extends ElasticsearchRepository<Contact, Long>, ContactSearchRepositoryInternal {}

interface ContactSearchRepositoryInternal {
    Stream<Contact> search(String query);
}

class ContactSearchRepositoryInternalImpl implements ContactSearchRepositoryInternal {

    private final ElasticsearchRestTemplate elasticsearchTemplate;

    ContactSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Stream<Contact> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return elasticsearchTemplate.search(nativeSearchQuery, Contact.class).map(SearchHit::getContent).stream();
    }
}
