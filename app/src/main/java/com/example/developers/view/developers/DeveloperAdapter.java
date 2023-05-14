package com.example.developers.view.developers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developers.R;
import com.example.developers.controller.LocalBuilder;
import com.example.developers.model.pojo.DeveloperEntity;

import java.util.List;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
    private List<DeveloperEntity> developerList;
    private DeleteDeveloper deleteDeveloper;

    public DeveloperAdapter(List<DeveloperEntity> developerList, DeleteDeveloper deleteDeveloper) {
        this.developerList = developerList;
        this.deleteDeveloper = deleteDeveloper;
    }

    @NonNull
    @Override
    public DeveloperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.developer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperAdapter.ViewHolder holder, int position) {
        DeveloperEntity developer = developerList.get(position);
        holder.dev_salary.setText(String.valueOf(developer.getSalary()));
        holder.dev_name.setText(developer.getName());
        holder.dev_title.setText(developer.getTitle());
        switch (developer.getTitle()) {
            case "Fresh": {
                holder.dev_img.setImageResource(R.drawable.fresh);
                break;
            }
            case "Junior": {
                holder.dev_img.setImageResource(R.drawable.junior);
                break;
            }
            case "Mid": {
                holder.dev_img.setImageResource(R.drawable.mid);
                break;
            }
            case "Senior": {
                holder.dev_img.setImageResource(R.drawable.senior);
                break;
            }

        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDeveloper.deleteDeveloperById(developer.getId());
                //to refresh list after delete developer
                developerList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), developerList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return developerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView dev_img;
        TextView dev_name;
        TextView dev_title;
        TextView dev_salary;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dev_img = itemView.findViewById(R.id.dev_img);
            dev_name = itemView.findViewById(R.id.dev_name);
            dev_title = itemView.findViewById(R.id.dev_title);
            dev_salary = itemView.findViewById(R.id.dev_salary);
            delete = itemView.findViewById(R.id.delete_image);
        }
    }
}
