package com.niyaz.beattheclock;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addques extends qm {
    Databasehelper mydb;
    EditText insertquesedit,ansedit,op2edit,op3edit,op4edit,timeedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addques);
        mydb=new Databasehelper(this);
        insertquesedit=(EditText)findViewById(R.id.insertquesedit);
        ansedit= (EditText)findViewById(R.id.ansedit);
        op2edit= (EditText)findViewById(R.id.op2edit);
        op3edit=(EditText)findViewById(R.id.op3edit);
        op4edit=(EditText)findViewById(R.id.op4edit);
        timeedit=(EditText)findViewById(R.id.timeedit);
    }

    boolean inserted;
    public void onclickaddques(View v){
        Button b = (Button)v;

            inserted = mydb.insertques(insertquesedit.getText().toString(), ansedit.getText().toString(),
                    op2edit.getText().toString(),op3edit.getText().toString(),op4edit.getText().toString(),
                    timeedit.getText().toString());
            if (inserted == true)
                Toast.makeText(addques.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(addques.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();

    }
    public void onclickshowques(View v){
        Button b = (Button)v;
        Cursor res=mydb.getallques();
        if (res.getCount()==0){
            showmessage("ERROR","NO DATA FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID "+res.getString(0)+"\n");
            buffer.append("Q "+ res.getString(1)+ "\n");
            buffer.append("time "+res.getString(6)+"\n");
            buffer.append("Answer "+ res.getString(2)+ "\n\n");
        }
        showmessage("Questions",buffer.toString());
    }




}

