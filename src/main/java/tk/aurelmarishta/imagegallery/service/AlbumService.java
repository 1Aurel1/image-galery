package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.aurelmarishta.imagegallery.dao.AlbumRepository;
import tk.aurelmarishta.imagegallery.dao.ImageRepository;
import tk.aurelmarishta.imagegallery.model.Album;
import tk.aurelmarishta.imagegallery.model.ImageForAlbum;

@Service
public class AlbumService implements IAlbumService {

    AlbumRepository albumDAO;
    ImageRepository imageDAO;

    @Autowired
    public AlbumService(
            AlbumRepository theAlbumDAO,
            ImageRepository theImageDAO) {
        albumDAO = theAlbumDAO;
        imageDAO = theImageDAO;
    }

    @Override
    public void save(Album album) {

        albumDAO.save(album);

        // int albumId = dbAlbum.getId();

        // List<ImageForAlbum> images = imageDAO.findAllByAlbumId(1);

        // if (images != null) {
        // 	for (ImageForAlbum img : images) {
        // 		img.setAlbumId(albumId);
        // 		imageDAO.save(img);
        // 	}
        // }
    }

    @Override
    public void delete(int theId) {
        albumDAO.deleteById(theId);

    }

    @Override
    public void deleteImage(int theId) {
        imageDAO.deleteById(theId);

    }

    @Override
    public List<Album> findAll() {

        return albumDAO.findAll();
    }

    @Override
    public Album findById(int theId) {

        return albumDAO.getOne(theId);
    }

    @Override
    public List<ImageForAlbum> findAllByAlbumId(int theId) {

        return null;
    }

    @Override
    public List<Album> findAllByUserId(String username) {

        return albumDAO.findallByUserId(username);
    }

    @Override
    public Album saveAndFlash(Album album) {

        return albumDAO.saveAndFlush(album);
    }

    public Album getOne(int id, String userId) {

        Album tempAlbum = albumDAO.getOne(id);

        // if (tempAlbum.getuserId() == userId) {
        return tempAlbum;
        // } else {
        // 	return null;
        // }

    }

}
