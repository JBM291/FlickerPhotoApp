package com.example.mills.b.joshua.flickerphotoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainFlickerActivity extends AppCompatActivity implements GetFlickrJsonData.OnDataAvailable{

    private static final String TAG = "MainFlickerActivity";
    private FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_flicker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(this,new ArrayList<Photo>());
        recyclerView.setAdapter(flickrRecyclerViewAdapter);
        Log.d(TAG, "onCreate: end");


    }

    @Override
    protected void onResume() {
        super.onResume();
        GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(this,UrlPerms.URL.getName(),UrlPerms.lang.getValue(),false);
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



}
