package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;

@Entity
@Table(name="giaidoanduan")
public class GiaiDoanDuAn extends Model<GiaiDoanDuAn>{
	private DuAn duAn;
	private GiaiDoanXucTien giaiDoanXucTien;
	//Thông tin giai đoạn 1
	private Date ngayGui;
	private Date ngayNhanPhanHoi;
	private TepTin taiLieuGD1;
	//Thông tin giai đoạn 2
	private Date ngayKhaoSat;
	private String ghiChu;
	private TepTin taiLieuGD2;
	private Date ngayPhatHanhCVGD2;
	private TepTin congVanGD2;
	//Thông tin giai đoạn 3
	private Date ngayGuiUBND;
	private Date ngayGuiSoXayDung;
	private Date ngayDuKienNhanPhanHoi;
	private TepTin taiLieuGD3;
	private Date ngayPhatHanhCV3;
	private TepTin congVanGD3;
	private List<DonViDuAn> donViDuAn = new ArrayList<DonViDuAn>();
	
	@Transient
	public List<DonViDuAn> getDonViDuAn() {
		return donViDuAn;
	}

	public void setDonViDuAn(List<DonViDuAn> donViDuAn) {
		this.donViDuAn = donViDuAn;
	}

	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

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

	@ManyToOne
	public TepTin getTaiLieuGD1() {
		return taiLieuGD1;
	}

	public void setTaiLieuGD1(TepTin taiLieuGD1) {
		this.taiLieuGD1 = taiLieuGD1;
	}

	public Date getNgayKhaoSat() {
		return ngayKhaoSat;
	}

	public void setNgayKhaoSat(Date ngayKhaoSat) {
		this.ngayKhaoSat = ngayKhaoSat;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@ManyToOne
	public TepTin getTaiLieuGD2() {
		return taiLieuGD2;
	}

	public void setTaiLieuGD2(TepTin taiLieuGD2) {
		this.taiLieuGD2 = taiLieuGD2;
	}

	public Date getNgayPhatHanhCVGD2() {
		return ngayPhatHanhCVGD2;
	}

	public void setNgayPhatHanhCVGD2(Date ngayPhatHanhCVGD2) {
		this.ngayPhatHanhCVGD2 = ngayPhatHanhCVGD2;
	}

	@ManyToOne
	public TepTin getCongVanGD2() {
		return congVanGD2;
	}

	public void setCongVanGD2(TepTin congVanGD2) {
		this.congVanGD2 = congVanGD2;
	}

	public Date getNgayGuiUBND() {
		return ngayGuiUBND;
	}

	public void setNgayGuiUBND(Date ngayGuiUBND) {
		this.ngayGuiUBND = ngayGuiUBND;
	}

	public Date getNgayGuiSoXayDung() {
		return ngayGuiSoXayDung;
	}

	public void setNgayGuiSoXayDung(Date ngayGuiSoXayDung) {
		this.ngayGuiSoXayDung = ngayGuiSoXayDung;
	}

	public Date getNgayDuKienNhanPhanHoi() {
		return ngayDuKienNhanPhanHoi;
	}

	public void setNgayDuKienNhanPhanHoi(Date ngayDuKienNhanPhanHoi) {
		this.ngayDuKienNhanPhanHoi = ngayDuKienNhanPhanHoi;
	}

	@ManyToOne
	public TepTin getTaiLieuGD3() {
		return taiLieuGD3;
	}

	public void setTaiLieuGD3(TepTin taiLieuGD3) {
		this.taiLieuGD3 = taiLieuGD3;
	}

	public Date getNgayPhatHanhCV3() {
		return ngayPhatHanhCV3;
	}

	public void setNgayPhatHanhCV3(Date ngayPhatHanhCV3) {
		this.ngayPhatHanhCV3 = ngayPhatHanhCV3;
	}

	@ManyToOne
	public TepTin getCongVanGD3() {
		return congVanGD3;
	}

	public void setCongVanGD3(TepTin congVanGD3) {
		this.congVanGD3 = congVanGD3;
	}

	@ManyToOne
	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}
	
}
