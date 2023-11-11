package com.example.dichvugiacsay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.Model.Service;
import com.example.dichvugiacsay.R;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    ArrayList<Service> arr;
    Context context;
    public interface AddCart{void addcart(int idService);}
    AddCart addCart;

    public ServiceAdapter(ArrayList<Service> arr, Context context , AddCart addCart) {
        this.arr = arr;
        this.context = context;
        this.addCart = addCart;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_dich_vu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, int position) {
        Service service = arr.get(position);
        if (service == null) return;
        holder.name.setText(service.getName());
        holder.price.setText(service.getPrice());
        holder.description.setText(service.getDescription());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCart.addcart(service.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, description;
        ImageView add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ServiceName);
            price = itemView.findViewById(R.id.ServicePrice);
            description = itemView.findViewById(R.id.ServiceDescription);
            add = itemView.findViewById(R.id.ServiceAdd);
        }
    }
}
