package form;
import koneksi.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class FormBarang extends javax.swing.JFrame {
    
    int idBarang = 0;
    
    private static final java.util.logging.Logger logger = 
      java.util.logging.Logger.getLogger(FormBarang.class.getName());
    private DataBarang parent;
    public FormBarang(DataBarang parent) {
        initComponents();
        loadKategori();
        this.parent = parent;
        
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
    
    private void loadKategori() {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT * FROM kategori";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            jComboBox1.removeAllItems();

            while (rs.next()) {
                jComboBox1.addItem(rs.getInt("id_kategori") + " - " + rs.getString("nama_kategori"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    public void setData(int id, String nama, int jumlah, String kondisi, String kategori) {
        this.idBarang = id;

        jTextField1.setText(nama);
        jTextField2.setText(String.valueOf(jumlah));
        jTextField3.setText(kondisi);

        for (int i = 0; i < jComboBox1.getItemCount(); i++) {
            if (jComboBox1.getItemAt(i).contains(kategori)) {
                jComboBox1.setSelectedIndex(i);
                break;
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jBatal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 167, 38));
        jLabel1.setText("FORM BARANG");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 209, 128));
        jLabel2.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 209, 128));
        jLabel3.setText("Jumlah");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 209, 128));
        jLabel4.setText("Kondisi");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 209, 128));
        jLabel5.setText("Kategori");

        jTextField1.setBackground(new java.awt.Color(255, 243, 224));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(78, 52, 46));
        jTextField1.addActionListener(this::jTextField1ActionPerformed);

        jTextField2.setBackground(new java.awt.Color(255, 243, 224));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(78, 52, 46));
        jTextField2.addActionListener(this::jTextField2ActionPerformed);

        jTextField3.setBackground(new java.awt.Color(255, 243, 224));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(78, 52, 46));
        jTextField3.addActionListener(this::jTextField3ActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 209, 128));
        jLabel6.setText(":");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 209, 128));
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 209, 128));
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 209, 128));
        jLabel9.setText(":");

        jComboBox1.setBackground(new java.awt.Color(255, 243, 224));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(78, 52, 46));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setBackground(new java.awt.Color(245, 124, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SIMPAN");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jBatal.setBackground(new java.awt.Color(245, 124, 0));
        jBatal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jBatal.setForeground(new java.awt.Color(255, 255, 255));
        jBatal.setText("BATAL");
        jBatal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128), new java.awt.Color(255, 204, 128)));
        jBatal.addActionListener(this::jBatalActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3)
                    .addComponent(jTextField2)
                    .addComponent(jTextField1))
                .addContainerGap(89, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(120, 120, 120))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jBatal))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(715, 574));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try {
            Connection conn = Koneksi.getConnection();

            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Koneksi gagal!");
                return;
            }

            String nama = jTextField1.getText();
            int jumlah = Integer.parseInt(jTextField2.getText());
            String kondisi = jTextField3.getText();

            String selected = jComboBox1.getSelectedItem().toString();
            int idKategori = Integer.parseInt(selected.split(" - ")[0]);

            if (idBarang == 0) {
                // INSERT
                String sql = "INSERT INTO barang(nama_barang, jumlah, kondisi, id_kategori) VALUES(?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, nama);
                pst.setInt(2, jumlah);
                pst.setString(3, kondisi);
                pst.setInt(4, idKategori);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil ditambah!");

            } else {
                // UPDATE
                String sql = "UPDATE barang SET nama_barang=?, jumlah=?, kondisi=?, id_kategori=? WHERE id_barang=?";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, nama);
                pst.setInt(2, jumlah);
                pst.setString(3, kondisi);
                pst.setInt(4, idKategori);
                pst.setInt(5, idBarang);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            }
            
            if (parent != null) {
    parent.refreshTable();
}
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBatalActionPerformed
    if (parent != null) {
        parent.setVisible(true);
    }
    this.dispose();
    }//GEN-LAST:event_jBatalActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBatal;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
