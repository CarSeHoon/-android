package com.example.teamproject.teamversus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamproject.score.ScoreActivity;
import com.example.teamproject.databinding.ActivityTeamVersusBinding;

public class TeamVersusActivity extends AppCompatActivity {

    private ActivityTeamVersusBinding binding;
    private TeamVersusViewModel vm;
    private TeamVersusAdapter adapter;
    private int average;
    private String roomName;
    private String roomId;
    private int teamNumber;
    private int teamPerson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomId = getIntent().getStringExtra("room_id");
        teamNumber = getIntent().getIntExtra("team_number", 0);
        teamPerson = getIntent().getIntExtra("team_person", 0);
        average = getIntent().getIntExtra("average", 0);
        vm = new ViewModelProvider(this).get(TeamVersusViewModel.class);
        binding = ActivityTeamVersusBinding.inflate(getLayoutInflater());
        binding.setVm(vm);
        binding.setLifecycleOwner(this);
        setContentView(binding.getRoot());
        adapter = new TeamVersusAdapter(vm);
        binding.recyclerView.setAdapter(adapter);
        vm.loadTeamVersusList(roomId, teamNumber, teamPerson);
        roomName = getIntent().getStringExtra("room_name");
        binding.btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ScoreActivity.class);
            startActivity(intent);
        });
    }
}
