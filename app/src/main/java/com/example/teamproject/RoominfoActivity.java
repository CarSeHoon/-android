package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teamproject.databinding.ActivityRoominfoBinding;
import com.example.teamproject.recycler.RecyclerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoominfoActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private ActivityRoominfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoominfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teamproject");





        binding.makecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRoomName = binding.etRoomName.getText().toString();

                RoomAccount account = new RoomAccount();
                account.setRoomName(strRoomName);

                mDatabaseRef.child("RoomAccount").child(mFirebaseAuth.getUid()).setValue(account);

                Intent intent = new Intent(RoominfoActivity.this, RecyclerActivity.class);
                intent.putExtra("room_name", strRoomName);
                intent.putExtra("team_count", Integer.parseInt(binding.etTeamNum.getText().toString()));
                intent.putExtra("team_person", Integer.parseInt(binding.numofid.getText().toString()));
                startActivity(intent);
            }
        });

    }
}