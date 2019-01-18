package com.li.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class DisplayActivity extends AppCompatActivity {

    public final static String ARG_PATH = "image_path";

    public static Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_display);

        String path = getIntent().getStringExtra(ARG_PATH);

        final ZoomImageView image = findViewById(R.id.image);

        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        }
    }
}
