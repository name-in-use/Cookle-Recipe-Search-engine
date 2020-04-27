package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Cookle_main extends AppCompatActivity {
    private static final String TAG = "Cookle_main";

    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private ImageButton mAdvSearchBtn;
    private static Cookle_main INSTANCE;
    private static  String data;
    private static String searchRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookle_main);
        Log.d(TAG, "onCreate");

        INSTANCE=this;
        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (ImageButton) findViewById(R.id.search_btn);
        mAdvSearchBtn= (ImageButton) findViewById(R.id.advsearch_btn);
    }
    public static Cookle_main getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        Log.d(TAG, "getData");

        return this.searchRecipe;

    }
    //i call this function in the recipe history
    public void nullHistory()
    {
        searchRecipe = "";
    }

  //recipe search--Starts Main Activity
    public void searchBTN(View view)
    {
        Log.d(TAG, "searchBTN");

        searchRecipe = mSearchField.getText().toString();
        data = searchRecipe;
        Intent intentLoadNewActivity = new Intent(Cookle_main.this, MainActivity.class);
        intentLoadNewActivity.putExtra("names",searchRecipe);
        startActivity(intentLoadNewActivity);

    }

    public void advSearchBTN(View view)
    {
        Log.d(TAG, "ADVsearchBTN");
        Intent intentLoadNewActivity = new Intent(Cookle_main.this, AdvancedSearch.class);
        startActivity(intentLoadNewActivity);

    }


    //Open profile activity
    public void openProfile(View view)
    {
        Intent intentLoadNewActivity = new Intent(Cookle_main.this, Activity_profile.class);
        startActivity(intentLoadNewActivity);

    }
}
