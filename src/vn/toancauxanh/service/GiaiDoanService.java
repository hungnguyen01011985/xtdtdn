package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class GiaiDoanService extends BasicService<GiaiDoanDuAn>{
	public GiaiDoanDuAn getGiaiDoanById(Long idDuAn) {
		if (idDuAn != null) {
			JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
					.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(idDuAn))
					.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.id.desc());
			if (q.fetch().size() > 0) {
				System.out.println("OKKKKKKKKKKKKKKKK");
				return q.fetchFirst();
			}
		}
		return new GiaiDoanDuAn();
	}
}
