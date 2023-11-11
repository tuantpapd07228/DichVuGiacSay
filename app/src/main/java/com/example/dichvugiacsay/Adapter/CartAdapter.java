package com.example.dichvugiacsay.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dichvugiacsay.Model.Cart;
import com.example.dichvugiacsay.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<Cart> arr;
    public interface CartITF {void xuli(Object obj);}
    CartITF cartITF;

    public CartAdapter(ArrayList<Cart> arr, CartITF cartITF) {
        this.arr = arr;
        this.cartITF = cartITF;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Cart cart = arr.get(position);
        if (cart ==  null) return;
        holder.name.setText(cart.getNameService());
        holder.price.setText((cart.getQuantity() * cart.getPriceService())+"");
        if (holder.choice.isChecked()){
            cartITF.xuli(cart);
        }
        holder.quantity.setText(cart.getQuantity()+"");
        holder.description.setText(cart.getDescription());
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, description, price, sub, quantity, plus;
        private CheckBox choice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemCard_name);
            description = itemView.findViewById(R.id.itemCard_Description);
            price = itemView.findViewById(R.id.itemCard_price);
            sub = itemView.findViewById(R.id.itemCard_sub);
            quantity = itemView.findViewById(R.id.itemCard_quantity);
            plus = itemView.findViewById(R.id.itemCard_plus);
            choice = itemView.findViewById(R.id.itemCard_choice);
        }
    }
}
