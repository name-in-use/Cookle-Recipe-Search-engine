package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdvancedSearch extends AppCompatActivity {

    private static final String TAG = "AdvancedSearch";


    private CheckBox pork, beef, chicken, bacon, calamari, octopus, mussels, shrimps, fish_fillet;
    private CheckBox lasagna, tagliatelle, rigatoni, spaghetti, farfaline, canelloni, raviolli, tortellini;
    private CheckBox white_rice, rissoto, wild_rice, barley, yellow_rice;
    private CheckBox tomato, lettuce, cabbage, carrot, potato, corn, broccoli, beet, pepper;
    private CheckBox mushrooms, orange, apple, pear, pinapple, lemon;
    private CheckBox peas, leutils, beans, chick_peas;
    private CheckBox parsley, spearmint, rosemary, thyme, oregano, mint, basil, anise, black_pepper;
    private CheckBox paprica, smoked_paprica, cumin, curry, coriander, cayenne_pepper, cinnamon;
    private CheckBox tomato_pulp, mustard, cream, barbeque_sauce, chicken_sauce, mayonnaise;

    private Button mbsearch;
    private ArrayList<String> ingredients;
    private ArrayList<String> database_imageURL;
    private ArrayList<String> database_name;
    private ArrayList<String> database_ingredient;
    private ArrayList<Integer> database_total_num;
    private ArrayList<String> database_execution;

    private ArrayList<String> exists;
    private static ArrayList<String> saved_imageURL;
    private static ArrayList<String> saved_name;
    private static ArrayList<String> saved_recipes_ingredients;
    private static ArrayList<Integer> saved_total_num;
    private static ArrayList<String> saved_execution;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private AdvancedQuery advQuer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);
        ingredients = new ArrayList<>();
        database_ingredient = new ArrayList<>();
        database_name = new ArrayList<>();
        database_imageURL = new ArrayList<>();
        database_total_num = new ArrayList<>();
        database_execution = new ArrayList<>();
        saved_total_num = new ArrayList<>();
        saved_imageURL = new ArrayList<>();
        saved_name = new ArrayList<>();
        saved_recipes_ingredients = new ArrayList<>();
        saved_execution = new ArrayList<>();
        exists = new ArrayList<>();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Recipes");

        pork = findViewById(R.id.pork);
        beef = findViewById(R.id.beef);
        chicken = findViewById(R.id.chicken);
        bacon = findViewById(R.id.bacon);
        calamari = findViewById(R.id.calamari);
        octopus = findViewById(R.id.octopus);
        mussels = findViewById(R.id.mussels);
        shrimps = findViewById(R.id.shrimps);
        fish_fillet = findViewById(R.id.fish_fillet);
        lasagna = findViewById(R.id.lasagna);
        tagliatelle = findViewById(R.id.tagliatelle);
        rigatoni = findViewById(R.id.rigatoni);
        spaghetti = findViewById(R.id.spaghetti);
        farfaline = findViewById(R.id.farfaline);
        canelloni = findViewById(R.id.canelloni);
        raviolli = findViewById(R.id.ravioli);
        tortellini = findViewById(R.id.tortellini);
        white_rice = findViewById(R.id.white_rice);
        rissoto = findViewById(R.id.rissoto);
        wild_rice = findViewById(R.id.wild_rice);
        barley = findViewById(R.id.barley);
        yellow_rice = findViewById(R.id.yellow_rice);
        tomato = findViewById(R.id.tomato);
        lettuce = findViewById(R.id.lettuce);
        cabbage = findViewById(R.id.cabbage);
        carrot = findViewById(R.id.carrot);
        potato = findViewById(R.id.potato);
        corn = findViewById(R.id.corn);
        broccoli = findViewById(R.id.broccoli);
        beet = findViewById(R.id.beet);
        pepper = findViewById(R.id.pepper);
        mushrooms = findViewById(R.id.mushrooms);
        orange = findViewById(R.id.orange);
        apple = findViewById(R.id.apple);
        pear = findViewById(R.id.pear);
        pinapple = findViewById(R.id.pineapple);
        lemon = findViewById(R.id.lemon);
        peas = findViewById(R.id.peas);
        leutils = findViewById(R.id.leutils);
        beans = findViewById(R.id.beans);
        chick_peas = findViewById(R.id.chick_peas);
        parsley = findViewById(R.id.parsley);
        spearmint = findViewById(R.id.spearmint);
        rosemary = findViewById(R.id.rosemary);
        thyme = findViewById(R.id.thyme);
        oregano = findViewById(R.id.oregano);
        mint = findViewById(R.id.mint);
        basil = findViewById(R.id.basil);
        anise = findViewById(R.id.anise);
        black_pepper = findViewById(R.id.black_pepper);
        paprica = findViewById(R.id.paprica);
        smoked_paprica = findViewById(R.id.smoked_paprica);
        cumin = findViewById(R.id.cumin);
        curry = findViewById(R.id.curry);
        coriander = findViewById(R.id.coriander);
        cayenne_pepper = findViewById(R.id.cayenne_pepper);
        cinnamon = findViewById(R.id.cinnamon);
        tomato_pulp = findViewById(R.id.tomato_pulp);
        mustard = findViewById(R.id.mustard);
        cream = findViewById(R.id.cream);
        barbeque_sauce = findViewById(R.id.barbeque_sauce);
        chicken_sauce = findViewById(R.id.chicken_sauce);
        mayonnaise = findViewById(R.id.mayonnaise);

        mbsearch = (Button) findViewById(R.id.search_button_adv);
        Log.d(TAG, "onCreate");
        goListener();

        mbsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick");
                doLocalQuery();
                goSort();
                Intent intentResults = new Intent(AdvancedSearch.this, AdvancedResults.class);

                startActivity(intentResults);
                openAdvancedResults();
            }
        });

    }

    private void goListener() {
        Log.d(TAG, "goListener");

        database_ingredient.clear();
        database_name.clear();
        database_total_num.clear();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    advQuer = recipeSnapshot.getValue(AdvancedQuery.class);
                    if (advQuer.getIngredients() != null) {
                        database_ingredient.add(advQuer.getIngredients());
                    }
                    if (advQuer.getName() != null) {
                        database_name.add(advQuer.getName());
                    }
                    if (advQuer.getTotalNum() != null) {
                        database_total_num.add(Integer.parseInt(advQuer.getTotalNum()));
                    }
                    if (advQuer.getImage() != null) {
                        database_imageURL.add(advQuer.getImage());
                    }
                    if (advQuer.getExecution() != null) {
                        database_execution.add(advQuer.getExecution());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

    private void doLocalQuery() {
        Log.d(TAG, "doLocalQuery");

        ingredients.clear();
        saved_name.clear();
        saved_recipes_ingredients.clear();
        saved_total_num.clear();
        saved_imageURL.clear();
        saved_execution.clear();
        exists.clear();


        if (pork.isChecked()) {
            ingredients.add("pork");
        }
        if (beef.isChecked()) {
            ingredients.add("beef");
        }
        if (chicken.isChecked()) {
            ingredients.add("chicken");
        }
        if (bacon.isChecked()) {
            ingredients.add("bacon");
        }
        if (calamari.isChecked()) {
            ingredients.add("calamari");
        }
        if (octopus.isChecked()) {
            ingredients.add("octopus");
        }
        if (mussels.isChecked()) {
            ingredients.add("mussels");
        }
        if (shrimps.isChecked()) {
            ingredients.add("shrimps");
        }
        if (fish_fillet.isChecked()) {
            ingredients.add("fish fillet");
        }
        if (lasagna.isChecked()) {
            ingredients.add("lasagna");
        }
        if (tagliatelle.isChecked()) {
            ingredients.add("tagliatelle");
        }
        if (rigatoni.isChecked()) {
            ingredients.add("rigatoni");
        }
        if (spaghetti.isChecked()) {
            ingredients.add("spaghetti");
        }
        if (farfaline.isChecked()) {
            ingredients.add("farfaline");
        }
        if (canelloni.isChecked()) {
            ingredients.add("canelloni");
        }
        if (raviolli.isChecked()) {
            ingredients.add("raviolli");
        }
        if (tortellini.isChecked()) {
            ingredients.add("tortellini");
        }
        if (white_rice.isChecked()) {
            ingredients.add("white rice");
        }
        if (rissoto.isChecked()) {
            ingredients.add("rissoto");
        }
        if (wild_rice.isChecked()) {
            ingredients.add("wild rice");
        }
        if (barley.isChecked()) {
            ingredients.add("barley");
        }
        if (yellow_rice.isChecked()) {
            ingredients.add("yellow rice");
        }
        if (tomato.isChecked()) {
            ingredients.add("tomato");
        }
        if (lettuce.isChecked()) {
            ingredients.add("lettuce");
        }
        if (cabbage.isChecked()) {
            ingredients.add("cabbage");
        }
        if (carrot.isChecked()) {
            ingredients.add("tortellini");
        }
        if (corn.isChecked()) {
            ingredients.add("corn");
        }
        if (broccoli.isChecked()) {
            ingredients.add("broccoli");
        }
        if (beet.isChecked()) {
            ingredients.add("beet");
        }
        if (pepper.isChecked()) {
            ingredients.add("pepper");
        }
        if (mushrooms.isChecked()) {
            ingredients.add("mushrooms");
        }
        if (orange.isChecked()) {
            ingredients.add("orange");
        }
        if (apple.isChecked()) {
            ingredients.add("apple");
        }
        if (pear.isChecked()) {
            ingredients.add("pear");
        }
        if (pinapple.isChecked()) {
            ingredients.add("pinapple");
        }
        if (lemon.isChecked()) {
            ingredients.add("lemon");
        }
        if (peas.isChecked()) {
            ingredients.add("peas");
        }
        if (leutils.isChecked()) {
            ingredients.add("leutils");
        }
        if (beans.isChecked()) {
            ingredients.add("beans");
        }
        if (chick_peas.isChecked()) {
            ingredients.add("chick peas");
        }
        if (parsley.isChecked()) {
            ingredients.add("parsley");
        }
        if (spearmint.isChecked()) {
            ingredients.add("spearmint");
        }
        if (rosemary.isChecked()) {
            ingredients.add("rosemary");
        }
        if (thyme.isChecked()) {
            ingredients.add("thyme");
        }
        if (oregano.isChecked()) {
            ingredients.add("oregano");
        }
        if (mint.isChecked()) {
            ingredients.add("mint");
        }
        if (basil.isChecked()) {
            ingredients.add("basil");
        }
        if (anise.isChecked()) {
            ingredients.add("anise");
        }
        if (black_pepper.isChecked()) {
            ingredients.add("black pepper");
        }
        if (paprica.isChecked()) {
            ingredients.add("paprica");
        }
        if (smoked_paprica.isChecked()) {
            ingredients.add("smoked paprica");
        }
        if (cumin.isChecked()) {
            ingredients.add("cumin");
        }
        if (curry.isChecked()) {
            ingredients.add("curry");
        }
        if (coriander.isChecked()) {
            ingredients.add("coriander");
        }
        if (cayenne_pepper.isChecked()) {
            ingredients.add("cayenne pepper");
        }
        if (cinnamon.isChecked()) {
            ingredients.add("cinnamon");
        }
        if (tomato_pulp.isChecked()) {
            ingredients.add("tomato pulp");
        }
        if (mustard.isChecked()) {
            ingredients.add("mustard");
        }
        if (cream.isChecked()) {
            ingredients.add("cream");
        }
        if (barbeque_sauce.isChecked()) {
            ingredients.add("barbeque sauce");
        }
        if (chicken_sauce.isChecked()) {
            ingredients.add("chicken sauce");
        }
        if (mayonnaise.isChecked()) {
            ingredients.add("mayonnaise");
        }

        for (int z = 0; z < database_ingredient.size(); z++){
            exists.add("no");
        }
        for (int i = 0; i < database_ingredient.size(); i++) {
            for (int y = 0; y < ingredients.size(); y++) {
                if (database_ingredient.get(i).contains(ingredients.get(y))) {
                    if (exists.get(i) == "no"){
                        saved_recipes_ingredients.add(database_ingredient.get(i));
                        saved_name.add(database_name.get(i));
                        saved_imageURL.add(database_imageURL.get(i));
                        saved_total_num.add(database_total_num.get(i) + 1);
                        saved_execution.add(database_execution.get(i));
                        exists.set(i, "yes");
                    } else if (exists.get(i) == "yes" && ingredients.size() < saved_total_num.size() ) {
                        saved_total_num.set(i, saved_total_num.get(i) - 1);
                    }
                }
            }
        }
    }

    public void goSort() {
        Log.d(TAG, "goSort");

        int temp;
        String tempName;
        String tempIngr;
        String tempImage;
        String tempExec;

        for (int i = 0; i < saved_total_num.size(); i++) {
            for (int j = 1; j < (saved_total_num.size() - i); j++) {
                if (saved_total_num.get(j - 1) > saved_total_num.get(j)) {

                    temp = saved_total_num.get(j - 1);
                    saved_total_num.set(j - 1, saved_total_num.get(j));
                    saved_total_num.set(j, temp);

                    tempName = saved_name.get(j - 1);
                    saved_name.set(j - 1, saved_name.get(j));
                    saved_name.set(j, tempName);

                    tempIngr = saved_recipes_ingredients.get(j - 1);
                    saved_recipes_ingredients.set(j - 1, saved_recipes_ingredients.get(j));
                    saved_recipes_ingredients.set(j, tempIngr);

                    tempImage = saved_imageURL.get(j - 1);
                    saved_imageURL.set(j - 1, saved_imageURL.get(j));
                    saved_imageURL.set(j, tempImage);

                    tempExec = saved_execution.get(j - 1);
                    saved_execution.set(j - 1, saved_execution.get(j));
                    saved_execution.set(j, tempExec);
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

    public static ArrayList<String> getSaved_recipes_ingredients() {
        return saved_recipes_ingredients;
    }

    public static void setSaved_recipes_ingredients(ArrayList<String> saved_recipes_ingredients) {
        AdvancedSearch.saved_recipes_ingredients = saved_recipes_ingredients;
    }

    public static ArrayList<String> getSaved_execution() {
        return saved_execution;
    }

    public static void setSaved_execution(ArrayList<String> saved_execution) {
        AdvancedSearch.saved_execution = saved_execution;
    }


    public void openAdvancedResults(){
        if (saved_name == null){
        Log.d(TAG, "openAdvancedResults");
        Intent intent = new Intent(this, AdvancedResults.class);
        startActivity(intent);
        } else {
            Log.d(TAG, "openAdvancedResults, zero results");
        }
    }
}
