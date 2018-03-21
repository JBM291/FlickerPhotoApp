package com.example.mills.b.joshua.flickerphotoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainFlickerActivity extends AppCompatActivity implements GetRawData.OnDownloadComplete {

    private static final String TAG = "MainFlickerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_flicker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GetRawData getRawData = new GetRawData(this);
        BuildUrl url = new BuildUrl();
        getRawData.execute(url.getUrl());

        Log.d(TAG, "onCreate: end");
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
    public void onDownloadComplete(String data, DownloadStatus status){
        if(status == DownloadStatus.OK){
            Log.d(TAG, "onDownloadComplete: "+ data);
        }else{
            Log.e(TAG, "onDownloadComplete: "+status );
        }
    }

}
