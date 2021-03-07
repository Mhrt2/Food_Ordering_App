package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.Models.User;

public class RegisterActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText mTextUsername;
    EditText mTextEmail;
    EditText mPassword;
    EditText conPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);
        mTextUsername = (EditText) findViewById(R.id.username);
        mTextEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        conPassword = (EditText) findViewById(R.id.con_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textView_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

    }


    public void onSignUpClick(View v) {
        if (v.getId() == R.id.button_register) {


            String namestr = mTextUsername.getText().toString();
            String emailstr = mTextEmail.getText().toString();
            String pass1str = mPassword.getText().toString();
            String pass2str = conPassword.getText().toString();

            if (!pass1str.equals(pass2str)) {
                //popup msg:
                Toast tpass = Toast.makeText(this, "passwords don't match", Toast.LENGTH_LONG);
                tpass.show();
            } else {
                //insert the details in DB:
                User c = new User();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setPassword(pass1str);
                dbHelper.insertUser(c);
                Toast tpass = Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG);
                tpass.show();
            }

        }
    }

}