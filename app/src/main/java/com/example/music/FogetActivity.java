package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.music.R;

public class FogetActivity extends AppCompatActivity {
    EditText edtusername;
    Button btnReset;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtusername.getText().toString();
                Boolean checkuser= dbHelper.checkUsername(user);
                if(checkuser==true)
                {
                    Intent intent=new Intent(FogetActivity.this,ResetActivity.class);
                    intent.putExtra("username",user);
                    startActivity(intent);
                }else {
                    Toast.makeText(FogetActivity.this,"Tên người dùng không tồn tại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControls() {
        edtusername=findViewById(R.id.edtUsername);
        btnReset=findViewById(R.id.btnReset);
        dbHelper=new DBHelper(this);
    }
}