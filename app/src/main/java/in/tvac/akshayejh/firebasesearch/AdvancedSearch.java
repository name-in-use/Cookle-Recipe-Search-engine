package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdvancedSearch extends AppCompatActivity {

    private CheckBox mint, spaghetti, chicken;
    private Button mbutton;
    private ArrayList<String> ingredients;
    private ArrayList<String> database_ingredient;
    private ArrayList<String> database_name;
    private ArrayList<Integer> database_total_num;
    private ArrayList<Integer> database_execution;
    private static ArrayList<Integer> saved_total_num;

    private ArrayList<String> exists;
    private ArrayList<String> database_imageURL;
    private static ArrayList<String> saved_imageURL; //////////////////
    private ArrayList<String> saved_execution;


    private static ArrayList<String> saved_name;
    private static ArrayList<String> saved_recipes_with_ingredients;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;


    private TextView mTextview;
    private AdvancedQuery test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        ingredients = new ArrayList<>();
        database_ingredient = new ArrayList<>();
        database_name = new ArrayList<>();
        database_total_num = new ArrayList<>();
        saved_total_num = new ArrayList<>();
        exists = new ArrayList<>();
        database_imageURL = new ArrayList<>();
        saved_imageURL = new ArrayList<>();

//test commits

        saved_name = new ArrayList<>();
        saved_recipes_with_ingredients = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Recipes");

        spaghetti = findViewById(R.id.spaghetti);
        chicken = findViewById(R.id.chicken);
        mint = findViewById(R.id.mint);

     //   mbutton = findViewById(R.id.button_search);
        mTextview.setEnabled(false);

        goListener();

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLocalQuery();
                goSort();

                Log.d("myTag", "1 " + saved_name.toString());

                Intent intentResults = new Intent(AdvancedSearch.this, AdvancedResults.class);
                intentResults.putStringArrayListExtra("names",saved_name);
                intentResults.putStringArrayListExtra("URL",saved_imageURL);
                intentResults.putStringArrayListExtra("ingredients",saved_recipes_with_ingredients);
                startActivity(intentResults);

                openAdvancedResults();
            }
        });
    }

    private void goListener() {

        database_ingredient.clear();
        database_name.clear();
        database_total_num.clear();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    test = recipeSnapshot.getValue(AdvancedQuery.class);
                    if (test.getIngredients() != null) {
                        database_ingredient.add(test.getIngredients());
                    }
                    if (test.getName() != null) {
                        database_name.add(test.getName());
                    }
                    if (test.getTotalNum() != null) {
                        database_total_num.add(Integer.parseInt(test.getTotalNum()));
                    }
                    if (test.getImage() != null) {
                        database_imageURL.add(test.getImage());
                    }
                    //           if (test.getExecution() != null) {
                    //               database_imageURL.add(test.getImage());
                    //           }
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private void doLocalQuery() {

        ingredients.clear();
        saved_name.clear();
        saved_recipes_with_ingredients.clear();
        exists.clear();

        if (spaghetti.isChecked()) {
            ingredients.add("spaghetti");
        }
        if (chicken.isChecked()) {
            ingredients.add("chicken");
        }
        if (mint.isChecked()) {
            ingredients.add("mint");
        }

        for (int z = 0; z < database_ingredient.size(); z++){
            exists.add("no");
        }


        for (int i = 0; i < database_ingredient.size(); i++) {
            //    exists.add("no");
            for (int y = 0; y < ingredients.size(); y++) {
                if (database_ingredient.get(i).contains(ingredients.get(y))) {
                    if (exists.get(i) == "no"){
                        saved_recipes_with_ingredients.add(database_ingredient.get(i));
                        saved_name.add(database_name.get(i));
                        saved_imageURL.add(database_imageURL.get(i));
                        saved_total_num.add(database_total_num.get(i));
                        exists.set(i, "yes");
                    }
                    else if (exists.get(i) == "yes") {
                        saved_total_num.set(i, saved_total_num.get(i) - 1);
                    }
                }
            }
        }
    }

    public void goSort() {
        int temp;
        String tempName;
        String tempIngr;
        String tempImage;

        for (int i = 0; i < saved_total_num.size(); i++) {
            for (int j = 1; j < (saved_total_num.size() - i); j++) {
                if (saved_total_num.get(j-1) > saved_total_num.get(j)) {

                    temp = saved_total_num.get(j - 1);
                    saved_total_num.set(j - 1, saved_total_num.get(j));
                    saved_total_num.set(j, temp);

                    tempName = saved_name.get(j - 1);
                    saved_name.set(j - 1, saved_name.get(j));
                    saved_name.set(j, tempName);

                    tempIngr = saved_recipes_with_ingredients.get(j - 1);
                    saved_recipes_with_ingredients.set(j - 1, saved_recipes_with_ingredients.get(j));
                    saved_recipes_with_ingredients.set(j, tempIngr);

                    tempImage = saved_imageURL.get(j - 1);
                    saved_imageURL.set(j - 1, saved_imageURL.get(j));
                    saved_imageURL.set(j, tempImage);
                }
            }
        }
    }

    public static ArrayList<String> getSaved_imageURL() {
        return saved_imageURL;
    }

    public static void setSaved_imageURL(ArrayList<String> saved_imageURL) {
        AdvancedSearch.saved_imageURL = saved_imageURL;
    }

    public static ArrayList<String> getSaved_name() {
        return saved_name;
    }

    public static void setSaved_name(ArrayList<String> saved_name) {
        AdvancedSearch.saved_name = saved_name;
    }

    public static ArrayList<String> getSaved_recipes_with_ingredients() {
        return saved_recipes_with_ingredients;
    }

    public static void setSaved_recipes_with_ingredients(ArrayList<String> saved_recipes_with_ingredients) {
        AdvancedSearch.saved_recipes_with_ingredients = saved_recipes_with_ingredients;
    }

    public void openAdvancedResults(){
        Intent intent = new Intent(this, AdvancedResults.class);
        startActivity(intent);
    }

}
