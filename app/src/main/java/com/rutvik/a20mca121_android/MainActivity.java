package com.rutvik.a20mca121_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
     EditText Email,pass;
     Button btnadd;
     DatabaseReference Book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email =findViewById(R.id.edemail);
        pass =findViewById(R.id.edpass);
        btnadd=findViewById(R.id.btnadd);

        Book= FirebaseDatabase.getInstance().getReference().child("user");

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map=new HashMap<>();
                map.put("email", Email.getText().toString());
                map.put("pass", pass.getText().toString());

                Book.push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("onComplete", "onComplete: ");
                               // Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("onFailure", "onFailure: "+e.toString());
                                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("onSuccess", "onSuccess: ");
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        });

    }
}