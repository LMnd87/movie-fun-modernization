package org.superbiz.moviefun.albums;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.superbiz.moviefun.blobstore.BlobStore;
import org.superbiz.moviefun.blobstore.S3Store;

@SpringBootApplication
public class AlbumServiceApplication {
    public static void main(String... args) {
        SpringApplication.run(AlbumServiceApplication.class, args);
    }

    @Bean
    public AlbumsRepository albumsRepository(){
        return new AlbumsRepository();
    }

    @Bean
    public BlobStore blobStore(ServiceCredentials serviceCredentials ){
        String access_key_id = serviceCredentials.getCredential("photo-storage", "user-provided","access_key_id");
        String bucket = serviceCredentials.getCredential("photo-storage", "user-provided","bucket");
        String secret_access_key = serviceCredentials.getCredential("photo-storage", "user-provided","secret_access_key");

        AWSCredentials credentials = new BasicAWSCredentials(access_key_id,secret_access_key);

        return new S3Store(new AmazonS3Client(credentials),bucket);
    }
}
