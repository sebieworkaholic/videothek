/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.videothek.controls;

import de.videothek.model.FSK;
import de.videothek.model.Kategorie;
import de.videothek.model.Kunden;
import de.videothek.model.Leihen;
import de.videothek.model.Medien;
import de.videothek.model.Medientypen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Datenbankoperationen {
    
    static Connection connection_object;

    private static String url = "jdbc:mysql://127.0.0.1:3306/as-video";
    private static String user = "root";
    private static String password = "";
    
    //Verbindung zur Datenbank herstellen
    public static void verbindenZurDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection_object = DriverManager.getConnection(url, user, null);
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(verbindenZurDB)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Verbindung zur Datenbank schließen
    public static void verbindungSchließenZurDB() {
        try {
            connection_object.close();
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(verbindungSchließenZurDB)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /////////////////////////
    ////    Kategorie    ////
    /////////////////////////
    
    //Methode zum Ausgeben der passenden 'Kategorie-ID' zum 'Kategorienamen'
    public static int kategorieNameToKategorieID(String kategorieName) {

        PreparedStatement ps;
        ResultSet rs;
        int kategorie_id = 0;

        try {
            ps = connection_object.prepareStatement("SELECT KAT_ID FROM kategorien WHERE Kategoriename = ?");
            ps.setString(1, kategorieName);
            rs = ps.executeQuery();
            rs.next();
            kategorie_id = rs.getInt("KAT_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieNameToKategorieID)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return kategorie_id;
    }

    //Methode zum Ausgeben des passenden 'Kategorie-Namen' zur 'Kategorie-ID'
    public static String kategorieIDToKategorieBezeichnung(int kategorie_id) {

        PreparedStatement ps;
        ResultSet rs;
        String kategoriename = "";
        try {
            ps = connection_object.prepareStatement("SELECT Kategoriename FROM kategorien WHERE KAT_ID = ?");
            ps.setInt(1, kategorie_id);
            rs = ps.executeQuery();
            rs.next();
            kategoriename = rs.getString("Kategoriename");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieIDToKategorieBezeichnung)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return kategoriename;
    }
    
    //Methode zum auslesen der Kategorien im Datenbanksystem
    public static ArrayList<Kategorie> getKategorieNameAlle() {

        ArrayList<Kategorie> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Kategorie kategorie;
        int id;
        String bezeichnung;
        try {
            ps = connection_object.prepareStatement("SELECT * FROM kategorien");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("KAT_ID");
                bezeichnung = rs.getString("Kategoriename");
                kategorie = new Kategorie(id, bezeichnung);  //Speicher die Spalte 'kategoriename' aus der Abfrage in die Variable
                aList.add(kategorie);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getKategorieNameAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    //Neue Kategorie anlegen in der Datenbank
    public static void kategorieAnlegen(String bezeichnung) {

        PreparedStatement ps;
        ArrayList<Kategorie> prüfen = getKategorieNameAlle();
        Boolean contains = false;
        for (Kategorie k : prüfen) {
            if (k.getBezeichnung().equals(bezeichnung)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Kategorie existiert bereits!");
        } else {
            try {
                ps = connection_object.prepareStatement("INSERT INTO kategorien (Kategoriename)" + "VALUES (?)");
                ps.setString (1,bezeichnung);                
                ps.execute();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
            } 

        }
    }
    
    //Kategorie löschen
    public static void kategorieLoeschen(int kategorie_id) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM kategorien WHERE KAT_ID=?");
            ps.setInt(1, kategorie_id);
            ps.execute();
        } catch (SQLException ex) {
        }
    }
    
    public static void kategorieLoeschen(String kategorieName) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM kategorien WHERE Kategoriename=?");
            ps.setString(1, kategorieName);
            ps.execute();
        } catch (SQLException ex) {
        }

    }
    
    //Kategorie bearbeiten
    public static void kategorieBezeichnungAendern(int kategorie_id, String kategorieName_neu) {
        PreparedStatement statement = null;
        String sqlString = "UPDATE kategorie SET Kategoriename=? WHERE KAT_ID=?";
        ArrayList<Kategorie> prüfen = getKategorieNameAlle();
        boolean contains = false;
        
        //Überprüfen ob die neu eingetragene Kategorie schon besteht
        for (Kategorie k : prüfen) {
            if (k.getBezeichnung().equals(kategorieName_neu)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Kategorie existiert bereits!");
        } else {
            //Update durchführen
            try {

                statement = connection_object.prepareStatement(sqlString);
                statement.setString(1, kategorieName_neu);
                statement.setInt(2, kategorie_id);
                statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude", "Fehler", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /////////////////////////
    ////       FSK       ////
    /////////////////////////
     
    //Methode zum auslesen der Altersklassen im Datenbanksystem
    public static ArrayList<FSK> getFSKAltersklassenAlle(){
    ArrayList<FSK> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        FSK fsk;
        int id;
        String Altersklasse;
        try {
            ps = connection_object.prepareStatement("SELECT * FROM fsk");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("FSK_ID");
                Altersklasse = rs.getString("Altersklassen");
                fsk = new FSK(id, Altersklasse);  //Speicher die Spalte 'Altersklasse' aus der Abfrage in die Variable
                aList.add(fsk);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getFSKAltersklassenAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    //Neue Altersklasse anlegen in der Datenbank
    public static void altersklasseAnlegen(String altersklassen) {

        PreparedStatement ps;
        ArrayList<FSK> prüfen = getFSKAltersklassenAlle();
        Boolean contains = false;
        for (FSK k : prüfen) {
            if (k.getAltersklassen().equals(altersklassen)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Altersklasse existiert bereits!");
        } else {
            try {
                ps = connection_object.prepareStatement("INSERT INTO fsk (Altersklassen)" + "VALUES (?)");
                ps.setString (1,altersklassen);                
                ps.execute();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(AltersklassenAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
            } 

        }
    }
    
    //Altersklasse löschen
    public static void altersklassenLoeschen(int fsk_id) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM fsk WHERE FSK_ID=?");
            ps.setInt(1, fsk_id);
            ps.execute();
        } catch (SQLException ex) {
        }
    }
    
    /////////////////////////
    ////   Medientypen   ////
    /////////////////////////
    
    //Methode zum Ausgeben der passenden 'Medien-ID' zum 'Medientyp-Bezeichnung'
    public static int medientypBezeichnungToMedientypID(String medientyp_bezeichnung) {

        PreparedStatement ps;
        ResultSet rs;
        int medientyp_id = 0;

        try {
            ps = connection_object.prepareStatement("SELECT Medien_ID FROM medientypen WHERE Bezeichnung = ?");
            ps.setString(1, medientyp_bezeichnung);
            rs = ps.executeQuery();
            rs.next();
            medientyp_id = rs.getInt("Medien_ID");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieNameToKategorieID)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return medientyp_id;
    }

    //Methode zum Ausgeben des passenden 'Medientyp-Bezeichnung' zur 'Medien_ID'
    public static String medientypIDToMedientypenBezeichnung(int medientyp_id) {

        PreparedStatement ps;
        ResultSet rs;
        String medientypBezeichnung = "";
        try {
            ps = connection_object.prepareStatement("SELECT Bezeichnung FROM medientypen WHERE Medien_ID = ?");
            ps.setInt(1, medientyp_id);
            rs = ps.executeQuery();
            rs.next();
            medientypBezeichnung = rs.getString("Bezeichnung");
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieIDToKategorieBezeichnung)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return medientypBezeichnung;
    }
    
    //Methode zum auslesen der Medientyp-Bezeichnung im Datenbanksystem
    public static ArrayList<Medientypen> getMedientypenNameAlle() {

        ArrayList<Medientypen> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Medientypen medientypen;
        int id;
        String bezeichnung;
        try {
            ps = connection_object.prepareStatement("SELECT * FROM medientypen");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("Medien_ID");
                bezeichnung = rs.getString("Bezeichnung");
                medientypen = new Medientypen(id, bezeichnung);  //Speicher die Spalte 'Bezeichnung' aus der Abfrage in die Variable
                aList.add(medientypen);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getKategorieNameAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    //Neue Medientyp anlegen in der Datenbank
    public static void medietypenAnlegen(String bezeichnung) {

        PreparedStatement ps;
        ArrayList<Medientypen> prüfen = getMedientypenNameAlle();
        Boolean contains = false;
        for (Medientypen k : prüfen) {
            if (k.getBezeichnung().equals(bezeichnung)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Medientyp existiert bereits!");
        } else {
            try {
                ps = connection_object.prepareStatement("INSERT INTO medietypen (Bezeichnung)" + "VALUES (?)");
                ps.setString (1,bezeichnung);                
                ps.execute();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(medietypenAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
            } 

        }
    }
    
    //Medietypen löschen
    public static void medientypLoeschen(int medientyp_id) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM medientypen WHERE Medien_ID=?");
            ps.setInt(1, medientyp_id);
            ps.execute();
        } catch (SQLException ex) {
        }
    }
    
    public static void medientypLoeschen(String kategorieName) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM medientypen WHERE Bezeichnung=?");
            ps.setString(1, kategorieName);
            ps.execute();
        } catch (SQLException ex) {
        }

    }
    
    //Medientyp bearbeiten
    public static void medientypBezeichnungAendern(int medientyp_id, String medientypBezeichnung_neu) {
        PreparedStatement statement = null;
        String sqlString = "UPDATE medientypen SET Bezeichnung=? WHERE Medien_ID=?";
        ArrayList<Medientypen> prüfen = getMedientypenNameAlle();
        boolean contains = false;
        
        //Überprüfen ob die neu eingetragene Medientyp schon besteht
        for (Medientypen k : prüfen) {
            if (k.getBezeichnung().equals(medientypBezeichnung_neu)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Medientyp existiert bereits!");
        } else {
            //Update durchführen
            try {

                statement = connection_object.prepareStatement(sqlString);
                statement.setString(1, medientypBezeichnung_neu);
                statement.setInt(2, medientyp_id);
                statement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude", "Fehler", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /////////////////////////
    ////      Kunden     ////
    /////////////////////////
    
    //Kunden anlegen
    public static void kundenAnlegen(String anrede, String vorname, String nachname, String strasse, String plz, String wohnort, String geburtsdatum){
        try {
            
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("INSERT INTO t_kunden (Anrede, Vorname, Nachname, Strasse, PLZ, Ort, Geburtsdatum) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, anrede);   
            ps.setString(2, vorname);
            ps.setString(3, nachname);
            ps.setString(4, strasse);
            ps.setString(5, plz);
            ps.setString(6, wohnort);
            ps.setString(7, geburtsdatum);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kundenAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);

        }            
    }
    
    //Kunden löschen
    public static void kundenLöschen(String kundenNr){
        try {
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("DELETE FROM t_kunden WHERE Kunden_Nr = ?");
            ps.setString(1, kundenNr);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde kann nicht gelöscht werde da Ausleihvorgang vorhanden(kundenLöschen)");
        }
    }
    
    //Kunden auslesen
    public static Kunden kundeAuslesen(String kundenNr) {

        ResultSet rs;
        Kunden kunden = new Kunden();
        try {
            PreparedStatement ps = connection_object.prepareStatement("SELECT * FROM t_kundnen WHERE Kunden_Nr = ?");
            ps.setString(1, kundenNr);
            rs = ps.executeQuery();
            int kundenID = rs.getInt("Kunden_Nr");
            String anrede = rs.getString("Anrede");
            String vorname = rs.getString("Vorname");
            String nachname = rs.getString("Nachname");
            String strasse = rs.getString("Strasse");
            String plz = rs.getString("PLZ");
            String wohnort = rs.getString("Ort");
            String geburtsdatum = rs.getString("Geburtsdatum");
            
            kunden.setKundenID(kundenID);
            kunden.setAnrede(anrede);
            kunden.setVorname(vorname);
            kunden.setNachname(nachname);
            kunden.setStrasse(strasse);
            kunden.setPlz(plz);
            kunden.setWohnort(wohnort);
            kunden.setGeburtsdatum(geburtsdatum);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kundeAuslesen)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } 
        return kunden;
    }
    
    //Kunden bearbeiten
    public static void kundenAendern(int Kunden_Nr, Kunden k) {
        PreparedStatement statement = null;
        String sqlString = "UPDATE kategorie SET Anrede=?, Vorname=?, Nachname=?, Strasse=?, PLZ=?, Ort=?, Geburtstag=? WHERE Kunden_Nr=?";
        
        //Update durchführen
        try {

            statement = connection_object.prepareStatement(sqlString);
            statement.setString(1, k.getAnrede());
            statement.setString(2, k.getVorname());
            statement.setString(3, k.getNachname());
            statement.setString(4, k.getStrasse());
            statement.setString(5, k.getPlz());
            statement.setString(6, k.getWohnort());
            statement.setString(7, k.getGeburtsdatum());
            statement.setInt(8, k.getKundenID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kundenAendern)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kundenAendern)", "Fehler", JOptionPane.ERROR_MESSAGE);
            }          
        }
    }
    
    
    /////////////////////////
    ////     Medien      ////
    /////////////////////////
    
    //Methode zum anlegen einer Media im Datenbanksystem
    public static void medienAnlegenInDB(Medien medienObject) {

        //Deklaration der benötigten Variablen
        String titel;
        String erscheinungsjahr;
        int medientyp, kategorie, fsk;

        titel = medienObject.getTitel();                            //Variable erwartet String
        erscheinungsjahr = medienObject.getErscheinungsjahr();      //Variable erwartet String
        medientyp = medienObject.getMedium();                       //Variable erwartet int
        kategorie = medienObject.getKategorie();                    //Variable erwartet int
        fsk = medienObject.getFSK();                                //Variable erwartet int
        

        //Eintragung des 'medien_object' als Medien in die Datenbank
        try {
            
            PreparedStatement ps;
            ps = connection_object.prepareStatement("INSERT INTO medien " //Einfüge-Befehl in die Tabelle 'medien'
                    + "(titel, "
                    + "erscheinungsjahr, "
                    + "medientyp, "
                    + "kategorie, "
                    + "fsk,) "                     
                    + "VALUES (?,?,?,?,?)");
            ps.setString(1, titel);   //Spalte 'titelname' = titelname
            ps.setString(2, erscheinungsjahr); //Spalte 'erscheinungsjahr' = erscheinungsjahr
            ps.setInt(3, medientyp);          //Spalte 'medientyp' = medientyp
            ps.setInt(4, kategorie);     //Spalte 'kategorie' = kategorie
            ps.setInt(5, fsk);       //Spalte 'fsk' = fsk            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(medienAnlegenInDB)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Medien löschen
    public static void medienLöschen(int FILM_ID) {
        try {
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("DELETE FROM medien WHERE FILM_ID = ?");
            ps.setInt(1, FILM_ID);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde kann nicht gelöscht werde da Ausleihvorgang vorhanden(medienLöschen)");
        }        
    }
    
    //Medien auslesen
    public static Medien medienAuslesen(int FILM_ID) {

        ResultSet rs;
        Medien medien = new Medien();
        try {
            PreparedStatement ps = connection_object.prepareStatement("SELECT * FROM medien WHERE FILM_ID = ?");
            ps.setInt(1, FILM_ID);
            rs = ps.executeQuery();
            
            medien.setFILM_ID(rs.getInt("FILM_ID"));
            medien.setMedium(rs.getInt("Medium"));
            medien.setErscheinungsjahr(rs.getString("Erscheinungsjahr"));
            medien.setTitel(rs.getString("Titel"));
            medien.setKategorie(rs.getInt("Kategorie"));
            medien.setFSK(rs.getInt("FSK"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(medienAuslesen)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } 
        return medien;
    }
    
    //Methode zum auslesen Medien Medien im Datenbanksystem
    public static ArrayList<Medien> getMedienAlle() {

        ArrayList<Medien> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Medien medien = new Medien();
        
        try {
            ps = connection_object.prepareStatement("SELECT * FROM medien");
            rs = ps.executeQuery();
            while (rs.next()) {
                
                medien.setFILM_ID(rs.getInt("FILM_ID"));
                medien.setMedium(rs.getInt("Medium"));
                medien.setErscheinungsjahr(rs.getString("Erscheinungsjahr"));
                medien.setTitel(rs.getString("Titel"));
                medien.setKategorie(rs.getInt("Kategorie"));
                medien.setFSK(rs.getInt("FSK"));
                               
                aList.add(medien);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getMedienAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    public static void medienBearbeiten(int FILM_ID) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
    
    /////////////////////////
    ////     Leihen      ////
    /////////////////////////
    
    //ausleihvorgang anlegen
    public static void leihenAnlegen(int Film_ID, int Kunden_NR, String Enddatum) {
        try {
            
            PreparedStatement ps;
            ps = connection_object.prepareStatement("INSERT INTO leihen " //Einfüge-Befehl in die Tabelle 'leihen'
                    + "(Film_ID, "
                    + "Kunden_NR, "
                    + "Enddatum, )"                                         
                    + "VALUES (?,?,?)");
            ps.setInt(1, Film_ID);   //Spalte 'Film_ID' = Film_ID
            ps.setInt(2, Kunden_NR); //Spalte 'Kunden_NR' = Kunden_NR
            ps.setString(3, Enddatum);          //Spalte 'Enddatum' = Enddatum         
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(leihenAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    //alle Ausleihvorgänge eines Kunden ausgeben
    public static ArrayList<Leihen> getKundenLeihenAlle(int Kunden_NR) {
        ArrayList<Leihen> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Leihen leihen = new Leihen();
        
        try {
            ps = connection_object.prepareStatement("SELECT * FROM leihen WHERE Kunden_NR = ?");
            ps.setInt(1, Kunden_NR);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                leihen.setFilm_ID(rs.getInt("Film_ID"));
                leihen.setKunden_Nr(rs.getInt("Kunden_NR"));
                leihen.setAnfangsdatum(rs.getString("Anfangsdatum"));
                leihen.setEnddatum(rs.getString("Enddatum"));
                
                               
                aList.add(leihen);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getKundenLeihenAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    //alle Ausleihvorgänge eines Medium ausgeben
    public static ArrayList<Leihen> getMediumLeihenAlle(int Film_ID) {
        ArrayList<Leihen> aList = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Leihen leihen = new Leihen();
        
        try {
            ps = connection_object.prepareStatement("SELECT * FROM leihen WHERE FILM_ID = ?");
            ps.setInt(1, Film_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                leihen.setFilm_ID(rs.getInt("Film_ID"));
                leihen.setKunden_Nr(rs.getInt("Kunden_NR"));
                leihen.setAnfangsdatum(rs.getString("Anfangsdatum"));
                leihen.setEnddatum(rs.getString("Enddatum"));
                
                               
                aList.add(leihen);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(getMediumLeihenAlle)", "Fehler", JOptionPane.ERROR_MESSAGE);
        } /*catch (IOException ex) {
            Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return aList;
    }
    
    //Ausleihvorgänge löschen
    public static void ausleihvorgängeLoeschen(int Kunden_NR, int Film_ID) {
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM leihen WHERE Film_ID=? AND Kunden_NR=?");
            ps.setInt(1, Film_ID);
            ps.setInt(2, Kunden_NR);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(ausleihvorgängeLoeschen)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
