package com.kcc.kccm_project.util;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener
{

    private OnItemClickListener listener;

    // 2
    private GestureDetector gestureDetector;


    // 1
    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener)
    {
        this.listener=listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            // 한번 탭
            public boolean onSingleTapUp(MotionEvent e)
            {
                return true;
            }

            // 길게 눌렀을 때
            @Override
            public void onLongPress(MotionEvent e)
            {
                View v = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if ( v != null && listener != null )
                    listener.onItemLongClick(v,recyclerView.getChildAdapterPosition(v));
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent motionEvent)
    {
        View childView = view.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        if ( childView != null && listener != null && gestureDetector.onTouchEvent(motionEvent) )
        {
                listener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
    {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b)
    {

    }
}
