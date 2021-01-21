package com.example.lenovo.firebasestorage1;

public class upload {
    private  String mName;
    private  String mImageurl;

    public upload(String name,String Imageurl)
    {
        if(name.trim().equals(""))
        {
            name="No Name";
        }
    mName=name;
    mImageurl=Imageurl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }

    public String getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(String Imageurl) {
        mImageurl = Imageurl;
    }
}
