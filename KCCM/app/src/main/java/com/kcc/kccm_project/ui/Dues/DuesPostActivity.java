package com.kcc.kccm_project.ui.Dues;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.R;

import java.util.HashMap;
import java.util.Map;


//글쓰기
public class DuesPostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText mTitle,mContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_dues_post);

        mTitle = findViewById(R.id.dues_post_title);
        mContents = findViewById(R.id.dues_post_contents);
        findViewById(R.id.dues_post_editbutton).setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser()!=null)
        {
            String postId=mStore.collection("DuesPost").document().getId();
            // 제목이 겹쳐도 덮어쓰지 않게하기 위함
            //FirebaseUser user = mAuth.getCurrentUser();

            Map<String,Object> data = new HashMap<>(); //게시물 해쉬맵으로 저장
            data.put("DuesPostId",postId);
            data.put("DuesTitle",mTitle.getText().toString());
            data.put("DuesContents",mContents.getText().toString());
            data.put("Duestimestamp", FieldValue.serverTimestamp());
            mStore.collection("DuesPost").add(data).addOnSuccessListener((aVoid) -> {
            Log.d("TAG", "Success to store in firestore!");
        })
                .addOnFailureListener((e) -> {
                    Log.d("TAG", "Fail to store in firestore!");
                });
        }
        finish();

    }
    /*
    private DuesPost setUserInfo(FirebaseUser user) {
        String uid = user.getUid();
        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String birthday = mBirthday.getText().toString();
        String password = mPassword.getText().toString();

        UserInfo userInfo = new UserInfo(uid, email, password, name, birthday);
        return userInfo;
    }
    */

}
