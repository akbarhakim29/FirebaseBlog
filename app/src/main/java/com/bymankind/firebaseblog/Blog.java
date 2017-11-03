package com.bymankind.firebaseblog;

/**
 * Created by Server-Panduit on 11/2/2017.
 */

public class Blog {
    private String title,description,image, key;

    public Blog(){

    }

    public Blog(String title, String description, String image, String key) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
