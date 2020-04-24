package in.tvac.akshayejh.firebasesearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class AdvancedAdapter extends RecyclerView.Adapter<AdvancedAdapter.AdvancedViewHolder> {
    private ArrayList<AdvancedItem> mAdvancedList;public String mImage;

    public static class AdvancedViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mImagebtn;
        public TextView mTextView1;
        public TextView mTextView2;

        public AdvancedViewHolder(View itemView) {
            super(itemView);
            mImagebtn = itemView.findViewById(R.id.imageButtonADV);
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


        mImage = currentItem.getImageResource();
//        mImage = "https://firebasestorage.googleapis.com/v0/b/fir-search-86b44.appspot.com/o/eggs.jpg?alt=media&token=e0305bd4-776e-4bd8-af7d-e0c216624dac";
        Picasso.get().load(mImage).into(holder.mImagebtn);
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