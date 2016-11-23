package com.niyaz.beattheclock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delquiztakers extends addquiztakers {


    Databasehelper mydb;
    private EditText deluseredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delquiztakers);
        mydb=new Databasehelper(this);
        deluseredit= (EditText)findViewById(R.id.deluseredit);
    }


    public void onclickdeluser(View v){
        Button b= (Button)v;
       Integer test= mydb.delstat(deluseredit.getText().toString());
        mydb.clearqtstat(deluseredit.getText().toString());
        if (test>0)
            Toast.makeText(delquiztakers.this, "Data Deleted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(delquiztakers.this, "Data NOT Deleted", Toast.LENGTH_LONG).show();
    }

    public void onclickdelstats(View v){
        Button b= (Button)v;
         mydb.update(deluseredit.getText().toString());
        mydb.clearqtstat(deluseredit.getText().toString());

    }
    public void onclickshowstatus(View v){
        Button b = (Button)v;
        onclickshow(v);
    }


}
