package com.niyaz.beattheclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class play_layout extends AppCompatActivity {
    public static String appusername;
    TextView usernamedisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_layout);
         appusername = getIntent().getStringExtra("USERNAME");
        usernamedisp= (TextView)findViewById(R.id.usernamedisp);
        usernamedisp.setText(appusername);
        quiz.timeerstop=true;
    }

    public void onclickplay(View v){
        Button b = (Button)v;
        Intent intent = new Intent(getApplicationContext(),quiz.class);
        intent.putExtra("user",appusername);
        startActivity(intent);
    }
}
