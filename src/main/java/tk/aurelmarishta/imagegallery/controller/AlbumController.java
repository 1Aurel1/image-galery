package tk.aurelmarishta.imagegallery.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.aurelmarishta.imagegallery.model.Album;
import tk.aurelmarishta.imagegallery.model.ImageForAlbum;
import tk.aurelmarishta.imagegallery.service.AlbumService;
import tk.aurelmarishta.imagegallery.service.ImageService;
import tk.aurelmarishta.imagegallery.service.UserService;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final static String prefix = "albums/";

    UserService userService;

    AlbumService albService;
    ImageService imageService;

    @Autowired
    public AlbumController(AlbumService theAlbService, ImageService theImageService, UserService theUserService) {
        albService = theAlbService;
        imageService = theImageService;
        userService = theUserService;
    }

    @GetMapping("")
    public String myAlbums(Model theModel) {

        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (user instanceof UserDetails) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }

        System.out.println("\n\n\n Username: " + username + "\n\n\n");

        List<Album> albums = albService.findAllByUserId(username);


        theModel.addAttribute("albums", albums);


        return prefix + "index";
    }

    @GetMapping("/{id}")
    public String showAlbum(@PathVariable("id") Integer id, Model theModdel) {

        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (user instanceof UserDetails) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }

        System.out.println("\n\n\n Username: " + username + "\n\n\n");

        Album theAlbum = albService.getOne(id, username);
        theModdel.addAttribute("theAlbum", theAlbum);

        System.out.println("\n\n\n " + theAlbum.getName() + "\n\n\n");

        return prefix + "show";
    }


    @GetMapping("/new")
    public String newAlbum(Model theModel) {

        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (user instanceof UserDetails) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }


        Random rand = new Random();

        Album tempAlb = new Album();
        String token = username + rand.nextInt(100);

        theModel.addAttribute("album", tempAlb);

        theModel.addAttribute("token", token);

        System.out.println("\n\n\n Token: " + token + "\n\n\n");
        // imageService.deleteAllByAlbumId(1);

        return prefix + "new";
    }

    @PostMapping("/save/{token}")
    public String saveAlbum(@ModelAttribute("album") Album theAlbum, @PathVariable("token") String token) {

        System.out.println("\n\n\n Token in save: " + token + "\n\n\n");


        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (user instanceof UserDetails) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }
        theAlbum.setuserId(username);
        albService.saveAndFlash(theAlbum);

        List<ImageForAlbum> images = imageService.findallByToken(token);

        for (ImageForAlbum image : images) {
            // System.out.println(image.getType());
            image.setAlbum(theAlbum);
            image.setToken(null);
            imageService.saveImageAndFlush(image);
        }

        return "redirect:/albums/new";
    }

    @GetMapping("/edit/{id}")
    public String editAlbum(@PathVariable(name = "id", required = true) int id, Model theModel) {
        final Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (user instanceof UserDetails) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }

        Album tempAlbum = albService.getOne(id, username);
        theModel.addAttribute("album", tempAlbum);

        return prefix + "edit";
    }

}
