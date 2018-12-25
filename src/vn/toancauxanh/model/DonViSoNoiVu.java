package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.w3c.dom.Node;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "donvisonoivu")
public class DonViSoNoiVu extends Model<DonViSoNoiVu> {

	private String idHeThong;
	private Long chaId;
	private String maSoToChuc;
	private String tenDonVi;
	private String tenVietTat;
	private String diaChi;
	private String email;
	private String dienThoai;
	private String fax;
	private String donViCapTrenId;
	private String tenDonViCapTren;
	private String maSoDonViCapTren;
	private String capDonViId;
	private String tenCapDonVi;
	private String tenCapDonViSearch;
	private String tenDonViCapTrenSearch;
	private String lftType;
	private String rgtType;
	private String type;
	private String search;
	private int level;
	private boolean updated;

	public DonViSoNoiVu() {
	}

	public DonViSoNoiVu(Node node) {
		super();
		if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
			List<Node> temp = new ArrayList<>();
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
					temp.add(node.getChildNodes().item(i));
				}
			}
			if (!temp.isEmpty()) {
				setIdHeThong(temp.get(0).getTextContent());
				setMaSoToChuc(temp.get(1).getTextContent());
				setTenDonVi(temp.get(2).getTextContent());
				setTenVietTat(temp.get(3).getTextContent());
				if (temp.get(4).getTextContent() != null && !temp.get(4).getTextContent().isEmpty()) {
					setChaId(Long.valueOf(temp.get(4).getTextContent()));
				}
				setDiaChi(temp.get(5).getTextContent());
				setEmail(temp.get(6).getTextContent());
				setDienThoai(temp.get(7).getTextContent());
				setFax(temp.get(8).getTextContent());
				setDonViCapTrenId(temp.get(9).getTextContent());
				setTenDonViCapTren(temp.get(10).getTextContent());
				setMaSoDonViCapTren(temp.get(11).getTextContent());
				setTenCapDonVi(temp.get(12).getTextContent());
				setCapDonViId(temp.get(13).getTextContent());
				setLftType(temp.get(14).getTextContent());
				setRgtType(temp.get(15).getTextContent());
				setType(temp.get(16).getTextContent());
				setSearch(getTenDonVi() + " " + getMaSoToChuc() + " " + getEmail());
				setTenCapDonViSearch(temp.get(12).getTextContent());
				setTenDonViCapTrenSearch(temp.get(10).getTextContent());
			}
		}
	}

	@Transient
	public Long getDonViSoNoiVuId() {
		return getId();
	}

	public String getIdHeThong() {
		return idHeThong;
	}

	public void setIdHeThong(String idHeThong) {
		this.idHeThong = idHeThong;
	}

	public Long getChaId() {
		return chaId;
	}

	public void setChaId(Long chaId) {
		this.chaId = chaId;
	}

	public String getMaSoToChuc() {
		return maSoToChuc;
	}

	public void setMaSoToChuc(String maSoToChuc) {
		this.maSoToChuc = maSoToChuc;
	}

	public String getTenDonVi() {
		return tenDonVi;
	}

	public void setTenDonVi(String tenDonVi) {
		this.tenDonVi = tenDonVi;
	}

	public String getTenVietTat() {
		return tenVietTat;
	}

	public void setTenVietTat(String tenVietTat) {
		this.tenVietTat = tenVietTat;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDonViCapTrenId() {
		return donViCapTrenId;
	}

	public void setDonViCapTrenId(String donViCapTrenId) {
		this.donViCapTrenId = donViCapTrenId;
	}

	public String getTenDonViCapTren() {
		return tenDonViCapTren;
	}

	public void setTenDonViCapTren(String tenDonViCapTren) {
		this.tenDonViCapTren = tenDonViCapTren;
	}

	public String getMaSoDonViCapTren() {
		return maSoDonViCapTren;
	}

	public void setMaSoDonViCapTren(String maSoDonViCapTren) {
		this.maSoDonViCapTren = maSoDonViCapTren;
	}

	public String getCapDonViId() {
		return capDonViId;
	}

	public void setCapDonViId(String capDonViId) {
		this.capDonViId = capDonViId;
	}

	public String getTenCapDonVi() {
		return tenCapDonVi;
	}

	public void setTenCapDonVi(String tenCapDonVi) {
		this.tenCapDonVi = tenCapDonVi;
	}

	public String getLftType() {
		return lftType;
	}

	public void setLftType(String lftType) {
		this.lftType = lftType;
	}

	public String getRgtType() {
		return rgtType;
	}

	public void setRgtType(String rgtType) {
		this.rgtType = rgtType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@JsonIgnore
	public String getTenCapDonViSearch() {
		return tenCapDonViSearch;
	}

	public void setTenCapDonViSearch(String tenCapDonViSearch) {
		this.tenCapDonViSearch = tenCapDonViSearch;
	}

	@JsonIgnore
	public String getTenDonViCapTrenSearch() {
		return tenDonViCapTrenSearch;
	}

	public void setTenDonViCapTrenSearch(String tenDonViCapTrenSearch) {
		this.tenDonViCapTrenSearch = tenDonViCapTrenSearch;
	}

	@JsonIgnore
	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	@Command
	public void selectDonViSoNoiVu(@BindingParam("vm") PhongBan phongBan,
			@BindingParam("item") DonViSoNoiVu donViSoNoiVu,
			@BindingParam("wdn") Window wdn) {
		phongBan.setTen(donViSoNoiVu.getTenDonVi());
		phongBan.setDonViSoNoiVu(donViSoNoiVu);
		BindUtils.postNotifyChange(null, null, phongBan, "ten");
		BindUtils.postNotifyChange(null, null, phongBan, "donViSoNoiVu");
		wdn.detach();
	}

}
