package com.example.teamproject.teamversus;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.databinding.ItemTeamBinding;

public class TeamVersusAdapter extends RecyclerView.Adapter<TeamVersusHolder> {

    TeamVersusViewModel vm;

    public TeamVersusAdapter(TeamVersusViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public TeamVersusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTeamBinding binding = ItemTeamBinding.inflate(inflater, parent, false);
        TeamVersusHolder holder = new TeamVersusHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamVersusHolder holder, int position) {
        holder.binding.setItem(vm.teamAccountList.get(position));
    }

    @Override
    public int getItemCount() {
        return vm.teamAccountList.size();
    }

    public void setList() {
        notifyDataSetChanged();
    }
}


