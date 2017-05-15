package com.example.wangk6771.mycontactapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

       // editName = (EditText) findViewById(R.id.editText_name);
        //editAge = (EditText) findViewById(R.id.editText_age);
        //editAddress = (EditText) findViewById(R.id.editText_address);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString());

        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly

        }
        else{
            Log.d("MyContact", "Data insertion not successful");
            //Create toast message to user indicating data inserted incorrectly
        }
    }


}
