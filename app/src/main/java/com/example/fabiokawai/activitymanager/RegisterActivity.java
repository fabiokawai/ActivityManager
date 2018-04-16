package com.example.fabiokawai.activitymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       txtName = (TextView) findViewById(R.id.txtName);

    }

    public void registerActivity(View view) {
       Intent i = new Intent();
       if (txtName.length() > 0){
           i.putExtra("activityName", txtName.getText().toString());
           setResult(RESULT_OK, i);
       }
       else{
           setResult(RESULT_CANCELED);
       }

       finish();
    }
}
