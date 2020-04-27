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

public class SaveRecipes extends AppCompatActivity {
    private static final String TAG = "SaveRecipes";

    ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ResultsView RV = new ResultsView();
    Cookle_main CM = new Cookle_main();
    static String getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sr);
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
        Type type = new TypeToken<ArrayList<ExampleItem>>() {}.getType();
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
        mAdapter = new ExampleAdapter(mExampleList);

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
        mExampleList.add(new ExampleItem(line1));
        mAdapter.notifyItemInserted(mExampleList.size());
    }
}