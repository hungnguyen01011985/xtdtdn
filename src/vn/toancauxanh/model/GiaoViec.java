package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;

@Entity
@Table(name="giaoviec")
public class GiaoViec extends Model<GiaoViec>{
	private DuAn duAn;
	private String tenCongViec;
	private NhanVien nguoiGiaoViec = new NhanVien();
	private NhanVien nguoiDuocGiao = new NhanVien();
	private Date ngayGiao;
	private Date hanThucHien;
	private GiaiDoanXucTien giaiDoanXucTien;
	private TrangThaiGiaoViec trangThaiGiaoViec = TrangThaiGiaoViec.CHUA_LAM;

	@ManyToOne
	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}
	
	@ManyToOne
	public NhanVien getNguoiGiaoViec() {
		return nguoiGiaoViec;
	}

	public void setNguoiGiaoViec(NhanVien nguoiGiaoViec) {
		this.nguoiGiaoViec = nguoiGiaoViec;
	}
	
	@ManyToOne
	public NhanVien getNguoiDuocGiao() {
		return nguoiDuocGiao;
	}

	public void setNguoiDuocGiao(NhanVien nguoiDuocGiao) {
		this.nguoiDuocGiao = nguoiDuocGiao;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public Date getHanThucHien() {
		return hanThucHien;
	}

	public void setHanThucHien(Date hanThucHien) {
		this.hanThucHien = hanThucHien;
	}
	
	@Enumerated(EnumType.STRING)
	public TrangThaiGiaoViec getTrangThaiGiaoViec() {
		return trangThaiGiaoViec;
	}

	public void setTrangThaiGiaoViec(TrangThaiGiaoViec trangThaiGiaoViec) {
		this.trangThaiGiaoViec = trangThaiGiaoViec;
	}
	
	@Enumerated(EnumType.STRING)
	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}
	
	
}
