package com.example.wangk6771.mycontactapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName, editAge, editAddress, searchFor;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editAddress = (EditText) findViewById(R.id.editText_address);
        searchFor = (EditText) findViewById(R.id.editText_searchFor);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editAddress.getText().toString());

        if(isInserted){
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Toast toast = null;
            toast.makeText(this, "Data insertion successful", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("MyContact", "Data insertion not successful");
            //Create toast message to user indicating data inserted incorrectly
            Toast toast = null;
            toast.makeText(this, "Data insertion not successful", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            Log.d("ViewData" , "No data found in database");
            //put a Log.d message and toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        //setup loop with moveToNext method
        //  append each COL to buffer
        //  use getString method

        Log.d("ViewData", "buffer" + buffer);

        while(res.moveToNext()){
            for(int i = 0; i<4; i++){
                if(i == 0) buffer.append("ID:");
                if(i == 1) buffer.append("NAME: ");
                if(i == 2) buffer.append("AGE: ");
                if(i == 3) buffer.append("ADDRESS: ");
                buffer.append(res.getString(i));
                buffer.append("\n");
            }
        }
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void searchName(View v){
        setContentView(R.layout.search_main);
        if(searchFor.getText().toString()=="") return;
        Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            for (int i = 0; i < 4; i++) {
                if (res.getString(i).equals(searchFor.getText().toString()) && i == 1) {
                    for (int k = 0; k < 4; k++) {
                        if (k == 0) buffer.append("ID: ");
                        if (k == 1) buffer.append("NAME: ");
                        if (k == 2) buffer.append("AGE: ");
                        if (k == 3) buffer.append("ADDRESS: ");
                        buffer.append(res.getString(k));
                        buffer.append("\n");
                    }
                }
            }
        }
        if (buffer.toString().equals("")){
            buffer.append("Not Found");
        }
        //showMessage("Search", buffer.toString());
        TextView display = (TextView) findViewById(R.id.displayText);
        display.setText(buffer.toString());
       }

    public void goBack(View v){
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editAddress = (EditText) findViewById(R.id.editText_address);
        searchFor = (EditText) findViewById(R.id.editText_searchFor);
    }

}
