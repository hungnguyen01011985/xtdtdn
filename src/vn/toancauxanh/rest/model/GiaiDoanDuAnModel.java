package vn.toancauxanh.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiaiDoanDuAnModel {

	// thong tin giai doan 1
	private String tenGiaiDoan = "";
	private Date ngayGui;
	private Date ngayNhanPhanHoi;
	private List<DonViDuAnModel> donViDuAns = new ArrayList<>();

	// thong tin giai doan 2
	private Date ngayKhaoSat;
	private Date ngayPhatHanhCVGD2;
	private String ghiChu = "";

	// thong tin giai doan 3

	private Date ngayGuiUBND;
	private Date ngayDuKienNhanPhanHoi;
	private Date ngayPhatHanhCV3;

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

	// thong tin giai doan 5

	private String tenCongTy = "";
	private String nguoiDaiDienPhapLy = "";
	private String diaChi = "";
	private String soDienThoai = "";
	private String email = "";

	public Date getNgayGui() {
		return ngayGui;
	}

	public void setNgayGui(Date ngayGui) {
		this.ngayGui = ngayGui;
	}

	public Date getNgayNhanPhanHoi() {
		return ngayNhanPhanHoi;
	}

	public void setNgayNhanPhanHoi(Date ngayNhanPhanHoi) {
		this.ngayNhanPhanHoi = ngayNhanPhanHoi;
	}

	public List<DonViDuAnModel> getDonViDuAns() {
		return donViDuAns;
	}

	public void setDonViDuAns(List<DonViDuAnModel> donViDuAns) {
		this.donViDuAns = donViDuAns;
	}

	public Date getNgayKhaoSat() {
		return ngayKhaoSat;
	}

	public void setNgayKhaoSat(Date ngayKhaoSat) {
		this.ngayKhaoSat = ngayKhaoSat;
	}

	public Date getNgayPhatHanhCVGD2() {
		return ngayPhatHanhCVGD2;
	}

	public void setNgayPhatHanhCVGD2(Date ngayPhatHanhCVGD2) {
		this.ngayPhatHanhCVGD2 = ngayPhatHanhCVGD2;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Date getNgayGuiUBND() {
		return ngayGuiUBND;
	}

	public void setNgayGuiUBND(Date ngayGuiUBND) {
		this.ngayGuiUBND = ngayGuiUBND;
	}

	public Date getNgayDuKienNhanPhanHoi() {
		return ngayDuKienNhanPhanHoi;
	}

	public void setNgayDuKienNhanPhanHoi(Date ngayDuKienNhanPhanHoi) {
		this.ngayDuKienNhanPhanHoi = ngayDuKienNhanPhanHoi;
	}

	public Date getNgayPhatHanhCV3() {
		return ngayPhatHanhCV3;
	}

	public void setNgayPhatHanhCV3(Date ngayPhatHanhCV3) {
		this.ngayPhatHanhCV3 = ngayPhatHanhCV3;
	}

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

	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public String getNguoiDaiDienPhapLy() {
		return nguoiDaiDienPhapLy;
	}

	public void setNguoiDaiDienPhapLy(String nguoiDaiDienPhapLy) {
		this.nguoiDaiDienPhapLy = nguoiDaiDienPhapLy;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTenGiaiDoan() {
		return tenGiaiDoan;
	}

	public void setTenGiaiDoan(String tenGiaiDoan) {
		this.tenGiaiDoan = tenGiaiDoan;
	}

}
