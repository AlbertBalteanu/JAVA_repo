package com.example;

import java.util.Date;
import java.util.List;

public class Image extends Repository {

    private String name;
    private Date date;
    private List<String> tags;
    private String location;

    public Image(String name, Date date, List<String> tags, String location) {
        this.name = name;
        this.date = date;
        this.tags = tags;
        this.location = location;
        super.addImage(this);

    }


    public Image(String name, String location) {
        this.name = name;
        this.location = location;
        super.addImage(this);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name;
    }
}
