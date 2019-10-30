package tk.aurelmarishta.imagegallery.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tk.aurelmarishta.imagegallery.model.ImageForAlbum;

public interface ImageRepository extends JpaRepository<ImageForAlbum, Integer> {

    // @Query("SELECT u FROM ImageForAlbum u WHERE u.albumId = :id")
    // List<ImageForAlbum> findAllByAlbumId(@Param("id") Integer album_id);

    @Query("select u from ImageForAlbum u where u.album.id = ?1")
    List<ImageForAlbum> findallByAlbumId(Integer id);

    @Query("select u from ImageForAlbum u where u.token = ?1")
    List<ImageForAlbum> findallByToken(String token);


}
