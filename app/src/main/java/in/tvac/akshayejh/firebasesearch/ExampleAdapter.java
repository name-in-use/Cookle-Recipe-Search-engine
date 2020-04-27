package in.tvac.akshayejh.firebasesearch;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private static final String TAG = "ExampleAdapter";

    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {

        mExampleList = new ArrayList<ExampleItem>();

        for (int i = 0; i < exampleList.size(); i++) {
            ExampleItem item = exampleList.get(i);
            if (item.getLine1() != null && !item.getLine1().isEmpty()) {
                mExampleList.add(item);
            }
        }
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "ExampleViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        ExampleItem currentItem = mExampleList.get(position);
        holder.mTextViewLine1.setText(currentItem.getLine1());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}