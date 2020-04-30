package in.tvac.akshayejh.firebasesearch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeHistory extends AppCompatActivity {
    private static final String TAG = "SaveRecipes";

    ArrayList<RecipeItem> mExampleList;
    private RecyclerView mRecyclerView;
    private RecipeHistoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SearchResults RV = new SearchResults();
    CookleMain CM = new CookleMain();
    static String getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_history);
        Log.d(TAG, "onCreate");

        //get searched string
        getName = CM.getData();

        loadData();
        buildRecyclerView();
        if(getName!="")
         {
           setInsertButton();
         }
        saveData();
    }

    private void saveData() {
        Log.d(TAG, "saveData");
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        Log.d(TAG, "loadData");
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<RecipeItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        Log.d(TAG, "buildRecyclerView");
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecipeHistoryAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Log.d(TAG, "setInsertButton");
        insertItem(getName);
        CM.nullHistory();
    }


    private void insertItem(String line1) {
        Log.d(TAG, "insertItem");
        mExampleList.add(new RecipeItem(line1));
        mAdapter.notifyItemInserted(mExampleList.size());
    }
}