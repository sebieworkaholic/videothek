/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.videothek.view;
import  de.videothek.controls.*;
import java.awt.event.ActionListener;

/**
 *
 * @author admin
 */
public class Videothek extends javax.swing.JFrame {

    /**
     * Creates new form Videothek
     */
    public Videothek() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTPHauptmenue = new javax.swing.JTabbedPane();
        jPAusleihen = new javax.swing.JPanel();
        jTPAusleihenMenu = new javax.swing.JTabbedPane();
        jPAusleiheStarten = new javax.swing.JPanel();
        jLASKundenNummer = new javax.swing.JLabel();
        jLASArtikelNummer = new javax.swing.JLabel();
        jTFASKundenNummer = new javax.swing.JTextField();
        jTFASArtikelNummer = new javax.swing.JTextField();
        jBASAusleiheBestaetigen = new javax.swing.JButton();
        jLASKundeAnzeigen = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAASKundeAllAusleihen = new javax.swing.JTextArea();
        jPOffeneAusleihenGesamt = new javax.swing.JPanel();
        jPOffeneAusleihenKunde = new javax.swing.JPanel();
        jPRueckgabe = new javax.swing.JPanel();
        jPKunden = new javax.swing.JPanel();
        jTPKundenMenu = new javax.swing.JTabbedPane();
        jPKundeBearbeiten = new javax.swing.JPanel();
        jPNeuerKunde = new javax.swing.JPanel();
        jTFAnrede = new javax.swing.JTextField();
        jTFVorname = new javax.swing.JTextField();
        jTFNachname = new javax.swing.JTextField();
        jTFStrasse = new javax.swing.JTextField();
        jLKundennummerAuto = new javax.swing.JLabel();
        jLKundennummerText = new javax.swing.JLabel();
        jLAnrede = new javax.swing.JLabel();
        jLVorname = new javax.swing.JLabel();
        jLNachname = new javax.swing.JLabel();
        jLStrasse = new javax.swing.JLabel();
        jLPlz = new javax.swing.JLabel();
        jTFPlz = new javax.swing.JTextField();
        jTFOrt = new javax.swing.JTextField();
        jTFGeburtsdatum = new javax.swing.JTextField();
        jLOrt = new javax.swing.JLabel();
        jLGeburtsdatum = new javax.swing.JLabel();
        jBKundeSpeichern = new javax.swing.JButton();
        jPKundeLoeschen = new javax.swing.JPanel();
        jLKundennummer = new javax.swing.JLabel();
        jTFKundennummer = new javax.swing.JTextField();
        jBSuchen = new javax.swing.JButton();
        jLVornameAuto = new javax.swing.JLabel();
        jLNachnameAuto = new javax.swing.JLabel();
        jLAdresseAuto = new javax.swing.JLabel();
        jLAusleiheStatusAuto = new javax.swing.JLabel();
        jBLoeschen = new javax.swing.JButton();
        jPMedien = new javax.swing.JPanel();
        jPFooter = new javax.swing.JPanel();
        jLDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        jTPHauptmenue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTPAusleihenMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLASKundenNummer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLASKundenNummer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLASKundenNummer.setText("Kunden-Nr.:");

        jLASArtikelNummer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLASArtikelNummer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLASArtikelNummer.setText("Artikel-Nr.:");

        jTFASKundenNummer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTFASArtikelNummer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jBASAusleiheBestaetigen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBASAusleiheBestaetigen.setText("Ausleihe Bestätigen");

        jLASKundeAnzeigen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLASKundeAnzeigen.setText("Aktuelle Ausleihen für Kunde: <Kunde Anzeigen>");

        jTAASKundeAllAusleihen.setColumns(20);
        jTAASKundeAllAusleihen.setRows(5);
        jScrollPane1.setViewportView(jTAASKundeAllAusleihen);

