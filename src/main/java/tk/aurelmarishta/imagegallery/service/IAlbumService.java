package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import tk.aurelmarishta.imagegallery.model.Album;
import tk.aurelmarishta.imagegallery.model.ImageForAlbum;

public interface IAlbumService {

    public void save(Album album);

    public Album saveAndFlash(Album album);

    public void delete(int theId);

    public void deleteImage(int theId);

    public List<Album> findAll();

    public Album findById(int theId);

    public Album getOne(int id, String userId);

    public List<ImageForAlbum> findAllByAlbumId(int theId);

    public List<Album> findAllByUserId(String username);


}
