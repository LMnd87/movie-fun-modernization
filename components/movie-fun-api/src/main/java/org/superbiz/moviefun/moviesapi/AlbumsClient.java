package org.superbiz.moviefun.moviesapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private RestTemplate restTemplate;
    private String albumsUrl;

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };


    public AlbumsClient(RestTemplate restTemplate, String albumsUrl) {
        this.restTemplate = restTemplate;
        this.albumsUrl = albumsUrl;
    }
    /*

    public String getAlbumDetails(long id){
        return restTemplate.getForObject(albumsUrl + "/"+ id, String.class);
    }

    public String uploadCover(long id, MultipartFile uploadedFile){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", uploadedFile);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(albumsUrl + "/" + id + "/cover", requestEntity, String.class);

        return response.getBody();
    }

    public HttpEntity<byte[]> getCover(long id){
        return restTemplate.getForObject(albumsUrl + "/" + id + "/cover", HttpEntity.class);
    }*/


    public void addAlbum(AlbumInfo albumInfo) {

        restTemplate.postForEntity(albumsUrl, albumInfo, null);
    }

    public AlbumInfo find(long id) {

        return restTemplate.getForObject(albumsUrl + "/" + id, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return restTemplate.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo albumInfo) {
        restTemplate.delete(albumsUrl, albumInfo);

    }

    public void updateAlbum(AlbumInfo albumInfo) {

        restTemplate.put(albumsUrl, albumInfo);
    }

}
