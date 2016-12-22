/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.area;

/**
 *
 * @author admin
 */
public class TestArea {
    public static String TestSebastian(String anrede, String vorname, String nachname, String strasse, String plz, String wohnort, String geburtsdatum){
        String abfrage = "SELECT 'Kunden_Nr' FROM `t_kunden` WHERE "
                    +"`Anrede`='"+anrede+"' AND "
                    +"`Vorname`='"+vorname+"' AND "
                    +"`Nachname`='"+nachname+"' AND "
                    +"`Strasse`='"+strasse+"' AND "
                    +"`PLZ`='"+plz+"' AND "
                    +"`Ort`='"+wohnort+"' AND "
                    +"`Geburtsdatum`='"+geburtsdatum+"';";
        return abfrage;
    }
    public static void main(final String[] args) {
               
    }
    
}
