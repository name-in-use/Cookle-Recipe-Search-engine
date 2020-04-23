package in.tvac.akshayejh.firebasesearch.InstructionalsFolder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import in.tvac.akshayejh.firebasesearch.R;

public class instructionals extends AppCompatActivity {
private ImageButton onionBTN,tomatoBTN,knifeBTN,poachedeggsBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructional_results);
        onionBTN = (ImageButton)findViewById(R.id.onion_button);
        tomatoBTN= (ImageButton)findViewById(R.id.tomato_button);
        knifeBTN = (ImageButton)findViewById(R.id.knife_button);
        poachedeggsBTN = (ImageButton)findViewById(R.id.poachedeggs_button);

        onionBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set the id of the video
                String onion= "dCGS067s0zo";
                //initialise new activity
                Intent intent = new Intent(getBaseContext(), onionResults.class);
                //transfer to new activity the searchRecipe string
                intent.putExtra("onion", onion);
                //start new activity
                startActivity(intent);

            }
        });

        tomatoBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set the id of the video
                String tomato= "TbO95qNOjPQ";
                //initialise new activity
                Intent intent = new Intent(getBaseContext(), TomatoResults.class);
                //transfer to new activity the searchRecipe string
                intent.putExtra("tomato", tomato);
                //start new activity
                startActivity(intent);

            }
        });

        knifeBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set the id of the video
                String knife= "VJNA4vrdWec";
                //initialise new activity
                Intent intent = new Intent(getBaseContext(), KnifeSkills.class);
                //transfer to new activity the searchRecipe string
                intent.putExtra("knifeskills", knife);
                //start new activity
                startActivity(intent);

            }
        });

        poachedeggsBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //set the id of the video
                String poachedeggs= "p633fYhNyqw";
                //initialise new activity
                Intent intent = new Intent(getBaseContext(), KnifeSkills.class);
                //transfer to new activity the searchRecipe string
                intent.putExtra("poachedeggs", poachedeggs);
                //start new activity
                startActivity(intent);

            }
        });
    }
}

