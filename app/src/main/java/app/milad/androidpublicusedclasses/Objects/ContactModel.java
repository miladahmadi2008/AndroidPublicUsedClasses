package app.milad.androidpublicusedclasses.Objects;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by milad on 7/23/2017.
 */

public class ContactModel {
    public String id;
    public String name;
    public String mobileNumber;
    public Bitmap photo;
    public Uri photoURI;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public Bitmap getPhoto()
    {
        return photo;
    }

    public void setPhoto(Bitmap photo)
    {
        this.photo = photo;
    }

    public Uri getPhotoURI()
    {
        return photoURI;
    }

    public void setPhotoURI(Uri photoURI)
    {
        this.photoURI = photoURI;
    }
}