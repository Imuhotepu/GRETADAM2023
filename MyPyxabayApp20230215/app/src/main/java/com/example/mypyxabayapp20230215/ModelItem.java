package com.example.mypyxabayapp20230215;

public class ModelItem {
    private String imageURL;
    private String creator;
    private int likes;

    public ModelItem() {
    }

    public ModelItem(String imageURL, String creator, int likes) {
        this.imageURL = imageURL;
        this.creator = creator;
        this.likes = likes;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getCreator() {
        return creator;

    }

    public int getLikes() {
        return likes;

    }
}
