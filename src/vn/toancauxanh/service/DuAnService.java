package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.util.resource.Labels;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDuAn;

public class DuAnService extends BasicService<DuAn> {
	public DuAn getDuAnById(String id) {
		if (id != null) {
			return find(DuAn.class).where(QDuAn.duAn.id.eq(Long.parseLong(id))).fetchOne();
		}
		return new DuAn();
	}

	public JPAQuery<DuAn> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String giaiDoanXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien", "");
		JPAQuery<DuAn> q = find(DuAn.class).orderBy(QDuAn.duAn.ngaySua.desc());
		if (param != null && !param.isEmpty()) {
			String tuKhoa = "%" + param + "%";
			q.where(QDuAn.duAn.tenDuAn.like(tuKhoa));
		}
		return q;
	}

	public List<GiaiDoanXucTien> getListGiaiDoanXucTienAndNull() {
		List<GiaiDoanXucTien> list = new ArrayList<>();
		list.add(null);
		list.add(GiaiDoanXucTien.GIAI_DOAN_MOT);
		list.add(GiaiDoanXucTien.GIAI_DOAN_HAI);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BA);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BON);
		return list;
	}
}
