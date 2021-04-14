package com.jeejio.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jeejio.recyclerviewdemo.java.Course;

import java.util.List;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/4/14 16:13
 * Description: 多布局适配器
 */
public class MultiCourseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int EMPTY_VIEW = 1;
    private final int CONTENT_VIEW = 2;
    private List<Course> mCourseList;

    public MultiCourseAdapter(List<Course> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_layout, parent, false);
            EmptyViewHolder viewHolder = new EmptyViewHolder(view);
            return viewHolder;
        } else if (viewType == CONTENT_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_layout, parent, false);
            ContentViewHolder viewHolder = new ContentViewHolder(view);
            return viewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).mTextView.setText("kkl");
        } else if (holder instanceof ContentViewHolder) {
            Course course = mCourseList.get(position);
            ((ContentViewHolder) holder).mTextView.setText(course.getName());
        }
    }

    @Override
    public int getItemCount() {
        if (mCourseList.size() == 0) {
            return 1;
        }
        return mCourseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mCourseList.size() == 0) {
            return EMPTY_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_course);
            mTextView = itemView.findViewById(R.id.tv_course);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_empty);
        }
    }
}
