package tk.aurelmarishta.imagegallery.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import tk.aurelmarishta.imagegallery.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("select u from #{#entityName} u where u.userId = ?1")
    List<Album> findallByUserId(String username);

}
