package com.example.android.photoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    static int REQUEST_IMAGE_CAPTURE=0 ;

   // GridView gridView;
   GridView gridView;
    ArrayList<Item> birdList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File folder = new File(Environment.getExternalStorageDirectory() + "/Photoaap");
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(MainActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
            success = folder.mkdir();
        }
        else{
            String name;
            char ch[];
            for (File f : folder.listFiles()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

                name = f.getName();
                ch=name.toCharArray();
                REQUEST_IMAGE_CAPTURE= Character.getNumericValue(ch[5])+1;
                birdList.add(new Item(myBitmap));
            }
        }
        //gridView=(GridView)findViewById(R.id.gridview);
        gridView = (GridView) findViewById(R.id.gridview);
       MyAdapter myAdapter=new MyAdapter(this,birdList);
        gridView.setAdapter(myAdapter);




    }


     public void toCaptureImage(View view){

        dispatchTakePictureIntent();

    }

    public  void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            birdList.add(new Item(imageBitmap));
            MyAdapter myAdapter=new MyAdapter(this,birdList);
            gridView.setAdapter(myAdapter);
            createDirectoryAndSaveFile(imageBitmap,"/Image"+REQUEST_IMAGE_CAPTURE);
            REQUEST_IMAGE_CAPTURE++;

            extras.clear();
        }

    }
    protected void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File folder = new File(Environment.getExternalStorageDirectory() + "/Photoaap");
        File file = new File(Environment.getExternalStorageDirectory() + "/Photoaap/"+fileName+".kkp");

        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    }

