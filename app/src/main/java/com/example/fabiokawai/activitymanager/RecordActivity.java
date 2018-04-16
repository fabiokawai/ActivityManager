package com.example.fabiokawai.activitymanager;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecordActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView txtDay;
    private String[] hexColor = {"255", "00", "00"};
    private SeekBar colorSeekBar;
    private TextView color;
    private TextView txtTimeIni;
    private TextView txtTimeEnd;
    private Calendar calendar1;
    private SimpleDateFormat formatter;
    private Date dateIni;
    private Date dateEnd;
    private Spinner spinner;
    private List<String> listOfActivities = new ArrayList<String>();
    private String day;
    private static final int RESULT_REGISTER_OK = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        txtDay = (TextView) findViewById(R.id.txtDay);
        colorSeekBar = (SeekBar) findViewById(R.id.seekBar);
        colorSeekBar.setOnSeekBarChangeListener(this);
        color = (TextView) findViewById(R.id.txtColor);
        txtTimeIni = (TextView) findViewById(R.id.editTxtTimeIni);
        txtTimeEnd = (TextView) findViewById(R.id.editTxtTimeEnd);

        spinner =  (Spinner) findViewById(R.id.spinner);



        calendar1 = Calendar.getInstance();
        formatter = new SimpleDateFormat("HH:mm");


        setHexNumber(127, (byte)0);
        setHexNumber(128, (byte)1);


        Intent i = getIntent();
        if (i!=null){
            Bundle params = i.getExtras();
            if (params != null){
                day = params.getString("day");
                listOfActivities = params.getStringArrayList("list");

                switch(day){
                    case "monday":
                        txtDay.setText("Segunda");
                        break;
                    case "tuesday":
                        txtDay.setText("Terça");
                        break;
                    case "wednesday":
                        txtDay.setText("Quarta");
                        break;
                    case "thursday":
                        txtDay.setText("Quinta");
                        break;
                    case "friday":
                        txtDay.setText("Sexta");
                        break;
                }
            }
        }

        try {
            dateIni = formatter.parse("12:00");
            dateEnd = formatter.parse("12:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtTimeIni.setText(formatTime(12, 00));
        txtTimeEnd.setText(formatTime(12, 00));

        try{
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listOfActivities);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp1);
        }
        catch (Exception e){

        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        setHexNumber(progress, (byte)0);
        setHexNumber(255-progress, (byte)1);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void setColor(String s){
        color.setBackgroundColor(Color.parseColor(s));
    }

    private void setHexNumber(int progress, byte color){
        String c = Integer.toHexString(progress);
        hexColor[color] = (c.length() == 2 ? "":"0") + c;
        setColor("#" + hexColor[0] + hexColor[1] + "00");
    }


    public void startTimeClick(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        try {
                            dateIni = formatter.parse(hourOfDay + ":" + minute);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        txtTimeIni.setText(formatTime(hourOfDay, minute));
                        try{
                            if (dateEnd.before(dateIni)){
                                dateEnd.setTime(dateIni.getTime());
                                txtTimeEnd.setText(txtTimeIni.getText());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                }, 12, 00, true);
        timePickerDialog.show();

    }

    private String formatTime(int hour, int minute){
        String time;
        if (hour<10){
            time = "0" + Integer.toString(hour) + ":";
        }
        else{
            time = Integer.toString(hour) + ":";
        }
        if (minute<10){
            if (minute == 0){
                time = time + "00";
            }
            else{
                time = time + "0" + Integer.toString(minute) + "";
            }
        }
        else{
            time = time + Integer.toString(minute) + "";
        }
        return time;
    }

    public void endTimeClick(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        try {
                            dateEnd = formatter.parse(hourOfDay + ":" + minute);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        txtTimeEnd.setText(formatTime(hourOfDay, minute));
                        try{
                            if (dateEnd.before(dateIni)){
                                dateIni.setTime(dateEnd.getTime());
                                txtTimeIni.setText(txtTimeEnd.getText());
                            }
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, 12, 00, true);
        timePickerDialog.show();
    }

    private int calculateTime(){
        long intervalMilli = dateEnd.getTime() - dateIni.getTime();
        intervalMilli = intervalMilli / 60000;
        int interval = (int)intervalMilli;
        return  interval;
    }

    public void clickRegisterActivity(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivityForResult(i, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            try{
                Intent i = new Intent();
                String activityName = data.getStringExtra("activityName");
                i.putExtra("day",day);
                i.putExtra("activityName", activityName);
                setResult(RESULT_REGISTER_OK, i);
                finish();
            }
            catch (Exception e){
                setResult(RecordActivity.RESULT_CANCELED);
            }
        }
        else{
            setResult(RecordActivity.RESULT_CANCELED);
        }
    }


    public void clickSaveActivity(View view) {
        try{
            int minutesInterval = calculateTime();
            Intent i = new Intent();
            i.putExtra("day", day);
            i.putExtra("minutes", (Integer)minutesInterval);
            i.putExtra("activity", (Integer)spinner.getSelectedItemPosition());
            i.putExtra("significance", colorSeekBar.getProgress());
            setResult(RESULT_OK, i);
            finish();
        }
        catch (Exception e){
            setResult(RecordActivity.RESULT_CANCELED);
        }

    }
}