        javax.swing.GroupLayout jPAusleiheStartenLayout = new javax.swing.GroupLayout(jPAusleiheStarten);
        jPAusleiheStarten.setLayout(jPAusleiheStartenLayout);
        jPAusleiheStartenLayout.setHorizontalGroup(
            jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAusleiheStartenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLASArtikelNummer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLASKundenNummer, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBASAusleiheBestaetigen, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jTFASKundenNummer)
                    .addComponent(jTFASArtikelNummer))
                .addGap(73, 73, 73)
                .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLASKundeAnzeigen, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPAusleiheStartenLayout.setVerticalGroup(
            jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAusleiheStartenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLASKundeAnzeigen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPAusleiheStartenLayout.createSequentialGroup()
                        .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFASKundenNummer)
                            .addComponent(jLASKundenNummer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPAusleiheStartenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLASArtikelNummer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFASArtikelNummer, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jBASAusleiheBestaetigen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 401, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTPAusleihenMenu.addTab("Ausleihe Starten...", jPAusleiheStarten);

        javax.swing.GroupLayout jPOffeneAusleihenGesamtLayout = new javax.swing.GroupLayout(jPOffeneAusleihenGesamt);
        jPOffeneAusleihenGesamt.setLayout(jPOffeneAusleihenGesamtLayout);
        jPOffeneAusleihenGesamtLayout.setHorizontalGroup(
            jPOffeneAusleihenGesamtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1014, Short.MAX_VALUE)
        );
        jPOffeneAusleihenGesamtLayout.setVerticalGroup(
            jPOffeneAusleihenGesamtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        jTPAusleihenMenu.addTab("Offene Ausleihen Gesamt", jPOffeneAusleihenGesamt);

        javax.swing.GroupLayout jPOffeneAusleihenKundeLayout = new javax.swing.GroupLayout(jPOffeneAusleihenKunde);
        jPOffeneAusleihenKunde.setLayout(jPOffeneAusleihenKundeLayout);
        jPOffeneAusleihenKundeLayout.setHorizontalGroup(
            jPOffeneAusleihenKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1014, Short.MAX_VALUE)
        );
        jPOffeneAusleihenKundeLayout.setVerticalGroup(
            jPOffeneAusleihenKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );

        jTPAusleihenMenu.addTab("Offene Ausleihen für Kunde", jPOffeneAusleihenKunde);

        javax.swing.GroupLayout jPAusleihenLayout = new javax.swing.GroupLayout(jPAusleihen);
        jPAusleihen.setLayout(jPAusleihenLayout);
        jPAusleihenLayout.setHorizontalGroup(
            jPAusleihenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPAusleihenMenu)
        );
        jPAusleihenLayout.setVerticalGroup(
            jPAusleihenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPAusleihenMenu)
        );

        jTPHauptmenue.addTab("Ausleihen", jPAusleihen);

        javax.swing.GroupLayout jPRueckgabeLayout = new javax.swing.GroupLayout(jPRueckgabe);
        jPRueckgabe.setLayout(jPRueckgabeLayout);
        jPRueckgabeLayout.setHorizontalGroup(
            jPRueckgabeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        jPRueckgabeLayout.setVerticalGroup(
            jPRueckgabeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );

        jTPHauptmenue.addTab("Rückgabe", jPRueckgabe);

        jTPKundenMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPKundeBearbeitenLayout = new javax.swing.GroupLayout(jPKundeBearbeiten);
        jPKundeBearbeiten.setLayout(jPKundeBearbeitenLayout);
        jPKundeBearbeitenLayout.setHorizontalGroup(
            jPKundeBearbeitenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPKundeBearbeitenLayout.setVerticalGroup(
            jPKundeBearbeitenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTPKundenMenu.addTab("Kunde Bearbeiten", jPKundeBearbeiten);

        jTFAnrede.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFAnrede.setText("Anrede");
        jTFAnrede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFAnredeActionPerformed(evt);
            }
        });

        jTFVorname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFVorname.setText("Vorname");

        jTFNachname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFNachname.setText("Nachname");

        jTFStrasse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFStrasse.setText("Straße");

        jLKundennummerAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLKundennummerAuto.setText("<autoKDNR>");

        jLKundennummerText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLKundennummerText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLKundennummerText.setText("Kundennummer");

        jLAnrede.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLAnrede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLAnrede.setText("Anrede");

        jLVorname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLVorname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLVorname.setText("Vorname");

        jLNachname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNachname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLNachname.setText("Nachname");

        jLStrasse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLStrasse.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLStrasse.setText("Straße");

        jLPlz.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLPlz.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLPlz.setText("Postleitzahl");

        jTFPlz.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFPlz.setText("Postleitzahl");

        jTFOrt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFOrt.setText("Ort");

        jTFGeburtsdatum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFGeburtsdatum.setText("jTextField7");

        jLOrt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLOrt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLOrt.setText("Ort");

        jLGeburtsdatum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLGeburtsdatum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLGeburtsdatum.setText("Geburtsdatum");

        jBKundeSpeichern.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBKundeSpeichern.setText("Kunde speichern");

        javax.swing.GroupLayout jPNeuerKundeLayout = new javax.swing.GroupLayout(jPNeuerKunde);
        jPNeuerKunde.setLayout(jPNeuerKundeLayout);
        jPNeuerKundeLayout.setHorizontalGroup(
            jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNeuerKundeLayout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLPlz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLAnrede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLVorname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLNachname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLStrasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLOrt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLGeburtsdatum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLKundennummerText, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBKundeSpeichern, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTFGeburtsdatum)
                        .addComponent(jTFOrt)
                        .addComponent(jLKundennummerAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addComponent(jTFStrasse)
                        .addComponent(jTFNachname)
                        .addComponent(jTFVorname)
                        .addComponent(jTFAnrede)
                        .addComponent(jTFPlz)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPNeuerKundeLayout.setVerticalGroup(
            jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPNeuerKundeLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLKundennummerText, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLKundennummerAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLAnrede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFAnrede, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLVorname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFVorname, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLNachname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFNachname, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFStrasse, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLStrasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLPlz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFPlz, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLOrt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFOrt, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPNeuerKundeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTFGeburtsdatum, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jLGeburtsdatum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jBKundeSpeichern, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPKundenMenu.addTab("Neuen Kunden anlegen...", jPNeuerKunde);

        jLKundennummer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLKundennummer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLKundennummer.setText("Kundennummer");

        jTFKundennummer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFKundennummer.setText("Kundennummer");

        jBSuchen.setBackground(new java.awt.Color(0, 153, 255));
        jBSuchen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBSuchen.setText("Suchen");

        jLVornameAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLVornameAuto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLVornameAuto.setText("<auto Vorname>");

        jLNachnameAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLNachnameAuto.setText("<auto Nachname>");

        jLAdresseAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLAdresseAuto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLAdresseAuto.setText("<auto Adresse>");

        jLAusleiheStatusAuto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLAusleiheStatusAuto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLAusleiheStatusAuto.setText("<auto Ausleihstatus: wenn ausleihe, dann nicht löschbar");

        jBLoeschen.setBackground(new java.awt.Color(255, 0, 0));
        jBLoeschen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBLoeschen.setText("Löschen");

        javax.swing.GroupLayout jPKundeLoeschenLayout = new javax.swing.GroupLayout(jPKundeLoeschen);
        jPKundeLoeschen.setLayout(jPKundeLoeschenLayout);
        jPKundeLoeschenLayout.setHorizontalGroup(
            jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPKundeLoeschenLayout.createSequentialGroup()
                .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBLoeschen, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPKundeLoeschenLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLAusleiheStatusAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPKundeLoeschenLayout.createSequentialGroup()
                            .addGap(99, 99, 99)
                            .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLAdresseAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPKundeLoeschenLayout.createSequentialGroup()
                                    .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLVornameAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                        .addComponent(jLKundennummer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTFKundennummer)
                                        .addComponent(jLNachnameAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBSuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );
        jPKundeLoeschenLayout.setVerticalGroup(
            jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPKundeLoeschenLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBSuchen, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jTFKundennummer)
                    .addComponent(jLKundennummer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPKundeLoeschenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLVornameAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jLNachnameAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAdresseAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLAusleiheStatusAuto, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBLoeschen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );

        jTPKundenMenu.addTab("Kunde Löschen", jPKundeLoeschen);

        javax.swing.GroupLayout jPKundenLayout = new javax.swing.GroupLayout(jPKunden);
        jPKunden.setLayout(jPKundenLayout);
        jPKundenLayout.setHorizontalGroup(
            jPKundenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPKundenMenu)
        );
        jPKundenLayout.setVerticalGroup(
            jPKundenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPKundenMenu)
        );

        jTPHauptmenue.addTab("Kunden", jPKunden);

        javax.swing.GroupLayout jPMedienLayout = new javax.swing.GroupLayout(jPMedien);
        jPMedien.setLayout(jPMedienLayout);
        jPMedienLayout.setHorizontalGroup(
            jPMedienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        jPMedienLayout.setVerticalGroup(
            jPMedienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );

        jTPHauptmenue.addTab("Medien", jPMedien);

        jPFooter.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLDate.setText(Controls.getAktDatumDDMMYYYY());

        jLabel1.setText("  Videothek - Verwaltungssoftware Version 1.0");

        javax.swing.GroupLayout jPFooterLayout = new javax.swing.GroupLayout(jPFooter);
        jPFooter.setLayout(jPFooterLayout);
        jPFooterLayout.setHorizontalGroup(
            jPFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFooterLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPFooterLayout.setVerticalGroup(
            jPFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLDate, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPHauptmenue)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTPHauptmenue)
                .addGap(18, 18, 18)
                .addComponent(jPFooter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFAnredeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFAnredeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFAnredeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Videothek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Videothek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Videothek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Videothek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Videothek().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBASAusleiheBestaetigen;
    private javax.swing.JButton jBKundeSpeichern;
    private javax.swing.JButton jBLoeschen;
    private javax.swing.JButton jBSuchen;
    private javax.swing.JLabel jLASArtikelNummer;
    private javax.swing.JLabel jLASKundeAnzeigen;
    private javax.swing.JLabel jLASKundenNummer;
    private javax.swing.JLabel jLAdresseAuto;
    private javax.swing.JLabel jLAnrede;
    private javax.swing.JLabel jLAusleiheStatusAuto;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLGeburtsdatum;
    private javax.swing.JLabel jLKundennummer;
    private javax.swing.JLabel jLKundennummerAuto;
    private javax.swing.JLabel jLKundennummerText;
    private javax.swing.JLabel jLNachname;
    private javax.swing.JLabel jLNachnameAuto;
    private javax.swing.JLabel jLOrt;
    private javax.swing.JLabel jLPlz;
    private javax.swing.JLabel jLStrasse;
    private javax.swing.JLabel jLVorname;
    private javax.swing.JLabel jLVornameAuto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPAusleiheStarten;
    private javax.swing.JPanel jPAusleihen;
    private javax.swing.JPanel jPFooter;
    private javax.swing.JPanel jPKundeBearbeiten;
    private javax.swing.JPanel jPKundeLoeschen;
    private javax.swing.JPanel jPKunden;
    private javax.swing.JPanel jPMedien;
    private javax.swing.JPanel jPNeuerKunde;
    private javax.swing.JPanel jPOffeneAusleihenGesamt;
    private javax.swing.JPanel jPOffeneAusleihenKunde;
    private javax.swing.JPanel jPRueckgabe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAASKundeAllAusleihen;
    private javax.swing.JTextField jTFASArtikelNummer;
    private javax.swing.JTextField jTFASKundenNummer;
    private javax.swing.JTextField jTFAnrede;
    private javax.swing.JTextField jTFGeburtsdatum;
    private javax.swing.JTextField jTFKundennummer;
    private javax.swing.JTextField jTFNachname;
    private javax.swing.JTextField jTFOrt;
    private javax.swing.JTextField jTFPlz;
    private javax.swing.JTextField jTFStrasse;
    private javax.swing.JTextField jTFVorname;
    private javax.swing.JTabbedPane jTPAusleihenMenu;
    private javax.swing.JTabbedPane jTPHauptmenue;
    private javax.swing.JTabbedPane jTPKundenMenu;
    // End of variables declaration//GEN-END:variables
}
