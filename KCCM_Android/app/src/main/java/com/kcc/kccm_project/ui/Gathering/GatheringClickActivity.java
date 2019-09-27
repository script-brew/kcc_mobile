package com.kcc.kccm_project.ui.Gathering;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kcc.kccm_project.R;

import java.util.Map;

public class GatheringClickActivity extends AppCompatActivity
{
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();
    private String id;

    private TextView mTitleText,mContentsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //String postId=mStore.collection("MakefreindPost").document("MakefriendPostId").getId();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_makefriend_click);
        mTitleText = findViewById(R.id.makefriend_click_title);
        mContentsText=findViewById(R.id.makefriend_click_contents);
        Intent getIntent = getIntent();
        id =getIntent.getStringExtra(GatheringPost.post);
        //Log.e("ITEM DOCUMENT ID",id);


        mStore.collection("GatheringPost").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    if(task.getResult().exists())
                    {
                        if (task.getResult() != null)
                        {
                            Map<String, Object> snap = task.getResult().getData();
                            String title = String.valueOf(snap.get("GatheringTitle"));
                            String contents = String.valueOf(snap.get("GatheringContents"));
                            mTitleText.setText(title);
                            mContentsText.setText(contents);
                        }
                    }
                    else
                    {
                        Toast.makeText(GatheringClickActivity.this,"삭제된문서입니다",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
