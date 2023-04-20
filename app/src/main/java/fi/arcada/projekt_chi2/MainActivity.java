package fi.arcada.projekt_chi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Deklarera 4 Button-objekt
    Button btn1, btn2, btn3, btn4, reset;
    // Deklarera 4 heltalsvariabler för knapparnas värden
    int val1, val2, val3, val4, t1, t2, proc1, proc2 = 0;

    TextView textViewCol1, textViewCol2, textViewRow1, textViewRow2, total1, total2, procent1, procent2, procentData;


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

        textViewCol1 = findViewById(R.id.textViewCol1);
        textViewCol2 = findViewById(R.id.textViewCol2);
        textViewRow1 = findViewById(R.id.textViewRow1);
        textViewRow2 = findViewById(R.id.textViewRow2);
        total1 = findViewById(R.id.total1);
        total2 = findViewById(R.id.total2);

        textViewCol1.setText("Your data");
        textViewCol2.setText("Your data");
        textViewRow1.setText("Your data");
        textViewRow2.setText("Your data");

        total1.setText("0");
        total2.setText("0");


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

        t2 = val1 + val3;
        t1 = val2 + val4;

/*

        if (t2 == 0)proc1 = 0;
        else if (val3 == 0 && val1 > 1) proc1 = 100;
        else proc1 = (val1/t2)*100;


        if (t1 == 0) proc2 = 0;
        else if (val4 == 0 && val2 > 1) proc2 = 100;
        else proc2 = (val2/t1)*100;

        */



        totals();



        // Slutligen, kör metoden som ska räkna ut allt!
        calculate();
    }

    /**
     * Metod som uppdaterar layouten och räknar ut själva analysen.
     */
    public void calculate() {

        // Uppdatera knapparna med de nuvarande värdena
        btn1.setText(String.valueOf(val1));
        btn2.setText(String.valueOf(val2));
        btn3.setText(String.valueOf(val3));
        btn4.setText(String.valueOf(val4));

        // Mata in värdena i Chi-2-uträkningen och ta emot resultatet
        // i en Double-variabel
        double chi2 = Significance.chiSquared(val1, val2, val3, val4);

        // Mata in chi2-resultatet i getP() och ta emot p-värdet
        double pValue = Significance.getP(chi2);

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

        total1.setText(String.valueOf(t1));
        total2.setText(String.valueOf(t2));
     //   procent1.setText(String.valueOf(proc1));
     //   procent2.setText(String.valueOf(proc2));
    }

    public void setReset(View view){
        val1 = 0;
        val2 = 0;
        val3 = 0;
        val4 = 0;
        t1 = 0;
        t2 = 0;
  //      proc2 = 0;
  //      proc1 = 0;
        calculate();

        totals();

    }


}