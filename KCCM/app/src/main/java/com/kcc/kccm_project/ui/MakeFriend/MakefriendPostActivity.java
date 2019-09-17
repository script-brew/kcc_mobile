package com.kcc.kccm_project.ui.MakeFriend;

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
import com.kcc.kccm_project.R;

import java.util.HashMap;
import java.util.Map;


//글쓰기
public class MakefriendPostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText mTitle,mContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_makefriend_post);

        mTitle = findViewById(R.id.makefriend_post_title);
        mContents = findViewById(R.id.makefriend_post_contents);
        findViewById(R.id.makefriend_post_editbutton).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(mAuth.getCurrentUser()!=null) {

            String postId = mStore.collection("MakefriendPost").document().getId();
            //String postId = mStore.collection(MakefriendPost.post).document().getId();
            // 제목이 겹쳐도 덮어쓰지 않게하기 위함
            //FirebaseUser user = mAuth.getCurrentUser();
            Map<String, Object> data = new HashMap<>(); //게시물 해쉬맵으로 저장
            data.put("MakefriendPostId", postId);
            data.put("MakefriendTitle", mTitle.getText().toString());
            data.put("MakefriendContents", mContents.getText().toString());
            data.put("Makefriendtimestamp", FieldValue.serverTimestamp());
            mStore.collection("MakefriendPost").document(postId).set(data, SetOptions.merge());


        /*.add(data).addOnSuccessListener((aVoid) -> {
            Log.d("TAG", "Success to store in firestore!");
        })
                .addOnFailureListener((e) -> {
                    Log.d("TAG", "Fail to store in firestore!");
                });
        }*/
            finish();
        }

    }

}
