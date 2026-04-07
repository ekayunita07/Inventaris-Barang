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
        tampilData();
}

    public void tampilData() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "SELECT t.*, b.nama_barang " +
             "FROM transaksi t LEFT JOIN barang b " +
             "ON t.id_barang = b.id_barang";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Nama_Barang");
        model.addColumn("Jenis_transaksi");
        model.addColumn("Jumlah");
        model.addColumn("Keterangan");

        while (rs.next()) {

        String tglDB = rs.getString("tanggal");
        String[] tgl = tglDB.split("-");
        String tanggalTampil = tgl[2] + "-" + tgl[1] + "-" + tgl[0];

        model.addRow(new Object[]{
            rs.getInt("id_transaksi"),
            tanggalTampil,
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("DATA TRANSAKSI");

        tblTransaksi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tanggal", "Nama Barang", "Jenis", "Jumlah", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblTransaksi);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Transaksi Barang");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Hapus");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Kembali");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(37, 37, 37)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(190, 190, 190))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FormTransaksi ft = new FormTransaksi(this);
            ft.setVisible(true);
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
        Dashboard fb = new Dashboard();
            fb.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    // End of variables declaration//GEN-END:variables
}
