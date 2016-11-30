/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.videothek.model;

/**
 *
 * @author user
 */
public class FSK {
    
    private String Altersklassen;
    private int id;

    public FSK(int id, String Altersklassen) {
        this.Altersklassen = Altersklassen;
        this.id = id;
    }

    public String getAltersklassen() {
        return Altersklassen;
    }

    public void setAltersklassen(String Altersklassen) {
        this.Altersklassen = Altersklassen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
        
}
