package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.LinhVucDuAn;
import vn.toancauxanh.model.QLinhVucDuAn;

public class LinhVucDuAnService extends BasicService<LinhVucDuAn> {

	public JPAQuery<LinhVucDuAn> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<LinhVucDuAn> q = find(LinhVucDuAn.class);

		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QLinhVucDuAn.linhVucDuAn.ten.like(tuKhoa));
		}

		q.orderBy(QLinhVucDuAn.linhVucDuAn.ngaySua.desc());
		return q;
	}

	public List<LinhVucDuAn> getListAllLinhVuc() {
		List<LinhVucDuAn> list = new ArrayList<LinhVucDuAn>();
		JPAQuery<LinhVucDuAn> q = find(LinhVucDuAn.class).orderBy(QLinhVucDuAn.linhVucDuAn.ngaySua.desc());
		list.addAll(q.fetch());
		return list;
	}

	public List<LinhVucDuAn> getListLinhVucAndNull() {
		List<LinhVucDuAn> list = new ArrayList<LinhVucDuAn>();
		list.add(null);
		list.addAll(getListAllLinhVuc());
		return list;
	}
}
