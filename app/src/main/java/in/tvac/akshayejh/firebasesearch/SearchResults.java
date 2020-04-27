package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchResults extends AppCompatActivity {
    private static final String TAG = "ResultsView";

    static String anyName;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_view);
        Intent intent = getIntent();
        Log.d(TAG, "onCreate");

        ArrayList<String> names = (ArrayList<String>) getIntent().getStringArrayListExtra("names");
        ArrayList<String> executions = (ArrayList<String>) getIntent().getStringArrayListExtra("Executions");
        ArrayList<String> ingredients = (ArrayList<String>) getIntent().getStringArrayListExtra("ingredients");

        //get the one string from the arraylists
        anyName = names.stream().findAny().orElse(null);
        String anyExecution = executions.stream().findAny().orElse(null);
        String anyIngredients = ingredients.stream().findAny().orElse(null);

        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("images");

        TextView Tname = findViewById(R.id.RecName);
        TextView Texecution = findViewById(R.id.recExecution);
        TextView Tingredients = findViewById(R.id.recIngredients);

        // ImageView ImageView1 = findViewById(R.id.imageView2);

        //display name
        if (anyName != null) {
            // call setText
            Tname.setText(anyName);
        }

        //display execution
        if (anyExecution != null) {
            // call setText
            Texecution.setText(anyExecution);
            Tingredients.setText(anyIngredients);
        }

    }
    public String RName()
    {
        return anyName;
    }

}
