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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableModel;
import static ta8.koneksi.koneksiDb;

public class AHP extends javax.swing.JPanel {
    Connection koneksi = null;
    private DefaultTableModel tabmode;
    protected KriteriaAhp kriteria = new KriteriaAhp();
    protected SubKriteriaAhp SubK = new SubKriteriaAhp();
    DecimalFormat df = new DecimalFormat("#.##");
    ArrayList<String> K = new ArrayList<String>();
    ArrayList<Double> KS4x4 = new ArrayList<Double>();
    ArrayList<Double> KS3x3 = new ArrayList<Double>();
    String kodeKasetAlternatif, namaKasetAlternatif, ratingAlternatif, hargaAlternatif, platformAlternatif, targetPemainAlternatif;
    double nilaiAlternatif, totalNilai;
   
    public AHP() {
        initComponents();
        koneksi = koneksi();
        getRelasiId();
        
        
    }
    
    void kosong(){
        TotalNilai.setText("");
    }
    
    
    public void getMatriksK(){
        k1k1.setText(df.format(kriteria.matriksBerpasangan[0][0]));
        k1k2.setText(df.format(kriteria.matriksBerpasangan[0][1]));
        k1k3.setText(df.format(kriteria.matriksBerpasangan[0][2]));
        k1k4.setText(df.format(kriteria.matriksBerpasangan[0][3]));
        k2k1.setText(df.format(kriteria.matriksBerpasangan[1][0]));
        k2k2.setText(df.format(kriteria.matriksBerpasangan[1][1]));
        k2k3.setText(df.format(kriteria.matriksBerpasangan[1][2]));
        k2k4.setText(df.format(kriteria.matriksBerpasangan[1][3]));
        k3k1.setText(df.format(kriteria.matriksBerpasangan[2][0]));
        k3k2.setText(df.format(kriteria.matriksBerpasangan[2][1]));
        k3k3.setText(df.format(kriteria.matriksBerpasangan[2][2]));
        k3k4.setText(df.format(kriteria.matriksBerpasangan[2][3]));
        k4k1.setText(df.format(kriteria.matriksBerpasangan[3][0]));
        k4k2.setText(df.format(kriteria.matriksBerpasangan[3][1]));
        k4k3.setText(df.format(kriteria.matriksBerpasangan[3][2]));
        k4k4.setText(df.format(kriteria.matriksBerpasangan[3][3]));
    }
    
    
    public void getMatriksNorK(){
        k1k1N.setText(df.format(kriteria.matriksNormalisasi[0][0]));
        k1k2N.setText(df.format(kriteria.matriksNormalisasi[0][1]));
        k1k3N.setText(df.format(kriteria.matriksNormalisasi[0][2]));
        k1k4N.setText(df.format(kriteria.matriksNormalisasi[0][3]));
        k2k1N.setText(df.format(kriteria.matriksNormalisasi[1][0]));
        k2k2N.setText(df.format(kriteria.matriksNormalisasi[1][1]));
        k2k3N.setText(df.format(kriteria.matriksNormalisasi[1][2]));
        k2k4N.setText(df.format(kriteria.matriksNormalisasi[1][3]));
        k3k1N.setText(df.format(kriteria.matriksNormalisasi[2][0]));
        k3k2N.setText(df.format(kriteria.matriksNormalisasi[2][1]));
        k3k3N.setText(df.format(kriteria.matriksNormalisasi[2][2]));
        k3k4N.setText(df.format(kriteria.matriksNormalisasi[2][3]));
        k4k1N.setText(df.format(kriteria.matriksNormalisasi[3][0]));
        k4k2N.setText(df.format(kriteria.matriksNormalisasi[3][1]));
        k4k3N.setText(df.format(kriteria.matriksNormalisasi[3][2]));
        k4k4N.setText(df.format(kriteria.matriksNormalisasi[3][3]));
        Prior1.setText(df.format(kriteria.prioritas[0]));
        Prior2.setText(df.format(kriteria.prioritas[1]));
        Prior3.setText(df.format(kriteria.prioritas[2]));
        Prior4.setText(df.format(kriteria.prioritas[3]));
        
    }
    
