/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domen.Lek;
import exception.ValidacijaException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kont.Kontroler;

/**
 *
 * @author Dusan
 */
public class UnosNovogLeka extends javax.swing.JFrame {

    Lek lek;

    public UnosNovogLeka() {
        initComponents();
        txtLekID.setEnabled(false);
        this.setTitle("Unos leka");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lek = new Lek("0","","");
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
        txtLekID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLekNaziv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRokTrajanja = new javax.swing.JTextField();
        btnSacuvajLek = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Lek ID:");

        jLabel2.setText("Naziv:");

        jLabel3.setText("Rok trajanja:");

        btnSacuvajLek.setText("Sacuvaj Lek");
        btnSacuvajLek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajLekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtRokTrajanja))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLekID, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(txtLekNaziv))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btnSacuvajLek, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLekID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLekNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRokTrajanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnSacuvajLek)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajLekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajLekActionPerformed
        String lekID = txtLekID.getText();
        String nazivLeka = txtLekNaziv.getText();
        String rokTrajanja = txtRokTrajanja.getText();
        
        try {
            validacijaIPostavkaPodataka(nazivLeka, rokTrajanja);
        } catch (ValidacijaException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Neuspe??no", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Lek l;
        try {
            l = Kontroler.vratiInstancu().sacuvajLek(lek);
            JOptionPane.showMessageDialog(this, "Sistem je zapamtio novi lek:\nID: " + l.getLekID() + "\nNaziv leka: " +l.getNaziv() + "\nRok trajanja: " + l.getRokTrajanja(), "Uspe??no", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            System.out.println("Gre??ka prilikom ??uvanja leka!");
        }
    }//GEN-LAST:event_btnSacuvajLekActionPerformed

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
            java.util.logging.Logger.getLogger(UnosNovogLeka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UnosNovogLeka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UnosNovogLeka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UnosNovogLeka.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UnosNovogLeka().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSacuvajLek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtLekID;
    private javax.swing.JTextField txtLekNaziv;
    private javax.swing.JTextField txtRokTrajanja;
    // End of variables declaration//GEN-END:variables

    private void validacijaIPostavkaPodataka(String nazivLeka, String rokTrajanja) throws ValidacijaException {
        if (nazivLeka.isEmpty() || rokTrajanja.isEmpty()) {
            throw new ValidacijaException("Sistem ne mo??e da zapamti lek!");

        }
        lek.setNaziv(nazivLeka);
        lek.setRokTrajanja(rokTrajanja);
    }
}
