/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ta8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import static ta8.koneksi.koneksiDb;

/**
 *
 * @author USER
 */
public class MenuSubKriteria extends javax.swing.JPanel {
     Connection koneksi = null;
     private DefaultTableModel tabmode;
   
    public MenuSubKriteria() {
        initComponents();
        koneksi = koneksi();
        updateDataTabel();
       Locale locale = new Locale ("id","ID");
       Locale.setDefault(locale);
       SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", locale);
       

         
         
          javax.swing.table.JTableHeader header = Tablesubkriteria.getTableHeader();
    javax.swing.table.DefaultTableCellRenderer renderer = 
        (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    renderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    
     DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < Tablesubkriteria.getColumnCount(); i++) {
            Tablesubkriteria.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    
      protected void kosong(){
        cbSubRating1.setSelectedIndex(0);
        cbSubRating2.setSelectedIndex(0);
        cbSubRating3.setSelectedIndex(0);
        cbSubRating4.setSelectedIndex(0);
        cbSubHarga1.setSelectedIndex(0);
        cbSubHarga2.setSelectedIndex(0);
        cbSubHarga3.setSelectedIndex(0);
        cbSubHarga4.setSelectedIndex(0);
        cbSubTarget1.setSelectedIndex(0);
        cbSubTarget2.setSelectedIndex(0);
        cbSubTarget3.setSelectedIndex(0);
        cbSubPlatform1.setSelectedIndex(0);
        cbSubPlatform2.setSelectedIndex(0);
        cbSubPlatform3.setSelectedIndex(0);
   
    }
    
    protected void updateDataTabel(){
        Object[] Baris = {
            "Kode Kriteria",
            "Nama Kriteria",
            "Nama SubKriteria",
            "Kepentingan SubKriteria"
        };
        tabmode = new DefaultTableModel(null, Baris);
        Tablesubkriteria.setModel(tabmode);
        String sql = "SELECT * FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kd_kriteria");
                String b = hasil.getString("nama_kriteria");
                String c = hasil.getString("nama_sub_kriteria");
                String d = hasil.getString("prioritas_kepentingan");
                
                String[] data={a, b, c, d};
                tabmode.addRow(data);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    protected void getDataTabel(){
        String sql = "SELECT nama_sub_kriteria FROM sub_kriteria ORDER BY kd_kriteria, no_sub";
        int n = 1;
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_sub_kriteria");
                if(n==1){
                    cbSubRating1.setSelectedItem(a);
                }else if(n==2){
                    cbSubRating2.setSelectedItem(a);
                }else if(n==3){
                    cbSubRating3.setSelectedItem(a);
                }else if(n==4){
                    cbSubRating4.setSelectedItem(a);
                }else if(n==5){
                    cbSubHarga1.setSelectedItem(a);
                }else if(n==6){
                    cbSubHarga2.setSelectedItem(a);
                }else if(n==7){
                    cbSubHarga3.setSelectedItem(a);
                }else if(n==8){
                    cbSubHarga4.setSelectedItem(a);
                }else if(n==9){
                    cbSubTarget1.setSelectedItem(a);
                }else if(n==10){
                    cbSubTarget2.setSelectedItem(a);
                }else if(n==11){
                    cbSubTarget3.setSelectedItem(a);
                }else if(n==12){
                    cbSubPlatform1.setSelectedItem(a);
                }else if(n==13){
                    cbSubPlatform2.setSelectedItem(a);
                }else if(n==14){
                    cbSubPlatform3.setSelectedItem(a);
                } 
                n++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //masukan data subkriteria
    protected void insertDataSubKriteria(){
        try{
        int n=1, nRating=1, nHarga=1, nTargetPemain=1, nPlatform=1, i=1;
            do{
                String kepentingan;
                String sql = "INSERT INTO sub_kriteria VALUES (?,?,?,?,?)";
                PreparedStatement stat = koneksi.prepareStatement(sql);
                java.sql.Statement statCek = koneksi.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlRating = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Rating'";
                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                String sqlTargetPemain = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Target Pemain'";
                String sqlPlatform = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Platform'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlRating);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nRating == 1){
                        stat.setString(4, cbSubRating1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nRating == 2){
                        stat.setString(4, cbSubRating2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nRating == 3){
                        stat.setString(4, cbSubRating3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubRating4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nRating++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHarga);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nHarga == 1){
                        stat.setString(4, cbSubHarga1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHarga == 2){
                        stat.setString(4, cbSubHarga2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nHarga == 3){
                        stat.setString(4, cbSubHarga3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(4, cbSubHarga4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nHarga++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlTargetPemain);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nTargetPemain == 1){
                        stat.setString(4, cbSubTarget1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTargetPemain == 2){
                        stat.setString(4, cbSubTarget2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubTarget3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nTargetPemain++;
                }else{    
                    hasil = statCek.executeQuery(sqlPlatform);
                    stat.setString(1, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(2, a);
                        stat.setString(3, b);
                    }
                    if(nPlatform == 1){
                        stat.setString(4, cbSubPlatform1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nPlatform == 2){
                        stat.setString(4, cbSubPlatform2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(4, cbSubPlatform3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(5, kepentingan);
                    stat.executeUpdate();
                    nPlatform++;
                }
            }while(n<=4);   
            JOptionPane.showMessageDialog(null, "DATA Berhasil Disimpan");
            kosong();
            updateDataTabel();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e);
        }
    }

    
    protected void hapusDataSubKriteria(){
        int ok = JOptionPane.showConfirmDialog(null,"hapus","Konfirmasi Dialog",JOptionPane.YES_NO_OPTION);
        if(ok == 0){
            String sql = "DELETE FROM sub_kriteria";
            try{
                PreparedStatement stat = koneksi.prepareStatement(sql);

                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil diHapus ");
                kosong();
                updateDataTabel();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Data Gagal diHapus " + e);
            }
        }
    }
    
    protected void editDataSubKriteria(){
        try{
        int n=1, nRating=1, nHarga=1, nTargetPemain=1, nPlatform=1, i=1;
            do{
                String kepentingan;
                String sql = "UPDATE sub_kriteria set kd_kriteria=?, nama_kriteria=?, nama_sub_kriteria=?, prioritas_kepentingan=? WHERE no_sub=?";
                PreparedStatement stat = koneksi.prepareStatement(sql);
                java.sql.Statement statCek = koneksi.createStatement();
                String sqlSub = "SELECT kd_kriteria, nama_kriteria FROM kriteria";
                String sqlRating = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Rating'";
                String sqlHarga = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Harga'";
                String sqlTargetPemain = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Target Pemain'";
                String sqlPlatform = "SELECT kd_kriteria, nama_kriteria FROM kriteria WHERE nama_kriteria='Platform'";
                ResultSet hasil = statCek.executeQuery(sqlSub);
                if(n==1){
                    hasil = statCek.executeQuery(sqlRating);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nRating == 1){
                        stat.setString(3, cbSubRating1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nRating == 2){
                        stat.setString(3, cbSubRating2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else if(nRating == 3){
                        stat.setString(3, cbSubRating3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                    }else{
                        stat.setString(3, cbSubRating4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nRating++;
                }else if(n==2){
                    hasil = statCek.executeQuery(sqlHarga);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nHarga == 1){
                        stat.setString(3, cbSubHarga1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nHarga == 2){
                        stat.setString(3, cbSubHarga2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else if (nHarga == 3){
                        stat.setString(3, cbSubHarga3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                    }else{
                        stat.setString(3, cbSubHarga4.getSelectedItem().toString());
                        kepentingan="Biasa ke-4";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nHarga++;
                }else if(n==3){
                    hasil = statCek.executeQuery(sqlTargetPemain);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){    
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nTargetPemain == 1){
                        stat.setString(3, cbSubTarget1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nTargetPemain == 2){
                        stat.setString(3, cbSubTarget2.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-2";
                    }else{
                        stat.setString(3, cbSubTarget3.getSelectedItem().toString());
                        kepentingan="Biasa ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nTargetPemain++;
                }else{    
                    hasil = statCek.executeQuery(sqlPlatform);
                    stat.setString(5, Integer.toString(i));
                    i++;
                    while(hasil.next()){
                        String a = hasil.getString("kd_kriteria");
                        String b = hasil.getString("nama_kriteria");
                        stat.setString(1, a);
                        stat.setString(2, b);
                    }
                    if(nPlatform == 1){
                        stat.setString(3, cbSubPlatform1.getSelectedItem().toString());
                        kepentingan="Sangat Penting ke-1";
                    }else if(nPlatform == 2){
                        stat.setString(3, cbSubPlatform2.getSelectedItem().toString());
                        kepentingan="Penting ke-2";
                    }else{
                        stat.setString(3, cbSubPlatform3.getSelectedItem().toString());
                        kepentingan="Cukup Penting ke-3";
                        n++;
                    }
                    stat.setString(4, kepentingan);
                    stat.executeUpdate();
                    nPlatform++;
                }
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
        blurWarna55 = new Blurwarna.BlurWarna5();
        scrollPaneWin112 = new scrollbar.ScrollPaneWin11();
        Tablesubkriteria = new javax.swing.JTable();
        blurWarna57 = new Blurwarna.BlurWarna5();
        jLabel20 = new javax.swing.JLabel();
        btnsimpan = new ta8.Buttonwarna();
        btnedit = new ta8.Buttonwarna();
        btnhapus = new ta8.Buttonwarna();
        jPanel4 = new javax.swing.JPanel();
        scrollPaneWin111 = new scrollbar.ScrollPaneWin11();
        panelGradient9 = new ta8.PanelGradient();
        blurWarna59 = new Blurwarna.BlurWarna5();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        cbSubRating4 = new javax.swing.JComboBox<>();
        cbSubRating3 = new javax.swing.JComboBox<>();
        cbSubRating2 = new javax.swing.JComboBox<>();
        cbSubRating1 = new javax.swing.JComboBox<>();
        blurWarna60 = new Blurwarna.BlurWarna5();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        cbSubHarga1 = new javax.swing.JComboBox<>();
        cbSubHarga2 = new javax.swing.JComboBox<>();
        cbSubHarga4 = new javax.swing.JComboBox<>();
        cbSubHarga3 = new javax.swing.JComboBox<>();
        blurWarna61 = new Blurwarna.BlurWarna5();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        cbSubTarget3 = new javax.swing.JComboBox<>();
        cbSubTarget2 = new javax.swing.JComboBox<>();
        cbSubTarget1 = new javax.swing.JComboBox<>();
        blurWarna62 = new Blurwarna.BlurWarna5();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        cbSubPlatform1 = new javax.swing.JComboBox<>();
        cbSubPlatform2 = new javax.swing.JComboBox<>();
        cbSubPlatform3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tablesubkriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Kode Kriteria", "Nama Kriteria", "Nama SubKriteria", "Kepentingan SubKriteria"
            }
        ));
        Tablesubkriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablesubkriteriaMouseClicked(evt);
            }
        });
        scrollPaneWin112.setViewportView(Tablesubkriteria);

        javax.swing.GroupLayout blurWarna55Layout = new javax.swing.GroupLayout(blurWarna55);
        blurWarna55.setLayout(blurWarna55Layout);
        blurWarna55Layout.setHorizontalGroup(
            blurWarna55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );
        blurWarna55Layout.setVerticalGroup(
            blurWarna55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(blurWarna55, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 680, 190));

        jLabel20.setFont(new java.awt.Font("Baskerville Old Face", 3, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("- Pengaturan Sub Kriteria -");

        javax.swing.GroupLayout blurWarna57Layout = new javax.swing.GroupLayout(blurWarna57);
        blurWarna57.setLayout(blurWarna57Layout);
        blurWarna57Layout.setHorizontalGroup(
            blurWarna57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna57Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel20)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        blurWarna57Layout.setVerticalGroup(
            blurWarna57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(blurWarna57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        btnsimpan.setText("Simpan");
        btnsimpan.setColorEnd(new java.awt.Color(255, 204, 0));
        btnsimpan.setColorStart(new java.awt.Color(255, 0, 255));
        btnsimpan.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnsimpan.setHoverEnd(new java.awt.Color(204, 102, 0));
        btnsimpan.setHoverStart(new java.awt.Color(153, 0, 255));
        btnsimpan.setRoundedCorner(25);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 137, 47));

        btnedit.setText("Ubah");
        btnedit.setColorEnd(new java.awt.Color(255, 204, 0));
        btnedit.setColorStart(new java.awt.Color(255, 0, 255));
        btnedit.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnedit.setHoverEnd(new java.awt.Color(204, 102, 0));
        btnedit.setHoverStart(new java.awt.Color(153, 0, 255));
        btnedit.setRoundedCorner(25);
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel1.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 137, 47));

        btnhapus.setText("Hapus");
        btnhapus.setColorEnd(new java.awt.Color(204, 255, 0));
        btnhapus.setColorStart(new java.awt.Color(255, 0, 0));
        btnhapus.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnhapus.setHoverEnd(new java.awt.Color(255, 153, 102));
        btnhapus.setHoverStart(new java.awt.Color(204, 0, 51));
        btnhapus.setRoundedCorner(25);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 137, 47));

        jPanel4.setBackground(new java.awt.Color(204, 0, 204));

        panelGradient9.setColorEnd(new java.awt.Color(51, 153, 255));
        panelGradient9.setColorStart(new java.awt.Color(153, 0, 153));

        jLabel97.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Rating");

        jLabel98.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(0, 255, 255));
        jLabel98.setText("Rating Sangat Penting ke - 1 ");

        jLabel99.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(0, 255, 255));
        jLabel99.setText("Rating Penting ke - 2 ");

        jLabel100.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 255, 255));
        jLabel100.setText("Rating Cukup Penting ke - 3 ");

        jLabel101.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(0, 255, 255));
        jLabel101.setText("Rating Biasa ke - 4 ");

        cbSubRating4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Rating ~", "Sangat Tinggi", "Tinggi", "Sedang", "Rendah" }));
        cbSubRating4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubRating4ActionPerformed(evt);
            }
        });

        cbSubRating3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Rating ~", "Sangat Tinggi", "Tinggi", "Sedang", "Rendah" }));
        cbSubRating3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubRating3ActionPerformed(evt);
            }
        });

        cbSubRating2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Rating ~", "Sangat Tinggi", "Tinggi", "Sedang", "Rendah", " " }));
        cbSubRating2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubRating2ActionPerformed(evt);
            }
        });

        cbSubRating1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Rating ~", "Sangat Tinggi", "Tinggi", "Sedang", "Rendah" }));
        cbSubRating1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubRating1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout blurWarna59Layout = new javax.swing.GroupLayout(blurWarna59);
        blurWarna59.setLayout(blurWarna59Layout);
        blurWarna59Layout.setHorizontalGroup(
            blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98)
                    .addComponent(jLabel100)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99)
                    .addComponent(jLabel101))
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(blurWarna59Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cbSubRating4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(blurWarna59Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbSubRating3, 0, 189, Short.MAX_VALUE)
                            .addComponent(cbSubRating2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSubRating1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        blurWarna59Layout.setVerticalGroup(
            blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna59Layout.createSequentialGroup()
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubRating1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubRating2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubRating3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(blurWarna59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubRating4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jLabel102.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Harga");

        jLabel103.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(0, 255, 255));
        jLabel103.setText("Harga Sangat Penting ke - 1 ");

        jLabel104.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(0, 255, 255));
        jLabel104.setText("Harga Penting ke - 2 ");

        jLabel105.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(0, 255, 255));
        jLabel105.setText("Harga Cukup Penting ke - 3 ");

        jLabel106.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 255, 255));
        jLabel106.setText("Harga Biasa ke - 4 ");

        cbSubHarga1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Harga ~", "Murah", "Terjangkau", "Mahal", "Sangat Mahal" }));
        cbSubHarga1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubHarga1ActionPerformed(evt);
            }
        });

        cbSubHarga2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Harga ~", "Murah", "Terjangkau", "Mahal", "Sangat Mahal" }));
        cbSubHarga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubHarga2ActionPerformed(evt);
            }
        });

        cbSubHarga4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Harga ~", "Murah", "Terjangkau", "Mahal", "Sangat Mahal" }));
        cbSubHarga4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubHarga4ActionPerformed(evt);
            }
        });

        cbSubHarga3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Harga ~", "Murah", "Terjangkau", "Mahal", "Sangat Mahal" }));
        cbSubHarga3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubHarga3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout blurWarna60Layout = new javax.swing.GroupLayout(blurWarna60);
        blurWarna60.setLayout(blurWarna60Layout);
        blurWarna60Layout.setHorizontalGroup(
            blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel103)
                    .addComponent(jLabel104)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105)
                    .addComponent(jLabel106))
                .addGap(67, 67, 67)
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbSubHarga3, 0, 189, Short.MAX_VALUE)
                    .addComponent(cbSubHarga4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        blurWarna60Layout.setVerticalGroup(
            blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna60Layout.createSequentialGroup()
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(blurWarna60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubHarga4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110))
        );

        jLabel107.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setText("Target Pemain");

        jLabel108.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 255, 255));
        jLabel108.setText("Targert Pemain Sangat Penting ke - 1 ");

        jLabel109.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(0, 255, 255));
        jLabel109.setText("Target Pemain Cukup Penting ke - 2 ");

        jLabel110.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(0, 255, 255));
        jLabel110.setText("Target Pemain Biasa ke - 3 ");

        cbSubTarget3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Target Pemain ~", "Anak - Anak", "Remaja", "Dewasa" }));
        cbSubTarget3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubTarget3ActionPerformed(evt);
            }
        });

        cbSubTarget2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Target Pemain ~", "Anak - Anak", "Remaja", "Dewasa" }));
        cbSubTarget2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubTarget2ActionPerformed(evt);
            }
        });

        cbSubTarget1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Target Pemain ~", "Anak - Anak", "Remaja", "Dewasa" }));
        cbSubTarget1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubTarget1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout blurWarna61Layout = new javax.swing.GroupLayout(blurWarna61);
        blurWarna61.setLayout(blurWarna61Layout);
        blurWarna61Layout.setHorizontalGroup(
            blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel107, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, blurWarna61Layout.createSequentialGroup()
                        .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel109)
                            .addComponent(jLabel110)
                            .addComponent(jLabel108))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbSubTarget2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSubTarget1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSubTarget3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        blurWarna61Layout.setVerticalGroup(
            blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna61Layout.createSequentialGroup()
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubTarget1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubTarget2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(blurWarna61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbSubTarget3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jLabel111.setFont(new java.awt.Font("Rockwell", 3, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Platform");

        jLabel112.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(0, 255, 255));
        jLabel112.setText("Platform Sangat Penting ke - 1 ");

        jLabel113.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(0, 255, 255));
        jLabel113.setText("Platform Cukup Penting ke - 2 ");

        jLabel114.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 255, 255));
        jLabel114.setText("Platform Biasa ke - 3 ");

        cbSubPlatform1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Platform ~", "Playstation", "PC", "X-Box" }));
        cbSubPlatform1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubPlatform1ActionPerformed(evt);
            }
        });

        cbSubPlatform2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Platform ~", "Playstation", "PC", "X-Box" }));
        cbSubPlatform2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubPlatform2ActionPerformed(evt);
            }
        });

        cbSubPlatform3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih SubKriteria Platform ~", "Playstation", "PC", "X-Box" }));
        cbSubPlatform3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubPlatform3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout blurWarna62Layout = new javax.swing.GroupLayout(blurWarna62);
        blurWarna62.setLayout(blurWarna62Layout);
        blurWarna62Layout.setHorizontalGroup(
            blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna62Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(blurWarna62Layout.createSequentialGroup()
                        .addComponent(jLabel111)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blurWarna62Layout.createSequentialGroup()
                        .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel113)
                            .addComponent(jLabel112)
                            .addComponent(jLabel114))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbSubPlatform2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSubPlatform1, 0, 193, Short.MAX_VALUE)
                            .addComponent(cbSubPlatform3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        blurWarna62Layout.setVerticalGroup(
            blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurWarna62Layout.createSequentialGroup()
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubPlatform1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSubPlatform2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(blurWarna62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(cbSubPlatform3, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGradient9Layout = new javax.swing.GroupLayout(panelGradient9);
        panelGradient9.setLayout(panelGradient9Layout);
        panelGradient9Layout.setHorizontalGroup(
            panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(blurWarna59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blurWarna61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blurWarna60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blurWarna62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelGradient9Layout.setVerticalGroup(
            panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(blurWarna59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blurWarna60, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelGradient9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blurWarna61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blurWarna62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        scrollPaneWin111.setViewportView(panelGradient9);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 830, 260));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BgGTAV.jpeg"))); // NOI18N
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

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
         insertDataSubKriteria();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        editDataSubKriteria();
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        hapusDataSubKriteria();
    }//GEN-LAST:event_btnhapusActionPerformed

    private void TablesubkriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablesubkriteriaMouseClicked
        // TODO add your handling code here:
          getDataTabel();
    }//GEN-LAST:event_TablesubkriteriaMouseClicked

    private void cbSubRating4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubRating4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubRating4ActionPerformed

    private void cbSubRating3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubRating3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubRating3ActionPerformed

    private void cbSubRating2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubRating2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubRating2ActionPerformed

    private void cbSubRating1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubRating1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubRating1ActionPerformed

    private void cbSubHarga1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubHarga1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubHarga1ActionPerformed

    private void cbSubHarga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubHarga2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubHarga2ActionPerformed

    private void cbSubHarga4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubHarga4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubHarga4ActionPerformed

    private void cbSubHarga3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubHarga3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubHarga3ActionPerformed

    private void cbSubTarget3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubTarget3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubTarget3ActionPerformed

    private void cbSubTarget2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubTarget2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubTarget2ActionPerformed

    private void cbSubTarget1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubTarget1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubTarget1ActionPerformed

    private void cbSubPlatform1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubPlatform1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubPlatform1ActionPerformed

    private void cbSubPlatform2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubPlatform2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubPlatform2ActionPerformed

    private void cbSubPlatform3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubPlatform3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubPlatform3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tablesubkriteria;
    private Blurwarna.BlurWarna5 blurWarna55;
    private Blurwarna.BlurWarna5 blurWarna57;
    private Blurwarna.BlurWarna5 blurWarna59;
    private Blurwarna.BlurWarna5 blurWarna60;
    private Blurwarna.BlurWarna5 blurWarna61;
    private Blurwarna.BlurWarna5 blurWarna62;
    private ta8.Buttonwarna btnedit;
    private ta8.Buttonwarna btnhapus;
    private ta8.Buttonwarna btnsimpan;
    private javax.swing.JComboBox<String> cbSubHarga1;
    private javax.swing.JComboBox<String> cbSubHarga2;
    private javax.swing.JComboBox<String> cbSubHarga3;
    private javax.swing.JComboBox<String> cbSubHarga4;
    private javax.swing.JComboBox<String> cbSubPlatform1;
    private javax.swing.JComboBox<String> cbSubPlatform2;
    private javax.swing.JComboBox<String> cbSubPlatform3;
    private javax.swing.JComboBox<String> cbSubRating1;
    private javax.swing.JComboBox<String> cbSubRating2;
    private javax.swing.JComboBox<String> cbSubRating3;
    private javax.swing.JComboBox<String> cbSubRating4;
    private javax.swing.JComboBox<String> cbSubTarget1;
    private javax.swing.JComboBox<String> cbSubTarget2;
    private javax.swing.JComboBox<String> cbSubTarget3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private ta8.PanelGradient panelGradient9;
    private scrollbar.ScrollPaneWin11 scrollPaneWin111;
    private scrollbar.ScrollPaneWin11 scrollPaneWin112;
    // End of variables declaration//GEN-END:variables
}
