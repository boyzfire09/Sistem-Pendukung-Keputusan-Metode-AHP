/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta8;
import java.sql.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import static ta8.koneksi.koneksiDb;

public class MenuDataKaset extends javax.swing.JPanel {
    Connection koneksi = null;
    public DefaultTableModel tabmode;
    private TambahData dialog = new TambahData();
    private String kodeKaset;
   
    
    
    public MenuDataKaset() {
        initComponents();
       Locale locale = new Locale ("id","ID");
       Locale.setDefault(locale);
        koneksi = koneksi();  
        updateDataTabel(); 
         btntambahkaset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
         btneditkaset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
         btnhapus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 

   
    }
   
    
   
   protected void updateDataTabel(){
    Object[] Baris = {
        "Kode Kaset",
        "Nama Kaset Game",
        "Genre",
        "Kondisi Kaset",
        "Mode Game",
        "Tahun Rilis"
    };

    tabmode = new DefaultTableModel(null, Baris);
    Tablekaset.setModel(tabmode);

    String sql = "SELECT * FROM kaset_game ORDER BY kode_kaset";
    try {
        Statement stat = koneksi.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        
        NumberFormat nf = NumberFormat.getInstance(new Locale("in", "ID")); 
        
        while(rs.next()){
            String kode = rs.getString("kode_kaset");
            String nama = rs.getString("nama_kaset_game");
            String genre = rs.getString("genre");
            String kondisi = rs.getString("kondisi_kaset");
            String mode = rs.getString("mode_game");
            String rilis = rs.getString("tahun_rilis");
          
            Object[] data = {
                kode,
                nama,
                genre,
                kondisi,
                mode,
                rilis
            };
            tabmode.addRow(data);
        }
        Tablekaset.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        Tablekaset.getColumnModel().getColumn(0).setPreferredWidth(115); 
        Tablekaset.getColumnModel().getColumn(1).setPreferredWidth(185); 
        Tablekaset.getColumnModel().getColumn(2).setPreferredWidth(176); 
        Tablekaset.getColumnModel().getColumn(3).setPreferredWidth(125); 
        Tablekaset.getColumnModel().getColumn(4).setPreferredWidth(106); 
        Tablekaset.getColumnModel().getColumn(5).setPreferredWidth(96); 
       
        
         javax.swing.table.JTableHeader header = Tablekaset.getTableHeader();
        javax.swing.table.DefaultTableCellRenderer renderer = 
        (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < Tablekaset.getColumnCount(); i++) {
            Tablekaset.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, "Gagal menampilkan data kaset: " + e.getMessage());
    }
}

    
    private void getDataTabel(){
        int bar = Tablekaset.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();
      
       
        
        kodeKaset = a;
        dialog.setDataTabel(a, b, c, d, e, f);
    }
    
