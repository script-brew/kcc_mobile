package com.kcc.kccm_project.ui.MakeFriend;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.kcc.kccm_project.R;
import com.kcc.kccm_project.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//회식관리 레이아웃 처리하는부분
public class MakefriendLayout extends Fragment implements View.OnClickListener, RecyclerItemClickListener.OnItemClickListener {
    View v;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private RecyclerView mPostRecyclerView;
    private MakefriendPostAdapter mAdapter;
    private List<MakefriendPost> mDatas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.board_makefriend,container,false);
        mPostRecyclerView = v.findViewById(R.id.makefriend_recyclerview);
        mPostRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),mPostRecyclerView,this));
        v.findViewById(R.id.makefriend_edit_button).setOnClickListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        //orderBy로 타임스탬프에따라서 오름차순,내림차순할지정함
        mStore.collection("MakefriendPost").orderBy("Makefriendtimestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    mDatas.clear();//데이터 중복을 막기위해 클리어하고 for문 시자
                    for (DocumentSnapshot snap : queryDocumentSnapshots.getDocuments()) {
                        Map<String, Object> shot = snap.getData(); // 게시판에 써진 글을 map에저장
                        String documentId = String.valueOf(shot.get("MakefriendPostId"));
                        String title = String.valueOf(shot.get("MakefriendTitle"));
                        String contents = String.valueOf(shot.get("MakefriendContents"));
                        MakefriendPost data = new MakefriendPost(documentId, title, contents); //post 생성자를 이용해서 게시문을 넣어줌
                        mDatas.add(data);//Data를 넣어줌
                    }
                    mAdapter = new MakefriendPostAdapter(mDatas);
                    mPostRecyclerView.setAdapter(mAdapter);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), MakefriendPostActivity.class);
        startActivity(intent);
    }

    //아이템 클릭할때 intent로 값넘김김
    @Override
    public void onItemClick(View view, int position) {
        //String documentId = mStore.collection("MakefriendPost").document().getId();
        Intent intent = new Intent(getActivity(),MakefriendClickActivity.class);
        intent.putExtra(MakefriendPost.post,mDatas.get(position).getDocumentId());
        startActivity(intent);
    }


    //아이템삭제할때
    @Override
    public void onItemLongClick(View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setMessage("삭제 하시겠습니까?");
        dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                mStore.collection("MakefriendPost").document(mDatas.get(position).getDocumentId()).delete();
                Toast.makeText(getActivity(),"삭제 되었습니다.",Toast.LENGTH_SHORT);
            }
        }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Toast.makeText(getActivity(),"취소 되었습니다.",Toast.LENGTH_SHORT);
            }
        });
        dialog.setTitle("삭제 알림");
        dialog.show() ;
    }


}
