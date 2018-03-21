package com.example.mills.b.joshua.flickerphotoapp;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by joshua on 03/20/2018.
 */

public class BuildUrl {
    private ArrayList<String> tags = new ArrayList<String>();
    private String url;

    public BuildUrl() {
        this.url = UrlPerms.URL.getValue()+"?"+UrlPerms.format+"&"+UrlPerms.nojsoncallback+"&"+UrlPerms.tagMode;
    }


    public String getUrl(){
        String newUrl;
        if(this.tags.size()<1){
            newUrl = url;
        }else{
            newUrl = url+getTags();
        }
        return newUrl;
    }

    public void setTags(String[] tags){
        this.tags = new ArrayList<>(Arrays.asList(tags));
    }

    public void addNewTags(String[] tags){
        this.tags.addAll(Arrays.asList(tags));
    }

    private String getTags(){
        StringBuilder tagList = new StringBuilder();
        tagList.append("tags=");
        for (String tag: tags) {
            if(tags.size() == tags.indexOf(tag)+1){
                tagList.append(tag);
            }else{
                tagList.append(String.format("%s+,",tag));
            }
        }
        return tagList.toString();

    }


}
