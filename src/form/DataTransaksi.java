package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DataTransaksi extends javax.swing.JFrame {
    public DataTransaksi() {
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
        
        
        jTransaksiUlang.setText("Transaksi Ulang");
        jTransaksiUlang.addActionListener(this::jTransaksiUlangActionPerformed);
        tampilData();
        if (Session.role.equals("petugas")) {
        jButton2.setVisible(false);
}
}

    public void tampilData() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "SELECT * FROM v_transaksi ";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Petugas");
        model.addColumn("Tanggal");
        model.addColumn("Tanggal Kembali");
        model.addColumn("Nama_Barang");
        model.addColumn("Jenis_transaksi");
        model.addColumn("Jumlah");
        model.addColumn("Keterangan");

        while (rs.next()) {

        String tglDB = rs.getString("tanggal");
        String tglKembaliDB = rs.getString("tanggal_kembali");

        String tanggalKembaliTampil = "-";
        if (tglKembaliDB != null) {
            String[] tgl2 = tglKembaliDB.split("-");
            tanggalKembaliTampil = tgl2[2] + "-" + tgl2[1] + "-" + tgl2[0];
        }
        String[] tgl = tglDB.split("-");
        String tanggalTampil = tgl[2] + "-" + tgl[1] + "-" + tgl[0];

        model.addRow(new Object[]{
            rs.getInt("id_transaksi"),
            rs.getString("nama_petugas"),
            tanggalTampil,
            tanggalKembaliTampil,
            rs.getString("nama_barang"),
            rs.getString("jenis_transaksi"),
            rs.getInt("jumlah"),
            rs.getString("keterangan")
        });
    }

        tblTransaksi.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DataTransaksi.class.getName());



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jTransaksiUlang = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 167, 38));
        jLabel1.setText("DATA TRANSAKSI");

        tblTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nama Petugas", "Tanggal", "Tanggal Kembali", "Nama Barang", "Jenis", "Jumlah", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        jButton1.setBackground(new java.awt.Color(245, 124, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TRANSAKSI BARANG");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setBackground(new java.awt.Color(245, 124, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("HAPUS");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(245, 124, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("KEMBALI");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButtonEdit.setBackground(new java.awt.Color(245, 124, 0));
        jButtonEdit.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButtonEdit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEdit.setText("EDIT");
        jButtonEdit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButtonEdit.addActionListener(this::jButtonEditActionPerformed);

        jTransaksiUlang.setBackground(new java.awt.Color(245, 124, 0));
        jTransaksiUlang.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jTransaksiUlang.setForeground(new java.awt.Color(255, 255, 255));
        jTransaksiUlang.setText("TRANSAKSI ULANG");
        jTransaksiUlang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jTransaksiUlang.addActionListener(this::jTransaksiUlangActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(330, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(287, 287, 287))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTransaksiUlang, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1)
                    .addComponent(jButtonEdit)
                    .addComponent(jTransaksiUlang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       FormTransaksi ft = new FormTransaksi(this);
        ft.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int baris = tblTransaksi.getSelectedRow();

if (baris == -1) {
    JOptionPane.showMessageDialog(this, "Pilih data dulu!");
    return;
}

int konfirmasi = JOptionPane.showConfirmDialog(this, "Yakin hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

if (konfirmasi == JOptionPane.YES_OPTION) {
    try {
        Connection conn = Koneksi.getConnection();

        int id = Integer.parseInt(tblTransaksi.getValueAt(baris, 0).toString());

        String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        tampilData();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (Session.role.equals("admin")) {
            new Dashboard().setVisible(true);
        } else {
            new DashboardPetugas().setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        int baris = tblTransaksi.getSelectedRow();

    if (baris == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data dulu!");
        return;
    }

    int id = Integer.parseInt(tblTransaksi.getValueAt(baris, 0).toString());

    FormTransaksi ft = new FormTransaksi(this, id);

ft.loadJenis();       // 🔥 WAJIB
ft.loadDataEdit();    // 🔥 WAJIB

ft.setVisible(true);
this.setVisible(false);
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTransaksiUlangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksiUlangActionPerformed
       int baris = tblTransaksi.getSelectedRow();

    if (baris == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data dulu!");
        return;
    }

    int id = Integer.parseInt(tblTransaksi.getValueAt(baris, 0).toString());

    FormTransaksi ft = new FormTransaksi(this, id);

    ft.setTransaksiUlang(true); // aktifkan mode dulu
    ft.loadJenis();             // set jenis sesuai mode
    ft.loadDataEdit();          // load data terakhir

    ft.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_jTransaksiUlangActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new DataTransaksi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jTransaksiUlang;
    private javax.swing.JTable tblTransaksi;
    // End of variables declaration//GEN-END:variables
}
