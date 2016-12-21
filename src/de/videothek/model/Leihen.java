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
public class Leihen {
    
    int Film_ID;
    int Kunden_Nr;
    Date Anfangsdatum;
    Date Enddatum;

    public Leihen() {
    }

    public Leihen(int Film_ID, int Kunden_Nr, Date Anfangsdatum, Date Enddatum) {
        this.Film_ID = Film_ID;
        this.Kunden_Nr = Kunden_Nr;
        this.Anfangsdatum = Anfangsdatum;
        this.Enddatum = Enddatum;
    }

    public int getFilm_ID() {
        return Film_ID;
    }

    public void setFilm_ID(int Film_ID) {
        this.Film_ID = Film_ID;
    }

    public int getKunden_Nr() {
        return Kunden_Nr;
    }

    public void setKunden_Nr(int Kunden_Nr) {
        this.Kunden_Nr = Kunden_Nr;
    }

    public Date getAnfangsdatum() {
        return Anfangsdatum;
    }

    public void setAnfangsdatum(Date Anfangsdatum) {
        this.Anfangsdatum = Anfangsdatum;
    }

    public Date getEnddatum() {
        return Enddatum;
    }

    public void setEnddatum(Date Enddatum) {
        this.Enddatum = Enddatum;
    }
    
    
           
    
}
