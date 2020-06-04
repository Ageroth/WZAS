package pl.lodz.p.it.wzas.service;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.wzas.model.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchQueryBuilder {

    private ElasticsearchTemplate elasticsearchTemplate;

    private List<String> commonWordsList = Arrays.asList("the","of","and","a","to","in","is","you","that","it","he","was","for","on","are","as","with","his","they","I","at","be","this","have","from","or","one","had","by","word","but","not","what","all","were","we","when","your","can","said","there","use","an","each","which","she","do","how","their","if","will","up","other","about","out","many","then","them","these","so","some","her","would","make","like","him","into","time","has","look","two","more","write","go","see","number","no","way","could","people","my","than","first","water","been","call","who","oil","its","now","find","long","down","day","did","get","come","made","may","part");

    @Autowired
    public SearchQueryBuilder(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    public List<Song> getSongsContaining(String text) {
        String[] words= text.split(" ");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String word : words) {
            if(!commonWordsList.contains(word)) // wyrzucam popularne słowa
            boolQueryBuilder.must(QueryBuilders.matchPhrasePrefixQuery("text", word));
        }

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder).build();

        return elasticsearchTemplate.queryForList(build, Song.class);
    }

    public List<Song> getSongsContainingOriginal(String text) {
        QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchPhrasePrefixQuery("text", text));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query).build();

        return elasticsearchTemplate.queryForList(build, Song.class);
    }
}
