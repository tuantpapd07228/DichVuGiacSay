package com.example.dichvugiacsay.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dichvugiacsay.ChangePassWordActivity;
import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.OrderActivity;
import com.example.dichvugiacsay.R;
public class Fragment_Account extends Fragment {
    LinearLayout changePW, logout, deleteAcc, info, order;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__account, container, false);
        changePW = view.findViewById(R.id.accChangPW);
        logout = view.findViewById(R.id.accLogout);
        deleteAcc = view.findViewById(R.id.accDelete);
        info = view.findViewById(R.id.accInfo);
        order = view.findViewById(R.id.accOrder);
        activity = (MainActivity) getActivity();
        User user = activity.getUser();
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePassWordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), OrderActivity.class);
                i.putExtra("user", user);
                startActivity(i);
            }
        });





        return view;
    }
}