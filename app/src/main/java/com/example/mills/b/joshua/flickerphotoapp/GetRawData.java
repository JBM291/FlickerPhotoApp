package com.example.mills.b.joshua.flickerphotoapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.EventLogTags;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Inferno on 3/21/2018.
 */
enum DownloadStatus{
    IDLE,
    PROCESSING,
    NOT_INITIALIZED,
    FAILED_OR_EMPTY,
    OK
}
class GetRawData  extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";
    private final OnDownloadComplete callBack;
    private DownloadStatus downloadStatus;

    interface OnDownloadComplete{
        void onDownloadComplete(String data, DownloadStatus status);
    }

    public GetRawData(OnDownloadComplete callBack) {
        this.downloadStatus = DownloadStatus.IDLE;
        this.callBack = callBack;
    }

    void executeSameTread(String s){
       if(this.callBack != null){
           this.callBack.onDownloadComplete(doInBackground(s),this.downloadStatus);
       }
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: perm"+ s);
        if(callBack != null){
           callBack.onDownloadComplete(s,this.downloadStatus);
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        if(strings == null){
            this.downloadStatus = DownloadStatus.NOT_INITIALIZED;
            return null;
        }
        try {
            URL url = new URL(strings[0]);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            Log.d(TAG, "doInBackground: response: "+ response);

            StringBuilder result = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                result.append(line).append("\n");
            }
            this.downloadStatus = DownloadStatus.OK;
            return result.toString();

        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: "+ e.getMessage() );
        }catch (IOException e){
            Log.e(TAG, "doInBackground: "+ e.getMessage() );
        }catch (SecurityException e){
            Log.e(TAG, "doInBackground: "+ e.getMessage() );
        }finally {
            if(connection != null){
                connection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    Log.e(TAG, "doInBackground: " + e.getMessage() );
                }
            }
        }
        downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        return null;
    }

}
