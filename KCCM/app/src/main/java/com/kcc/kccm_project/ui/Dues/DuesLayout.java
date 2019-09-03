package com.kcc.kccm_project.ui.Dues;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kcc.kccm_project.Entity.FirebaseID;
import com.kcc.kccm_project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//회식관리 레이아웃 처리하는부분
public class DuesLayout extends Fragment implements View.OnClickListener {
    View v;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mPostRecyclerView;
    private DuesPostAdapter mAdapter;
    private List<DuesPost> mDatas;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.board_dues,container,false);
        mPostRecyclerView = v.findViewById(R.id.dues_recyclerview);
        v.findViewById(R.id.dues_edit_button).setOnClickListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        //orderBy로 타임스탬프에따라서 오름차순,내림차순할지정함
        mStore.collection("DuesPost").orderBy("Duestimestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    mDatas.clear();//데이터 중복을 막기위해 클리어하고 for문 시자
                    for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> shot = snap.getData(); // 게시판에 써진 글을 map에저장
                        String documentId = String.valueOf(shot.get("DuesPost"));
                        String title = String.valueOf(shot.get("DuesTitle"));
                        String contents = String.valueOf(shot.get("DuesContents"));
                        DuesPost data = new DuesPost(documentId, title, contents); //post 생성자를 이용해서 게시문을 넣어줌
                        mDatas.add(data);//Data를 넣어줌
                    }
                    mAdapter = new DuesPostAdapter(mDatas);
                    mPostRecyclerView.setAdapter(mAdapter);
                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), DuesPostActivity.class);
        startActivity(intent);
    }
}
