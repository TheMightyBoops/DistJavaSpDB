package com.nolting.second;

import com.nolting.second.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @RequestMapping("genres")
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }

    /*@RequestMapping("/genres")
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Horror", "8"));
        genres.add(new Genre("Action", "7"));
        genres.add(new Genre("Drama", "8"));
        genres.add(new Genre("Crime", "9"));
        genres.add(new Genre("Other", "9"));
        return genres;
    }*/

    @RequestMapping("/genres/{id}")
    public Genre getGenre(@PathVariable String id) {
        return genreService.getGenre(id);
    }

    @RequestMapping(method= RequestMethod.POST, value="/genres")
    public void addGenre(@RequestBody Genre genre) {
        genreService.addGenre(genre);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/genres/{id}")
    public void updateGenre(@RequestBody Genre genre, @PathVariable String id) {
        genreService.updateGenre(genre, id);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/genres/{id}")
    public void deleteGenre(@PathVariable String id) {
        genreService.deleteGenre(id);
    }

}