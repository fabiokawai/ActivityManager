package com.example.fabiokawai.activitymanager;

import java.util.ArrayList;
import java.util.List;

public class Activities {
    private List<String> listActivities = new ArrayList<String>();

    public Activities(){
    }

    public String getAtividade(int index){
        String activity = listActivities.get(index);
        return activity;
    }

    public List<String> getListActivities() {
        return listActivities;
    }

    public void setListActivities(List<String> listActivities) {
        this.listActivities = listActivities;
    }

    public void addActivity(String name){
        listActivities.add(name);
    }

}
