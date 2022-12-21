package com.example.teamproject.ready;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.databinding.ListItemBinding;
import com.example.teamproject.recycler.RecyclerHolder;

public class ReadyAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    ReadyViewModel vm;

    public ReadyAdapter(ReadyViewModel vm) {
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

