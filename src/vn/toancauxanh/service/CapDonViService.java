package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.CapDonVi;
import vn.toancauxanh.model.QCapDonVi;

public class CapDonViService extends BasicService<CapDonVi> {
	public JPAQuery<CapDonVi> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<CapDonVi> q = find(CapDonVi.class);
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QCapDonVi.capDonVi.ten.like(tuKhoa));
		}
		q.orderBy(QCapDonVi.capDonVi.ten.desc()).orderBy(QCapDonVi.capDonVi.ngayTao.desc());
		return q;
	}
}
