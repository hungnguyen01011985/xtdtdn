package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "capdonvi")
public class CapDonVi extends Model<CapDonVi> {

	private String ten = "";
	private String moTa = "";

	public CapDonVi() {
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
}
