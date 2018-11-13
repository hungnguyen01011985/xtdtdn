package vn.toancauxanh.service;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.HoSoKhuDat;
import vn.toancauxanh.model.QHoSoKhuDat;

public class HoSoKhuDatService extends BaseObject<HoSoKhuDat> {
	public List<HoSoKhuDat> getListHoSoKhuDatById(Long idGiaiDoan) {
		if (idGiaiDoan != null) {
			JPAQuery<HoSoKhuDat> q = find(HoSoKhuDat.class).where(QHoSoKhuDat.hoSoKhuDat.giaiDoanDuAn.id.eq(idGiaiDoan));
			if (q.fetch().size() > 0) {
				return q.fetch();
			}
		}
		return null;
	}

	public HoSoKhuDat getHoSoKhuDat() {
		return new HoSoKhuDat();
	}
}
