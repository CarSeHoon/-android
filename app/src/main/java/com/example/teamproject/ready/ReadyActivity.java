package com.example.teamproject.ready;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamproject.databinding.ActivityReadyBinding;
import com.example.teamproject.teamversus.TeamVersusActivity;

public class ReadyActivity extends AppCompatActivity {

    private ActivityReadyBinding binding;
    private ReadyViewModel vm;
    private ReadyAdapter adapter;

    private String roomId;
    private String roomName;
    private int teamNumber;
    private int teamPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomId = getIntent().getStringExtra("room_id");
        teamNumber = getIntent().getIntExtra("team_number", 0);
        teamPerson = getIntent().getIntExtra("team_person", 0);
        vm = new ViewModelProvider(this).get(ReadyViewModel.class);
        binding = ActivityReadyBinding.inflate(getLayoutInflater());
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        adapter = new ReadyAdapter(vm);
        binding.recyclerView.setAdapter(adapter);
        vm.loadUserData(roomId);
        roomName = getIntent().getStringExtra("room_name");
        binding.roomName.setText(roomName);
        binding.btnConfiguration.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), TeamVersusActivity.class);
            intent.putExtra("room_id", roomId);
            intent.putExtra("average", vm.getAverage());
            intent.putExtra("room_name", roomName);
            intent.putExtra("team_number", teamNumber);
            intent.putExtra("team_person", teamPerson);
            startActivity(intent);
        });

        binding.btnInvite.setOnClickListener(view -> {
            finish();
        });
    }


}
