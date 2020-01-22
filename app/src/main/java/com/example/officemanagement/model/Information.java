package com.example.officemanagement.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Information implements Serializable {

    @SerializedName("assignedTask")
    private String assignedTask;
    @SerializedName("currentLocation")
    private String currentLocation;
    @SerializedName("designation")
    private String designation;
    @SerializedName("id")
    private String id;
    @SerializedName("joiningDate")
    private String joiningDate;
    @SerializedName("responsibility")
    private String responsibility;

    public String getAssignedTask() {
        return assignedTask;
    }

    public void setAssignedTask(String assignedTask) {
        this.assignedTask = assignedTask;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

}
