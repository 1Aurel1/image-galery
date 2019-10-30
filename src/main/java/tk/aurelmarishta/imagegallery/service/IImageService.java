package tk.aurelmarishta.imagegallery.service;

import java.util.List;

import tk.aurelmarishta.imagegallery.model.ImageForAlbum;

public interface IImageService {

    void save(ImageForAlbum image);

    ImageForAlbum saveImageAndFlush(ImageForAlbum image);

    void deleteImage(Integer theId);

    void deleteAllWithToken(String token);

    ;

    public List<ImageForAlbum> findAllByAlbumId(int theId);

    public List<ImageForAlbum> findallByToken(String token);


}
