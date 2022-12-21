package com.example.teamproject.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.databinding.ListItemBinding;

public class RecyclerHolder extends RecyclerView.ViewHolder {

    public ListItemBinding binding;

    public RecyclerHolder(@NonNull View itemView) {
        super(itemView);
    }
}
