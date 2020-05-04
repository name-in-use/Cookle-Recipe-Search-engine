package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class CookleMain extends AppCompatActivity {
    private static final String TAG = "Cookle_main";

    private EditText mSearchField;
    private ImageButton mSearchBtn;
    private ImageButton mAdvSearchBtn;
    private static CookleMain INSTANCE;
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
    public static CookleMain getActivityInstance()
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

  //recipe search--Starts search
    public void searchBTN(View view)
    {
        Log.d(TAG, "searchBTN");

        searchRecipe = mSearchField.getText().toString();
        data = searchRecipe;
        if(!searchRecipe.equals("")) {
            Intent intentLoadNewActivity = new Intent(CookleMain.this, FirebaseResults.class);
            intentLoadNewActivity.putExtra("names", searchRecipe);
            startActivity(intentLoadNewActivity);
        }

    }

    public void advSearchBTN(View view)
    {
        Log.d(TAG, "ADVsearchBTN");
        Intent intentLoadNewActivity = new Intent(CookleMain.this, AdvancedSearch.class);
        startActivity(intentLoadNewActivity);

    }


    //Open profile activity
    public void openProfile(View view)
    {
        Intent intentLoadNewActivity = new Intent(CookleMain.this, Activityprofile.class);
        startActivity(intentLoadNewActivity);

    }
}
