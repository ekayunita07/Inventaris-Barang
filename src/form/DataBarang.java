package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class DataBarang extends javax.swing.JFrame {
    private boolean modeStokMenipis = false;
    
    private static final java.util.logging.Logger logger = 
        java.util.logging.Logger.getLogger(DataBarang.class.getName());
    
    public DataBarang() {
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
        
        
        jCekBarang.setText("CEK BARANG MENIPIS");
        tampilData();
        if (Session.role != null && Session.role.equals("petugas")) {
            jButton1.setVisible(false); // tambah
            jButton2.setVisible(false); // edit
        }
    }

    private void tampilData() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "SELECT barang.*, kategori.nama_kategori " +
             "FROM barang LEFT JOIN kategori " +
             "ON barang.id_kategori = kategori.id_kategori";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jumlah");
        model.addColumn("Kondisi");
        model.addColumn("Kategori");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah"),
                rs.getString("kondisi"),
                rs.getString("nama_kategori")
            });
        }

        tblBarang.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    public void tampilStokMenipis() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "SELECT barang.*, kategori.nama_kategori " +
                     "FROM barang LEFT JOIN kategori " +
                     "ON barang.id_kategori = kategori.id_kategori " +
                     "WHERE jumlah < (SELECT AVG(jumlah) FROM barang)";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jumlah");
        model.addColumn("Kondisi");
        model.addColumn("Kategori");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah"),
                rs.getString("kondisi"),
                rs.getString("nama_kategori")
            });
        }

        tblBarang.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    public void refreshTable() {
    tampilData();
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCekBarang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 167, 38));
        jLabel1.setText("DATA BARANG");

        tblBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama ", "Jumlah", "Kondisi", "Kategori"
            }
        ));
        jScrollPane1.setViewportView(tblBarang);

        jButton1.setBackground(new java.awt.Color(245, 124, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TAMBAH");
        jButton1.setActionCommand("Tambah");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(245, 124, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDIT");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton4.setBackground(new java.awt.Color(245, 124, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("KEMBALI");
        jButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jCekBarang.setBackground(new java.awt.Color(245, 124, 0));
        jCekBarang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jCekBarang.setForeground(new java.awt.Color(255, 255, 255));
        jCekBarang.setText("CEK BARANG MENIPIS");
        jCekBarang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jCekBarang.addActionListener(this::jCekBarangActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCekBarang)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(231, 231, 231))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCekBarang)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormBarang fb = new FormBarang(this);
            fb.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int baris = tblBarang.getSelectedRow();

        if (baris == -1) {
         JOptionPane.showMessageDialog(this, "Pilih data dulu!");
             return;
        }

        int id = Integer.parseInt(tblBarang.getValueAt(baris, 0).toString());
        String nama = tblBarang.getValueAt(baris, 1).toString();
        int jumlah = Integer.parseInt(tblBarang.getValueAt(baris, 2).toString());
        String kondisi = tblBarang.getValueAt(baris, 3).toString();
        String kategori = tblBarang.getValueAt(baris, 4).toString();

        FormBarang fb = new FormBarang(this);
        fb.setData(id, nama, jumlah, kondisi, kategori);
        fb.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     if (modeStokMenipis) {
        tampilData();
        modeStokMenipis = false;

        // kalau admin → tampilkan tombol
        if ("admin".equalsIgnoreCase(Session.role)) {
            jButton1.setVisible(true);
            jButton2.setVisible(true);
        }

        // kalau petugas → tetap disembunyikan
        return;
    }

    // kalau bukan mode menipis → baru keluar
    if ("admin".equalsIgnoreCase(Session.role)) {
        new Dashboard().setVisible(true);
    } else {
        new DashboardPetugas().setVisible(true);
    }

    this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCekBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCekBarangActionPerformed
       tampilStokMenipis();
       modeStokMenipis = true;
       
       jButton1.setVisible(false);
       jButton2.setVisible(false);
    }//GEN-LAST:event_jCekBarangActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new DataBarang().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jCekBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBarang;
    // End of variables declaration//GEN-END:variables
}
