package in.tvac.akshayejh.firebasesearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String data;
    private static MainActivity INSTANCE;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    public static ArrayList<String> RecipeNames = new ArrayList<>();
    public static ArrayList<String> RecipeExecutions = new ArrayList<>();
    public static ArrayList<Bitmap> RecipeImages = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        //create firebase instance
        mUserDatabase = FirebaseDatabase.getInstance().getReference("Recipes");
        //get recipe name from cookle_Main activity
        final String receivedData = intent.getStringExtra("names");

        //build recycler view
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
       mUserDatabase.orderByChild("name").equalTo(receivedData).addValueEventListener(new ValueEventListener() {
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
       });

    }

    public static MainActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        return this.data;
    }
    public void firebaseSearch(String searchText) {

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<Recipes, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Recipes, UsersViewHolder>(

                Recipes.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            public void populateViewHolder(UsersViewHolder viewHolder, Recipes model, int position) {


                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getexecution(), model.getImage());

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


        public void setDetails(Context ctx, String RecName, String RecExecution, String RecImage){

            TextView Rname = (TextView) mView.findViewById(R.id.name_text);
            //TextView Rexecution = (TextView) mView.findViewById(R.id.execution_text);
            ImageView Rimage = (ImageView) mView.findViewById(R.id.imageButton);

            Rname.setText(RecName);

            //load image
            Glide.with(ctx).load(RecImage).into(Rimage);

            RecipeNames.add(RecName);
            RecipeExecutions.add(RecExecution);

            data = RecipeNames.get(0);

        }

    }

       //open results activity
       public void openActivity2(View view)
       {
            Bundle bundle=null;
            Intent intentLoadNewActivity = new Intent(MainActivity.this, ResultsView.class);
            //load name and execution in the next activity
            intentLoadNewActivity.putExtra("names",RecipeNames);
            intentLoadNewActivity.putExtra("Executions",RecipeExecutions);

            startActivity(intentLoadNewActivity);
            //clear to array gia na mhn stelnei panw apo 1 Recipe to epomeno activity

            RecipeNames.clear();
            RecipeExecutions.clear();
        }

}



