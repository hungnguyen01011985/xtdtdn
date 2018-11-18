package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vn.toancauxanh.gg.model.enums.NoiDungCongViec;
import vn.toancauxanh.gg.model.enums.TrangThaiCongViec;

@Entity
@Table(name = "congviec")
public class CongViec extends Model<CongViec> {

	private String ghiChu = "";
	private boolean loaiCongViec;
	private NhanVien nguoiThucHien;
	private Date thoiGian;
	private NoiDungCongViec noiDungCongViec;
	private TrangThaiCongViec trangThaiCongViec = TrangThaiCongViec.CHUA_THUC_HIEN;
	private DoanVao doanVao;

	public CongViec() {
	}
	
	public CongViec(NoiDungCongViec noiDungCongViec, NhanVien nguoiThucHien, Date thoiGian,
			TrangThaiCongViec trangThaiCongViec, String ghiChu, boolean loaiCongViec) {
		this.noiDungCongViec = noiDungCongViec;
		this.nguoiThucHien = nguoiThucHien;
		this.thoiGian = thoiGian;
		this.trangThaiCongViec = trangThaiCongViec;
		this.ghiChu = ghiChu;
		this.loaiCongViec = loaiCongViec;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	public boolean isLoaiCongViec() {
		return loaiCongViec;
	}

	public void setLoaiCongViec(boolean loaiCongViec) {
		this.loaiCongViec = loaiCongViec;
	}

	@ManyToOne
	public NhanVien getNguoiThucHien() {
		return nguoiThucHien;
	}

	public void setNguoiThucHien(NhanVien nguoiThucHien) {
		this.nguoiThucHien = nguoiThucHien;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	@Enumerated(EnumType.STRING)
	public TrangThaiCongViec getTrangThaiCongViec() {
		return trangThaiCongViec;
	}

	public void setTrangThaiCongViec(TrangThaiCongViec trangThaiCongViec) {
		this.trangThaiCongViec = trangThaiCongViec;
	}
	
	@Enumerated(EnumType.STRING)
	public NoiDungCongViec getNoiDungCongViec() {
		return noiDungCongViec;
	}

	public void setNoiDungCongViec(NoiDungCongViec noiDungCongViec) {
		this.noiDungCongViec = noiDungCongViec;
	}

	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
	}

	@Transient
	public List<CongViec> getListCongViecKhoiTao(){
		List<CongViec> list = new ArrayList<CongViec>();
		list.add(new CongViec(NoiDungCongViec.TITLE_NHAN_SU_LAM_VIEC, null, null, null, null, false));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_NGUOI_DUOC_PHAN_CONG, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUYEN_VIEN, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.TITLE_CONG_TAC_HAU_CAN, null, null, null, null, false));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_PHONG_HOP, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_HOA_QUA, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_THIET_BI, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_TAI_LIEU, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.TITLE_CONG_TAC_KHAC, null, null, null, null, false));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_XAY_DUNG_CHUONG_TRINH, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_BAI_GIOI_THIEU, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_XAC_NHAN_LAI_THONG_TIN, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_GHI_BIEN_BAN, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		list.add(new CongViec(NoiDungCongViec.CONG_VIEC_KIEM_TRA_LAI_CONG_TAC_CHUAN_BI, nguoiThucHien, thoiGian, trangThaiCongViec, ghiChu, true));
		return list;
	}
}
