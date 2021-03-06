/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/**
 *
 * @author Hafizh
 */
public class ubahTask extends javax.swing.JFrame {

    private Statement stat;
    private ResultSet res;
    private Connection con;
    private String id_tugas;
    private String tipe;
    
    /**
     * Creates new form CreateTask
     */
    public ubahTask() {
        initComponents();
        Date d = new Date();
        jDateChooser2.setDate(d);
        koneksi();
        status();
    }
    
    private void koneksi(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pengingat_tugas", "root", "");
            stat=con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void status(){ 
        DefaultComboBoxModel cb = new DefaultComboBoxModel();
        jComboBox1.setModel(cb);
        try{
            // Mengambil data dari database
            res = stat.executeQuery("SELECT * FROM `status`");

            while (res.next())
            {
                // Mengambil data dari database berdasarkan nama kolom pada tabel
                // Lalu di tampilkan ke dalam JComboBox
                jComboBox1.addItem(res.getString("nama_status"));
            }
        }catch (Exception e){
            Logger.getLogger(buatTask.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void setIdTugas(String id) {
        id_tugas = id;
    }
    public void setNamaTugas(String nama) {
        jTextField_namaTugas.setText(nama);
    }
    
    public void setDeadline(String deadline) throws ParseException {
        String dl = deadline;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dl);
        jDateChooser2.setDate(date);
    }
    
    public void setStatus(String status) {
        for (int i=0; i < 3; i++) {
            if (jComboBox1.getItemAt(i).equals(status)) {
                jComboBox1.setSelectedIndex(i);
            }
        }
    }
    
    public void setTipe(String nama_tipe) {
        if (nama_tipe.equals("Perkuliahan")) {
            tipe = "1";
        } else if (nama_tipe.equals("Organisasi")) {
            tipe = "2";
        } else if (nama_tipe.equals("Pribadi")) {
            tipe = "3";
        }
    }
    
    public void UpdateData() {
        String nama_tugas = jTextField_namaTugas.getText();
        // set id status
        String status = jComboBox1.getSelectedItem().toString();
        if(status.equals("Belum dikerjakan")) {
            status = "1";
        } else if(status.equals("Belum selesai")) {
            status = "2";
        } else if(status.equals("Selesai")) {
            status = "3";
        } else {
            status = "0";
        }
        // ambil tanggal
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = sdf.format(jDateChooser2.getDate());
        
        String sql = "UPDATE tugas SET nama_tugas = '" + nama_tugas + "', tipe = '" + tipe + "', status = '" + status + "', deadline = '" + deadline + "' WHERE id_tugas = '" + id_tugas + "'";
        try {
            stat = con.createStatement();
            int k = stat.executeUpdate(sql);
            
            if (k==1) {
                JOptionPane.showMessageDialog(null, "Data tugas berhasil diubah!");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data tugas gagal diubah!");
            }
        } catch (Exception e) {
            Logger.getLogger(ubahTask.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void DeleteData() {
        String sql = "DELETE FROM tugas WHERE id_tugas = '" + id_tugas + "'";
        
        try {
            stat = con.createStatement();
            int k = stat.executeUpdate(sql);
            
            if (k==1) {
                JOptionPane.showMessageDialog(null, "Data tugas berhasil dihapus!");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Data tugas gagal dihapus!");
            }
        } catch (Exception e) {
            Logger.getLogger(ubahTask.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jTextField_namaTugas = new javax.swing.JTextField();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(235, 231, 255));

        jTextField_namaTugas.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jTextField_namaTugas.setText("Praktikum 2 Pemograman Web");
        jTextField_namaTugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_namaTugasActionPerformed(evt);
            }
        });

        jDateChooser2.setDateFormatString("dd MMM, yyyy");
        jDateChooser2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel1.setText("Nama Tugas");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel2.setText("Deadline");

        jButton1.setBackground(new java.awt.Color(209, 109, 106));
        jButton1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(88, 67, 190));
        jButton2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(209, 109, 106));
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setBackground(new java.awt.Color(246, 244, 255));
        jComboBox1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(88, 67, 190));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Belum Selesai", "Sudah Selesai" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_namaTugas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_namaTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jDateChooser2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UpdateData();
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField_namaTugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_namaTugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_namaTugasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DeleteData();
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ubahTask().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextField_namaTugas;
    // End of variables declaration//GEN-END:variables
}
