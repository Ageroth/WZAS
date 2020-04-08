package pl.lodz.pl.springmongocrud.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.pl.springmongocrud.model.Song;
import pl.lodz.pl.springmongocrud.repository.ISongRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SongController {

    @Autowired
    private ISongRepository repository;

    @Autowired
    private MongoOperations mongoOperations;


    @GetMapping("/songFirst")
    public Song getFirst() {
        return repository.findAll().stream().findFirst().get();
    }

    @GetMapping("/songs")
    public List<Song> getAllSongs() {
        return repository.findAll();
    }

    @GetMapping("/song/{id}")
    public Optional<Song> getSong(@PathVariable String song) {
        return repository.findById(song);
    }


}
