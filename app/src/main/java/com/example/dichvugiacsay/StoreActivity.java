package com.example.dichvugiacsay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.dichvugiacsay.Adapter.ServiceAdapter;
import com.example.dichvugiacsay.Model.Service;
import com.example.dichvugiacsay.Model.Store;
import com.example.dichvugiacsay.data.StoreDAO;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private int idStore;
    private StoreDAO storeDAO;
    private Store store;
    private TextView name, address;
    private RecyclerView rcvService;
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Intent intent = getIntent();
        idStore = intent.getIntExtra("id", 0);
        storeDAO = new StoreDAO(this);
        name = findViewById(R.id.storeName);
        address = findViewById(R.id.storeAddress);
        rcvService = findViewById(R.id.storeService_RCV);
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(RecyclerView.VERTICAL);
        rcvService.setLayoutManager(l);

        setData();
    }

    private void setData(){
        storeDAO.getStore(idStore, new StoreDAO.XuLiStore() {
            @Override
            public void xuli(Object store1) {
                Log.e("atuan stor", store1.toString());
                store = (Store) store1;
                name.setText(store.getName());
                address.setText(store.getAddress());
            }
        });
        storeDAO.getAllService(new StoreDAO.XuLiStore() {
            @Override
            public void xuli(Object obj) {
                serviceAdapter = new ServiceAdapter((ArrayList<Service>) obj, StoreActivity.this, new ServiceAdapter.AddCart() {
                    @Override
                    public void addcart(int idService) {

                    }
                });
                rcvService.setAdapter(serviceAdapter);
            }
        });
    }
}