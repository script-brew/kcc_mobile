package com.kcc.kccm_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kcc.kccm_project.R;
import com.kcc.kccm_project.ui.Dues.DuesActivity;
import com.kcc.kccm_project.ui.Gathering.GatheringActivity;
import com.kcc.kccm_project.ui.Study.StudyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //온클릭 리스너 생성
        findViewById(R.id.main_study_button).setOnClickListener(this);
        findViewById(R.id.main_dues_button).setOnClickListener(this);
        findViewById(R.id.main_gathering_button).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //case 나눠서 클릭
        switch (view.getId()) {
            case R.id.main_study_button:
                startActivity(new Intent(this, StudyActivity.class));
                break;
            case R.id.main_gathering_button:
                startActivity(new Intent(this, GatheringActivity.class));
                break;
            case R.id.main_dues_button:
                startActivity(new Intent(this, DuesActivity.class));
                break;
        }

    }
}
