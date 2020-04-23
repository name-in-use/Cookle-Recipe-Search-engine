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
    ImageView PROFILE_IMG;
    Button PHOTObutton;
    private static final int PICK_IMAGE = 100;
    static  Context context;
   static SharedPreferences sharedPref;
   static SharedPreferences.Editor editor;
    private static final String PREFS_NAME = "preferenceName";
    Uri imageUri;
    private EditText namefield;
    private  TextView namelabel;
    private Button UPDATEbtn;
    private Button INSTRUCTIONAL;
    TextView Tname;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        PROFILE_IMG = (ImageView)findViewById(R.id.profile_image);
        PHOTObutton = (Button)findViewById(R.id.photo_btn);
        namefield = (EditText) findViewById(R.id.username);
        namelabel = (TextView) findViewById(R.id.namelabel);
        Tname = (TextView) findViewById(R.id.NameLabel);
        UPDATEbtn = (Button)findViewById(R.id.Update_btn);
        INSTRUCTIONAL = (Button)findViewById(R.id.INS);

       // Get from the SharedPreferences

        sharedPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        String USERname = sharedPref.getString("name", null);

        //set the saved username
        Tname.setText(USERname);

        data=Cookle_main.getActivityInstance().getData();

        //open gallery-- select profile photo
        PHOTObutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        INSTRUCTIONAL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), instructionals.class);
                startActivity(intent);

            }
        });

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();

           // sharedPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
           // editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
           // editor.putString("imageURI", imageUri.toString());
           // editor.commit();
            PROFILE_IMG.setImageURI(imageUri);


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
    public void UpdateInfo(View view)
    {
    Tname.setText(namefield.getText());

        sharedPref = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
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
    public void Contact(View view)
    {
        Intent intentLoadNewActivity = new Intent(Activity_profile.this, Contact.class);

        startActivity(intentLoadNewActivity);
    }
    //open recipe history
    public void SRecipe(View view)
    {
        Intent intentLoadNewActivity = new Intent(Activity_profile.this, SaveRecipes.class);
        startActivity(intentLoadNewActivity);
    }



}
