package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Repository
public class MoviesClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ParameterizedTypeReference<List<MovieInfo>> movieListType = new ParameterizedTypeReference<List<MovieInfo>>() {
    };

    private RestTemplate restTemplate;
    private String moviesUrl;

    public MoviesClient(RestTemplate restTemplate, String moviesUrl) {
        this.restTemplate = restTemplate;
        this.moviesUrl = moviesUrl;
    }

    public MovieInfo find(Long id) {
        return restTemplate.getForObject(moviesUrl + "/" + id, MovieInfo.class);
    }

    public void addMovie(MovieInfo movieInfo) {
        logger.debug("Creating movie with title {}, and year {}", movieInfo.getTitle(), movieInfo.getYear());
        restTemplate.put(moviesUrl, movieInfo);

    }


    public void updateMovie(MovieInfo movieInfo) {
        restTemplate.put(moviesUrl, movieInfo);

    }


    public void deleteMovie(MovieInfo movieInfo) {
        deleteMovieId(movieInfo.getId());
    }


    public void deleteMovieId(long id) {
        restTemplate.delete(moviesUrl,id);
    }

    public List<MovieInfo> getMovies() {
        return restTemplate.exchange(moviesUrl, GET, null, movieListType).getBody();
    }

    public List<MovieInfo> findAll(int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restTemplate.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }

    public int countAll() {
        return restTemplate.getForObject(moviesUrl + "/count", Integer.class);
    }

    public int count(String field, String key) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl + "/count")
                .queryParam("field", field)
                .queryParam("key", key);

        return restTemplate.getForObject(builder.toUriString(), Integer.class);
    }
    public List<MovieInfo> findRange(String field, String key, int start, int pageSize) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(moviesUrl)
                .queryParam("field", field)
                .queryParam("key", key)
                .queryParam("start", start)
                .queryParam("pageSize", pageSize);

        return restTemplate.exchange(builder.toUriString(), GET, null, movieListType).getBody();
    }

}
