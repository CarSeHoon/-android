package com.example.teamproject;


import android.annotation.SuppressLint;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private FirebaseAuth mFirebaseAuth;         //파이어베이스 인증
    private DatabaseReference mDatabaseRef;
    private ArrayList<UserAccount> arryList;
    private Context context;

    public CustomAdapter(ArrayList<UserAccount> arryList, Context context) {
        this.arryList = arryList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("teamproject");
        Glide.with(holder.itemView)
                .load(arryList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_id.setText(arryList.get(position).getEmailId());
        holder.tv_userName.setText(arryList.get(position).getName());
        holder.tv_score.setText(arryList.get(position).getScore());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(arryList.get(position).isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                // 여기의 item은 final 키워드를 붙인 모델 클래스의 객체와 동일하다
                arryList.get(position).setSelected(isChecked);
            }
        });

/*        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.checkBox = (CheckBox) v;
                UserAccount account = new UserAccount();
                if(holder.checkBox.isChecked()) {
                    account.setSelected(true);
                }
                else if(!holder.checkBox.isChecked()){
                    account.setSelected(false);
                }
            }
        });
        holder.checkBox.setSelected(arryList.get(position).isSelected());*/



    }

    @Override
    public int getItemCount() {
        return (arryList != null ? arryList.size() : 0);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView iv_profile;
        TextView tv_id, tv_userName, tv_score;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.isSelected);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);
            this.tv_score = itemView.findViewById(R.id.tv_score);

        }
    }
}

