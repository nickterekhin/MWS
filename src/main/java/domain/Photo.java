package domain;

/**
 * Created by Nick on 24.06.2015.
 */
public class Photo {
    private Long photoId;
    private Long flatId;
    public String photoName;
    private String fileName;
    private int position;
    private String description;

    public Photo() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;

        Photo photo = (Photo) o;

        if (photoId != photo.photoId) return false;
        if (flatId != photo.flatId) return false;
        if (!photoName.equals(photo.photoName)) return false;
        return fileName.equals(photo.fileName);

    }

    @Override
    public int hashCode() {
        int result = photoId.hashCode();
        result = 31 * result + flatId.hashCode();
        result = 31 * result + photoName.hashCode();
        result = 31 * result + fileName.hashCode();
        return result;
    }
}
