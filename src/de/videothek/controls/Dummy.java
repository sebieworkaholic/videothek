package de.videothek.controls;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Dummy {
    public static boolean checkPLZ(String plz){
        boolean check = false;
        int i=-1;
        try{
            i = Integer.parseInt(plz);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ihr Fehler", "Bitte PLZ Überprüfen", JOptionPane.ERROR_MESSAGE);
        } 
        if((i>=10000)&&(i<=99999)){
            check=true;
        }
        else{
            JOptionPane.showMessageDialog(null, "Ihr Fehler", "Bitte PLZ Überprüfen", JOptionPane.ERROR_MESSAGE);
        }
        return check;
    }
    public static boolean checkDatum(String tag, String monat, String jahr){
        boolean check=false;
        int i=-1;
        try{
            i = Integer.parseInt(tag);
            if((i>0) && (i<32)){
                i = Integer.parseInt(monat);
                if((i>0) && (i<13)){
                    i = Integer.parseInt(jahr);
                    if((i>1900) && (i<2017)){
                        check=true;
                    }
                }
            }            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ihr Fehler", "Bitte Datum Überprüfen", JOptionPane.ERROR_MESSAGE);
            check=false;
        }
        if(check==false){
            JOptionPane.showMessageDialog(null, "Ihr Fehler", "Bitte Datum Überprüfen", JOptionPane.ERROR_MESSAGE);
        }        
        return check;
    }
    public static String datumZusammensetzen(String tag, String monat, String jahr){
        String datum=""+jahr+"-"+monat+"-"+tag+"";
        return datum;
    }
    public static boolean checkInt(String eingabe){
        int i;
        boolean check = true;
        try{
            i = Integer.parseInt(eingabe);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Dies is keine logische Zahl", "Ihr Fehler", JOptionPane.ERROR_MESSAGE);
            check = false;
        }
        return check;
    }
    public static boolean checkIntFilmJahr(String eingabe){
        int a=0,e=0;
        boolean check=true;
        SimpleDateFormat format=new SimpleDateFormat("YYYY");       
        Date aktuelleZeit=new Date();
        String date=format.format(aktuelleZeit);      
        try{
            e = Integer.parseInt(date);
        }catch (Exception eYear){
            JOptionPane.showMessageDialog(null, "Jahreszahl kann nicht umgewandelt werden", "Fehler", JOptionPane.ERROR_MESSAGE);
            check = false;
        }
        try{
            a = Integer.parseInt(eingabe);            
        }catch (Exception eInput){
            JOptionPane.showMessageDialog(null, "1895 gab es den 1. Film", "Ihr Fehler", JOptionPane.ERROR_MESSAGE);
            check = false;
        }        
        if((a>=1895)&&(a<=e)){
            check=true;
        }
        return check;
    }
    public static boolean checkIntArtikelNr(String eingabe){
        int i;
        boolean check = true;
        try{
            i = Integer.parseInt(eingabe);
            if(i<10000){
                check=false;
                //JOptionPane.showMessageDialog(null, "Artikel-Nr.: minimum 10000","Ihr Fehler", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null, "Dies is keine logische Zahl", "Ihr Fehler", JOptionPane.ERROR_MESSAGE);
            check = false;
        }
        return check;
    }
}
