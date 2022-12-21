package com.example.teamproject.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.UserAccount;
import com.example.teamproject.databinding.ListItemBinding;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    RecyclerViewModel vm;

    public RecyclerAdapter(RecyclerViewModel vm) {
        this.vm = vm;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListItemBinding binding = ListItemBinding.inflate(inflater, parent, false);
        RecyclerHolder holder = new RecyclerHolder(binding.getRoot());
        holder.binding = binding;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.binding.setItem(vm.userAccounts.get(position));
    }

    @Override
    public int getItemCount() {
        return vm.userAccounts.size();
    }

    public void setList() {
        notifyDataSetChanged();
    }
}
