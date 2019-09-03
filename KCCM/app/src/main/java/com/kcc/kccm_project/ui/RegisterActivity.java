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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail,mPassword,mName,mBirthday;
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mEmail = findViewById(R.id.signup_idtext);
        mPassword=findViewById(R.id.signup_passwordtext);
        mName=findViewById(R.id.signup_nametext);
        mBirthday=findViewById(R.id.signup_birthdaytext);
        findViewById(R.id.signup_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //mAuth 중에 이메일,비밀번호 가입 메소드
        mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                .addOnCompleteListener(this, (task) -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user!=null) {
                                UserInfo userInfo = setUserInfo(user);
                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("uid", userInfo.getUid());
                                userMap.put("email", userInfo.getEmail());
                                userMap.put("password", userInfo.getPassword());
                                userMap.put("name", userInfo.getName());
                                userMap.put("birthday", userInfo.getBirthday());

                                db.collection("UserInfo")
                                        .add(userMap)
                                        .addOnSuccessListener((aVoid) -> {
                                            Log.d("TAG", "Success to store in firestore!");
                                        })
                                        .addOnFailureListener((e) -> {
                                            Log.d("TAG", "Fail to store in firestore!");
                                        });
                                //회원가입 완료
                                Toast.makeText(RegisterActivity.this, "Success to sign up!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(RegisterActivity.this, "Sign up error",
                                    Toast.LENGTH_SHORT).show();
                        }

                    });
    }

    private UserInfo setUserInfo(FirebaseUser user) {
        String uid = user.getUid();
        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String birthday = mBirthday.getText().toString();
        String password = mPassword.getText().toString();

        UserInfo userInfo = new UserInfo(uid, email, password, name, birthday);
        return userInfo;
    }


}