    public void getPrioritasSub(){
        getKriteria();
        if(K.get(0).equals("Rating") || K.get(0).equals("Harga")){
            PriorS11.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS12.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS13.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS14.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(1).equals("Rating") || K.get(1).equals("Harga")){
            PriorS21.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS22.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS23.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS24.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(2).equals("Rating") || K.get(2).equals("Harga")){
            PriorS31.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS32.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS33.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS34.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(3).equals("Rating") || K.get(3).equals("Harga")){
            PriorS41.setText(df.format(SubK.prioritasSub4x4[0]));
            PriorS42.setText(df.format(SubK.prioritasSub4x4[1]));
            PriorS43.setText(df.format(SubK.prioritasSub4x4[2]));
            PriorS44.setText(df.format(SubK.prioritasSub4x4[3]));
        }
        if(K.get(0).equals("Target Pemain") || K.get(0).equals("Platform")){
            PriorS11.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS12.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS13.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(1).equals("Target Pemain") || K.get(1).equals("Platform")){
            PriorS21.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS22.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS23.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(2).equals("Target Pemain") || K.get(2).equals("Platform")){
            PriorS31.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS32.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS33.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        if(K.get(3).equals("Target Pemain") || K.get(3).equals("Platform")){
            PriorS41.setText(df.format(SubK.prioritasSub3x3[0]));
            PriorS42.setText(df.format(SubK.prioritasSub3x3[1]));
            PriorS43.setText(df.format(SubK.prioritasSub3x3[2]));
        }
        
    }
    
    
     public void getKriteria(){
        K.clear();
        String sql = "SELECT nama_kriteria FROM kriteria ORDER BY kd_kriteria";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama_kriteria");
                K.add(a);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
     
   
    public void getAlternatif(){
        String sql = "SELECT DISTINCT * FROM penilaian_kaset WHERE kode_kaset='"+cbkodekaset.getSelectedItem().toString()+"'";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kode_kaset");
                String b = hasil.getString("nama_kaset_game");
                String c = hasil.getString("rating");
                String d = hasil.getString("harga");
                String e = hasil.getString("target_pemain");
                String f = hasil.getString("platform");
                kodeKasetAlternatif = a;
                namaKasetAlternatif = b;
                ratingAlternatif = c;
                hargaAlternatif = d;
                targetPemainAlternatif = e;
                platformAlternatif = f;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    } 
    
 
    
  public void getPenilaian(){
    totalNilai = 0;
    
    
    if(ratingAlternatif.equals("Sangat Tinggi")){
        nilaiAlternatif = SubK.prioritasSub4x4[0] * kriteria.prioritas[0];
         totalNilai += nilaiAlternatif;
    } else if(ratingAlternatif.equals("Tinggi")){
        nilaiAlternatif = SubK.prioritasSub4x4[1] * kriteria.prioritas[0];
        totalNilai += nilaiAlternatif; 
    } else if(ratingAlternatif.equals("Sedang")){
        nilaiAlternatif = SubK.prioritasSub4x4[2] * kriteria.prioritas[0];
        totalNilai += nilaiAlternatif; 
    } else { 
        nilaiAlternatif = SubK.prioritasSub4x4[3] * kriteria.prioritas[0];
        totalNilai += nilaiAlternatif; 
    }
   
    
    if (hargaAlternatif.equals("Murah")) {
        nilaiAlternatif = SubK.prioritasSub4x4[0] * kriteria.prioritas[1];
        totalNilai += nilaiAlternatif;
    } else if (hargaAlternatif.equals("Terjangkau")) {
        nilaiAlternatif = SubK.prioritasSub4x4[1] * kriteria.prioritas[1];
        totalNilai += nilaiAlternatif;
    } else if (hargaAlternatif.equals("Mahal")) {
        nilaiAlternatif = SubK.prioritasSub4x4[2] * kriteria.prioritas[1];
        totalNilai += nilaiAlternatif;
    } else {
        nilaiAlternatif = SubK.prioritasSub4x4[3] * kriteria.prioritas[1];
        totalNilai += nilaiAlternatif;
    }
   
   
    if(targetPemainAlternatif.equals("Anak-anak")){
        nilaiAlternatif = SubK.prioritasSub3x3[0] * kriteria.prioritas[2];
        totalNilai += nilaiAlternatif;
    } else if(targetPemainAlternatif.equals("Remaja")){
        nilaiAlternatif = SubK.prioritasSub3x3[1] * kriteria.prioritas[2];
        totalNilai += nilaiAlternatif;
    } else { // Dewasa
        nilaiAlternatif = SubK.prioritasSub3x3[2] * kriteria.prioritas[2];
        totalNilai += nilaiAlternatif;
    }
   
    
    if(platformAlternatif.equals("PlayStation")){
        nilaiAlternatif = SubK.prioritasSub3x3[0] * kriteria.prioritas[3];
        totalNilai += nilaiAlternatif;
    } else if(platformAlternatif.equals("PC")){
        nilaiAlternatif = SubK.prioritasSub3x3[1] * kriteria.prioritas[3];
        totalNilai += nilaiAlternatif;
    } else { // Xbox
        nilaiAlternatif = SubK.prioritasSub3x3[2] * kriteria.prioritas[3];
        totalNilai += nilaiAlternatif;
    }
    
    
     
     TotalNilai.setText(Double.toString(totalNilai));
}

    public void getRelasiId(){
        String sql = "SELECT DISTINCT kode_kaset, nama_kaset_game FROM kaset_game ORDER BY kode_kaset";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("kode_kaset");
                cbkodekaset.addItem(a);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
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
        blurwarna1 = new Blurwarna.Blurwarna();
        jLabel5 = new javax.swing.JLabel();
        panelBlur2 = new ta8.PanelBlur();
        scrollPaneWin112 = new scrollbar.ScrollPaneWin11();
        panelGradient3 = new ta8.PanelGradient();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namakaset = new ta8.RoundTextField();
        TotalNilai = new ta8.RoundTextField();
        cbkodekaset = new javax.swing.JComboBox<>();
        btnmulaihitung = new ta8.Buttonwarna();
        btnsimpan = new ta8.Buttonwarna();
        panelGradient6 = new ta8.PanelGradient();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        k2k1 = new ta8.RoundTextField();
        k1k1 = new ta8.RoundTextField();
        k3k1 = new ta8.RoundTextField();
        k4k1 = new ta8.RoundTextField();
        k1k2 = new ta8.RoundTextField();
        k2k2 = new ta8.RoundTextField();
        k1k3 = new ta8.RoundTextField();
        k3k2 = new ta8.RoundTextField();
        k4k2 = new ta8.RoundTextField();
        k2k3 = new ta8.RoundTextField();
        k3k3 = new ta8.RoundTextField();
        k4k3 = new ta8.RoundTextField();
        k1k4 = new ta8.RoundTextField();
        k2k4 = new ta8.RoundTextField();
        k3k4 = new ta8.RoundTextField();
        k4k4 = new ta8.RoundTextField();
        panelGradient7 = new ta8.PanelGradient();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        PriorS11 = new ta8.RoundTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        PriorS12 = new ta8.RoundTextField();
        PriorS13 = new ta8.RoundTextField();
        PriorS14 = new ta8.RoundTextField();
        PriorS21 = new ta8.RoundTextField();
        PriorS33 = new ta8.RoundTextField();
        PriorS31 = new ta8.RoundTextField();
        PriorS34 = new ta8.RoundTextField();
        PriorS22 = new ta8.RoundTextField();
        PriorS23 = new ta8.RoundTextField();
        PriorS24 = new ta8.RoundTextField();
        PriorS41 = new ta8.RoundTextField();
        PriorS43 = new ta8.RoundTextField();
        PriorS44 = new ta8.RoundTextField();
        PriorS42 = new ta8.RoundTextField();
        PriorS32 = new ta8.RoundTextField();
        panelGradient10 = new ta8.PanelGradient();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        k1k1N = new ta8.RoundTextField();
        k2k1N = new ta8.RoundTextField();
        k3k1N = new ta8.RoundTextField();
        k4k1N = new ta8.RoundTextField();
        k1k2N = new ta8.RoundTextField();
        k2k2N = new ta8.RoundTextField();
        k3k2N = new ta8.RoundTextField();
        k4k2N = new ta8.RoundTextField();
        k1k3N = new ta8.RoundTextField();
        k2k3N = new ta8.RoundTextField();
        k3k3N = new ta8.RoundTextField();
        k4k3N = new ta8.RoundTextField();
        jLabel88 = new javax.swing.JLabel();
        Prior1 = new ta8.RoundTextField();
        Prior2 = new ta8.RoundTextField();
        Prior3 = new ta8.RoundTextField();
        k1k4N = new ta8.RoundTextField();
        Prior4 = new ta8.RoundTextField();
        k2k4N = new ta8.RoundTextField();
        k3k4N = new ta8.RoundTextField();
        k4k4N = new ta8.RoundTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("~ Perhitungan Hasil Penilaian Kaset Game Menggunakan Metode AHP ~");

        javax.swing.GroupLayout blurwarna1Layout = new javax.swing.GroupLayout(blurwarna1);
        blurwarna1.setLayout(blurwarna1Layout);
        blurwarna1Layout.setHorizontalGroup(
            blurwarna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurwarna1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                .addContainerGap())
        );
        blurwarna1Layout.setVerticalGroup(
            blurwarna1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.add(blurwarna1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 660, 30));

        panelBlur2.setPreferredSize(new java.awt.Dimension(550, 360));

        panelGradient3.setColorEnd(new java.awt.Color(51, 51, 51));
        panelGradient3.setColorStart(new java.awt.Color(0, 102, 153));
        panelGradient3.setPreferredSize(new java.awt.Dimension(848, 642));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Kaset");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Kaset Game");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total Penilaian Kaset");

        namakaset.setForeground(new java.awt.Color(255, 255, 255));

        TotalNilai.setForeground(new java.awt.Color(255, 255, 255));

        cbkodekaset.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbkodekaset.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbkodekasetItemStateChanged(evt);
            }
        });
        cbkodekaset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkodekasetActionPerformed(evt);
            }
        });

        btnmulaihitung.setText("Mulai Hitung");
        btnmulaihitung.setColorEnd(new java.awt.Color(255, 204, 51));
        btnmulaihitung.setColorStart(new java.awt.Color(255, 0, 0));
        btnmulaihitung.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        btnmulaihitung.setHoverEnd(new java.awt.Color(255, 51, 204));
        btnmulaihitung.setHoverStart(new java.awt.Color(255, 0, 0));
        btnmulaihitung.setRoundedCorner(25);
        btnmulaihitung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmulaihitungActionPerformed(evt);
            }
        });

        btnsimpan.setText("Simpan Data");
        btnsimpan.setColorEnd(new java.awt.Color(255, 51, 255));
        btnsimpan.setColorStart(new java.awt.Color(0, 102, 204));
        btnsimpan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnsimpan.setHoverEnd(new java.awt.Color(102, 0, 255));
        btnsimpan.setHoverStart(new java.awt.Color(0, 255, 204));
        btnsimpan.setRoundedCorner(25);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        panelGradient6.setColorEnd(new java.awt.Color(153, 153, 153));
        panelGradient6.setColorStart(new java.awt.Color(0, 153, 153));

        jLabel25.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Matriks Perbandingan Kriteria");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("K1");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("K2");

        jLabel44.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("K3");

        jLabel45.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("K4");

        jLabel46.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("K1");

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("K2");

        jLabel48.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("K3");

        jLabel49.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("K4");

        k2k1.setForeground(new java.awt.Color(255, 255, 255));
        k2k1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k1.setForeground(new java.awt.Color(255, 255, 255));
        k1k1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k1.setForeground(new java.awt.Color(255, 255, 255));
        k3k1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k1.setForeground(new java.awt.Color(255, 255, 255));
        k4k1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k2.setForeground(new java.awt.Color(255, 255, 255));
        k1k2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k2.setForeground(new java.awt.Color(255, 255, 255));
        k2k2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k3.setForeground(new java.awt.Color(255, 255, 255));
        k1k3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k2.setForeground(new java.awt.Color(255, 255, 255));
        k3k2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k2.setForeground(new java.awt.Color(255, 255, 255));
        k4k2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k3.setForeground(new java.awt.Color(255, 255, 255));
        k2k3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k3.setForeground(new java.awt.Color(255, 255, 255));
        k3k3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k3.setForeground(new java.awt.Color(255, 255, 255));
        k4k3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k4.setForeground(new java.awt.Color(255, 255, 255));
        k1k4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k4.setForeground(new java.awt.Color(255, 255, 255));
        k2k4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k4.setForeground(new java.awt.Color(255, 255, 255));
        k3k4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k4.setForeground(new java.awt.Color(255, 255, 255));
        k4k4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelGradient6Layout = new javax.swing.GroupLayout(panelGradient6);
        panelGradient6.setLayout(panelGradient6Layout);
        panelGradient6Layout.setHorizontalGroup(
            panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient6Layout.createSequentialGroup()
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGradient6Layout.createSequentialGroup()
                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelGradient6Layout.createSequentialGroup()
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(panelGradient6Layout.createSequentialGroup()
                                                .addComponent(k3k1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(k3k2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(k3k3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelGradient6Layout.createSequentialGroup()
                                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient6Layout.createSequentialGroup()
                                                        .addComponent(k4k1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(k4k2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient6Layout.createSequentialGroup()
                                                        .addComponent(k2k1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(k2k2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelGradient6Layout.createSequentialGroup()
                                                        .addGap(24, 24, 24)
                                                        .addComponent(k2k3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient6Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(k4k3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(k3k4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(k2k4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(k4k4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelGradient6Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelGradient6Layout.createSequentialGroup()
                                        .addComponent(k1k1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(k1k2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(k1k3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(k1k4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelGradient6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))))
                .addContainerGap())
        );
        panelGradient6Layout.setVerticalGroup(
            panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(k1k2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k1k3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k1k4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(k1k1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(k2k1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k2k2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k2k3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k2k4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(k3k1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k3k2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k3k3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k3k4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelGradient6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(k4k1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k4k2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k4k3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k4k4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGradient7.setColorEnd(new java.awt.Color(153, 153, 153));
        panelGradient7.setColorStart(new java.awt.Color(0, 153, 153));

        jLabel50.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Prioritas Sub Kriteria sesuai Kriteria");

        jLabel51.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("K1");

        jLabel52.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("K4");

        jLabel53.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("K3");

        jLabel54.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("K2");

        PriorS11.setForeground(new java.awt.Color(255, 255, 255));
        PriorS11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel55.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("Prioritas Sub Kriteria");

        jLabel56.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("Prioritas Sub Kriteria");

        jLabel57.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("Prioritas Sub Kriteria");

        jLabel58.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("Prioritas Sub Kriteria");

        PriorS12.setForeground(new java.awt.Color(255, 255, 255));
        PriorS12.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS13.setForeground(new java.awt.Color(255, 255, 255));
        PriorS13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS14.setForeground(new java.awt.Color(255, 255, 255));
        PriorS14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS21.setForeground(new java.awt.Color(255, 255, 255));
        PriorS21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS33.setForeground(new java.awt.Color(255, 255, 255));
        PriorS33.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS31.setForeground(new java.awt.Color(255, 255, 255));
        PriorS31.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS34.setForeground(new java.awt.Color(255, 255, 255));
        PriorS34.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS22.setForeground(new java.awt.Color(255, 255, 255));
        PriorS22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS23.setForeground(new java.awt.Color(255, 255, 255));
        PriorS23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS24.setForeground(new java.awt.Color(255, 255, 255));
        PriorS24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS41.setForeground(new java.awt.Color(255, 255, 255));
        PriorS41.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS43.setForeground(new java.awt.Color(255, 255, 255));
        PriorS43.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS44.setForeground(new java.awt.Color(255, 255, 255));
        PriorS44.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        PriorS42.setForeground(new java.awt.Color(255, 255, 255));
        PriorS42.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        PriorS42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriorS42ActionPerformed(evt);
            }
        });

        PriorS32.setForeground(new java.awt.Color(255, 255, 255));
        PriorS32.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        PriorS32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriorS32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGradient7Layout = new javax.swing.GroupLayout(panelGradient7);
        panelGradient7.setLayout(panelGradient7Layout);
        panelGradient7Layout.setHorizontalGroup(
            panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient7Layout.createSequentialGroup()
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel50))
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel58)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelGradient7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS22, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS24, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(64, 64, 64)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PriorS31, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriorS33, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriorS34, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriorS32, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient7Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient7Layout.createSequentialGroup()
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PriorS42, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS41, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS43, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS44, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))))
        );
        panelGradient7Layout.setVerticalGroup(
            panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel50)
                .addGap(13, 13, 13)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57)
                    .addComponent(jLabel58))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(jLabel52))
                .addGap(8, 8, 8)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PriorS41, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(PriorS31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PriorS21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PriorS11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(PriorS23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PriorS33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGradient7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PriorS34, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorS24, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGradient7Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(PriorS42, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PriorS43, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PriorS44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGradient10.setColorEnd(new java.awt.Color(153, 153, 153));
        panelGradient10.setColorStart(new java.awt.Color(0, 153, 153));

        jLabel79.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Matriks Normalisasi");

        jLabel80.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("K1");

        jLabel81.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("K2");

        jLabel82.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("K3");

        jLabel83.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("K4");

        jLabel84.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("K1");

        jLabel85.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("K3");

        jLabel86.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("K2");

        jLabel87.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Prioritas");

        k1k1N.setForeground(new java.awt.Color(255, 255, 255));
        k1k1N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k1N.setForeground(new java.awt.Color(255, 255, 255));
        k2k1N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k1N.setForeground(new java.awt.Color(255, 255, 255));
        k3k1N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k1N.setForeground(new java.awt.Color(255, 255, 255));
        k4k1N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k2N.setForeground(new java.awt.Color(255, 255, 255));
        k1k2N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k2N.setForeground(new java.awt.Color(255, 255, 255));
        k2k2N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k2N.setForeground(new java.awt.Color(255, 255, 255));
        k3k2N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k2N.setForeground(new java.awt.Color(255, 255, 255));
        k4k2N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k1k3N.setForeground(new java.awt.Color(255, 255, 255));
        k1k3N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k3N.setForeground(new java.awt.Color(255, 255, 255));
        k2k3N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k3N.setForeground(new java.awt.Color(255, 255, 255));
        k3k3N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k3N.setForeground(new java.awt.Color(255, 255, 255));
        k4k3N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        jLabel88.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("K4");

        Prior1.setForeground(new java.awt.Color(255, 255, 255));
        Prior1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        Prior2.setForeground(new java.awt.Color(255, 255, 255));
        Prior2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        Prior3.setForeground(new java.awt.Color(255, 255, 255));
        Prior3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Prior3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Prior3ActionPerformed(evt);
            }
        });

        k1k4N.setForeground(new java.awt.Color(255, 255, 255));
        k1k4N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        Prior4.setForeground(new java.awt.Color(255, 255, 255));
        Prior4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k2k4N.setForeground(new java.awt.Color(255, 255, 255));
        k2k4N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k3k4N.setForeground(new java.awt.Color(255, 255, 255));
        k3k4N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        k4k4N.setForeground(new java.awt.Color(255, 255, 255));
        k4k4N.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        javax.swing.GroupLayout panelGradient10Layout = new javax.swing.GroupLayout(panelGradient10);
        panelGradient10.setLayout(panelGradient10Layout);
        panelGradient10Layout.setHorizontalGroup(
            panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient10Layout.createSequentialGroup()
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel79))
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel87))
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(k1k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(k1k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(k1k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(k1k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Prior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(k4k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(k4k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(k4k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(k4k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Prior4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGradient10Layout.createSequentialGroup()
                                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(k3k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(k3k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(k3k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(k3k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelGradient10Layout.createSequentialGroup()
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(k2k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(k2k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(k2k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(k2k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prior2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Prior3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGradient10Layout.setVerticalGroup(
            panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient10Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel79)
                .addGap(11, 11, 11)
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84)
                    .addComponent(jLabel86)
                    .addComponent(jLabel85)
                    .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel87)
                        .addComponent(jLabel88)))
                .addGap(6, 6, 6)
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel80))
                    .addComponent(k1k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k1k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k1k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Prior1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k1k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel81))
                    .addComponent(k2k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k2k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k2k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Prior2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k2k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel82))
                    .addComponent(k3k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k3k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k3k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Prior3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k3k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel83))
                    .addComponent(k4k1N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(k4k2N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelGradient10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(k4k3N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(k4k4N, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Prior4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGradient3Layout = new javax.swing.GroupLayout(panelGradient3);
        panelGradient3.setLayout(panelGradient3Layout);
        panelGradient3Layout.setHorizontalGroup(
            panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient3Layout.createSequentialGroup()
                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGradient3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelGradient3Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(94, 94, 94)
                                        .addComponent(namakaset, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelGradient3Layout.createSequentialGroup()
                                .addGap(259, 259, 259)
                                .addComponent(cbkodekaset, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelGradient3Layout.createSequentialGroup()
                                        .addComponent(btnmulaihitung, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelGradient3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(TotalNilai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelGradient3Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(panelGradient6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(panelGradient10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGradient3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(panelGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        panelGradient3Layout.setVerticalGroup(
            panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbkodekaset, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TotalNilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGradient3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnmulaihitung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGradient3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namakaset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelGradient3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGradient10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelGradient6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        scrollPaneWin112.setViewportView(panelGradient3);

        javax.swing.GroupLayout panelBlur2Layout = new javax.swing.GroupLayout(panelBlur2);
        panelBlur2.setLayout(panelBlur2Layout);
        panelBlur2Layout.setHorizontalGroup(
            panelBlur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlur2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBlur2Layout.setVerticalGroup(
            panelBlur2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBlur2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panelBlur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 830, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Bgsub.jpg"))); // NOI18N
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

    private void Prior3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Prior3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Prior3ActionPerformed

    private void PriorS32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriorS32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriorS32ActionPerformed

    private void PriorS42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriorS42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriorS42ActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String sql = "INSERT INTO seleksi VALUES (?,?,?)";
        try{
            PreparedStatement stat = koneksi.prepareStatement(sql);

            stat.setString(1, kodeKasetAlternatif);
            stat.setString(2, namaKasetAlternatif);
            stat.setString(3, TotalNilai.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATA berhasil disimpan");
            kosong();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Data gagal disimpan "+e);
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnmulaihitungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmulaihitungActionPerformed
        // TODO add your handling code here:
        getMatriksK();
        getMatriksNorK();
        getPrioritasSub();
        getAlternatif();
        getPenilaian();
        totalNilai = 0;
    }//GEN-LAST:event_btnmulaihitungActionPerformed

    private void cbkodekasetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkodekasetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkodekasetActionPerformed

    private void cbkodekasetItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbkodekasetItemStateChanged
        // TODO add your handling code here:
        String sql = "SELECT DISTINCT nama_kaset_game FROM penilaian_kaset WHERE kode_kaset='"+cbkodekaset.getSelectedItem().toString()+"';";
        try{
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String b = hasil.getString("nama_kaset_game");
                namakaset.setText(b);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_cbkodekasetItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ta8.RoundTextField Prior1;
    private ta8.RoundTextField Prior2;
    private ta8.RoundTextField Prior3;
    private ta8.RoundTextField Prior4;
    private ta8.RoundTextField PriorS11;
    private ta8.RoundTextField PriorS12;
    private ta8.RoundTextField PriorS13;
    private ta8.RoundTextField PriorS14;
    private ta8.RoundTextField PriorS21;
    private ta8.RoundTextField PriorS22;
    private ta8.RoundTextField PriorS23;
    private ta8.RoundTextField PriorS24;
    private ta8.RoundTextField PriorS31;
    private ta8.RoundTextField PriorS32;
    private ta8.RoundTextField PriorS33;
    private ta8.RoundTextField PriorS34;
    private ta8.RoundTextField PriorS41;
    private ta8.RoundTextField PriorS42;
    private ta8.RoundTextField PriorS43;
    private ta8.RoundTextField PriorS44;
    private ta8.RoundTextField TotalNilai;
    private Blurwarna.Blurwarna blurwarna1;
    private ta8.Buttonwarna btnmulaihitung;
    private ta8.Buttonwarna btnsimpan;
    private javax.swing.JComboBox<String> cbkodekaset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JPanel jPanel1;
    private ta8.RoundTextField k1k1;
    private ta8.RoundTextField k1k1N;
    private ta8.RoundTextField k1k2;
    private ta8.RoundTextField k1k2N;
    private ta8.RoundTextField k1k3;
    private ta8.RoundTextField k1k3N;
    private ta8.RoundTextField k1k4;
    private ta8.RoundTextField k1k4N;
    private ta8.RoundTextField k2k1;
    private ta8.RoundTextField k2k1N;
    private ta8.RoundTextField k2k2;
    private ta8.RoundTextField k2k2N;
    private ta8.RoundTextField k2k3;
    private ta8.RoundTextField k2k3N;
    private ta8.RoundTextField k2k4;
    private ta8.RoundTextField k2k4N;
    private ta8.RoundTextField k3k1;
    private ta8.RoundTextField k3k1N;
    private ta8.RoundTextField k3k2;
    private ta8.RoundTextField k3k2N;
    private ta8.RoundTextField k3k3;
    private ta8.RoundTextField k3k3N;
    private ta8.RoundTextField k3k4;
    private ta8.RoundTextField k3k4N;
    private ta8.RoundTextField k4k1;
    private ta8.RoundTextField k4k1N;
    private ta8.RoundTextField k4k2;
    private ta8.RoundTextField k4k2N;
    private ta8.RoundTextField k4k3;
    private ta8.RoundTextField k4k3N;
    private ta8.RoundTextField k4k4;
    private ta8.RoundTextField k4k4N;
    private ta8.RoundTextField namakaset;
    private ta8.PanelBlur panelBlur2;
    private ta8.PanelGradient panelGradient10;
    private ta8.PanelGradient panelGradient3;
    private ta8.PanelGradient panelGradient6;
    private ta8.PanelGradient panelGradient7;
    private scrollbar.ScrollPaneWin11 scrollPaneWin112;
    // End of variables declaration//GEN-END:variables
}
