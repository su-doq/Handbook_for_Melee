package com.thatkawaiiguy.meleehandbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thatkawaiiguy.meleehandbook.R;
import com.thatkawaiiguy.meleehandbook.other.ArrayHelper;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {

    private String[] mDataSet;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView menuText;
        private TextView subText;

        public ViewHolder(View v) {
            super(v);
            menuText = (TextView) v.findViewById(R.id.termListTitle);
            subText = (TextView) v.findViewById(R.id.termListSubtitle);
        }

        public TextView getTextView() {
            return menuText;
        }

        public TextView getTermTextView() {
            return subText;
        }
    }

    public TermAdapter(String[] mDataSet, Context context) {
        this.mDataSet = mDataSet;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.term_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(mDataSet[position]);

        viewHolder.getTermTextView().setText(ArrayHelper.getTermInfoArray(mContext)[position]);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}