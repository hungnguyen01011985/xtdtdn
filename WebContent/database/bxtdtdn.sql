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
  KEY `FKlwu9xeremec3qfux5rk19v7yp` (`nguoiTao_id`),
  CONSTRAINT `FKj8f045crwo3qj1iro2juw73no` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKlwu9xeremec3qfux5rk19v7yp` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.capdonvi: ~2 rows (approximately)
DELETE FROM `capdonvi`;
/*!40000 ALTER TABLE `capdonvi` DISABLE KEYS */;
INSERT INTO `capdonvi` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-05 18:03:35', '2018-11-05 18:03:35', 'ap_dung', NULL, 'Sở ban ngành', 1, 1),
	(2, b'0', '2018-11-05 18:03:35', '2018-11-05 18:03:35', 'ap_dung', NULL, 'Quận huyện', 1, 1);
/*!40000 ALTER TABLE `capdonvi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.congchucsonoivu
DROP TABLE IF EXISTS `congchucsonoivu`;
CREATE TABLE IF NOT EXISTS `congchucsonoivu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `bacLuong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `chucVuId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `chucVuTuongDuong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `diDong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `dienThoaiLamViec` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `donViId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `donVi_maSo` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `emailTinhThanh` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `fileAnhThe` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `fileCmnd` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `gioiTinh` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `heSoLuong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `hoSo_ngayHieuChinh` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `hoTen` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `hoVaTen` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `idHeThong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `maNgach` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `maSo` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `ngayCapCmnd` datetime DEFAULT NULL,
  `ngaySinh` datetime DEFAULT NULL,
  `noiCapCmnd` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `phongId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `phong_maSo` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `search` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `soCmnd` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenChucVu` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenDonVi` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenDonViSearch` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenNgach` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenPhong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenPhongSearch` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenTrangThai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `trangThaiId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `updated` bit(1) NOT NULL,
  `vuotKhung` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `trangThai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk1a0eyd4aqpfdbvvwo1q2ahj8` (`nguoiSua_id`),
  KEY `FKadw15jnpfca75kqe51p80mam0` (`nguoiTao_id`),
  CONSTRAINT `FKadw15jnpfca75kqe51p80mam0` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKk1a0eyd4aqpfdbvvwo1q2ahj8` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- Dumping data for table bxtdtdn.congchucsonoivu: ~41 rows (approximately)
