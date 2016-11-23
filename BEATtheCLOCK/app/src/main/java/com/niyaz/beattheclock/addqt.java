package com.niyaz.beattheclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class addqt extends qm {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addqt);
    }

    public void onllickqtbuttons(View v){
        Button b = (Button)v;

        if (b.getId()== R.id.addqt1){
            Intent intent = new Intent(getApplicationContext(),addquiztakers.class);
            startActivity(intent);

        }
        else if (b.getId()==R.id.delqt){
            Intent intent = new Intent(getApplicationContext(),delquiztakers.class);
            startActivity(intent);

        }
    }
}
