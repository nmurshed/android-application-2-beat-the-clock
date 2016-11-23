package com.niyaz.beattheclock;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class qtstats extends addquiztakers {


    Databasehelper mydb;
    TextView statbox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qtstats);
        mydb = new Databasehelper(this);
        statbox = (TextView) findViewById(R.id.statbox);
        showstatbox();
    }

    public void showstatbox() {
        Cursor res = mydb.getallstat();
        if (res.getCount() == 0) {
            showmessage("ERROR", "NO DATA FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("ID " + res.getString(0) + "\n");
            buffer.append("Name " + res.getString(1) + "\n");
            buffer.append("Round " + res.getString(2) + "\n");
            buffer.append("Score " + res.getString(3) + "\n\n");
        }
       statbox.setText(buffer.toString());


    }
}