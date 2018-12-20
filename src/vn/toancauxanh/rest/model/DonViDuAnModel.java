package vn.toancauxanh.rest.model;

import java.util.Date;

public class DonViDuAnModel {
	private String tenDonViXucTien = "";
	private Date ngayNhanTraLoi;

	public String getTenDonViXucTien() {
		return tenDonViXucTien;
	}

	public void setTenDonViXucTien(String tenDonViXucTien) {
		this.tenDonViXucTien = tenDonViXucTien;
	}

	public Date getNgayNhanTraLoi() {
		return ngayNhanTraLoi;
	}

	public void setNgayNhanTraLoi(Date ngayNhanTraLoi) {
		this.ngayNhanTraLoi = ngayNhanTraLoi;
	}

}
