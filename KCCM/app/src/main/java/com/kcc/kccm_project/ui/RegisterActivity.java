package com.kcc.kccm_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.R;
import com.kcc.kccm_project.controller.SignController;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    private SignController signController = new SignController();
    private EditText mEmail,mPassword,mName,mBirthday;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEmail = findViewById(R.id.signup_idtext);
        mPassword=findViewById(R.id.signup_passwordtext);
        mName=findViewById(R.id.signup_nametext);
        mBirthday=findViewById(R.id.signup_birthdaytext);
        findViewById(R.id.signup_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        UserInfo userInfo = setUserInfo();
        String response = signController.signUp(userInfo);
        if(response.equals("OK")) {
            mAuth.createUserWithEmailAndPassword(userInfo.getEmail(), userInfo.getPassword())
                    .addOnCompleteListener(this, (task) -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Success to sign up!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Sign up error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(RegisterActivity.this, "Sign up error",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private UserInfo setUserInfo()
    {

        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String birthday = mBirthday.getText().toString();
        String password = mPassword.getText().toString();

        UserInfo userInfo = new UserInfo(email, password, name, birthday);
        
        return userInfo;
    }

}
