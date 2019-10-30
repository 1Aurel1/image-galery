package tk.aurelmarishta.imagegallery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String name;

    @Column(name = "user_id")
    private String userId;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ImageForAlbum> imageForAlbum;


    public Album() {
    }

    public Album(int id, String name, String userId, List<ImageForAlbum> imageForAlbum) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.imageForAlbum = imageForAlbum;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuserId() {
        return this.userId;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public List<ImageForAlbum> getImageForAlbum() {
        return this.imageForAlbum;
    }

    public void setImageForAlbum(List<ImageForAlbum> imageForAlbum) {
        this.imageForAlbum = imageForAlbum;
    }

    public Album id(int id) {
        this.id = id;
        return this;
    }

    public Album name(String name) {
        this.name = name;
        return this;
    }

    public Album userId(String userId) {
        this.userId = userId;
        return this;
    }

    public Album imageForAlbum(List<ImageForAlbum> imageForAlbum) {
        this.imageForAlbum = imageForAlbum;
        return this;
    }


    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", userId='" + getuserId() + "'" +
                ", imageForAlbum='" + getImageForAlbum() + "'" +
                "}";
    }


}
