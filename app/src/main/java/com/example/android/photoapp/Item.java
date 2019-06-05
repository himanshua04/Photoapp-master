package com.example.android.photoapp;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

public class Item {
    Bitmap birdListName;

    public Item(Bitmap birdName)
    {
        this.birdListName=birdName;
    }

    public Bitmap getbirdImage()
    {
        return birdListName;
    }

}
