/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ongkir;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class FormOngkir extends javax.swing.JFrame {

    /**
     * Creates new form FormOngkir
     */
    DefaultTableModel tabel1;
    Object[] list_brg = new Object[9]; //menyiappkan Array yang akan dimasukan ke table
    int x = 0; //integer untuk nomor ascending
    Koneksi dbConn = new Koneksi();
    Connection con = dbConn.getConnection();
    
    public FormOngkir() throws SQLException {
        initComponents();
        
        tabel1 = new DefaultTableModel();
        tabel.setModel(tabel1);
        
        //tambahkan Kolom pada tabel
        tabel1.addColumn("No."); tabel1.addColumn("Pengirim");
        tabel1.addColumn("Petugas"); tabel1.addColumn("Berat");
        tabel1.addColumn("Muatan"); tabel1.addColumn("Harga");
        
        setResizable(false); //tidak bisa diperbesar
        
        //tidak bisa diketik
        beratTxt.setEditable(false);
        muatan.setEditable(false);
        harga.setEditable(false);
        
        //tidak bisa diklik
        Simpan.setEnabled(false);
        Update.setEnabled(false);
        Hapus.setEnabled(false);
        Proses1.setEnabled(false);
        
        tampilkanDiTabel();
    }
    
    //Menampilkan data dari database ke dalam tabel
    public void  tampilkanDiTabel() throws SQLException{
        ArrayList[] list = getBrgList();
        DefaultTableModel model = (DefaultTableModel)tabel.getModel();
        
        Object[] row = new Object [9];
        for (int i = 0; i<list.length; i++){
            row [0] = i+1;
            row [1] = list[i].get (0); row [2] = list[i].get (1); 
            row [3] = list[i].get (2); row [4] = list[i].get (3); 
            row [5] = list[i].get (4);
            
            model.addRow (row);


        }
        
    }
    //Mengambil data dari DB
    public ArrayList[] getBrgList() throws SQLException{
        //Mendapatkan data dari DB dan disimpan di Array List
        String queryCount = "SELECT COUNT(*) AS c FROM brg";
        //menghitung jumlah record yg ada di tabel brg
        Statement st; //add import 
        ResultSet rsCount, rs;
        st = con.createStatement();
        rsCount = st.executeQuery(queryCount);
        int sizeTable = 0;
        while(rsCount.next()){
            sizeTable = rsCount.getInt("c"); //panggil alisa c
        }
        ArrayList[] brgList = new ArrayList[sizeTable];
        //membuat array list dengan size sebanyak data yang ada di Database
         String query = "SELECT * FROM brg"; //ambil semua data dari tabel brg
        rs = st.executeQuery (query);
        int x=0; 
        while (rs.next()){
            brgList[x] = new ArrayList<>();
            brgList[x].add (rs.getString ("pengirim")) ; 
            brgList[x].add (rs.getString ("petugas")) ;
            brgList[x].add (rs.getDouble ("berat")); 
            brgList[x].add (rs.getString ("muatan")); 
            brgList[x].add (rs.getString ("harga")); 
            x++;

        }
        return brgList;
    }
    //Untuk mengkosongkan semua text field
    //Digunakan saat menekan tombol “Tambah Lain”, “Simpan”, “Update”, “Hapus”

    public void kosongkanTextField(){
            pengirim.setText(""); petugas.setText(""); 
            panjang.setText(""); lebar.setText(""); tinggi.setText(""); 
            beratTxt.setText(""); muatan.setText(""); harga.setText("");  
    }

    //Untuk menghitung berat,muatan, dan harga dengan memanfaatkan method pada class Barang
    public void prosesHitungBerat(){
        try{
            String pr = pengirim.getText();
            String pt = petugas.getText();
            Double pj = Double.parseDouble(panjang.getText());
            Double le = Double.parseDouble(lebar.getText());
            Double ti = Double.parseDouble(tinggi.getText());

            Barang b = new Barang(pr, pt, pj, le,ti);
            beratTxt.setText(""+b.getBerat()) ; 
            muatan.setText(""+b.getMuatanBrg(b.getBerat()));
            harga.setText(""+b.getHarga(b.getMuatanBrg(b.getBerat()))) ;
            Simpan.setEnabled(true);
        }catch(NumberFormatException e){
                //JIka ada text field yg belum terisi
                JOptionPane.showMessageDialog(null, "Inputan Masih Kosong", "Warning", JOptionPane.WARNING_MESSAGE);
            }
    }
    //Untuk mengosongkan tabel  
    //Digunakan saat menekan tombol “Simpan”, “Update”, “Hapus”
    public void kosongkanTabel(){
        DefaultTableModel model = (DefaultTableModel)this.tabel.getModel();
        model.setRowCount(0);
        
    }
    //Untuk mengisi text filed dengan informasi baris tabel yang dipilih
    //Digunakan saat memilih salah satu baris di tabel
    public void terpilih(int index) throws SQLException{
        ArrayList[] list = getBrgList();
        pengirim.setText((String) list[index].get(0));
        petugas.setText((String) list[index].get(1));
        panjang.setText((String) list[index].get(2).toString());
        lebar.setText((String) list[index].get(3).toString());
        tinggi.setText((String) list[index].get(4).toString());
        
        Proses.setEnabled(false);
        Simpan.setEnabled(false);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pengirim = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        petugas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        panjang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lebar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tinggi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        beratTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        muatan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        Proses = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        Simpan = new javax.swing.JButton();
        Hapus = new javax.swing.JButton();
        Tambah = new javax.swing.JButton();
        Cetak = new javax.swing.JButton();
        Keluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        Proses1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FormServicePackage");

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form Service Package");

        jLabel2.setText("Nama Pengirim");

        pengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pengirimActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Petugas");

        petugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petugasActionPerformed(evt);
            }
        });

        jLabel4.setText("Panjang");

        panjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                panjangActionPerformed(evt);
            }
        });

        jLabel5.setText("Lebar");

        lebar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lebarActionPerformed(evt);
            }
        });

        jLabel6.setText("Tinggi");

        tinggi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tinggiActionPerformed(evt);
            }
        });

        jLabel7.setText("Berat");

        beratTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beratTxtActionPerformed(evt);
            }
        });

        jLabel8.setText("Muatan");

        muatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muatanActionPerformed(evt);
            }
        });

        jLabel9.setText("Harga");

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });

        Proses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-process-24.png"))); // NOI18N
        Proses.setText("Proses");
        Proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProsesActionPerformed(evt);
            }
        });

        Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-24.png"))); // NOI18N
        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-save-30.png"))); // NOI18N
        Simpan.setText("Simpan");
        Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SimpanActionPerformed(evt);
            }
        });

        Hapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-24.png"))); // NOI18N
        Hapus.setText("Hapus");
        Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusActionPerformed(evt);
            }
        });

        Tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-add-24.png"))); // NOI18N
        Tambah.setText("Tambah");
        Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahActionPerformed(evt);
            }
        });

        Cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-print-24.png"))); // NOI18N
        Cetak.setText("Cetak");
        Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CetakActionPerformed(evt);
            }
        });

        Keluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-logout-24.png"))); // NOI18N
        Keluar.setText("Keluar");
        Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeluarActionPerformed(evt);
            }
        });

        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        Proses1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-process-improvement-30.png"))); // NOI18N
        Proses1.setText("Proses");
        Proses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Proses1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(364, 364, 364))
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(beratTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Proses1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(muatan, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(68, 68, 68))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(Simpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Hapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(15, 15, 15)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lebar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(tinggi, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Proses, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(207, 207, 207))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pengirim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lebar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(panjang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(beratTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(muatan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Proses, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Update, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Proses1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(petugas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tinggi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(harga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 193, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        String updateQuery=null;
        PreparedStatement ps=null;
        
        updateQuery = "UPDATE brg SET pengirim=?,petugas=?, berat=?,muatan=?,harga=? WHERE pengirim=?";
        
        try{
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, pengirim.getText()); ps.setString(2, petugas.getText()); 
            ps.setString(3, beratTxt.getText()); ps.setString(4, muatan.getText());
            ps.setString(5, harga.getText()); 
            ps.setString(6, pengirim.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate");
        }catch  (SQLException ex){
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data gagal diupdate");
        }
        kosongkanTextField();
        kosongkanTabel();
        try {
            tampilkanDiTabel();
        } catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void muatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_muatanActionPerformed

    private void beratTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beratTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beratTxtActionPerformed

    private void tinggiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tinggiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tinggiActionPerformed

    private void lebarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lebarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lebarActionPerformed

    private void panjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panjangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_panjangActionPerformed

    private void petugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_petugasActionPerformed

    private void pengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pengirimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pengirimActionPerformed

    private void ProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProsesActionPerformed
       prosesHitungBerat();
    }//GEN-LAST:event_ProsesActionPerformed

    private void KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeluarActionPerformed
        dispose();
    }//GEN-LAST:event_KeluarActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        int index = tabel.getSelectedRow();
        try {
            terpilih(index);
        } catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
        }
        Proses1.setEnabled(true);
        Hapus.setEnabled(true);
    }//GEN-LAST:event_tabelMouseClicked

    private void Proses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Proses1ActionPerformed
        prosesHitungBerat();
        Update.setEnabled(true);
        Simpan.setEnabled(false);
    }//GEN-LAST:event_Proses1ActionPerformed

    private void SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SimpanActionPerformed
         try{ 
             PreparedStatement ps = con.prepareStatement("INSERT INTO brg(pengirim, petugas, berat, muatan, harga)VALUES(?,?,?,?,?)");
             ps.setString(1, pengirim.getText()); ps.setString(2, petugas.getText()); 
             ps.setString(3, beratTxt.getText()); ps.setString(4, muatan.getText());
             ps.setString(5, harga.getText());
             ps.executeUpdate();
             JOptionPane.showMessageDialog(null, "Data Tersimpan");
         }catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan");
         }
        kosongkanTextField();
        kosongkanTabel();
        try {
            tampilkanDiTabel();
        } catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SimpanActionPerformed

    private void TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahActionPerformed
       kosongkanTextField();
       Proses.setEnabled(true);
       
       beratTxt.setEditable(false);
       muatan.setEditable(false);
       harga.setEditable(false);
       
       Simpan.setEnabled(false);
       Update.setEnabled(false);
       Hapus.setEnabled(false);
       Proses1.setEnabled(false);
    }//GEN-LAST:event_TambahActionPerformed

    private void HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusActionPerformed
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM brg WHERE pengirim=?");
            ps.setString(1, pengirim.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Terhapus");
        } catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Data Gagal terhapus");
        }
        kosongkanTextField();
        kosongkanTabel();
        try {
            tampilkanDiTabel();
        } catch (SQLException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_HapusActionPerformed

    private void CetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CetakActionPerformed
        JasperReport reports;
        
        String path=".\\src\\ongkir\\reportBrg.jasper";
        try {
            reports = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null, con);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CetakActionPerformed

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
            java.util.logging.Logger.getLogger(FormOngkir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOngkir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOngkir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOngkir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormOngkir().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(FormOngkir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cetak;
    private javax.swing.JButton Hapus;
    private javax.swing.JButton Keluar;
    private javax.swing.JButton Proses;
    private javax.swing.JButton Proses1;
    private javax.swing.JButton Simpan;
    private javax.swing.JButton Tambah;
    private javax.swing.JButton Update;
    private javax.swing.JTextField beratTxt;
    private javax.swing.JTextField harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField lebar;
    private javax.swing.JTextField muatan;
    private javax.swing.JTextField panjang;
    private javax.swing.JTextField pengirim;
    private javax.swing.JTextField petugas;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField tinggi;
    // End of variables declaration//GEN-END:variables
}
