package com.niyaz.beattheclock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class qm extends MainActivity {

    Databasehelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qm);
        mydb = new Databasehelper(this);
    }

    public void onclickbuttons(View v ) {
        Button b = (Button)v;
        if (b.getId()==R.id.addbutton){
            Intent intent = new Intent(getApplicationContext(),addques.class);
            startActivity(intent);
        }
        else  if (b.getId()==R.id.addqtbutton){
            Intent intent = new Intent(getApplicationContext(),addqt.class);
            startActivity(intent);
        }
        else  if (b.getId()==R.id.statusbutton){


           Cursor temp = mydb.getalldata();
            if (temp.getCount() == 0) {
              showmessage("ERROR", "NO DATA FOUND");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            Cursor res;
            while (temp.moveToNext()){
                 res = mydb.getstatbyname(temp.getString(2));
                buffer.append("                         " + temp.getString(2) + "\n");
                if (res.getCount() == 0) {
                    buffer.append("              Quiz Not Taken\n");
                }

                while (res.moveToNext()) {

                    buffer.append("              Round  " + res.getString(2) + "  Score " + res.getString(3) + "\n");

                }
                buffer.append("\n");

           }
            showmessage("QT Stats",buffer.toString());
        }


    }

public  void  showmessage(String title, String message){

    AlertDialog.Builder builder = new  AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.setPositiveButton("OK",null);
    builder.show();
}

}
