package db.impl;

import db.CountryJDBC;
import db.FlatJDBC;
import db.jdbcadapter.JDBCAdapter;
import domain.Flat;
import domain.Photo;
import org.joda.time.DateTime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 01.04.2015.
 */
public class FlatJDBCImpl extends JDBCAdapter implements FlatJDBC {
    private Connection connection = null;
    private CountryJDBC countryJDBC=null;
    public FlatJDBCImpl(CountryJDBC countryJDBC)
    {
        this.countryJDBC = countryJDBC;
    }
    @Override
    public List<Flat> getAllFlats() {
        List<Flat> flats = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM catalog WHERE isActive=1");
            ResultSet res = preparedStatement.executeQuery();
            while(res.next())
            {
                Flat flat = new Flat();
                initFlat(res,flat);
                flats.add(flat);
            }
            return flats;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }


    @Override
    public List<Flat> getAllFlatPaged() {
        return null;
    }

    @Override
    public List<Flat> getAllFlatsByUserId(Long userId) {
        List<Flat> flats = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM catalog WHERE uid=? ");
            preparedStatement.setLong(1,userId);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next())
            {
                Flat flat = new Flat();
                initFlat(res,flat);
                flats.add(flat);
            }
            return flats;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;

    }
    public void updateFlat(Flat flat)
    {
        if(flat==null)
        return;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE catalog SET id=" +flat.getFlatId()+","+
                    "uid=" +flat.getUid()+","+
                    "country=" +flat.getCountryId()+","+
                    "city='" +flat.getCityName()+"',"+
                    "District='" +flat.getDistrictName()+"',"+
                    "Street='" +flat.getStreet()+"',"+
                    "FlatNumber=" +flat.getFlatnumber()+","+
                    "Rooms=" +flat.getRoomsQty()+","+
                    "Square=" +flat.getFlatSquare()+","+
                    "Level=" +flat.getLevel()+","+
                    "isPrivate=" +flat.getIsPrivate()+","+
                    "buildDate=" +Math.round(flat.getBuildDate().getMillis()/1000)+","+
                    "Description='" +flat.getFullDescription()+"',"+
                    "price_forSale=" +flat.getPriceForSale()+","+
                    "price_forRent=" +flat.getPriceForRent()+","+
                    "rentRate='" + flat.getRentRate()+"',"+
                    "isRent="+flat.getIsRent()+" WHERE id="+flat.getFlatId());
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

    }

    @Override
    public Flat getFlatById(Long flatId) {
        if(flatId==null)
            return null;

        try{
            connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM catalog WHERE id="+flatId);
            ResultSet r = preparedStatement.executeQuery();

            if(r.next())
            {
                Flat flat = new Flat();
                initFlat(r,flat);
                if(flat.getFlatId()!=null && flat.getFlatId()!=0) {
                    flat.setGallery(this.getPhotosFromGallery(flat.getFlatId()));
                }
                return flat;
            }
            return null;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public void setPhotoInGallery(Long flatId, String photo,String fileName, int pos) {
        if(flatId==null)
            return;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Gallery (id,flatId,Photo,FileName,pos)VALUES(default,?,?,?,?)");
            preparedStatement.setLong(1,flatId);
            preparedStatement.setString(2,photo);
            preparedStatement.setString(3,fileName);
            preparedStatement.setInt(4,pos);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

    }

    @Override
    public void updatePhotoInGallery(Photo photo) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Gallery SET Photo=?, FileName=?,pos=? WHERE id=?");
            preparedStatement.setString(1,photo.getPhotoName());
            preparedStatement.setString(2,photo.getFileName());
            preparedStatement.setLong(3,photo.getPhotoId());
            preparedStatement.setLong(4,photo.getPosition());
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean isPhotoInGallery(Long flatId, String photoNum, Photo p) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *,COUNT(id) as item FROM Gallery WHERE flatId=? AND Photo=?");
            preparedStatement.setLong(1,flatId);
            preparedStatement.setString(2,photoNum);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next()) {
                p.setPhotoName(r.getString("Photo"));
                p.setPhotoId(r.getLong("Id"));
                p.setFlatId(r.getLong("flatId"));
                p.setFileName(r.getString("FileName"));
                p.setPosition(r.getInt("pos"));

                return r.getLong("item")>0?true:false;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

        return false;
    }

    public boolean isPhotoInGalleryByFileName(Long flatId, String fileName, Photo p) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *,COUNT(id) as item FROM Gallery WHERE flatId=? AND FileName=?");
            preparedStatement.setLong(1,flatId);
            preparedStatement.setString(2,fileName);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next()) {
                p.setPhotoName(r.getString("Photo"));
                p.setPhotoId(r.getLong("Id"));
                p.setFlatId(r.getLong("flatId"));
                p.setFileName(r.getString("FileName"));
                p.setPosition(r.getInt("pos"));

                return r.getLong("item")>0?true:false;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }

        return false;
    }

    @Override
    public boolean addDescriptionToPhoto(Long position,Long flatId,String description) {

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE gallery SET Description=? WHERE pos=? AND flatId=?");
            preparedStatement.setString(1,description);
            preparedStatement.setLong(2,position);
            preparedStatement.setLong(3,flatId);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }

        return false;
    }

    @Override
    public String getPhotoDescription(Long position,Long flatId) {
            try{
                connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT Description FROM Gallery WHERE pos=? AND flatId=?");
                preparedStatement.setLong(1,position);
                preparedStatement.setLong(2,flatId);
                ResultSet r = preparedStatement.executeQuery();
                if(r.next()) {
                    return r.getString("Description");
                }
                else
                {
                    return "";
                }
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        finally {
                closeConnection(connection);
            }
        return "";
    }

    @Override
    public boolean deletePhotoFromGallery(Long position, Long flatId) {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Gallery WHERE pos=? AND flatId=?");
            preparedStatement.setLong(1,position);
            preparedStatement.setLong(2,flatId);
            preparedStatement.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean deleteAllPhotoFromGallery(Long flatId) {
        return false;
    }

    @Override
    public Photo getPhoto(Long position, Long flatId)
    {
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Gallery WHERE pos=? AND flatId=?");
            preparedStatement.setLong(1,position);
            preparedStatement.setLong(2,flatId);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next())
            {
                Photo p = new Photo();
                initPhoto(p,r);
                return p;
            }else{
             return null;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Photo> getPhotosFromGallery(Long flatId) {
            List<Photo> photoList = new ArrayList<>();
        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Gallery WHERE flatId=?");
            preparedStatement.setLong(1,flatId);
            ResultSet r = preparedStatement.executeQuery();
            while(r.next())
            {
                Photo p = new Photo();
                initPhoto(p,r);
                photoList.add(p);
            }

        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return photoList;
    }

    @Override
    public void setDefaultPhoto(Long flatId, String photoFileName) {

    }

    @Override
    public void createFlat(Flat flat) {
        if(flat==null)
            return;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO catalog (id," +
                                                                                                    "uid," +
                                                                                                    "country," +
                    "city," +
                    "District," +
                    "Street," +
                    "FlatNumber," +
                    "Rooms," +
                    "Square," +
                    "Level," +
                    "isPrivate," +
                    "buildDate," +
                    "Description," +
                    "publishDate," +
                    "isActive," +
                    "def_photo," +
                    "photo,"+
                    "price_forSale," +
                    "price_forRent," +
                    "rentRate," +
                    "isRent" +
                    ")VALUES(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1,flat.getUid());
            preparedStatement.setLong(2,flat.getCountryId());
            preparedStatement.setString(3,flat.getCityName());
            preparedStatement.setString(4,flat.getDistrictName());
            preparedStatement.setString(5,flat.getStreet());
            preparedStatement.setString(6,flat.getFlatnumber());
            preparedStatement.setInt(7,flat.getRoomsQty());
            preparedStatement.setDouble(8,flat.getFlatSquare());
            preparedStatement.setInt(9,flat.getLevel());
            preparedStatement.setBoolean(10,flat.getIsPrivate());
            preparedStatement.setLong(11,Math.round(flat.getBuildDate().getMillis()/1000));
            preparedStatement.setString(12,flat.getFullDescription());
            preparedStatement.setLong(13,Math.round(flat.getRegDate().getMillis()/1000));
            preparedStatement.setBoolean(14,flat.getIsActive());
            preparedStatement.setBoolean(15,flat.getDef_foto());
            preparedStatement.setString(16,flat.getPhoto());
            preparedStatement.setDouble(17,flat.getPriceForSale());
            preparedStatement.setDouble(18,flat.getPriceForRent());
            preparedStatement.setString(19,flat.getRentRate());
            preparedStatement.setBoolean(20,flat.getIsRent());
            preparedStatement.executeUpdate();

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public void flatAction(String action, Long flatId) {
     if(flatId==null)
            return;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement;
            switch(action)
            {
                case "open":
                    preparedStatement= connection.prepareStatement("UPDATE catalog SET isActive=true WHERE id="+flatId);
                    break;
                case "closed":
                    preparedStatement= connection.prepareStatement("UPDATE catalog SET isActive=false WHERE id="+flatId);
                    break;
                case "del":
                    preparedStatement= connection.prepareStatement("DELETE FROM catalog WHERE id="+flatId);
                    break;
                default:
                    preparedStatement= connection.prepareStatement("UPDATE catalog SET isActive='0' WHERE id="+flatId);
            }
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }

    private void initFlat(ResultSet r, Flat f) throws SQLException {
        f.setFlatId(r.getLong("Id"));
        f.setUid(r.getLong("uid"));
        f.setCountryId(r.getLong("country"));
        f.setCountryName(countryJDBC.getCountryNameById(f.getCountryId()));
        f.setCityName(r.getString("city"));
        f.setDistrictName(r.getString("District"));
        f.setStreet(r.getString("Street"));
        f.setFlatnumber(r.getString("FlatNumber"));
        f.setRoomsQty(r.getInt("Rooms"));
        f.setShortDescription(r.getString("FlatShortDescription"));
        f.setFlatSquare(r.getDouble("Square"));
        f.setLevel(r.getInt("Level"));
        f.setIsPrivate(r.getBoolean("isPrivate"));
        f.setBuildDate(new DateTime(r.getLong("buildDate")*1000));
        f.setFullDescription(r.getString("Description"));
        f.setRegDate(new DateTime(r.getLong("publishDate")*1000));
        f.setViewCount(r.getLong("Views"));
        f.setContactPerson(r.getLong("ContactPerson"));
        f.setDefault_contact(r.getBoolean("default_contact"));
        f.setIsActive(r.getBoolean("isActive"));
        f.setDef_foto(r.getBoolean("def_photo"));
        f.setPhoto(r.getString("photo"));
        f.setPriceForSale(r.getDouble("price_forSale"));
        f.setPriceForRent(r.getDouble("price_forRent"));
        f.setRentRate(r.getString("rentRate"));
        f.setIsRent(r.getBoolean("isRent"));
    }

    private void initPhoto(Photo p, ResultSet r) throws SQLException {
        p.setPhotoId(r.getLong("Id"));
        p.setFlatId(r.getLong("flatId"));
        p.setPhotoName(r.getString("Photo"));
        p.setFileName(r.getString("FileName"));
        p.setPosition(r.getInt("pos"));


    }
}
