package com.rutvik.a20mca121_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class RViewActivity extends AppCompatActivity {
    TextView rid,rname,rdept;
    String id,name,dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_view);

        ActionBar actionBar=getSupportActionBar();

        rid=findViewById(R.id.txtrid);
        rname=findViewById(R.id.txtrname);
        rdept=findViewById(R.id.txtrdept);
        getdata();
        setdata();


    }
    private void getdata(){
        if (getIntent().hasExtra("gid")&&getIntent().hasExtra("gname")&&getIntent().hasExtra("gdept")){
            id=getIntent().getStringExtra("gid");
            name=getIntent().getStringExtra("gname");
            dept=getIntent().getStringExtra("gdept");


        }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    private  void setdata(){
        rid.setText(id);
        rname.setText(name);
        rdept.setText(dept);

    }
}
