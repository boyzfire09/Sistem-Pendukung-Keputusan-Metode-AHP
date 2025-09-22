/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta8;
import java.sql.*;
import combobox.Combobox;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Cursor;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import scrollbar.ScrollBarWin11UI;
import javax.swing.table.DefaultTableModel;
import static ta8.koneksi.koneksiDb;

public class TambahData extends javax.swing.JPanel {
    Connection koneksi = null;
    private DefaultTableModel tabmode;
    private javax.swing.JDialog parentDialog = null;

public void setParentDialog(javax.swing.JDialog dialog) {
    this.parentDialog = dialog;
}
   
    public TambahData() {
        UIDefaults ui = UIManager.getDefaults();
        ui.put("ScrollBarUI", ScrollBarWin11UI.class.getCanonicalName());
        initComponents();
        btntambah.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        btnedit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
    }
    
     public void setDataTabel(String kode_kaset, String nama_kaset_game, String genre, String kondisi_kaset, String mode_game, String tahun_rilis){
        tkode.setText(kode_kaset);
        tnama.setText(nama_kaset_game);
        cgenre.setSelectedItem(genre);
        ckondisi.setSelectedItem(kondisi_kaset);
        cmode.setSelectedItem(mode_game);
        trilis.setText(tahun_rilis);
    }
    
    protected void kosong(){
        tkode.setText("");
        tnama.setText("");
        cgenre.setSelectedIndex(0);
        ckondisi.setSelectedIndex(0);
        cmode.setSelectedIndex(0);
        trilis.setText("");
    }
    
