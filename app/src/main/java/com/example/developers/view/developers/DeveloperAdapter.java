package com.example.developers.view.developers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developers.R;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
    @NonNull
    @Override
    public DeveloperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dev_img;
        TextView dev_name;
        TextView dev_title;
        TextView dev_salary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dev_img = itemView.findViewById(R.id.dev_img);
            dev_name = itemView.findViewById(R.id.dev_name);
            dev_title = itemView.findViewById(R.id.dev_title);
            dev_salary = itemView.findViewById(R.id.dev_salary);
        }
    }
}
