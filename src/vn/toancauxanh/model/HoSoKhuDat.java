package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hosokhudat")
public class HoSoKhuDat extends Model<HoSoKhuDat> {

	private String tenHoSoKhuDat = "";
	private TepTin taiLieu = new TepTin();
	private GiaiDoanDuAn giaiDoanDuAn;

	public String getTenHoSoKhuDat() {
		return tenHoSoKhuDat;
	}

	public void setTenHoSoKhuDat(String tenHoSoKhuDat) {
		this.tenHoSoKhuDat = tenHoSoKhuDat;
	}

	@ManyToOne
	public TepTin getTaiLieu() {
		return taiLieu;
	}

	public void setTaiLieu(TepTin taiLieu) {
		this.taiLieu = taiLieu;
	}

	@ManyToOne
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}

}
