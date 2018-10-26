package vn.toancauxanh.service;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DonViDuAn;
import vn.toancauxanh.model.QDonViDuAn;

public class DonViDuAnService extends BaseObject<DonViDuAn> {
	public List<DonViDuAn> getListDonViById(Long idGiaiDoan) {
		if (idGiaiDoan != null) {
			JPAQuery<DonViDuAn> q = find(DonViDuAn.class).where(QDonViDuAn.donViDuAn.giaiDoanDuAn.id.eq(idGiaiDoan));
			if (q.fetch().size() > 0) {
				return q.fetch();
			}
		}
		return null;
	}

	public DonViDuAn getDonViDuAn() {
		return new DonViDuAn();
	}
}
