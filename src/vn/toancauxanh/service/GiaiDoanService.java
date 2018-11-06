package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class GiaiDoanService extends BasicService<GiaiDoanDuAn>{
	public GiaiDoanDuAn getGiaiDoanById(Long idDuAn, GiaiDoanXucTien giaiDoanXucTien) {
		if (idDuAn != null) {
			JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
					.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(idDuAn))
					.where(QGiaiDoanDuAn.giaiDoanDuAn.trangThai.ne(core().TT_DA_XOA))
					.where(QGiaiDoanDuAn.giaiDoanDuAn.giaiDoanXucTien.eq(giaiDoanXucTien))
					.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.id.desc());
			q.setHint("org.hibernate.cacheable", false);
			if(q.fetchCount() > 0) {
				return q.fetchFirst();
			}
			
		}
		return new GiaiDoanDuAn();
	}
}
