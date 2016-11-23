package com.niyaz.beattheclock;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Handler;
import static android.R.attr.delay;
import static android.R.attr.id;
import static android.R.attr.searchHintIcon;
import static android.R.attr.setupActivity;

public class quiz extends play_layout{
    Integer qcounter=0;
    String usname;
  //  static Integer round = 1;
    long timer1;
    Databasehelper mydb;
    TextView ques,uname,unameset,settime,quescounter,rndno;
    Button op1,op2,op3,op4;
    List<Integer>  list = new ArrayList<>();
    Integer count,j,qrnd=1,score=0;

    static  List<Integer> scorelist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
         usname = getIntent().getStringExtra("user");
         ques = (TextView)findViewById(R.id.ques);
         uname =(TextView)findViewById(R.id.uname);
         uname.setText(loginactivity2.getusername); //set user name
         op1 = (Button)findViewById(R.id.op1);
         op2 = (Button)findViewById(R.id.op2);
         op3 = (Button)findViewById(R.id.op3);
         op4 = (Button)findViewById(R.id.op4);
         unameset = (TextView)findViewById(R.id.unameset);
         settime = (TextView)findViewById(R.id.settime);

        quescounter = (TextView)findViewById(R.id.quescounter);
        rndno = (TextView)findViewById(R.id.rndno) ;
        rndno.setText(String.valueOf(MainActivity.round));
        quescounter.setText("Out of "+String.valueOf(qrnd));
        mydb= new Databasehelper(this);
        count = getcount();
        Integer l;
        for (l=1;l<=count;l++)
            list.add(l);
        Collections.shuffle(list);
        j = list.get(qcounter);
        setdata(String.valueOf(j));
        timeerstop=false;

    }

    public void setdata(String id){

        Cursor res=mydb.getquesbyid(id);
        if (res.getCount()==0){
            return;
        }
        Cursor res1=mydb.getalldata();
        if (res1.getCount()==0){
            return;
        }

        ques.setText(res.getString(1));
        Integer k;
        List<String>  options = new ArrayList<>();
        for (k =2; k<6 ;k++) {
                String str = res.getString(k);
                options.add(str);
            }
        Collections.shuffle(options);
        op1.setText(options.get(0));
        op2.setText(options.get(1));
        op3.setText(options.get(2));
        op4.setText(options.get(3));
        settime.setText(res.getString(6));
        startCountDown(Long.valueOf(res.getString(6))*1000);
        counter.start();                                              //counter start

    }
    CountDownTimer counter;
    public void startCountDown(long duration) {
       counter = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(timeerstop==true)
                {
                    counter.cancel();
                    timeerstop=false;
                    System.out.println("STOP");
                    return;
                }
                else
                settime.setText("" + millisUntilFinished / 1000);


            }

            @Override
            public void onFinish() {


                if (MainActivity.quescount%5==0){
                    ++MainActivity.round;
                    ++MainActivity.quescount;
                    scorelist.add(score);
                    timeerstop=true;
                    System.out.println("counter cancelled onFInish");
                    MainActivity.roundlist.add(MainActivity.round-1);
                    rndno.setText(String.valueOf(MainActivity.round));
                    insertstat();
                    Intent intent = new Intent(getApplicationContext(),optiontocontinue.class);
                    intent.putExtra("user1",usname);
                    startActivity(intent);
                }


                else {
                    System.out.println("counter finished");
                    System.out.println("ques count " +MainActivity.quescount);
                    timeerstop=true;
                    ++MainActivity.quescount;
                    quescounter.setText("Out of " + String.valueOf(qrnd));
                    ++qcounter;
                    ++qrnd;
                    if (qcounter < count){
                        timeerstop=false;
                        setdata(String.valueOf(list.get(qcounter)));}
                    else {
                        Collections.shuffle(list);
                        qcounter = 0;
                        timeerstop=false;
                        setdata(String.valueOf(list.get(qcounter)));
                    }
                }
            }

        };
    }



    public Integer getcount(){
        Integer t;
        Cursor c = mydb.getallques();
        t= c.getCount();
        return t;
    }

    public Integer randomcount(int max,int min){

        Integer randnum = min + (int)(Math.random() * ((max - min) + 1));
        return randnum;
    }
    static boolean timeerstop;

    public void onclickanswers(View v){
        Button b = (Button)v;
    //   counter.cancel();                                  // counter stop

        timeerstop=true;
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(100);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(7);
        Cursor  cursor = mydb.getquesbyid(String.valueOf(list.get(qcounter)));



        if (b.getText().toString().equalsIgnoreCase(cursor.getString(2))){
            String str =unameset.getText().toString();
            score= Integer.valueOf(str);
            score = score +1;
            b.setBackgroundColor(0xff00ff00);
            b.startAnimation(anim);
            Runnable test = new Runnable() {
                @Override
                public void run() {
                    ++qcounter;
                    ++qrnd;
                  //  j=qcounter;
                    if (qcounter<count){
                        ++MainActivity.quescount;
                        setdata(String.valueOf(list.get(qcounter)));}
                    else
                    {
                        Collections.shuffle(list);
                        qcounter=0;
                        ++MainActivity.quescount;
                        setdata(String.valueOf(list.get(qcounter)));
                    }
                    resetbutton();
                }
            };
            b.postDelayed(test, 3000);

            unameset.setText(String.valueOf(score));
            quescounter.setText("Out of "+String.valueOf(qrnd));

        }
        else {

            b.setBackgroundColor(0xffff0000);
            b.startAnimation(anim);
            for(int i = 1; i<5; i++ ){
                int buttonId = getResources().getIdentifier("op"+i, "id", getPackageName());
                Button t = (Button)findViewById(buttonId);
                if (t.getText().toString().equalsIgnoreCase(cursor.getString(2))){
                    t.setBackgroundColor(0xff00ff00);
                    t.startAnimation(anim);
                    break;
                }
            }
            Runnable test = new Runnable() {
                @Override
                public void run() {
                    ++qcounter;
                    ++qrnd;
                   // j=qcounter;
                    if (qcounter<count){
                        ++MainActivity.quescount;
                        setdata(String.valueOf(list.get(qcounter)));}
                    else
                    {
                        Collections.shuffle(list);
                        qcounter=0;
                        ++MainActivity.quescount;
                        setdata(String.valueOf(list.get(qcounter)));
                    }

                    resetbutton();

                }
            };
            b.postDelayed(test, 3000);
            quescounter.setText("Out of "+String.valueOf(qrnd));
        }
        if (MainActivity.quescount%5==0){
            counter.cancel();
            MainActivity.round++;
            scorelist.add(score);
         //  counter.cancel();
            System.out.println("counter cancel in button");
            MainActivity.roundlist.add(MainActivity.round-1);
            rndno.setText(String.valueOf(MainActivity.round));
            insertstat();

                    Intent intent = new Intent(getApplicationContext(), optiontocontinue.class);
                    intent.putExtra("user1", usname);
                    startActivity(intent);

        }

    }

        Integer color = 0xff888888;
        public void resetbutton(){
            op1.setBackgroundColor(color);
            op2.setBackgroundColor(color);
            op3.setBackgroundColor(color);
            op4.setBackgroundColor(color);
        }

        public void onclicklogout(View v){
            Button b = (Button)v;
            Intent intent = new Intent(getApplicationContext(),play_layout.class);
            intent.putExtra("USERNAME",usname);
            startActivity(intent);
        }

    public  static String t_id;
        public void getid(){
            System.out.println(loginactivity2.getusername);
            t_id = mydb.getdatabyname(loginactivity2.getusername);
        }

        boolean check;
        public void insertstat(){
           getid();
            Cursor res = mydb.getround(t_id);
        Integer i = res.getCount();
        ++i;
        check = mydb.insertstat(t_id,loginactivity2.getusername,String.valueOf(i),score.toString());
        if (check == true)
            Toast.makeText(quiz.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(quiz.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
    }

}
