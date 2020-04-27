package in.tvac.akshayejh.firebasesearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class AdvancedAdapter extends RecyclerView.Adapter<AdvancedAdapter.AdvancedViewHolder> {
    private ArrayList<AdvancedItem> mAdvancedList;
    private Context context;
    public String mImage;


    public static class AdvancedViewHolder extends RecyclerView.ViewHolder {
        public ImageButton mImagebtn;
        public TextView mTextView1;
        public TextView mTextView2;
        public RelativeLayout madv_layout;

        public AdvancedViewHolder(View itemView) {
            super(itemView);
            mImagebtn = itemView.findViewById(R.id.imageButtonADV);
            mTextView1 = itemView.findViewById(R.id.textViewADV1);
//            mTextView2 = itemView.findViewById(R.id.textViewADV2);
            madv_layout = itemView.findViewById(R.id.adv_layout);
        }

    }

    public AdvancedAdapter(ArrayList<AdvancedItem> advancedList,Context context) {

        this.context = context;
        mAdvancedList = advancedList;
    }

    @Override
    public AdvancedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.advanced_item, parent, false);
        AdvancedViewHolder evh = new AdvancedViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final AdvancedViewHolder holder, final int position) {


        final AdvancedItem currentItem = mAdvancedList.get(position);
        holder.mTextView1.setText(currentItem.getText1());
//        holder.mTextView2.setText(currentItem.getText2());
        mImage = currentItem.getImageResource();
        Picasso.get().load(mImage).into(holder.mImagebtn);

        holder.madv_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OpenSelectedAdvanced.class);

                intent.putExtra("recipName", holder.mTextView1.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAdvancedList.size();
    }
}