package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class AdvancedResults extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    //    private RecyclerView.Adapter mAdapter;
    private AdvancedAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> saved_recipes_with_ingredients = new ArrayList<>();
    /*
    public static ArrayList<String> RecipeNames = new ArrayList<>();
    public static ArrayList<String> RecipeExecutions = new ArrayList<>();
    public static ArrayList<Bitmap> RecipeImages = new ArrayList<Bitmap>();
    private RecyclerView mResultList;


     */

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
            advancedList.add(new AdvancedItem(R.drawable.ic_android, mNames.get(i), saved_recipes_with_ingredients.get(i)));
            i++;

        }
/*
        AdvancedAdapter.OnItemClickListener i1 = new AdvancedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String textData) {

            }
        }

 */

        mRecyclerView = findViewById(R.id.recyclerView1);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdvancedAdapter(advancedList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdvancedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent i = new Intent();
                //             i.putExtra("TEXT_KEY", String textData);
                startActivity(i);
            }
        });

   /*
        mAdapter.setOnItemClickListener(new AdvancedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String textData) {
                Intent i = new Intent();
                i.putExtra("TEXT_KEY", textData);
                startActivity(i);
            }
        });

    */

    }


/*
    private void initImageBitmaps() {

        mNames = AdvancedSearch.getSaved_name();
        saved_recipes_with_ingredients = AdvancedSearch.getSaved_recipes_with_ingredients();
        mImageUrls = AdvancedSearch.getSaved_imageURL();


        System.out.println(mNames.toString());
        System.out.println(mImageUrls.toString());
    }
 */
}



