/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.AbstractObject;
import domen.Lekar;
import domen.Pacijent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kont.Kontroler;
import modeli.ModelTabeleLekara;
import modeli.ModelTabeleLekova;
import modeli.ModelTabelePacijenta;

/**
 *
 * @author Dusan
 */
public class PretragaPacijenta extends javax.swing.JFrame {

    GlavnaForma gf;
    ModelTabelePacijenta mtp;

    public PretragaPacijenta(GlavnaForma gf) {
        initComponents();
        this.setTitle("Pregled pacijenata");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.gf = gf;
        try {
            srediFormu();
        } catch (Exception ex) {
            Logger.getLogger(PretragaPacijenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PretragaPacijenta() {
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPacijenata = new javax.swing.JTable();
        btnIzmeni = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPretraga = new javax.swing.JTextField();
        btnPretrazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tabela pacijenata:");

        tabelaPacijenata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaPacijenata);

        btnIzmeni.setText("Izmeni pacijenta");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi pacijenta");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        jLabel2.setText("Filter prema delu imena pacijenta:");

        btnPretrazi.setText("Pretrazi");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPretrazi, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretrazi))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzmeni)
                    .addComponent(btnObrisi))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiActionPerformed
        mtp = (ModelTabelePacijenta) tabelaPacijenata.getModel();

        int index = tabelaPacijenata.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Sistem ne mo??e da zapamti novog pacijenta!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(rootPane, "Izaberite voza??a kog ??elite da obri??ete!", "Gre??ka", JOptionPane.INFORMATION_MESSAGE);

            return;
        } else {
            try {
                Pacijent izabraniPacijent = (Pacijent) mtp.getListaPacijenata().get(index);
                JOptionPane.showMessageDialog(rootPane, "Sistem je u??itao podatke o pacijentu!", "Uspe??no", JOptionPane.INFORMATION_MESSAGE);
                int odluka = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da ??elite da obri??ete izabranog pacijenta " + izabraniPacijent.toString() + "?", "", JOptionPane.WARNING_MESSAGE);
                if (odluka == 0) {
                    Pacijent pacijent = (Pacijent) Kontroler.vratiInstancu().obrisiPacijenta(izabraniPacijent);
                    JOptionPane.showMessageDialog(rootPane, "Sistem je obrisao pacijenta: " + pacijent.toString(), "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    srediTabelu();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Sistem ne mo??e da obri??e pacijenta!", "Gre??ka", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_btnObrisiActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        try {
            String pretraga = txtPretraga.getText().trim();

            if (pretraga.isEmpty()) {

                List<AbstractObject> listaPacijenata = Kontroler.vratiInstancu().vratiPacijente();
                mtp = (ModelTabelePacijenta) tabelaPacijenata.getModel();
                mtp.setListaPacijenata(listaPacijenata);

            } else {

                List<AbstractObject> listaPacijenata = Kontroler.vratiInstancu().pretraziPacijente(pretraga);
                if (listaPacijenata.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Sistem je na??ao pacijente po zadatoj vrednosti.", "Uspe??no", JOptionPane.INFORMATION_MESSAGE);
                    mtp = (ModelTabelePacijenta) tabelaPacijenata.getModel();
                    mtp.setListaPacijenata(listaPacijenata);
                } else {
                    JOptionPane.showMessageDialog(this, "Sistem ne mo??e da obri??e pacijenta!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gre??ka prilikom pretrage!");
        }
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniActionPerformed
        int index = tabelaPacijenata.getSelectedRow();

        if (index != -1) {
            try {
                Pacijent p = (Pacijent) mtp.getListaPacijenata().get(index);
                JOptionPane.showMessageDialog(rootPane, "Sistem je u??itao podatke o pacijentu!", "Uspe??no", JOptionPane.INFORMATION_MESSAGE);
                UnosNovogPacijenta unp = new UnosNovogPacijenta(this, p);
                unp.setVisible(true);
                this.setVisible(false);
            } catch (Exception ex) {
                Logger.getLogger(PretragaPacijenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne mo??e da zapamti novog pacijenta!", "Gre??ka", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnIzmeniActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PretragaPacijenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PretragaPacijenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PretragaPacijenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PretragaPacijenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PretragaPacijenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaPacijenata;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration//GEN-END:variables

    private void srediTabelu() throws Exception {
        List<AbstractObject> listaPacijenata = Kontroler.vratiInstancu().vratiPacijente();
        mtp = new ModelTabelePacijenta((ArrayList<AbstractObject>) (listaPacijenata));
        tabelaPacijenata.setModel(mtp);
    }

    private void srediFormu() throws Exception {
        srediTabelu();
    }
}
