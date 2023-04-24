package fi.arcada.projekt_chi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    Button settings, changeData;
    EditText text1, text2, text3, text4, sig;
    String t1, t2, t3, t4, tsig;

    SharedPreferences sharedpref;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        settings = findViewById(R.id.settings);
        changeData = findViewById(R.id.changeData);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        sig = findViewById(R.id.sig);

        sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = sharedpref.edit();

        text1.setText(sharedpref.getString("text1", "Your data"));
        text2.setText(sharedpref.getString("text2", "Your data"));
        text3.setText(sharedpref.getString("text3", "Your data"));
        text4.setText(sharedpref.getString("text4", "Your data"));
        sig.setText(sharedpref.getString("sig", "0.05"));
    }


    public void setData (View view){
        t1 = text1.getText().toString();
        t2 = text2.getText().toString();
        t3 = text3.getText().toString();
        t4 = text4.getText().toString();
        tsig = sig.getText().toString();

        prefEditor.putString("text1", t1);
        prefEditor.putString("text2", t2);
        prefEditor.putString("text3", t3);
        prefEditor.putString("text4", t4);
        prefEditor.putString("sig", tsig);
        prefEditor.apply();







        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        // start the activity connect to the specified class
        startActivity(intent);

    }


    public void setSettings(View view){

        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        // start the activity connect to the specified class
        startActivity(intent);

    }
}