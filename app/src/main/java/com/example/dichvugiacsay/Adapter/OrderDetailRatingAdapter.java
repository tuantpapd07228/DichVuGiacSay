package com.example.dichvugiacsay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.Model.OrderDetail;
import com.example.dichvugiacsay.R;

import java.util.ArrayList;

public class OrderDetailRatingAdapter extends RecyclerView.Adapter<OrderDetailRatingAdapter.ViewModel> {
    private ArrayList<OrderDetail> arr;
    private Context context;

    public OrderDetailRatingAdapter(ArrayList<OrderDetail> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderDetailRatingAdapter.ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_detail, parent, false);
        return new ViewModel(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailRatingAdapter.ViewModel holder, int position) {
        OrderDetail orderDetail = arr.get(position);
        if (orderDetail == null) return;
        holder.id.setText(orderDetail.getIdOrder()+"");
        holder.name.setText(orderDetail.getName());
        holder.price.setText(orderDetail.getPrice()+"");
        holder.quantity.setText(orderDetail.getQuantity()+"");
        holder.status.setText("Đã hoàn thành");
        holder.orderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, SeviceActivity.class);
//                intent.putExtra("service", orderDetail.getIdService());
//                context.startActivity(intent);
            }
        });
        holder.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, RatingService.class);
//                intent.putExtra("service", orderDetail.getIdService());
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        TextView id, name, price, quantity, status;
        Button orderAgain, rating;
        public ViewModel(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.orderdetail_id);
            name = itemView.findViewById(R.id.orderdetail_nameservice);
            price = itemView.findViewById(R.id.orderdetail_price);
            quantity = itemView.findViewById(R.id.orderdetail_quantity);
            orderAgain = itemView.findViewById(R.id.orderdetail_orderagin);
            rating = itemView.findViewById(R.id.orderdetail_rating);
            status = itemView.findViewById(R.id.orderdetail_status);
        }
    }
}
