package vn.toancauxanh.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoanVaoModel {
	private Long id;
	private Long idNguoiPhuTrach;
	private String tenDoanVao = "";
	private String quocGia = "";
	private String trangThaiTiepDoan = "";
	private String tomTatNoiDungKq = "";
	private String deXuatCVPhuTrach = "";
	private String yKienChiDao = "";
	private String noiDoanDiTham = "";
	private String link = "";
	private String congVanChiDaoUB = "";
	private String nguoiPhuTrach = "";
	private int soNguoi;
	private Date thoiGianDenLamViec;
	private List<ThanhVienDoanModel> thanhVienDoans = new ArrayList<>();
	private List<CongViecModel> congViecs = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenDoanVao() {
		return tenDoanVao;
	}

	public void setTenDoanVao(String tenDoanVao) {
		this.tenDoanVao = tenDoanVao;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public String getTrangThaiTiepDoan() {
		return trangThaiTiepDoan;
	}

	public void setTrangThaiTiepDoan(String trangThaiTiepDoan) {
		this.trangThaiTiepDoan = trangThaiTiepDoan;
	}

	public String getTomTatNoiDungKq() {
		return tomTatNoiDungKq;
	}

	public void setTomTatNoiDungKq(String tomTatNoiDungKq) {
		this.tomTatNoiDungKq = tomTatNoiDungKq;
	}

	public String getDeXuatCVPhuTrach() {
		return deXuatCVPhuTrach;
	}

	public void setDeXuatCVPhuTrach(String deXuatCVPhuTrach) {
		this.deXuatCVPhuTrach = deXuatCVPhuTrach;
	}

	public String getyKienChiDao() {
		return yKienChiDao;
	}

	public void setyKienChiDao(String yKienChiDao) {
		this.yKienChiDao = yKienChiDao;
	}

	public String getNoiDoanDiTham() {
		return noiDoanDiTham;
	}

	public void setNoiDoanDiTham(String noiDoanDiTham) {
		this.noiDoanDiTham = noiDoanDiTham;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNguoiPhuTrach() {
		return nguoiPhuTrach;
	}

	public void setNguoiPhuTrach(String nguoiPhuTrach) {
		this.nguoiPhuTrach = nguoiPhuTrach;
	}

	public String getCongVanChiDaoUB() {
		return congVanChiDaoUB;
	}

	public void setCongVanChiDaoUB(String congVanChiDaoUB) {
		this.congVanChiDaoUB = congVanChiDaoUB;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public Date getThoiGianDenLamViec() {
		return thoiGianDenLamViec;
	}

	public void setThoiGianDenLamViec(Date thoiGianDenLamViec) {
		this.thoiGianDenLamViec = thoiGianDenLamViec;
	}

	public Long getIdNguoiPhuTrach() {
		return idNguoiPhuTrach;
	}

	public void setIdNguoiPhuTrach(Long idNguoiPhuTrach) {
		this.idNguoiPhuTrach = idNguoiPhuTrach;
	}

	public List<ThanhVienDoanModel> getThanhVienDoans() {
		return thanhVienDoans;
	}

	public void setThanhVienDoans(List<ThanhVienDoanModel> thanhVienDoans) {
		this.thanhVienDoans = thanhVienDoans;
	}

	public List<CongViecModel> getCongViecs() {
		return congViecs;
	}

	public void setCongViecs(List<CongViecModel> congViecs) {
		this.congViecs = congViecs;
	}
}