     private void insertMenuDataKaset(){
         try {
        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_game", "root", "");
        String sql = "INSERT INTO kaset_game (kode_kaset, nama_kaset_game, genre, kondisi_kaset, mode_game, tahun_rilis) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = koneksi.prepareStatement(sql);
                ps.setString(1, tkode.getText());
                ps.setString(2, tnama.getText());
                ps.setString(3, cgenre.getSelectedItem().toString());
                ps.setString(4, ckondisi.getSelectedItem().toString());
                ps.setString(5, cmode.getSelectedItem().toString()); 
                ps.setString(6, trilis.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                kosong();
                tkode.requestFocus();
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan Biodata"+e);
            }
    }
     
     
    private void editMenuDataKaset(){
    try{
        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_game", "root", "");
        String sql = "UPDATE kaset_game SET nama_kaset_game=?, genre=?, kondisi_kaset=?, mode_game=?, tahun_rilis=? WHERE kode_kaset=?";
        PreparedStatement stat = koneksi.prepareStatement(sql);
        stat.setString(1, tnama.getText());
        stat.setString(2, cgenre.getSelectedItem().toString());
        stat.setString(3, ckondisi.getSelectedItem().toString());
        stat.setString(4, cmode.getSelectedItem().toString());
        stat.setString(5, trilis.getText());
        stat.setString(6, tkode.getText()); 
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        kembaliKeMenuDataKaset();
    }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e);
    }
}

    private void kembaliKeMenuDataKaset() {
    Window window = SwingUtilities.getWindowAncestor(this);
    if (window instanceof JDialog) {
        ((JDialog) window).dispose();  
    }
}
     
     private Connection koneksi(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_game","root","");
        return koneksi;
        
    }catch (Exception e){
        System.out.println("Koneksi Gagal:" + e.getMessage());
        return null;
    }
}
     
    public void setDataKaset(String kode, String nama, String genre, String kondisi, String mode, String rilis) {
    tkode.setText(kode);
    tnama.setText(nama);
    cgenre.setSelectedItem(genre);
    ckondisi.setSelectedItem(kondisi);
    cmode.setSelectedItem(mode);
    trilis.setText(rilis);
    tkode.setEditable(false); 
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelBlur1 = new ta8.PanelBlur();
        btntambah = new ta8.Buttonwarna();
        btnedit = new ta8.Buttonwarna();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tkode = new ta8.RoundTextField();
        tnama = new ta8.RoundTextField();
        cgenre = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmode = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        ckondisi = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        trilis = new ta8.RoundTextField();
        panelBlur2 = new ta8.PanelBlur();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btntambah.setText("Tambah");
        btntambah.setColorEnd(new java.awt.Color(0, 51, 51));
        btntambah.setColorStart(new java.awt.Color(153, 153, 153));
        btntambah.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btntambah.setHoverEnd(new java.awt.Color(0, 0, 102));
        btntambah.setHoverStart(new java.awt.Color(0, 204, 204));
        btntambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntambahMouseClicked(evt);
            }
        });
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnedit.setText("Ubah");
        btnedit.setColorEnd(new java.awt.Color(0, 51, 51));
        btnedit.setColorStart(new java.awt.Color(153, 153, 153));
        btnedit.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        btnedit.setHoverEnd(new java.awt.Color(0, 0, 102));
        btnedit.setHoverStart(new java.awt.Color(0, 204, 204));
        btnedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btneditMouseClicked(evt);
            }
        });
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Kode Kaset");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Kaset Game");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Genre");

        tkode.setForeground(new java.awt.Color(255, 255, 255));
        tkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkodeActionPerformed(evt);
            }
        });

        tnama.setForeground(new java.awt.Color(255, 255, 255));
        tnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnamaActionPerformed(evt);
            }
        });

        cgenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Genre ~", "Action, RPG, Adventure", "Adventure, RPG", "Shooter, Action", "Action, Horror, Adventure", "Adventure, Puzzle, Simulasi", "Sports, Racing, Simulasi", "Sport, Simulasi", "Horror, Puzzle, Simulasi", "Simulasi, Adventure", " ", " ", " " }));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kondisi Kaset");

        cmode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Mode Game ~", "Single Player", "Multi Player", " ", " ", " ", " ", " ", " ", " " }));
        cmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmodeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mode Game");

        ckondisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Kondisi ~", "Baru", "Baik", "Bekas", " " }));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tahun Rilis");

        trilis.setForeground(new java.awt.Color(255, 255, 255));
        trilis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trilisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBlur1Layout = new javax.swing.GroupLayout(panelBlur1);
        panelBlur1.setLayout(panelBlur1Layout);
        panelBlur1Layout.setHorizontalGroup(
            panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlur1Layout.createSequentialGroup()
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addGap(98, 98, 98)
                        .addComponent(tkode, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addGap(53, 53, 53)
                        .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(135, 135, 135)
                        .addComponent(cgenre, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(ckondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmode, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trilis, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panelBlur1Layout.setVerticalGroup(
            panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlur1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBlur1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))
                            .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGap(18, 18, 18)
                        .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBlur1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel4))
                            .addComponent(cgenre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(panelBlur1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ckondisi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBlur1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(trilis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.add(panelBlur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 760, 280));

        jLabel7.setFont(new java.awt.Font("Baskerville Old Face", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 255, 51));
        jLabel7.setText("- INPUT DATA KASET -");

        javax.swing.GroupLayout panelBlur2Layout = new javax.swing.GroupLayout(panelBlur2);
        panelBlur2.setLayout(panelBlur2Layout);
        panelBlur2Layout.setHorizontalGroup(
            panelBlur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBlur2Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        panelBlur2Layout.setVerticalGroup(
            panelBlur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlur2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        );

        jPanel2.add(panelBlur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 410, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgtambahdata.jpg"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkodeActionPerformed

    private void tnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnamaActionPerformed

    private void cmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmodeActionPerformed

    private void btntambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseClicked
        // TODO add your handling code here:
         if(!tnama.getText().equals("") && cgenre.getSelectedIndex() != 0
                && ckondisi.getSelectedIndex() != 0 && cmode.getSelectedIndex() != 0 && !trilis.getText().equals("")){    
            insertMenuDataKaset();
            Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    } else {
        
        JOptionPane.showMessageDialog(null, 
            "Mohon isi semua kolom isian pada form !", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btntambahMouseClicked

    private void btneditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btneditMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_btneditMouseClicked

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        editMenuDataKaset();
        kembaliKeMenuDataKaset();
    }//GEN-LAST:event_btneditActionPerformed

    private void trilisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trilisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_trilisActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ta8.Buttonwarna btnedit;
    private ta8.Buttonwarna btntambah;
    private javax.swing.JComboBox<String> cgenre;
    private javax.swing.JComboBox<String> ckondisi;
    private javax.swing.JComboBox<String> cmode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private ta8.PanelBlur panelBlur1;
    private ta8.PanelBlur panelBlur2;
    private ta8.RoundTextField tkode;
    private ta8.RoundTextField tnama;
    private ta8.RoundTextField trilis;
    // End of variables declaration//GEN-END:variables
}
