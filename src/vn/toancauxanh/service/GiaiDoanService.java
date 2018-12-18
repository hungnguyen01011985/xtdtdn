package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class GiaiDoanService extends BasicService<GiaiDoanDuAn>{
	public GiaiDoanDuAn getGiaiDoanById(Long idDuAn, GiaiDoanXucTien giaiDoanXucTien) {
		if (idDuAn != null) {
			JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
					.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(idDuAn))
					.where(QGiaiDoanDuAn.giaiDoanDuAn.giaiDoanXucTien.eq(giaiDoanXucTien));
			q.setHint("org.hibernate.cacheable", false);
			if(q.fetchCount() > 0) {
				return q.fetchFirst();
			}
			
		}
		return new GiaiDoanDuAn();
	}
	
	public List<GiaiDoanDuAn> getListGiaiDoanDuAnById(Long idDuAn) {
		List<GiaiDoanDuAn> list = new ArrayList<>();
		if (idDuAn != null) {
			JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(idDuAn));
			q.setHint("org.hibernate.cacheable", false);
			if (q.fetchCount() > 0) {
				list.addAll(q.fetch());
				return list;
			}
		}
		return list;
	}
}
