package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AdvancedAdapter extends RecyclerView.Adapter<AdvancedAdapter.AdvancedViewHolder> {
    private ArrayList<AdvancedItem> mAdvancedList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class AdvancedViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mImagebtn;
        public TextView mTextView1;
        public TextView mTextView2;


        public AdvancedViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textViewADV1);
            mTextView2 = itemView.findViewById(R.id.textViewADV2);
        }
    }

    public AdvancedAdapter(ArrayList<AdvancedItem> advancedList) {
        mAdvancedList = advancedList;
    }

    @Override
    public AdvancedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advanced_item, parent, false);
        AdvancedViewHolder evh = new AdvancedViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdvancedViewHolder holder, int position) {
        AdvancedItem currentItem = mAdvancedList.get(position);

 //       holder.mImageView.setImageResource(currentItem.getImageResource());
//        holder.mImagebtn.setImageResource(R.drawable.searchbtn);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mAdvancedList.size();
    }
}


/*
    public AdvancedAdapter(ArrayList<AdvancedItem> advancedList) {
        mAdvancedList = advancedList;
    }

    @Override
    public AdvancedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        AdvancedViewHolder evh = new AdvancedViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdvancedViewHolder holder, int position) {
        AdvancedItem currentItem = mAdvancedList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mAdvancedList.size();
    }
}

 */