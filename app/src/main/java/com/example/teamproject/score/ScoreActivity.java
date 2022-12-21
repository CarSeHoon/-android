package com.example.teamproject.score;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.teamproject.MainActivity;
import com.example.teamproject.databinding.ActivityScoreBinding;
import com.example.teamproject.recycler.RecyclerViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.xml.transform.Result;

public class ScoreActivity extends AppCompatActivity {

    private ActivityScoreBinding binding;
    private ScoreViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        binding.setLifecycleOwner(this);

        vm = new ViewModelProvider(this).get(ScoreViewModel.class);
        binding.setVm(vm);
        setContentView(binding.getRoot());
        binding.btnFinish.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("teamproject")
                    .child("UserAccount")
                    .child(FirebaseAuth.getInstance().getUid())
                    .child("score")
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String score = snapshot.getValue(String.class);
                            int currentScore = Integer.parseInt(score);
                            int totalScore = Integer.parseInt(binding.Total2.getText().toString());

                            int average = (currentScore + totalScore) / 2;

                            FirebaseDatabase.getInstance().getReference("teamproject")
                                    .child("UserAccount")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child("score")
                                    .setValue(String.valueOf(average))
                                    .addOnCompleteListener(task ->
                                            finishActivity()
                                    );
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        });

        vm.par1.observe(this, s -> { vm.total1(); });
        vm.par2.observe(this, s -> { vm.total1(); });
        vm.par3.observe(this, s -> { vm.total1(); });
        vm.par4.observe(this, s -> { vm.total1(); });
        vm.par5.observe(this, s -> { vm.total1(); });
        vm.par6.observe(this, s -> { vm.total1(); });
        vm.par7.observe(this, s -> { vm.total1(); });
        vm.par8.observe(this, s -> { vm.total1(); });
        vm.par9.observe(this, s -> { vm.total1(); });

        vm.par10.observe(this, s -> { vm.total2(); });
        vm.par11.observe(this, s -> { vm.total2(); });
        vm.par12.observe(this, s -> { vm.total2(); });
        vm.par13.observe(this, s -> { vm.total2(); });
        vm.par14.observe(this, s -> { vm.total2(); });
        vm.par15.observe(this, s -> { vm.total2(); });
        vm.par16.observe(this, s -> { vm.total2(); });
        vm.par17.observe(this, s -> { vm.total2(); });
        vm.par18.observe(this, s -> { vm.total2(); });
    }

    private void finishActivity() {
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}