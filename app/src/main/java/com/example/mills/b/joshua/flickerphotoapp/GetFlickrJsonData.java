package com.example.mills.b.joshua.flickerphotoapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshua on 03/22/2018.
 */

class GetFlickrJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetFlickrJsonData";
    private List<Photo> photoList = null;
    private String baseUrl;
    private String Language;
    private boolean matchAll;

    private final OnDataAvailable CallBack;



    interface OnDataAvailable{
        void OnDataAvailable(List<Photo> data, DownloadStatus status);
    }

    public GetFlickrJsonData(OnDataAvailable callBack, String baseUrl, String language, boolean matchAll) {
        this.baseUrl = baseUrl;
        Language = language;
        this.matchAll = matchAll;
        CallBack = callBack;
    }

    void executeSameTread(String searchCriteria){
        Log.d(TAG, "executeSameTread: "+ searchCriteria);
        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(new BuildUri().getUri(searchCriteria,matchAll));
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        if(status == DownloadStatus.OK){
            this.photoList = new ArrayList<>();
            try{
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemArray = jsonObject.getJSONArray("items");
                for(int i=0;i<itemArray.length();i++){
                    JSONObject jsonPhoto = itemArray.getJSONObject(i);
                    String title = jsonPhoto.getString("title");
                    String author = jsonPhoto.getString("author");
                    String authorId = jsonPhoto.getString("author_id");
                    String tags = jsonPhoto.getString("tags");

                    JSONObject jsonMedia = jsonPhoto.getJSONObject("media");
                    String photoURL = jsonMedia.getString("m");

                    String link = photoURL.replace("_m.","_b.");

                    Photo photoObj = new Photo(title,author,authorId,link,tags,photoURL);
                    this.photoList.add(photoObj);
                }
            }catch (JSONException e){
                Log.e(TAG, "onDownloadComplete: " + e.getMessage() );
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }

        if(this.CallBack != null){
            this.CallBack.OnDataAvailable(this.photoList, status);
        }
    }
}
