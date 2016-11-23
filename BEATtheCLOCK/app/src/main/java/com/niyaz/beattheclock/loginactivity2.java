package com.niyaz.beattheclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity2 extends AppCompatActivity {

    Databasehelper mydb;
    EditText useredit1,passedit1;
    public static String getusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity2);
        mydb = new Databasehelper(this);
        useredit1 = (EditText)findViewById(R.id.useredit1);
        passedit1 = (EditText)findViewById(R.id.passedit1);

    }
    public void onlicklogqtin(View v){
        Button b = (Button)v;
        String tempuser = useredit1.getText().toString();
        getusername=tempuser;
        String temppass = passedit1.getText().toString();
        String storedpass = mydb.getpassword(tempuser);
        if(temppass.equals(storedpass))
        {
            Intent intent = new Intent(getApplicationContext(), play_layout.class);
            intent.putExtra("USERNAME",tempuser);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(loginactivity2.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }

    }

}
