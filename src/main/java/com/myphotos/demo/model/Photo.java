package com.myphotos.demo.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@javax.persistence.Entity 
public class Photo {

    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "This field is required")
    @Length(min = 2, message = "Min length is 2")
    private String url;


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public Photo(){

    }

    public Photo(int id, String url){
        this.id=id;
        this.url=url;
    }
}
