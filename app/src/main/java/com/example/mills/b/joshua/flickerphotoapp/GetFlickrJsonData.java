package com.example.mills.b.joshua.flickerphotoapp;

import android.util.Log;

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

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {

    }

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
        getRawData.execute(new BuildUrl().getUrl(searchCriteria,matchAll));
    }
}
