package com.sundroid.traininfo.activity;

/**
 * Created by emobi5 on 4/15/2016.
 */
public class InfoApps {

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_code() {
        return station_code;
    }

    public void setStation_code(String station_code) {
        this.station_code = station_code;
    }

    public String getTrain_status() {
        return train_status;
    }

    public void setTrain_status(String train_status) {
        this.train_status = train_status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getScharr_expectedarrivaltime() {
        return scharr_expectedarrivaltime;
    }

    public void setScharr_expectedarrivaltime(String scharr_expectedarrivaltime) {
        this.scharr_expectedarrivaltime = scharr_expectedarrivaltime;
    }

    public String getSchdeptime() {
        return schdeptime;
    }

    public void setSchdeptime(String schdeptime) {
        this.schdeptime = schdeptime;
    }

    public String getPlatform_no() {
        return platform_no;
    }

    public void setPlatform_no(String platform_no) {
        this.platform_no = platform_no;
    }

    public String getStation_distance() {
        return station_distance;
    }

    public void setStation_distance(String station_distance) {
        this.station_distance = station_distance;
    }

    private String station_name;
    private String station_code;
    private String train_status;

    public String getTrain_status_position() {
        return train_status_position;
    }

    public void setTrain_status_position(String train_status_position) {
        this.train_status_position = train_status_position;
    }

    private String train_status_position;
    private String day;

    public String getTraindate() {
        return traindate;
    }

    public void setTraindate(String traindate) {
        this.traindate = traindate;
    }

    private String traindate;
    private String scharr_expectedarrivaltime;
    private String schdeptime;
    private String platform_no;

    public String getAct_dep() {
        return act_dep;
    }

    public void setAct_dep(String act_dep) {
        this.act_dep = act_dep;
    }

    private String act_dep;

    public String getExp_dep() {
        return exp_dep;
    }

    public void setExp_dep(String exp_dep) {
        this.exp_dep = exp_dep;
    }

    private String exp_dep;
    private String station_distance;

    public String getTrain_number() {
        return train_number;
    }

    public void setTrain_number(String train_number) {
        this.train_number = train_number;
    }

    private String train_number;

    public String getActarr_expectedarrivaltime() {
        return actarr_expectedarrivaltime;
    }

    public void setActarr_expectedarrivaltime(String actarr_expectedarrivaltime) {
        this.actarr_expectedarrivaltime = actarr_expectedarrivaltime;
    }

    private String actarr_expectedarrivaltime;


    @Override
    public String toString() {

        return "Name:-"+station_distance+",Number:-"+schdeptime;
    }
}
