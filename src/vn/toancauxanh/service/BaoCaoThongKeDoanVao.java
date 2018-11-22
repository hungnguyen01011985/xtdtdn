package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class BaoCaoThongKeDoanVao extends BasicService<DoanVao>{
	public JPAQuery<DoanVao> getTargetQuery() {
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QDoanVao.doanVao.ngaySua.desc());
		return q;
	}
}
