package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class Laporan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Laporan.class.getName());

    public Laporan() {
        initComponents();
        tampilData();
    }
    
    private void tampilData() {
    try {
        Connection conn = Koneksi.getConnection();

        String sql = "SELECT transaksi.*, barang.nama_barang " +
                     "FROM transaksi " +
                     "LEFT JOIN barang ON transaksi.id_barang = barang.id_barang";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Tanggal");
        model.addColumn("Barang");
        model.addColumn("Jenis");
        model.addColumn("Jumlah");
        model.addColumn("Keterangan");

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_transaksi"),
                rs.getString("tanggal"),
                rs.getString("nama_barang"),
                rs.getString("jenis_transaksi"),
                rs.getInt("jumlah"),
                rs.getString("keterangan")
            });
        }

        tblLaporan.setModel(model);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("LAPORAN");

        tblLaporan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblLaporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tanggal", "Barang", "Jenis", "Jumlah", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblLaporan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(225, 225, 225))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLaporan;
    // End of variables declaration//GEN-END:variables
}
