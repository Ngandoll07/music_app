package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText edtUser,edtPass,edtXacNhanPass;
    Button btnSignUp;
    TextView login_btn;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user,pass,repas;
                user=edtUser.getText().toString();
                pass=edtPass.getText().toString();
                repas=edtXacNhanPass.getText().toString();
                if(user.equals("")|| pass.equals("")||repas.equals("")){
                    Toast.makeText(SignupActivity.this,"Vui lòng điền thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(pass.equals(repas)){
                        if (dbHelper.checkUsername(user)){
                            Toast.makeText(SignupActivity.this,"Đã tồn tại tên đăng nhập",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        boolean registerSuccess= dbHelper.insertData(user,pass);
                        if(registerSuccess){
                            Toast.makeText(SignupActivity.this,"Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignupActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignupActivity.this,"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtUser=findViewById(R.id.edtUsername);
        edtPass=findViewById(R.id.edtPass);
        edtXacNhanPass=findViewById(R.id.edtXacNhanPass);
        btnSignUp=findViewById(R.id.btnSignup);
        login_btn=findViewById(R.id.login_btn);
        dbHelper=new DBHelper(this);
    }
}