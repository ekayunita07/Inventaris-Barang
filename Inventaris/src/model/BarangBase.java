package model;

import koneksi.Koneksi;
import java.sql.Connection;

public abstract class BarangBase {

    protected Connection conn;

    public void connect() {
        conn = Koneksi.getConnection();
    }

    public abstract void simpan();
    public abstract void update();
}