
package de.videothek.controls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controls {
    
    public static String getAktDatumDDMMYYYY (){ 
            //Gibt das Aktuelle Datum ink. Zeit als String

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");       
            Date aktuelleZeit = new Date();
            String date = format.format(aktuelleZeit);
            return date;
        }        
}
