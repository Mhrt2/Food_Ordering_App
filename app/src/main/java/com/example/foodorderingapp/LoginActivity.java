package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new DBHelper(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.do_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);

            }
        });
    }

    public void onSigninClick(View v) {
        if (v.getId() == R.id.button_login) {

            String str = mTextUsername.getText().toString();

            String pass = mTextPassword.getText().toString();

            String password = dbHelper.searchPass(str);
            if (pass.equals(password)) {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("name", str);
                startActivity(i);
            } else {//popup msg:
                Toast temp = Toast.makeText(LoginActivity.this, "username & passwords don't match", Toast.LENGTH_LONG);
                temp.show();
            }

        }
    }
}
