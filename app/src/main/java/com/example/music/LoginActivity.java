package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPass;
    Button btnLogin;
    TextView signup_btn;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icError=getResources().getDrawable(R.drawable.ic_error);
                icError.setBounds(0,0,icError.getIntrinsicWidth(),icError.getIntrinsicHeight());
                String username=edtUsername.getText().toString().trim();
                String password=edtPass.getText().toString().trim();
                boolean isLoggedID=dbHelper.checkUser(edtUsername.getText().toString(),
                        edtPass.getText().toString());
                if(isLoggedID){
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username",username);
                    intent.putExtra("password",password);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }

                if (username.isEmpty()){
                    edtUsername.setCompoundDrawables(null,null,icError,null);
                    edtUsername.setError("Vui lòng nhập tên",icError);
                }
                if (password.isEmpty()){
                    edtPass.setCompoundDrawables(null,null,icError,null);
                    edtPass.setError("Vui lòng nhập mật khẩu",icError);
                }
//                if (!username.isEmpty()&&!password.isEmpty()){
//                    edtUsername.setCompoundDrawables(null,null,null,null);
//                    edtPass.setCompoundDrawables(null,null,null,null);
//                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("username",username);
//                    intent.putExtra("password",password);
//                    startActivity(intent);
//                }
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtUsername=findViewById(R.id.edtUsername);
        edtPass=findViewById(R.id.edtPass);
        btnLogin=findViewById(R.id.btnLogin);
        signup_btn=findViewById(R.id.signup_btn);
        dbHelper=new DBHelper(this);
    }
}