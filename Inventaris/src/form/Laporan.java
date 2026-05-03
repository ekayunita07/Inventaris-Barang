package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class Laporan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = 
      java.util.logging.Logger.getLogger(Laporan.class.getName());

    public Laporan() {
        initComponents();
        tampilData();
        
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
    }
    
    private void tampilData() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "{CALL getLaporan()}";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Petugas");
        model.addColumn("Tanggal");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Barang");
        model.addColumn("Jenis");
        model.addColumn("Jumlah");
        model.addColumn("Keterangan");

    while (rs.next()) {

        // ubah tanggal
        String tgl = rs.getString("tanggal");
        String[] t1 = tgl.split("-");
        String tanggal = t1[2] + "-" + t1[1] + "-" + t1[0];

        // ubah tanggal kembali
        String tglK = rs.getString("tanggal_kembali");
        String tanggalKembali = "-";

    if (tglK != null) {
        String[] t2 = tglK.split("-");
        tanggalKembali = t2[2] + "-" + t2[1] + "-" + t2[0];
    }

    model.addRow(new Object[]{
        rs.getInt("id_transaksi"),
        rs.getString("nama_petugas"),
        tanggal,
        tanggalKembali,
        rs.getString("nama_barang"),
        rs.getString("jenis_transaksi"),
        rs.getInt("jumlah"),
        rs.getString("keterangan")
    });
}

    tblLaporan.setModel(model);

    // TOTAL DIPINJAM
    String sqlPinjam = "SELECT SUM(jumlah) AS total FROM v_transaksi WHERE jenis_transaksi='Dipinjam'";
    ResultSet rsPinjam = conn.createStatement().executeQuery(sqlPinjam);

    if (rsPinjam.next()) {
        int totalPinjam = rsPinjam.getInt("total");
        lblTotalPinjam.setText("Total Barang Dipinjam : " + totalPinjam);
    }

    // TOTAL DIKEMBALIKAN
    String sqlKembali = "SELECT SUM(jumlah) AS total FROM v_transaksi WHERE jenis_transaksi='Dikembalikan'";
    ResultSet rsKembali = conn.createStatement().executeQuery(sqlKembali);

        if (rsKembali.next()) {
            int totalKembali = rsKembali.getInt("total");
            lblTotalKembali.setText("Total Barang Dikembalikan : " + totalKembali);
        }
        // FUNCTION (TOTAL SEMUA BARANG)
        String sqlFunction = "SELECT totalBarang() AS total";
        ResultSet rsFunc = conn.createStatement().executeQuery(sqlFunction);

        if (rsFunc.next()) {
            int total = rsFunc.getInt("total");
            lblTotalBarang.setText("Total Semua Barang : " + total);
        }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLaporan = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        lblTotalPinjam = new javax.swing.JLabel();
        lblTotalKembali = new javax.swing.JLabel();
        lblTotalBarang = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 167, 38));
        jLabel1.setText("LAPORAN");

        tblLaporan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama Petugas", "Tanggal", "Tanggal Kembali", "Barang", "Jenis", "Jumlah", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblLaporan);

        jButton1.setBackground(new java.awt.Color(245, 124, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("KEMBALI");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        lblTotalPinjam.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalPinjam.setForeground(new java.awt.Color(255, 209, 128));
        lblTotalPinjam.setText("Total Barang Dipinjam          :");

        lblTotalKembali.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalKembali.setForeground(new java.awt.Color(255, 209, 128));
        lblTotalKembali.setText("Total Barang Dikembalikan  :");

        lblTotalBarang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotalBarang.setForeground(new java.awt.Color(255, 209, 128));
        lblTotalBarang.setText("Total Barang                          :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalPinjam)
                            .addComponent(lblTotalKembali)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalBarang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(354, 354, 354))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalPinjam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalBarang)
                        .addContainerGap(108, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Dashboard fb = new Dashboard();
            fb.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Laporan().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotalBarang;
    private javax.swing.JLabel lblTotalKembali;
    private javax.swing.JLabel lblTotalPinjam;
    private javax.swing.JTable tblLaporan;
    // End of variables declaration//GEN-END:variables
}
