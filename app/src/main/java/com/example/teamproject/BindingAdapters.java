package com.example.teamproject;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.ready.ReadyAdapter;
import com.example.teamproject.recycler.RecyclerAdapter;
import com.example.teamproject.teamversus.TeamAccount;
import com.example.teamproject.teamversus.TeamVersusAdapter;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;

public class BindingAdapters {
    @BindingAdapter("userAccounts")
    public static void setUserAccounts(RecyclerView view, @Nullable ArrayList<UserAccount> items) {
        RecyclerAdapter adapter = (RecyclerAdapter)view.getAdapter();
        adapter.setList();
    }

    @BindingAdapter("readyUserAccounts")
    public static void setReadyUserAccounts(RecyclerView view, @Nullable ArrayList<UserAccount> items) {
        ReadyAdapter adapter = (ReadyAdapter)view.getAdapter();
        adapter.setList();
    }

    @BindingAdapter("teamAccounts")
    public static void setTeamAccounts(RecyclerView view, @Nullable ArrayList<TeamAccount> teamAccount) {
        TeamVersusAdapter adapter = (TeamVersusAdapter)view.getAdapter();
        adapter.setList();
    }

    @BindingAdapter("flexAccount")
    public static void setFlexAccounts(FlexboxLayout view, @Nullable ArrayList<UserAccount> accounts) {
        accounts.forEach(user -> {
            TextView tv = new TextView(view.getContext());
            tv.setText(user.getName());
            view.addView(tv);
        });
    }
}