    protected void hapusMenuDataKaset(){
        if(kodeKaset != null){
            int ok = JOptionPane.showConfirmDialog(null,"Ingin Menghapus Data ini ?","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
            if(ok == 0){
                String sql = "DELETE FROM kaset_game WHERE kode_kaset='"+kodeKaset+"'";
                try{
                    PreparedStatement stat = koneksi.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                    updateDataTabel();
                    kodeKaset=null;
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null,"Data Gagal diHapus " + e);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus! ");
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
    
    public void tampilTabel() {
    DefaultTableModel model = (DefaultTableModel) Tablekaset.getModel(); 
    model.setRowCount(0); 
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db_game", "root", "");
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM kaset_game");
        while (rs.next()) {
            Object[] row = {
                rs.getString("kode_kaset"),
                rs.getString("nama_kaset_game"),
                rs.getString("genre"),
                rs.getString("kondisi_kaset"),
                rs.getString("mode_game"),
                rs.getString("tahun_rilis")
            };
            model.addRow(row);
            
            
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Load Data: " + e.getMessage());
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
        btneditkaset = new ta8.Buttonwarna();
        btntambahkaset = new ta8.Buttonwarna();
        btnhapus = new ta8.Buttonwarna();
        blurwarna2 = new Blurwarna.Blurwarna();
        scrollPaneWin111 = new scrollbar.ScrollPaneWin11();
        Tablekaset = new javax.swing.JTable();
        blurwarna1 = new Blurwarna.Blurwarna();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(870, 520));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btneditkaset.setText("Ubah");
        btneditkaset.setColorEnd(new java.awt.Color(0, 102, 255));
        btneditkaset.setColorStart(new java.awt.Color(102, 204, 255));
        btneditkaset.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btneditkaset.setHoverEnd(new java.awt.Color(153, 0, 153));
        btneditkaset.setHoverStart(new java.awt.Color(51, 51, 255));
        btneditkaset.setRoundedCorner(25);
        btneditkaset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditkasetActionPerformed(evt);
            }
        });
        jPanel1.add(btneditkaset, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 100, 40));

        btntambahkaset.setText("Tambah Data Kaset");
        btntambahkaset.setColorEnd(new java.awt.Color(0, 102, 255));
        btntambahkaset.setColorStart(new java.awt.Color(102, 204, 255));
        btntambahkaset.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btntambahkaset.setHoverEnd(new java.awt.Color(153, 0, 153));
        btntambahkaset.setHoverStart(new java.awt.Color(51, 51, 255));
        btntambahkaset.setRoundedCorner(25);
        btntambahkaset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntambahkasetMouseClicked(evt);
            }
        });
        jPanel1.add(btntambahkaset, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 170, 40));

        btnhapus.setText("Hapus");
        btnhapus.setColorEnd(new java.awt.Color(255, 204, 51));
        btnhapus.setColorStart(new java.awt.Color(255, 0, 0));
        btnhapus.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnhapus.setHoverEnd(new java.awt.Color(255, 51, 204));
        btnhapus.setHoverStart(new java.awt.Color(255, 0, 0));
        btnhapus.setRoundedCorner(25);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 320, 90, 40));

        scrollPaneWin111.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneWin111.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Tablekaset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Kaset", "Nama Kaset Game", "Genre", "Kondisi Kaset", "Mode Game", "Tahun Rilis"
            }
        ));
        Tablekaset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablekasetMouseClicked(evt);
            }
        });
        scrollPaneWin111.setViewportView(Tablekaset);

        javax.swing.GroupLayout blurwarna2Layout = new javax.swing.GroupLayout(blurwarna2);
        blurwarna2.setLayout(blurwarna2Layout);
        blurwarna2Layout.setHorizontalGroup(
            blurwarna2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurwarna2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        blurwarna2Layout.setVerticalGroup(
            blurwarna2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blurwarna2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(blurwarna2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 820, 180));

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("~ Data Kaset Game ~");

        javax.swing.GroupLayout blurwarna1Layout = new javax.swing.GroupLayout(blurwarna1);
        blurwarna1.setLayout(blurwarna1Layout);
        blurwarna1Layout.setHorizontalGroup(
            blurwarna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blurwarna1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addContainerGap())
        );
        blurwarna1Layout.setVerticalGroup(
            blurwarna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurwarna1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(blurwarna1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 240, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bgkaset.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btntambahkasetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahkasetMouseClicked
        // TODO add your handling code here:
   
     TambahData panelTambah = new TambahData();

    JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Tambah Data", true);
    dialog.getContentPane().add(panelTambah);
    dialog.pack();
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);

    updateDataTabel();
    
   
    }//GEN-LAST:event_btntambahkasetMouseClicked

    private void TablekasetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablekasetMouseClicked
        // TODO add your handling code here:
         getDataTabel();
    }//GEN-LAST:event_TablekasetMouseClicked

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        hapusMenuDataKaset();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btneditkasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditkasetActionPerformed
        // TODO add your handling code here:
       int selectedRow = Tablekaset.getSelectedRow();
    if (selectedRow >= 0) {
       
        String kode = tabmode.getValueAt(selectedRow, 0).toString();
        String nama = tabmode.getValueAt(selectedRow, 1).toString();
        String genre = tabmode.getValueAt(selectedRow, 2).toString();
        String kondisi = tabmode.getValueAt(selectedRow, 3).toString();
        String mode = tabmode.getValueAt(selectedRow, 4).toString();
        String rilis = tabmode.getValueAt(selectedRow, 5).toString();
       

        
        TambahData panelTambah = new TambahData();
        panelTambah.setDataTabel(kode, nama, genre, kondisi, mode, rilis);

        
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Edit Data Kaset", true);
        dialog.getContentPane().add(panelTambah);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        
        tampilTabel();
    } else {
        JOptionPane.showMessageDialog(null, "Silakan pilih data terlebih dahulu!");
    }
    }//GEN-LAST:event_btneditkasetActionPerformed

     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablekaset;
    private Blurwarna.Blurwarna blurwarna1;
    private Blurwarna.Blurwarna blurwarna2;
    private ta8.Buttonwarna btneditkaset;
    private ta8.Buttonwarna btnhapus;
    private ta8.Buttonwarna btntambahkaset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private scrollbar.ScrollPaneWin11 scrollPaneWin111;
    // End of variables declaration//GEN-END:variables
}
