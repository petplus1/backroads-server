package com.backroads.common;

public class RandomPhotos {
    private Long id;
    private String picture;

    public RandomPhotos(Long id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public RandomPhotos() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
