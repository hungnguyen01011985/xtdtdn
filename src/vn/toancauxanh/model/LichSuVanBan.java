package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lichsuvanban")
public class LichSuVanBan extends Model<LichSuVanBan>{
	private DuAn duAn;
	private GiaiDoanDuAn giaiDoanDuAn;
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
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
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
	
	
}
