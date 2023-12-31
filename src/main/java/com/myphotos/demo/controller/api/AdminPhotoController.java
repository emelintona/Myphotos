
/*AdminPhotoController
 * AdminPhotoController
 */
package com.myphotos.demo.controller.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.myphotos.demo.model.Photo;
import com.myphotos.demo.service.IPhotoService;


@RestController
public class AdminPhotoController {

    @Autowired
    @Qualifier("mainPhotoService")
    private IPhotoService photoService;

    public AdminPhotoController()
    {
        
    }

    @RequestMapping("admin/api/photos")
    public Iterable <Photo> getAll(){
        return photoService.getAll();
    }
    @RequestMapping("admin/api/photos/{id}")
    public Photo getByID(@PathVariable int id)
    {
        Optional <Photo> photo = photoService.getByID(id);
        if(photo.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ithem not found");
        }
        return photo.get();
    }
    @RequestMapping(value = "/admin/api/photos" , method = RequestMethod.POST)
    public Photo create(@Valid @RequestBody Photo photo)    //deve essere valido in Photos.java c'è la regola
    {
        return photoService.create(photo);
    }
    @RequestMapping(value = "/admin/api/photos/{id}" , method = RequestMethod.PUT)
    public Photo update(@PathVariable int id, @Valid @RequestBody Photo photo)
    {
        Optional <Photo> updatePhoto = photoService.update(id, photo);
        if(updatePhoto.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ithem not found");
        }
        return updatePhoto.get();
    }
    
    @RequestMapping(value = "/admin/api/photos/{id}" , method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        boolean isDeleted = photoService.delete(id);
        if(!isDeleted)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ithem not found");

        }     
           
    }
}
