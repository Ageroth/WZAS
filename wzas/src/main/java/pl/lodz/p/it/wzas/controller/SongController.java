package pl.lodz.p.it.wzas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.wzas.model.Song;
import pl.lodz.p.it.wzas.repository.SongRepository;
import pl.lodz.p.it.wzas.service.SearchQueryBuilder;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/songs")
public class SongController {

    private SongRepository songRepository;
    private SearchQueryBuilder searchQueryBuilder;

    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Autowired
    public void setSearchQueryBuilder(SearchQueryBuilder searchQueryBuilder) {
        this.searchQueryBuilder = searchQueryBuilder;
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/artists/{artist}")
    public List<Song> getSongByArtist(@PathVariable String artist) {
        return songRepository.findByArtist(artist);
    }

    @GetMapping("/contains/{word}")
    public List<Song> getSongByTextContaining(@PathVariable String word) {
        return songRepository.findSongByTextContaining(word);
    }

    @GetMapping("/builder/contains/sentence")
    public List<Song> getSongByTextContainingFormQuery(@RequestParam String text) {
        return searchQueryBuilder.getSongsContaining(text);
    }
}
