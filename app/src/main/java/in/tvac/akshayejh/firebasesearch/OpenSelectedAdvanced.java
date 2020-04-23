package in.tvac.akshayejh.firebasesearch;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class OpenSelectedAdvanced extends AppCompatActivity {

    public TextView mTxt;
    static String anyName;
    public ArrayList<String> names;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_advanced);

        Bundle bundle = getIntent().getExtras();

        String recipName = bundle.getString("recipName");
        String recipExec = bundle.getString("recipExec");
        mTxt =  findViewById(R.id.textView4);
        mTxt.setText(recipName);

    }


}
