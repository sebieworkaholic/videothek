/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.videothek.controls;

import de.videothek.model.Kategorie;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
    
    
    
    
    
    
    
    
}
