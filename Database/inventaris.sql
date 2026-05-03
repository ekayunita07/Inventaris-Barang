-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Bulan Mei 2026 pada 11.17
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventaris`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getLaporan` ()   BEGIN
    SELECT * FROM v_transaksi;
END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `totalBarang` () RETURNS INT(11) DETERMINISTIC BEGIN
    DECLARE total INT;

    SELECT SUM(jumlah) INTO total FROM barang;

    RETURN total;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `kondisi` varchar(50) DEFAULT NULL,
  `id_kategori` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `jumlah`, `kondisi`, `id_kategori`) VALUES
(1, 'Mouse', 5, 'Baik', 2),
(6, 'Chip', 4, 'Baik', 2),
(11, 'Laptop', 6, 'baik', 1),
(12, 'Kyboard', 10, 'Baik', 1),
(13, 'mouse', 2, 'baik', 1),
(14, 'mouse', 2, 'baik', 1),
(15, 'mouse', 2, 'baik', 1),
(16, 'mouse', 2, 'baik', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Komputer'),
(2, 'Aksesoris');

-- --------------------------------------------------------

--
-- Struktur dari tabel `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(11) NOT NULL,
  `nama_petugas` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` enum('admin','petugas') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `nama_petugas`, `username`, `password`, `role`) VALUES
(2, 'Alfina', 'alfina', '4321', 'admin'),
(3, 'Alexa', 'alexa', '1234', 'petugas'),
(4, 'yun', 'yun', '2306', 'admin'),
(6, 'intan', 'intan', '123', 'admin'),
(7, 'Eka', 'Eka123', '1234', 'admin'),
(8, 'Admin', 'Admin', 'Admin123', 'admin'),
(9, 'Petugas', 'Petugas', 'Petugas123', 'petugas');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) DEFAULT NULL,
  `id_petugas` int(11) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `jenis_transaksi` varchar(20) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `tanggal_kembali` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_barang`, `id_petugas`, `tanggal`, `jenis_transaksi`, `jumlah`, `keterangan`, `tanggal_kembali`) VALUES
(38, 6, 2, '2026-04-27', 'Dipinjam', 4, 'Praktik', '2026-04-29'),
(39, 1, 2, '2026-04-28', 'Dipinjam', 2, 'Praktikum', '2026-04-30'),
(40, 1, 2, '2026-04-28', 'Dikembalikan', 2, 'Praktikum', '2026-04-30'),
(41, 1, 2, '2026-04-27', 'Dipinjam', 2, 'praktikum', '2026-04-28'),
(42, 1, 2, '2026-04-27', 'Dikembalikan', 2, 'praktikum', '2026-04-28');

--
-- Trigger `transaksi`
--
DELIMITER $$
CREATE TRIGGER `trg_delete_transaksi` AFTER DELETE ON `transaksi` FOR EACH ROW BEGIN
    IF OLD.jenis_transaksi = 'Dipinjam' THEN
        UPDATE barang SET jumlah = jumlah + OLD.jumlah
        WHERE id_barang = OLD.id_barang;
    ELSEIF OLD.jenis_transaksi = 'Dikembalikan' THEN
        UPDATE barang SET jumlah = jumlah - OLD.jumlah
        WHERE id_barang = OLD.id_barang;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_insert_transaksi` AFTER INSERT ON `transaksi` FOR EACH ROW BEGIN
    IF NEW.jenis_transaksi = 'Dipinjam' THEN
        UPDATE barang
        SET jumlah = jumlah - NEW.jumlah
        WHERE id_barang = NEW.id_barang;

    ELSEIF NEW.jenis_transaksi = 'Dikembalikan' THEN
        UPDATE barang
        SET jumlah = jumlah + NEW.jumlah
        WHERE id_barang = NEW.id_barang;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_update_transaksi` AFTER UPDATE ON `transaksi` FOR EACH ROW BEGIN
    -- BALIKKAN STOK LAMA
    IF OLD.jenis_transaksi = 'Dipinjam' THEN
        UPDATE barang SET jumlah = jumlah + OLD.jumlah
        WHERE id_barang = OLD.id_barang;
    ELSEIF OLD.jenis_transaksi = 'Dikembalikan' THEN
        UPDATE barang SET jumlah = jumlah - OLD.jumlah
        WHERE id_barang = OLD.id_barang;
    END IF;

    -- TERAPKAN DATA BARU
    IF NEW.jenis_transaksi = 'Dipinjam' THEN
        UPDATE barang SET jumlah = jumlah - NEW.jumlah
        WHERE id_barang = NEW.id_barang;
    ELSEIF NEW.jenis_transaksi = 'Dikembalikan' THEN
        UPDATE barang SET jumlah = jumlah + NEW.jumlah
        WHERE id_barang = NEW.id_barang;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_transaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_transaksi` (
`id_transaksi` int(11)
,`tanggal` date
,`tanggal_kembali` date
,`jenis_transaksi` varchar(20)
,`jumlah` int(11)
,`keterangan` text
,`nama_barang` varchar(100)
,`nama_petugas` varchar(100)
);

-- --------------------------------------------------------

--
-- Struktur untuk view `v_transaksi`
--
DROP TABLE IF EXISTS `v_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_transaksi`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `t`.`tanggal` AS `tanggal`, `t`.`tanggal_kembali` AS `tanggal_kembali`, `t`.`jenis_transaksi` AS `jenis_transaksi`, `t`.`jumlah` AS `jumlah`, `t`.`keterangan` AS `keterangan`, `b`.`nama_barang` AS `nama_barang`, `p`.`nama_petugas` AS `nama_petugas` FROM ((`transaksi` `t` join `barang` `b` on(`t`.`id_barang` = `b`.`id_barang`)) join `petugas` `p` on(`t`.`id_petugas` = `p`.`id_petugas`)) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_petugas` (`id_petugas`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
