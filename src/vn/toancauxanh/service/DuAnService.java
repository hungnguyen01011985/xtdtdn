package vn.toancauxanh.service;

import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDuAn;

public class DuAnService extends BasicService<DuAn> {
	public DuAn getDuAnById(String id) {
		if (id != null) {
			return find(DuAn.class).where(QDuAn.duAn.id.eq(Long.parseLong(id))).fetchOne();
		}
		return new DuAn();
	}
}
