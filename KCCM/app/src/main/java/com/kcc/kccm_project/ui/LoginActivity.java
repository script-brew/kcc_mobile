package com.kcc.kccm_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kcc.kccm_project.R;
import com.kcc.kccm_project.controller.SignController;
import com.kcc.kccm_project.util.signUtill.NullValueException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText mEmail;
    private EditText mPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private SignController signController = new SignController();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.sign_idText);
        mPassword = findViewById(R.id.sign_passwordText);

        // 클릭할 수 있게 해줌
        findViewById(R.id.sign_signinbutton).setOnClickListener(this);
        findViewById(R.id.sign_registerbutton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch ( view.getId() )
        {
            // 로그인 클릭했을때, SignUp
            case R.id.sign_registerbutton:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.sign_signinbutton:
                try {
                    String response = signController.signIn(mEmail.getText().toString(), mPassword.getText().toString());
                    if(response.equals("OK")) {
                        mAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                                .addOnCompleteListener(this, (task -> {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null)
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    Toast.makeText(this, "Success to sign in!", Toast.LENGTH_SHORT).show();

                                }))
                                .addOnFailureListener(this, (task -> {
                                    Toast.makeText(this, "Fail to sign in!", Toast.LENGTH_SHORT).show();
                                }));
                    } else {

                    }

                } catch (NullValueException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;

        } // end switch

    } // end onClick
} // end class LoginActivity
