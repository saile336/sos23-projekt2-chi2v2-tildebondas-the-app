package fi.arcada.projekt_chi2;

public class Significance {

    /**
     * Metod som räknar ut Chi-två på basis av fyra observerade värden (o1 - o4).
     */
    public static double chiSquared(int val1, int val2, int val3, int val4) {

        // heltalsvariabler tänkta att få de förväntade värdena



        double e1, e2, e3, e4;

        double up, down, left, right, tots;
        
        //net = North East total, et = East total, st South total and swt =South West total
        
        up = val1 + val2;
        down = val3 + val4;
        right = val2 + val4;
        left = val1 + val3;
        
        tots = up + down;
        
        e1 = (up * left)/tots;
        e2 = (up * right)/tots;
        e3 = (down * left)/tots;
        e4 = (down * right)/tots;


        
        

        
        /**
        
        e12 = (val1 + val2)+(val3+val4);
        d12 = val2 + val4;
        c12 = val1 + val3;
        e10 = val3 + val4;
        e8 = val1 + val2;

        e1 = e8+c12/e12;
        e2 = e8+d12/e12;
        e3 = e10+c12/e12;
        e4 = d12+e10/e12;
         */

        double chi1, chi2, chi3, chi4;

        chi1 = Math.pow(val1 - e1, 2)/e1;
        chi2 = Math.pow(val2 - e2, 2)/e2;
        chi3 = Math.pow(val3 - e3, 2)/e3;
        chi4 = Math.pow(val4 - e4, 2)/e4;

        double chiResult = chi1 + chi2 + chi3 + chi4;






        /**
         *  Implementera din egen Chi-två-uträkning här!
         *
         *  1.  Räkna de förväntade värdena, spara resultaten i e1 - e4
         *
         *  2.  Använd de observerade värdena (o1 - o4) och de förväntade
         *      värdena (e1 - e4) för att räkna ut Chi-två enligt formeln.
         *
         *  3.  returnera resultatet
         *      (använd det sedan för att få p-värdet via getP()
         *
         * */

        return chiResult;
    }


    /**
     * Metod som tar emot resultatet från Chi-två-uträkningen
     * och returnerar p-värde enligt tabellen (en frihetsgrad)
     * (De mest extrema värdena har lämnats bort, det är ok för våra syften)
     *
     * exempel: getP(2.82) returnerar ett p-värde på 0.1
     *
     */
    public static double getP(double chiResult) {

        double p = 0.99;

        if (chiResult >= 1.642) p = 0.2;
        if (chiResult >= 2.706) p = 0.1;
        if (chiResult >= 3.841) p = 0.05;
        if (chiResult >= 5.024) p = 0.025;
        if (chiResult >= 5.412) p = 0.02;
        if (chiResult >= 6.635) p = 0.01;
        if (chiResult >= 7.879) p = 0.005;
        if (chiResult >= 9.550) p = 0.002;

        return p;

    }

}
