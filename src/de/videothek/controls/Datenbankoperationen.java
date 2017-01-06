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
import java.util.EventListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
    public static String[] getKategorieNameAlle() {
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        ArrayList<String> aList = new ArrayList<>();
        String[] aArray; 
        boolean check = false;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT kategorien.Kategoriename FROM kategorien ORDER BY kategorien.KAT_ID ASC ");
            //Auslesen 
            while(rs.next()){
                aList.add(rs.getString(1));
            }            
            rs.close();
            stmt.close();  
            check = true;
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"KategorienArrayLesefehler", "Fehler", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                    }
                } 
            }
            if(check == false){
                aArray=new String[]{"Keine Daten"};
            }
            else{
                aArray=aList.toArray(new String[aList.size()]);
            }
        return aArray;
    }
    
    //Neue Kategorie anlegen in der Datenbank
    public static void kategorieAnlegen(String bezeichnung) {
        Connection con = null;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        boolean check = false;        
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT kategorien.Kategoriename FROM kategorien WHERE kategorien.Kategoriename LIKE '"+bezeichnung+"' ");
            while(rs.next()){ 
                check = true;
                break;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Abfrage nach Kategorie", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        if(check == false){           
            try {
                con = DriverManager.getConnection(url, user, password);
                ps = con.prepareStatement("INSERT INTO kategorien (kategorien.KAT_ID, kategorien.Kategoriename) VALUES (NULL, ?)");
                ps.setString(1, bezeichnung);
                ps.execute();
                ps.close();
                JOptionPane.showMessageDialog(null, "Kategorie "+bezeichnung+" wurde gespeichert");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL beim Eintragen", "Fehler", JOptionPane.ERROR_MESSAGE);
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
        else{
            JOptionPane.showMessageDialog(null, "Kategorie "+bezeichnung+" existiert bereits.");
        }
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
        
    }
    
    /////////////////////////
    ////       FSK       ////
    /////////////////////////
     
    //Methode zum auslesen der Altersklassen im Datenbanksystem
    public static String[] getFSKAltersklassenAlle(){
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        ArrayList<String> aList = new ArrayList<>();
        String[] aArray; 
        boolean check = false;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT fsk.Altersklasse FROM fsk ORDER BY fsk.FSK_ID ASC");
            //Auslesen 
            while(rs.next()){
                aList.add(rs.getString(1));
            }            
            rs.close();
            stmt.close();  
            check = true;
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"FSKArrayLesefehler", "Fehler", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                    }
                } 
            }
            if(check == false){
                aArray=new String[]{"Keine Daten"};
            }
            else{
                aArray=aList.toArray(new String[aList.size()]);
            }
        return aArray;
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
    public static String[] getMedientypenNameAlle() {
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        ArrayList<String> aList = new ArrayList<>();
        String[] aArray; 
        boolean check = false;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT medientypen.Bezeichnung FROM medientypen ORDER BY medientypen.Medien_ID ASC");
            //Auslesen 
            while(rs.next()){
                aList.add(rs.getString(1));
            }            
            rs.close();
            stmt.close();  
            check = true;
            }
            catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"MedientypenArrayLesefehler", "Fehler", JOptionPane.ERROR_MESSAGE);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                    }
                } 
            }
            if(check == false){
                aArray=new String[]{"Keine Daten"};
            }
            else{
                aArray=aList.toArray(new String[aList.size()]);
            }
        return aArray;
    }
    
    //Neue Medientyp anlegen in der Datenbank
    public static void medietypenAnlegen(String bezeichnung) {
        Connection con = null;
        Statement stmt;
        ResultSet rs;
        PreparedStatement ps;
        boolean check = false;        
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Bezeichnung FROM medientypen WHERE Bezeichnung LIKE '"+bezeichnung+"' ");
            while(rs.next()){ 
                check = true;
                break;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Abfrage nach Medientyp", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        if(check == false){           
            try {
                con = DriverManager.getConnection(url, user, password);
                ps = con.prepareStatement("INSERT INTO medientypen (medientypen.Medien_ID, medientypen.Bezeichnung) VALUES (NULL, ?)");
                ps.setString(1, bezeichnung);
                ps.execute();
                ps.close();
                JOptionPane.showMessageDialog(null, "Medientyp "+bezeichnung+" wurde gespeichert");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL beim Eintragen", "Fehler", JOptionPane.ERROR_MESSAGE);
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
        else{
            JOptionPane.showMessageDialog(null, "Medientyp "+bezeichnung+" existiert bereits.");
        }
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
    
    //Kunde löschen
    public static void kundenLöschen(String kundenNr){
        Connection con = null;
        PreparedStatement ps;
        boolean check = false;
        try {
            con = DriverManager.getConnection(url, user, password);
            ps = con.prepareStatement("DELETE FROM t_kunden WHERE Kunden_Nr = ?");
            ps.setString(1, kundenNr);
            ps.execute();
            ps.close();
            check = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        if(check == true){
            JOptionPane.showMessageDialog(null, "Kunde: "+kundenNr+" gelöscht.");
        }
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
            JOptionPane.showMessageDialog(null, "Kunde: "+k.getKundenID()+" geändert.");
            
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
    
    //Methode zum anlegen einer Media im Datenbanksystem
    public static void medienAnlegenInDB(Medien medienObject){
        Connection con = null;
        PreparedStatement ps;
        Statement stmt;
        ResultSet rs;
                
        String titel;
        String erscheinungsjahr;
        int medientyp, kategorie, fsk;
        String newID = new String ();

        titel = medienObject.getTitel();                            
        erscheinungsjahr = medienObject.getErscheinungsjahr();      
        medientyp = medienObject.getMedium();                       
        kategorie = medienObject.getKategorie();                    
        fsk = medienObject.getFSK(); 
        //Eintragung des 'medien_object' als Medien in die Datenbank
        try {
            con = DriverManager.getConnection(url, user, password);
            ps = con.prepareStatement("INSERT INTO medien (medien.FILM_ID, medien.Medium, medien.Erscheinungsjahr, medien.Titel, medien.Kategorie, "
                                        +"medien.FSK) VALUES (NULL, "
                                        +"?, ?, ?, ?, ?)");
            ps.setInt(1, medientyp);   
            ps.setString(2, erscheinungsjahr);
            ps.setString(3, titel);          
            ps.setInt(4, kategorie);     
            ps.setInt(5, fsk);        
            ps.execute();
            ps.close();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT medien.FILM_ID FROM medien ORDER BY medien.FILM_ID DESC LIMIT 1");
            while(rs.next()){
                newID=rs.getString(1);
            }             
            JOptionPane.showMessageDialog(null, ""+titel+" wurde unter der Artikel-Nr.: "+newID+" gespeichert");            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ""+titel+" konnte nicht erstellt werden", "Fehler", JOptionPane.ERROR_MESSAGE);
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
    //Methode zum anlegen einer Media im Datenbanksystem
    public static void medienAendern(Medien medienObject){
        Connection con = null;
        String update = "UPDATE medien SET "
                        +"medien.Medium=?, "
                        +"medien.Erscheinungsjahr=?, "
                        +"medien.Titel=?, "
                        +"medien.Kategorie=?, "
                        +"medien.FSK=? "
                        +"WHERE medien.FILM_ID="
                        +"? "; 
        
        String titel=medienObject.getTitel();   
        String erscheinungsjahr= medienObject.getErscheinungsjahr();
        int medientyp = medienObject.getMedium(); 
        int kategorie = medienObject.getKategorie();
        int fsk = medienObject.getFSK();
        int filmID = medienObject.getFILM_ID();
        //Änderung in DB
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(update);
            ps.setInt(1, medientyp);   
            ps.setString(2, erscheinungsjahr);
            ps.setString(3, titel);          
            ps.setInt(4, kategorie);     
            ps.setInt(5, fsk);
            ps.setInt(6, filmID);
            ps.execute();
            ps.close();            
            JOptionPane.showMessageDialog(null, "Änderung von: "+titel+" wurde unter der Artikel-Nr.: "+medienObject.getFILM_ID()+" gespeichert");            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ""+titel+" konnte nicht geändert werden", "Fehler", JOptionPane.ERROR_MESSAGE);
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
    
    //Medien löschen
    public static void medienLoeschen(int FILM_ID) {
        verbindenZurDB();
        try {
            PreparedStatement ps = Datenbankoperationen.connection_object.prepareStatement("DELETE FROM medien WHERE FILM_ID = ?");
            ps.setInt(1, FILM_ID);
            ps.execute();
            JOptionPane.showMessageDialog(null, " "+FILM_ID+" gelöscht");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fehler SQL");
        }   
        verbindungSchließenZurDB();
    }
    
    //Medien auslesen
    public static Medien medienAuslesen(String filmID) {
        Connection con = null;
        Statement stmt;
        ResultSet rs;    
        Medien medien = new Medien();
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM medien WHERE medien.FILM_ID = "+filmID);
            while(rs.next()){
                medien.setFILM_ID(rs.getInt(1));
                medien.setMedium(rs.getInt(2));
                medien.setErscheinungsjahr(rs.getString(3));
                medien.setTitel(rs.getString(4));
                medien.setKategorie(rs.getInt(5));
                medien.setFSK(rs.getInt(6));
                break;
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
    /////////////////////////
    ////     Leihen      ////
    /////////////////////////
    
    //ausleihvorgang anlegen
    public static void leihenAnlegen(String Film_ID, String Kunden_NR) {
        verbindenZurDB();
        try {
            PreparedStatement ps;
            ps = connection_object.prepareStatement("INSERT INTO leihen (Film_ID, Kunden_NR, Anfangsdatum, Enddatum) "
                                                    +"VALUES ( ?, ?, CURRENT_DATE, null)");
            ps.setString(1, Film_ID);   
            ps.setString(2, Kunden_NR);
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
    //Check Kundennummer vorhanden
    public static Boolean CheckKundennummern(String kundenNr) {
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        boolean check = false;
        List<String> liste = new ArrayList<String>();
        int i = 0;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Kunden_Nr FROM t_kunden");
            while(rs.next()){ 
                liste.add(rs.getString(1));
                i++;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Keine Kunden", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        if(i > 0){
            for(int a = 0; a < liste.size() ; a++){
                if(liste.get(a).equals(kundenNr) == true){
                    check = true;
                    break;
                }
                else{
                    continue;
                }
            }
        }        
        return check;
    }
    //Check offene Ausleihe für Kundennummer
    public static Boolean CheckOffeneAusleihe(String kundenNr) {
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        boolean check = false;        
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM leihen WHERE Kunden_NR = "+kundenNr+" and Enddatum = null ");
            while(rs.next()){ 
                check = true;
                break;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Keine Ausleihe vorhanden", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }            
        return check;
    }
    //Check Artikel ausgeliehen
    public static Boolean CheckArtikelAusgeliehen(String filmID) {
        Connection con = null;
        Statement stmt;
        ResultSet rs; 
        boolean check = false;        
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM leihen WHERE leihen.Film_ID="+filmID+" and leihen.Enddatum = null ");
            while(rs.next()){ 
                check = true;
                break;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Keine Ausleihe Fehler", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }            
        return check;
    }
    //Methode zum anlegen einer Media im Datenbanksystem
    public static boolean medienCheckArtikelNr(String checkID){
        Connection con = null;        
        Statement stmt;
        ResultSet rs;               
        boolean check = false;
        
        try {
            con = DriverManager.getConnection(url, user, password);            
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT medien.FILM_ID FROM medien WHERE medien.FILM_ID = "+checkID);
            while(rs.next()){
                check=true;
                break;
            }      
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ""+checkID+" ??? SQL", "Fehler", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "SQL Connect", "Just a Mistake", JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        if(check == false){
            JOptionPane.showMessageDialog(null, ""+checkID+" existiert nicht");    
        }
        return check;        
    }
}   