package com.kcc.kccm_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kcc.kccm_project.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText mEmail;
    private EditText mPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


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
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if ( task.isSuccessful() )
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if ( user != null ) {
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

        } // end switch

    }
}
