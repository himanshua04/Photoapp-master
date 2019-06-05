package com.example.android.photoapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Item> {

  //  public ArrayList<Item> birdList;
    private static final String LOG_TAG = MyAdapter.class.getSimpleName();

    public MyAdapter(Activity context, ArrayList<Item> objects) {
        super(context, 0, objects);
       // birdList = (ArrayList<Item>)objects;
    }
/*
    @Override
    public int getCount() {
        return birdList.size();
    }
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(
                    R.layout.particular_image, parent, false);
        }

     //   LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  v = inflater.inflate(R.layout.activity_main, null);
        Item currentAndroidFlavor = getItem(position);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
       // Bitmap f=currentAndroidFlavor.getbirdImage();
        //Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

        imageView.setImageBitmap(currentAndroidFlavor.getbirdImage());

        return v;



    }

}