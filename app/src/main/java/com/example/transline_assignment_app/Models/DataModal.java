package com.example.transline_assignment_app.Models;

public class DataModal {

    private String id;
    private String created_at;
    private String latlng;
    private String location;
    private String actDate;
    private String remark;


    public DataModal(String id, String created_at, String latlng, String location, String actDate, String remark) {
        this.id = id;
        this.created_at = created_at;
        this.latlng = latlng;
        this.location = location;
        this.actDate = actDate;
        this.remark = remark;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}