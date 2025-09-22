/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta8;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static ta8.koneksi.koneksiDb;

public class MenuKriteria extends javax.swing.JPanel {
     Connection koneksi = null;
     private DefaultTableModel tabmode;
   
    public MenuKriteria() {
        initComponents();
        koneksi = koneksi();
         updateDataTabel();
         Locale locale = new Locale ("id","ID");
         Locale.setDefault(locale);
         
           javax.swing.table.JTableHeader header =  Tablekriteria.getTableHeader();
    javax.swing.table.DefaultTableCellRenderer renderer = 
        (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    
     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i <  Tablekriteria.getColumnCount(); i++) {
             Tablekriteria.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    
    }

    protected void kosong(){
        cbKriteria1.setSelectedIndex(0);
        cbKriteria2.setSelectedIndex(0);
        cbKriteria3.setSelectedIndex(0);
        cbKriteria4.setSelectedIndex(0);
    }
    
    protected void updateDataTabel(){
        Object[] Baris = {
            "Kode Kriteria",
            "Nama Kriteria",
            "Prioritas Kepentingan"
        };
        tabmode = new DefaultTableModel(null, Baris);
        Tablekriteria.setModel(tabmode);
        String sql = "SELECT * FROM kriteria ORDER BY kd_kriteria";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kd_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("prioritas_kepentingan");
                String[] data={a, b, c};
                tabmode.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void getDataTabel(){
        String sql = "SELECT nama_kriteria FROM kriteria ORDER BY kd_kriteria";
        int n = 1;
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_kriteria");
                if(n==1){
                    cbKriteria1.setSelectedItem(a);
                }else if(n==2){
                    cbKriteria2.setSelectedItem(a);
                }else if(n==3){
                    cbKriteria3.setSelectedItem(a);
                }else{
                    cbKriteria4.setSelectedItem(a);
                }   
                n++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void insertDataKriteria(){
        try{
            int n=1;
            do{
                String kepentingan, kodeKriteria;
                String sql = "INSERT INTO kriteria VALUES (?,?,?)";
                PreparedStatement stat = koneksi.prepareStatement(sql);
                kodeKriteria = "K"+n;
                stat.setString(1, kodeKriteria);
                if(n == 1){
                    stat.setString(2, cbKriteria1.getSelectedItem().toString());
                    kepentingan="Sangat Penting ke-1";
                }else if(n == 2){
                    stat.setString(2, cbKriteria2.getSelectedItem().toString());
                    kepentingan="Penting ke-2";
                }else if(n == 3){
                    stat.setString(2, cbKriteria3.getSelectedItem().toString());
                    kepentingan="Cukup Penting ke-3";
                }else{
                    stat.setString(2, cbKriteria4.getSelectedItem().toString());
                    kepentingan="Biasa ke-4";
                }
                stat.setString(3, kepentingan);
                stat.executeUpdate();
                
                n++;
            }while(n<=4);
            JOptionPane.showMessageDialog(null, "Kriteria Berhasil Disimpan");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }
    
    protected void hapusDataKriteria(){
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_CANCEL_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM kriteria";
            try{
                PreparedStatement stat = koneksi.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus ");
                kosong();
                updateDataTabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal DiHapus " + e);
            }
        }
    }
    
    protected void editDataKriteria(){
        try{
            int n=1;
            do{
                String kepentingan, kodeKriteria;
                String sql = "UPDATE kriteria set nama_kriteria=?, prioritas_kepentingan=? WHERE kd_kriteria=?";
                PreparedStatement stat = koneksi.prepareStatement(sql);
                if(n == 1){
                    stat.setString(1, cbKriteria1.getSelectedItem().toString());
                    kepentingan="Sangat Penting ke-1";
                }else if(n == 2){
                    stat.setString(1, cbKriteria2.getSelectedItem().toString());
                    kepentingan="Penting ke-2";
                }else if(n == 3){
                    stat.setString(1, cbKriteria3.getSelectedItem().toString());
                    kepentingan="Cukup Penting ke-3";
                }else{
                    stat.setString(1, cbKriteria4.getSelectedItem().toString());
                    kepentingan="Biasa ke-4";
                }
                stat.setString(2, kepentingan);
                kodeKriteria = "K"+n;
                stat.setString(3, kodeKriteria);
                stat.executeUpdate();
                n++;
            }while(n<=4);
            JOptionPane.showMessageDialog(null, "DATA Berhasil DiUbah");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal DiUbah "+e);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        blurWarna21 = new Blurwarna.BlurWarna2();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbKriteria1 = new javax.swing.JComboBox<>();
        cbKriteria2 = new javax.swing.JComboBox<>();
        cbKriteria3 = new javax.swing.JComboBox<>();
        cbKriteria4 = new javax.swing.JComboBox<>();
        btnsimpan = new ta8.Buttonwarna();
        btnedit = new ta8.Buttonwarna();
        btnhapus = new ta8.Buttonwarna();
        scrollPaneWin111 = new scrollbar.ScrollPaneWin11();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tablekriteria = new javax.swing.JTable();
        blurWarna22 = new Blurwarna.BlurWarna2();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setPreferredSize(new java.awt.Dimension(870, 520));

        jPanel2.setPreferredSize(new java.awt.Dimension(870, 520));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Kriteria Biasa ke-4");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Kriteria Cukup Penting ke-3");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Kriteria Penting ke-2 ");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText(" Kriteria Sangat Penting ke-1");

        cbKriteria1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Kriteria ~", "Rating", "Harga", "Target Pemain", "Platform" }));

        cbKriteria2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Kriteria ~", "Rating", "Harga", "Target Pemain", "Platform" }));

        cbKriteria3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Kriteria ~", "Rating", "Harga", "Target Pemain", "Platform" }));

        cbKriteria4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Kriteria ~", "Rating", "Harga", "Target Pemain", "Platform" }));

        javax.swing.GroupLayout blurWarna21Layout = new javax.swing.GroupLayout(blurWarna21);
        blurWarna21.setLayout(blurWarna21Layout);
        blurWarna21Layout.setHorizontalGroup(
            blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna21Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbKriteria3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 170, Short.MAX_VALUE)
                    .addComponent(cbKriteria2, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKriteria1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbKriteria4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        blurWarna21Layout.setVerticalGroup(
            blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKriteria1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKriteria2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKriteria3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(blurWarna21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbKriteria4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel2.add(blurWarna21, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 460, 160));

        btnsimpan.setText("Simpan");
        btnsimpan.setColorEnd(new java.awt.Color(255, 255, 0));
        btnsimpan.setColorStart(new java.awt.Color(255, 102, 0));
        btnsimpan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnsimpan.setHoverEnd(new java.awt.Color(51, 204, 0));
        btnsimpan.setHoverStart(new java.awt.Color(0, 153, 153));
        btnsimpan.setRoundedCorner(25);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel2.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 120, 40));

        btnedit.setText("Ubah");
        btnedit.setColorEnd(new java.awt.Color(255, 255, 0));
        btnedit.setColorStart(new java.awt.Color(255, 153, 0));
        btnedit.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnedit.setHoverEnd(new java.awt.Color(51, 204, 0));
        btnedit.setHoverStart(new java.awt.Color(0, 153, 153));
        btnedit.setRoundedCorner(25);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel2.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 120, 40));

        btnhapus.setText("Hapus");
        btnhapus.setColorEnd(new java.awt.Color(255, 204, 102));
        btnhapus.setColorStart(new java.awt.Color(255, 0, 0));
        btnhapus.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnhapus.setHoverEnd(new java.awt.Color(255, 0, 51));
        btnhapus.setHoverStart(new java.awt.Color(204, 0, 204));
        btnhapus.setRoundedCorner(25);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel2.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 120, 40));

