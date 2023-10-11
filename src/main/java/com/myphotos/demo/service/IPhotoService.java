package com.myphotos.demo.service;

import java.util.Optional;
import com.myphotos.demo.model.Photo;


public interface IPhotoService {

    public Iterable <Photo> getAll();

    public Optional<Photo> getByID(int id);

    public Photo create(Photo photo);

    public Optional<Photo> update(int id, Photo photo);

    public boolean delete(int id);
    
}