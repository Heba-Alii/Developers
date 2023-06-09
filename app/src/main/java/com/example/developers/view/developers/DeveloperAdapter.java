package com.example.developers.view.developers;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                holder.dev_title.setText(R.string.fresh);
                break;
            }

            case "Junior": {
                holder.dev_img.setImageResource(R.drawable.junior);
                holder.dev_title.setText(R.string.junior);
                break;
            }
            case "Mid": {
                holder.dev_img.setImageResource(R.drawable.mid);
                holder.dev_title.setText(R.string.mid);
                break;
            }
            case "Senior": {
                holder.dev_img.setImageResource(R.drawable.senior);
                holder.dev_title.setText(R.string.senior);
                break;
            }

        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("Delete Developer")
                        .setMessage("Are you want to delete this developer?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteDeveloper.deleteDeveloperById(developer.getId());
                                //to refresh list after delete developer
                                developerList.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), developerList.size());
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(R.drawable.baseline_add_alert_24)
                        .show();
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
