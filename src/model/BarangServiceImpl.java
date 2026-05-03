package model;

import java.sql.PreparedStatement;

public class BarangServiceImpl extends BarangBase implements BarangService {

    @Override
    public void simpan() {
        connect();
        try {
            String sql = "INSERT INTO barang(nama_barang, jumlah, kondisi) VALUES(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        connect();
    }

    @Override
    public void hapus() {
        connect();
    }
}