        scrollPaneWin111.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneWin111.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Tablekriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Prioritas Kepentingan"
            }
        ));
        jScrollPane1.setViewportView(Tablekriteria);

        scrollPaneWin111.setViewportView(jScrollPane1);

        jPanel2.add(scrollPaneWin111, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 460, 180));

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("~ Pengaturan Kriteria ~");

        javax.swing.GroupLayout blurWarna22Layout = new javax.swing.GroupLayout(blurWarna22);
        blurWarna22.setLayout(blurWarna22Layout);
        blurWarna22Layout.setHorizontalGroup(
            blurWarna22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna22Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        blurWarna22Layout.setVerticalGroup(
            blurWarna22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(blurWarna22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 280, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgminecraft.jpg"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 520));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
         if(cbKriteria1.getSelectedIndex() != 0 && cbKriteria2.getSelectedIndex() != 0 
                && cbKriteria3.getSelectedIndex() != 0 && cbKriteria4.getSelectedIndex() != 0){    
            insertDataKriteria();
        }else{
            JOptionPane.showMessageDialog(null, "Mohon isi semua kriteria yang ada!", "Error", ERROR_MESSAGE );
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        editDataKriteria();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
           hapusDataKriteria();
    }//GEN-LAST:event_btnhapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablekriteria;
    private Blurwarna.BlurWarna2 blurWarna21;
    private Blurwarna.BlurWarna2 blurWarna22;
    private ta8.Buttonwarna btnedit;
    private ta8.Buttonwarna btnhapus;
    private ta8.Buttonwarna btnsimpan;
    private javax.swing.JComboBox<String> cbKriteria1;
    private javax.swing.JComboBox<String> cbKriteria2;
    private javax.swing.JComboBox<String> cbKriteria3;
    private javax.swing.JComboBox<String> cbKriteria4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private scrollbar.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