DELETE FROM `congchucsonoivu`;
/*!40000 ALTER TABLE `congchucsonoivu` DISABLE KEYS */;
INSERT INTO `congchucsonoivu` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `nguoiSua_id`, `nguoiTao_id`, `bacLuong`, `chucVuId`, `chucVuTuongDuong`, `diDong`, `dienThoaiLamViec`, `donViId`, `donVi_maSo`, `email`, `emailTinhThanh`, `fileAnhThe`, `fileCmnd`, `gioiTinh`, `heSoLuong`, `hoSo_ngayHieuChinh`, `hoTen`, `hoVaTen`, `idHeThong`, `maNgach`, `maSo`, `ngayCapCmnd`, `ngaySinh`, `noiCapCmnd`, `phongId`, `phong_maSo`, `search`, `soCmnd`, `tenChucVu`, `tenDonVi`, `tenDonViSearch`, `tenNgach`, `tenPhong`, `tenPhongSearch`, `tenTrangThai`, `trangThaiId`, `updated`, `vuotKhung`, `trangThai`) VALUES
	(1, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '', '99', '0933331191', '3880243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/2014042105115913589.jpg', 'http://49.156.54.87/uploader/files/cmnd/20140519034001105.pdf', 'Nữ', '2.67', '2018-12-06', 'vũ xuân cẩm tú', 'Vũ Xuân Cẩm Tú', '184708', '01003', '0030359', '2008-01-23 00:08:00', '1991-01-30 00:11:00', 'Thành phố Đà Nẵng', '154953', '', 'Vũ Xuân Cẩm Tú 201625206 ', '201625206', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(2, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '11024', '4', '0905509560', '3812 340', '151710', '00033', 'anhnk@danang.gov.vn', 'anhnk@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014040102332222847.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042605131418916.pdf', 'Nam', '4.74', '2018-08-24', 'nguyễn kỳ anh', 'Nguyễn Kỳ Anh', '152299', '01002', '0013331', '2014-01-04 00:03:00', '1974-01-03 00:10:00', 'Hà Tĩnh', '151723', '00033.2', 'Nguyễn Kỳ Anh 201615868 anhnk@danang.gov.vn', '201615868', 'Phó Giám đốc', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên Chính', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(3, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '0', '99', '0935399138', '3886243', '151710', '00033', 'mynh@danang.gov.vn', 'mynh@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404210447569669.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042106334913075.pdf', 'Nữ', '2.67', '2016-06-27', 'nguyễn hạ my', 'Nguyễn Hạ My', '176982', '01003', '0030424', '2007-01-19 00:06:00', '1990-01-13 00:08:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Nguyễn Hạ My 201608139 mynh@danang.gov.vn', '201608139', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(4, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '4', '11024', '4', '0905211755', '02363886243', '151710', '00033', 'phuonghl@danang.gov.vn', 'phuonghl@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042204354623226.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041510501726147.pdf', 'Nữ', '5.42', '2018-12-03', 'huỳnh liên phương', 'Huỳnh Liên Phương', '154502', '01002', '0015303', '2002-01-03 00:09:00', '1969-01-17 00:12:00', 'Thành phố Đà Nẵng', '151723', '00033.2', 'Huỳnh Liên Phương 201046426 phuonghl@danang.gov.vn', '201046426', 'Phó Giám đốc', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên Chính', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(5, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '', '99', '0983134059', '0511-3886243', '151710', '00033', 'duonglc@danang.gov.vn', 'duonglc@danang.gov.vn', 'http://49.156.54.87/uploader/?file=beaae63b8e86756645815935f98c626d&download=1', '', 'Nam', '4.74', '2018-07-03', 'lê cảnh dương', 'Lê Cảnh Dương', '154402', '01002', '0015302', '2011-01-10 00:05:00', '1975-01-14 00:04:00', 'Thành phố Đà Nẵng', '151723', '00033.2', 'Lê Cảnh Dương 201328883 duonglc@danang.gov.vn', '201328883', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên Chính', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(6, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '9', '11024', '4', '0913403599', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/2014042204470329544.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041510432924764.pdf', 'Nam', '4.98', '2015-07-07', 'trương hào', 'Trương Hào', '154506', '01003', '0015305', '2004-01-23 00:08:00', '1955-01-10 00:06:00', 'Thành phố Đà Nẵng', '151723', '00033.2', 'Trương Hào 201546124 ', '201546124', 'Phó Giám đốc', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 'Đã nghỉ hưu', '02', b'0', '9', 'ap_dung'),
	(7, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '4', '11032', '6', '', '', '151710', '00033', 'hienptb@danang.gov.vn', 'hienptb@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014041805361226830.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014051903510923185.pdf', 'Nữ', '3.33', '2018-11-14', 'phạm thị bích hiền', 'Phạm Thị Bích Hiền', '154575', '01003', '0030274', '2013-01-12 00:01:00', '1986-01-13 00:12:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Phạm Thị Bích Hiền 201523612 hienptb@danang.gov.vn', '201523612', 'Phó Trưởng phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(8, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '5', '11028', '5', '0914251181', '3886243', '151710', '00033', 'tramdtq@danang.gov.vn', 'tramdtq@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042115593318530.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042203070430892.pdf', 'Nữ', '3.66', '2018-11-14', 'đỗ thị quỳnh trâm', 'Đỗ Thị Quỳnh Trâm', '154579', '01003', '0020482', '2009-01-31 00:12:00', '1981-01-25 00:11:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Đỗ Thị Quỳnh Trâm 201392918 tramdtq@danang.gov.vn', '201392918', 'Trưởng phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(9, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '1', '0', '99', '', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/2014041805385314354.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041805391819084.pdf', 'Nam', '2.34', '2015-01-13', 'bùi ngọc quang', 'Bùi Ngọc Quang', '154594', '01003', '0022374', '2002-01-19 00:09:00', '1984-01-05 00:09:00', 'Thành phố Đà Nẵng', '151721', '00033.4', 'Bùi Ngọc Quang 201519633 ', '201519633', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(10, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '4', '', '99', '0905159199', '3886243', '151710', '00033', 'trangdt1@danang.gov.vn', 'trangdt1@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404210557426790.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404160403459638.pdf', 'Nữ', '3.33', '2018-01-02', 'đinh thùy trang', 'Đinh Thùy Trang', '154659', '01003', '0022370', '2003-01-07 00:08:00', '1986-01-06 00:08:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Đinh Thùy Trang 201531116 trangdt1@danang.gov.vn', '201531116', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(11, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '3', '0', '99', '', '3886243', '151710', '00033', 'ngochnt@danang.gov.vn', 'ngochnt@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014041805295427068.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041805303826789.pdf', 'Nữ', '3.00', '2016-12-01', 'huỳnh nguyễn thiên ngọc', 'Huỳnh Nguyễn Thiên Ngọc', '154631', '01003', '0021072', '2013-01-07 00:05:00', '1987-01-10 00:05:00', 'Thành phố Đà Nẵng', '151721', '00033.4', 'Huỳnh Nguyễn Thiên Ngọc 201523071 ngochnt@danang.gov.vn', '201523071', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(12, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '6', '11028', '5', '0905000555', '3886243', '151710', '00033', 'phuchtd2@danang.gov.vn', 'phuchtd2@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042104472717486.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404220315413951.pdf', 'Nữ', '3.99', '2018-12-05', 'hường thị diễm phúc', 'Hường Thị Diễm Phúc', '154633', '01003', '0020307', '2001-01-04 00:05:00', '1979-01-21 00:04:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Hường Thị Diễm Phúc 201386621 phuchtd2@danang.gov.vn', '201386621', 'Chánh Văn phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(13, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '0', '99', '01265086992', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/2014042103521828740.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404210632299441.pdf', 'Nữ', '2.67', '2014-11-05', 'phan kim dung', 'Phan Kim Dung', '154635', '01003', '0030351', '2004-01-17 00:06:00', '1987-01-04 00:11:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Phan Kim Dung 201543543 ', '201543543', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(14, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '1', '0', '99', '0905112095', '3886243', '151710', '00033', '', '', '', '', 'Nam', '2.34', '', 'nguyễn thành long', 'Nguyễn Thành Long', '154637', '01003', '', NULL, '1988-01-26 00:10:00', '', '151722', '00033.3', 'Nguyễn Thành Long  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(15, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '5', '11032', '6', '0905154415', '3886243', '151710', '00033', 'nhidt@danang.gov.vn', 'nhidt@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201405060352336370.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041510222323175.pdf', 'Nữ', '5.76', '2018-11-14', 'dương thị nhi', 'Dương Thị Nhi', '154640', '01002', '0030270', '2007-01-27 00:02:00', '1965-01-14 00:07:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Dương Thị Nhi 200778941 nhidt@danang.gov.vn', '200778941', 'Phó Chánh Văn phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên Chính', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(16, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '9', '', '99', '0914025996', '3886243', '151710', '00033', 'dungtt1@danang.gov.vn', 'dungtt1@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201405051134215465.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404151100144721.pdf', 'Nam', '4.98', '2018-05-15', 'trần tuấn dũng', 'Trần Tuấn Dũng', '154646', '01003', '0030362', '2009-01-25 00:04:00', '1963-01-31 00:03:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Trần Tuấn Dũng 200687006 dungtt1@danang.gov.vn', '200687006', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Tổ chức - Hành chính', 'Phòng Tổ chức - Hành chính', 'Đang làm việc', '00', b'0', '9', 'ap_dung'),
	(17, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '5', '11032', '6', '', '3886243', '151710', '00033', 'thunta3@danang.gov.vn', 'thunta3@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042106284424896.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042106301621674.pdf', 'Nữ', '3.66', '2018-11-14', 'nguyễn thị anh thư', 'Nguyễn Thị Anh Thư', '154648', '01003', '0020312', '2010-01-28 00:10:00', '1979-01-28 00:06:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Nguyễn Thị Anh Thư 201359651 thunta3@danang.gov.vn', '201359651', 'Phó Chánh Văn phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(18, b'0', '2018-12-24 10:50:44', '2018-12-24 10:50:44', 1, 1, '2', '0', '99', '', '3886243', '151710', '00033', '', '', '', '', 'Nữ', '2.67', '', 'nguyễn kim chi', 'Nguyễn Kim Chi', '154662', '01003', '', NULL, '1985-01-10 00:06:00', '', '151720', '00033.5', 'Nguyễn Kim Chi  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Tư vấn đầu tư', 'Phòng Tư vấn đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(19, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '4', '0', '99', '', '3886243', '151710', '00033', 'diepnt@danang.gov.vn', 'diepnt@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404210503257348.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041512131514943.pdf', 'Nữ', '3.33', '2017-04-25', 'nguyễn thị điệp', 'Nguyễn Thị Điệp', '154650', '01003', '0015319', '2014-01-13 00:03:00', '1984-01-16 00:09:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Nguyễn Thị Điệp 201508145 diepnt@danang.gov.vn', '201508145', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(20, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '6', '11032', '6', '0982500868', '3886243', '151710', '00033', 'nhinh2@danang.gov.vn', 'nhinh2@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404220334536662.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404151056351743.pdf', 'Nam', '3.99', '2018-11-14', 'nguyễn hữu nhĩ', 'Nguyễn Hữu Nhĩ', '154652', '01003', '0012266', '2010-01-10 00:08:00', '1974-01-30 00:01:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Nguyễn Hữu Nhĩ 201274348 nhinh2@danang.gov.vn', '201274348', 'Phó Chánh Văn phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(21, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '', '99', '', '3886243', '151710', '00033', 'tramttt@danang.gov.vn', 'tramttt@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042204574727940.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042510595729049.pdf', 'Nữ', '2.67', '2018-10-15', 'trần thị thùy trâm', 'Trần Thị Thùy Trâm', '154655', '01003', '0030275', NULL, '1988-01-17 00:01:00', '', '151721', '00033.4', 'Trần Thị Thùy Trâm  tramttt@danang.gov.vn', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(22, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '4', '11028', '5', '', '3886243', '151710', '00033', 'trangvtk@danang.gov.vn', 'trangvtk@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042104583819293.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041603540926583.pdf', 'Nữ', '3.33', '2018-12-06', 'võ thị kiều trang', 'Võ Thị Kiều Trang', '154657', '01003', '0030272', '2001-01-07 00:08:00', '1984-01-30 00:08:00', 'Thành phố Đà Nẵng', '154953', '', 'Võ Thị Kiều Trang 201504347 trangvtk@danang.gov.vn', '201504347', 'Trưởng phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(23, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '4', '11032', '6', '0905078079', '', '151710', '00033', 'maintt2@danang.gov.vn', 'maintt2@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042105002121378.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404160402559654.pdf', 'Nữ', '3.33', '2018-12-05', 'nguyễn thị tuyết mai', 'Nguyễn Thị Tuyết Mai', '154660', '01003', '0020478', '2007-01-14 00:08:00', '1983-01-07 00:08:00', 'Thành phố Đà Nẵng', '154953', '', 'Nguyễn Thị Tuyết Mai 201485928 maintt2@danang.gov.vn', '201485928', 'Phó Trưởng phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(24, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '10', '0', '99', '', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/201404151200116611.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041512003129231.pdf', 'Nam', '3.67', '2018-11-14', 'trương cự', 'Trương Cự', '154664', '01005', '0031139', '2014-01-10 00:07:00', '1958-01-02 00:10:00', 'Thành phố Hồ Chí Minh', '151728', '00033.1', 'Trương Cự 023627446 ', '023627446', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Nhân viên (cũ)', 'Văn phòng', 'Văn phòng', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(25, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '8', '0', '99', '0934776670', '3886243', '151710', '00033', '', '', '', '', 'Nam', '2.76', '', 'kiều xuân lễ', 'Kiều Xuân Lễ', '154665', '01011', '', NULL, '1954-01-02 00:02:00', '', '151728', '00033.1', 'Kiều Xuân Lễ  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Nhân viên bảo vệ', 'Văn phòng', 'Văn phòng', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(26, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '', '0', '99', '0932560536', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/2014042105022126263.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014051903465729975.pdf', 'Nữ', '', '2014-11-10', 'phan thị sỹ', 'Phan Thị Sỹ', '154667', '', '0031398', '2014-01-01 00:04:00', '1958-01-05 00:02:00', 'Quảng Nam', '151728', '00033.1', 'Phan Thị Sỹ 206185658 ', '206185658', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', '', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(27, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '6', '', '99', '', '3886243', '151710', '00033', 'letth2@danang.gov.vn', 'letth2@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404151219269805.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041512194823567.pdf', 'Nữ', '3.99', '2018-11-14', 'trần thị hoài lê', 'Trần Thị Hoài Lê', '154669', '01003', '0030276', '2010-01-06 00:05:00', '1977-01-12 00:01:00', '', '151728', '00033.1', 'Trần Thị Hoài Lê 201264004 letth2@danang.gov.vn', '201264004', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(28, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '0', '99', '', '0511-3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/201404210627387799.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014042106030014102.pdf', 'Nữ', '2.67', '2017-05-29', 'trần thị quỳnh trang', 'Trần Thị Quỳnh Trang', '155465', '01003', '0030352', '2006-01-29 00:06:00', '1989-01-21 00:10:00', 'Thành phố Đà Nẵng', '151721', '00033.4', 'Trần Thị Quỳnh Trang 201577443 ', '201577443', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(29, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '', '99', '0905.855.551', '05113.683.146', '151710', '00033', 'hieutt2@danang.gov.vn', 'hieutt2@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014082609274714342.jpg', '', 'Nam', '2.67', '2016-08-11', 'trần trung hiếu', 'Trần Trung Hiếu', '157885', '01003', '', '2003-01-23 00:01:00', '1986-01-07 00:01:00', 'Thành phố Đà Nẵng', '151720', '00033.5', 'Trần Trung Hiếu 201.506.403 hieutt2@danang.gov.vn', '201.506.403', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Tư vấn đầu tư', 'Phòng Tư vấn đầu tư', 'Hồ sơ đã xóa', '15', b'0', '', 'ap_dung'),
	(30, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '0', '0', '99', '', '', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/201404210501552695.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404151203263713.pdf', 'Nam', '', '2014-11-04', 'đặng văn vương', 'Đặng Văn Vương', '171258', '', '', '2007-01-01 00:03:00', '1954-01-02 00:03:00', 'Thành phố Đà Nẵng', '151728', '00033.1', 'Đặng Văn Vương 200195512 ', '200195512', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', '', 'Văn phòng', 'Văn phòng', 'Đã thôi việc', '03', b'0', '0', 'ap_dung'),
	(31, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '4', '11032', '6', '0935031008', '', '151710', '00033', 'huongvtm1@danang.gov.vn', 'huongvtm1@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014042105112715698.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041604053927354.pdf', 'Nữ', '3.33', '2018-07-03', 'võ thị mai hương', 'Võ Thị Mai Hương', '173526', '01003', '0030353', '2004-01-20 00:07:00', '1988-01-03 00:10:00', 'Thành phố Đà Nẵng', '154953', '', 'Võ Thị Mai Hương 201544743 huongvtm1@danang.gov.vn', '201544743', 'Phó Trưởng phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(32, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '4', '0', '99', '0935278157', '', '151710', '00033', 'hanhvth@danang.gov.vn', 'hanhvth@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/201404210448286183.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404181008086015.pdf', 'Nữ', '3.33', '2018-07-03', 'vũ thị hồng hạnh', 'Vũ Thị Hồng Hạnh', '173947', '01003', '0030354', '2004-01-11 00:03:00', '1987-01-28 00:11:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Vũ Thị Hồng Hạnh 201540672 hanhvth@danang.gov.vn', '201540672', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(33, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '0', '99', '', '3886243', '151710', '00033', 'thudta@danang.gov.vn', 'thudta@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/2014050511273927631.jpg', 'http://49.156.54.87/uploader/files/cmnd/201404180542416070.pdf', 'Nữ', '2.67', '2016-05-09', 'đinh thị anh thư', 'Đinh thị Anh Thư', '174503', '01003', '0030355', '2009-01-28 00:05:00', '1988-01-12 00:10:00', 'Thành phố Đà Nẵng', '151721', '00033.4', 'Đinh thị Anh Thư 201545022 thudta@danang.gov.vn', '201545022', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 'Đã thôi việc', '03', b'0', '', 'ap_dung'),
	(34, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '', '99', '0941770603', '', '151710', '00033', 'longld@danang.gov.vn', 'longld@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/20140421045658840.jpg', 'http://49.156.54.87/uploader/files/cmnd/2014041811000531912.pdf', 'Nam', '2.67', '2018-01-02', 'lê đức long', 'Lê Đức Long', '183919', '01003', '0031400', '2004-01-27 00:07:00', '1988-01-06 00:03:00', 'Thành phố Đà Nẵng', '151722', '00033.3', 'Lê Đức Long 201544950 longld@danang.gov.vn', '201544950', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(35, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '3', '', '99', '', '', '151710', '00033', 'hieutt3@danang.gov.vn', 'hieutt3@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/TRAN_TRUNG_HIEU.jpg', 'http://49.156.54.87/uploader/?file=3924dd40ae1773be14aadd35fa9a93c7&download=1', 'Nam', '3.00', '2018-12-06', 'trần trung hiếu', 'Trần Trung Hiếu', '1113638', '01003', '0382573', NULL, '1986-01-07 00:01:00', '', '154953', '', 'Trần Trung Hiếu  hieutt3@danang.gov.vn', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(36, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '2', '11024', '4', '0905883482', '', '151710', '00033', 'tuonglm@danang.gov.vn', 'tuonglm@danang.gov.vn', 'https://cbccvc.danang.gov.vn/uploader/files/IMG%20%285%29.jpg', 'http://49.156.54.87/uploader/?file=79b7695ba35b30cb8b9aac7171e414ef&download=1', 'Nam', '4.74', '2018-10-15', 'lê minh tường ', 'Lê Minh Tường ', '1115350', '01002', '0037422', '2015-01-05 00:10:00', '1982-01-03 00:04:00', '', '151723', '00033.2', 'Lê Minh Tường  201454904 tuonglm@danang.gov.vn', '201454904', 'Phó Giám đốc', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên Chính', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(37, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '1', '', '99', '0905551099', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/14743566593_x_4_lam.jpg', 'http://49.156.54.87/uploader/files/1474357645CMND_mat_truoc.jpg', 'Nữ', '2.34', '2016-11-24', 'huỳnh thị mai lâm', 'Huỳnh Thị Mai Lâm', '1117237', '01003', '0040423', '2012-01-11 00:05:00', '1993-01-05 00:04:00', 'Quảng Nam', '154953', '', 'Huỳnh Thị Mai Lâm 205702442 ', '205702442', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(38, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '1', '', '99', '0935304803', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/1477467902thao_ipc_3_x_4.jpg', 'http://49.156.54.87/uploader/?file=dd5790f76f53ca21c905290b5617c70c&download=1', 'Nữ', '2.34', '2018-12-06', 'nguyễn thu thảo', 'Nguyễn Thu Thảo', '1117493', '01003', '0041056', NULL, '1991-01-08 00:03:00', '', '151722', '00033.3', 'Nguyễn Thu Thảo  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(39, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '1', '', '99', '', '', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/1502247123Minh_Duc.jpg', 'http://49.156.54.87/uploader/?file=b2634c24fd9d345b9867fd49a625c3f7&download=1', 'Nữ', '2.34', '2017-12-06', 'vũ thị minh đức', 'Vũ Thị Minh Đức', '1119147', '01003', '0045941', '2013-01-30 00:07:00', '1978-01-23 00:09:00', 'Thành phố Hà Nội', '154953', '', 'Vũ Thị Minh Đức  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(40, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '1', '', '99', '', '2363886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/1509358405thang_4_x_6.jpg', 'http://49.156.54.87/uploader/?file=0c56b5c622f9f92b93070c7dcb736194&download=1', 'Nam', '2.34', '2018-04-02', 'trần nguyễn an thắng', 'Trần Nguyễn An Thắng', '1119724', '01003', '0047469', '2009-01-20 00:08:00', '1994-01-16 00:02:00', 'Quảng Nam', '154953', '', 'Trần Nguyễn An Thắng 205687790 ', '205687790', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Chuyên viên', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 'Đang làm việc', '00', b'0', '', 'ap_dung'),
	(41, b'0', '2018-12-24 10:50:45', '2018-12-24 10:50:45', 1, 1, '', '', '99', '0901972344', '3886243', '151710', '00033', '', '', 'https://cbccvc.danang.gov.vn/uploader/files/1543395105ngoc.jpg', 'https://cbccvc.danang.gov.vn/uploader/?file=468fa49300b79efd6d7f4366d10fa002&download=1', 'Nam', '', '2018-12-03', 'trương hồng ngọc', 'TRƯƠNG HỒNG NGỌC', '1121692', '', '', NULL, '1996-01-13 00:04:00', '', '151728', '00033.1', 'TRƯƠNG HỒNG NGỌC  ', '', '', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', '', 'Văn phòng', 'Văn phòng', 'Đang làm việc', '00', b'0', '', 'ap_dung');
/*!40000 ALTER TABLE `congchucsonoivu` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.congvieckehoach
DROP TABLE IF EXISTS `congvieckehoach`;
CREATE TABLE IF NOT EXISTS `congvieckehoach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `loaiCongViecKeHoach_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdk0gka7y5b5ja5d4mxxcr9x0` (`nguoiSua_id`),
  KEY `FKg6qlp80xqcapiblc5jnlp3r0n` (`nguoiTao_id`),
  KEY `FKf5u84k64js8si4aq8t7h9ovfq` (`loaiCongViecKeHoach_id`),
  CONSTRAINT `FKdk0gka7y5b5ja5d4mxxcr9x0` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKf5u84k64js8si4aq8t7h9ovfq` FOREIGN KEY (`loaiCongViecKeHoach_id`) REFERENCES `loaicongviec` (`id`),
  CONSTRAINT `FKg6qlp80xqcapiblc5jnlp3r0n` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.congvieckehoach: ~11 rows (approximately)
DELETE FROM `congvieckehoach`;
/*!40000 ALTER TABLE `congvieckehoach` DISABLE KEYS */;
INSERT INTO `congvieckehoach` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`, `loaiCongViecKeHoach_id`) VALUES
	(1, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Lãnh đạo, người được phân công', 1, 1, 1),
	(2, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Chuyên viên', 1, 1, 1),
	(3, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Chuẩn bị phòng họp (nếu cần)', 1, 1, 2),
	(4, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Nước, quà, hoa quả (nếu cần)', 1, 1, 2),
	(5, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Thiết bị phục vụ phòng họp', 1, 1, 2),
	(6, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Chuẩn bị tài liệu giới thiệu', 1, 1, 2),
	(7, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Xây dựng chương trình làm việc cho đoàn', 1, 1, 3),
	(8, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Chuẩn bị bài giới thiệu', 1, 1, 3),
	(9, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Xác nhận lại thông tin thời gian làm việc với đoàn', 1, 1, 3),
	(10, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Ghi biên bản nội dung làm việc', 1, 1, 3),
	(11, b'0', '2018-12-13 14:20:25', '2018-12-13 14:20:26', 'ap_dung', NULL, 'Kiểm tra lại công tác chuẩn bị', 1, 1, 3);
/*!40000 ALTER TABLE `congvieckehoach` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.doanvao
DROP TABLE IF EXISTS `doanvao`;
CREATE TABLE IF NOT EXISTS `doanvao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `deXuatCVPhuTrach` varchar(255) DEFAULT NULL,
  `link` longtext,
  `noiDoanDiTham` varchar(255) DEFAULT NULL,
  `soNguoi` int(11) NOT NULL,
  `tenDoanVao` longtext,
  `thoiGianDenLamViec` datetime DEFAULT NULL,
  `tomTatNoiDungKQ` varchar(255) DEFAULT NULL,
  `trangThaiTiepDoan` varchar(255) DEFAULT NULL,
  `yKienChiDao` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `congVanChiDaoUB_id` bigint(20) DEFAULT NULL,
  `nguoiPhuTrach_id` bigint(20) DEFAULT NULL,
  `taiLieu_id` bigint(20) DEFAULT NULL,
  `idNguoiLienQuan` varchar(255) DEFAULT NULL,
  `tenQuocGia` varchar(255) DEFAULT NULL,
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

-- Dumping structure for table bxtdtdn.doanvao_teptin
DROP TABLE IF EXISTS `doanvao_teptin`;
CREATE TABLE IF NOT EXISTS `doanvao_teptin` (
  `doanvao_id` bigint(20) NOT NULL,
  `teptin_id` bigint(20) NOT NULL,
  KEY `FKtcrf48we33l0bthn6yr1ote10` (`teptin_id`),
  KEY `FKoimvqhxwmbcspj321866br96o` (`doanvao_id`),
  CONSTRAINT `FKoimvqhxwmbcspj321866br96o` FOREIGN KEY (`doanvao_id`) REFERENCES `doanvao` (`id`),
  CONSTRAINT `FKtcrf48we33l0bthn6yr1ote10` FOREIGN KEY (`teptin_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.doanvao_teptin: ~0 rows (approximately)
DELETE FROM `doanvao_teptin`;
/*!40000 ALTER TABLE `doanvao_teptin` DISABLE KEYS */;
/*!40000 ALTER TABLE `doanvao_teptin` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donvi
DROP TABLE IF EXISTS `donvi`;
CREATE TABLE IF NOT EXISTS `donvi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `loaiDonVi` varchar(255) DEFAULT NULL,
  `moTa` longtext,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqo1cbpspkivr6nmmtlb71wo4e` (`nguoiSua_id`),
  KEY `FKh4r0e2r8rma8vovk8w8cvdtcs` (`nguoiTao_id`),
  CONSTRAINT `FKh4r0e2r8rma8vovk8w8cvdtcs` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKqo1cbpspkivr6nmmtlb71wo4e` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donvi: ~7 rows (approximately)
DELETE FROM `donvi`;
/*!40000 ALTER TABLE `donvi` DISABLE KEYS */;
INSERT INTO `donvi` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `loaiDonVi`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-11-06 17:00:32', '2018-11-06 17:00:32', 'ap_dung', 'DON_VI_CHU_TRI', '', 'Trung tâm Phát triển quỹ đất', 1, 1),
	(2, b'0', '2018-11-06 17:00:39', '2018-11-06 17:00:39', 'ap_dung', 'DON_VI_CHU_TRI', '', 'BQL dự án đầu tư xây dựng các công trình giao thông', 1, 1),
	(3, b'0', '2018-11-06 17:00:49', '2018-11-06 17:00:49', 'ap_dung', 'DON_VI_CHU_TRI', '', 'BQL dự án đầu tư xây dựng các công trình NN&PTNT', 1, 1),
	(4, b'0', '2018-11-06 17:00:54', '2018-11-06 17:00:54', 'ap_dung', 'DON_VI_CHU_TRI', '', 'BQL dự án đầu tư xây dựng hạ tầng và phát triển đô thị', 1, 1),
	(5, b'0', '2018-11-06 17:01:07', '2018-11-06 17:01:07', 'ap_dung', 'DON_VI_CHU_TRI', '', 'BQL dự án đầu tư xây dựng các công trình dân dụng và công nghiệp', 1, 1),
	(6, b'0', '2018-11-06 17:01:16', '2018-11-06 17:01:16', 'ap_dung', 'DON_VI_TU_VAN', '', 'Viện Quy hoạch xây dựng Đà Nẵng', 1, 1),
	(7, b'0', '2018-11-06 17:02:28', '2018-11-06 17:02:28', 'ap_dung', 'DON_VI_THUC_HIEN', '', 'Sở Tài chính', 1, 1);
/*!40000 ALTER TABLE `donvi` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donviduan
DROP TABLE IF EXISTS `donviduan`;
CREATE TABLE IF NOT EXISTS `donviduan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
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
  KEY `FKg4a0kg9p53caenhnnbidwspcr` (`capDonVi_id`),
  KEY `FKdmpqrxwobc7q32b3uur1v9qds` (`donVi_id`),
  CONSTRAINT `FK496c3my4hb98cnl5v66mfuirj` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK63kkomy32teak8w3ieftmuw7m` FOREIGN KEY (`giaiDoanDuAn_id`) REFERENCES `giaidoanduan` (`id`),
  CONSTRAINT `FKdmpqrxwobc7q32b3uur1v9qds` FOREIGN KEY (`donVi_id`) REFERENCES `donvixuctien` (`id`),
  CONSTRAINT `FKdrs18gr9q794gjd8klsqnssi7` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKg4a0kg9p53caenhnnbidwspcr` FOREIGN KEY (`capDonVi_id`) REFERENCES `capdonvi` (`id`),
  CONSTRAINT `FKguhew035fr9due90afktyhejr` FOREIGN KEY (`congVanGiaiThich_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKv6emym9npdf5o3gawpcc07a2` FOREIGN KEY (`congVanTraLoi_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donviduan: ~0 rows (approximately)
DELETE FROM `donviduan`;
/*!40000 ALTER TABLE `donviduan` DISABLE KEYS */;
/*!40000 ALTER TABLE `donviduan` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.donvisonoivu
DROP TABLE IF EXISTS `donvisonoivu`;
CREATE TABLE IF NOT EXISTS `donvisonoivu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `capDonViId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `chaId` bigint(20) DEFAULT NULL,
  `diaChi` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `dienThoai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `donViCapTrenId` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `fax` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `idHeThong` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `level` int(11) NOT NULL,
  `lftType` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `maSoDonViCapTren` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `maSoToChuc` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `rgtType` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `search` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenCapDonVi` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenCapDonViSearch` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenDonVi` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenDonViCapTren` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenDonViCapTrenSearch` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `tenVietTat` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `updated` bit(1) NOT NULL,
  `trangThai` varchar(255) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKggwui9uc26vsh4uddakn0fxia` (`nguoiSua_id`),
  KEY `FK4xb4yvb5jmp37u9h2iwjlr0cy` (`nguoiTao_id`),
  CONSTRAINT `FK4xb4yvb5jmp37u9h2iwjlr0cy` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKggwui9uc26vsh4uddakn0fxia` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- Dumping data for table bxtdtdn.donvisonoivu: ~6 rows (approximately)
DELETE FROM `donvisonoivu`;
/*!40000 ALTER TABLE `donvisonoivu` DISABLE KEYS */;
INSERT INTO `donvisonoivu` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `nguoiSua_id`, `nguoiTao_id`, `capDonViId`, `chaId`, `diaChi`, `dienThoai`, `donViCapTrenId`, `email`, `fax`, `idHeThong`, `level`, `lftType`, `maSoDonViCapTren`, `maSoToChuc`, `rgtType`, `search`, `tenCapDonVi`, `tenCapDonViSearch`, `tenDonVi`, `tenDonViCapTren`, `tenDonViCapTrenSearch`, `tenVietTat`, `type`, `updated`, `trangThai`) VALUES
	(1, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '', 151710, '', '', '154666', '', '', '154953', 0, '8133', '00048', '', '8134', 'Phòng Phát triển dự án  ', '', '', 'Phòng Phát triển dự án', 'UBND thành phố Đà Nẵng', 'UBND thành phố Đà Nẵng', 'Phòng Phát triển dự án', '0', b'0', 'ap_dung'),
	(2, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '0', 151710, '', '', '151710', '', '', '151728', 0, '8131', '00033', '00033.1', '8132', 'Văn phòng 00033.1 ', '', '', 'Văn phòng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Văn phòng', '0', b'0', 'ap_dung'),
	(3, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '0', 151710, '', '', '151710', '', '', '151723', 0, '8129', '00033', '00033.2', '8130', 'Ban Lãnh đạo 00033.2 ', '', '', 'Ban Lãnh đạo', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Lãnh đạo', '0', b'0', 'ap_dung'),
	(4, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '0', 151710, '', '', '151710', '', '', '151722', 0, '8127', '00033', '00033.3', '8128', 'Phòng Xúc tiến đầu tư 00033.3 ', '', '', 'Phòng Xúc tiến đầu tư', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Phòng Xúc tiến đầu tư', '0', b'0', 'ap_dung'),
	(5, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '0', 151710, '', '', '151710', '', '', '151721', 0, '8125', '00033', '00033.4', '8126', 'Phòng Hỗ trợ đầu tư 00033.4 ', '', '', 'Phòng Hỗ trợ đầu tư', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Phòng Hỗ trợ đầu tư', '0', b'0', 'ap_dung'),
	(6, b'0', '2018-12-24 10:50:17', '2018-12-24 10:50:17', 1, 1, '0', 151710, '', '', '151710', '', '', '151720', 0, '8123', '00033', '00033.5', '8124', 'Phòng Tư vấn đầu tư 00033.5 ', '', '', 'Phòng Tư vấn đầu tư', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Ban Xúc tiến và Hỗ trợ đầu tư Đà Nẵng', 'Phòng Tư vấn đầu tư', '0', b'0', 'ap_dung');
/*!40000 ALTER TABLE `donvisonoivu` ENABLE KEYS */;

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
  KEY `FKdxo42sukdy01pmuf6y8xhx2ec` (`capDonVi_id`),
  CONSTRAINT `FK10mbxstuk5l7c2pp4bsi8gdbx` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKdxo42sukdy01pmuf6y8xhx2ec` FOREIGN KEY (`capDonVi_id`) REFERENCES `capdonvi` (`id`),
  CONSTRAINT `FKrhoiq8ydfovah5571ml26eqys` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.donvixuctien: ~32 rows (approximately)
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
  `giaiDoanXucTien` varchar(255) DEFAULT NULL,
  `idNguoiLienQuan` varchar(255) DEFAULT NULL,
  `khaNangDauTu` varchar(255) DEFAULT NULL,
  `mucDoUuTien` varchar(255) DEFAULT NULL,
  `mucTieuDuAn` longtext,
  `ngayBatDauXucTien` datetime DEFAULT NULL,
  `quyMoDuAn` varchar(255) DEFAULT NULL,
  `tenDuAn` longtext,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `linhVuc_id` bigint(20) DEFAULT NULL,
  `nguoiPhuTrach_id` bigint(20) DEFAULT NULL,
  `taiLieuNDT_id` bigint(20) DEFAULT NULL,
  `dienTichSuDungDat` double DEFAULT NULL,
  `tongVonDauTu` double DEFAULT NULL,
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
  `email` varchar(255) DEFAULT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `giaDatKhoiDiemDauGia` bigint(20) NOT NULL,
  `giaiDoanXucTien` varchar(255) DEFAULT NULL,
  `kiemTraThongBao` bit(1) NOT NULL,
  `ngayDuKienNhanPhanHoi` datetime DEFAULT NULL,
  `ngayGui` datetime DEFAULT NULL,
  `ngayGuiUBND` datetime DEFAULT NULL,
  `ngayKhaoSat` datetime DEFAULT NULL,
  `ngayNhanPhanHoi` datetime DEFAULT NULL,
  `ngayPhatHanhCV3` datetime DEFAULT NULL,
  `ngayPhatHanhCVGD2` datetime DEFAULT NULL,
  `ngayThongBaoOld` datetime DEFAULT NULL,
  `nguoiDaiDienPhapLy` varchar(255) DEFAULT NULL,
  `option` bit(1) NOT NULL,
  `phuongThucLuaChonNDT` varchar(255) DEFAULT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `tenCongTy` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `baoCaoDoDacKhuDat_id` bigint(20) DEFAULT NULL,
  `congVanGD2_id` bigint(20) DEFAULT NULL,
  `congVanGD3_id` bigint(20) DEFAULT NULL,
  `donViChuTri_id` bigint(20) DEFAULT NULL,
  `donViLapKeHoach_id` bigint(20) DEFAULT NULL,
  `donViThucHien_id` bigint(20) DEFAULT NULL,
  `donViTuVan_id` bigint(20) DEFAULT NULL,
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
  `daQuaGiaiDoan` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0tg50cys89jo0ppipmx65vc6` (`nguoiSua_id`),
  KEY `FKiovtr2bkv8rtnq9fu7w3u69m4` (`nguoiTao_id`),
  KEY `FK3k456x2prsltyaib0v3aqo40k` (`baoCaoDoDacKhuDat_id`),
  KEY `FK13v10ictjd7tufnep9f7pkb2e` (`congVanGD2_id`),
  KEY `FK9khye8wkfwkxwc7jkitjmb351` (`congVanGD3_id`),
  KEY `FKaveeq0mftnl3197kdfnya7pvy` (`donViChuTri_id`),
  KEY `FK8se3deuwrgsi6fx4kl1ghs99k` (`donViLapKeHoach_id`),
  KEY `FKkccvqdnpcp905ao88e2knibfy` (`donViThucHien_id`),
  KEY `FKg0s9q91sdoi7i1fb1lqyc4yxd` (`donViTuVan_id`),
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
  CONSTRAINT `FK8se3deuwrgsi6fx4kl1ghs99k` FOREIGN KEY (`donViLapKeHoach_id`) REFERENCES `donvi` (`id`),
  CONSTRAINT `FK98wb9lo3g6jmm4pwp1j9jk3j9` FOREIGN KEY (`vanBanDeNghiBoSung_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FK9khye8wkfwkxwc7jkitjmb351` FOREIGN KEY (`congVanGD3_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKaqx5swb048j26pmat7da9iq6b` FOREIGN KEY (`hoSoQuyHoachLQH_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKaveeq0mftnl3197kdfnya7pvy` FOREIGN KEY (`donViChuTri_id`) REFERENCES `donvi` (`id`),
  CONSTRAINT `FKc0tg50cys89jo0ppipmx65vc6` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKcna1e638vtd0ly0cefsbs96o5` FOREIGN KEY (`phuongAnTaiDinhCu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKet5xnvq9dclcbso869giasf6k` FOREIGN KEY (`quyetDinhPheDuyetBoSungKinhPhi_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKfeokeu9wyd8e4v8k90gabxt4t` FOREIGN KEY (`giayChungNhanQuyenSuDungDat_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKg0s9q91sdoi7i1fb1lqyc4yxd` FOREIGN KEY (`donViTuVan_id`) REFERENCES `donvi` (`id`),
  CONSTRAINT `FKgimoch72f5l1b4s9hit4dc8ty` FOREIGN KEY (`quyetDinhDauGiaQSDD_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKgo5hsmtncd6hr3o0cc06l6e1s` FOREIGN KEY (`quyetDinhPheDuyet2000_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKgwmh7c7vtvy08uweclioh94em` FOREIGN KEY (`taiLieuGD2_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKhv8lk8c6aerdaym0pmb0j67rv` FOREIGN KEY (`quyetDinhPheDuyetGiaKhoiDiem_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKic5u40grsamr45srt1gye5kig` FOREIGN KEY (`quyetDinhPheDuyetPADG_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKikf8uio2qjpv3qcxibp5i04u5` FOREIGN KEY (`quyetDinhPheDuyetLQH_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKiovtr2bkv8rtnq9fu7w3u69m4` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKkccvqdnpcp905ao88e2knibfy` FOREIGN KEY (`donViThucHien_id`) REFERENCES `donvi` (`id`),
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

-- Dumping structure for table bxtdtdn.giaidoanduan_teptin
DROP TABLE IF EXISTS `giaidoanduan_teptin`;
CREATE TABLE IF NOT EXISTS `giaidoanduan_teptin` (
  `giaidoanduan_id` bigint(20) NOT NULL,
  `teptin_id` bigint(20) NOT NULL,
  KEY `FKqi7vk6g2aud9xis6ebsus1g3u` (`teptin_id`),
  KEY `FKq3j9bxjfbnfpj1oyy13v54k3y` (`giaidoanduan_id`),
  CONSTRAINT `FKq3j9bxjfbnfpj1oyy13v54k3y` FOREIGN KEY (`giaidoanduan_id`) REFERENCES `giaidoanduan` (`id`),
  CONSTRAINT `FKqi7vk6g2aud9xis6ebsus1g3u` FOREIGN KEY (`teptin_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.giaidoanduan_teptin: ~0 rows (approximately)
DELETE FROM `giaidoanduan_teptin`;
/*!40000 ALTER TABLE `giaidoanduan_teptin` DISABLE KEYS */;
/*!40000 ALTER TABLE `giaidoanduan_teptin` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.giaidoanduan_tepTins
DROP TABLE IF EXISTS `giaidoanduan_tepTins`;
CREATE TABLE IF NOT EXISTS `giaidoanduan_tepTins` (
  `GiaiDoanDuAn_id` bigint(20) NOT NULL,
  `tepTins_id` bigint(20) NOT NULL,
  KEY `FKk5fordnhpm4xe8hfcy0hvn05b` (`GiaiDoanDuAn_id`),
  KEY `FK3jjenhyoyewiqnn9v32nmax6d` (`tepTins_id`),
  CONSTRAINT `FK3jjenhyoyewiqnn9v32nmax6d` FOREIGN KEY (`tepTins_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.giaidoanduan_tepTins: ~0 rows (approximately)
DELETE FROM `giaidoanduan_tepTins`;
/*!40000 ALTER TABLE `giaidoanduan_tepTins` DISABLE KEYS */;
/*!40000 ALTER TABLE `giaidoanduan_tepTins` ENABLE KEYS */;

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
  `loaiCongViec` varchar(255) DEFAULT NULL,
  `ngayGiao` datetime DEFAULT NULL,
  `ngayHoanThanh` datetime DEFAULT NULL,
  `tenCongViec` varchar(255) DEFAULT NULL,
  `trangThaiGiaoViec` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `duAn_id` bigint(20) DEFAULT NULL,
  `nguoiDuocGiao_id` bigint(20) DEFAULT NULL,
  `nguoiGiaoViec_id` bigint(20) DEFAULT NULL,
  `taiLieu_id` bigint(20) DEFAULT NULL,
  `taiLieuKetQua_id` bigint(20) DEFAULT NULL,
  `noiDungCongViec` varchar(255) DEFAULT NULL,
  `doanVao_id` bigint(20) DEFAULT NULL,
  `ghiChu` longtext,
  `ketQua` longtext,
  `yKienChiDao` longtext,
  `tenNhiemVu` varchar(255) DEFAULT NULL,
  `cha_id` bigint(20) DEFAULT NULL,
  `phongBan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jkhxiuynhv7kg8lpysxgxa36` (`nguoiSua_id`),
  KEY `FKrtob3779e4wj2cn2uo88tjlku` (`nguoiTao_id`),
  KEY `FKc03v84oo8ne7yrq07j0xbk3nx` (`duAn_id`),
  KEY `FKjpb7j625yctta0ar1as62ea79` (`nguoiDuocGiao_id`),
  KEY `FK7koe49gx4fwfrtqjt5c1mnpju` (`nguoiGiaoViec_id`),
  KEY `FK8wvh4hnwxwkr081alfgiaatsp` (`taiLieu_id`),
  KEY `FKl7t2w9u86m83h4j1e0g1u0qnw` (`taiLieuKetQua_id`),
  KEY `FKkh11optenfh7r9eu6jlylbd88` (`doanVao_id`),
  KEY `FKd7jsaqo2y9lerub0g6b596qm2` (`phongBan_id`),
  KEY `FK63vdkg2eldlorjm817kt81po9` (`cha_id`),
  CONSTRAINT `FK1jkhxiuynhv7kg8lpysxgxa36` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK63vdkg2eldlorjm817kt81po9` FOREIGN KEY (`cha_id`) REFERENCES `loaicongviec` (`id`),
  CONSTRAINT `FK7koe49gx4fwfrtqjt5c1mnpju` FOREIGN KEY (`nguoiGiaoViec_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK8wvh4hnwxwkr081alfgiaatsp` FOREIGN KEY (`taiLieu_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKc03v84oo8ne7yrq07j0xbk3nx` FOREIGN KEY (`duAn_id`) REFERENCES `duan` (`id`),
  CONSTRAINT `FKd7jsaqo2y9lerub0g6b596qm2` FOREIGN KEY (`phongBan_id`) REFERENCES `phongban` (`id`),
  CONSTRAINT `FKjpb7j625yctta0ar1as62ea79` FOREIGN KEY (`nguoiDuocGiao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKkh11optenfh7r9eu6jlylbd88` FOREIGN KEY (`doanVao_id`) REFERENCES `doanvao` (`id`),
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

-- Dumping structure for table bxtdtdn.hosokhudat
DROP TABLE IF EXISTS `hosokhudat`;
CREATE TABLE IF NOT EXISTS `hosokhudat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `tenHoSoKhuDat` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `giaiDoanDuAn_id` bigint(20) DEFAULT NULL,
  `taiLieu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ke37tjnhev279psg797hlueq` (`nguoiSua_id`),
  KEY `FK5datqu80j48wxyi0c6j5wgci3` (`nguoiTao_id`),
  KEY `FKnkr9upk7lqd1cw2edtopw8mi8` (`giaiDoanDuAn_id`),
  KEY `FKs3mm718i5w2w7orswyvbons7a` (`taiLieu_id`),
  CONSTRAINT `FK3ke37tjnhev279psg797hlueq` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FK5datqu80j48wxyi0c6j5wgci3` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKnkr9upk7lqd1cw2edtopw8mi8` FOREIGN KEY (`giaiDoanDuAn_id`) REFERENCES `giaidoanduan` (`id`),
  CONSTRAINT `FKs3mm718i5w2w7orswyvbons7a` FOREIGN KEY (`taiLieu_id`) REFERENCES `teptin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.hosokhudat: ~0 rows (approximately)
DELETE FROM `hosokhudat`;
/*!40000 ALTER TABLE `hosokhudat` DISABLE KEYS */;
/*!40000 ALTER TABLE `hosokhudat` ENABLE KEYS */;

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

-- Dumping structure for table bxtdtdn.keyapi
DROP TABLE IF EXISTS `keyapi`;
CREATE TABLE IF NOT EXISTS `keyapi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `keyApi` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmap8n5qejxoidgdvdfy39mpey` (`nguoiSua_id`),
  KEY `FKrby5gt0i0vigtj2a83xdq051x` (`nguoiTao_id`),
  CONSTRAINT `FKmap8n5qejxoidgdvdfy39mpey` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKrby5gt0i0vigtj2a83xdq051x` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.keyapi: ~0 rows (approximately)
DELETE FROM `keyapi`;
/*!40000 ALTER TABLE `keyapi` DISABLE KEYS */;
INSERT INTO `keyapi` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `keyApi`, `nguoiSua_id`, `nguoiTao_id`, `ten`) VALUES
	(1, b'0', '2018-12-24 10:52:26', '2018-12-24 10:52:12', 'ap_dung', 'mHUi1VlZw2X2Y13IKW7CR8Wdd8FaNDMGAtB0GFzokwtR0otaII/mcGSNX3+7xH/L', 1, 1, 'Toàn Cầu Xanh');
/*!40000 ALTER TABLE `keyapi` ENABLE KEYS */;

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

-- Dumping structure for table bxtdtdn.lichsuvanban
DROP TABLE IF EXISTS `lichsuvanban`;
CREATE TABLE IF NOT EXISTS `lichsuvanban` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `giaiDoanXucTien` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `duAn_id` bigint(20) DEFAULT NULL,
  `nguoiNhap_id` bigint(20) DEFAULT NULL,
  `vanBan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1e1yn4rcyy4if0sc4484goxh2` (`nguoiSua_id`),
  KEY `FKg710sph8j2y6fofq5he5ymc9c` (`nguoiTao_id`),
  KEY `FKs330pjru9puqc94ptqkr5tc63` (`duAn_id`),
  KEY `FKqdg6wgfj36m0cmokmy04r51in` (`nguoiNhap_id`),
  KEY `FKehdld3es5cy3jq8fg7buq568p` (`vanBan_id`),
  CONSTRAINT `FK1e1yn4rcyy4if0sc4484goxh2` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKehdld3es5cy3jq8fg7buq568p` FOREIGN KEY (`vanBan_id`) REFERENCES `teptin` (`id`),
  CONSTRAINT `FKg710sph8j2y6fofq5he5ymc9c` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKqdg6wgfj36m0cmokmy04r51in` FOREIGN KEY (`nguoiNhap_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKs330pjru9puqc94ptqkr5tc63` FOREIGN KEY (`duAn_id`) REFERENCES `duan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.lichsuvanban: ~0 rows (approximately)
DELETE FROM `lichsuvanban`;
/*!40000 ALTER TABLE `lichsuvanban` DISABLE KEYS */;
/*!40000 ALTER TABLE `lichsuvanban` ENABLE KEYS */;

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

-- Dumping structure for table bxtdtdn.loaicongviec
DROP TABLE IF EXISTS `loaicongviec`;
CREATE TABLE IF NOT EXISTS `loaicongviec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `moTa` varchar(255) DEFAULT NULL,
  `ten` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8pgkbv8iopdbrm03dwosq78hx` (`nguoiSua_id`),
  KEY `FKbi4mfocvx2docrnvice2pheaa` (`nguoiTao_id`),
  CONSTRAINT `FK8pgkbv8iopdbrm03dwosq78hx` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKbi4mfocvx2docrnvice2pheaa` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.loaicongviec: ~3 rows (approximately)
DELETE FROM `loaicongviec`;
/*!40000 ALTER TABLE `loaicongviec` DISABLE KEYS */;
INSERT INTO `loaicongviec` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`) VALUES
	(1, b'0', '2018-12-13 14:21:46', '2018-12-13 14:21:47', 'ap_dung', NULL, 'Nhân sự làm việc', 1, 1),
	(2, b'0', '2018-12-13 14:21:46', '2018-12-13 14:21:47', 'ap_dung', NULL, 'Công tác hậu cần', 1, 1),
	(3, b'0', '2018-12-13 14:21:46', '2018-12-13 14:21:47', 'ap_dung', NULL, 'Công tác khác', 1, 1);
/*!40000 ALTER TABLE `loaicongviec` ENABLE KEYS */;

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

-- Dumping structure for table bxtdtdn.nhadautu
DROP TABLE IF EXISTS `nhadautu`;
CREATE TABLE IF NOT EXISTS `nhadautu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daXoa` bit(1) NOT NULL,
  `ngaySua` datetime DEFAULT NULL,
  `ngayTao` datetime DEFAULT NULL,
  `trangThai` varchar(255) DEFAULT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nguoiDaiDienPhapLy` varchar(255) DEFAULT NULL,
  `soDienThoai` varchar(255) DEFAULT NULL,
  `tenNhaDauTu` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5oppdlv87e8ehglwru9oimy5o` (`nguoiSua_id`),
  KEY `FKix16fcj5hepelskq55ilign66` (`nguoiTao_id`),
  CONSTRAINT `FK5oppdlv87e8ehglwru9oimy5o` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKix16fcj5hepelskq55ilign66` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.nhadautu: ~0 rows (approximately)
DELETE FROM `nhadautu`;
/*!40000 ALTER TABLE `nhadautu` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhadautu` ENABLE KEYS */;

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
  `vaiTro_id` bigint(20) DEFAULT NULL,
  `congChucSoNoiVu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKea6is3f6do1ybghqu5uo8xiap` (`nguoiSua_id`),
  KEY `FKlyyrr2uas0f50iupka9ergm8x` (`nguoiTao_id`),
  KEY `FK3qvwtuxp7rmr2kvksmb0bh34g` (`phongBan_id`),
  KEY `FKj6192ntptfiiw2ie88p77jb5n` (`vaiTro_id`),
  KEY `FK3bddmwpy7ds3jy3k0f5hn90yp` (`congChucSoNoiVu_id`),
  CONSTRAINT `FK3bddmwpy7ds3jy3k0f5hn90yp` FOREIGN KEY (`congChucSoNoiVu_id`) REFERENCES `congchucsonoivu` (`id`),
  CONSTRAINT `FK3qvwtuxp7rmr2kvksmb0bh34g` FOREIGN KEY (`phongBan_id`) REFERENCES `phongban` (`id`),
  CONSTRAINT `FKea6is3f6do1ybghqu5uo8xiap` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKj6192ntptfiiw2ie88p77jb5n` FOREIGN KEY (`vaiTro_id`) REFERENCES `vaitro` (`id`),
  CONSTRAINT `FKlyyrr2uas0f50iupka9ergm8x` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.nhanvien: ~26 rows (approximately)
DELETE FROM `nhanvien`;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `checkKichHoat`, `chucVu`, `diaChi`, `email`, `hoVaTen`, `matKhau`, `ngaySinh`, `pathAvatar`, `salkey`, `selectedDV`, `soDienThoai`, `nguoiSua_id`, `nguoiTao_id`, `phongBan_id`, `vaiTro_id`, `congChucSoNoiVu_id`) VALUES
	(1, b'0', '2018-12-25 11:42:13', '2018-11-05 15:34:08', 'ap_dung', b'0', '', '', 'gopy@danang.gov.vn', 'Super Admin', 'RvxJVVj3R+X7PNPj9AcCJuvTMlGtPSI8', NULL, '', 'iZz36mQl2qfG4ZZEnjUSdTzdTWjn4wQo', b'0', '', 1, NULL, NULL, 1, NULL),
	(2, b'0', '2018-12-25 09:56:23', '2018-11-05 15:59:11', 'ap_dung', b'0', '', '', 'trangvtk@danang.gov.vn', 'Võ Thị Kiều Trang', 'laazf97nxzQtNu3Js3q1D5p1UySHgNNi', NULL, '', 'urCf2zlLSw806FGDDHSt5TSwPSJryvE+', b'0', '', 1, 1, 1, 4, 22),
	(3, b'0', '2018-12-25 10:31:38', '2018-11-05 15:59:29', 'ap_dung', b'0', '', '', 'maintt2@danang.gov.vn', 'Nguyễn Thị Tuyết Mai', 'A9UePq+rNdWznt2kqIkgeN8hFfEgfTiW', NULL, '', 'fRgJ//2MonOykjh0RKrSjk0of3F6AO9E', b'0', '', 1, 1, 1, 4, 23),
	(4, b'0', '2018-12-25 10:27:45', '2018-11-05 15:59:45', 'ap_dung', b'0', '', '', 'hieutt3@danang.gov.vn', 'Trần Trung Hiếu', 'U7B1QJ4mQPHD/yy6XE7rwFVEvn5Qq87N', NULL, '', 'ZrehvZoll2UZnIRmG6bjNHphP9ZdYMTn', b'0', '', 1, 1, 1, 2, 35),
	(5, b'0', '2018-12-25 10:28:16', '2018-11-05 16:00:03', 'ap_dung', b'0', '', '', 'diepnt@danang.gov.vn', 'Nguyễn Thị Điệp', 'mTByyoF0OaJ6W937c3kiBXO4RL9Vzy1s', NULL, '', 't1l2bAchxVu8a0+ASCApYUpinZAoOET1', b'0', '', 1, 1, 2, 2, 19),
	(6, b'0', '2018-12-25 10:31:55', '2018-11-05 16:00:22', 'ap_dung', b'0', '', '', 'nhinh2@danang.gov.vn', 'Nguyễn Hữu Nhĩ', 'zj2eKBA+hhZUSjL0kto9TWzPTL3LNjC4', NULL, '', '+Edm/m+2dfD6aWUMip6h5Hx8D+Tbi1SG', b'0', '', 1, 1, 2, 4, 20),
	(7, b'0', '2018-12-25 10:28:52', '2018-11-05 16:00:53', 'ap_dung', b'0', '', '', 'tramttt@danang.gov.vn', 'Trần Thị Thùy Trâm', '/xWfDKYK5ppTL9nxaHvHkMR5A/JXhJA6', NULL, '', 'VJvoywVC2jgjCgvYO7roOkvDtl/pZwPQ', b'0', '', 1, 1, 4, 2, 21),
	(8, b'0', '2018-12-25 10:29:26', '2018-11-05 16:01:12', 'ap_dung', b'0', '', '', 'letth2@danang.gov.vn', 'Trần Thị Hoài Lê', 'GGCEslJSPxt241k3bBoJrDqRV5jY4N71', NULL, '', 'DTOPo0OfXNeb5IJM0fxOZa1p0V6cJczr', b'0', '', 1, 1, 2, 2, 27),
	(9, b'0', '2018-12-25 10:25:17', '2018-11-05 16:01:51', 'ap_dung', b'0', '', '', 'tuonglm@danang.gov.vn', 'Lê Minh Tường', '7FxtkTr3iHy+XvvbSoG6GAV5DJy9ieXc', NULL, '', 'dEUMni2ho6g6yqAGShJFdwoV/FYRQd1A', b'0', '', 1, 1, 6, 3, 36),
	(10, b'0', '2018-12-25 10:32:47', '2018-11-05 16:02:12', 'ap_dung', b'0', '', '', 'hieutt2@danang.gov.vn', 'Trần Trung Hiếu', '1Vm9Fxiuo6tWGUyQPGKVa8jTSYocHArO', NULL, '', 'lUy2xOQeHJgu6gSIkAo4gZP1XcAjGtoA', b'0', '', 1, 1, 5, 2, 29),
	(11, b'0', '2018-12-25 10:33:04', '2018-11-05 16:02:28', 'ap_dung', b'0', '', '', 'huongvtm1@danang.gov.vn', 'Võ Thị Mai Hương', 'rYOm6Ldi0Kw0+XUM1cC1jQdKQYWMrNo6', NULL, '', 'UpNRrh7hRBCkYnd/H8TYHsx3qZ+7oPlj', b'0', '', 1, 1, 1, 4, 31),
	(12, b'0', '2018-12-25 11:41:52', '2018-12-25 10:33:24', 'ap_dung', b'0', '', '', 'haichau1@danang.gov.vn', 'Vũ Thị Hồng Hạnh', '6YQufFwbofjM+CBmtsqQ0r82lMCFhlOk', NULL, '', '2/tV4rIaCJ+t8xiop0Kz/WY1S8vANNsO', b'0', '', 1, 1, 3, 2, 32),
	(13, b'0', '2018-12-25 10:36:16', '2018-12-25 10:36:16', 'ap_dung', b'0', '', '', 'thudta@danang.gov.vn', 'Đinh Thị Anh Thư', '/gQ14R6ZcNddZiSHBTSDvf7DilImbT4u', NULL, '', 'L6SjN7MdNtBJ+Db6Vn8ku2p9iG5Wg4un', b'0', '', 1, 1, 4, 2, 33),
	(14, b'0', '2018-12-25 11:41:40', '2018-12-25 10:36:32', 'ap_dung', b'0', '', '', 'quanhaichau@danang.gov.vn', 'Lê Đức Long', 'y9E0tWnQeF2Qdti5MkNMabBeHvouxdOk', NULL, '', 'RMTjymaTwl1gyFZpDeRGEOIWhhSfQU70', b'0', '', 1, 1, 3, 2, 34),
	(15, b'0', '2018-12-25 10:37:41', '2018-12-25 10:37:41', 'ap_dung', b'0', '', '', 'anhnk@danang.gov.vn', 'Nguyễn Kỳ Anh', 'vL3N3ivi0Mn4KDALXubDxd8/SdHpZr68', NULL, '', 'RqzfICQIw4K/GNlz/RHYbHOBuboCekeB', b'0', '', 1, 1, 6, 3, 2),
	(16, b'0', '2018-12-25 11:41:25', '2018-12-25 10:38:00', 'ap_dung', b'0', '', '', 'stttt@danang.gov.vn', 'Nguyễn Hạ My', 'DZzl5wdd20yM+xw2kEC7dMv/qdVUqs3g', NULL, '', 'ao2iXqfsAhsGMKpVGe3+StvJlaV5HUaC', b'0', '', 1, 1, 3, 2, 3),
	(17, b'0', '2018-12-25 10:38:15', '2018-12-25 10:38:15', 'ap_dung', b'0', '', '', 'phuonghl@danang.gov.vn', 'Huỳnh Liên Phương', 'hhpOy0JHhvCpMYwkB5WfRdZuKffVhoqw', NULL, '', 'w0laTkmLKrHXXYfW92t1fEYkrwqv/sLg', b'0', '', 1, 1, 6, 3, 4),
	(18, b'0', '2018-12-25 10:38:30', '2018-12-25 10:38:30', 'ap_dung', b'0', '', '', 'duonglc@danang.gov.vn', 'Lê Cảnh Dương', 'J4wnNmksKFtnnDJ/STT5ey1U88I6pbaS', NULL, '', 'XUldoOv+hV9AYuAQ58PKEA4WOrkq4/IU', b'0', '', 1, 1, 6, 2, 5),
	(19, b'0', '2018-12-25 11:41:05', '2018-12-25 10:38:47', 'ap_dung', b'0', '', '', 'sgtvt@danang.gov.vn', 'Phạm Thị Bích Hiền', 'gixD4Qy4LExcYOIZ4Xm17ZAhz1leDzfa', NULL, '', '8FfzfxxUWUP7BT2VfCgFwh0n+lxAzkie', b'0', '', 1, 1, 3, 4, 7),
	(20, b'0', '2018-12-25 11:40:51', '2018-12-25 10:39:06', 'ap_dung', b'0', '', '', 'lanhn@danang.gov.vn', 'Đỗ Thị Quỳnh Trâm', '2Or1KroiTiTaXQF3K4mh6RoHwdYv3sER', NULL, '', 'JakI1M6FP7QLvrlrom13HUoeRrDxgp1V', b'0', '', 1, 1, 3, 4, 8),
	(21, b'0', '2018-12-25 11:41:16', '2018-12-25 10:39:20', 'ap_dung', b'0', '', '', 'stnmt@danang.gov.vn', 'Đinh Thùy Trang', 'JUQCsCsYAVHuhnlYiGtec/76Zdd7+7mM', NULL, '', 'wIxbeunhFIdSDNEXPgeNP1JDwv4nMN0v', b'0', '', 1, 1, 3, 2, 10),
	(22, b'0', '2018-12-25 10:39:34', '2018-12-25 10:39:34', 'ap_dung', b'0', '', '', 'ngochnt@danang.gov.vn', 'Huỳnh Nguyễn Thiên Ngọc', 'O3DtlhEITDCceN30oX5B/IL3YIhnrrBV', NULL, '', '4zcdxLEI6XZfK7GdgkRKNfYF3NPwAUa/', b'0', '', 1, 1, 4, 2, 11),
	(23, b'0', '2018-12-25 10:39:55', '2018-12-25 10:39:55', 'ap_dung', b'0', '', '', 'phuchtd2@danang.gov.vn', 'Hường Thị Diễm Phúc', 'hmdtcfVMvoXXfdMPv7LVZr51rALch45u', NULL, '', 'yQEB6cooWi7/B2HrtKGvHssW0R397CA+', b'0', '', 1, 1, 2, 4, 12),
	(24, b'0', '2018-12-25 10:40:10', '2018-12-25 10:40:10', 'ap_dung', b'0', '', '', 'nhidt@danang.gov.vn', 'Dương Thị Nhi', '0eV7sbaZ6K07IKSVlDTjzV4EV5wdU8b1', NULL, '', 'vZkGmskS7Jwhd1bE7rcVz1oep0K7pKKl', b'0', '', 1, 1, 2, 4, 15),
	(25, b'0', '2018-12-25 10:40:21', '2018-12-25 10:40:21', 'ap_dung', b'0', '', '', 'dungtt1@danang.gov.vn', 'Trần Tuấn Dũng', 'n/dQsAPSarplaYYQmP70Z0nW2gzSMvxz', NULL, '', 'WGrPxz+Zeoq3UlU7xEo3fII6OH174MvK', b'0', '', 1, 1, 2, 2, 16),
	(26, b'0', '2018-12-25 10:40:41', '2018-12-25 10:40:41', 'ap_dung', b'0', '', '', 'thunta3@danang.gov.vn', 'Nguyễn Thị Anh Thư', '1l0murN6QpMIn9Ye1UT+0nnBLLZmpxLm', NULL, '', 'RDMaEQl79xeASf9hXTiPHDUVrEatrF4X', b'0', '', 1, 1, 2, 4, 17);
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

-- Dumping data for table bxtdtdn.nhanvien_vaitro: ~25 rows (approximately)
DELETE FROM `nhanvien_vaitro`;
/*!40000 ALTER TABLE `nhanvien_vaitro` DISABLE KEYS */;
INSERT INTO `nhanvien_vaitro` (`nhanvien_id`, `vaitros_id`) VALUES
	(2, 4),
	(3, 4),
	(4, 2),
	(5, 2),
	(6, 4),
	(7, 2),
	(8, 2),
	(9, 3),
	(10, 2),
	(11, 4),
	(12, 2),
	(13, 2),
	(14, 2),
	(15, 3),
	(16, 2),
	(17, 3),
	(18, 2),
	(19, 4),
	(20, 4),
	(21, 2),
	(22, 2),
	(23, 4),
	(24, 4),
	(25, 2),
	(26, 4);
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
  `donViSoNoiVu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn1jfu4n83q1rny3eqlxx7q2j6` (`nguoiSua_id`),
  KEY `FKfobc77hmqd3hx4kgmoc2fnmyn` (`nguoiTao_id`),
  KEY `FK83r8l6d4jlq533pfe27yel4np` (`donViSoNoiVu_id`),
  CONSTRAINT `FK83r8l6d4jlq533pfe27yel4np` FOREIGN KEY (`donViSoNoiVu_id`) REFERENCES `donvisonoivu` (`id`),
  CONSTRAINT `FKfobc77hmqd3hx4kgmoc2fnmyn` FOREIGN KEY (`nguoiTao_id`) REFERENCES `nhanvien` (`id`),
  CONSTRAINT `FKn1jfu4n83q1rny3eqlxx7q2j6` FOREIGN KEY (`nguoiSua_id`) REFERENCES `nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.phongban: ~6 rows (approximately)
DELETE FROM `phongban`;
/*!40000 ALTER TABLE `phongban` DISABLE KEYS */;
INSERT INTO `phongban` (`id`, `daXoa`, `ngaySua`, `ngayTao`, `trangThai`, `moTa`, `ten`, `nguoiSua_id`, `nguoiTao_id`, `donViSoNoiVu_id`) VALUES
	(1, b'0', '2018-12-25 09:55:21', '2018-11-05 15:57:34', 'ap_dung', 'Phòng Phát triển dự án', 'Phòng Phát triển dự án', 1, 1, 1),
	(2, b'0', '2018-12-25 09:53:47', '2018-11-05 15:57:40', 'ap_dung', 'Văn phòng', 'Văn phòng', 1, 1, 2),
	(3, b'0', '2018-12-25 09:53:39', '2018-11-05 15:57:45', 'ap_dung', 'Phòng Xúc tiến đầu tư', 'Phòng Xúc tiến đầu tư', 1, 1, 4),
	(4, b'0', '2018-12-25 09:53:31', '2018-11-05 15:57:50', 'ap_dung', 'Phòng Hỗ trợ đầu tư', 'Phòng Hỗ trợ đầu tư', 1, 1, 5),
	(5, b'0', '2018-12-25 09:55:40', '2018-12-25 09:55:40', 'ap_dung', 'Phòng Tư vấn đầu tư', 'Phòng Tư vấn đầu tư', 1, 1, 6),
	(6, b'0', '2018-12-25 09:55:47', '2018-12-25 09:55:47', 'ap_dung', 'Ban Lãnh đạo', 'Ban Lãnh đạo', 1, 1, 3);
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
  `tenTaiLieu` varchar(255) DEFAULT NULL,
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
  `soDienThoai` varchar(255) DEFAULT NULL,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `doanVao_id` bigint(20) DEFAULT NULL,
  `tenQuocGia` varchar(255) DEFAULT NULL,
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
  `loaiThongBao` varchar(50) DEFAULT NULL,
  `noiDung` longtext,
  `nguoiSua_id` bigint(20) DEFAULT NULL,
  `nguoiTao_id` bigint(20) DEFAULT NULL,
  `nguoiGui_id` bigint(20) DEFAULT NULL,
  `nguoiNhan_id` bigint(20) DEFAULT NULL,
  `kieuThongBao` varchar(255) DEFAULT NULL,
  `xemChiTiet` bit(1) NOT NULL,
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
	(1, b'0', '2018-11-23 14:11:24', '2018-11-05 15:34:37', 'ap_dung', 'quantrihethong', b'0', 'VAI_TRO_LANH_DAO', 0, 'Quản trị hệ thống', 1, 1),
	(2, b'0', '2018-11-23 14:16:08', '2018-11-05 15:34:37', 'ap_dung', 'chuyenvien', b'0', 'VAI_TRO_CHUYEN_VIEN', 0, 'Chuyên viên', 1, 1),
	(3, b'0', '2018-11-23 14:11:49', '2018-11-05 15:34:37', 'ap_dung', 'lanhdao', b'0', 'VAI_TRO_LANH_DAO', 0, 'Lãnh đạo', 1, 1),
	(4, b'0', '2018-11-23 14:12:04', '2018-11-05 15:34:37', 'ap_dung', 'truongphong', b'0', 'VAI_TRO_TRUONG_PHONG', 0, 'Trưởng phòng', 1, 1);
/*!40000 ALTER TABLE `vaitro` ENABLE KEYS */;

-- Dumping structure for table bxtdtdn.vaitro_quyens
DROP TABLE IF EXISTS `vaitro_quyens`;
CREATE TABLE IF NOT EXISTS `vaitro_quyens` (
  `vaitro_id` bigint(20) NOT NULL,
  `quyens` varchar(255) DEFAULT NULL,
  KEY `FKqldf0fggg0f8sc37im018c5ti` (`vaitro_id`),
  CONSTRAINT `FKqldf0fggg0f8sc37im018c5ti` FOREIGN KEY (`vaitro_id`) REFERENCES `vaitro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table bxtdtdn.vaitro_quyens: ~119 rows (approximately)
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
	(2, 'quanlyduan:sua'),
	(2, 'vaitro:sua'),
	(2, 'quanlyduan:xoa'),
	(2, 'quanlyduan:lietke'),
	(2, 'quanlyduan:xem'),
	(3, 'vaitro:tim'),
	(3, 'quanlyduan:xem'),
	(3, 'vaitro:xem'),
	(3, 'vaitro:them'),
	(3, 'vaitro:xoa'),
	(3, 'quanlyduan:xoa'),
	(3, 'quanlyduan:sua'),
	(3, 'vaitro:sua'),
	(3, 'quanlyduan:lietke'),
	(3, 'quanlyduan:giaoviec'),
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
	(1, 'quanlygiaoviec:sua'),
	(3, 'quanlyduan:nhacnho'),
	(3, 'quanlyduan:phutrach'),
	(1, 'quanlyduan:phutrach'),
	(4, 'quanlygiaoviec:them'),
	(4, 'quanlydoanvao:xoa'),
	(4, 'quanlydoanvao:lietke'),
	(4, 'quanlydoanvao:sua'),
	(4, 'quanlygiaoviec:xem'),
	(4, 'quanlydoanvao'),
	(4, 'quanlygiaoviec:lietke'),
	(4, 'quanlygiaoviec'),
	(4, 'quanlygiaoviec:xoa'),
	(4, 'quanlydoanvao:xem'),
	(4, 'quanlyduan:phutrach'),
	(4, 'quanlydoanvao:them'),
	(4, 'quanlygiaoviec:sua'),
	(2, 'quanlygiaoviec:lietke'),
	(2, 'quanlydoanvao:xoa'),
	(2, 'quanlygiaoviec:xoa'),
	(2, 'quanlydoanvao:xem'),
	(2, 'quanlydoanvao:lietke'),
	(2, 'quanlydoanvao:sua'),
	(2, 'quanlygiaoviec:xem'),
	(2, 'quanlygiaoviec:sua'),
	(1, 'quanlydoanvao:nhacnho'),
	(3, 'quanlygiaoviec:them'),
	(3, 'quanlydoanvao:xoa'),
	(3, 'quanlydoanvao:lietke'),
	(3, 'quanlydoanvao:sua'),
	(3, 'quanlygiaoviec:xem'),
	(3, 'quanlydoanvao'),
	(3, 'quanlydoanvao:nhacnho'),
	(3, 'quanlygiaoviec:lietke'),
	(3, 'quanlygiaoviec'),
	(3, 'quanlygiaoviec:xoa'),
	(3, 'quanlydoanvao:xem'),
	(3, 'quanlydoanvao:them'),
	(3, 'quanlygiaoviec:sua'),
	(4, 'quanlydoanvao:nhacnho');
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
