package com.example.mills.b.joshua.flickerphotoapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.Format;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        activateToolbar(true);

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo!=null){
            Resources resources = getResources();
            TextView photoTitle = findViewById(R.id.photo_title);
            photoTitle.setText(String.format(resources.getString(R.string.photo_title_text),photo.getTitle()));

            TextView photoTags = findViewById(R.id.photo_tags);
            photoTags.setText(String.format(resources.getString(R.string.photo_tags_text),photo.getTags()));

            TextView photoAurthor = findViewById(R.id.photo_author);
            photoAurthor.setText(String.format(resources.getString(R.string.photo_author_text),photo.getAuthor()));

            ImageView imageView = findViewById(R.id.photo_image);

            Picasso.get().load(photo.getLink())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
        }
    }

}
