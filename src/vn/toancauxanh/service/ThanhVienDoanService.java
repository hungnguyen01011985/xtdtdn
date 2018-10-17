package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.QThanhVienDoan;
import vn.toancauxanh.model.ThanhVienDoan;

public class ThanhVienDoanService extends BasicService<ThanhVienDoan> {

	public JPAQuery<ThanhVienDoan> getTargetQuery() {
		JPAQuery<ThanhVienDoan> q = find(ThanhVienDoan.class).orderBy(QThanhVienDoan.thanhVienDoan.ngaySua.desc());
		return q;
	}
}
