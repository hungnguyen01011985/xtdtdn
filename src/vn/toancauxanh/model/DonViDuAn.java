package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="donviduan")
public class DonViDuAn extends Model<DonViDuAn>{
	private String capDonVi;
	private String donVi;
	private Date ngayNhanTraLoi;
	private TepTin congVanTraLoi = new TepTin();
	private Date ngayNhanGiaiThich;
	private TepTin congVanGiaiThich = new TepTin();
	private GiaiDoanDuAn giaiDoanDuAn;
	
	public String getCapDonVi() {
		return capDonVi;
	}

	public void setCapDonVi(String capDonVi) {
		this.capDonVi = capDonVi;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public Date getNgayNhanTraLoi() {
		return ngayNhanTraLoi;
	}

	public void setNgayNhanTraLoi(Date ngayNhanTraLoi) {
		this.ngayNhanTraLoi = ngayNhanTraLoi;
	}
	
	@ManyToOne
	public TepTin getCongVanTraLoi() {
		return congVanTraLoi;
	}

	public void setCongVanTraLoi(TepTin congVanTraLoi) {
		this.congVanTraLoi = congVanTraLoi;
	}

	public Date getNgayNhanGiaiThich() {
		return ngayNhanGiaiThich;
	}

	public void setNgayNhanGiaiThich(Date ngayNhanGiaiThich) {
		this.ngayNhanGiaiThich = ngayNhanGiaiThich;
	}
	
	@ManyToOne
	public TepTin getCongVanGiaiThich() {
		return congVanGiaiThich;
	}

	public void setCongVanGiaiThich(TepTin congVanGiaiThich) {
		this.congVanGiaiThich = congVanGiaiThich;
	}
	
	@ManyToOne
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}
	
}
