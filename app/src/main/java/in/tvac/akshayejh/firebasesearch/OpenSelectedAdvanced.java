package in.tvac.akshayejh.firebasesearch;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class OpenSelectedAdvanced extends AppCompatActivity {
    private static final String TAG = "OpenSelectedAdvanced";


    public TextView mTxt;
    private Integer mIndex;
    private String recip_exec;
    private String recip_ingr;


    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mExecution = new ArrayList<>();
    private ArrayList<String> saved_recipes_ingredients = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_advanced);
        Log.d(TAG, "onCreate");

        Bundle bundle = getIntent().getExtras();
        String recipName = bundle.getString("recipName");
        mNames = AdvancedSearch.getSaved_name();
        saved_recipes_ingredients = AdvancedSearch.getSaved_recipes_ingredients();
        mExecution = AdvancedSearch.getSaved_execution();


        mIndex = mNames.indexOf(recipName);
        recip_exec =  mExecution.get(mIndex);
        recip_ingr =  saved_recipes_ingredients.get(mIndex);

//        String recipExec = bundle.getString("recipExec");
        mTxt =  findViewById(R.id.textView4);
        mTxt.setText(recipName);

    }


}
