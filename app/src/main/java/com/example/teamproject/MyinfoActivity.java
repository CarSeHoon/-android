package com.example.teamproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyinfoActivity extends AppCompatActivity{
    private FirebaseAuth mFirebaseAuth;         //파이어베이스 인증
    private DatabaseReference mDatabaseRef;     //실시간 데이터베이스

    TextView et_name, et_email, et_birthday, et_score;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_birthday = findViewById(R.id.et_birth);
        et_score = findViewById(R.id.et_score);
        check = findViewById(R.id.btn_check);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teamproject");

        mDatabaseRef.child("UserAccount").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAccount user = snapshot.getValue(UserAccount.class);
                String name = user.getName();
                String email = user.getEmailId();
                String birthday = user.getBirthday();
                String score = user.getScore();

                et_score.setText(score);
                et_name.setText(name);
                et_email.setText(email);
                et_birthday.setText(birthday);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyinfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
