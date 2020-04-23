package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Cookle_main extends AppCompatActivity {
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
        return this.searchRecipe;

    }
    //i call this function in the recipe history
    public void NullHistory()
    {
        searchRecipe = "";
    }

  //recipe search--Starts Main Activity
    public void searchBTN(View view)
    {
        searchRecipe = mSearchField.getText().toString();
        data = searchRecipe;
        Intent intentLoadNewActivity = new Intent(Cookle_main.this, MainActivity.class);
        intentLoadNewActivity.putExtra("names",searchRecipe);
        startActivity(intentLoadNewActivity);

    }

    public void ADVsearchBTN(View view)
    {
        Intent intentLoadNewActivity = new Intent(Cookle_main.this, AdvancedSearch.class);
        startActivity(intentLoadNewActivity);

    }


    //Open profile activity
    public void OpenProfile(View view)
    {
        Intent intentLoadNewActivity = new Intent(Cookle_main.this,activity_profile.class);
        startActivity(intentLoadNewActivity);

    }
}
