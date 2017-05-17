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
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText editName, editAge, editAddress;
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editAddress = (EditText) findViewById(R.id.editText_address);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString());

        if(isInserted){
            Log.d("MyContact", "Data insertion successful");
            //Create toast message to user indicating data inserted correctly
            Toast toast = null;
            toast.makeText(this, "Toast message successful", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("MyContact", "Data insertion not successful");
            //Create toast message to user indicating data inserted incorrectly
        }
    }

    public void viewData(View v){
        Log.d("MyContact", "Started viewData");
        Cursor res = myDb.getAllData();
        Log.d("MyContact", "intialized Cursor");
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            Log.d("MyContact" , "No data found in database");
            //put a Log.d message and toast
            return;
        }
        Log.d("MyContact", res.getCount() + " things in the database");
/*        StringBuffer buffer = new StringBuffer();
        //setup loop with moveToNext method
        //  append each COL to buffer
        //  use getString method

        Log.d("MyContact", "buffer" + buffer);

        int loc = 0;
        while(res.moveToNext()){
            for(int i = 0; i<4; i++){
                if(i == 0) buffer.append("ID:");
                if(i == 1) buffer.append("NAME: ");
                if(i == 2) buffer.append("AGE: ");
                if(i == 3) buffer.append("ADDRESS: ");
                buffer.append(res.getString(i));
                buffer.append("\n");
                loc++;
            }
        }
        showMessage("Data", buffer.toString());*/
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
