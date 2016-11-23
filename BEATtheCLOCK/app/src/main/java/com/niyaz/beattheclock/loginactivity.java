package com.niyaz.beattheclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    EditText useredit,passedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        useredit = (EditText)findViewById(R.id.useredit);
        passedit = (EditText)findViewById(R.id.passedit);
    }

    public void onclicklogin(View v){
        Button b = (Button)v;
        if (useredit.getText().toString().equals("admin") && passedit.getText().toString().equals("admin")){
        Intent intent = new Intent(getApplicationContext(),qm.class);
        startActivity(intent);}
        else
            Toast.makeText(loginactivity.this,"Please check username & password",Toast.LENGTH_LONG).show();
    }


}
