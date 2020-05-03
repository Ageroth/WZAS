package pl.lodz.p.it.wzas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.it.wzas.model.Song;
import pl.lodz.p.it.wzas.repository.SongRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/songs")
public class SongController {

    private SongRepository songRepository;

    @Autowired
    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return (List<Song>) songRepository.findAll();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/artists/{artist}")
    public List<Song> getSongByArtist(@PathVariable String artist) {
        return songRepository.findByArtist(artist);
    }
}
