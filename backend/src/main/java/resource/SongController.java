package resource;

import model.Song;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.ISongRepository;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class SongController {

    @Autowired
    private ISongRepository repository;

    @Autowired
    private MongoOperations mongoOperations;



    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return repository.findAll();
    }

    @GetMapping("/song/{id}")
    public Optional<Song> getSong(@PathVariable String song) {
        return repository.findById(song);
    }


}
