package com.kcc.kccm_project.ui.MakeFriend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kcc.kccm_project.R;

import java.util.List;


// 리사이클러 뷰를 처리하는 시스템 (게시판)
public class MakefriendPostAdapter extends RecyclerView.Adapter<MakefriendPostAdapter.PostViewHolder> {

    private List<MakefriendPost> datas; //게시물 데이터

    public MakefriendPostAdapter(List<MakefriendPost> datas) {
        this.datas = datas;
    } //생성자
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.board_makefriend_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        MakefriendPost data = datas.get(position); //리스트에 넣어주는 작업 ,포지션 0,1,2,3,4
        holder.title.setText(data.getTitle());
        holder.contents.setText(data.getContents());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    } //아이템의 개수

    //아이템을 화면에 나타냄
    class PostViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView contents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.makefriend_item_title);
            contents=itemView.findViewById(R.id.makefriend_item_contents) ;
        }
    }
}
