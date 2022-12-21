package com.example.teamproject.ready;

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

public class ReadyViewModel extends ViewModel {

    public MutableLiveData<ArrayList<UserAccount>> userLiveData = new MutableLiveData<>();
    public ArrayList<UserAccount> userAccounts = new ArrayList<>();

    public int getAverage() {
        return 0;
    }

    public void loadUserData(String roomId) {
        FirebaseDatabase.getInstance()
                .getReference("teamproject")
                .child("RoomAccount")
                .child(roomId)
                .child("user")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userAccounts.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            UserAccount userAccount = snapshot.getValue(UserAccount.class);
                            userAccounts.add(userAccount);
                        }
                        Log.d("RecyclerViewModel", String.valueOf(userAccounts.size()));
                        userLiveData.setValue(userAccounts);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

}
