package pl.lodz.p.it.wzas.service;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.wzas.model.Song;
import java.util.List;

@Service
public class SearchQueryBuilder {

    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public SearchQueryBuilder(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<Song> getSongsContaining(String text) {
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery("*"+text+"*")
                        .lenient(true).field("song").field("text"));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query).build();

        return elasticsearchTemplate.queryForList(build, Song.class);
    }
}
