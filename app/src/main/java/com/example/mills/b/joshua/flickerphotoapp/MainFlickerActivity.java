package com.example.mills.b.joshua.flickerphotoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainFlickerActivity extends BaseActivity implements GetFlickrJsonData.OnDataAvailable,
                                                            RecyclerClickListener.OnRecyclerClickListener{

    private static final String TAG = "MainFlickerActivity";
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_flicker);

        activateToolbar(false);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, recyclerView, this));

        flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(this,new ArrayList<Photo>());
        recyclerView.setAdapter(flickrRecyclerViewAdapter);
        Log.d(TAG, "onCreate: end");


    }

    @Override
    protected void onResume() {
        super.onResume();
        GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(this,UrlPerms.URL.getName(),UrlPerms.lang.getValue(),true);
        getFlickrJsonData.execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: start");
        getMenuInflater().inflate(R.menu.main_menu,menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnDataAvailable(List<Photo> data, DownloadStatus status) {
        if(status == DownloadStatus.OK){
            flickrRecyclerViewAdapter.loadNewData(data);
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this,PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,flickrRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Intent intent = new Intent(this,PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,flickrRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }
}
