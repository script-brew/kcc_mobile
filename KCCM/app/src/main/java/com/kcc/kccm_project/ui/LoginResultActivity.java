package com.kcc.kccm_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginResultActivity extends AppCompatActivity {

    Intent intent = getIntent();
    Bundle bundle=intent.getExtras(); //엑스트라들로 번들(묶음)으로 먼저 가져오고
    String email = bundle.getString("email"); //묶음 중에서 하나하나 씩 꺼낸다
    String password = bundle.getString("password");
    /*
    가져온값 넘겨받고
    url로 쏘기
    */
}
