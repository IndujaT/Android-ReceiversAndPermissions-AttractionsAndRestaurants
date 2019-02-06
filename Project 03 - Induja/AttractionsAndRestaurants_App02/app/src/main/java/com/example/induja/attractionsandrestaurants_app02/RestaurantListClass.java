package com.example.induja.attractionsandrestaurants_app02;

/**
 * Created by Induja on 3/31/2018.
 */

public class RestaurantListClass {
    String place_name;
    String url;

    public RestaurantListClass(String place_name, String url){
        this.place_name = place_name;
        this.url = url;
    }

    public String getPlace_name(){
        return place_name;
    }
    public void setPlace_name(String place_name){
        this.place_name = place_name;
    }

    public  String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
}
