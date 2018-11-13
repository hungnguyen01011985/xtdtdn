package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hosokhudat")
public class HoSoKhuDat extends Model<HoSoKhuDat> {

	private String tenHoSoKhuDat = "";
	private TepTin quyetDinhDauGiaQSDD = new TepTin();
	private GiaiDoanDuAn giaiDoanDuAn;

	public String getTenHoSoKhuDat() {
		return tenHoSoKhuDat;
	}

	public void setTenHoSoKhuDat(String tenHoSoKhuDat) {
		this.tenHoSoKhuDat = tenHoSoKhuDat;
	}

	@ManyToOne
	public TepTin getQuyetDinhDauGiaQSDD() {
		return quyetDinhDauGiaQSDD;
	}

	public void setQuyetDinhDauGiaQSDD(TepTin quyetDinhDauGiaQSDD) {
		this.quyetDinhDauGiaQSDD = quyetDinhDauGiaQSDD;
	}

	@ManyToOne
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}

}
