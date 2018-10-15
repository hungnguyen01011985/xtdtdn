package vn.toancauxanh.gg.model;

import java.util.ArrayList;
import java.util.List;

public class QuocGia {

	private int id;
	private String tenQuocGia = "";

	public QuocGia() {
	}

	public QuocGia(int id, String tenQuocGia) {
		this.id = id;
		this.tenQuocGia = tenQuocGia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenQuocGia() {
		return tenQuocGia;
	}

	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia = tenQuocGia;
	}

	public List<QuocGia> getBootstrap() {
		List<QuocGia> list = new ArrayList<QuocGia>();
		list.add(new QuocGia(1, "Afghanistan"));
		list.add(new QuocGia(2, "Ai Cập"));
		list.add(new QuocGia(3, "Albania"));
		list.add(new QuocGia(4, "Algérie"));
		list.add(new QuocGia(5, "Andorra"));
		list.add(new QuocGia(6, "Angola"));
		list.add(new QuocGia(7, "Vương quốc Liên hiệp Anh và Bắc Ireland"));
		list.add(new QuocGia(8, "Antigua và Barbuda"));
		list.add(new QuocGia(9, "Áo"));
		list.add(new QuocGia(10, "Ả Rập Xê Út"));
		list.add(new QuocGia(11, "Armenia"));
		list.add(new QuocGia(12, "Argentina"));
		list.add(new QuocGia(13, "Azerbaijan"));
		list.add(new QuocGia(14, "Ấn Độ"));
		list.add(new QuocGia(15, "Bahamas"));
		list.add(new QuocGia(16, "Bahrain"));
		list.add(new QuocGia(17, "Ba Lan"));
		list.add(new QuocGia(18, "Bangladesh"));
		list.add(new QuocGia(19, "Barbados"));
		list.add(new QuocGia(20, "Belarus"));
		list.add(new QuocGia(21, "Belize"));
		list.add(new QuocGia(22, "Bénin"));
		list.add(new QuocGia(23, "Bhutan"));
		list.add(new QuocGia(24, "Bỉ"));
		list.add(new QuocGia(25, "Bolivia"));
		list.add(new QuocGia(26, "Bosna và Hercegovina"));
		list.add(new QuocGia(27, "Botswana"));
		list.add(new QuocGia(28, "Bồ Đào Nha"));
		list.add(new QuocGia(29, "Bờ Biển Ngà"));
		list.add(new QuocGia(30, "Brasil"));
		list.add(new QuocGia(31, "Brunei"));
		list.add(new QuocGia(32, "Bulgaria"));
		list.add(new QuocGia(33, "Burkina Faso"));
		list.add(new QuocGia(34, "Burundi"));
		list.add(new QuocGia(35, "Cabo Verde"));
		list.add(new QuocGia(36, "UAE"));
		list.add(new QuocGia(37, "Cameroon"));
		list.add(new QuocGia(38, "Campuchia"));
		list.add(new QuocGia(39, "Canada"));
		list.add(new QuocGia(40, "Chile"));
		list.add(new QuocGia(41, "Colombia"));
		list.add(new QuocGia(42, "Comoros"));
		list.add(new QuocGia(43, "Cộng hòa Congo"));
		list.add(new QuocGia(44, "Cộng hòa Dân chủ Congo"));
		list.add(new QuocGia(45, "Costa Rica"));
		list.add(new QuocGia(46, "Croatia"));
		list.add(new QuocGia(47, "Cuba"));
		list.add(new QuocGia(48, "Djibouti"));
		list.add(new QuocGia(49, "Dominica"));
		list.add(new QuocGia(50, "Cộng hòa Dominica"));
		list.add(new QuocGia(51, "Đan Mạch"));
		list.add(new QuocGia(52, "Đông Timor"));
		list.add(new QuocGia(53, "Đức"));
		list.add(new QuocGia(54, "Ecuador"));
		list.add(new QuocGia(55, "El Salvador"));
		list.add(new QuocGia(56, "Eritrea"));
		list.add(new QuocGia(57, "Estonia"));
		list.add(new QuocGia(58, "Ethiopia"));
		list.add(new QuocGia(59, "Fiji"));
		list.add(new QuocGia(60, "Gabon"));
		list.add(new QuocGia(61, "Gambia"));
		list.add(new QuocGia(62, "Ghana"));
		list.add(new QuocGia(63, "Grenada"));
		list.add(new QuocGia(64, "Gruzia"));
		list.add(new QuocGia(65, "Guatemala"));
		list.add(new QuocGia(66, "Guiné-Bissau"));
		list.add(new QuocGia(67, "Guinea Xích Đạo"));
		list.add(new QuocGia(68, "Guinée"));
		list.add(new QuocGia(69, "Guyana"));
		list.add(new QuocGia(70, "Haiti"));
		list.add(new QuocGia(71, "Hà Lan"));
		list.add(new QuocGia(72, "Hàn Quốc"));
		list.add(new QuocGia(73, "Hoa Kỳ"));
		list.add(new QuocGia(74, "Honduras"));
		list.add(new QuocGia(75, "Hungary"));
		list.add(new QuocGia(76, "Hy Lạp"));
		list.add(new QuocGia(77, "Iceland"));
		list.add(new QuocGia(78, "Indonesia"));
		list.add(new QuocGia(79, "Iran"));
		list.add(new QuocGia(80, "Iraq"));
		list.add(new QuocGia(81, "Ireland"));
		list.add(new QuocGia(82, "Israel"));
		list.add(new QuocGia(83, "Jamaica"));
		list.add(new QuocGia(84, "Jordan"));
		list.add(new QuocGia(85, "Kazakhstan"));
		list.add(new QuocGia(86, "Kenya"));
		list.add(new QuocGia(87, "Kiribati"));
		list.add(new QuocGia(88, "Kosovo"));
		list.add(new QuocGia(89, "Kuwait"));
		list.add(new QuocGia(90, "Kyrgyzstan"));
		list.add(new QuocGia(91, "Lào"));
		list.add(new QuocGia(92, "Latvia"));
		list.add(new QuocGia(93, "Lesotho"));
		list.add(new QuocGia(94, "Liban"));
		list.add(new QuocGia(95, "Liberia"));
		list.add(new QuocGia(96, "Libya"));
		list.add(new QuocGia(97, "Liechtenstein"));
		list.add(new QuocGia(98, "Litva"));
		list.add(new QuocGia(99, "Luxembourg"));
		list.add(new QuocGia(100, "Macedonia"));
		list.add(new QuocGia(101, "Madagascar"));
		list.add(new QuocGia(102, "Malawi"));
		list.add(new QuocGia(103, "Malaysia"));
		list.add(new QuocGia(104, "Maldives"));
		list.add(new QuocGia(105, "Mali"));
		list.add(new QuocGia(106, "Malta"));
		list.add(new QuocGia(107, "Maroc"));
		list.add(new QuocGia(108, "Quần đảo Marshall"));
		list.add(new QuocGia(109, "Mauritanie"));
		list.add(new QuocGia(110, "Mauritius"));
		list.add(new QuocGia(111, "México"));
		list.add(new QuocGia(112, "Micronesia"));
		list.add(new QuocGia(113, "Moldova"));
		list.add(new QuocGia(114, "Monaco"));
		list.add(new QuocGia(115, "Mông Cổ"));
		list.add(new QuocGia(116, "Montenegro"));
		list.add(new QuocGia(117, "Mozambique"));
		list.add(new QuocGia(118, "Myanmar"));
		list.add(new QuocGia(119, "Namibia"));
		list.add(new QuocGia(120, "Nam Sudan"));
		list.add(new QuocGia(121, "Nam Phi"));
		list.add(new QuocGia(122, "Nauru"));
		list.add(new QuocGia(123, "Na Uy"));
		list.add(new QuocGia(124, "Nepal"));
		list.add(new QuocGia(125, "New Zealand"));
		list.add(new QuocGia(126, "Nicaragua"));
		list.add(new QuocGia(127, "Niger"));
		list.add(new QuocGia(128, "Nigeria"));
		list.add(new QuocGia(129, "Nga"));
		list.add(new QuocGia(130, "Nhật Bản"));
		list.add(new QuocGia(131, "Oman"));
		list.add(new QuocGia(132, "Pakistan"));
		list.add(new QuocGia(133, "Palau"));
		list.add(new QuocGia(134, "Palestine"));
		list.add(new QuocGia(135, "Panama"));
		list.add(new QuocGia(136, "Papua New Guinea"));
		list.add(new QuocGia(137, "Paraguay"));
		list.add(new QuocGia(138, "Peru"));
		list.add(new QuocGia(139, "Pháp"));
		list.add(new QuocGia(140, "Phần Lan"));
		list.add(new QuocGia(141, "Philippines"));
		list.add(new QuocGia(142, "Qatar"));
		list.add(new QuocGia(143, "România"));
		list.add(new QuocGia(144, "Rwanda"));
		list.add(new QuocGia(145, "Saint Kitts và Nevis"));
		list.add(new QuocGia(146, "Saint Lucia"));
		list.add(new QuocGia(147, "Saint Vincent và Grenadines"));
		list.add(new QuocGia(148, "Samoa"));
		list.add(new QuocGia(149, "San Marino"));
		list.add(new QuocGia(150, "São Tomé và Príncipe"));
		list.add(new QuocGia(151, "Séc"));
		list.add(new QuocGia(152, "Sénégal"));
		list.add(new QuocGia(153, "Serbia"));
		list.add(new QuocGia(154, "Seychelles"));
		list.add(new QuocGia(155, "Sierra Leone"));
		list.add(new QuocGia(156, "Singapore"));
		list.add(new QuocGia(157, "Síp"));
		list.add(new QuocGia(158, "Slovakia"));
		list.add(new QuocGia(159, "Slovenia"));
		list.add(new QuocGia(160, "Solomon"));
		list.add(new QuocGia(161, "Somalia"));
		list.add(new QuocGia(162, "Sri Lanka"));
		list.add(new QuocGia(163, "Sudan"));
		list.add(new QuocGia(164, "Suriname"));
		list.add(new QuocGia(165, "Swaziland"));
		list.add(new QuocGia(166, "Syria"));
		list.add(new QuocGia(167, "Tajikistan"));
		list.add(new QuocGia(168, "Tây Ban Nha"));
		list.add(new QuocGia(169, "Tchad"));
		list.add(new QuocGia(170, "Thái Lan"));
		list.add(new QuocGia(171, "Thổ Nhĩ Kỳ"));
		list.add(new QuocGia(172, "Thụy Điển"));
		list.add(new QuocGia(173, "Thụy Sĩ"));
		list.add(new QuocGia(174, "Togo"));
		list.add(new QuocGia(175, "Tonga"));
		list.add(new QuocGia(176, "Triều Tiên"));
		list.add(new QuocGia(177, "Trinidad và Tobago"));
		list.add(new QuocGia(178, "Trung Quốc"));
		list.add(new QuocGia(179, "Trung Phi"));
		list.add(new QuocGia(180, "Tunisia"));
		list.add(new QuocGia(181, "Turkmenistan"));
		list.add(new QuocGia(182, "Tuvalu"));
		list.add(new QuocGia(183, "Úc"));
		list.add(new QuocGia(184, "Uganda"));
		list.add(new QuocGia(185, "Ukraina"));
		list.add(new QuocGia(186, "Uruguay"));
		list.add(new QuocGia(187, "Uzbekistan"));
		list.add(new QuocGia(188, "Vanuatu"));
		list.add(new QuocGia(189, "Vatican"));
		list.add(new QuocGia(190, "Venezuela"));
		list.add(new QuocGia(191, "Việt Nam"));
		list.add(new QuocGia(192, "Ý"));
		list.add(new QuocGia(193, "Yemen"));
		list.add(new QuocGia(194, "Zambia"));
		list.add(new QuocGia(195, "Zimbabwe"));
		return list;
	}

	public List<QuocGia> getListQuocGiaAndNull() {
		List<QuocGia> list = new ArrayList<QuocGia>();
		list.add(null);
		list.addAll(getBootstrap());
		return list;
	}

}
