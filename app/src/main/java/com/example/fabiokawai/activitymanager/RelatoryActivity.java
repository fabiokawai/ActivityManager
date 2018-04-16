package com.example.fabiokawai.activitymanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RelatoryActivity extends AppCompatActivity {

    private String[] hexColor = {"255", "00", "00"};
    ArrayList<String> listMondayActivities;
    ArrayList<String> listTuesdayActivities;
    ArrayList<String> listWednesdayActivities;
    ArrayList<String> listThursdayActivities;
    ArrayList<String> listFridayActivities;
    ListView listMonday;
    ListView listTuesday;
    ListView listWednesday;
    ListView listThursday;
    ListView listFriday;
    TextView colorMonday;
    TextView colorTuesday;
    TextView colorWednesday;
    TextView colorThursday;
    TextView colorFriday;

    TextView titleMonday;
    TextView titleTuesday;
    TextView titleWednesday;
    TextView titleThursday;
    TextView titleFriday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatory);

        titleMonday = (TextView) findViewById(R.id.txtMonday);
        titleTuesday = (TextView) findViewById(R.id.txtTuesday);
        titleWednesday = (TextView) findViewById(R.id.txtWednesday);
        titleThursday = (TextView) findViewById(R.id.txtThursday);
        titleFriday = (TextView) findViewById(R.id.txtFriday);




        colorMonday = (TextView) findViewById(R.id.colorTxtMonday);
        colorTuesday = (TextView) findViewById(R.id.colorTxtTuesday);
        colorWednesday = (TextView) findViewById(R.id.colorTxtWednesday);
        colorThursday = (TextView) findViewById(R.id.colorTxtThursday);
        colorFriday = (TextView) findViewById(R.id.colorTxtFriday);

        listMonday = (ListView) findViewById(R.id.listViewMonday);
        listTuesday = (ListView) findViewById(R.id.listViewTuesday);
        listWednesday = (ListView) findViewById(R.id.listViewWednesday);
        listThursday = (ListView) findViewById(R.id.listViewThursday);
        listFriday = (ListView) findViewById(R.id.listViewFriday);

        listMondayActivities = new ArrayList<String>();
        listTuesdayActivities = new ArrayList<String>();
        listWednesdayActivities = new ArrayList<String>();
        listThursdayActivities = new ArrayList<String>();
        listFridayActivities = new ArrayList<String>();


        int importance;

        Intent i = getIntent();
        if (i!=null){
            Bundle params = i.getExtras();
            if (params != null){
                listMondayActivities = params.getStringArrayList("listMonday");
                listTuesdayActivities = params.getStringArrayList("listTuesday");
                listWednesdayActivities = params.getStringArrayList("listWednesday");
                listThursdayActivities = params.getStringArrayList("listThursday");
                listFridayActivities = params.getStringArrayList("listFriday");


                ArrayAdapter<String> adapterMonday = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listMondayActivities);
                ArrayAdapter<String> adapterTuesday = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listTuesdayActivities);
                ArrayAdapter<String> adapterWednesday = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listWednesdayActivities);
                ArrayAdapter<String> adapterThursday = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listThursdayActivities);
                ArrayAdapter<String> adapterFriday = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, listFridayActivities);

                listMonday.setAdapter(adapterMonday);
                listTuesday.setAdapter(adapterTuesday);
                listWednesday.setAdapter(adapterWednesday);
                listThursday.setAdapter(adapterThursday);
                listFriday.setAdapter(adapterFriday);

                importance = params.getInt("mondayImportance", 0);
                setHexNumber(colorMonday, importance, (byte)0);
                setHexNumber(colorMonday,255-importance, (byte)1);

                importance = params.getInt("tuesdayImportance", 0);
                setHexNumber(colorTuesday, importance, (byte)0);
                setHexNumber(colorTuesday,255-importance, (byte)1);

                importance = params.getInt("wednesdayImportance", 0);
                setHexNumber(colorWednesday, importance, (byte)0);
                setHexNumber(colorWednesday,255-importance, (byte)1);

                importance = params.getInt("thursdayImportance", 0);
                setHexNumber(colorThursday, importance, (byte)0);
                setHexNumber(colorThursday,255-importance, (byte)1);

                importance = params.getInt("fridayImportance", 0);
                setHexNumber(colorFriday, importance, (byte)0);
                setHexNumber(colorFriday,255-importance, (byte)1);

                titleMonday.setText(titleMonday.getText() + " (" + params.getString("remainingMonday") + " restantes" + "  )");
                titleTuesday.setText(titleTuesday.getText() + " (" +  params.getString("remainingTuesday") + " restantes" + "  )");
                titleWednesday.setText(titleWednesday.getText() + " (" +  params.getString("remainingWednesday") + " restantes" + "  )");
                titleThursday.setText(titleThursday.getText() + " (" +  params.getString("remainingThursday") + " restantes" + "  )");
                titleFriday.setText(titleFriday.getText() + " (" +  params.getString("remainingFriday") + " restantes" + "  )");
            }
        }


    }

    private void setColor(TextView colorBox, String s){
        colorBox.setBackgroundColor(Color.parseColor(s));
    }

    private void setHexNumber(TextView colorBox, int progress, byte color){
        String c = Integer.toHexString(progress);
        hexColor[color] = (c.length() == 2 ? "":"0") + c;
        setColor(colorBox,"#" + hexColor[0] + hexColor[1] + "00");
    }
}
