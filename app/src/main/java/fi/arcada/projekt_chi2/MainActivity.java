package fi.arcada.projekt_chi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Deklarera 4 Button-objekt
    Button btn1, btn2, btn3, btn4, reset, settings;
    // Deklarera 4 heltalsvariabler för knapparnas värden
    int val1, val2, val3, val4, t1, t2 = 0;
    double proc1, proc2, chi2, pValue = 0;

    TextView textViewCol1, textViewCol2, textViewRow1, textViewRow2, total1, total2, procent1, procent2, procentData, textChi, textSig, textP;

    SharedPreferences sharedpref;
    SharedPreferences.Editor prefEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Koppla samman Button-objekten med knapparna i layouten
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        reset = findViewById(R.id.reset);
        settings = findViewById(R.id.settings);

        textViewCol1 = findViewById(R.id.textViewCol1);
        textViewCol2 = findViewById(R.id.textViewCol2);
        textViewRow1 = findViewById(R.id.textViewRow1);
        textViewRow2 = findViewById(R.id.textViewRow2);
        procentData = findViewById(R.id.procentData);


        textChi = findViewById(R.id.textChi);
        textSig = findViewById(R.id.textSig);
        textP = findViewById(R.id.textP);



        total1 = findViewById(R.id.total1);
        total2 = findViewById(R.id.total2);

        sharedpref = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = sharedpref.edit();


     /**   textViewCol1.setText("Your data");
        textViewCol2.setText("Your data");
        textViewRow1.setText("Your data");
        textViewRow2.setText("Your data");
*/
        textViewCol1.setText(sharedpref.getString("text1", "Your data"));
        textViewCol2.setText(sharedpref.getString("text2", "Your data"));
        textViewRow1.setText(sharedpref.getString("text3", "Your data"));
        textViewRow2.setText(sharedpref.getString("text4", "Your data"));
        procentData.setText(sharedpref.getString("text3", "Your %") + "%");


        val1 = sharedpref.getInt("val1", 0);
        val2 = sharedpref.getInt("val2", 0);
        val3 = sharedpref.getInt("val3", 0);
        val4 = sharedpref.getInt("val4", 0);

        btn1.setText(String.valueOf(val1));
        btn2.setText(String.valueOf(val2));
        btn3.setText(String.valueOf(val3));
        btn4.setText(String.valueOf(val4));



        totals();


    }

    /**
     *  Klickhanterare för knapparna
     */
    public void buttonClick(View view) {

        // Skapa ett Button-objekt genom att type-casta (byta datatyp)
        // på det View-objekt som kommer med knapptrycket
        Button btn = (Button) view;

        // Kontrollera vilken knapp som klickats, öka värde på rätt vaiabel
        if (view.getId() == R.id.button1) val1++;
        if (view.getId() == R.id.button2) val2++;
        if (view.getId() == R.id.button3) val3++;
        if (view.getId() == R.id.button4) val4++;

        prefEditor.putInt("val1", val1);
        prefEditor.putInt("val2", val2);
        prefEditor.putInt("val3", val3);
        prefEditor.putInt("val4", val4);
        prefEditor.apply();



        totals();

        if (val1 == 0 && val3 == 0){proc1 = 0;}
        else if (val1 > 1 && val3 == 0){proc1 = 100;}
        else if (val1 == 0 && val3 > 1){proc1 = 0;}
        else if (val1 > 1 && val3 > 1){proc1 = (val1/t2)*100;}

        if (val2 == 0 && val4 == 0){proc2 = 0;}
        else if (val2 > 1 && val4 == 0){proc2 = 100;}
        else if (val2 == 0 && val4 > 1){proc2 = 0;}
        else if (val2 > 1 && val4 > 1){proc2 = (val2/t1)*100;}


        // Slutligen, kör metoden som ska räkna ut allt!
        calculate();
    }

    /**
     * Metod som uppdaterar layouten och räknar ut själva analysen.
     */

    // Uppdatera knapparna med de nuvarande värdena
    public void updateText() {
        btn1.setText(String.valueOf(val1));
        btn2.setText(String.valueOf(val2));
        btn3.setText(String.valueOf(val3));
        btn4.setText(String.valueOf(val4));
    }

    public void calculate() {

        updateText();

        // Mata in värdena i Chi-2-uträkningen och ta emot resultatet
        // i en Double-variabel
        chi2 = Significance.chiSquared(val1, val2, val3, val4);

        textChi.setText(String.format("Chi-2 result is: %.2f", chi2));

        // Mata in chi2-resultatet i getP() och ta emot p-värdet
        pValue = Significance.getP(chi2);

        textP.setText(String.format("P value is: %.3f", pValue));

        textSig.setText("Significance: " + sharedpref.getString("sig", "0.05"));

        /**
         *  - Visa chi2 och pValue åt användaren på ett bra och tydligt sätt!
         *
         *  - Visa procentuella andelen jakande svar inom de olika grupperna.
         *    T.ex. (val1 / (val1+val3) * 100) och (val2 / (val2+val4) * 100
         *
         *  - Analysera signifikansen genom att jämföra p-värdet
         *    med signifikansnivån, visa reultatet åt användaren
         *
         */

    }

    public void totals(){

        t2 = val1 + val3;
        t1 = val2 + val4;


        total1.setText(String.valueOf(t1));
        total2.setText(String.valueOf(t2));

        //procent1.setText(String.valueOf(proc1));
        //procent2.setText(String.valueOf(proc2));
    }

    public void setReset(View view){
        val1 = 0;
        val2 = 0;
        val3 = 0;
        val4 = 0;
        t1 = 0;
        t2 = 0;

        prefEditor.putInt("val1", val1);
        prefEditor.putInt("val2", val2);
        prefEditor.putInt("val3", val3);
        prefEditor.putInt("val4", val4);
        prefEditor.apply();


        proc2 = 0;
        proc1 = 0;
        textChi.setText("");
        textP.setText("");
        textSig.setText("");
        updateText();

        totals();

    }

    public void setSettings(View view){

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        // start the activity connect to the specified class
        startActivity(intent);

    }


}