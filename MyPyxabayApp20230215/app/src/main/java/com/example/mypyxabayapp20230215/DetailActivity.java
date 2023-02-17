package com.example.mypyxabayapp20230215;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // les composants graphiques
       ImageView ivImageView = findViewById(R.id.imageView);
       TextView tvCreater = findViewById(R.id.tvCreater);
       TextView tvLikes= findViewById(R.id.tvLikes);

       //le recuperation des data




    }
}