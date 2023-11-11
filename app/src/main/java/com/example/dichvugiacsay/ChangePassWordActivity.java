package com.example.dichvugiacsay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dichvugiacsay.Model.User;
import com.example.dichvugiacsay.data.UserDAO;

public class ChangePassWordActivity extends AppCompatActivity {

    UserDAO userDAO;
    EditText oldpass, newpass, cfpass;
    Button changepw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);

        userDAO = new UserDAO(ChangePassWordActivity.this);
        oldpass = findViewById(R.id.changpwOldPw);
        newpass = findViewById(R.id.changpwNewPw);
        cfpass = findViewById(R.id.changpwcCfPw);
        changepw = findViewById(R.id.btnChangePW);



        Intent intent = getIntent();

        User user = (User) intent.getSerializableExtra("user");

       changepw.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String oldpw = oldpass.getText().toString().trim();
               String newpw = newpass.getText().toString().trim();
               String cfpw = cfpass.getText().toString().trim();
               if (cfpw.equalsIgnoreCase(newpw)){
                   if (validateForm(oldpw) && validateForm(newpw) && validateForm(cfpw)){
                       if (newpw.equalsIgnoreCase(cfpw)){
                           changePW(user.getUsername(), oldpw, newpw);
                       }
                   }else{
                       Toast.makeText(getApplicationContext(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                   }
               }else {
                   Toast.makeText(ChangePassWordActivity.this, "Mật khẩu mới phải nhập giống nhau", Toast.LENGTH_SHORT).show();
               }

           }
       });

    }
    private void changePW(String username, String oldpw, String newpw){
        userDAO.changePassword(username , oldpw, newpw);
    }
    private boolean validateForm(String str){
        return (str.isEmpty() || str.equals("")) ? false :true;
    }
}