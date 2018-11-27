package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DonViXucTien;
import vn.toancauxanh.model.QDonViXucTien;

public class DonViXucTienService extends BasicService<DonViXucTien> {

	public JPAQuery<DonViXucTien> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<DonViXucTien> q = find(DonViXucTien.class);
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QDonViXucTien.donViXucTien.ten.like(tuKhoa));
		}
		q.orderBy(QDonViXucTien.donViXucTien.ten.desc()).orderBy(QDonViXucTien.donViXucTien.ngayTao.desc());
		return q;
	}
}
