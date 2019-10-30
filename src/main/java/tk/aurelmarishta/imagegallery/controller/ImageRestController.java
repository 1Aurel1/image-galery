package tk.aurelmarishta.imagegallery.controller;


import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tk.aurelmarishta.imagegallery.model.ImageForAlbum;
import tk.aurelmarishta.imagegallery.pojo.SimpleImage;
import tk.aurelmarishta.imagegallery.service.ImageService;

@RestController
@RequestMapping("upload/images")
public class ImageRestController {

    ImageService imageService;


    @Autowired
    public ImageRestController(ImageService theImageService) {
        imageService = theImageService;

    }

    @GetMapping("")
    public List<SimpleImage> images(@RequestParam(value = "albumId", required = false) int albumId) {

        List<SimpleImage> images = new ArrayList<>();

        List<ImageForAlbum> tempImgs = imageService.findAllByAlbumId(albumId);

        for (ImageForAlbum image : tempImgs) {

            SimpleImage temp = new SimpleImage(image.getImage(), image.getType());

            images.add(temp);
        }


        return images;
    }


    @PostMapping("")
    public ImageForAlbum saveImage(@RequestParam("token") String token, @RequestParam("file") MultipartFile file) {

        ImageForAlbum image = new ImageForAlbum();

        try {
            // image.setAlbumId(1);
            image.setImage(new SerialBlob(file.getBytes()));
            image.setImageName(file.getName());
            image.setType(file.getContentType());
            image.size(file.getSize());
            image.setToken(token);

            image = imageService.saveImageAndFlush(image);

            return image;

        } catch (Exception e) {
            System.err.println(e);
            System.out.println("\n\n\n\n some error \n\n\n ");
            return null;
        }
        // imageService.saveImage(tempImage);
    }

    @DeleteMapping("")
    public void deleteImage(@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "token", required = false) String token) {
        if (id != null) {
            imageService.deleteImage(id);
        } else if (token != null) {
            imageService.deleteAllWithToken(token);
        }
    }


}
