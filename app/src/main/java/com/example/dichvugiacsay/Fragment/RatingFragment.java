package com.example.dichvugiacsay.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dichvugiacsay.Adapter.OrderDetailRatingAdapter;
import com.example.dichvugiacsay.Model.OrderDetail;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.OrderActivity;
import com.example.dichvugiacsay.R;
import com.example.dichvugiacsay.data.OrderDetailDAO;

import java.util.ArrayList;


public class RatingFragment extends Fragment {

    OrderDetailRatingAdapter orderDetailRatingAdapter;
    ArrayList<OrderDetail> orderDetails;
    RecyclerView recyclerView;
    OrderDetailDAO orderDetailDAO;

    OrderActivity activity ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_rating, container, false);
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        activity = (OrderActivity) getActivity();
        User user = activity.getUser();
        orderDetailDAO = new OrderDetailDAO(getContext());
        recyclerView = v.findViewById(R.id.rating_rcv);
        recyclerView.setLayoutManager(l);
        setOrderDetailAdapter(user.getUsername());
        return v;
    }
    private void setOrderDetailAdapter(String username){
        orderDetails = new ArrayList<>();
        orderDetailDAO.getAll( username, new OrderDetailDAO.OrderDetailitf() {
            @Override
            public void xuli(Object obj) {
                orderDetails = (ArrayList<OrderDetail>) obj;
                orderDetailRatingAdapter = new OrderDetailRatingAdapter(orderDetails, getContext());
                Toast.makeText(getContext(), "arr "+ orderDetails.size(), Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(orderDetailRatingAdapter);
            }
        });

    }
}