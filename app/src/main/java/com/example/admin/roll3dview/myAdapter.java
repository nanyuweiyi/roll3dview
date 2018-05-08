package com.example.admin.roll3dview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * ================================================
 * 描   述:
 * 作   者：tnn
 * 创建日期：2018/5/7
 * 版   本：1.0.0
 * ================================================
 */
public class myAdapter extends RecyclerView.Adapter{
    private List<String> mData;
    CallBack callBack;

    public void setData(List<String> data, CallBack callBack) {
        mData = data;
        this.callBack = callBack;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTextView.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.btnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0:mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_content);
        }
    }

    public interface CallBack {
        void btnClick(int position);
    }
}
