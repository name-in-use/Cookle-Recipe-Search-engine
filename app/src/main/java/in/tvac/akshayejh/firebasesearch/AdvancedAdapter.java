package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;


        public AdvancedViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageViewADV);
            mTextView1 = itemView.findViewById(R.id.textViewADV1);
            mTextView2 = itemView.findViewById(R.id.textViewADV2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        listener.onItemClick(position, mTextView1.getText().toString());
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position, mTextView1.getText().toString());
                        }
                    }
                }
            });
        }

        public interface OnItemClickListener {
            void onItemClick(int position, String text);
        }
    }



    public AdvancedAdapter(ArrayList<AdvancedItem> advancedList) {
        mAdvancedList = advancedList;
    }

    @Override
    public AdvancedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advanced_item, parent, false);
        AdvancedViewHolder evh = new AdvancedViewHolder(v, (AdvancedViewHolder.OnItemClickListener) mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(AdvancedViewHolder holder, int position) {
        AdvancedItem currentItem = mAdvancedList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mAdvancedList.size();
    }
}
