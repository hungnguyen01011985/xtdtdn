package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donvixuctien")
public class DonViXucTien extends Model<DonViXucTien> {

	private String ten = "";
	private String moTa = "";
	private CapDonVi capDonVi;

	public DonViXucTien() {
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@ManyToOne
	public CapDonVi getCapDonVi() {
		return capDonVi;
	}

	public void setCapDonVi(CapDonVi capDonVi) {
		this.capDonVi = capDonVi;
	}

}
