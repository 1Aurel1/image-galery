package tk.aurelmarishta.imagegallery.pojo;


public class SimpleImage {

    private String image;

    private String type;


    public SimpleImage() {
    }

    public SimpleImage(String image, String type) {
        this.image = image;
        this.type = type;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SimpleImage image(String image) {
        this.image = image;
        return this;
    }

    public SimpleImage type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " image='" + getImage() + "'" +
                ", type='" + getType() + "'" +
                "}";
    }


}
