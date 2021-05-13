package com.rutvik.a20mca121_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BookActivity extends AppCompatActivity {
    private EditText title,description,author;
    private TextView text;
    private Button save,read;
    private DatabaseReference Post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        author = findViewById(R.id.author);
        save = findViewById(R.id.save);

        Post = FirebaseDatabase.getInstance().getReference().child("Post");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("title",title.getText().toString());
                map.put("description",description.getText().toString());
                map.put("author",author.getText().toString());

                Post.push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("d", "onComplete: ");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.i("d", "onFailure: "+e.toString());
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("d", "onSuccess: ");
                        Toast.makeText(BookActivity.this, "Book Add", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BookActivity.this,PostListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

    }
}