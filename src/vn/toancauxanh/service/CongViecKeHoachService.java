package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.CongViecKeHoach;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QCongViecKeHoach;
import vn.toancauxanh.model.QGiaoViec;

public class CongViecKeHoachService extends BasicService<CongViecKeHoach> {

	public JPAQuery<CongViecKeHoach> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<CongViecKeHoach> q = find(CongViecKeHoach.class);
		if (param !=null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%" ;
			q.where(QCongViecKeHoach.congViecKeHoach.ten.like(tuKhoa));
		}
		q.orderBy(QCongViecKeHoach.congViecKeHoach.ngayTao.desc());
		return q;
	}

	public List<CongViecKeHoach> getListCongViecKeHoach() {
		JPAQuery<CongViecKeHoach> q = find(CongViecKeHoach.class);
		if (q.fetchCount() > 0) {
			return q.fetch();
		}
		return new ArrayList<>();
	}

	public List<GiaoViec> getListCongViecKhoiTao() {
		List<GiaoViec> list = new ArrayList<>();
		List<CongViecKeHoach> listCongViecKeHoach = new ArrayList<>();
		listCongViecKeHoach.addAll(getListCongViecKeHoach());
		if (!listCongViecKeHoach.isEmpty()) {
			listCongViecKeHoach.forEach(item -> {
				GiaoViec obj = new GiaoViec();
				obj.setTenCongViec(item.getTen());
				obj.setTrangThaiGiaoViec(null);
				obj.setHanThucHien(null);
				list.add(obj);
			});
		}
		return list;
	}

	public List<GiaoViec> getListGiaoViecTheoDoanVao(Long idDoanVao) {
		List<GiaoViec> list = new ArrayList<>();
		if (idDoanVao != null && idDoanVao > 0) {
			JPAQuery<GiaoViec> q = find(GiaoViec.class).where(QGiaoViec.giaoViec.doanVao.id.eq(idDoanVao))
					.orderBy(QGiaoViec.giaoViec.cha.ten.asc()).orderBy(QGiaoViec.giaoViec.ngayTao.desc());
			q.setHint("org.hibernate.cacheable", false);
			if (q != null && q.fetchCount() > 0) {
				return q.fetch();
			}
		}
		return list;
	}
}
