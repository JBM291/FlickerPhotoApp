package com.example.mills.b.joshua.flickerphotoapp;

/**
 * Created by joshua on 03/20/2018.
 */

public enum UrlPerms {
    format("format","json"),
    nojsoncallback("nojsoncallback","1"),
    tagModeAny("tagMode","any"),
    tagModeAll("tagMode","all"),
    lang("lang",""),
    URL("URL","https://api.flickr.com/services/feeds/photos_public.gne");

    private final String name;
    private final String value;
    private  UrlPerms(String name, String value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
      return this.name;
    }

    public String getValue(){
       return this.value;
    }

    @Override
    public String toString() {
        return this.name+this.value;
    }
}
