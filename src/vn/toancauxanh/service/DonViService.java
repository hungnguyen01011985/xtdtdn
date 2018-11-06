package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DonVi;
import vn.toancauxanh.model.QDonVi;

public class DonViService extends BasicService<DonVi> {

	public JPAQuery<DonVi> getTargetQuery(){
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<DonVi> q = find(DonVi.class);
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QDonVi.donVi.ten.like(tuKhoa));
		}
		q.orderBy(QDonVi.donVi.loaiDonVi.desc()).orderBy(QDonVi.donVi.ngayTao.desc());
		return q;
	}
}
