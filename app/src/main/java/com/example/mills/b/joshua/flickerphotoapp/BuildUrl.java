package com.example.mills.b.joshua.flickerphotoapp;

import android.graphics.Bitmap;
import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by joshua on 03/20/2018.
 */

public class BuildUrl {


    public String getUrl(String searchCriteria, boolean matchAll){

        return Uri.parse(UrlPerms.URL.getValue()).buildUpon()
                .appendQueryParameter("tags", searchCriteria)
                .appendQueryParameter(UrlPerms.format.getName(),UrlPerms.format.getValue())
                .appendQueryParameter(UrlPerms.nojsoncallback.getName(),UrlPerms.nojsoncallback.getValue())
                .appendQueryParameter(UrlPerms.tagModeAll.getName(), matchAll? UrlPerms.tagModeAll.getValue():UrlPerms.tagModeAny.getValue())
                .appendQueryParameter(UrlPerms.lang.getName(),UrlPerms.lang.getValue())
                .build()
                .toString();
    }





}
