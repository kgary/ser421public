package edu.asu.ser421.samplehybrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button goButton;
    private EditText nameText;
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab our widgets
        goButton = (Button)findViewById(R.id.button);
        nameText = (EditText)findViewById(R.id.editText);
        helloText = (TextView)findViewById(R.id.textView);

        // Now add a listener when the button is clicked.
        // Note we do this in onCreate as we only have to set this up on initialization,
        // not on each lifecycle event (so not in onStart or onResume
        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // we want to grab a name from editText here, let's make sure we have one
                String s = nameText.getText().toString().trim();
                if (s != null && !s.equals("")) {
                    helloText.setText("Hello " + s);
                    //Toast.makeText(getApplicationContext(), "OK! Good Name!", Toast.LENGTH_SHORT).show();
                    Intent webIntent = new Intent(MainActivity.this, WebActivity.class);
                    webIntent.putExtra("NAME", s);
                    startActivity(webIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Hey! put in a name!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
