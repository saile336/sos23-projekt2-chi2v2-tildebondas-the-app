package fi.arcada.projekt_chi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        settings = findViewById(R.id.settings);
    }


    public void setSettings(View view){

        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        // start the activity connect to the specified class
        startActivity(intent);

    }
}