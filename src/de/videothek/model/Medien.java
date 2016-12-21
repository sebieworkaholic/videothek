/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.videothek.model;

import java.sql.Date;

/**
 *
 * @author Athsur
 */
public class Medien {
    
    int FILM_ID;
    int Medium;
    String Erscheinungsjahr;
    String Titel;
    int Kategorie;
    int FSK;

    public Medien(int FILM_ID, int Medium, String Erscheinungsjahr, String Titel, int Kategorie, int FSK) {
        this.FILM_ID = FILM_ID;
        this.Medium = Medium;
        this.Erscheinungsjahr = Erscheinungsjahr;
        this.Titel = Titel;
        this.Kategorie = Kategorie;
        this.FSK = FSK;
    }

    public Medien(int Medium, String Erscheinungsjahr, String Titel, int Kategorie, int FSK) {
        this.Medium = Medium;
        this.Erscheinungsjahr = Erscheinungsjahr;
        this.Titel = Titel;
        this.Kategorie = Kategorie;
        this.FSK = FSK;
    }

    public Medien() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getFILM_ID() {
        return FILM_ID;
    }

    public void setFILM_ID(int FILM_ID) {
        this.FILM_ID = FILM_ID;
    }

    public int getMedium() {
        return Medium;
    }

    public void setMedium(int Medium) {
        this.Medium = Medium;
    }

    public String getErscheinungsjahr() {
        return Erscheinungsjahr;
    }

    public void setErscheinungsjahr(String Erscheinungsjahr) {
        this.Erscheinungsjahr = Erscheinungsjahr;
    }

    public String getTitel() {
        return Titel;
    }

    public void setTitel(String Titel) {
        this.Titel = Titel;
    }

    public int getKategorie() {
        return Kategorie;
    }

    public void setKategorie(int Kategorie) {
        this.Kategorie = Kategorie;
    }

    public int getFSK() {
        return FSK;
    }

    public void setFSK(int FSK) {
        this.FSK = FSK;
    }
    
    
    
}
