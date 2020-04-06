package repository;

import model.Song;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ISongRepository extends MongoRepository<Song, String> {

    @DeleteQuery
    void deleteOrderByRowId(String rowId);
}
