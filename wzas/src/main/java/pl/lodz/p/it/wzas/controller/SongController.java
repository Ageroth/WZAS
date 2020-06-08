package pl.lodz.p.it.wzas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.wzas.model.SkipRequest;
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

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable String id) {
        return songRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/artists/{artist}")
    public List<Song> getSongByArtist(@PathVariable String artist) {
        return songRepository.findByArtist(artist);
    }

    @PostMapping("/builder/contains/sentence")
    public List<Song> getSongByTextContainingFormQuery(@RequestParam String text, @RequestBody SkipRequest skipRequest) {
        return searchQueryBuilder.getSongsContaining(text, skipRequest);
    }
}
