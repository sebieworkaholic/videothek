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
        jPAusleihe = new javax.swing.JPanel();
        jPRueckgabe = new javax.swing.JPanel();
        jPKunden = new javax.swing.JPanel();
        jPMedien = new javax.swing.JPanel();
        jPFooter = new javax.swing.JPanel();
        jLDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        jTPHauptmenue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPAusleiheLayout = new javax.swing.GroupLayout(jPAusleihe);
        jPAusleihe.setLayout(jPAusleiheLayout);
        jPAusleiheLayout.setHorizontalGroup(
            jPAusleiheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        jPAusleiheLayout.setVerticalGroup(
            jPAusleiheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );

        jTPHauptmenue.addTab("Ausleihen", jPAusleihe);

        javax.swing.GroupLayout jPRueckgabeLayout = new javax.swing.GroupLayout(jPRueckgabe);
        jPRueckgabe.setLayout(jPRueckgabeLayout);
        jPRueckgabeLayout.setHorizontalGroup(
            jPRueckgabeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        jPRueckgabeLayout.setVerticalGroup(
            jPRueckgabeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );

        jTPHauptmenue.addTab("Rückgabe", jPRueckgabe);

        javax.swing.GroupLayout jPKundenLayout = new javax.swing.GroupLayout(jPKunden);
        jPKunden.setLayout(jPKundenLayout);
        jPKundenLayout.setHorizontalGroup(
            jPKundenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1019, Short.MAX_VALUE)
        );
        jPKundenLayout.setVerticalGroup(
            jPKundenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
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
            .addGap(0, 669, Short.MAX_VALUE)
        );

        jTPHauptmenue.addTab("Medien", jPMedien);

        jLDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLDate.setText(AktuellesDatum.getAktuellesDatumDDMMYYYY());

        javax.swing.GroupLayout jPFooterLayout = new javax.swing.GroupLayout(jPFooter);
        jPFooter.setLayout(jPFooterLayout);
        jPFooterLayout.setHorizontalGroup(
            jPFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPFooterLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPFooterLayout.setVerticalGroup(
            jPFooterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLDate, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTPHauptmenue)
            .addComponent(jPFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTPHauptmenue, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPFooter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLDate;
    private javax.swing.JPanel jPAusleihe;
    private javax.swing.JPanel jPFooter;
    private javax.swing.JPanel jPKunden;
    private javax.swing.JPanel jPMedien;
    private javax.swing.JPanel jPRueckgabe;
    private javax.swing.JTabbedPane jTPHauptmenue;
    // End of variables declaration//GEN-END:variables
}
