package in.tvac.akshayejh.firebasesearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import in.tvac.akshayejh.firebasesearch.InstructionalsFolder.instructionals;

public class Activity_profile extends AppCompatActivity {
    ArrayList<String> addArray = new ArrayList<String>();
    ImageView profileIMG;
    Button photoButton;
    private static final int pickImage = 100;
    static  Context context;
   static SharedPreferences sharedPref;
   static SharedPreferences.Editor editor;
    private static final String prefs_Name = "preferenceName";
    Uri imageUri;
    private EditText namefield;
    private  TextView namelabel;
    private Button UPDATEbtn;
    private Button instructional;
    TextView tName;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileIMG = (ImageView)findViewById(R.id.profile_image);
        photoButton = (Button)findViewById(R.id.photo_btn);
        namefield = (EditText) findViewById(R.id.username);
        namelabel = (TextView) findViewById(R.id.namelabel);
        tName = (TextView) findViewById(R.id.NameLabel);
        UPDATEbtn = (Button)findViewById(R.id.Update_btn);
        instructional = (Button)findViewById(R.id.INS);

       // Get from the SharedPreferences

        sharedPref = getApplicationContext().getSharedPreferences(prefs_Name, 0);
        String USERname = sharedPref.getString("name", null);

        //set the saved username
        tName.setText(USERname);

        data=Cookle_main.getActivityInstance().getData();

        //open gallery-- select profile photo
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        instructional.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), instructionals.class);
                startActivity(intent);

            }
        });

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, pickImage);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == pickImage){
            imageUri = data.getData();

           // sharedPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
           // editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
           // editor.putString("imageURI", imageUri.toString());
           // editor.commit();
            profileIMG.setImageURI(imageUri);


        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    //set name
    public void updateInfo(View view)
    {
    tName.setText(namefield.getText());

        sharedPref = getApplicationContext().getSharedPreferences(prefs_Name, 0);
         editor = sharedPref.edit();
        editor.putString("name", (namefield.getText().toString()));
        namefield.setText("");
      // Apply the edits!
        editor.apply();

        // namefield.setVisibility(view.INVISIBLE);
       // namelabel.setVisibility(view.INVISIBLE);
      // UPDATEbtn.setVisibility(view.INVISIBLE);

    }


    //open contact activity
    public void contact(View view)
    {
        Intent intentLoadNewActivity = new Intent(Activity_profile.this, Contact.class);

        startActivity(intentLoadNewActivity);
    }
    //open recipe history
    public void sRecipe(View view)
    {
        Intent intentLoadNewActivity = new Intent(Activity_profile.this, RecipeHistory.class);
        startActivity(intentLoadNewActivity);
    }



}
