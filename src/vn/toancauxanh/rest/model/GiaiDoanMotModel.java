package vn.toancauxanh.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiaiDoanMotModel {
	
	// thong tin giai doan 1
	private Date ngayGui;
	private Date ngayNhanPhanHoi;
	private List<DonViDuAnModel> donViDuAns = new ArrayList<>();

	public Date getNgayGui() {
		return ngayGui;
	}

	public void setNgayGui(Date ngayGui) {
		this.ngayGui = ngayGui;
	}

	public Date getNgayNhanPhanHoi() {
		return ngayNhanPhanHoi;
	}

	public void setNgayNhanPhanHoi(Date ngayNhanPhanHoi) {
		this.ngayNhanPhanHoi = ngayNhanPhanHoi;
	}

	public List<DonViDuAnModel> getDonViDuAns() {
		return donViDuAns;
	}

	public void setDonViDuAns(List<DonViDuAnModel> donViDuAns) {
		this.donViDuAns = donViDuAns;
	}

}
