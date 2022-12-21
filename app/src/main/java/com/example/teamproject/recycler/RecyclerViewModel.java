package com.example.teamproject.recycler;

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
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewModel extends ViewModel {

    public MutableLiveData<String> inputText = new MutableLiveData<>();
    public MutableLiveData<ArrayList<UserAccount>> userLiveData = new MutableLiveData<>();
    public ArrayList<UserAccount> userAccounts = new ArrayList<>();

    public void filtering(String text) {
        List<UserAccount> filtered =  userAccounts.stream()
                .filter(m -> m.getEmailId().contains(text))
                .collect(Collectors.toList());

        userLiveData.setValue(toArrayList(filtered));
    }

    public List<UserAccount> getCheckedUsers() {
        return userAccounts.stream()
                .filter(m -> m.isSelected())
                .collect(Collectors.toList());
    }

    public boolean checkUserAvailable(int teamCount, int teamPerson) {
        int size = userAccounts.stream()
                .filter(m -> m.isSelected())
                .collect(Collectors.toList()).size();

        Log.d("RecyclerViewModel", "Size" + size);
        Log.d("RecyclerViewModel", "teamCount" + teamCount);
        Log.d("RecyclerViewModel", "teamPerson" + teamPerson);

        return size == (teamCount * teamPerson) && size != 0;
    }

    public void loadUserData() {
        FirebaseDatabase.getInstance()
                .getReference("teamproject")
                .child("UserAccount")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userAccounts.clear();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Log.d("RecyclerViewModel", "Size");
                            UserAccount userAccount = snapshot.getValue(UserAccount.class);
                            Log.d("RecyclerViewModel", "Size" + userAccount.getEmailId());
                            userAccount.setIsShowCheckBox(true);
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

    private ArrayList<UserAccount> toArrayList(List<UserAccount> list) {
        ArrayList<UserAccount> arrayList = new ArrayList<>();
        arrayList.addAll(list);
        return arrayList;
    }


}
