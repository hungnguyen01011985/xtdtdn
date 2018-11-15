package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import vn.toancauxanh.gg.model.enums.QuocGiaEnum;

@Entity
@Table(name = "thanhviendoan")
public class ThanhVienDoan extends Model<ThanhVienDoan> {
	private String hoVaTen = "";
	private String donVi = "";
	private String chucDanh = "";
	private QuocGiaEnum quocGia;
	private String email = "";
	private String soDienThoai = "";
	private DoanVao doanVao;

	public ThanhVienDoan() {
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(String chucDanh) {
		this.chucDanh = chucDanh;
	}

	@Enumerated(EnumType.STRING)
	public QuocGiaEnum getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(QuocGiaEnum quocGia) {
		this.quocGia = quocGia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
	}
}
