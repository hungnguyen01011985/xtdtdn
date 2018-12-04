package vn.toancauxanh.rest.model;

import java.util.Date;

public class CongViecModel {
	private Long id;
	private String tenCongViec = "";
	private String yKienChiDao = "";
	private String ketQua = "";
	private String ghiChu = "";
	private String nguoiGiaoViec = "";
	private String nguoiDuocGiao = "";
	private String giaiDoanXucTien = "";
	private String trangThaiCongViec = "";
	private String loaiCongViec = "";
	private Date ngayGiao;
	private Date hanThucHien;
	private Date ngayHoanThanh;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public String getyKienChiDao() {
		return yKienChiDao;
	}

	public String getKetQua() {
		return ketQua;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public String getNguoiGiaoViec() {
		return nguoiGiaoViec;
	}

	public String getNguoiDuocGiao() {
		return nguoiDuocGiao;
	}

	public String getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public String getTrangThaiCongViec() {
		return trangThaiCongViec;
	}

	public String getLoaiCongViec() {
		return loaiCongViec;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public Date getHanThucHien() {
		return hanThucHien;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public void setyKienChiDao(String yKienChiDao) {
		this.yKienChiDao = yKienChiDao;
	}

	public void setKetQua(String ketQua) {
		this.ketQua = ketQua;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public void setNguoiGiaoViec(String nguoiGiaoViec) {
		this.nguoiGiaoViec = nguoiGiaoViec;
	}

	public void setNguoiDuocGiao(String nguoiDuocGiao) {
		this.nguoiDuocGiao = nguoiDuocGiao;
	}

	public void setGiaiDoanXucTien(String giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

	public void setTrangThaiCongViec(String trangThaiCongViec) {
		this.trangThaiCongViec = trangThaiCongViec;
	}

	public void setLoaiCongViec(String loaiCongViec) {
		this.loaiCongViec = loaiCongViec;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public void setHanThucHien(Date hanThucHien) {
		this.hanThucHien = hanThucHien;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

}
