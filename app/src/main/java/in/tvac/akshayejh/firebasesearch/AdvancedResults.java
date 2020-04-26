package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvancedResults extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdvancedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public TextView mtxtVName;
    public String tempName;
    public String tempExec;



    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> saved_recipes_ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_results);
        int i =0;

        mNames = AdvancedSearch.getSaved_name();
        saved_recipes_ingredients = AdvancedSearch.getSaved_recipes_ingredients();
        mImageUrls = AdvancedSearch.getSaved_imageURL();

        ArrayList<AdvancedItem> advancedList = new ArrayList<>();
        for (String object: mNames) {
            advancedList.add(new AdvancedItem(mImageUrls.get(i), mNames.get(i), saved_recipes_ingredients.get(i)));
            i++;
        }

        mRecyclerView = findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdvancedAdapter(advancedList , AdvancedResults.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}



