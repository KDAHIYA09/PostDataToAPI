package com.example.transline_assignment_app.Models;

import com.google.gson.annotations.SerializedName;


public class UploadResponse {

    @SerializedName("path")
    private String path;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("size")
    private int size;

    @SerializedName("mime")
    private String mime;

    @SerializedName("meta")
    private MetaData meta;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }


}



