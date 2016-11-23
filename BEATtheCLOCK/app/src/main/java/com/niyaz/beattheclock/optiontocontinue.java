package com.niyaz.beattheclock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

public class optiontocontinue extends qm {

    Databasehelper mydb;
    String us1name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optiontocontinue);
        mydb = new Databasehelper(this);
        us1name = getIntent().getStringExtra("user1");
    }

    public void onclickbuttonsoptions(View v ) {
        Button b = (Button)v;
        if (b.getId()==R.id.bscore){


            StringBuffer buffer = new StringBuffer();

            buffer.append("NAME "+loginactivity2.getusername+"\n");
            System.out.println("roundlist size"+MainActivity.roundlist.size()+"\n");
            for (int i = 0;i<MainActivity.roundlist.size();i++) {
                buffer.append("ROUND " + MainActivity.roundlist.get(i) + " SCORE " + quiz.scorelist.get(i) + "\n");
                System.out.println(MainActivity.roundlist.get(i) +"\n");
                System.out.println(quiz.scorelist.get(i) +"\n");
            }
            showmessage("STATS",buffer.toString());
        }
        else  if (b.getId()==R.id.bcontinue){
            Intent intent = new Intent(getApplicationContext(),quiz.class);
          //  intent.putExtra("user",us1name);
            startActivity(intent);
        }
        else  if (b.getId()==R.id.blogout){
                MainActivity.roundlist.clear();
            quiz.scorelist.clear();
            MainActivity.round=1;
            MainActivity.quescount=1;
            System.out.println("roundlist size"+MainActivity.roundlist.size()+"\n");
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }


}
