package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class instructionals extends AppCompatActivity {
private Button onionBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructional_results);
        onionBTN = (Button)findViewById(R.id.onion_button);

        onionBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String onion= "dCGS067s0zo";
                Intent intent = new Intent(getBaseContext(), instructionalResults.class);
                intent.putExtra("onion", onion);
                startActivity(intent);

            }
        });
    }
}

