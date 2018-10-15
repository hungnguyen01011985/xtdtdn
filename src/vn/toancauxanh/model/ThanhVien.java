package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thanhvien")
public class ThanhVien extends Model<ThanhVien>{
	private String hoVaTen;
	private String chucDanh;
	private String quocGia;
	private String email;
	private String dienThoai;
	private PhongBan donVi;
	private DoanVao doanVao;
	public ThanhVien() {

	}
	
	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(String chucDanh) {
		this.chucDanh = chucDanh;
	}

	@ManyToOne
	public PhongBan getDonVi() {
		return donVi;
	}

	public void setDonVi(PhongBan donVi) {
		this.donVi = donVi;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	
}
