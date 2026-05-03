package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class FormTransaksi extends javax.swing.JFrame {
    private DataTransaksi parent;
    private int id_petugas;
  
    private boolean isTransaksiUlangMode = false;
    private boolean isEdit = false;
    private boolean isTransaksiUlang = false;
    private int id_transaksi;
    
    private static final java.util.logging.Logger logger = 
      java.util.logging.Logger.getLogger(FormTransaksi.class.getName());

public FormTransaksi(DataTransaksi parent) {

     initComponents();
     
     setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    setLocationRelativeTo(null);

    // Ambil panel utama
    javax.swing.JPanel panel = (javax.swing.JPanel) getContentPane();

    // Custom JLabel biar gambar auto resize
    javax.swing.JLabel background = new javax.swing.JLabel() {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
                getClass().getResource("/gambar/bg_all.png")
            );

            java.awt.Image img = icon.getImage();

            // Scale gambar sesuai ukuran window
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };

    background.setLayout(new java.awt.GridBagLayout());

    setContentPane(background);

    panel.setOpaque(false);

    background.add(panel, new java.awt.GridBagConstraints());

    // 🔥 VALIDASI SESSION DI SINI
    if (Session.id_petugas == 0) {
        JOptionPane.showMessageDialog(this, "Harus login dulu!");
        new FormLogin().setVisible(true);
        this.dispose();
        return;
    }

    // DEBUG (opsional tapi penting)
    System.out.println("MASUK FORM, SESSION ID: " + Session.id_petugas);

    this.parent = parent;
    this.id_petugas = Session.id_petugas;

    txtPetugas.setText(Session.nama);

    loadBarang();
    loadJenis();
}
    
    public FormTransaksi(DataTransaksi parent, int id_transaksi) {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    setLocationRelativeTo(null);

    // background (biar konsisten sama constructor atas)
    javax.swing.JPanel panel = (javax.swing.JPanel) getContentPane();

    javax.swing.JLabel background = new javax.swing.JLabel() {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);

            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(
                getClass().getResource("/gambar/bg_all.png")
            );

            java.awt.Image img = icon.getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    };

    background.setLayout(new java.awt.GridBagLayout());
    setContentPane(background);
    panel.setOpaque(false);
    background.add(panel, new java.awt.GridBagConstraints());

    this.parent = parent;
    this.id_transaksi = id_transaksi;
    this.isEdit = true;

    txtPetugas.setText(Session.nama);

    loadBarang();
    }
    
    
    
    private void loadBarang() {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM barang";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        cmbBarang.removeAllItems(); // 🔥 WAJIB (biar nggak numpuk)

        while (rs.next()) {
            cmbBarang.addItem(
                rs.getInt("id_barang") + " - " + rs.getString("nama_barang")
            );
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    
public void loadJenis() {
    cmbJenis.removeAllItems();

    if (isEdit) {
        cmbJenis.addItem("Dipinjam");
        cmbJenis.addItem("Dikembalikan");
    } else {
        cmbJenis.addItem("Dipinjam");
    }
}

public void loadDataEdit() {
    try {
        Connection conn = Koneksi.getConnection();
        String sql = "SELECT * FROM transaksi WHERE id_transaksi=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id_transaksi);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            txtJumlah.setText(String.valueOf(rs.getInt("jumlah")));
            txtKeterangan.setText(rs.getString("keterangan"));

            cmbJenis.setSelectedItem(rs.getString("jenis_transaksi"));

            // tanggal pinjam
            java.util.Date tgl = new java.text.SimpleDateFormat("yyyy-MM-dd")
                    .parse(rs.getString("tanggal"));
            jDateChooser2.setDate(tgl);

            // tanggal kembali (ambil dari DB)
            if (rs.getString("tanggal_kembali") != null) {
                java.util.Date tglKembali = new java.text.SimpleDateFormat("yyyy-MM-dd")
                        .parse(rs.getString("tanggal_kembali"));
                jDateKembali.setDate(tglKembali);
            }

            // biar tidak diubah
            if (!isTransaksiUlangMode) {
    jDateKembali.setEnabled(false);
}
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

public void setTransaksiUlang(boolean status) {
    this.isTransaksiUlang = status;
    this.isTransaksiUlangMode = status;

    if (status) {
        isEdit = false;

        cmbBarang.setEnabled(false);
        txtJumlah.setEnabled(false);
        txtKeterangan.setEnabled(false);

        cmbJenis.removeAllItems();
        cmbJenis.addItem("Dikembalikan");
        cmbJenis.setEnabled(false);

        jDateChooser2.setEnabled(false);
        jDateKembali.setEnabled(true);
    }
}

private boolean isProcessing = false;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        cmbBarang = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        cmbJenis = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateKembali = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPetugas = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 167, 38));
        jLabel1.setText("FORM TRANSAKSI");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 128));
        jLabel2.setText("Tanggal");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 209, 128));
        jLabel3.setText("Barang");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 209, 128));
        jLabel4.setText("Jenis");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 209, 128));
        jLabel5.setText("Jumlah");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 209, 128));
        jLabel6.setText("Keterangan");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 209, 128));
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 209, 128));
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 209, 128));
        jLabel9.setText(":");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 209, 128));
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 209, 128));
        jLabel11.setText(":");

        txtJumlah.setBackground(new java.awt.Color(255, 243, 224));
        txtJumlah.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtJumlah.setForeground(new java.awt.Color(78, 52, 46));
        txtJumlah.addActionListener(this::txtJumlahActionPerformed);

        txtKeterangan.setBackground(new java.awt.Color(255, 243, 224));
        txtKeterangan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtKeterangan.setForeground(new java.awt.Color(78, 52, 46));
        txtKeterangan.addActionListener(this::txtKeteranganActionPerformed);

        cmbBarang.setBackground(new java.awt.Color(255, 243, 224));
        cmbBarang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cmbBarang.setForeground(new java.awt.Color(78, 52, 46));
        cmbBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        cmbBarang.addActionListener(this::cmbBarangActionPerformed);

        jButton1.setBackground(new java.awt.Color(245, 124, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIMPAN");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        cmbJenis.setBackground(new java.awt.Color(255, 243, 224));
        cmbJenis.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cmbJenis.setForeground(new java.awt.Color(78, 52, 46));
        cmbJenis.addActionListener(this::cmbJenisActionPerformed);

        jDateChooser2.setBackground(new java.awt.Color(255, 243, 224));
        jDateChooser2.setForeground(new java.awt.Color(78, 52, 46));

        jDateKembali.setBackground(new java.awt.Color(255, 243, 224));
        jDateKembali.setForeground(new java.awt.Color(78, 52, 46));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 209, 128));
        jLabel12.setText("Tanggal Kembali");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 209, 128));
        jLabel13.setText(":");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 209, 128));
        jLabel14.setText("Petugas");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 209, 128));
        jLabel15.setText(":");

        txtPetugas.setBackground(new java.awt.Color(255, 243, 224));
        txtPetugas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPetugas.setForeground(new java.awt.Color(255, 209, 128));

        jButton2.setBackground(new java.awt.Color(245, 124, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BATAL");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtKeterangan)
                    .addComponent(txtJumlah)
                    .addComponent(cmbJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 126, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(94, 94, 94))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPetugas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(25, 25, 25)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (isProcessing) return;
    isProcessing = true;

    Connection conn = null;

    try {
        conn = Koneksi.getConnection();
        conn.setAutoCommit(false);

        // ================= VALIDASI SESSION =================
        if (Session.id_petugas == 0) {
            JOptionPane.showMessageDialog(this, "Harus login dulu!");
            return;
        }

        // ================= TANGGAL =================
        java.util.Date date = jDateChooser2.getDate();
        if (date == null) {
            JOptionPane.showMessageDialog(this, "Tanggal harus diisi!");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = sdf.format(date);

        // ================= BARANG =================
        String selected = cmbBarang.getSelectedItem().toString();
        int idBarang = Integer.parseInt(selected.split(" - ")[0]);

        // ================= JENIS =================
        String jenis = cmbJenis.getSelectedItem().toString();

        // ================= JUMLAH =================
        int jumlah = Integer.parseInt(txtJumlah.getText());

        // ================= KETERANGAN =================
        String keterangan = txtKeterangan.getText();

        String tanggalKembali = null;
        java.util.Date dateKembali = jDateKembali.getDate();

        if (dateKembali != null) {
            tanggalKembali = sdf.format(dateKembali);
        }

        // ================== EDIT ==================
        if (isEdit) {

            String sql = "UPDATE transaksi SET tanggal=?, tanggal_kembali=?, id_barang=?, jenis_transaksi=?, jumlah=?, keterangan=? WHERE id_transaksi=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, tanggal);
            pst.setString(2, tanggalKembali);
            pst.setInt(3, idBarang);
            pst.setString(4, jenis);
            pst.setInt(5, jumlah);
            pst.setString(6, keterangan);
            pst.setInt(7, id_transaksi);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        }

        // ================== TAMBAH ==================
        else {

    // 🔥 CEK STOK HANYA SAAT PINJAM DAN BUKAN TRANSAKSI ULANG
    if (!isTransaksiUlangMode && jenis.equals("Dipinjam")) {
        String cek = "SELECT jumlah FROM barang WHERE id_barang=?";
        PreparedStatement pstCek = conn.prepareStatement(cek);
        pstCek.setInt(1, idBarang);
        ResultSet rs = pstCek.executeQuery();

        if (rs.next()) {
            int stok = rs.getInt("jumlah");
            if (stok < jumlah) {
                JOptionPane.showMessageDialog(this, "Stok tidak cukup!");
                return;
            }
        }
    }

    String sql = "INSERT INTO transaksi(tanggal, tanggal_kembali, id_barang, jenis_transaksi, jumlah, keterangan, id_petugas) VALUES(?,?,?,?,?,?,?)";
    PreparedStatement pst = conn.prepareStatement(sql);

    pst.setString(1, tanggal);
    pst.setString(2, tanggalKembali);
    pst.setInt(3, idBarang);
    pst.setString(4, jenis);
    pst.setInt(5, jumlah);
    pst.setString(6, keterangan);
    pst.setInt(7, Session.id_petugas);

    pst.executeUpdate();

    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
}

        conn.commit();

        parent.tampilData();
        parent.setVisible(true);
        this.dispose();

    } catch (Exception e) {

        try {
            if (conn != null) conn.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, e.getMessage());

    } finally {
        isProcessing = false;

        try {
            if (conn != null) conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBarangActionPerformed

    private void cmbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJenisActionPerformed

    private void txtKeteranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeteranganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeteranganActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if (parent != null) {
            parent.setVisible(true); // tampilkan DataTransaksi lagi
        } else {
            new DataTransaksi().setVisible(true); // kalau null (backup)
        }
        this.dispose(); // tutup form transaksi
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> 
        new FormLogin().setVisible(true)
    );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbBarang;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JLabel txtPetugas;
    // End of variables declaration//GEN-END:variables
}
