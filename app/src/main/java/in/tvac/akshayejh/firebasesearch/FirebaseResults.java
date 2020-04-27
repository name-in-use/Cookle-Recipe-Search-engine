package in.tvac.akshayejh.firebasesearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseResults extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static String data;
    private static FirebaseResults INSTANCE;
    public static TextView MatchRecipe;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    public static ArrayList<String> recipeNames = new ArrayList<>();
    public static ArrayList<String> recipeExecutions = new ArrayList<>();
    public static ArrayList<String> recipeIngredients = new ArrayList<>();

    public static ArrayList<Bitmap> RecipeImages = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        Intent intent = getIntent();
        MatchRecipe=(TextView) findViewById(R.id.MatchesText);
        //create firebase instance
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Recipes");
        //get recipe name from cookle_Main activity
        final String receivedData = intent.getStringExtra("names");

        //build recycler view
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        firebaseSearch(receivedData);
     /*  mUserDatabase.orderByChild("name").equalTo(receivedData).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()) {
                   //name exist
                   firebaseSearch(receivedData);
               }
               else
               {
                   Toast.makeText(MainActivity.this,
                           "Recipe not found", Toast.LENGTH_SHORT).show();
               }
           }
           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
           }

           //start search
       });*/

    }

    public static FirebaseResults getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        return this.data;
    }
    public void firebaseSearch(String searchText) {
        Log.d(TAG, "firebaseSearch");


        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");



        FirebaseRecyclerAdapter<RecipeObject, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecipeObject, UsersViewHolder>(

                RecipeObject.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            public void populateViewHolder(UsersViewHolder viewHolder, RecipeObject model, int position) {
                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getexecution(), model.getImage(), model.getIngredients());
            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }
    // View Holder Class
    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String RecName, String RecExecution, String RecImage, String RecIngredients){
            Log.d(TAG, "setDetails");
            TextView Rname = (TextView) mView.findViewById(R.id.name_text);
            //TextView Rexecution = (TextView) mView.findViewById(R.id.execution_text);
            ImageView Rimage = (ImageView) mView.findViewById(R.id.imageButton);

            Rname.setText(RecName);

            //load image
            Glide.with(ctx).load(RecImage).into(Rimage);

            recipeNames.add(RecName);
            recipeExecutions.add(RecExecution);
            recipeIngredients.add(RecIngredients);
            data = recipeNames.get(0);
        }
    }

       //open results activity
       public void openResultsView(View view)
       {
           Log.d(TAG, "openResultsView");
           Bundle bundle=null;
            Intent intentLoadNewActivity = new Intent(FirebaseResults.this, SearchResults.class);
            //load name and execution in the next activity
            intentLoadNewActivity.putExtra("names", recipeNames);
            intentLoadNewActivity.putExtra("Executions", recipeExecutions);
            intentLoadNewActivity.putExtra("ingredients", recipeIngredients);


           startActivity(intentLoadNewActivity);
            //clear to array gia na mhn stelnei panw apo 1 Recipe to epomeno activity

            recipeNames.clear();
            recipeExecutions.clear();
            recipeIngredients.clear();
        }

}



