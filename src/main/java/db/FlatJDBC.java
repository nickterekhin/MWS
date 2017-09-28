package db;

import db.jdbcadapter.JDBCAdapter;
import domain.Flat;
import domain.Photo;

import java.util.List;

/**
 * Created by Nick on 01.04.2015.
 */
public interface FlatJDBC{
    public List<Flat> getAllFlats();
    public List<Flat> getAllFlatPaged();
    public List<Flat> getAllFlatsByUserId(Long userId);
    public void createFlat(Flat flat);
    public void flatAction(String action, Long flatId);
    public void updateFlat(Flat flat);
    public Flat getFlatById(Long flatid);
    public void setPhotoInGallery(Long flatId, String photo,String fileName, int pos);
    public void updatePhotoInGallery(Photo photo);
    public boolean isPhotoInGalleryByFileName(Long flatId, String photoNum, Photo p);
    public boolean addDescriptionToPhoto(Long position,Long flatId,String description);
    public String getPhotoDescription(Long position,Long flatId);
    public Photo getPhoto(Long postion,Long flatId);
    public boolean deletePhotoFromGallery(Long position,Long flatId);
    public boolean deleteAllPhotoFromGallery(Long flatId);
    public boolean isPhotoInGallery(Long flatId, String photoNum, Photo photo);
    public List<Photo> getPhotosFromGallery(Long flatId);
    public void setDefaultPhoto(Long flatId, String photoFileName);
}
