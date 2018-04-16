package com.example.fabiokawai.activitymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class WeeklyRoutine extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar mondaySeekBar;
    private SeekBar tuesdaySeekBar;
    private SeekBar wednesdaySeekBar;
    private SeekBar thursdaySeekBar;
    private SeekBar fridaySeekBar;

    private TextView txtMonday;
    private TextView txtTuesday;
    private TextView txtWednesday;
    private TextView txtThursday;
    private TextView txtFriday;

    private int mondayRoutine;
    private int tuesdayRoutine;
    private int wednesdayRoutine;
    private int thursdayRoutine;
    private int fridayRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_routine);

        txtMonday = (TextView) findViewById(R.id.txtMonday);
        mondaySeekBar = (SeekBar) findViewById(R.id.seekBarMonday);
        mondaySeekBar.setOnSeekBarChangeListener(this);

        txtTuesday = (TextView) findViewById(R.id.txtTuesday);
        tuesdaySeekBar = (SeekBar) findViewById(R.id.seekBarTuesday);
        tuesdaySeekBar.setOnSeekBarChangeListener(this);

        txtWednesday = (TextView) findViewById(R.id.txtWednesday);
        wednesdaySeekBar = (SeekBar) findViewById(R.id.seekBarWednesday);
        wednesdaySeekBar.setOnSeekBarChangeListener(this);

        txtThursday = (TextView) findViewById(R.id.txtThursday);
        thursdaySeekBar = (SeekBar) findViewById(R.id.seekBarThursday);
        thursdaySeekBar.setOnSeekBarChangeListener(this);

        txtFriday = (TextView) findViewById(R.id.txtFriday);
        fridaySeekBar = (SeekBar) findViewById(R.id.seekBarFriday);
        fridaySeekBar.setOnSeekBarChangeListener(this);

        Intent i = getIntent();
        if (i!=null){
            Bundle params = i.getExtras();
            if (params != null){
                mondayRoutine = params.getInt("mondayRoutine");
                mondaySeekBar.setProgress(mondayRoutine);
                tuesdayRoutine = params.getInt("tuesdayRoutine");
                tuesdaySeekBar.setProgress(tuesdayRoutine);
                wednesdayRoutine = params.getInt("wednesdayRoutine");
                wednesdaySeekBar.setProgress(wednesdayRoutine);
                thursdayRoutine = params.getInt("thursdayRoutine");
                thursdaySeekBar.setProgress(thursdayRoutine);
                fridayRoutine = params.getInt("fridayRoutine");
                fridaySeekBar.setProgress(fridayRoutine);
            }
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        switch (seekBar.getId()){
            case R.id.seekBarMonday:
                mondayRoutine = progress;
                break;
            case R.id.seekBarTuesday:
                tuesdayRoutine = progress;
                break;
            case R.id.seekBarWednesday:
                wednesdayRoutine = progress;
                break;
            case R.id.seekBarThursday:
                thursdayRoutine = progress;
                break;
            case R.id.seekBarFriday:
                fridayRoutine = progress;
                break;
        }
        updateTime();
    }

    private void updateTime(){
        txtMonday.setText(minutesToTimeString(mondayRoutine));
        txtTuesday.setText(minutesToTimeString(tuesdayRoutine));
        txtWednesday.setText(minutesToTimeString(wednesdayRoutine));
        txtThursday.setText(minutesToTimeString(thursdayRoutine));
        txtFriday.setText(minutesToTimeString(fridayRoutine));
    }

    private String minutesToTimeString(int minutes){
        int hour = minutes/60;
        int minute = minutes%60;
        String time;
        //ADD HOUR
        if (hour<10){
            time = "0" + Integer.toString(hour) + "h : ";
        }
        else{
            time = Integer.toString(hour) + "h : ";
        }
        if (minute<10){
            if (minute == 0){
                time = time + "00m";
            }
            else{
                time = time + "0" + Integer.toString(minute) + "m";
            }
        }
        else{
            time = time + Integer.toString(minute) + "m";
        }

        return time;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void clickSaveRoutine(View view) {
        Intent i = new Intent();
        i.putExtra("mondayRoutine", mondayRoutine);
        i.putExtra("tuesdayRoutine", tuesdayRoutine);
        i.putExtra("wednesdayRoutine", wednesdayRoutine);
        i.putExtra("thursdayRoutine", thursdayRoutine);
        i.putExtra("fridayRoutine", fridayRoutine);
        setResult(RESULT_OK, i);
        finish();
    }
}
