package org.superbiz.moviefun.movies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/movies")
public class MoviesController {

    private MoviesRepository repository;

    public MoviesController(MoviesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable final long id)
    {

        Movie movie = repository.find(id);
        if (movie == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
    {
        try {
            repository.addMovie(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie)
    {
        try {
            repository.updateMovie(movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovieId(@PathVariable final long id)
    {
        try {
            repository.deleteMovieId(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<Movie> deleteMovie(@RequestBody Movie movie)
    {
        try {
            repository.deleteMovieId(movie.getId());
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public List<Movie> getMovies(){
        return repository.getMovies();
    }

    @GetMapping("/{start}/{pageSize}")
    public List<Movie> findAll(@PathVariable("{start}") int start,@PathVariable("{pageSize}") int pageSize)
    {
        return repository.findAll(start,pageSize);
    }

    @GetMapping("/count")
    public int countAll(){
        return repository.countAll();
    }

    @GetMapping("/count/{field}/{key}")
        public int count(@PathVariable("{field}") String field, @PathVariable("{key}") String key) {
        return repository.count(field, key);
    }

    @GetMapping("/{field}/{key}/{start}/{pageSize}")
    public List<Movie> findRange(@PathVariable("{field}") String field, @PathVariable("{key}") String key, @PathVariable("{start}") int start, @PathVariable("{pageSize}") int pageSize) {
        return repository.findRange(field,key,start,pageSize);
    }




}
