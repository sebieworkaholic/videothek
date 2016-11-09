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
    
    //FEHLER
    //Neue Kategorie anlegen in der Datenbank
    public static int kategorieAnlegen(String bezeichnung) {

        PreparedStatement ps;
        PreparedStatement ps2;
        ResultSet rs;
        int rueck = 0;
        ArrayList<Kategorie> prüfen = getKategorieNameAlle();
        Boolean contains = false;
        //Überprüfen ob die neu eingetragene Kategorie schon besteht
        for (Kategorie k : prüfen) {
            if (k.getBezeichnung().equals(bezeichnung)) {
                contains = true;
            }
        }
        if (contains) {
            JOptionPane.showMessageDialog(null, "Kategorie existiert bereits!");
        } else {
            try {
                ps = connection_object.prepareStatement("INSERT INTO (kategorien) Kategoriename VALUES (?)");
                ps.setString (1,bezeichnung);
                //ps.execute();
                /*ps2 = connection_object.prepareStatement("SELECT LAST_INSERT_ID() AS KAT_ID");
                rs = ps2.executeQuery();*/
                rs = ps.executeQuery();
                rs.next();
                rueck = rs.getInt("KAT_ID");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL Server läuft nicht bitte verlassen Sie in Panik das Gebäude(kategorieAnlegen)", "Fehler", JOptionPane.ERROR_MESSAGE);
            } /*catch (IOException ex) {
                Logger.getLogger(Datenbankoperationen.class.getName()).log(Level.SEVERE, null, ex);
            }*/

        }
        return rueck;
    }
}
