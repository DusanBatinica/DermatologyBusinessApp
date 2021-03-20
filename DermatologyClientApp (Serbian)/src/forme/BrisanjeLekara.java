package forme;

import domen.AbstractObject;
import domen.Lek;
import domen.Lekar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kont.Kontroler;
import modeli.ModelTabeleLekara;
import modeli.ModelTabeleLekova;

public class BrisanjeLekara extends javax.swing.JFrame {

    GlavnaForma gf;
    ModelTabeleLekara mtl;

    public BrisanjeLekara(GlavnaForma glavna) {
        initComponents();
        this.setTitle("Brisanje lekara");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.gf = glavna;
        try {
            srediFormu();
        } catch (Exception ex) {
            Logger.getLogger(BrisanjeLeka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BrisanjeLekara() {
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
        tabelaLekara = new javax.swing.JTable();
        btnObrisiLekara = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtPretraga = new javax.swing.JTextField();
        btnPretrazi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tabela lekara:");

        tabelaLekara.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaLekara);

        btnObrisiLekara.setText("Obrisi lekara");
        btnObrisiLekara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiLekaraActionPerformed(evt);
            }
        });

        jLabel2.setText("Filter prema delu imenu lekara:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(btnObrisiLekara, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPretrazi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretrazi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnObrisiLekara)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnObrisiLekaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiLekaraActionPerformed
        mtl = (ModelTabeleLekara) tabelaLekara.getModel();

        int index = tabelaLekara.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Sistem ne može da učita lekara!", "Greška", JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(rootPane, "Izaberite vozača kog želite da obrišete!", "Greška", JOptionPane.INFORMATION_MESSAGE);

            return;
        } else {
            try {
                Lekar izabraniLekar = (Lekar) mtl.getListaLekara().get(index);
                JOptionPane.showMessageDialog(rootPane, "Sistem je učitao podatke o lekaru!", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                int odluka = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da obrišete izabranog lekara " + izabraniLekar.toString() + "?", "", JOptionPane.WARNING_MESSAGE);
                if (odluka == 0) {
                    Lekar lekar = (Lekar) Kontroler.vratiInstancu().obrisiLekara(izabraniLekar);
                    JOptionPane.showMessageDialog(rootPane, "Sistem je obrisao lekara: " + lekar.toString(), "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    srediTabelu();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, "Sistem ne može da obriše lekara!", "Greška", JOptionPane.ERROR_MESSAGE);

            }
        }
    }//GEN-LAST:event_btnObrisiLekaraActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        try {
            String pretraga = txtPretraga.getText().trim();

            if (pretraga.isEmpty()) {

                List<AbstractObject> listaLekara = Kontroler.vratiInstancu().vratiLekare();
                mtl = (ModelTabeleLekara) tabelaLekara.getModel();
                mtl.setListaLekova(listaLekara);

            } else {

                List<AbstractObject> listaLekara = Kontroler.vratiInstancu().pretraziLekare(pretraga);
                if (listaLekara.size() > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Sistem je našao lekare po zadatoj vrednosti.", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
                    mtl = (ModelTabeleLekara) tabelaLekara.getModel();
                    mtl.setListaLekova(listaLekara);
                } else {
                    JOptionPane.showMessageDialog(this, "Sistem ne može da obriše lekara!", "Greška", JOptionPane.ERROR_MESSAGE);
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška prilikom pretrage!");
        }

    }//GEN-LAST:event_btnPretraziActionPerformed

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
            java.util.logging.Logger.getLogger(BrisanjeLekara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BrisanjeLekara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BrisanjeLekara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BrisanjeLekara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BrisanjeLekara().setVisible(true);
            }
        });
    }

    private void srediFormu() throws Exception {
        srediTabelu();
    }

    private void srediTabelu() throws Exception {
        List<AbstractObject> listaLekara = Kontroler.vratiInstancu().vratiLekare();
        ModelTabeleLekara mtll = new ModelTabeleLekara((ArrayList<AbstractObject>) listaLekara);
        tabelaLekara.setModel(mtll);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnObrisiLekara;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaLekara;
    private javax.swing.JTextField txtPretraga;
    // End of variables declaration//GEN-END:variables
}