package com.niyaz.beattheclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Databasehelper mydb;
    static Integer round=1;
    static Integer quescount=1;
    public  static List<Integer> roundlist =new ArrayList<>();
    private EditText useredit,passedit,useredit1,passedit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useredit= (EditText)findViewById(R.id.useredit);
        passedit= (EditText)findViewById(R.id.passedit);
        useredit1= (EditText)findViewById(R.id.useredit1);
        passedit1= (EditText)findViewById(R.id.passedit1);
        mydb = new Databasehelper(this);
     //   round=1;
      //  quescount=1;
        //roundlist =new ArrayList<>();
       // roundlist.add(round);
    }

    public void onclickloginasqm(View v){
        Button b = (Button)v;
        if (b.getId()==R.id.logqm){
            Intent intent = new Intent(getApplicationContext(),loginactivity.class);
            startActivity(intent);}
         if (b.getId()==R.id.logqt){
            Intent intent1 = new Intent(getApplicationContext(),loginactivity2.class);
            startActivity(intent1);
        }

    }
}
