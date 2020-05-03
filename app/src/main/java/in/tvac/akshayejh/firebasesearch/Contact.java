package in.tvac.akshayejh.firebasesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Contact extends AppCompatActivity {
    private static final String TAG = "Contact";

    private EditText textMail;
   // private EditText mEditTextSubject;
    private EditText textMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

       // textMail = findViewById(R.id.textMail);
        //textMail.setText("teamprojectcookle@gmail.com");
        //mEditTextSubject = findViewById(R.id.edit_text_subject);
        textMessage = findViewById(R.id.textMessage);

        ImageButton buttonSend = findViewById(R.id.send_btn);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }
    private void sendMail() {
        //String recipientList = mEditTextTo.getText().toString();
        String recipientList = "teamprojectcookle@gmail.com";
        String[] recipients = recipientList.split(",");

       // String subject = mEditTextSubject.getText().toString();
        String message = textMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        //intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }


}
