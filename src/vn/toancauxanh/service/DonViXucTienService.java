package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DonViDuAn;
import vn.toancauxanh.model.DonViXucTien;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDonViXucTien;

public class DonViXucTienService extends BasicService<DonViXucTien> {

	@SuppressWarnings("unchecked")
	public JPAQuery<DonViXucTien> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		// Lấy list id đơn vị xúc tiến
		List<Long> giaiDoan = (List<Long>) MapUtils.getObject(argDeco(), "listId");
		JPAQuery<DonViXucTien> q = find(DonViXucTien.class);
		// Loại bỏ những đơn vị xúc tiến đã được chọn trước đó
		if (giaiDoan != null && !giaiDoan.isEmpty()) {
			q.where(QDonViXucTien.donViXucTien.id.notIn(giaiDoan));
		}
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QDonViXucTien.donViXucTien.ten.like(tuKhoa));
		}
		
		q.orderBy(QDonViXucTien.donViXucTien.capDonVi.ten.asc()).orderBy(QDonViXucTien.donViXucTien.ngayTao.desc());
		return q;
	}
	
	private Set<DonViXucTien> selectedItems = new HashSet<DonViXucTien>();

	public Set<DonViXucTien> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(Set<DonViXucTien> selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	private Set<DonViXucTien> list = new HashSet<DonViXucTien>();

	public Set<DonViXucTien> getList() {
		return list;
	}

	public void setList(Set<DonViXucTien> list) {
		this.list = list;
	}
	
	public void init(List<DonViDuAn> donViDuAn) {
		if (donViDuAn != null && !donViDuAn.isEmpty()) {
			donViDuAn.forEach(item -> list.add(item.getDonVi()));
		}
	}
	
	public List<DonViXucTien> getListDonViXucTien() {
		List<DonViXucTien> list = new ArrayList<DonViXucTien>();
		find(DonViXucTien.class).fetch().forEach(item -> {
			if (!this.list.contains(item)) {
				list.add(item);
			}
		});
		return list;
	}
	
	@Command
	public void addDonViXucTien(@BindingParam("obj") DuAn duAn, @BindingParam("wdn") Window wdn) {
		wdn.detach();
		//Mỗi đơn vị xúc tiến thì tạo ra 1 đơn vị dự án
		selectedItems.forEach(item -> {
			DonViDuAn donViDuAn = new DonViDuAn();
			donViDuAn.setDonVi(item);
			duAn.getGiaiDoanDuAn().getDonViDuAn().add(0, donViDuAn);
			List<String> list = new ArrayList<String>();
			list.add("");
			list.add("");
			duAn.getListMessageDonViDuAn().add(0, list);
		});
		BindUtils.postNotifyChange(null, null, duAn.getGiaiDoanDuAn(), "*");
	}
	
}
