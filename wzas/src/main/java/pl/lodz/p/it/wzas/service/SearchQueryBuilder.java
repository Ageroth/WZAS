package pl.lodz.p.it.wzas.service;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.wzas.model.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

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
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            if(!commonWordsList.contains(word)) // wyrzucam popularne słowa
                stringBuilder.append(word).append(" ");
        }

        text = stringBuilder.toString();

        //TODO to wczytywać z frontu:
        String[] fieldsNames = {"text", "song"};
        String mSM = "80%"; //procentowo, można też liczbowo jakoś - dopasowanie ile minimum powinno pasować
        String fuzziness = "AUTO"; //Fuzziness okresla o ile liter mozna sie pomylic w danym slowie
                                    // np. jak damy 2 to dupa moze byc zupa, duma, ale też samo "pa"
        int slop = 2; // jak odległe mogą być od siebie wpisane słowa
        int limit = 10; // limit rezultatów

        MultiMatchQueryBuilder fuzzyMmQueryBuilder =  QueryBuilders.multiMatchQuery(text, fieldsNames)
                //.field("artist", 3) //to można też zparametryzować i jakoś przerobić ogólnie no nw.
                //.field("song", 3) // ten boost to wgl podnosi "ważność" danego pola
                .minimumShouldMatch(mSM)
                .fuzziness(fuzziness)
                .slop(slop);

        //BoolQueryBuilder bqb = boolQuery().should(fuzzyMmQueryBuilder);
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(text, fieldsNames)
                //ten typ chyba najlepszy ale nwm w sumie
                .type(MultiMatchQueryBuilder.Type.PHRASE).field("text", 3)
                .boost(5).slop(slop);


        BoolQueryBuilder bqb = boolQuery().should(multiMatchQuery).should(fuzzyMmQueryBuilder);

        NativeSearchQuery build = new NativeSearchQueryBuilder().withPageable(PageRequest.of(0,limit))
                .withQuery(bqb).build();

        return elasticsearchTemplate.queryForList(build, Song.class);
    }

    public List<Song> getSongsContainingOriginal(String text) {
        QueryBuilder query = boolQuery().must(QueryBuilders.matchPhrasePrefixQuery("text", text));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query).build();

        return elasticsearchTemplate.queryForList(build, Song.class);
    }
}
