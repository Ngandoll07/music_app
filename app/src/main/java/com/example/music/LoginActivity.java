package com.example.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.music.fragment_menu.AccountFragment;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPass;
    Button btnLogin,btnCancel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }



    public void getData(String username){

    }
    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icError=getResources().getDrawable(R.drawable.ic_error);
                icError.setBounds(0,0,icError.getIntrinsicWidth(),icError.getIntrinsicHeight());
                String username=edtUsername.getText().toString().trim();
                String password=edtPass.getText().toString().trim();
                if (username.isEmpty()){
                    edtUsername.setCompoundDrawables(null,null,icError,null);
                    edtUsername.setError("Vui lòng nhập tên",icError);
                }
                if (password.isEmpty()){
                    edtPass.setCompoundDrawables(null,null,icError,null);
                    edtPass.setError("Vui lòng nhập mật khẩu",icError);
                }
                if (!username.isEmpty()&&!password.isEmpty()){
                    edtUsername.setCompoundDrawables(null,null,null,null);
                    edtPass.setCompoundDrawables(null,null,null,null);
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void addControls() {
        edtUsername=findViewById(R.id.edtUsername);
        edtPass=findViewById(R.id.edtPass);
        btnLogin=findViewById(R.id.btnLogin);
        btnCancel=findViewById(R.id.btnCancel);
    }
}