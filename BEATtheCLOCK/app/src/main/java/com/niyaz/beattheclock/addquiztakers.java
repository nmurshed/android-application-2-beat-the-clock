package com.niyaz.beattheclock;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addquiztakers extends qm {


    Databasehelper mydb;
    private EditText newuseredit,newpassedit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquiztakers);
         mydb=new Databasehelper(this);
         newuseredit = (EditText)findViewById(R.id.newuseredit);
         newpassedit = (EditText)findViewById(R.id.newpassedit);

    }


    // ADD data
    boolean inserted,check;
    public void onclickadddata(View v){
        Button b = (Button)v;
      //  check = mydb.CheckData(newuseredit.getText().toString());
       // if (check==true){
         //   Toast.makeText(addquiztakers.this, "User Already Exist", Toast.LENGTH_LONG).show();
       // }
        //else {

            inserted = mydb.insertdata(newuseredit.getText().toString(), newpassedit.getText().toString());

            if (inserted == true)
                Toast.makeText(addquiztakers.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(addquiztakers.this, "Data NOT Inserted", Toast.LENGTH_LONG).show();
        //}
    }
    // Show Data
    public void onclickshow(View v){
        Button b = (Button)v;
        Cursor res=mydb.getalldata();
        if (res.getCount()==0){
            showmessage("ERROR","NO DATA FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID "+ res.getString(0)+"\n");
           // buffer.append("Points "+ res.getString(1)+"\n");
            buffer.append("NAME "+ res.getString(2)+ "\n");
            buffer.append("Password "+ res.getString(3)+ "\n\n");
        }
        showmessage("QUIZ TAKERS",buffer.toString());
    }

}
