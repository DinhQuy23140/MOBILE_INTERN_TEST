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

    // dùng field khác thay cho "class"
    @SerializedName("class")
    private String locationClass;

    @SerializedName("type")
    private String type;

    @SerializedName("importance")
    private String importance;

    @SerializedName("icon")
    private String icon;

    public Location(List<String> boundingBox, String displayName, String icon, String importance, String lat, String licence, String locationClass, String lon, String osmId, String osmType, String placeId, String type) {
        this.boundingBox = boundingBox;
        this.displayName = displayName;
        this.icon = icon;
        this.importance = importance;
        this.lat = lat;
        this.licence = licence;
        this.locationClass = locationClass;
        this.lon = lon;
        this.osmId = osmId;
        this.osmType = osmType;
        this.placeId = placeId;
        this.type = type;
    }

    public List<String> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(List<String> boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLocationClass() {
        return locationClass;
    }

    public void setLocationClass(String locationClass) {
        this.locationClass = locationClass;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public String getOsmId() {
        return osmId;
    }

    public void setOsmId(String osmId) {
        this.osmId = osmId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
