package vn.toancauxanh.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "congchucsonoivu")
public class CongChucSoNoiVu extends Model<CongChucSoNoiVu> {

	private String idHeThong;
	private String hoVaTen;
	private String hoTen;
	private String maSo;
	private String gioiTinh;
	private Date ngaySinh;
	private String donViId;
	private String tenDonVi;
	private String tenDonViSearch;
	private String phongId;
	private String tenPhong;
	private String tenPhongSearch;
	private String chucVuId;
	private String chucVuTuongDuong;
	private String tenChucVu;
	private String email;
	private String diDong;
	private String dienThoaiLamViec;
	private String soCmnd;
	private Date ngayCapCmnd;
	private String noiCapCmnd;
	private String maNgach;
	private String tenNgach;
	private String bacLuong;
	private String heSoLuong;
	private String vuotKhung;
	private String trangThaiId;
	private String tenTrangThai;
	private String hoSo_ngayHieuChinh;
	private String emailTinhThanh;
	private String donVi_maSo;
	private String phong_maSo;
	private String fileAnhThe;
	private String fileCmnd;
	private String search;
	private boolean updated;

	public CongChucSoNoiVu(Node node) throws DOMException, ParseException {
		super();
		if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			List<Node> temp = new ArrayList<>();
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
					temp.add(node.getChildNodes().item(i));
				}
			}
			if (!temp.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				setIdHeThong(temp.get(0).getTextContent());
				setHoVaTen(temp.get(1).getTextContent());
				setHoTen(temp.get(2).getTextContent());
				setMaSo(temp.get(3).getTextContent());
				setGioiTinh(temp.get(4).getTextContent());
				if (!temp.get(5).getTextContent().isEmpty()) {
					setNgaySinh(sdf.parse(temp.get(5).getTextContent()));
				}
				setDonViId(temp.get(6).getTextContent());
				setTenDonVi(temp.get(7).getTextContent());
				setPhongId(temp.get(8).getTextContent());
				setTenPhong(temp.get(9).getTextContent());
				setChucVuId(temp.get(10).getTextContent());
				setChucVuTuongDuong(temp.get(11).getTextContent());
				setTenChucVu(temp.get(12).getTextContent());
				setEmail(temp.get(13).getTextContent());
				setDiDong(temp.get(14).getTextContent());
				setDienThoaiLamViec(temp.get(15).getTextContent());
				setSoCmnd(temp.get(16).getTextContent());
				if (!temp.get(17).getTextContent().isEmpty()) {
					setNgayCapCmnd(sdf.parse(temp.get(17).getTextContent()));
				}
				setNoiCapCmnd(temp.get(18).getTextContent());
				setMaNgach(temp.get(19).getTextContent());
				setTenNgach(temp.get(20).getTextContent());
				setBacLuong(temp.get(21).getTextContent());
				setHeSoLuong(temp.get(22).getTextContent());
				setVuotKhung(temp.get(23).getTextContent());
				setTrangThaiId(temp.get(24).getTextContent());
				setTenTrangThai(temp.get(25).getTextContent());
				setHoSo_ngayHieuChinh(temp.get(26).getTextContent());
				setEmailTinhThanh(temp.get(27).getTextContent());
				setDonVi_maSo(temp.get(28).getTextContent());
				setPhong_maSo(temp.get(29).getTextContent());
				setFileAnhThe(temp.get(30).getTextContent());
				setFileCmnd(temp.get(31).getTextContent());
				setTenDonViSearch(temp.get(7).getTextContent());
				setTenPhongSearch(temp.get(9).getTextContent());
				setSearch(getHoVaTen() + " " + getSoCmnd() + " " + getEmail());
			}

		}
	}

	@Transient
	public Long getCongChucSoNoiVuId() {
		return getId();
	}

	public CongChucSoNoiVu() {
	}

	public String getIdHeThong() {
		return idHeThong;
	}

	public void setIdHeThong(String idHeThong) {
		this.idHeThong = idHeThong;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getMaSo() {
		return maSo;
	}

	public void setMaSo(String maSo) {
		this.maSo = maSo;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDonViId() {
		return donViId;
	}

	public void setDonViId(String donViId) {
		this.donViId = donViId;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public String getPhongId() {
		return phongId;
	}

	public void setPhongId(String phongId) {
		this.phongId = phongId;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getChucVuId() {
		return chucVuId;
	}

	public void setChucVuId(String chucVuId) {
		this.chucVuId = chucVuId;
	}

	public String getChucVuTuongDuong() {
		return chucVuTuongDuong;
	}

	public void setChucVuTuongDuong(String chucVuTuongDuong) {
		this.chucVuTuongDuong = chucVuTuongDuong;
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiDong() {
		return diDong;
	}

	public void setDiDong(String diDong) {
		this.diDong = diDong;
	}

	public String getDienThoaiLamViec() {
		return dienThoaiLamViec;
	}

	public void setDienThoaiLamViec(String dienThoaiLamViec) {
		this.dienThoaiLamViec = dienThoaiLamViec;
	}

	public String getSoCmnd() {
		return soCmnd;
	}

	public void setSoCmnd(String soCmnd) {
		this.soCmnd = soCmnd;
	}

	public Date getNgayCapCmnd() {
		return ngayCapCmnd;
	}

	public void setNgayCapCmnd(Date ngayCapCmnd) {
		this.ngayCapCmnd = ngayCapCmnd;
	}

	public String getNoiCapCmnd() {
		return noiCapCmnd;
	}

	public void setNoiCapCmnd(String noiCapCmnd) {
		this.noiCapCmnd = noiCapCmnd;
	}

	public String getMaNgach() {
		return maNgach;
	}

	public void setMaNgach(String maNgach) {
		this.maNgach = maNgach;
	}

	public String getTenNgach() {
		return tenNgach;
	}

	public void setTenNgach(String tenNgach) {
		this.tenNgach = tenNgach;
	}

	public String getBacLuong() {
		return bacLuong;
	}

	public void setBacLuong(String bacLuong) {
		this.bacLuong = bacLuong;
	}

	public String getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(String heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public String getVuotKhung() {
		return vuotKhung;
	}

	public void setVuotKhung(String vuotKhung) {
		this.vuotKhung = vuotKhung;
	}

	public String getTrangThaiId() {
		return trangThaiId;
	}

	public void setTrangThaiId(String trangThaiId) {
		this.trangThaiId = trangThaiId;
	}

	public String getTenTrangThai() {
		return tenTrangThai;
	}

	public void setTenTrangThai(String tenTrangThai) {
		this.tenTrangThai = tenTrangThai;
	}

	public String getHoSo_ngayHieuChinh() {
		return hoSo_ngayHieuChinh;
	}

	public void setHoSo_ngayHieuChinh(String hoSo_ngayHieuChinh) {
		this.hoSo_ngayHieuChinh = hoSo_ngayHieuChinh;
	}

	public String getEmailTinhThanh() {
		return emailTinhThanh;
	}

	public void setEmailTinhThanh(String emailTinhThanh) {
		this.emailTinhThanh = emailTinhThanh;
	}

	public String getDonVi_maSo() {
		return donVi_maSo;
	}

	public void setDonVi_maSo(String donVi_maSo) {
		this.donVi_maSo = donVi_maSo;
	}

	public String getPhong_maSo() {
		return phong_maSo;
	}

	public void setPhong_maSo(String phong_maSo) {
		this.phong_maSo = phong_maSo;
	}

	public String getFileAnhThe() {
		return fileAnhThe;
	}

	public void setFileAnhThe(String fileAnhThe) {
		this.fileAnhThe = fileAnhThe;
	}

	public String getFileCmnd() {
		return fileCmnd;
	}

	public void setFileCmnd(String fileCmnd) {
		this.fileCmnd = fileCmnd;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@JsonIgnore
	public String getTenDonViSearch() {
		return tenDonViSearch;
	}

	public void setTenDonViSearch(String tenDonViSearch) {
		this.tenDonViSearch = tenDonViSearch;
	}

	@JsonIgnore
	public String getTenPhongSearch() {
		return tenPhongSearch;
	}

	public void setTenPhongSearch(String tenPhongSearch) {
		this.tenPhongSearch = tenPhongSearch;
	}

	@JsonIgnore
	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	@Command
	public void selectCongChucSoNoiVu(@BindingParam("vm") NhanVien nhanVien,
			@BindingParam("item") CongChucSoNoiVu congChucSoNoiVu,
			@BindingParam("wdn") Window wdn) {
		nhanVien.setHoVaTen(congChucSoNoiVu.getHoTen());
		nhanVien.setEmail(congChucSoNoiVu.getEmail());
//		if (congChucSoNoiVu.getPhongId() != null && !"".equals(congChucSoNoiVu.getPhongId())) {
//			PhongBan phongBan = find(PhongBan.class)
//					.where(QPhongBan.phongBan.id.eq(Long.valueOf(congChucSoNoiVu.getPhongId().trim().toString()))).fetchFirst();
//			if (phongBan != null) {
//				nhanVien.setPhongBan(phongBan);
//			}
//		}
		
		BindUtils.postNotifyChange(null, null, nhanVien, "hoVaTen");
		BindUtils.postNotifyChange(null, null, nhanVien, "email");
//		BindUtils.postNotifyChange(null, null, nhanVien, "phongBan");
		wdn.detach();
	}

}
