package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;

@Entity
@Table(name="lichsuvanban")
public class LichSuVanBan extends Model<LichSuVanBan>{
	private DuAn duAn;
	private GiaiDoanXucTien giaiDoanXucTien;
	private TepTin vanBan;
	private NhanVien nguoiNhap;

	@ManyToOne
	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}

	@ManyToOne
	public TepTin getVanBan() {
		return vanBan;
	}

	public void setVanBan(TepTin vanBan) {
		this.vanBan = vanBan;
	}

	@ManyToOne
	public NhanVien getNguoiNhap() {
		return nguoiNhap;
	}

	public void setNguoiNhap(NhanVien nguoiNhap) {
		this.nguoiNhap = nguoiNhap;
	}

	@Enumerated(EnumType.STRING)
	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}
	
	
	
}
