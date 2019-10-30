package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tk.aurelmarishta.imagegallery.dao.ImageRepository;
import tk.aurelmarishta.imagegallery.model.ImageForAlbum;

@Service
public class ImageService implements IImageService {

    ImageRepository imageDAO;

    public ImageService(ImageRepository theImageDAO) {
        imageDAO = theImageDAO;
    }

    @Override
    public ImageForAlbum saveImageAndFlush(ImageForAlbum image) {
        return imageDAO.saveAndFlush(image);
    }

    @Override
    public void save(ImageForAlbum image) {
        imageDAO.save(image);
    }

    @Override
    public List<ImageForAlbum> findAllByAlbumId(int theId) {

        return imageDAO.findallByAlbumId(theId);
    }

    @Override
    public List<ImageForAlbum> findallByToken(String token) {

        return imageDAO.findallByToken(token);
    }

    @Override
    public void deleteImage(Integer theId) {
        imageDAO.delete(imageDAO.getOne(theId));
    }

    @Override
    public void deleteAllWithToken(String token) {
        List<ImageForAlbum> images = imageDAO.findallByToken(token);

        for (ImageForAlbum image : images) {
            if (image.getAlbum() == null) {
                imageDAO.delete(image);
            }
        }
    }

    // @Override
    // public void deleteAllByAlbumId(int albumId){

    // 	List<ImageForAlbum> images = imageDAO.findAllByAlbumId(albumId);

    // 	for (ImageForAlbum image : images) {
    // 		imageDAO.delete(image);
    // 	}

    // }

}
