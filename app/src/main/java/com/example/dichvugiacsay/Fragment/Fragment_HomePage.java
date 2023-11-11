package com.example.dichvugiacsay.Fragment;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.dichvugiacsay.Adapter.Slider_Image_Adapter;
import com.example.dichvugiacsay.Adapter.StoreAdapter;
import com.example.dichvugiacsay.Adapter.TipsAdapter;
import com.example.dichvugiacsay.Adapter.User_CardViewAdapter;
import com.example.dichvugiacsay.Model.Slider_Image;
import com.example.dichvugiacsay.Model.Store;
import com.example.dichvugiacsay.Model.Tips;
import com.example.dichvugiacsay.Model.User_CardView;
import com.example.dichvugiacsay.R;
import com.example.dichvugiacsay.StoreActivity;
import com.example.dichvugiacsay.data.StoreDAO;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class Fragment_HomePage extends Fragment  {
    View view;
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Slider_Image> list;

    private RecyclerView recyclerView, recyclerView1, recyclerView2;

    private User_CardViewAdapter userCartViewAdapter;

    private TipsAdapter tipsAdapter;

    private StoreAdapter storeAdapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private StoreDAO storeDAO;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager2.getCurrentItem();
            if(currentPosition == list.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__home_page, container, false);
        storeDAO = new StoreDAO(getContext());
        viewPager2 = view.findViewById(R.id.view_pager_2);
        circleIndicator3 = view.findViewById(R.id.circle_Indicator3);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        recyclerView2 = view.findViewById(R.id.recyclerView2);

        // set các thuộc tính viewPager2
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        // Thực hiện chuyển động cho hình ảnh
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        // hiển thị hình ở giữa lớn hơn những hình còn lại
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        list = getListImage();
        Slider_Image_Adapter slider_image_adapter = new Slider_Image_Adapter(list);
        viewPager2.setAdapter(slider_image_adapter);
        // liên kết viewPager2 với circleIndicator
        circleIndicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        userCartViewAdapter = new User_CardViewAdapter(getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        userCartViewAdapter.setData(getListUserCartView());
        recyclerView.setAdapter(userCartViewAdapter);


        tipsAdapter = new TipsAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setFocusable(false);
        recyclerView1.setNestedScrollingEnabled(false);

        tipsAdapter.setData(getListTipsCardView());
        recyclerView1.setAdapter(tipsAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager1);
        storeAdapter = new StoreAdapter(getContext());

        storeDAO.getAll(new StoreDAO.XuLiStore() {
            @Override
            public void xuli(Object obj) {
                ArrayList<Store > arr = (ArrayList<Store>) obj;
                Log.e("atuan", arr.get(0).toString());

                storeAdapter.setData(arr);
                recyclerView2.setAdapter(storeAdapter);
            }
        });

        storeAdapter.setListener(new StoreAdapter.StoreListener() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getContext() , StoreActivity.class);
                intent.putExtra("id", pos+1);
                startActivity(intent);
            }
        });
        return view;
    }


    private List<User_CardView> getListUserCartView(){
        List<User_CardView> list = new ArrayList<>();
        list.add(new User_CardView(R.drawable.icon_dichvu1, "Giặt ủi"));
        list.add(new User_CardView(R.drawable.icon_dichvu, "Giặt hấp"));
        list.add(new User_CardView(R.drawable.icon_dichvu2, "Giặt sấy"));
        list.add(new User_CardView(R.drawable.icon_dichvu3, "Giặt giày"));
        list.add(new User_CardView(R.drawable.icon_dichvu4, "Giặt vest"));
        list.add(new User_CardView(R.drawable.icon_dichvu5, "Khác"));

        return list;
    }


    private List<Tips> getListTipsCardView(){
        List<Tips> tipsList = new ArrayList<>();
        tipsList.add(new Tips(R.drawable.meovat1, "Mẹo vặt quần áo không hôi vào mùa mưa."));
        tipsList.add(new Tips(R.drawable.meovat3, "Giặt quần jeans không làm mất màu, form và chất lượng vải?"));
        tipsList.add(new Tips(R.drawable.meovat2, "Làm thế nào để quần áo khô nhanh trong mùa mưa ẩm?"));

        return tipsList;
    }



    private List<Slider_Image> getListImage() {
        List<Slider_Image> list = new ArrayList<>();
        list.add(new Slider_Image(R.drawable.slider_image2));
        list.add(new Slider_Image(R.drawable.slider_image1));
        list.add(new Slider_Image(R.drawable.slider_image));

        return list;
    }



}