package tk.aurelmarishta.imagegallery.model;

import java.sql.Blob;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class ImageForAlbum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(length = 16777215)
    private Blob image;

    @Column
    private String imageName;

    @Column
    private long size;

    @Column
    private String type;

    @Column
    String token;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;


    public ImageForAlbum() {
    }

    public ImageForAlbum(int id, Blob image, String imageName, long size, String type, String token, Album album) {
        this.id = id;
        this.image = image;
        this.imageName = imageName;
        this.size = size;
        this.type = type;
        this.token = token;
        this.album = album;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {

        String image = "";

        try {
            int length = (int) this.image.length();
            long start = 1;
            byte[] bdata = this.image.getBytes(start, length);
            image = Base64.getEncoder().encodeToString(bdata);
        } catch (Exception e) {
            System.out.println(e);
        }

        return image;
    }

    // // serialize as data uri instead
    // @JsonProperty("photoData")
    // public String getPhotoBase64() {
    // 	// just assuming it is a jpeg. you would need to cater for different media types
    // 	return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(image.getBytes()));
    // }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public ImageForAlbum id(int id) {
        this.id = id;
        return this;
    }

    public ImageForAlbum image(Blob image) {
        this.image = image;
        return this;
    }

    public ImageForAlbum imageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public ImageForAlbum size(long size) {
        this.size = size;
        return this;
    }

    public ImageForAlbum type(String type) {
        this.type = type;
        return this;
    }

    public ImageForAlbum token(String token) {
        this.token = token;
        return this;
    }

    public ImageForAlbum album(Album album) {
        this.album = album;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", imageName='" + getImageName() + "'" +
                ", size='" + getSize() + "'" +
                ", type='" + getType() + "'" +
                ", token='" + getToken() + "'" +
                ", album='" + getAlbum() + "'" +
                "}";
    }


}
