package com.example.fabiokawai.activitymanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private TextView remainingMonday;
    private TextView remainingTuesday;
    private TextView remainingWednesday;
    private TextView remainingThursday;
    private TextView remainingFriday;
    private DayWeek monday;
    private DayWeek tuesday;
    private DayWeek wednesday;
    private DayWeek thursday;
    private DayWeek friday;
    public Activities listOfActivities;
    private static final int RESULT_REGISTER_OK = 10;
    private boolean isRelatoryAvailable = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        monday = new DayWeek(240);
        tuesday = new DayWeek(240);
        wednesday = new DayWeek(240);
        thursday = new DayWeek(240);
        friday = new DayWeek(240);

        listOfActivities = new Activities();

        remainingMonday = (TextView) findViewById(R.id.txtRemainingMonday);
        remainingTuesday = (TextView) findViewById(R.id.txtRemainingTuesday);
        remainingWednesday = (TextView) findViewById(R.id.txtRemainingWednesday);
        remainingThursday = (TextView) findViewById(R.id.txtRemainingThursday);
        remainingFriday = (TextView) findViewById(R.id.txtRemainingFriday);

        updateScreen();

    }

    public void updateScreen(){

        remainingMonday.setText(minutesToTimeString(monday.getRemainingRoutine()));
        remainingTuesday.setText(minutesToTimeString(tuesday.getRemainingRoutine()));
        remainingWednesday.setText(minutesToTimeString(wednesday.getRemainingRoutine()));
        remainingThursday.setText(minutesToTimeString(thursday.getRemainingRoutine()));
        remainingFriday.setText(minutesToTimeString(friday.getRemainingRoutine()));

        if (remainingMonday.getText().toString().contains("-")){
            remainingMonday.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            remainingMonday.setTextColor(Color.parseColor("#000000"));
        }
        if (remainingTuesday.getText().toString().contains("-")){
            remainingTuesday.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            remainingTuesday.setTextColor(Color.parseColor("#000000"));
        }
        if (remainingWednesday.getText().toString().contains("-")){
            remainingWednesday.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            remainingWednesday.setTextColor(Color.parseColor("#000000"));
        }
        if (remainingThursday.getText().toString().contains("-")){
            remainingThursday.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            remainingThursday.setTextColor(Color.parseColor("#000000"));
        }
        if (remainingFriday.getText().toString().contains("-")){
            remainingFriday.setTextColor(Color.parseColor("#FF0000"));
        }
        else{
            remainingFriday.setTextColor(Color.parseColor("#000000"));
        }
    }

    private String minutesToTimeString(int minutes){
        int hour = minutes/60;
        int minute = minutes%60;
        String time;

        //ADD HOUR
        if (hour >= 0) {
            if (hour < 10) {
                time = " 0" + Integer.toString(hour) + "h : ";
            } else {
                time = " "+ Integer.toString(hour) + "h : ";
            }
        }
        else{
            hour = -hour;
            if (hour < 10) {
                time = "-0" + Integer.toString(hour) + "h : ";
            } else {
                time = "-"+ Integer.toString(hour) + "h : ";
            }
        }

        //ADD MINUTE
        if (minute >= 0){
            if (minute<10){
                if (minute == 0){
                    time = " " + time + "00m";
                }
                else{
                    time = " " + time + "0" + Integer.toString(minute) + "m";
                }
            }
            else{
                time = " " + time + Integer.toString(minute) + "m";
            }
        }
        else{
            minute = -minute;
            if (!time.startsWith("-")) {
                if (minute < 10) {
                    if (minute == 0) {
                        time = "-" + time + "00m";
                    } else {
                        time = "-" + time + "0" + Integer.toString(minute) + "m";
                    }
                } else {
                    time = time + Integer.toString(minute) + "m";
                }
            }
            else{
                if (minute < 10) {
                    if (minute == 0) {
                        time = time + "00m";
                    } else {
                        time = time + "0" + Integer.toString(minute) + "m";
                    }
                } else {
                    time = time + Integer.toString(minute) + "m";
                }
            }

        }


        return time;
    }


    public void mondayClick(View view) {
        //Segunda
        Intent i = new Intent(this, RecordActivity.class);
        Bundle params = new Bundle();
        params.putString("day", "monday");
        params.putStringArrayList("list", (ArrayList<String>) listOfActivities.getListActivities());
        i.putExtras(params);
        startActivityForResult(i, 2);
    }

    public void tuesdayClick(View view) {
        //terça
        Intent i = new Intent(this, RecordActivity.class);
        Bundle params = new Bundle();
        params.putString("day", "tuesday");
        params.putStringArrayList("list", (ArrayList<String>) listOfActivities.getListActivities());
        i.putExtras(params);
        startActivityForResult(i, 2);
    }

    public void wednesdayClick(View view) {
        //quarta
        Intent i = new Intent(this, RecordActivity.class);
        Bundle params = new Bundle();
        params.putString("day", "wednesday");
        params.putStringArrayList("list", (ArrayList<String>) listOfActivities.getListActivities());
        i.putExtras(params);
        startActivityForResult(i, 2);
    }

    public void thursdayClick(View view) {
        //quinta
        Intent i = new Intent(this, RecordActivity.class);
        Bundle params = new Bundle();
        params.putString("day", "thursday");
        params.putStringArrayList("list", (ArrayList<String>) listOfActivities.getListActivities());
        i.putExtras(params);
        startActivityForResult(i, 2);
    }

    public void fridayClick(View view) {
        //sexta
        Intent i = new Intent(this, RecordActivity.class);
        Bundle params = new Bundle();
        params.putString("day", "friday");
        params.putStringArrayList("list", (ArrayList<String>) listOfActivities.getListActivities());
        i.putExtras(params);
        startActivityForResult(i, 2);
    }

    public void addActivity(String name){
        listOfActivities.addActivity(name);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){
            case 2: //Record Activity
                if (resultCode == RESULT_OK) {
                    try{
                        String day =data.getStringExtra("day");
                        int minutes = data.getIntExtra("minutes", 0);
                        int activity = data.getIntExtra("activity", 0);
                        int significance = data.getIntExtra("significance", 0);
                        DailyActivity activityToRecord = new DailyActivity(listOfActivities.getAtividade(activity), minutes, significance);
                        switch (day){
                            case "monday":
                                monday.addDailyActivity(activityToRecord);
                                break;
                            case "tuesday":
                                tuesday.addDailyActivity(activityToRecord);
                                break;
                            case "wednesday":
                                wednesday.addDailyActivity(activityToRecord);
                                break;
                            case "thursday":
                                thursday.addDailyActivity(activityToRecord);
                                break;
                            case "friday":
                                friday.addDailyActivity(activityToRecord);
                                break;
                        }
                         Toast.makeText(this, "Atividade Registrada", Toast.LENGTH_SHORT).show();
                        isRelatoryAvailable = true;
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Um erro ocorreu ao registrar atividade", Toast.LENGTH_SHORT).show();
                    }

                }
                if (resultCode == RESULT_REGISTER_OK){
                    try{
                        listOfActivities.addActivity(data.getStringExtra("activityName"));
                        Toast.makeText(this, "Atividade cadastrada", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Um erro ocorreu ao cadastrar atividade", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case 3: //Weekly Routine
                if (resultCode == RESULT_OK) {
                    try{
                        monday.setRoutine(data.getIntExtra("mondayRoutine", 0));
                        tuesday.setRoutine(data.getIntExtra("tuesdayRoutine", 0));
                        wednesday.setRoutine(data.getIntExtra("wednesdayRoutine", 0));
                        thursday.setRoutine(data.getIntExtra("thursdayRoutine", 0));
                        friday.setRoutine(data.getIntExtra("fridayRoutine", 0));
                    }
                    catch(Exception e){
                        Toast.makeText(this, "Um erro ocorreu ao alterar a jornada", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case 4: //Register Activity
                if (resultCode == RESULT_OK){
                    try{
                        listOfActivities.addActivity(data.getStringExtra("activityName"));
                    }
                    catch (Exception e){
                        Toast.makeText(this, "Um erro ocorreu ao cadastrar atividade", Toast.LENGTH_SHORT).show();
                    }
                    //activityName
                }
                break;
        }
        try{
            updateScreen();
        }
        catch (Exception e){
            Toast.makeText(this, "Falha ao atualizar informações da tela", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickChangeRoutine(View view) {
        Intent i = new Intent(this, WeeklyRoutine.class);
        Bundle params = new Bundle();
        params.putInt("mondayRoutine", monday.getRoutine());
        params.putInt("tuesdayRoutine", tuesday.getRoutine());
        params.putInt("wednesdayRoutine", wednesday.getRoutine());
        params.putInt("thursdayRoutine", thursday.getRoutine());
        params.putInt("fridayRoutine", friday.getRoutine());
        i.putExtras(params);
        startActivityForResult(i, 3);
    }

    public void clickRegisterActivity(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivityForResult(i, 4);
    }

    public int getAverageImportance(DayWeek day){
        int averageImportance = 0;
        int count = 0;
        for (DailyActivity dailyActivity:day.getDailyRoutine()) {
            averageImportance += dailyActivity.getImportance();
            count++;
        }
        averageImportance = averageImportance / count;
        return averageImportance;
    }

    public void clickRelatory(View view) {

            if (isRelatoryAvailable){
                ArrayList<String> mondayActivities = new ArrayList<String>();
                ArrayList<String> tuesdayActivities = new ArrayList<String>();
                ArrayList<String> wednesdayActivities = new ArrayList<String>();
                ArrayList<String> thursdayActivities = new ArrayList<String>();
                ArrayList<String> fridayActivities = new ArrayList<String>();

                Intent i = new Intent(this, RelatoryActivity.class);
                Bundle params = new Bundle();

                try{
                    for (DailyActivity dailyActivity:monday.getDailyRoutine()) {
                        mondayActivities.add(dailyActivity.getName() + "(" + minutesToTimeString(dailyActivity.getMinutes()) + ")");
                    }
                }
                catch (Exception e){

                }
                try{
                    for (DailyActivity dailyActivity:tuesday.getDailyRoutine()) {
                        tuesdayActivities.add(dailyActivity.getName() + "(" + minutesToTimeString(dailyActivity.getMinutes()) + ")");
                    }
                }
                catch (Exception e){

                }

                try{
                    for (DailyActivity dailyActivity:wednesday.getDailyRoutine()) {
                        wednesdayActivities.add(dailyActivity.getName() + "(" + minutesToTimeString(dailyActivity.getMinutes()) + ")");
                    }
                }
                catch (Exception e){

                }

                try{
                    for (DailyActivity dailyActivity:thursday.getDailyRoutine()) {
                        thursdayActivities.add(dailyActivity.getName() + "(" + minutesToTimeString(dailyActivity.getMinutes()) + ")");
                    }
                }
                catch (Exception e){

                }

                try{
                    for (DailyActivity dailyActivity:friday.getDailyRoutine()) {
                        fridayActivities.add(dailyActivity.getName() + "(" + minutesToTimeString(dailyActivity.getMinutes()) + ")");
                    }
                }
                catch (Exception e){

                }
                try{
                    params.putStringArrayList("listMonday", mondayActivities);
                    params.putInt("mondayImportance", getAverageImportance(monday));
                    params.putString("remainingMonday", remainingMonday.getText().toString());
                }
                catch (Exception e){
                    params.putString("remainingMonday", minutesToTimeString(monday.getRoutine()));

                }
                try{
                    params.putStringArrayList("listTuesday", tuesdayActivities);
                    params.putInt("tuesdayImportance", getAverageImportance(tuesday));
                    params.putString("remainingTuesday", remainingTuesday.getText().toString());
                }
                catch (Exception e){
                    params.putString("remainingTuesday", minutesToTimeString(tuesday.getRoutine()));
                }
                try{
                    params.putStringArrayList("listWednesday", wednesdayActivities);
                    params.putInt("wednesdayImportance", getAverageImportance(wednesday));
                    params.putString("remainingWednesday", remainingWednesday.getText().toString());
                }
                catch (Exception e){
                    params.putString("remainingWednesday", minutesToTimeString(wednesday.getRoutine()));
                }
                try{
                    params.putStringArrayList("listThursday", thursdayActivities);
                    params.putInt("thursdayImportance", getAverageImportance(thursday));
                    params.putString("remainingThursday", remainingThursday.getText().toString());
                }
                catch (Exception e){
                    params.putString("remainingThursday", minutesToTimeString(thursday.getRoutine()));
                }
                try{
                    params.putStringArrayList("listFriday", fridayActivities);
                    params.putInt("fridayImportance", getAverageImportance(friday));
                    params.putString("remainingFriday", remainingFriday.getText().toString());
                }
                catch (Exception e){
                    params.putString("remainingFriday", minutesToTimeString(friday.getRoutine()));
                }
                try{
                    i.putExtras(params);
                    startActivityForResult(i, 9);
                }
                catch (Exception e){}


            }
            else{
                Toast.makeText(this, "Sem informações disponíveis", Toast.LENGTH_SHORT).show();
            }




    }
}
