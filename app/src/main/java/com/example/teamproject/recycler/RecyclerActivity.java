package com.example.teamproject.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamproject.databinding.ActivityRecyclerBinding;
import com.example.teamproject.ready.ReadyActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding binding;
    private RecyclerViewModel vm;
    private RecyclerAdapter adapter;
    private String roomName;
    private int teamCount;
    private int teamPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomName = getIntent().getStringExtra("room_name");
        teamCount = getIntent().getIntExtra("team_count", 0);
        teamPerson = getIntent().getIntExtra("team_person", 0);
        vm = new ViewModelProvider(this).get(RecyclerViewModel.class);
        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        adapter = new RecyclerAdapter(vm);
        binding.recyclerView.setAdapter(adapter);
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
        vm.inputText.observe(this, text -> vm.filtering(text));

        setContentView(binding.getRoot());
        //mFirebaseAuth = FirebaseAuth.getInstance();
        vm.loadUserData();

        binding.btnInvite.setOnClickListener(view -> {
            boolean enable = vm.checkUserAvailable(teamCount, teamPerson);
            if (enable) {
                String uid = FirebaseAuth.getInstance().getUid();
                if (uid != null) {
                    FirebaseDatabase.getInstance()
                            .getReference("teamproject")
                            .child("RoomAccount")
                            .child(uid)
                            .child("user")
                            .setValue(vm.getCheckedUsers())
                            .addOnCompleteListener( task -> {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(RecyclerActivity.this, ReadyActivity.class);
                                    intent.putExtra("room_id", uid);
                                    intent.putExtra("room_name", roomName);
                                    intent.putExtra("team_number", teamCount);
                                    intent.putExtra("team_person", teamPerson);
                                    startActivity(intent);
                                }
                            });
                }
            } else {
                Toast.makeText(this, "팀 인원 구성을 맞추어주세요", Toast.LENGTH_SHORT).show();
            }
        });

        //mDatabaseRef = database.getReference("teamproject");

        /*binding.btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRef.child("RoomAccount").child(mFirebaseAuth.getUid()).child("user").setValue(arrayList);
                Intent intent = new Intent(RecyclerActivity.this, Ready.class);
                startActivity(intent);

            }
        });*/
    }
}