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
import com.kcc.kccm_project.util.signUtill.InvalidValueException;
import com.kcc.kccm_project.util.signUtill.NullValueException;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    private SignController signController = new SignController();
    private EditText mEmail,mPassword,mName,mBirthday, mSchoolNumber, mDepartment;
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
        mSchoolNumber = findViewById(R.id.signup_schoolnumbertext);
        mDepartment = findViewById(R.id.signup_departmenttext);
        findViewById(R.id.signup_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        UserInfo userInfo = setUserInfo();
        try {
            String response = signController.signUp(userInfo); // 회원가입 유효성 검사

            if (response.equals("OK")) {
                mAuth.createUserWithEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()) // 로그인 하는 기능
                        .addOnCompleteListener(this, (task) -> {
                            if (task.isSuccessful()) { // 회원가입 성공시
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

        } catch(NullValueException | InvalidValueException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {

        }

    }

    //사용자가 입력한 유저의 정보 userInfo 객체로 만드는 메소드
    private UserInfo setUserInfo()
    {

        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String birthday = mBirthday.getText().toString();
        String password = mPassword.getText().toString();
        String department = mDepartment.getText().toString();
        String schoolNumber = mSchoolNumber.getText().toString();

        UserInfo userInfo = new UserInfo(email,schoolNumber, password, name, department, birthday);
        
        return userInfo;
    }

}
