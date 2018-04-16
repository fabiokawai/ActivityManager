package com.example.fabiokawai.activitymanager;

public class DailyActivity {
    String name;
    int minutes;
    int importance;
    public DailyActivity(String name, int minutes, int importance){
        this.name = name;
        this.minutes = minutes;
        this.importance = importance;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int min) {
        this.minutes = min;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int i) {
        this.importance = i;
    }
}
