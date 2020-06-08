package pl.lodz.p.it.wzas.repository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.lodz.p.it.wzas.model.Song;

import java.util.List;

public interface SongRepository extends ElasticsearchRepository<Song, String> {
    List<Song> findByArtist(String artist);
}
