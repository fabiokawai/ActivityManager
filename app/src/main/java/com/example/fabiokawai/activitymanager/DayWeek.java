package com.example.fabiokawai.activitymanager;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DayWeek {

    private int routine;
    private int remainingRoutine;
    private List<DailyActivity> dailyRoutine = new ArrayList<DailyActivity>();



    public int getRoutine() {
        return routine;
    }

    public int getRemainingRoutine() {
        checkRemainingRoutine();
        return remainingRoutine;
    }

    public void setRemainingRoutine(int remainingRoutine) {
        this.remainingRoutine = remainingRoutine;
    }

    public void setRoutine(int rt) {
        routine = rt;
    }

    public List<DailyActivity> getDailyRoutine() {
        return dailyRoutine;
    }

    public void setDailyRoutine(List<DailyActivity> dr) {
        this.dailyRoutine = dr;
    }

    public void addDailyActivity(DailyActivity da){
        this.dailyRoutine.add(da);
    }

    public void checkRemainingRoutine(){
        this.remainingRoutine = this.routine;
        for(DailyActivity dailyActivity:dailyRoutine){
            this.remainingRoutine -= dailyActivity.getMinutes();
        }
    }


    public int getHighestCountProgress(){
        int highestCount = 0;
        int highestCountImportance = 999;
        int count = 0;

        for (DailyActivity currentActivity:dailyRoutine) {
            count = 0;
            int currentImportance = currentActivity.getImportance();
            for (DailyActivity activity: dailyRoutine) {
                if (activity.getImportance() == currentImportance){
                    count +=1;

                }
            }
            if (count > highestCount){
                highestCount = count;
                highestCountImportance = currentActivity.getImportance();
            }
        }
        return highestCountImportance;
    }

    public DayWeek(int dayRoutine){
        this.routine = dayRoutine;
        this.remainingRoutine = routine;
    }
}
