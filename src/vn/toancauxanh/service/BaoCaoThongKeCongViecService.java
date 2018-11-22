package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QGiaoViec;

public class BaoCaoThongKeCongViecService extends BasicService<GiaoViec>{
	public JPAQuery<GiaoViec> getTargetQuery() {
		String tienDoXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien");
		JPAQuery<GiaoViec> q = find(GiaoViec.class);
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		return q;
	}
}
