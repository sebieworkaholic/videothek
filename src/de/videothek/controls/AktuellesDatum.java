
package de.videothek.controls;
import java.text.SimpleDateFormat;
import java.util.Date; 
/**
 *
 * @author admin
 */
public class AktuellesDatum {  
    
    public static String getAktuellesDatumDDMMYYYY (){ 
        //Gibt das Aktuelle Datum ink. Zeit als String
        
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");       
        Date aktuelleZeit = new Date();
        String date = format.format(aktuelleZeit);
        return date;
    }    
}
