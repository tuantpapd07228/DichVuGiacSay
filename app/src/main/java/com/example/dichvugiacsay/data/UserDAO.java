package com.example.dichvugiacsay.data;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dichvugiacsay.Login;
import com.example.dichvugiacsay.MainActivity;
import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.itf.RememberUS;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private String checkLoginURL = IP.IP+"/giatsay/checklogin.php";
    private String regAccount = IP.IP + "/giatsay/regAccount.php";
    private String changPasswordURL = IP.IP + "/giatsay/changePassword.php";
    private Context context;


    public UserDAO(Context context) {
        this.context = context;
    }
    // check login
    public void checkLogin(String us, String pw, RememberUS rememberUS){
        if (!us.isEmpty() && !pw.isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, checkLoginURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        User user = new User(
                                jsonObject.getString("name"),
                                jsonObject.getString("address"),
                                jsonObject.getString("email"),
                                jsonObject.getString("phone"),
                                us);
                        rememberUS.remember();
                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("user", user);
                        context.startActivity(intent);
                    } catch (JSONException e) {
                        Toast.makeText(context, "checklogin err 52", Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,  error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", us);
                    map.put("password", pw);
                    return map;
                }
            };
            requestQueue.add(stringRequest);

        }

    }

    // reg account
    public void setRegAccount(String name, String email, String phone, String us, String pw){
        if (validateForm(name) && validateForm(email) && validateForm(phone) &&validateForm(us) && validateForm(pw)){
            Toast.makeText(context, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, regAccount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                context.startActivity(new Intent(context ,  Login.class));
                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Some thing wrong", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();
                map.put("username" ,us);
                map.put("password", pw);
                map.put("name" ,name);
                map.put("email", email);
                map.put("phone" ,phone);
                return map;
            }
        };

        requestQueue.add(stringRequest);



    }
    public void changePassword(String us, String oldpw, String newpw){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, changPasswordURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("ok")){
                    Toast.makeText(context, "Password đã được thay đổi", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Đã xảy ra lỗi hoặc bạn chưa nhâp đúng thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something wrong"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", us);
                map.put("oldpassword", oldpw);
                map.put("newpassword", newpw);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-HTTP-Method-Override", "PATCH");
                return headers;
            }
        };
        requestQueue.add(stringRequest);
    }

    private boolean validateForm(String str){
        return (str.isEmpty() || str.equals("")) ? true : false ;

    }
}
