package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "capdonvi")
public class CapDonVi extends Model<CapDonVi> {

	private String tenCapDonVi = "";
	private String moTa = "";

	public CapDonVi() {
		super();
	}

	public String getTenCapDonVi() {
		return tenCapDonVi;
	}

	public void setTenCapDonVi(String tenCapDonVi) {
		this.tenCapDonVi = tenCapDonVi;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
}
