package vn.toancauxanh.rest.model;

import java.util.ArrayList;
import java.util.List;

public class GiaiDoanBonModel {
	
	// thong tin giai doan 4

	private String phuongThucChonNhaDauTu = "";
	private String tenDonViChuTri = "";
	private String tenDonViTuVan = "";
	private String tenDonViLapKeHoach = "";
	private String tenDonViThucHien = "";
	private Long idDonViChuTri;
	private Long idDonViTuVan;
	private Long idDonViLapKeHoach;
	private Long idDonViThucHien;
	private List<HoSoKhuDatModel> hoSoKhuDats = new ArrayList<>();
	private Double giaDatKhoiDiemDauGia;

	public String getPhuongThucChonNhaDauTu() {
		return phuongThucChonNhaDauTu;
	}

	public void setPhuongThucChonNhaDauTu(String phuongThucChonNhaDauTu) {
		this.phuongThucChonNhaDauTu = phuongThucChonNhaDauTu;
	}

	public String getTenDonViChuTri() {
		return tenDonViChuTri;
	}

	public void setTenDonViChuTri(String tenDonViChuTri) {
		this.tenDonViChuTri = tenDonViChuTri;
	}

	public String getTenDonViTuVan() {
		return tenDonViTuVan;
	}

	public void setTenDonViTuVan(String tenDonViTuVan) {
		this.tenDonViTuVan = tenDonViTuVan;
	}

	public String getTenDonViLapKeHoach() {
		return tenDonViLapKeHoach;
	}

	public void setTenDonViLapKeHoach(String tenDonViLapKeHoach) {
		this.tenDonViLapKeHoach = tenDonViLapKeHoach;
	}

	public String getTenDonViThucHien() {
		return tenDonViThucHien;
	}

	public void setTenDonViThucHien(String tenDonViThucHien) {
		this.tenDonViThucHien = tenDonViThucHien;
	}

	public Long getIdDonViChuTri() {
		return idDonViChuTri;
	}

	public void setIdDonViChuTri(Long idDonViChuTri) {
		this.idDonViChuTri = idDonViChuTri;
	}

	public Long getIdDonViTuVan() {
		return idDonViTuVan;
	}

	public void setIdDonViTuVan(Long idDonViTuVan) {
		this.idDonViTuVan = idDonViTuVan;
	}

	public Long getIdDonViLapKeHoach() {
		return idDonViLapKeHoach;
	}

	public void setIdDonViLapKeHoach(Long idDonViLapKeHoach) {
		this.idDonViLapKeHoach = idDonViLapKeHoach;
	}

	public Long getIdDonViThucHien() {
		return idDonViThucHien;
	}

	public void setIdDonViThucHien(Long idDonViThucHien) {
		this.idDonViThucHien = idDonViThucHien;
	}

	public List<HoSoKhuDatModel> getHoSoKhuDats() {
		return hoSoKhuDats;
	}

	public void setHoSoKhuDats(List<HoSoKhuDatModel> hoSoKhuDats) {
		this.hoSoKhuDats = hoSoKhuDats;
	}

	public Double getGiaDatKhoiDiemDauGia() {
		return giaDatKhoiDiemDauGia;
	}

	public void setGiaDatKhoiDiemDauGia(Double giaDatKhoiDiemDauGia) {
		this.giaDatKhoiDiemDauGia = giaDatKhoiDiemDauGia;
	}

}
