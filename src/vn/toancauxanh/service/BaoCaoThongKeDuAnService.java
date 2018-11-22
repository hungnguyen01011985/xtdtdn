package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class BaoCaoThongKeDuAnService extends BasicService<GiaiDoanDuAn>{
	public JPAQuery<GiaiDoanDuAn> getTargetQuery() {
		String tienDoXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien");
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
				.where(QGiaiDoanDuAn.giaiDoanDuAn.daQuaGiaiDoan.ne(false));
		if (tienDoXucTien != null && !tienDoXucTien.isEmpty()) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.giaiDoanXucTien.eq(GiaiDoanXucTien.valueOf(tienDoXucTien)));
		}
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.ngaySua.desc());
		return q;
	}
}
