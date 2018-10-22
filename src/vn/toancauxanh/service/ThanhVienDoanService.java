package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QThanhVienDoan;
import vn.toancauxanh.model.ThanhVienDoan;

public class ThanhVienDoanService extends BasicService<ThanhVienDoan> {

	public JPAQuery<ThanhVienDoan> getTargetQuery() {
		JPAQuery<ThanhVienDoan> q = find(ThanhVienDoan.class).orderBy(QThanhVienDoan.thanhVienDoan.ngaySua.desc());
		return q;
	}

	public ThanhVienDoan getThanhVienDoanByDoanVao(DoanVao doanVao) {
		if (doanVao != null) {
			JPAQuery<ThanhVienDoan> q = find(ThanhVienDoan.class)
					.where(QThanhVienDoan.thanhVienDoan.doanVao.eq(doanVao));
			return q.fetchFirst();
		}
		return null;
	}
}
