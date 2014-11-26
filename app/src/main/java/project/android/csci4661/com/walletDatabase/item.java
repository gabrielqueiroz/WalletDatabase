package project.android.csci4661.com.walletDatabase;

/**
 * Created by gabrielqueiroz on 11/24/14.
 */

public class item {
    private int id;
    private String name;
    private double value;
    private String imageURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getImage() {
        return imageURL;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
    }

    public item(){

    }

    public item (int id, String name, double value, String imageURL){
        this.id = id;
        this.name = name;
        this.value = value;
        this.imageURL = imageURL;
    }
}
