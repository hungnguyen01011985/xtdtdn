package vn.toancauxanh.rest.model;

import java.util.Date;

public class DuAnModel {
	private Long id;
	private Long idNguoiPhuTrach;
	private String tenDuAn = "";
	private String quyMoDuAn = "";
	private String mucTieuDuAn = "";
	private String tenLinhVucDuAn = "";
	private String mucDoUuTien = "";
	private String khaNangDauTu = "";
	private String hoTenNguoiPhuTrach = "";
	private String giaiDoanXucTien = "";
	private Date ngayBatDauXucTien;
	private Double tongVonDauTu;
	private Double dienTichSuDungDat;
	private GiaiDoanMotModel giaiDoanMot;
	private GiaiDoanHaiModel giaiDoanHai;
	private GiaiDoanBaModel giaiDoanBa;
	private GiaiDoanBonModel giaiDoanBon;
	private GiaiDoanNamModel giaiDoanNam;

	public String getTenDuAn() {
		return tenDuAn;
	}

	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}

	public String getQuyMoDuAn() {
		return quyMoDuAn;
	}

	public void setQuyMoDuAn(String quyMoDuAn) {
		this.quyMoDuAn = quyMoDuAn;
	}

	public String getMucTieuDuAn() {
		return mucTieuDuAn;
	}

	public void setMucTieuDuAn(String mucTieuDuAn) {
		this.mucTieuDuAn = mucTieuDuAn;
	}

	public Double getTongVonDauTu() {
		return tongVonDauTu;
	}

	public void setTongVonDauTu(Double tongVonDauTu) {
		this.tongVonDauTu = tongVonDauTu;
	}

	public Double getDienTichSuDungDat() {
		return dienTichSuDungDat;
	}

	public void setDienTichSuDungDat(Double dienTichSuDungDat) {
		this.dienTichSuDungDat = dienTichSuDungDat;
	}

	public String getTenLinhVucDuAn() {
		return tenLinhVucDuAn;
	}

	public void setTenLinhVucDuAn(String tenLinhVucDuAn) {
		this.tenLinhVucDuAn = tenLinhVucDuAn;
	}

	public String getMucDoUuTien() {
		return mucDoUuTien;
	}

	public void setMucDoUuTien(String mucDoUuTien) {
		this.mucDoUuTien = mucDoUuTien;
	}

	public String getKhaNangDauTu() {
		return khaNangDauTu;
	}

	public void setKhaNangDauTu(String khaNangDauTu) {
		this.khaNangDauTu = khaNangDauTu;
	}

	public String getHoTenNguoiPhuTrach() {
		return hoTenNguoiPhuTrach;
	}

	public void setHoTenNguoiPhuTrach(String hoTenNguoiPhuTrach) {
		this.hoTenNguoiPhuTrach = hoTenNguoiPhuTrach;
	}

	public String getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(String giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

	public Date getNgayBatDauXucTien() {
		return ngayBatDauXucTien;
	}

	public void setNgayBatDauXucTien(Date ngayBatDauXucTien) {
		this.ngayBatDauXucTien = ngayBatDauXucTien;
	}

	public GiaiDoanMotModel getGiaiDoanMot() {
		return giaiDoanMot;
	}

	public void setGiaiDoanMot(GiaiDoanMotModel giaiDoanMot) {
		this.giaiDoanMot = giaiDoanMot;
	}

	public GiaiDoanHaiModel getGiaiDoanHai() {
		return giaiDoanHai;
	}

	public void setGiaiDoanHai(GiaiDoanHaiModel giaiDoanHai) {
		this.giaiDoanHai = giaiDoanHai;
	}

	public GiaiDoanBaModel getGiaiDoanBa() {
		return giaiDoanBa;
	}

	public void setGiaiDoanBa(GiaiDoanBaModel giaiDoanBa) {
		this.giaiDoanBa = giaiDoanBa;
	}

	public GiaiDoanBonModel getGiaiDoanBon() {
		return giaiDoanBon;
	}

	public void setGiaiDoanBon(GiaiDoanBonModel giaiDoanBon) {
		this.giaiDoanBon = giaiDoanBon;
	}

	public GiaiDoanNamModel getGiaiDoanNam() {
		return giaiDoanNam;
	}

	public void setGiaiDoanNam(GiaiDoanNamModel giaiDoanNam) {
		this.giaiDoanNam = giaiDoanNam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdNguoiPhuTrach() {
		return idNguoiPhuTrach;
	}

	public void setIdNguoiPhuTrach(Long idNguoiPhuTrach) {
		this.idNguoiPhuTrach = idNguoiPhuTrach;
	}

}
