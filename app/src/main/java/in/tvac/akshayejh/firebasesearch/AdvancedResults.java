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
    private ArrayList<String> saved_recipes_with_ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_results);
        int i =0;

        mNames = AdvancedSearch.getSaved_name();
        saved_recipes_with_ingredients = AdvancedSearch.getSaved_recipes_with_ingredients();
        mImageUrls = AdvancedSearch.getSaved_imageURL();

        ArrayList<AdvancedItem> advancedList = new ArrayList<>();
        for (String object: mNames) {
            advancedList.add(new AdvancedItem(mImageUrls.get(i), mNames.get(i), saved_recipes_with_ingredients.get(i)));
            i++;
        }

        mRecyclerView = findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdvancedAdapter(advancedList , AdvancedResults.this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void openActivity_adv_result(View view)
    {
        tempName ="";
        tempExec = "";
        mtxtVName = (TextView) findViewById(R.id.textViewADV1);
        Log.d("test", mtxtVName.getText().toString());
        tempName = mtxtVName.getText().toString();
        int temp = mNames.indexOf(tempName);
        tempExec = saved_recipes_with_ingredients.get(temp);

        Intent intentLoadNewActivity = new Intent(AdvancedResults.this, OpenSelectedAdvanced.class);
        intentLoadNewActivity.putExtra("recipName",tempName);
//        intentLoadNewActivity.putExtra("recipExec",tempExec);
        startActivity(intentLoadNewActivity);
    }
}



