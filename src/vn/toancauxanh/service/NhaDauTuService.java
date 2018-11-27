package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.NhaDauTu;
import vn.toancauxanh.model.QNhaDauTu;

public class NhaDauTuService extends BasicService<NhaDauTu>{
	public JPAQuery<NhaDauTu> getTargetQuery(){
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<NhaDauTu> q = find(NhaDauTu.class);
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QNhaDauTu.nhaDauTu.tenNhaDauTu.like(tuKhoa));
		}
		q.orderBy(QNhaDauTu.nhaDauTu.ngayTao.desc());
		return q;
	}
}
