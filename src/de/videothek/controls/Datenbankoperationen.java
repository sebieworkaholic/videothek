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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return kategorie_id;
    }

    //Methode zum Ausgeben des passenden 'Kategorie-Namen' zur 'Kategorie-ID'
    public static String kategorieIDToKategorieBezeichnung(int kategorie_id) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return kategoriename;
    }
    
    //Methode zum auslesen der Kategorien im Datenbanksystem
    public static ArrayList<Kategorie> getKategorieNameAlle() {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return aList;
    }
    
    //Neue Kategorie anlegen in der Datenbank
    public static void kategorieAnlegen(String bezeichnung) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
    }
    
    //Kategorie löschen
    public static void kategorieLoeschen(int kategorie_id) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM kategorien WHERE KAT_ID=?");
            ps.setInt(1, kategorie_id);
            ps.execute();
        } catch (SQLException ex) {
        }
        verbindungSchließenZurDB();
    }
    
    public static void kategorieLoeschen(String kategorieName) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM kategorien WHERE Kategoriename=?");
            ps.setString(1, kategorieName);
            ps.execute();
        } catch (SQLException ex) {
        }
        verbindungSchließenZurDB();
    }
    
    //Kategorie bearbeiten
    public static void kategorieBezeichnungAendern(int kategorie_id, String kategorieName_neu) {
        verbindenZurDB();
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
        verbindungSchließenZurDB();
    }
    
    /////////////////////////
    ////       FSK       ////
    /////////////////////////
     
    //Methode zum auslesen der Altersklassen im Datenbanksystem
    public static ArrayList<FSK> getFSKAltersklassenAlle(){
        verbindenZurDB();
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
        verbindungSchließenZurDB();
        return aList;
    }
    
    //Neue Altersklasse anlegen in der Datenbank
    public static void altersklasseAnlegen(String altersklassen) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
    }
    
    //Altersklasse löschen
    public static void altersklassenLoeschen(int fsk_id) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM fsk WHERE FSK_ID=?");
            ps.setInt(1, fsk_id);
            ps.execute();
        } catch (SQLException ex) {
        }
        verbindungSchließenZurDB();
    }
    
    /////////////////////////
    ////   Medientypen   ////
    /////////////////////////
    
    //Methode zum Ausgeben der passenden 'Medien-ID' zum 'Medientyp-Bezeichnung'
    public static int medientypBezeichnungToMedientypID(String medientyp_bezeichnung) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return medientyp_id;
    }

    //Methode zum Ausgeben des passenden 'Medientyp-Bezeichnung' zur 'Medien_ID'
    public static String medientypIDToMedientypenBezeichnung(int medientyp_id) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return medientypBezeichnung;
    }
    
    //Methode zum auslesen der Medientyp-Bezeichnung im Datenbanksystem
    public static ArrayList<Medientypen> getMedientypenNameAlle() {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return aList;
    }
    
    //Neue Medientyp anlegen in der Datenbank
    public static void medietypenAnlegen(String bezeichnung) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
    }
    
    //Medietypen löschen
    public static void medientypLoeschen(int medientyp_id) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM medientypen WHERE Medien_ID=?");
            ps.setInt(1, medientyp_id);
            ps.execute();
        } catch (SQLException ex) {
        }
        verbindungSchließenZurDB();
    }
    
    public static void medientypLoeschen(String kategorieName) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM medientypen WHERE Bezeichnung=?");
            ps.setString(1, kategorieName);
            ps.execute();
        } catch (SQLException ex) {
        }
        verbindungSchließenZurDB();
    }
    
    //Medientyp bearbeiten
    public static void medientypBezeichnungAendern(int medientyp_id, String medientypBezeichnung_neu) {
        verbindenZurDB();
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
        verbindungSchließenZurDB();
    }
    
    /////////////////////////
    ////      Kunden     ////
    /////////////////////////
    
    //Kunden anlegen
    public static void kundenAnlegen(String anrede, String vorname, String nachname, String strasse, String plz, String wohnort, String geburtsdatum){
        verbindenZurDB();
        try {
            
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement(
                    "INSERT INTO `as-video`.`t_kunden` (`Kunden_Nr`, `Anrede`, `Vorname`, "
                    + "`Nachname`, `Strasse`, `PLZ`, `Ort`, `Geburtsdatum`) VALUES "
                    + "(NULL, ?, ?, ?, ?, ?, ?, ?);");
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
        verbindungSchließenZurDB();
    }
    //ID von Angelegten Kunden ermitteln
    public static String kundenAnlegenIDRuckgabe(String anrede, String vorname, String nachname, String strasse, String plz, String wohnort, String geburtsdatum){
        String kdnr = "";
        Connection con = null;
        Statement stmt;
        ResultSet rs;
        String abfrage = "SELECT t_kunden.Kunden_Nr FROM t_kunden WHERE "
                    +"Anrede='"+anrede+"' AND "
                    +"Vorname='"+vorname+"' AND "
                    +"Nachname='"+nachname+"' AND "
                    +"Strasse='"+strasse+"' AND "
                    +"PLZ='"+plz+"' AND "
                    +"Ort='"+wohnort+"' AND "
                    +"Geburtsdatum='"+geburtsdatum+"';";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(abfrage);

            while (rs.next()) {
                kdnr = rs.getString(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return kdnr;
    }
    
    //Kunden löschen
    public static void kundenLöschen(String kundenNr){
        verbindenZurDB();
        try {
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("DELETE FROM t_kunden WHERE Kunden_Nr = ?");
            ps.setString(1, kundenNr);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde kann nicht gelöscht werde da Ausleihvorgang vorhanden(kundenLöschen)");
        }
        verbindungSchließenZurDB();
    }
    
    //Kunden auslesen FIXED
    public static Kunden kundeAuslesen(String kundenNr) {
        Connection con = null;
        Statement stmt;
        ResultSet rs;        
        Kunden kunden = new Kunden();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM t_kunden WHERE Kunden_Nr = "+kundenNr+"");
            while(rs.next()){                
                kunden.setKundenID(rs.getInt("Kunden_Nr"));
                kunden.setAnrede(rs.getString("Anrede"));
                kunden.setVorname(rs.getString("Vorname"));
                kunden.setNachname(rs.getString("Nachname"));
                kunden.setStrasse(rs.getString("Strasse"));
                kunden.setPlz(rs.getString("PLZ"));
                kunden.setWohnort(rs.getString("Ort"));
                kunden.setGeburtsdatum(rs.getString("Geburtsdatum"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kundennummer existiert nicht", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        return kunden;
    }
    
    //Kunden bearbeiten FIXED ALLES!!!!!!!!!
    public static void kundenAendern(int Kunden_Nr, Kunden k) {
        Connection con = null;
        String update = "UPDATE t_kunden SET Anrede=?, Vorname=?, Nachname=?, Strasse=?, PLZ=?, Ort=?, Geburtsdatum=? WHERE Kunden_Nr=?";
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(update);
            
            ps.setString(1, k.getAnrede());
            ps.setString(2, k.getVorname());
            ps.setString(3, k.getNachname());
            ps.setString(4, k.getStrasse());
            ps.setInt(5, Integer.parseInt(k.getPlz()));
            ps.setString(6, k.getWohnort());
            ps.setString(7, k.getGeburtsdatum());
            ps.setInt(8, k.getKundenID()); 
            
            ps.executeUpdate();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Error", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    /////////////////////////
    ////     Medien      ////
    /////////////////////////
    
    //Methode zum anlegen einer Media im Datenbanksystem
    public static void medienAnlegenInDB(Medien medienObject) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
    }
    
    //Medien löschen
    public static void medienLöschen(int FILM_ID) {
        verbindenZurDB();
        try {
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("DELETE FROM medien WHERE FILM_ID = ?");
            ps.setInt(1, FILM_ID);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Kunde kann nicht gelöscht werde da Ausleihvorgang vorhanden(medienLöschen)");
        }   
        verbindungSchließenZurDB();
    }
    
    //Medien auslesen
    public static Medien medienAuslesen(int FILM_ID) {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return medien;
    }
    
    //Methode zum auslesen Medien Medien im Datenbanksystem
    public static ArrayList<Medien> getMedienAlle() {
        verbindenZurDB();

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
        verbindungSchließenZurDB();
        return aList;
    }
    
    public static void medienBearbeiten(int FILM_ID) {
        verbindenZurDB();
        verbindungSchließenZurDB();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
        
    }
    
    /////////////////////////
    ////     Leihen      ////
    /////////////////////////
    
    //ausleihvorgang anlegen
    public static void leihenAnlegen(int Film_ID, int Kunden_NR, String Enddatum) {
        verbindenZurDB();
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
        verbindungSchließenZurDB();
    }
    
    //alle Ausleihvorgänge eines Kunden ausgeben
    public static ArrayList<Leihen> getKundenLeihenAlle(int Kunden_NR) {
        verbindenZurDB();
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
        verbindungSchließenZurDB();
        return aList;
    }
    
    //alle Ausleihvorgänge eines Medium ausgeben
    public static ArrayList<Leihen> getMediumLeihenAlle(int Film_ID) {
        verbindenZurDB();
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
        verbindungSchließenZurDB();
        return aList;
    }
    
    //Ausleihvorgänge löschen
    public static void ausleihvorgängeLoeschen(int Kunden_NR, int Film_ID) {
        verbindenZurDB();
        PreparedStatement ps;
        try {
            ps = connection_object.prepareStatement("DELETE FROM leihen WHERE Film_ID=? AND Kunden_NR=?");
            ps.setInt(1, Film_ID);
            ps.setInt(2, Kunden_NR);
            ps.execute();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(ausleihvorgängeLoeschen)", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        verbindungSchließenZurDB();
    }
    
    //Alle Tabelle aus Abfrage erzeugen
    public static DefaultTableModel buildTable(String abfrage){
        DefaultTableModel dtm = null;
        Connection con = null;
        Statement stmt;
        ResultSet rs;
        ResultSetMetaData metadata;
        
        try {          
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(abfrage);
            metadata = rs.getMetaData();
           
            rs.last();
            int rowcount = rs.getRow();
            rs.beforeFirst();
            int columncount = metadata.getColumnCount();
           
            String[] columnnames = new String[columncount];
           
            for(int i=0;i<columncount;i++){
                columnnames[i] = metadata.getColumnLabel(i+1);
            }
           
            int k = 0;
            String[][] data = new String[rowcount][columncount];
            while(rs.next()){
               
                for(int i=0;i<data[0].length;i++){
                    String s =  rs.getString(i+1);
                    data[k][i] = s;
                }
                k++;
            }
            dtm = new DefaultTableModel(data,columnnames);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            dtm = new DefaultTableModel(new String[][]{{e.getMessage()},{}},new String[]{"Error"});
        }finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return dtm;
    }
}   