package com.example.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    TextView txtUsername;
    EditText edtPassreset, edtComfirmpass;
    Button btnConfirm;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        addControls();
        Intent intent=getIntent();
        txtUsername.setText(intent.getStringExtra("username"));
        addEvents();
    }

    private void addEvents() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txtUsername.getText().toString();
                String password = edtPassreset.getText().toString();
                String comfirmpass = edtComfirmpass.getText().toString();
                if (password.equals(comfirmpass)) {


                    Boolean check_pass_update = dbHelper.updatepassword(user,password);
                    if (check_pass_update == true) {
                        Intent intent1 = new Intent(ResetActivity.this, MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(ResetActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ResetActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControls() {
        txtUsername=findViewById(R.id.txtUsername);
        edtPassreset=findViewById(R.id.edtPassreset);
        edtComfirmpass=findViewById(R.id.edtComfirmPass);
        btnConfirm=findViewById(R.id.btnComfirm);
        dbHelper=new DBHelper(this);
    }
}