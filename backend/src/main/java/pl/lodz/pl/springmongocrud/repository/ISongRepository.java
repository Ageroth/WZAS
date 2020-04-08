package pl.lodz.pl.springmongocrud.repository;


import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lodz.pl.springmongocrud.model.Song;


public interface ISongRepository extends MongoRepository<Song, String> {

    @DeleteQuery
    void deleteOrderByRowId(String rowId);
}
