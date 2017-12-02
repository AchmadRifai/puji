-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versi server:                 10.2.6-MariaDB - mariadb.org binary distribution
-- OS Server:                    Win64
-- HeidiSQL Versi:               9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Membuang struktur basisdata untuk resto
CREATE DATABASE IF NOT EXISTS `resto` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `resto`;
-- membuang struktur untuk table resto.kat_menu
CREATE TABLE IF NOT EXISTS `kat_menu` (
  `kode` int(11) NOT NULL,
  `nama` varchar(15) NOT NULL,
  `gbr` text NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.kat_menu: ~6 rows (lebih kurang)
/*!40000 ALTER TABLE `kat_menu` DISABLE KEYS */;
INSERT INTO `kat_menu` (`kode`, `nama`, `gbr`) VALUES
	(1, 'Hot Drink', 'img/hot%20drink.png'),
	(2, 'Soft Drink', 'img/soft%20drink.png'),
	(3, 'Juice', 'img/juice.png'),
	(4, 'Paket', 'img/paket.png'),
	(5, 'Sayur', 'img/sayur.png'),
	(6, 'Seafood', 'img/seafood.png');
/*!40000 ALTER TABLE `kat_menu` ENABLE KEYS */;

-- membuang struktur untuk table resto.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `kode` varchar(20) NOT NULL,
  `kat` int(11) NOT NULL,
  `harga` text NOT NULL,
  `gbr` text NOT NULL,
  `nama` varchar(30) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  PRIMARY KEY (`kode`),
  KEY `FK__kat_menu` (`kat`),
  CONSTRAINT `FK__kat_menu` FOREIGN KEY (`kat`) REFERENCES `kat_menu` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.menu: ~49 rows (lebih kurang)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`kode`, `kat`, `harga`, `gbr`, `nama`, `satuan`) VALUES
	('MKN001', 4, '21000', 'img/Makanan/Menu%20Paket/ayambakar.jpg', 'Ayam Bakar', 'porsi'),
	('MKN002', 4, '27000', 'img/Makanan/Menu%20Paket/ayamgorenglombokijo.jpg', 'Ayam Goreng Lombok Ijo', 'porsi'),
	('MKN003', 4, '21000', 'img/Makanan/Menu%20Paket/ayamgoreng.jpg', 'Ayam Goreng', 'porsi'),
	('MKN004', 4, '22000', 'img/Makanan/Menu%20Paket/ayamkecap.jpg', 'Ayam Kecap', 'porsi'),
	('MKN005', 4, '29000', 'img/Makanan/Menu%20Paket/bebekgoreng.jpg', 'Bebek Goreng', 'porsi'),
	('MKN006', 4, '25000', 'img/Makanan/Menu%20Paket/capcayseafood.jpg', 'Capcay Seafood', 'porsi'),
	('MKN007', 4, '38000', 'img/Makanan/Menu%20Paket/empalsapigepuk.jpg', 'Empal Sapi Gepuk', 'porsi'),
	('MKN008', 4, '45000', 'img/Makanan/Menu%20Paket/guramibakar.jpg', 'Gurami Bakar', 'porsi'),
	('MKN009', 4, '35000', 'img/Makanan/Menu%20Paket/igabakar.jpg', 'Iga Bakar', 'porsi'),
	('MKN010', 4, '18000', 'img/Makanan/Menu%20Paket/miegoreng.jpg', 'Mie Goreng', 'porsi'),
	('MKN011', 4, '29000', 'img/Makanan/Menu%20Paket/nilabakar.jpg', 'Nila Bakar', 'porsi'),
	('MKN012', 4, '23000', 'img/Makanan/Menu%20Paket/osengkikillombokijo.jpg', 'Oseng Kikil Lombok Ijo', 'porsi'),
	('MKN013', 4, '20000', 'img/Makanan/Menu%20Paket/pecellele.jpg', 'Pecel Lele', 'porsi'),
	('MKN014', 4, '22000', 'img/Makanan/Menu%20Paket/supayam.jpg', 'Sup Ayam', 'porsi'),
	('MKN015', 4, '35000', 'img/Makanan/Menu%20Paket/supiga.jpg', 'Sup Iga', 'porsi'),
	('MKN016', 4, '9000', 'img/Makanan/Menu%20Paket/tahutempepenyet.jpg', 'Tahu Tempe Penyet', 'porsi'),
	('MKN017', 4, '22000', 'img/Makanan/Menu%20Paket/tongsengayam.jpg', 'Tongseng Ayam', 'porsi'),
	('MKN018', 5, '21000', 'img/Makanan/Menu%20Sayur/baladopete.jpg', 'Balado Pete', 'porsi'),
	('MKN019', 5, '16000', 'img/Makanan/Menu%20Sayur/baladoterong.jpg', 'Balado Terong', 'porsi'),
	('MKN020', 5, '16000', 'img/Makanan/Menu%20Sayur/cahkangkung.jpg', 'Cah Kangkung', 'porsi'),
	('MKN021', 5, '16000', 'img/Makanan/Menu%20Sayur/cahtauge.jpg', 'Cah Tauge', 'porsi'),
	('MKN022', 5, '18000', 'img/Makanan/Menu%20Sayur/capcaybiasa.jpg', 'Capcay Biasa', 'porsi'),
	('MKN023', 6, '28000', 'img/Makanan/Seafood/baladocumiudang.jpg', 'Balado Cumi/Udang', 'porsi'),
	('MKN024', 6, '28000', 'img/Makanan/Seafood/cumigorengtepung.jpg', 'Cumi Goreng Tepung', 'porsi'),
	('MKN025', 6, '50000', 'img/Makanan/Seafood/guramiasammanis.jpg', 'Gurami Asam Manis', 'porsi'),
	('MKN026', 6, '26000', 'img/Makanan/Seafood/nasigorengseafood.jpg', 'Nasi Goreng Seafood', 'porsi'),
	('MKN027', 6, '32000', 'img/Makanan/Seafood/udanggorengmentega.jpg', 'Udang Goreng Mentega', 'porsi'),
	('MNM028', 1, '9000', 'img/Minuman/Hotdrink/jerukhangat.jpg', 'Jeruk Hangat', 'porsi'),
	('MNM029', 1, '5000', 'img/Minuman/Hotdrink/kopi.jpg', 'Kopi', 'porsi'),
	('MNM030', 1, '6000', 'img/Minuman/Hotdrink/kopisusu.jpg', 'Kopi Susu', 'porsi'),
	('MNM031', 1, '8000', 'img/Minuman/Hotdrink/lemonteahangat.jpg', 'Lemon Tea Hangat', 'porsi'),
	('MNM032', 1, '4000', 'img/Minuman/Hotdrink/teahangat.jpg', 'Tea Hangat', 'porsi'),
	('MNM033', 3, '12000', 'img/Minuman/Juice/jusalpukat.jpg', 'Jus Alpukat', 'porsi'),
	('MNM034', 3, '10000', 'img/Minuman/Juice/jusbelimbing.jpg', 'Jus Belimbing', 'porsi'),
	('MNM035', 3, '12000', 'img/Minuman/Juice/jusjambu.jpg', 'Jus Jambu', 'porsi'),
	('MNM036', 3, '12000', 'img/Minuman/Juice/jusmangga.jpg', 'Jus Mangga', 'porsi'),
	('MNM037', 3, '10000', 'img/Minuman/Juice/jusmelon.jpg', 'Jus Melon', 'porsi'),
	('MNM038', 3, '10000', 'img/Minuman/Juice/juspepaya.jpg', 'Jus Pepaya', 'porsi'),
	('MNM039', 3, '10000', 'img/Minuman/Juice/jussemangka.jpg', 'Jus Semangka', 'porsi'),
	('MNM040', 3, '12000', 'img/Minuman/Juice/jussirsak.jpg', 'Jus Sirsak', 'porsi'),
	('MNM041', 3, '10000', 'img/Minuman/Juice/justomat.jpg', 'Jus Tomat', 'porsi'),
	('MNM042', 2, '5000', 'img/Minuman/SoftDrink/airmineral.jpg', 'Air Mineral', 'porsi'),
	('MNM043', 2, '10000', 'img/Minuman/SoftDrink/esblewah.jpg', 'Es Blewah', 'porsi'),
	('MNM044', 2, '9000', 'img/Minuman/SoftDrink/esdegan.jpg', 'Es Degan', 'porsi'),
	('MNM045', 2, '9000', 'img/Minuman/SoftDrink/esjeruk.jpg', 'Es Jeruk', 'porsi'),
	('MNM046', 2, '4000', 'img/Minuman/SoftDrink/estehmanis.jpg', 'Es Teh Manis', 'porsi'),
	('MNM047', 2, '8000', 'img/Minuman/SoftDrink/jeruknipis.jpg', 'Es Jeruk Nipis', 'porsi'),
	('MNM048', 2, '8000', 'img/Minuman/SoftDrink/lemontea.jpg', 'Es Lemon Tea', 'porsi'),
	('MNM049', 2, '16000', 'img/Minuman/SoftDrink/sodagembira.jpg', 'Soda Gembira', 'porsi');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `karyawan` (
  `kode` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `almt` text NOT NULL,
  `jab` varchar(10) NOT NULL,
  `mlebu` tinyint(1) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.karyawan: ~3 rows (lebih kurang)
/*!40000 ALTER TABLE `karyawan` DISABLE KEYS */;
INSERT INTO `karyawan` (`kode`, `pass`, `nama`, `almt`, `jab`, `mlebu`) VALUES
	('admin', 'adm', 'admin', '-', 'admin', 0),
	('dapur', 'dpr', 'koki', '-', 'koki', 0),
	('kasir', 'ksr', 'lobby', '-', 'teller', 0);
/*!40000 ALTER TABLE `karyawan` ENABLE KEYS */;

-- membuang struktur untuk table resto.meja
CREATE TABLE IF NOT EXISTS `meja` (
  `nomor` int(11) NOT NULL,
  `ket` text NOT NULL,
  PRIMARY KEY (`nomor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.meja: ~15 rows (lebih kurang)
/*!40000 ALTER TABLE `meja` DISABLE KEYS */;
INSERT INTO `meja` (`nomor`, `ket`) VALUES
	(1, '-'),
	(2, '-'),
	(3, '-'),
	(4, '-'),
	(5, '-'),
	(6, '-'),
	(7, '-'),
	(8, '-'),
	(9, '-'),
	(10, '-'),
	(11, '-'),
	(12, '-'),
	(13, '-'),
	(14, '-'),
	(15, '-');
/*!40000 ALTER TABLE `meja` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `persediaan` (
  `kode` varchar(30) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `stok` float NOT NULL,
  `satuan` varchar(20) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.persediaan: ~17 rows (lebih kurang)
/*!40000 ALTER TABLE `persediaan` DISABLE KEYS */;
INSERT INTO `persediaan` (`kode`, `nama`, `stok`, `satuan`) VALUES
	('air jeruk nipis', 'Air Jeruk Nipis', 100, 'butir'),
	('air putih', 'Air Putih', 100, 'liter'),
	('air sagu', 'Air Sagu', 10, 'liter'),
	('asam jawa', 'Asam Jawa', 50, 'kg'),
	('ayam1', 'Ayam ', 100, 'ekor'),
	('bakso ikan', 'Bakso Ikan', 300, 'butir'),
	('bawang bombay', 'Bawang Bombay', 200, 'buah'),
	('bawang merah', 'Bawang Merah', 500, 'butir'),
	('bawang putih', 'Bawang Putih', 500, 'siung'),
	('bebek1', 'Bebek ', 500, 'ekor'),
	('cabai hijau besar', 'Cabai Hijau Besar', 500, 'buah'),
	('cabai merah besar', 'Cabai Merai Besar', 500, 'buah'),
	('cabai rawit hijau', 'Cabai Rawit hijau', 200, 'buah'),
	('cabai rawit merah', 'Cabai Rawit Merah', 200, 'buah'),
	('cumi', 'Cumi', 100, 'ekor'),
	('daging ayam', 'Daging Ayam', 500, 'kg'),
	('daging sapi', 'Daging Sapi', 50, 'kg'),
	('daun bawang', 'Daun Bawang', 300, 'batang'),
	('daun jeruk', 'Daun Jeruk', 1000, 'lembar'),
	('daun kol', 'Daun Kol', 100, 'lembar'),
	('daun salam', 'Daun Salam', 1000, 'lembar'),
	('es batu', 'Es Batu', 500, 'biji'),
	('garam', 'Garam', 10, 'kg'),
	('gula merah', 'Gula Merah', 30, 'kg'),
	('gula pasir', 'Gula Pasir', 20, 'kg'),
	('gurami', 'Gurami', 50, 'kg'),
	('iga sapi', 'Iga Sapi', 50, 'kg'),
	('jagung muda', 'Jagung Muda', 50, 'buah'),
	('jahe', 'Jahe', 50, 'ruas'),
	('kecap asin', 'Kecap Asin', 30, 'liter'),
	('kecap manis', 'Kecap Manis', 50, 'liter'),
	('kemiri', 'Kemiri', 100, 'butir'),
	('ketumbar', 'ketumbar', 10, 'kg'),
	('kunyit', 'Kunyit', 50, 'ruas'),
	('lada bubuk', 'Lada Bubuk', 10, 'kg'),
	('lemon', 'Lemon', 50, 'buah'),
	('lengkuas', 'Lengkuas', 50, 'ruas'),
	('madu', 'Madu', 2, 'liter'),
	('merica bubuk', 'Merica Bubuk', 10, 'kg'),
	('minyak goreng', 'Minyak Goreng', 100, 'liter'),
	('pala bubuk', 'Pala Bubuk', 5, 'kg'),
	('penyedap rasa ayam', 'Penyedap Rasa Ayam', 10, 'kg'),
	('petai', 'Petai', 50, 'papan'),
	('santan', 'Santan', 50, 'liter'),
	('saus sambal pedas', 'Saus Sambal Pedas', 50, 'liter'),
	('saus tiram', 'Saus Tiram', 30, 'liter'),
	('saus tomat', 'Saus Tomat', 50, 'liter'),
	('serai', 'Serai', 1000, 'batang'),
	('sirup kental merah', 'Sirup Kental Merah', 5, 'liter'),
	('soda', 'Soda', 100, 'botol'),
	('susu kental', 'Susu Kental', 100, 'kaleng'),
	('teh celup', 'Teh Celup', 200, 'biji'),
	('tomat', 'Tomat', 100, 'buah'),
	('udang', 'Udang', 200, 'ekor'),
	('wortel', 'Wortel', 100, 'buah');
/*!40000 ALTER TABLE `persediaan` ENABLE KEYS */;

-- membuang struktur untuk table resto.bahan
CREATE TABLE IF NOT EXISTS `bahan` (
  `menu` varchar(20) NOT NULL,
  `bahan` varchar(30) NOT NULL,
  `qty` float NOT NULL,
  KEY `FK_bahan_menu` (`menu`),
  KEY `FK_bahan_persediaan` (`bahan`),
  CONSTRAINT `FK_bahan_menu` FOREIGN KEY (`menu`) REFERENCES `menu` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_bahan_persediaan` FOREIGN KEY (`bahan`) REFERENCES `persediaan` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.bahan: ~2 rows (lebih kurang)
/*!40000 ALTER TABLE `bahan` DISABLE KEYS */;
INSERT INTO `bahan` (`menu`, `bahan`, `qty`) VALUES
	('MKN001', 'ayam1', 0.25),
	('MKN001', 'serai', 2),
	('MKN001', 'daun salam', 4),
	('MKN001', 'asam jawa', 0.02),
	('MKN001', 'santan', 0.5),
	('MKN001', 'bawang merah', 10),
	('MKN001', 'bawang putih', 5),
	('MKN001', 'ketumbar', 0.02),
	('MKN001', 'kemiri', 5),
	('MKN001', 'merica bubuk', 0.002),
	('MKN001', 'kunyit', 1),
	('MKN001', 'lengkuas', 1),
	('MKN001', 'gula merah', 0.05),
	('MKN001', 'garam', 0.004),
	('MKN001', 'gula pasir', 0.01),
	('MKN001', 'kecap manis', 0.015),
	('MKN002', 'ayam1', 0.25),
	('MKN002', 'air jeruk nipis', 0.25),
	('MKN002', 'garam', 0.008),
	('MKN002', 'minyak goreng', 0.045),
	('MKN002', 'daun jeruk', 2),
	('MKN002', 'serai', 1),
	('MKN002', 'bawang putih', 5),
	('MKN002', 'cabai hijau besar', 10),
	('MKN002', 'cabai rawit hijau', 5),
	('MKN002', 'jahe', 2),
	('MKN002', 'kunyit', 2),
	('MKN002', 'kemiri', 3),
	('MKN002', 'garam', 0.004),
	('MKN002', 'gula pasir', 0.004),
	('MKN003', 'ayam1', 0.25),
	('MKN003', 'bawang merah', 3),
	('MKN003', 'bawang putih', 3),
	('MKN003', 'kemiri', 6),
	('MKN003', 'kunyit', 2),
	('MKN003', 'garam', 0.004),
	('MKN003', 'penyedap rasa ayam', 0.004),
	('MKN004', 'ayam1', 0.25),
	('MKN004', 'bawang merah', 12),
	('MKN004', 'bawang putih', 2),
	('MKN004', 'kecap manis', 0.15),
	('MKN004', 'jahe', 3),
	('MKN004', 'saus sambal pedas', 0.015),
	('MKN004', 'saus tomat', 0.03),
	('MKN004', 'lada bubuk', 0.004),
	('MKN004', 'garam', 0.01),
	('MKN004', 'air jeruk nipis', 1),
	('MKN004', 'air putih', 0.2),
	('MKN004', 'minyak goreng', 0.045),
	('MKN005', 'bebek1', 0.25),
	('MKN005', 'air jeruk nipis', 1),
	('MKN005', 'lengkuas', 2),
	('MKN005', 'serai', 1),
	('MKN005', 'daun jeruk', 3),
	('MKN005', 'kemiri', 3),
	('MKN005', 'ketumbar', 0.002),
	('MKN005', 'kunyit', 2),
	('MKN005', 'bawang putih', 3),
	('MKN005', 'bawang merah', 8),
	('MKN005', 'merica bubuk', 0.002),
	('MKN005', 'gula pasir', 0.004),
	('MKN005', 'garam', 0.004),
	('MKN005', 'penyedap rasa ayam', 0.004),
	('MKN006', 'wortel', 1),
	('MKN006', 'daun kol', 2),
	('MKN006', 'jagung muda', 5),
	('MKN006', 'daun bawang', 2),
	('MKN006', 'bakso ikan', 5),
	('MKN006', 'udang', 0.08),
	('MKN006', 'cumi', 0.02),
	('MKN006', 'bawang putih', 3),
	('MKN006', 'kecap asin', 0.03),
	('MKN006', 'saus tiram', 0.015),
	('MKN006', 'air putih', 0.3),
	('MKN006', 'air sagu', 0.015),
	('MKN006', 'garam', 0.004),
	('MKN006', 'merica bubuk', 0.004),
	('MKN006', 'gula pasir', 0.004),
	('MKN002', 'bawang merah', 8),
	('MKN007', 'daging sapi', 0.5),
	('MKN007', 'daun salam', 3),
	('MKN007', 'asam jawa', 0.002),
	('MKN007', 'santan', 0.7),
	('MKN007', 'air putih', 0.05),
	('MKN007', 'minyak goreng', 0.045),
	('MKN007', 'bawang merah', 10),
	('MKN007', 'bawang putih', 3),
	('MKN007', 'ketumbar', 0.008),
	('MKN007', 'lengkuas', 2),
	('MKN007', 'garam', 0.002),
	('MKN007', 'gula merah', 0.008),
	('MKN007', 'serai', 1),
	('MKN008', 'gurami', 1),
	('MKN008', 'kemiri', 8),
	('MKN008', 'kunyit', 1),
	('MKN008', 'bawang merah', 6),
	('MKN008', 'bawang putih', 3),
	('MKN008', 'cabai rawit merah', 4),
	('MKN008', 'garam', 0.004),
	('MKN008', 'merica bubuk', 0.004),
	('MKN008', 'air jeruk nipis', 2),
	('MKN008', 'tomat', 1),
	('MKN009', 'bawang putih', 6),
	('MKN009', 'iga sapi', 1.5),
	('MKN009', 'bawang bombay', 1),
	('MKN009', 'jahe', 3),
	('MKN009', 'kecap manis', 0.03),
	('MKN009', 'madu', 0.03),
	('MKN009', 'saus tiram', 0.03),
	('MKN009', 'garam', 0.002),
	('MKN009', 'merica bubuk', 0.002),
	('MKN009', 'pala bubuk', 0.002),
	('MKN009', 'air putih', 1.5),
	('MKN009', 'minyak goreng', 0.09),
	('MKN018', 'minyak goreng', 0.09),
	('MKN018', 'tomat', 2),
	('MKN018', 'petai', 1),
	('MKN018', 'garam', 0.002),
	('MKN018', 'penyedap rasa ayam', 0.001),
	('MKN018', 'cabai rawit merah', 15),
	('MKN018', 'cabai merah besar', 2),
	('MKN018', 'merica bubuk', 0.001),
	('MKN018', 'gula pasir', 0.004),
	('MKN018', 'bawang merah', 4),
	('MKN018', 'bawang putih', 4),
	('MKN018', 'air putih', 0.1),
	('MNM049', 'soda', 1),
	('MNM049', 'susu kental', 0.5),
	('MNM049', 'sirup kental merah', 0.15),
	('MNM049', 'es batu', 6);
/*!40000 ALTER TABLE `bahan` ENABLE KEYS */;
CREATE TABLE IF NOT EXISTS `pesanan` (
  `nota` varchar(30) NOT NULL,
  `meja` int(11) NOT NULL,
  `tgl` date NOT NULL,
  `byr` text NOT NULL,
  `susuk` text NOT NULL,
  `total` text NOT NULL,
  `terima` tinyint(1) NOT NULL,
  `cetak` tinyint(1) NOT NULL,
  PRIMARY KEY (`nota`),
  KEY `FK__meja` (`meja`),
  CONSTRAINT `FK__meja` FOREIGN KEY (`meja`) REFERENCES `meja` (`nomor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- membuang struktur untuk table resto.item_pesanan
CREATE TABLE IF NOT EXISTS `item_pesanan` (
  `nota` varchar(20) NOT NULL,
  `menu` varchar(20) NOT NULL,
  `qty` int(11) NOT NULL,
  `hrg` text NOT NULL,
  KEY `FK__pesanan` (`nota`),
  KEY `FK__menu` (`menu`),
  CONSTRAINT `FK__menu` FOREIGN KEY (`menu`) REFERENCES `menu` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__pesanan` FOREIGN KEY (`nota`) REFERENCES `pesanan` (`nota`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.item_pesanan: ~0 rows (lebih kurang)
/*!40000 ALTER TABLE `item_pesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_pesanan` ENABLE KEYS */;

-- membuang struktur untuk table resto.karyawan

-- membuang struktur untuk table resto.persediaan

-- membuang struktur untuk table resto.pesanan
-- Membuang data untuk tabel resto.pesanan: ~0 rows (lebih kurang)
/*!40000 ALTER TABLE `pesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `pesanan` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
