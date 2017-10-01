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

-- membuang struktur untuk table resto.meja
CREATE TABLE IF NOT EXISTS `meja` (
  `nomor` int(11) NOT NULL,
  `ket` text NOT NULL,
  PRIMARY KEY (`nomor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.meja: ~2 rows (lebih kurang)
/*!40000 ALTER TABLE `meja` DISABLE KEYS */;
INSERT INTO `meja` (`nomor`, `ket`) VALUES
	(1, '-'),
	(2, '-');
/*!40000 ALTER TABLE `meja` ENABLE KEYS */;

-- membuang struktur untuk table resto.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `kode` varchar(20) NOT NULL,
  `kat` int(11) NOT NULL,
  `harga` text NOT NULL,
  `gbr` text NOT NULL,
  `nama` varchar(30) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `sedia` tinyint(1) NOT NULL,
  PRIMARY KEY (`kode`),
  KEY `FK__kat_menu` (`kat`),
  CONSTRAINT `FK__kat_menu` FOREIGN KEY (`kat`) REFERENCES `kat_menu` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Membuang data untuk tabel resto.menu: ~48 rows (lebih kurang)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`kode`, `kat`, `harga`, `gbr`, `nama`, `satuan`, `sedia`) VALUES
	('1', 4, '21000', 'img/Makanan/Menu%20Paket/ayambakar.jpg', 'Ayam Bakar', 'porsi', 1),
	('10', 4, '22000', 'img/Makanan/Menu%20Paket/supayam.jpg', 'Sup Ayam', 'porsi', 1),
	('11', 4, '40000', 'img/Makanan/Menu%20Paket/guramibakar.jpg', 'Gurami Bakar', 'porsi', 1),
	('12', 4, '38000', 'img/Makanan/Menu%20Paket/empalsapigepuk.jpg', 'Empal Sapi Gepuk', 'porsi', 1),
	('13', 4, '27000', 'img/Makanan/Menu%20Paket/ayamgorenglombokijo.jpg', 'Ayam Goreng Lombok Ijo', 'porsi', 1),
	('14', 4, '25000', 'img/Makanan/Menu%20Paket/capcayseafood.jpg', 'Capcay Seafood', 'porsi', 1),
	('15', 4, '22000', 'img/Makanan/Menu%20Paket/tongsengayam.jpg', 'Tongseng Ayam', 'porsi', 1),
	('16', 4, '23000', 'img/Makanan/Menu%20Paket/osengkikillombokijo.jpg', 'Oseng Kikil Lombok Ijo', 'porsi', 1),
	('17', 4, '35000', 'img/Makanan/Menu%20Paket/igabakar.jpg', 'Iga Bakar', 'porsi', 1),
	('18', 5, '21000', 'img/Makanan/Menu%20Sayur/baladopete.jpg', 'Balado Pete', 'porsi', 1),
	('19', 5, '16000', 'img/Makanan/Menu%20Sayur/cahkangkung.jpg', 'Cah Kangkung', 'porsi', 1),
	('2', 4, '21000', 'img/Makanan/Menu%20Paket/ayamgoreng.jpg', 'Ayam Goreng', 'porsi', 1),
	('20', 5, '16000', 'img/Makanan/Menu%20Sayur/baladoterong.jpg', 'Balado Terong', 'porsi', 1),
	('21', 5, '18000', 'img/Makanan/Menu%20Sayur/capcaybiasa.jpg', 'Capcay Biasa', 'porsi', 1),
	('22', 5, '15000', 'img/Makanan/Menu%20Sayur/cahtaugte.jpg', 'Cah Tauge', 'porsi', 1),
	('23', 6, '28000', 'img/Makanan/Menu%20Seafood/baladocumiudang.jpg', 'Balado Cumi/Udang', 'porsi', 1),
	('24', 6, '32000', 'img/Makanan/Menu%20Seafood/udanggorengmentega.jpg', 'Udang Goreng Mentega', 'porsi', 1),
	('25', 6, '28000', 'img/Makanan/Menu%20Seafood/cumigorengtepung.jpg', 'Cumi Goreng Tepung', 'porsi', 1),
	('26', 6, '25000', 'img/Makanan/Menu%20Seafood/nasigorengseafood.jpg', 'Nasi Goreng Seafood', 'porsi', 1),
	('27', 2, '5000', 'img/Minuman/SoftDrink/airmineral.jpg', 'Air Mineral', 'porsi', 1),
	('28', 2, '10000', 'img/Minuman/SoftDrink/esblewah.jpg', 'Es Blewah', 'porsi', 1),
	('29', 2, '9000', 'img/Minuman/SoftDrink/esdegan.jpg', 'Es Degan', 'porsi', 1),
	('3', 4, '20000', 'img/Makanan/Menu%20Paket/pecellele.jpg', 'Pecel Lele', 'porsi', 1),
	('30', 2, '8000', 'img/Minuman/SoftDrink/jeruknipis.jpg', 'Jeruk Nipis', 'porsi', 1),
	('31', 2, '8000', 'img/Minuman/SoftDrink/lemontea.jpg', 'Lemon Tea', 'porsi', 1),
	('32', 2, '9000', 'img/Minuman/SoftDrink/esjeruk.jpg', 'Es Jeruk', 'porsi', 1),
	('33', 2, '4000', 'img/Minuman/SoftDrink/estehmanis.jpg', 'Es Teh Manis', 'porsi', 1),
	('34', 2, '16000', 'img/Minuman/SoftDrink/sodagembiral.jpg', 'Soda Gembira', 'porsi', 1),
	('35', 1, '5000', 'img/Minuman/HotDrink/kopi.jpg', 'Kopi', 'porsi', 1),
	('36', 1, '6000', 'img/Minuman/HotDrink/kopisusu.jpg', 'Kopi Susu', 'porsi', 1),
	('37', 1, '8000', 'img/Minuman/HotDrink/lemonteahangat.jpg', 'Lemon Tea Hangat', 'porsi', 1),
	('38', 1, '9000', 'img/Minuman/HotDrink/jerukhangat.jpg', 'Jeruk Hangat', 'porsi', 1),
	('39', 1, '4000', 'img/Minuman/HotDrink/tehhangat.jpg', 'Teh Hangat', 'porsi', 1),
	('4', 4, '22000', 'img/Makanan/Menu%20Paket/nilabakar.jpg', 'Nila Bakar', 'porsi', 1),
	('40', 3, '12000', 'img/Minuman/Juice/jusalpukat.jpg', 'Jus Alpukat', 'porsi', 1),
	('41', 3, '10000', 'img/Minuman/Juice/jusbelimbing.jpg', 'Jus Belimbing', 'porsi', 1),
	('42', 3, '12000', 'img/Minuman/Juice/jusmangga.jpg', 'Jus Mangga', 'porsi', 1),
	('43', 3, '10000', 'img/Minuman/Juice/jusmelon.jpg', 'Jus Melon', 'porsi', 1),
	('44', 3, '12000', 'img/Minuman/Juice/jussirsak.jpg', 'Jus Sirsak', 'porsi', 1),
	('45', 3, '12000', 'img/Minuman/Juice/jusjambu.jpg', 'Jus Jambu', 'porsi', 1),
	('46', 3, '10000', 'img/Minuman/Juice/justomat.jpg', 'Jus Tomat', 'porsi', 1),
	('47', 3, '10000', 'img/Minuman/Juice/jussemangka.jpg', 'Jus Semangka', 'porsi', 1),
	('48', 3, '10000', 'img/Minuman/Juice/juspepaya.jpg', 'Jus Pepaya', 'porsi', 1),
	('5', 4, '29000', 'img/Makanan/Menu%20Paket/bebekgoreng.jpg', 'Bebek Goreng', 'porsi', 1),
	('6', 4, '18000', 'img/Makanan/Menu%20Paket/miegoreng.jpg', 'Mie Goreng', 'porsi', 1),
	('7', 4, '10000', 'img/Makanan/Menu%20Paket/tahutempegorengpenyet.jpg', 'Tahu/Tempe Goreng', 'porsi', 1),
	('8', 4, '22000', 'img/Makanan/Menu%20Paket/ayamkecap.jpg', 'Ayam Kecap', 'porsi', 1),
	('9', 4, '35000', 'img/Makanan/Menu%20Paket/supiga.jpg', 'Sup Iga', 'porsi', 1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- membuang struktur untuk table resto.pesanan
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

-- Membuang data untuk tabel resto.pesanan: ~0 rows (lebih kurang)
/*!40000 ALTER TABLE `pesanan` DISABLE KEYS */;
/*!40000 ALTER TABLE `pesanan` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
