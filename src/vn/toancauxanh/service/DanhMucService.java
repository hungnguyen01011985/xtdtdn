package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DanhMuc;
import vn.toancauxanh.model.QDanhMuc;

public class DanhMucService extends BasicService<DanhMuc> {

	public JPAQuery<DanhMuc> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<DanhMuc> q = find(DanhMuc.class);

		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QDanhMuc.danhMuc.ten.like(tuKhoa));
		}

		q.orderBy(QDanhMuc.danhMuc.ngaySua.desc());
		return q;
	}

	public List<DanhMuc> getListAllDanhMuc() {
		List<DanhMuc> list = new ArrayList<DanhMuc>();
		JPAQuery<DanhMuc> q = find(DanhMuc.class).orderBy(QDanhMuc.danhMuc.ngaySua.desc());
		list.addAll(q.fetch());
		return list;
	}

	public List<DanhMuc> getListDanhMucAndNull() {
		List<DanhMuc> list = new ArrayList<DanhMuc>();
		list.add(null);
		list.addAll(getListAllDanhMuc());
		return list;
	}
}
