-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.0.36-MariaDB-0ubuntu0.16.04.1 - Ubuntu 16.04
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.4.0.5164
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bxtdtdn
DROP DATABASE IF EXISTS `bxtdtdn`;
CREATE DATABASE IF NOT EXISTS `bxtdtdn` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bxtdtdn`;

-- Dumping structure for table bxtdtdn.bando
DROP TABLE IF EXISTS `bando`;
CREATE TABLE IF NOT EXISTS `bando` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh495o542bjvgcwn6ux9wi6eus` (`nguoiSua_id`),
  KEY `FKpu2u1pd23rhwwcnvffpb6kj74` (`nguoiTao_id`),
  CONSTRAINT `FKh495o542bjvgcwn6ux9wi6eus` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKpu2u1pd23rhwwcnvffpb6kj74` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.bando: ~0 rows (approximately)
DELETE FROM `bando`;
/*!40000 ALTER TABLE `bando` DISABLE KEYS */;
/*!40000 ALTER TABLE `bando` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.capdonvi
DROP TABLE IF EXISTS `capdonvi`;
CREATE TABLE IF NOT EXISTS `capdonvi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` longtext,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8f045crwo3qj1iro2juw73no` (`nguoiSua_id`),
  KEY `FKlwu9xeremec3qfux5rk19v7yp` (`nguoiTao_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.capdonvi: 2 rows
DELETE FROM `capdonvi`;
/*!40000 ALTER TABLE `capdonvi` DISABLE KEYS */;
INSERT INTO `capdonvi` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-05 18:03:35', '2018-11-05 18:03:35', 'ap_dung', NULL, 'Sở ban ngành', 1, 1),
	(2, b'0', '2018-11-05 18:03:35', '2018-11-05 18:03:35', 'ap_dung', NULL, 'Quận huyện', 1, 1);
/*!40000 ALTER TABLE `capdonvi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.disanvanhoaphivatthe
DROP TABLE IF EXISTS `disanvanhoaphivatthe`;
CREATE TABLE IF NOT EXISTS `disanvanhoaphivatthe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `buttonRemove` bit(1) NOT NULL,
  `capHuyen` varchar(255) DEFAULT NULL,
  `capTinh` varchar(255) DEFAULT NULL,
  `capXa` varchar(255) DEFAULT NULL,
  `description` text,
  `details` text,
  `ghichu` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quyetDinhXepHangDiSanVanHoaPhiVatThe` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `avatarImage_id` bigint(20) DEFAULT NULL,
  `banDo_id` bigint(20) DEFAULT NULL,
  `loai_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKayw9y9d324jwvmoge5dqp7qre` (`nguoiSua_id`),
  KEY `FKtarkny99yiw2jlnagciv2a6id` (`nguoiTao_id`),
  KEY `FK9khdaailg5889i35ax9nawt46` (`avatarImage_id`),
  KEY `FKvrqvnjkbjvi9gdbwls61qe22` (`banDo_id`),
  KEY `FKdxguj464c3p90ybag8tnsvwhe` (`loai_id`),
  CONSTRAINT `FK9khdaailg5889i35ax9nawt46` FOREIGN KEY (`avatarImage_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKayw9y9d324jwvmoge5dqp7qre` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKdxguj464c3p90ybag8tnsvwhe` FOREIGN KEY (`loai_id`) REFERENCES `loaidisan` (`id`),
  CONSTRAINT `FKtarkny99yiw2jlnagciv2a6id` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKvrqvnjkbjvi9gdbwls61qe22` FOREIGN KEY (`banDo_id`) REFERENCES `bando` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.disanvanhoaphivatthe: ~0 rows (approximately)
DELETE FROM `disanvanhoaphivatthe`;
/*!40000 ALTER TABLE `disanvanhoaphivatthe` DISABLE KEYS */;
/*!40000 ALTER TABLE `disanvanhoaphivatthe` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.disanvanhoaphivatthe_images
DROP TABLE IF EXISTS `disanvanhoaphivatthe_images`;
CREATE TABLE IF NOT EXISTS `disanvanhoaphivatthe_images` (
  `disanvanhoaphivatthe_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  KEY `FKql3g84tg0ya95805ck5bub3h7` (`images_id`),
  KEY `FK5n81429hlig4phahi2pl9utvg` (`disanvanhoaphivatthe_id`),
  CONSTRAINT `FK5n81429hlig4phahi2pl9utvg` FOREIGN KEY (`disanvanhoaphivatthe_id`) REFERENCES `disanvanhoaphivatthe` (`id`),
  CONSTRAINT `FKql3g84tg0ya95805ck5bub3h7` FOREIGN KEY (`images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.disanvanhoaphivatthe_images: ~0 rows (approximately)
DELETE FROM `disanvanhoaphivatthe_images`;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_images` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.disanvanhoaphivatthe_teptins
DROP TABLE IF EXISTS `disanvanhoaphivatthe_teptins`;
CREATE TABLE IF NOT EXISTS `disanvanhoaphivatthe_teptins` (
  `disanvanhoaphivatthe_id` bigint(20) NOT NULL,
  `teptins_id` bigint(20) NOT NULL,
  KEY `FK6np0l06c4s9eatn9nk6juf56m` (`teptins_id`),
  KEY `FKjb1yevu37aynrscg08mj3byyu` (`disanvanhoaphivatthe_id`),
  CONSTRAINT `FK6np0l06c4s9eatn9nk6juf56m` FOREIGN KEY (`teptins_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKjb1yevu37aynrscg08mj3byyu` FOREIGN KEY (`disanvanhoaphivatthe_id`) REFERENCES `disanvanhoaphivatthe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.disanvanhoaphivatthe_teptins: ~0 rows (approximately)
DELETE FROM `disanvanhoaphivatthe_teptins`;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_teptins` DISABLE KEYS */;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_teptins` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.disanvanhoaphivatthe_videos
DROP TABLE IF EXISTS `disanvanhoaphivatthe_videos`;
CREATE TABLE IF NOT EXISTS `disanvanhoaphivatthe_videos` (
  `disanvanhoaphivatthe_id` bigint(20) NOT NULL,
  `videos_id` bigint(20) NOT NULL,
  KEY `FKi16gltuw5pvo5umworiucp12h` (`videos_id`),
  KEY `FK3jh8i8ichroc4oxw0xtesnhpx` (`disanvanhoaphivatthe_id`),
  CONSTRAINT `FK3jh8i8ichroc4oxw0xtesnhpx` FOREIGN KEY (`disanvanhoaphivatthe_id`) REFERENCES `disanvanhoaphivatthe` (`id`),
  CONSTRAINT `FKi16gltuw5pvo5umworiucp12h` FOREIGN KEY (`videos_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.disanvanhoaphivatthe_videos: ~0 rows (approximately)
DELETE FROM `disanvanhoaphivatthe_videos`;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_videos` DISABLE KEYS */;
/*!40000 ALTER TABLE `disanvanhoaphivatthe_videos` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.doanvao
DROP TABLE IF EXISTS `doanvao`;
CREATE TABLE IF NOT EXISTS `doanvao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `deXuatCVPhuTrach` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `noiDoanDiTham` varchar(255) DEFAULT NULL,
  `quocGia` int(11) NOT NULL,
  `soNguoi` int(11) NOT NULL,
  `tenDoanVao` varchar(255) DEFAULT NULL,
  `thoiGianDenLamViec` datetime DEFAULT NULL,
  `tomTatNoiDungKQ` varchar(255) DEFAULT NULL,
  `trangThaiTiepDoan` varchar(255) DEFAULT NULL,
  `yKienChiDao` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `congVanChiDaoUB_id` bigint(20) DEFAULT NULL,
  `nguoiPhuTrach_id` bigint(20) DEFAULT NULL,
  `taiLieu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yboq69ojpgbter6d99b5ebt2` (`nguoiSua_id`),
  KEY `FKp3mnmxf3nr816yi313x8mchy` (`nguoiTao_id`),
  KEY `FKaf8apslvv3b7y4jg9qii5l9jp` (`congVanChiDaoUB_id`),
  KEY `FKd78wuneumsmt71747fcuei9cg` (`nguoiPhuTrach_id`),
  KEY `FKefg299mok0jcsmbel3bs786ht` (`taiLieu_id`),
  CONSTRAINT `FK7yboq69ojpgbter6d99b5ebt2` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKaf8apslvv3b7y4jg9qii5l9jp` FOREIGN KEY (`congVanChiDaoUB_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKd78wuneumsmt71747fcuei9cg` FOREIGN KEY (`nguoiPhuTrach_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKefg299mok0jcsmbel3bs786ht` FOREIGN KEY (`taiLieu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKp3mnmxf3nr816yi313x8mchy` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.doanvao: ~0 rows (approximately)
DELETE FROM `doanvao`;
/*!40000 ALTER TABLE `doanvao` DISABLE KEYS */;
/*!40000 ALTER TABLE `doanvao` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donvi
DROP TABLE IF EXISTS `donvi`;
CREATE TABLE IF NOT EXISTS `donvi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `capHuyen` varchar(255) DEFAULT NULL,
  `capTinh` varchar(255) DEFAULT NULL,
  `capXa` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqo1cbpspkivr6nmmtlb71wo4e` (`nguoiSua_id`),
  KEY `FKh4r0e2r8rma8vovk8w8cvdtcs` (`nguoiTao_id`),
  CONSTRAINT `FKh4r0e2r8rma8vovk8w8cvdtcs` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKqo1cbpspkivr6nmmtlb71wo4e` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donvi: ~0 rows (approximately)
DELETE FROM `donvi`;
/*!40000 ALTER TABLE `donvi` DISABLE KEYS */;
/*!40000 ALTER TABLE `donvi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donviduan
DROP TABLE IF EXISTS `donviduan`;
CREATE TABLE IF NOT EXISTS `donviduan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `capDonVi` varchar(255) DEFAULT NULL,
  `donVi` varchar(255) DEFAULT NULL,
  `ngayNhanGiaiThich` datetime DEFAULT NULL,
  `ngayNhanTraLoi` datetime DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `congVanGiaiThich_id` bigint(20) DEFAULT NULL,
  `congVanTraLoi_id` bigint(20) DEFAULT NULL,
  `giaiDoanDuAn_id` bigint(20) DEFAULT NULL,
  `capDonVi_id` bigint(20) DEFAULT NULL,
  `donVi_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdrs18gr9q794gjd8klsqnssi7` (`nguoiSua_id`),
  KEY `FK496c3my4hb98cnl5v66mfuirj` (`nguoiTao_id`),
  KEY `FKguhew035fr9due90afktyhejr` (`congVanGiaiThich_id`),
  KEY `FKv6emym9npdf5o3gawpcc07a2` (`congVanTraLoi_id`),
  KEY `FK63kkomy32teak8w3ieftmuw7m` (`giaiDoanDuAn_id`),
  CONSTRAINT `FK496c3my4hb98cnl5v66mfuirj` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK63kkomy32teak8w3ieftmuw7m` FOREIGN KEY (`giaiDoanDuAn_id`) REFERENCES `giaidoanduan` (`id`),
  CONSTRAINT `FKdrs18gr9q794gjd8klsqnssi7` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKguhew035fr9due90afktyhejr` FOREIGN KEY (`congVanGiaiThich_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKv6emym9npdf5o3gawpcc07a2` FOREIGN KEY (`congVanTraLoi_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donviduan: ~0 rows (approximately)
DELETE FROM `donviduan`;
/*!40000 ALTER TABLE `donviduan` DISABLE KEYS */;
/*!40000 ALTER TABLE `donviduan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donvihanhchinh
DROP TABLE IF EXISTS `donvihanhchinh`;
CREATE TABLE IF NOT EXISTS `donvihanhchinh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `donViHanhChinhCha_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpakq9huylair7d7g1pl9uisk8` (`nguoiSua_id`),
  KEY `FKb0h2taxo5hwj6ivej4terlh` (`nguoiTao_id`),
  KEY `FK7v301ig4ljm2jbr9xeaown3qg` (`donViHanhChinhCha_id`),
  CONSTRAINT `FK7v301ig4ljm2jbr9xeaown3qg` FOREIGN KEY (`donViHanhChinhCha_id`) REFERENCES `donvihanhchinh` (`id`),
  CONSTRAINT `FKb0h2taxo5hwj6ivej4terlh` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKpakq9huylair7d7g1pl9uisk8` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donvihanhchinh: ~0 rows (approximately)
DELETE FROM `donvihanhchinh`;
/*!40000 ALTER TABLE `donvihanhchinh` DISABLE KEYS */;
/*!40000 ALTER TABLE `donvihanhchinh` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donvixuctien
DROP TABLE IF EXISTS `donvixuctien`;
CREATE TABLE IF NOT EXISTS `donvixuctien` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` longtext,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `capDonVi_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrhoiq8ydfovah5571ml26eqys` (`nguoiSua_id`),
  KEY `FK10mbxstuk5l7c2pp4bsi8gdbx` (`nguoiTao_id`),
  KEY `FKdxo42sukdy01pmuf6y8xhx2ec` (`capDonVi_id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donvixuctien: 32 rows
DELETE FROM `donvixuctien`;
/*!40000 ALTER TABLE `donvixuctien` DISABLE KEYS */;
INSERT INTO `donvixuctien` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`, `capDonVi_id`) VALUES
	(1, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Văn phòng Ủy ban nhân dân thành phố', 1, 1, 1),
	(2, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Nông nghiệp và Phát triển nông thôn', 1, 1, 1),
	(3, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Công Thương', 1, 1, 1),
	(4, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Nội Vụ', 1, 1, 1),
	(5, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Du lịch', 1, 1, 1),
	(6, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Văn hóa và Thể thao', 1, 1, 1),
	(7, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Tư Pháp', 1, 1, 1),
	(8, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Thông tin và Truyền thông', 1, 1, 1),
	(9, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Lao động - Thương binh và Xã hội', 1, 1, 1),
	(10, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Y Tế', 1, 1, 1),
	(11, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Tài chính', 1, 1, 1),
	(12, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Khoa học và công nghệ', 1, 1, 1),
	(13, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Giao thông vận tải', 1, 1, 1),
	(14, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Giáo dục và đào tạo', 1, 1, 1),
	(15, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Kế hoạch đầu tư', 1, 1, 1),
	(16, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Ngoại vụ', 1, 1, 1),
	(17, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Xây dựng', 1, 1, 1),
	(18, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Sở Tài nguyên môi trường', 1, 1, 1),
	(19, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Thanh tra thành phố', 1, 1, 1),
	(20, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Ban quản lý các Khu công nghiệp và Chế xuất', 1, 1, 1),
	(21, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Ban xúc tiến và Hỗ trợ đầu tư', 1, 1, 1),
	(22, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Viện Nghiên cứu phát triển kinh tế - xã hội Đà Nẵng', 1, 1, 1),
	(23, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Ban Quản lý Khu công nghệ cao', 1, 1, 1),
	(24, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Ban Quản lý An toàn thực phẩm thành phố Đà Nẵng', 1, 1, 1),
	(25, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Thanh khê', 1, 1, 2),
	(26, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Sơn trà', 1, 1, 2),
	(27, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Ngũ hành sơn', 1, 1, 2),
	(28, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Liên chiểu', 1, 1, 2),
	(29, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Hải châu', 1, 1, 2),
	(30, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Quận Cẩm lệ', 1, 1, 2),
	(31, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Huyện Hòa vang', 1, 1, 2),
	(32, b'0', '2018-11-05 18:04:52', '2018-11-05 18:04:52', 'ap_dung', NULL, 'Huyện Hoàng sa', 1, 1, 2);
/*!40000 ALTER TABLE `donvixuctien` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.duan
DROP TABLE IF EXISTS `duan`;
CREATE TABLE IF NOT EXISTS `duan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `diaDiem` varchar(255) DEFAULT NULL,
  `dienTichSuDungDat` int(11) NOT NULL,
  `giaiDoanXucTien` varchar(255) DEFAULT NULL,
  `idNguoiLienQuan` varchar(255) DEFAULT NULL,
  `khaNangDauTu` varchar(255) DEFAULT NULL,
  `mucDoCanhTranh` varchar(255) DEFAULT NULL,
  `mucDoUuTien` varchar(255) DEFAULT NULL,
  `mucTieuDuAn` varchar(255) DEFAULT NULL,
  `ngayBatDauXucTien` datetime DEFAULT NULL,
  `quyMoDuAn` varchar(255) DEFAULT NULL,
  `tenDuAn` varchar(255) DEFAULT NULL,
  `tongVonDauTu` double NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `linhVuc_id` bigint(20) DEFAULT NULL,
  `nguoiPhuTrach_id` bigint(20) DEFAULT NULL,
  `taiLieuNDT_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKipdmghhwn0k31w37cpdydigwd` (`nguoiSua_id`),
  KEY `FKl434e4x3lmw1a6ey0nohkr35c` (`nguoiTao_id`),
  KEY `FK6fo7bn4lv30v3kacvo40j46g5` (`linhVuc_id`),
  KEY `FK85se4j733smob6atk11dtvxgo` (`nguoiPhuTrach_id`),
  KEY `FK3pvdbi6bus3lwsorxnrx6dhkf` (`taiLieuNDT_id`),
  CONSTRAINT `FK3pvdbi6bus3lwsorxnrx6dhkf` FOREIGN KEY (`taiLieuNDT_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK6fo7bn4lv30v3kacvo40j46g5` FOREIGN KEY (`linhVuc_id`) REFERENCES `linhvucduan` (`id`),
  CONSTRAINT `FK85se4j733smob6atk11dtvxgo` FOREIGN KEY (`nguoiPhuTrach_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKipdmghhwn0k31w37cpdydigwd` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKl434e4x3lmw1a6ey0nohkr35c` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.duan: ~0 rows (approximately)
DELETE FROM `duan`;
/*!40000 ALTER TABLE `duan` DISABLE KEYS */;
/*!40000 ALTER TABLE `duan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.giaidoanduan
DROP TABLE IF EXISTS `giaidoanduan`;
CREATE TABLE IF NOT EXISTS `giaidoanduan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `donViChuTri` int(11) DEFAULT NULL,
  `donViThucHien` int(11) DEFAULT NULL,
  `donViTuVan` int(11) DEFAULT NULL,
  `donViTuVan2000` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `giaDatKhoiDiemDauGia` double DEFAULT NULL,
  `giaiDoanXucTien` int(11) DEFAULT NULL,
  `ngayDuKienNhanPhanHoi` datetime DEFAULT NULL,
  `ngayGui` datetime DEFAULT NULL,
  `ngayGuiSoXayDung` datetime DEFAULT NULL,
  `ngayGuiUBND` datetime DEFAULT NULL,
  `ngayKhaoSat` datetime DEFAULT NULL,
  `ngayNhanPhanHoi` datetime DEFAULT NULL,
  `ngayPhatHanhCV3` datetime DEFAULT NULL,
  `ngayPhatHanhCVGD2` datetime DEFAULT NULL,
  `nguoiDaiDienPhapLy` varchar(255) DEFAULT NULL,
  `option` bit(1) NOT NULL,
  `phuongThucLuaChonNDT` int(11) DEFAULT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `tenCongTy` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `baoCaoDoDacKhuDat_id` bigint(20) DEFAULT NULL,
  `congVanGD2_id` bigint(20) DEFAULT NULL,
  `congVanGD3_id` bigint(20) DEFAULT NULL,
  `duAn_id` bigint(20) DEFAULT NULL,
  `giayChungNhanDangKyDoanhNghiep_id` bigint(20) DEFAULT NULL,
  `giayChungNhanDauTu_id` bigint(20) DEFAULT NULL,
  `giayChungNhanQuyenSuDungDat_id` bigint(20) DEFAULT NULL,
  `hoSoMoiThau_id` bigint(20) DEFAULT NULL,
  `hoSoMoiTuyen_id` bigint(20) DEFAULT NULL,
  `hoSoQuyHoach2000_id` bigint(20) DEFAULT NULL,
  `hoSoQuyHoachLQH_id` bigint(20) DEFAULT NULL,
  `keHoachLuaChonNhaDauTu_id` bigint(20) DEFAULT NULL,
  `nghiQuyetPheDuyetCongTrinh_id` bigint(20) DEFAULT NULL,
  `pheDuyetKeHoachSuDungDat_id` bigint(20) DEFAULT NULL,
  `phuongAnDauGia_id` bigint(20) DEFAULT NULL,
  `phuongAnTaiDinhCu_id` bigint(20) DEFAULT NULL,
  `quyetDinhBoSungDanhMuc_id` bigint(20) DEFAULT NULL,
  `quyetDinhDauGiaQSDD_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyeHoSoMoiTuyen_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyet2000_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetBoSungKinhPhi_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetDanhMuc_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetGiaKhoiDiem_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetKetQuaTrungTuyen_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetLQH_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetMoiThau_id` bigint(20) DEFAULT NULL,
  `quyetDinhPheDuyetPADG_id` bigint(20) DEFAULT NULL,
  `quyetDinhThuHoiDat_id` bigint(20) DEFAULT NULL,
  `taiLieuDinhKem_id` bigint(20) DEFAULT NULL,
  `taiLieuGD1_id` bigint(20) DEFAULT NULL,
  `taiLieuGD2_id` bigint(20) DEFAULT NULL,
  `taiLieuGD3_id` bigint(20) DEFAULT NULL,
  `vanBanChuyenMucDichSDD_id` bigint(20) DEFAULT NULL,
  `vanBanDeNghiBoSung_id` bigint(20) DEFAULT NULL,
  `vanBanDeNghiThuHoiDat_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0tg50cys89jo0ppipmx65vc6` (`nguoiSua_id`),
  KEY `FKiovtr2bkv8rtnq9fu7w3u69m4` (`nguoiTao_id`),
  KEY `FK3k456x2prsltyaib0v3aqo40k` (`baoCaoDoDacKhuDat_id`),
  KEY `FK13v10ictjd7tufnep9f7pkb2e` (`congVanGD2_id`),
  KEY `FK9khye8wkfwkxwc7jkitjmb351` (`congVanGD3_id`),
  KEY `FK367hkqbgdslb2m3lddji89nc6` (`duAn_id`),
  KEY `FK47690nqyiikgpmojllh59s446` (`giayChungNhanDangKyDoanhNghiep_id`),
  KEY `FKs1t0q6hji8phhcwi3jfj1od8u` (`giayChungNhanDauTu_id`),
  KEY `FKfeokeu9wyd8e4v8k90gabxt4t` (`giayChungNhanQuyenSuDungDat_id`),
  KEY `FKkjaw0cyhvgghhalhymg56ybxs` (`hoSoMoiThau_id`),
  KEY `FK3f48e8k69dkltn4dfgc9ycj9j` (`hoSoMoiTuyen_id`),
  KEY `FKtfr03bnqxbtoactrsoj8c4i7t` (`hoSoQuyHoach2000_id`),
  KEY `FKaqx5swb048j26pmat7da9iq6b` (`hoSoQuyHoachLQH_id`),
  KEY `FK30auw378vfv0xjitwos8hdbte` (`keHoachLuaChonNhaDauTu_id`),
  KEY `FK5304t69kusn98vnbvkac7tsfk` (`nghiQuyetPheDuyetCongTrinh_id`),
  KEY `FKto9tplet4aplud6nxmsmmayu7` (`pheDuyetKeHoachSuDungDat_id`),
  KEY `FKlq8y7ft7n3l7a2f8aq0ypbxv6` (`phuongAnDauGia_id`),
  KEY `FKcna1e638vtd0ly0cefsbs96o5` (`phuongAnTaiDinhCu_id`),
  KEY `FK8j30yicpo5k5r0miu0j9jehkd` (`quyetDinhBoSungDanhMuc_id`),
  KEY `FKgimoch72f5l1b4s9hit4dc8ty` (`quyetDinhDauGiaQSDD_id`),
  KEY `FKot4epkpvesre0o9h06c5a1rpg` (`quyetDinhPheDuyeHoSoMoiTuyen_id`),
  KEY `FKgo5hsmtncd6hr3o0cc06l6e1s` (`quyetDinhPheDuyet2000_id`),
  KEY `FKet5xnvq9dclcbso869giasf6k` (`quyetDinhPheDuyetBoSungKinhPhi_id`),
  KEY `FKqb0kj47fm0840hvidu0ht37jq` (`quyetDinhPheDuyetDanhMuc_id`),
  KEY `FKhv8lk8c6aerdaym0pmb0j67rv` (`quyetDinhPheDuyetGiaKhoiDiem_id`),
  KEY `FK15ybv8uu4clk8fe3fl7wa766l` (`quyetDinhPheDuyetKetQuaTrungTuyen_id`),
  KEY `FKikf8uio2qjpv3qcxibp5i04u5` (`quyetDinhPheDuyetLQH_id`),
  KEY `FKko8a9ec548y0yygdjub15to4v` (`quyetDinhPheDuyetMoiThau_id`),
  KEY `FKic5u40grsamr45srt1gye5kig` (`quyetDinhPheDuyetPADG_id`),
  KEY `FKrv4ck2rkuf9325ubfrfhm1kty` (`quyetDinhThuHoiDat_id`),
  KEY `FK8nb9eb2c1yt2tcsn049o0cdxu` (`taiLieuDinhKem_id`),
  KEY `FK7b8m4swxei7lw9yjdt4ojo77h` (`taiLieuGD1_id`),
  KEY `FKgwmh7c7vtvy08uweclioh94em` (`taiLieuGD2_id`),
  KEY `FK54lkb2gimh4oiwpt23lrqujfn` (`taiLieuGD3_id`),
  KEY `FKp2ojg9eqfo8a0adelae5789yg` (`vanBanChuyenMucDichSDD_id`),
  KEY `FK98wb9lo3g6jmm4pwp1j9jk3j9` (`vanBanDeNghiBoSung_id`),
  KEY `FKqagb4is0rwe57s8buqou8l4c5` (`vanBanDeNghiThuHoiDat_id`),
  CONSTRAINT `FK13v10ictjd7tufnep9f7pkb2e` FOREIGN KEY (`congVanGD2_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK15ybv8uu4clk8fe3fl7wa766l` FOREIGN KEY (`quyetDinhPheDuyetKetQuaTrungTuyen_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK30auw378vfv0xjitwos8hdbte` FOREIGN KEY (`keHoachLuaChonNhaDauTu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK367hkqbgdslb2m3lddji89nc6` FOREIGN KEY (`duAn_id`) REFERENCES `duan` (`id`),
  CONSTRAINT `FK3f48e8k69dkltn4dfgc9ycj9j` FOREIGN KEY (`hoSoMoiTuyen_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK3k456x2prsltyaib0v3aqo40k` FOREIGN KEY (`baoCaoDoDacKhuDat_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK47690nqyiikgpmojllh59s446` FOREIGN KEY (`giayChungNhanDangKyDoanhNghiep_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK5304t69kusn98vnbvkac7tsfk` FOREIGN KEY (`nghiQuyetPheDuyetCongTrinh_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK54lkb2gimh4oiwpt23lrqujfn` FOREIGN KEY (`taiLieuGD3_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK7b8m4swxei7lw9yjdt4ojo77h` FOREIGN KEY (`taiLieuGD1_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK8j30yicpo5k5r0miu0j9jehkd` FOREIGN KEY (`quyetDinhBoSungDanhMuc_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK8nb9eb2c1yt2tcsn049o0cdxu` FOREIGN KEY (`taiLieuDinhKem_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK98wb9lo3g6jmm4pwp1j9jk3j9` FOREIGN KEY (`vanBanDeNghiBoSung_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK9khye8wkfwkxwc7jkitjmb351` FOREIGN KEY (`congVanGD3_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKaqx5swb048j26pmat7da9iq6b` FOREIGN KEY (`hoSoQuyHoachLQH_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKc0tg50cys89jo0ppipmx65vc6` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKcna1e638vtd0ly0cefsbs96o5` FOREIGN KEY (`phuongAnTaiDinhCu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKet5xnvq9dclcbso869giasf6k` FOREIGN KEY (`quyetDinhPheDuyetBoSungKinhPhi_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKfeokeu9wyd8e4v8k90gabxt4t` FOREIGN KEY (`giayChungNhanQuyenSuDungDat_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKgimoch72f5l1b4s9hit4dc8ty` FOREIGN KEY (`quyetDinhDauGiaQSDD_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKgo5hsmtncd6hr3o0cc06l6e1s` FOREIGN KEY (`quyetDinhPheDuyet2000_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKgwmh7c7vtvy08uweclioh94em` FOREIGN KEY (`taiLieuGD2_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKhv8lk8c6aerdaym0pmb0j67rv` FOREIGN KEY (`quyetDinhPheDuyetGiaKhoiDiem_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKic5u40grsamr45srt1gye5kig` FOREIGN KEY (`quyetDinhPheDuyetPADG_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKikf8uio2qjpv3qcxibp5i04u5` FOREIGN KEY (`quyetDinhPheDuyetLQH_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKiovtr2bkv8rtnq9fu7w3u69m4` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKkjaw0cyhvgghhalhymg56ybxs` FOREIGN KEY (`hoSoMoiThau_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKko8a9ec548y0yygdjub15to4v` FOREIGN KEY (`quyetDinhPheDuyetMoiThau_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKlq8y7ft7n3l7a2f8aq0ypbxv6` FOREIGN KEY (`phuongAnDauGia_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKot4epkpvesre0o9h06c5a1rpg` FOREIGN KEY (`quyetDinhPheDuyeHoSoMoiTuyen_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKp2ojg9eqfo8a0adelae5789yg` FOREIGN KEY (`vanBanChuyenMucDichSDD_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKqagb4is0rwe57s8buqou8l4c5` FOREIGN KEY (`vanBanDeNghiThuHoiDat_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKqb0kj47fm0840hvidu0ht37jq` FOREIGN KEY (`quyetDinhPheDuyetDanhMuc_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKrv4ck2rkuf9325ubfrfhm1kty` FOREIGN KEY (`quyetDinhThuHoiDat_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKs1t0q6hji8phhcwi3jfj1od8u` FOREIGN KEY (`giayChungNhanDauTu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKtfr03bnqxbtoactrsoj8c4i7t` FOREIGN KEY (`hoSoQuyHoach2000_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKto9tplet4aplud6nxmsmmayu7` FOREIGN KEY (`pheDuyetKeHoachSuDungDat_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.giaidoanduan: ~0 rows (approximately)
DELETE FROM `giaidoanduan`;
/*!40000 ALTER TABLE `giaidoanduan` DISABLE KEYS */;
/*!40000 ALTER TABLE `giaidoanduan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.giaoviec
DROP TABLE IF EXISTS `giaoviec`;
CREATE TABLE IF NOT EXISTS `giaoviec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `giaiDoanXucTien` varchar(255) DEFAULT NULL,
  `hanThucHien` datetime DEFAULT NULL,
  `ketQua` varchar(255) DEFAULT NULL,
  `ngayGiao` datetime DEFAULT NULL,
  `ngayHoanThanh` datetime DEFAULT NULL,
  `tenCongViec` varchar(255) DEFAULT NULL,
  `trangThaiGiaoViec` varchar(255) DEFAULT NULL,
  `yKienChiDao` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `duAn_id` bigint(20) DEFAULT NULL,
  `nguoiDuocGiao_id` bigint(20) DEFAULT NULL,
  `nguoiGiaoViec_id` bigint(20) DEFAULT NULL,
  `taiLieu_id` bigint(20) DEFAULT NULL,
  `taiLieuKetQua_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jkhxiuynhv7kg8lpysxgxa36` (`nguoiSua_id`),
  KEY `FKrtob3779e4wj2cn2uo88tjlku` (`nguoiTao_id`),
  KEY `FKc03v84oo8ne7yrq07j0xbk3nx` (`duAn_id`),
  KEY `FKjpb7j625yctta0ar1as62ea79` (`nguoiDuocGiao_id`),
  KEY `FK7koe49gx4fwfrtqjt5c1mnpju` (`nguoiGiaoViec_id`),
  KEY `FK8wvh4hnwxwkr081alfgiaatsp` (`taiLieu_id`),
  KEY `FKl7t2w9u86m83h4j1e0g1u0qnw` (`taiLieuKetQua_id`),
  CONSTRAINT `FK1jkhxiuynhv7kg8lpysxgxa36` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK7koe49gx4fwfrtqjt5c1mnpju` FOREIGN KEY (`nguoiGiaoViec_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK8wvh4hnwxwkr081alfgiaatsp` FOREIGN KEY (`taiLieu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKc03v84oo8ne7yrq07j0xbk3nx` FOREIGN KEY (`duAn_id`) REFERENCES `duan` (`id`),
  CONSTRAINT `FKjpb7j625yctta0ar1as62ea79` FOREIGN KEY (`nguoiDuocGiao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKl7t2w9u86m83h4j1e0g1u0qnw` FOREIGN KEY (`taiLieuKetQua_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKrtob3779e4wj2cn2uo88tjlku` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.giaoviec: ~0 rows (approximately)
DELETE FROM `giaoviec`;
/*!40000 ALTER TABLE `giaoviec` DISABLE KEYS */;
/*!40000 ALTER TABLE `giaoviec` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.gopyphanmem
DROP TABLE IF EXISTS `gopyphanmem`;
CREATE TABLE IF NOT EXISTS `gopyphanmem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `hoTen` varchar(255) DEFAULT NULL,
  `noiDung` varchar(5000) DEFAULT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8epembdgkq07hhy58ha2dgye0` (`nguoiSua_id`),
  KEY `FKnc5dawrtdtoju93h2bxgr5h4i` (`nguoiTao_id`),
  CONSTRAINT `FK8epembdgkq07hhy58ha2dgye0` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKnc5dawrtdtoju93h2bxgr5h4i` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.gopyphanmem: ~0 rows (approximately)
DELETE FROM `gopyphanmem`;
/*!40000 ALTER TABLE `gopyphanmem` DISABLE KEYS */;
/*!40000 ALTER TABLE `gopyphanmem` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.image
DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `bannerImage` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `detailImage` varchar(255) DEFAULT NULL,
  `extension` varchar(255) DEFAULT NULL,
  `imageUrl` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nameFileHash` varchar(255) DEFAULT NULL,
  `smallImage` varchar(255) DEFAULT NULL,
  `videoImage` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKou77algsm9v32c5q7ix04jxgl` (`nguoiSua_id`),
  KEY `FK33foc3k9ct7i13wy28myg3f0g` (`nguoiTao_id`),
  CONSTRAINT `FK33foc3k9ct7i13wy28myg3f0g` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKou77algsm9v32c5q7ix04jxgl` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.image: ~0 rows (approximately)
DELETE FROM `image`;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.kehoachlamviec
DROP TABLE IF EXISTS `kehoachlamviec`;
CREATE TABLE IF NOT EXISTS `kehoachlamviec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `flag` bit(1) NOT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `tenCongViec` varchar(255) DEFAULT NULL,
  `thoiGian` datetime DEFAULT NULL,
  `trangThaiCongViec` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `doanVao_id` bigint(20) DEFAULT NULL,
  `nguoiThucHien_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27c9bto2adfdwnm7krfb4bn5j` (`nguoiSua_id`),
  KEY `FK6th7v8etsvy5251b8onomfbk9` (`nguoiTao_id`),
  KEY `FKn1kmg3w52mduwxoxyx2xjv5yr` (`doanVao_id`),
  KEY `FK9uen69y4mfsrxqae8r8v8iwbb` (`nguoiThucHien_id`),
  CONSTRAINT `FK27c9bto2adfdwnm7krfb4bn5j` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK6th7v8etsvy5251b8onomfbk9` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK9uen69y4mfsrxqae8r8v8iwbb` FOREIGN KEY (`nguoiThucHien_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKn1kmg3w52mduwxoxyx2xjv5yr` FOREIGN KEY (`doanVao_id`) REFERENCES `doanvao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.kehoachlamviec: ~0 rows (approximately)
DELETE FROM `kehoachlamviec`;
/*!40000 ALTER TABLE `kehoachlamviec` DISABLE KEYS */;
/*!40000 ALTER TABLE `kehoachlamviec` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.language
DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqe2qrfo14h35y71xo32jrm36t` (`nguoiSua_id`),
  KEY `FKq8mmr7f05cve848nfxl98tkao` (`nguoiTao_id`),
  CONSTRAINT `FKq8mmr7f05cve848nfxl98tkao` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKqe2qrfo14h35y71xo32jrm36t` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.language: ~0 rows (approximately)
DELETE FROM `language`;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
/*!40000 ALTER TABLE `language` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.lehoi
DROP TABLE IF EXISTS `lehoi`;
CREATE TABLE IF NOT EXISTS `lehoi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `buttonRemove` bit(1) NOT NULL,
  `capHuyen` varchar(255) DEFAULT NULL,
  `capTinh` varchar(255) DEFAULT NULL,
  `capXa` varchar(255) DEFAULT NULL,
  `description` text,
  `details` text,
  `ghichu` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `quyetDinhXepHangLehoi` varchar(255) DEFAULT NULL,
  `thoiGianBatDau` datetime DEFAULT NULL,
  `thoiGianKetThuc` datetime DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `avatarImage_id` bigint(20) DEFAULT NULL,
  `banDo_id` bigint(20) DEFAULT NULL,
  `loai_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhr5sc634q0v2b3nxl4ek2vrh3` (`nguoiSua_id`),
  KEY `FKruerp2ff3tw284n0cpw2v529e` (`nguoiTao_id`),
  KEY `FKdkevya41q2dpk2wrk74ed3p93` (`avatarImage_id`),
  KEY `FK194g5cwefa4glfw2ate1413ty` (`banDo_id`),
  KEY `FKrr5ivppcswffj0nqr47h26fbi` (`loai_id`),
  CONSTRAINT `FK194g5cwefa4glfw2ate1413ty` FOREIGN KEY (`banDo_id`) REFERENCES `bando` (`id`),
  CONSTRAINT `FKdkevya41q2dpk2wrk74ed3p93` FOREIGN KEY (`avatarImage_id`) REFERENCES `image` (`id`),
  CONSTRAINT `FKhr5sc634q0v2b3nxl4ek2vrh3` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKrr5ivppcswffj0nqr47h26fbi` FOREIGN KEY (`loai_id`) REFERENCES `loailehoi` (`id`),
  CONSTRAINT `FKruerp2ff3tw284n0cpw2v529e` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.lehoi: ~0 rows (approximately)
DELETE FROM `lehoi`;
/*!40000 ALTER TABLE `lehoi` DISABLE KEYS */;
/*!40000 ALTER TABLE `lehoi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.lehoi_images
DROP TABLE IF EXISTS `lehoi_images`;
CREATE TABLE IF NOT EXISTS `lehoi_images` (
  `lehoi_id` bigint(20) NOT NULL,
  `images_id` bigint(20) NOT NULL,
  KEY `FKffb3i8b53o4vvvfh1d93bmqf` (`images_id`),
  KEY `FK7jniowrrliccuk41ngxyq7obg` (`lehoi_id`),
  CONSTRAINT `FK7jniowrrliccuk41ngxyq7obg` FOREIGN KEY (`lehoi_id`) REFERENCES `lehoi` (`id`),
  CONSTRAINT `FKffb3i8b53o4vvvfh1d93bmqf` FOREIGN KEY (`images_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.lehoi_images: ~0 rows (approximately)
DELETE FROM `lehoi_images`;
/*!40000 ALTER TABLE `lehoi_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `lehoi_images` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.lehoi_teptins
DROP TABLE IF EXISTS `lehoi_teptins`;
CREATE TABLE IF NOT EXISTS `lehoi_teptins` (
  `lehoi_id` bigint(20) NOT NULL,
  `teptins_id` bigint(20) NOT NULL,
  KEY `FKrf5kd2xtqoy5ycnoj27ywmdbw` (`teptins_id`),
  KEY `FKi1slwrdtxql1q35gpf8eosov5` (`lehoi_id`),
  CONSTRAINT `FKi1slwrdtxql1q35gpf8eosov5` FOREIGN KEY (`lehoi_id`) REFERENCES `lehoi` (`id`),
  CONSTRAINT `FKrf5kd2xtqoy5ycnoj27ywmdbw` FOREIGN KEY (`teptins_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.lehoi_teptins: ~0 rows (approximately)
DELETE FROM `lehoi_teptins`;
/*!40000 ALTER TABLE `lehoi_teptins` DISABLE KEYS */;
/*!40000 ALTER TABLE `lehoi_teptins` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.lehoi_videos
DROP TABLE IF EXISTS `lehoi_videos`;
CREATE TABLE IF NOT EXISTS `lehoi_videos` (
  `lehoi_id` bigint(20) NOT NULL,
  `videos_id` bigint(20) NOT NULL,
  KEY `FK10nu6ch6vqik85me62sqh18pa` (`videos_id`),
  KEY `FKsyypa7uyqf3xlok7ba2xcgd6i` (`lehoi_id`),
  CONSTRAINT `FK10nu6ch6vqik85me62sqh18pa` FOREIGN KEY (`videos_id`) REFERENCES `video` (`id`),
  CONSTRAINT `FKsyypa7uyqf3xlok7ba2xcgd6i` FOREIGN KEY (`lehoi_id`) REFERENCES `lehoi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.lehoi_videos: ~0 rows (approximately)
DELETE FROM `lehoi_videos`;
/*!40000 ALTER TABLE `lehoi_videos` DISABLE KEYS */;
/*!40000 ALTER TABLE `lehoi_videos` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.linhvucduan
DROP TABLE IF EXISTS `linhvucduan`;
CREATE TABLE IF NOT EXISTS `linhvucduan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` longtext,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqfa7rds3k2ujf3cec673xy6fu` (`nguoiSua_id`),
  KEY `FKo7q6ht6ruv0sotmb4ja778p8a` (`nguoiTao_id`),
  CONSTRAINT `FKo7q6ht6ruv0sotmb4ja778p8a` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKqfa7rds3k2ujf3cec673xy6fu` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.linhvucduan: ~10 rows (approximately)
DELETE FROM `linhvucduan`;
/*!40000 ALTER TABLE `linhvucduan` DISABLE KEYS */;
INSERT INTO `linhvucduan` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-05 15:55:32', '2018-11-05 15:55:32', 'ap_dung', 'CNC', 'CNC', 1, 1),
	(2, b'0', '2018-11-05 15:55:44', '2018-11-05 15:55:44', 'ap_dung', 'CNTT', 'CNTT', 1, 1),
	(3, b'0', '2018-11-05 15:55:49', '2018-11-05 15:55:49', 'ap_dung', 'NNCNC', 'NNCNC', 1, 1),
	(4, b'0', '2018-11-05 15:55:55', '2018-11-05 15:55:55', 'ap_dung', 'Dịch vụ', 'Dịch vụ', 1, 1),
	(5, b'0', '2018-11-05 15:56:00', '2018-11-05 15:56:00', 'ap_dung', 'Y tế', 'Y tế', 1, 1),
	(6, b'0', '2018-11-05 15:56:04', '2018-11-05 15:56:04', 'ap_dung', 'CSHT', 'CSHT', 1, 1),
	(7, b'0', '2018-11-05 15:56:10', '2018-11-05 15:56:10', 'ap_dung', 'BĐS', 'BĐS', 1, 1),
	(8, b'0', '2018-11-05 15:56:16', '2018-11-05 15:56:16', 'ap_dung', 'Dịch vụ Logistic', 'Dịch vụ Logistic', 1, 1),
	(9, b'0', '2018-11-05 15:56:22', '2018-11-05 15:56:22', 'ap_dung', 'Thương mại', 'Thương mại', 1, 1),
	(10, b'0', '2018-11-05 15:56:27', '2018-11-05 15:56:27', 'ap_dung', 'CNPT', 'CNPT', 1, 1);
/*!40000 ALTER TABLE `linhvucduan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.loaidisan
DROP TABLE IF EXISTS `loaidisan`;
CREATE TABLE IF NOT EXISTS `loaidisan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `soThuTu` int(11) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhgjrdfxuol87ipo7yfx5xb68m` (`nguoiSua_id`),
  KEY `FKfq2icbgdoi2pxlkgdkgudg47c` (`nguoiTao_id`),
  CONSTRAINT `FKfq2icbgdoi2pxlkgdkgudg47c` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKhgjrdfxuol87ipo7yfx5xb68m` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.loaidisan: ~0 rows (approximately)
DELETE FROM `loaidisan`;
/*!40000 ALTER TABLE `loaidisan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loaidisan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.loaiditich
DROP TABLE IF EXISTS `loaiditich`;
CREATE TABLE IF NOT EXISTS `loaiditich` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `soThuTu` int(11) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1r4qif2cfm3qaibfcu9p79beh` (`nguoiSua_id`),
  KEY `FKakx3k567jaao9in33a901l4s6` (`nguoiTao_id`),
  CONSTRAINT `FK1r4qif2cfm3qaibfcu9p79beh` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKakx3k567jaao9in33a901l4s6` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.loaiditich: ~0 rows (approximately)
DELETE FROM `loaiditich`;
/*!40000 ALTER TABLE `loaiditich` DISABLE KEYS */;
/*!40000 ALTER TABLE `loaiditich` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.loailehoi
DROP TABLE IF EXISTS `loailehoi`;
CREATE TABLE IF NOT EXISTS `loailehoi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `soThuTu` int(11) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK737mkirs97hivm6cmwu431mfo` (`nguoiSua_id`),
  KEY `FKgqysh0v6g4maim7ro3wm4vwip` (`nguoiTao_id`),
  CONSTRAINT `FK737mkirs97hivm6cmwu431mfo` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKgqysh0v6g4maim7ro3wm4vwip` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.loailehoi: ~0 rows (approximately)
DELETE FROM `loailehoi`;
/*!40000 ALTER TABLE `loailehoi` DISABLE KEYS */;
/*!40000 ALTER TABLE `loailehoi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.nhanvien
DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `checkKichHoat` bit(1) NOT NULL,
  `chucVu` varchar(255) DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hoVaTen` varchar(255) DEFAULT NULL,
  `matKhau` varchar(255) DEFAULT NULL,
  `ngaySinh` datetime DEFAULT NULL,
  `pathAvatar` varchar(255) DEFAULT NULL,
  `salkey` varchar(255) DEFAULT NULL,
  `selectedDV` bit(1) NOT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `phongBan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKea6is3f6do1ybghqu5uo8xiap` (`nguoiSua_id`),
  KEY `FKlyyrr2uas0f50iupka9ergm8x` (`nguoiTao_id`),
  KEY `FK3qvwtuxp7rmr2kvksmb0bh34g` (`phongBan_id`),
  CONSTRAINT `FK3qvwtuxp7rmr2kvksmb0bh34g` FOREIGN KEY (`phongBan_id`) REFERENCES `phongban` (`id`),
  CONSTRAINT `FKea6is3f6do1ybghqu5uo8xiap` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKlyyrr2uas0f50iupka9ergm8x` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.nhanvien: ~11 rows (approximately)
DELETE FROM `nhanvien`;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `checkKichHoat`, `chucVu`, `diaChi`, `email`, `hoVaTen`, `matKhau`, `ngaySinh`, `pathAvatar`, `salkey`, `selectedDV`, `soDienThoai`, `nguoiSua_id`, `nguoiTao_id`, `phongBan_id`) VALUES
	(1, b'0', '2018-11-05 15:34:08', '2018-11-05 15:34:08', 'ap_dung', b'0', '', '', 'admin@greenglobal.vn', 'Super Admin', 'RvxJVVj3R+X7PNPj9AcCJuvTMlGtPSI8', NULL, '', 'iZz36mQl2qfG4ZZEnjUSdTzdTWjn4wQo', b'0', '', NULL, NULL, NULL),
	(2, b'0', '2018-11-05 15:59:11', '2018-11-05 15:59:11', 'ap_dung', b'0', '', '', 'trangvtk@danang.gov.vn', 'Võ Thị Kiều Trang', 'laazf97nxzQtNu3Js3q1D5p1UySHgNNi', NULL, '', 'urCf2zlLSw806FGDDHSt5TSwPSJryvE+', b'0', '', 1, 1, 1),
	(3, b'0', '2018-11-05 15:59:29', '2018-11-05 15:59:29', 'ap_dung', b'0', '', '', 'maintt@danang.gov.vn', 'Nguyễn Thị Tuyết Mai', 'A9UePq+rNdWznt2kqIkgeN8hFfEgfTiW', NULL, '', 'fRgJ//2MonOykjh0RKrSjk0of3F6AO9E', b'0', '', 1, 1, 1),
	(4, b'0', '2018-11-05 15:59:45', '2018-11-05 15:59:45', 'ap_dung', b'0', '', '', 'hieutt@danang.gov.vn', 'Trần Trung Hiếu', 'U7B1QJ4mQPHD/yy6XE7rwFVEvn5Qq87N', NULL, '', 'ZrehvZoll2UZnIRmG6bjNHphP9ZdYMTn', b'0', '', 1, 1, 1),
	(5, b'0', '2018-11-05 16:00:03', '2018-11-05 16:00:03', 'ap_dung', b'0', '', '', 'tuvxc@danang.gov.vn', 'Vũ Xuân Cẩm Tú', 'mTByyoF0OaJ6W937c3kiBXO4RL9Vzy1s', NULL, '', 't1l2bAchxVu8a0+ASCApYUpinZAoOET1', b'0', '', 1, 1, 1),
	(6, b'0', '2018-11-05 16:00:22', '2018-11-05 16:00:22', 'ap_dung', b'0', '', '', 'lamhtm@danang.gov.vn', 'Huỳnh Thị Mai Lâm', 'zj2eKBA+hhZUSjL0kto9TWzPTL3LNjC4', NULL, '', '+Edm/m+2dfD6aWUMip6h5Hx8D+Tbi1SG', b'0', '', 1, 1, 1),
	(7, b'0', '2018-11-05 16:00:53', '2018-11-05 16:00:53', 'ap_dung', b'0', '', '', 'ducvm@danang.gov.vn', 'Vũ Minh Đức', '/xWfDKYK5ppTL9nxaHvHkMR5A/JXhJA6', NULL, '', 'VJvoywVC2jgjCgvYO7roOkvDtl/pZwPQ', b'0', '', 1, 1, 1),
	(8, b'0', '2018-11-05 16:01:12', '2018-11-05 16:01:12', 'ap_dung', b'0', '', '', 'thangtna@danang.gov.vn', 'Trần Nguyễn An Thắng', 'GGCEslJSPxt241k3bBoJrDqRV5jY4N71', NULL, '', 'DTOPo0OfXNeb5IJM0fxOZa1p0V6cJczr', b'0', '', 1, 1, 1),
	(9, b'0', '2018-11-05 16:01:51', '2018-11-05 16:01:51', 'ap_dung', b'0', '', '', 'lanhdao@danang.gov.vn', 'Lãnh đạo', '7FxtkTr3iHy+XvvbSoG6GAV5DJy9ieXc', NULL, '', 'dEUMni2ho6g6yqAGShJFdwoV/FYRQd1A', b'0', '', 1, 1, 1),
	(10, b'0', '2018-11-05 16:02:12', '2018-11-05 16:02:12', 'ap_dung', b'0', '', '', 'truongphong001@danang.gov.vn', 'Trưởng phòng 001', '1Vm9Fxiuo6tWGUyQPGKVa8jTSYocHArO', NULL, '', 'lUy2xOQeHJgu6gSIkAo4gZP1XcAjGtoA', b'0', '', 1, 1, 1),
	(11, b'0', '2018-11-05 16:02:28', '2018-11-05 16:02:28', 'ap_dung', b'0', '', '', 'truongphong002@danang.gov.vn', 'Trưởng phòng 002', 'rYOm6Ldi0Kw0+XUM1cC1jQdKQYWMrNo6', NULL, '', 'UpNRrh7hRBCkYnd/H8TYHsx3qZ+7oPlj', b'0', '', 1, 1, 1);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.nhanvien_quyens
DROP TABLE IF EXISTS `nhanvien_quyens`;
CREATE TABLE IF NOT EXISTS `nhanvien_quyens` (
  `nhanVien_id` bigint(20) NOT NULL,
  `quyens` varchar(255) DEFAULT NULL,
  KEY `FKnlof2gbqm97pmg0mpfr2ytmui` (`nhanVien_id`),
  CONSTRAINT `FKnlof2gbqm97pmg0mpfr2ytmui` FOREIGN KEY (`nhanVien_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.nhanvien_quyens: ~0 rows (approximately)
DELETE FROM `nhanvien_quyens`;
/*!40000 ALTER TABLE `nhanvien_quyens` DISABLE KEYS */;
INSERT INTO `nhanvien_quyens` (`nhanVien_id`, `quyens`) VALUES
	(1, '*');
/*!40000 ALTER TABLE `nhanvien_quyens` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.nhanvien_vaitro
DROP TABLE IF EXISTS `nhanvien_vaitro`;
CREATE TABLE IF NOT EXISTS `nhanvien_vaitro` (
  `nhanvien_id` bigint(20) NOT NULL,
  `vaitros_id` bigint(20) NOT NULL,
  PRIMARY KEY (`nhanvien_id`,`vaitros_id`),
  KEY `FKidefm6rhsejb07ce6hcdlecg` (`vaitros_id`),
  CONSTRAINT `FK1a3c4rpnp3nnpo6virnexlgjo` FOREIGN KEY (`nhanvien_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKidefm6rhsejb07ce6hcdlecg` FOREIGN KEY (`vaitros_id`) REFERENCES `vaitro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.nhanvien_vaitro: ~10 rows (approximately)
DELETE FROM `nhanvien_vaitro`;
/*!40000 ALTER TABLE `nhanvien_vaitro` DISABLE KEYS */;
INSERT INTO `nhanvien_vaitro` (`nhanvien_id`, `vaitros_id`) VALUES
	(2, 2),
	(3, 2),
	(4, 2),
	(5, 2),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 3),
	(10, 4),
	(11, 4);
/*!40000 ALTER TABLE `nhanvien_vaitro` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.phongban
DROP TABLE IF EXISTS `phongban`;
CREATE TABLE IF NOT EXISTS `phongban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` longtext,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1jfu4n83q1rny3eqlxx7q2j6` (`nguoiSua_id`),
  KEY `FKfobc77hmqd3hx4kgmoc2fnmyn` (`nguoiTao_id`),
  CONSTRAINT `FKfobc77hmqd3hx4kgmoc2fnmyn` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKn1jfu4n83q1rny3eqlxx7q2j6` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.phongban: ~4 rows (approximately)
DELETE FROM `phongban`;
/*!40000 ALTER TABLE `phongban` DISABLE KEYS */;
INSERT INTO `phongban` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-05 15:57:34', '2018-11-05 15:57:34', 'ap_dung', 'Tư vấn đầu tư - Phát triển dự án', 'Tư vấn đầu tư - Phát triển dự án', 1, 1),
	(2, b'0', '2018-11-05 15:57:40', '2018-11-05 15:57:40', 'ap_dung', 'Văn phòng', 'Văn phòng', 1, 1),
	(3, b'0', '2018-11-05 15:57:45', '2018-11-05 15:57:45', 'ap_dung', 'Xúc tiến đầu tư', 'Xúc tiến đầu tư', 1, 1),
	(4, b'0', '2018-11-05 15:57:50', '2018-11-05 15:57:50', 'ap_dung', 'Hỗ trợ đầu tư', 'Hỗ trợ đầu tư', 1, 1);
/*!40000 ALTER TABLE `phongban` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.sessioncount
DROP TABLE IF EXISTS `sessioncount`;
CREATE TABLE IF NOT EXISTS `sessioncount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `hostName` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `sessionId` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX6y95ko2jyoux28yfwqlvfgt5y` (`sessionId`),
  KEY `FK33u1wllpubpeaor6et0hy6j1c` (`nguoiSua_id`),
  KEY `FKgw3b3qkj5ylnajbjir8dq6bx1` (`nguoiTao_id`),
  CONSTRAINT `FK33u1wllpubpeaor6et0hy6j1c` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKgw3b3qkj5ylnajbjir8dq6bx1` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.sessioncount: ~0 rows (approximately)
DELETE FROM `sessioncount`;
/*!40000 ALTER TABLE `sessioncount` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessioncount` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.settings
DROP TABLE IF EXISTS `settings`;
CREATE TABLE IF NOT EXISTS `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `canBoQuanLy` bit(1) NOT NULL,
  `dacDiemNhanDang` bit(1) NOT NULL,
  `dem` bigint(20) NOT NULL,
  `diaChiHienNay` bit(1) NOT NULL,
  `diaChiHienNayHuyen` bit(1) NOT NULL,
  `diaChiHienNayTinh` bit(1) NOT NULL,
  `diaChiHienNayToDanPho` bit(1) NOT NULL,
  `diaChiHienNayXa` bit(1) NOT NULL,
  `diaChiThuongTru` bit(1) NOT NULL,
  `diaChiThuongTruHuyen` bit(1) NOT NULL,
  `diaChiThuongTruTinh` bit(1) NOT NULL,
  `diaChiThuongTruToDanPho` bit(1) NOT NULL,
  `diaChiThuongTruXa` bit(1) NOT NULL,
  `donViCanBoQuanLy` bit(1) NOT NULL,
  `email` bit(1) NOT NULL,
  `ngayCapCMND` bit(1) NOT NULL,
  `ngayQuanLySauKhiRaTrungTam` int(11) NOT NULL,
  `ngheNghiep` bit(1) NOT NULL,
  `noiCapCMND` bit(1) NOT NULL,
  `soCMND` bit(1) NOT NULL,
  `soDTCanBoQuanLy` bit(1) NOT NULL,
  `soDienThoai` bit(1) NOT NULL,
  `soDinhDanh` bit(1) NOT NULL,
  `tenKhac` bit(1) NOT NULL,
  `thangQuanLySauCai` int(11) NOT NULL,
  `thangQuanLySauViPham` int(11) NOT NULL,
  `thanhPhanDoiTuong` bit(1) NOT NULL,
  `tinhTrangViecLam` bit(1) NOT NULL,
  `trinhDoHocVan` bit(1) NOT NULL,
  `widthMedium` int(11) NOT NULL,
  `widthSmall` int(11) NOT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp204jwnv8cjfxoni9c3s81ap1` (`nguoiSua_id`),
  KEY `FK3u6flv7dh5v0048tsnv4jtefd` (`nguoiTao_id`),
  CONSTRAINT `FK3u6flv7dh5v0048tsnv4jtefd` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKp204jwnv8cjfxoni9c3s81ap1` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.settings: ~0 rows (approximately)
DELETE FROM `settings`;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.teptin
DROP TABLE IF EXISTS `teptin`;
CREATE TABLE IF NOT EXISTS `teptin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `nameHash` varchar(255) DEFAULT NULL,
  `pathFile` varchar(255) DEFAULT NULL,
  `tenFile` varchar(255) DEFAULT NULL,
  `typeFile` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrrosiyu0g66a2g269ubsin7mh` (`nguoiSua_id`),
  KEY `FKrcexf7uj6tqr098i97larre3x` (`nguoiTao_id`),
  CONSTRAINT `FKrcexf7uj6tqr098i97larre3x` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKrrosiyu0g66a2g269ubsin7mh` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.teptin: ~0 rows (approximately)
DELETE FROM `teptin`;
/*!40000 ALTER TABLE `teptin` DISABLE KEYS */;
/*!40000 ALTER TABLE `teptin` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.thamso
DROP TABLE IF EXISTS `thamso`;
CREATE TABLE IF NOT EXISTS `thamso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `ma` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlaliea6x5rnn8bn2jt6kehqun` (`nguoiSua_id`),
  KEY `FKtnyvrvdhkcwpuj70pfhn42yy5` (`nguoiTao_id`),
  CONSTRAINT `FKlaliea6x5rnn8bn2jt6kehqun` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKtnyvrvdhkcwpuj70pfhn42yy5` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.thamso: ~0 rows (approximately)
DELETE FROM `thamso`;
/*!40000 ALTER TABLE `thamso` DISABLE KEYS */;
/*!40000 ALTER TABLE `thamso` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.thanhvien
DROP TABLE IF EXISTS `thanhvien`;
CREATE TABLE IF NOT EXISTS `thanhvien` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `chucDanh` varchar(255) DEFAULT NULL,
  `dienThoai` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hoVaTen` varchar(255) DEFAULT NULL,
  `quocGia` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `doanVao_id` bigint(20) DEFAULT NULL,
  `donVi_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8wp9uf6wfp05aobemvnn6mec` (`nguoiSua_id`),
  KEY `FKn9jv439o8bfb4clr3cmxxhgc8` (`nguoiTao_id`),
  KEY `FKsmb92q2k4km0e402t6up23089` (`doanVao_id`),
  KEY `FKhe270o0t6dnv3k05s7rusf7ar` (`donVi_id`),
  CONSTRAINT `FKh8wp9uf6wfp05aobemvnn6mec` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKhe270o0t6dnv3k05s7rusf7ar` FOREIGN KEY (`donVi_id`) REFERENCES `phongban` (`id`),
  CONSTRAINT `FKn9jv439o8bfb4clr3cmxxhgc8` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKsmb92q2k4km0e402t6up23089` FOREIGN KEY (`doanVao_id`) REFERENCES `doanvao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.thanhvien: ~0 rows (approximately)
DELETE FROM `thanhvien`;
/*!40000 ALTER TABLE `thanhvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanhvien` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.thanhviendoan
DROP TABLE IF EXISTS `thanhviendoan`;
CREATE TABLE IF NOT EXISTS `thanhviendoan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `chucDanh` varchar(255) DEFAULT NULL,
  `donVi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `hoVaTen` varchar(255) DEFAULT NULL,
  `quocGia` int(11) NOT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `doanVao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8n046ax6xssf8bjkcne8pcas` (`nguoiSua_id`),
  KEY `FK2wq0t5b2bxasdrybft9vq5v93` (`nguoiTao_id`),
  KEY `FK4ojn65wsb02t4inpoo7r0dijq` (`doanVao_id`),
  CONSTRAINT `FK2wq0t5b2bxasdrybft9vq5v93` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK4ojn65wsb02t4inpoo7r0dijq` FOREIGN KEY (`doanVao_id`) REFERENCES `doanvao` (`id`),
  CONSTRAINT `FKj8n046ax6xssf8bjkcne8pcas` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.thanhviendoan: ~0 rows (approximately)
DELETE FROM `thanhviendoan`;
/*!40000 ALTER TABLE `thanhviendoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `thanhviendoan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.thongbao
DROP TABLE IF EXISTS `thongbao`;
CREATE TABLE IF NOT EXISTS `thongbao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `daXem` bit(1) NOT NULL,
  `idObject` bigint(20) DEFAULT NULL,
  `loaiThongBao` int(11) DEFAULT NULL,
  `noiDung` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `nguoiGui_id` bigint(20) DEFAULT NULL,
  `nguoiNhan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpieyg7mkasgkf1jl4gxj3qsvw` (`nguoiSua_id`),
  KEY `FK83hv7q7ahv7mbktsy8tn4mwb7` (`nguoiTao_id`),
  KEY `FK3kymlanbdgk6u8f9kwxbeqa58` (`nguoiGui_id`),
  KEY `FK4ua79gs8n01q2v8nblg9bccls` (`nguoiNhan_id`),
  CONSTRAINT `FK3kymlanbdgk6u8f9kwxbeqa58` FOREIGN KEY (`nguoiGui_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK4ua79gs8n01q2v8nblg9bccls` FOREIGN KEY (`nguoiNhan_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK83hv7q7ahv7mbktsy8tn4mwb7` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKpieyg7mkasgkf1jl4gxj3qsvw` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.thongbao: ~0 rows (approximately)
DELETE FROM `thongbao`;
/*!40000 ALTER TABLE `thongbao` DISABLE KEYS */;
/*!40000 ALTER TABLE `thongbao` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.thuctrangditich
DROP TABLE IF EXISTS `thuctrangditich`;
CREATE TABLE IF NOT EXISTS `thuctrangditich` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65w7tedgjqi2x34ne2cx2f82p` (`nguoiSua_id`),
  KEY `FK7g6u570rgvvjicvx0jq95wdlf` (`nguoiTao_id`),
  CONSTRAINT `FK65w7tedgjqi2x34ne2cx2f82p` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK7g6u570rgvvjicvx0jq95wdlf` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.thuctrangditich: ~0 rows (approximately)
DELETE FROM `thuctrangditich`;
/*!40000 ALTER TABLE `thuctrangditich` DISABLE KEYS */;
/*!40000 ALTER TABLE `thuctrangditich` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.vaitro
DROP TABLE IF EXISTS `vaitro`;
CREATE TABLE IF NOT EXISTS `vaitro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `checkKichHoat` bit(1) NOT NULL,
  `loaiVaiTro` varchar(255) DEFAULT NULL,
  `soThuTu` int(11) NOT NULL,
  `tenVaiTro` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXcmrfq32k80bedobmb03xl79ev` (`alias`),
  KEY `IDXh5nr4ds4p644a4lr7aicqyedy` (`tenVaiTro`),
  KEY `FK5qixi7fouimmv3r9fau58tk2q` (`nguoiSua_id`),
  KEY `FK9b0cfnlecgosjvuq6lkxe8fy9` (`nguoiTao_id`),
  CONSTRAINT `FK5qixi7fouimmv3r9fau58tk2q` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK9b0cfnlecgosjvuq6lkxe8fy9` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.vaitro: ~4 rows (approximately)
DELETE FROM `vaitro`;
/*!40000 ALTER TABLE `vaitro` DISABLE KEYS */;
INSERT INTO `vaitro` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `alias`, `checkKichHoat`, `loaiVaiTro`, `soThuTu`, `tenVaiTro`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-05 15:54:45', '2018-11-05 15:34:37', 'ap_dung', 'quantrihethong', b'0', 'VAI_TRO_LANH_DAO', 0, 'Quản trị hệ thống', 1, 1),
	(2, b'0', '2018-11-05 15:54:00', '2018-11-05 15:34:37', 'ap_dung', 'chuyenvien', b'0', 'VAI_TRO_CHUYEN_VIEN', 0, 'Chuyên viên', 1, 1),
	(3, b'0', '2018-11-05 15:54:42', '2018-11-05 15:34:37', 'ap_dung', 'lanhdao', b'0', 'VAI_TRO_LANH_DAO', 0, 'Lãnh đạo', 1, 1),
	(4, b'0', '2018-11-05 15:54:22', '2018-11-05 15:34:37', 'ap_dung', 'truongphong', b'0', 'VAI_TRO_TRUONG_PHONG', 0, 'Trưởng phòng', 1, 1);
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.vaitro_quyens
DROP TABLE IF EXISTS `vaitro_quyens`;
CREATE TABLE IF NOT EXISTS `vaitro_quyens` (
  `vaitro_id` bigint(20) NOT NULL,
  `quyens` varchar(255) DEFAULT NULL,
  KEY `FKqldf0fggg0f8sc37im018c5ti` (`vaitro_id`),
  CONSTRAINT `FKqldf0fggg0f8sc37im018c5ti` FOREIGN KEY (`vaitro_id`) REFERENCES `vaitro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.vaitro_quyens: ~87 rows (approximately)
DELETE FROM `vaitro_quyens`;
/*!40000 ALTER TABLE `vaitro_quyens` DISABLE KEYS */;
INSERT INTO `vaitro_quyens` (`vaitro_id`, `quyens`) VALUES
	(1, 'donvihanhchinh:sua'),
	(1, 'linhVucDuAn:xoa'),
	(1, 'donvihanhchinh:xem'),
	(1, 'donvihanhchinh:them'),
	(1, 'phongban:xoa'),
	(1, 'nguoidung:sua'),
	(1, 'vaitro:tim'),
	(1, 'nguoidung:xem'),
	(1, 'phongban:sua'),
	(1, 'phongban:xem'),
	(1, 'linhVucDuAn:lietke'),
	(1, 'linhVucDuAn:sua'),
	(1, 'linhVucDuAn:xem'),
	(1, 'nguoidung:them'),
	(1, 'vaitro:xem'),
	(1, 'vaitro:sua'),
	(1, 'nguoidung:xoa'),
	(1, 'nguoidung:lietke'),
	(1, 'vaitro:them'),
	(1, 'vaitro:xoa'),
	(1, 'phongban:lietke'),
	(1, 'linhVucDuAn:them'),
	(1, 'phongban:them'),
	(1, 'donvihanhchinh:xoa'),
	(2, 'vaitro:tim'),
	(2, 'vaitro:xem'),
	(2, 'vaitro:them'),
	(2, 'vaitro:xoa'),
	(2, 'quanlyduan:giaoviec'),
	(2, 'quanlyduan:sua'),
	(2, 'vaitro:sua'),
	(2, 'quanlyduan:xoa'),
	(2, 'quanlyduan:lietke'),
	(2, 'quanlyduan:them'),
	(2, 'quanlyduan:nhacnho'),
	(2, 'quanlyduan:xem'),
	(2, 'quanlyduan'),
	(3, 'vaitro:tim'),
	(3, 'quanlyduan:xem'),
	(3, 'vaitro:xem'),
	(3, 'quanlyduan:nhacnho'),
	(3, 'vaitro:them'),
	(3, 'vaitro:xoa'),
	(3, 'quanlyduan:them'),
	(3, 'quanlyduan:xoa'),
	(3, 'quanlyduan:sua'),
	(3, 'vaitro:sua'),
	(3, 'quanlyduan:lietke'),
	(3, 'quanlyduan:giaoviec'),
	(3, 'quanlyduan'),
	(4, 'vaitro:tim'),
	(4, 'quanlyduan:xem'),
	(4, 'vaitro:xem'),
	(4, 'quanlyduan:nhacnho'),
	(4, 'vaitro:them'),
	(4, 'vaitro:xoa'),
	(4, 'quanlyduan:them'),
	(4, 'quanlyduan:xoa'),
	(4, 'quanlyduan:sua'),
	(4, 'vaitro:sua'),
	(4, 'quanlyduan:lietke'),
	(4, 'quanlyduan:giaoviec'),
	(4, 'quanlyduan'),
	(1, 'linhVucDuAn'),
	(1, 'quanlyduan'),
	(1, 'quanlyduan:xem'),
	(1, 'quanlygiaoviec:them'),
	(1, 'quanlydoanvao:xoa'),
	(1, 'quanlyduan:nhacnho'),
	(1, 'nguoidung'),
	(1, 'quanlyduan:them'),
	(1, 'quanlydoanvao:lietke'),
	(1, 'quanlydoanvao:sua'),
	(1, 'quanlygiaoviec:xem'),
	(1, 'quanlydoanvao'),
	(1, 'quanlyduan:xoa'),
	(1, 'phongban'),
	(1, 'quanlygiaoviec:lietke'),
	(1, 'donvihanhchinh'),
	(1, 'quanlygiaoviec'),
	(1, 'quanlyduan:sua'),
	(1, 'quanlyduan:lietke'),
	(1, 'quanlygiaoviec:xoa'),
	(1, 'quanlyduan:giaoviec'),
	(1, 'quanlydoanvao:xem'),
	(1, 'quanlydoanvao:them'),
	(1, 'quanlygiaoviec:sua');
/*!40000 ALTER TABLE `vaitro_quyens` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.video
DROP TABLE IF EXISTS `video`;
CREATE TABLE IF NOT EXISTS `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `pathAvatar` varchar(255) DEFAULT NULL,
  `typeVideo` varchar(255) DEFAULT NULL,
  `urlVideo` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdqu3pi315rdfelhtncxb5ef5x` (`nguoiSua_id`),
  KEY `FKovwponteaes1f4srct7p2lc0p` (`nguoiTao_id`),
  CONSTRAINT `FKdqu3pi315rdfelhtncxb5ef5x` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKovwponteaes1f4srct7p2lc0p` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.video: ~0 rows (approximately)
DELETE FROM `video`;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
/*!40000 ALTER TABLE `video` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
