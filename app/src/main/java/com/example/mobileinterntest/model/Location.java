package com.example.mobileinterntest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {
    @SerializedName("place_id")
    private String placeId;

    @SerializedName("licence")
    private String licence;

    @SerializedName("osm_type")
    private String osmType;

    @SerializedName("osm_id")
    private String osmId;

    @SerializedName("boundingbox")
    private List<String> boundingBox;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("class")
    private String clazz;

    @SerializedName("type")
    private String type;

    @SerializedName("importance")
    private String importance;

    @SerializedName("icon")
    private String icon;

    public String getPlaceId() {
        return placeId;
    }

    public String getLicence() {
        return licence;
    }

    public String getOsmType() {
        return osmType;
    }

    public String getOsmId() {
        return osmId;
    }

    public List<String> getBoundingBox() {
        return boundingBox;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getClazz() {
        return clazz;
    }

    public String getType() {
        return type;
    }

    public String getImportance() {
        return importance;
    }

    public String getIcon() {
        return icon;
    }
}
