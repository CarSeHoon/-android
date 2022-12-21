package com.example.teamproject.teamversus;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.teamproject.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TeamVersusViewModel extends ViewModel {

    public MutableLiveData<ArrayList<TeamAccount>> teamLiveData = new MutableLiveData<>();
    public ArrayList<TeamAccount> teamAccountList = new ArrayList<TeamAccount>();

    public void loadTeamVersusList(String roomId, int teamNumber, int teamPerson) {
        Log.d("TeamVersusViewModel", "teamNumber" + teamNumber);
        Log.d("TeamVersusViewModel", "teamPerson" + teamPerson);

        FirebaseDatabase.getInstance()
                .getReference("teamproject")
                .child("RoomAccount")
                .child(roomId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        teamAccountList.clear();

                        //String roomName = dataSnapshot.child("roomName").getValue(String.class);
                        ArrayList<UserAccount> userAccounts = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.child("user").getChildren()) {
                            UserAccount userAccount = snapshot.getValue(UserAccount.class);
                            userAccounts.add(userAccount);
                        }

                        Collections.sort(userAccounts, new ScoreComparator());

                        ArrayList<TeamAccount> teams = getTeamConfiguration(userAccounts, teamNumber, teamPerson);
                        teams.forEach(a -> {
                            Log.d("TeamVersusViewModel", "TeamName" + a.getTeamName());
                            a.getTeamUsers().forEach(user -> {
                                Log.d("TeamVersusViewModel", "Score:" + user.getScore() + " Name:" + user.getName());
                            });
                        });
                        teamAccountList.addAll(teams);
                        teamLiveData.setValue(teamAccountList);

                        Log.d("TeamVersusViewModel", "team size" + teams.size());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    private ArrayList<TeamAccount> getTeamConfiguration(ArrayList<UserAccount> userAccounts, int teamNumber, int teamPerson) {
        ArrayList<TeamAccount> teams = new ArrayList<>();

        for (int i = 0; i < teamNumber; i++) {
            TeamAccount teamAccount = new TeamAccount();
            teamAccount.setTeamName((i + 1) + "팀");
            teamAccount.setTeamUsers(new ArrayList<>());
            teams.add(teamAccount);
        }

        for (int i = 0; i< userAccounts.size(); i++) {
            int teamIndex = i % teamNumber;
            if (i < teamNumber) {
                teams.get(teamIndex).getTeamUsers().add(userAccounts.get(i));
            } else {
                teams.get(teamIndex).getTeamUsers().add(0, userAccounts.get(i));
            }
        }

        return teams;
    }
}
