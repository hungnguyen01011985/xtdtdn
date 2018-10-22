package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.TrangThaiEnum;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class DoanVaoService extends BasicService<DoanVao> {

	public JPAQuery<DoanVao> getTargetQuery() {
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		int quocGia = MapUtils.getIntValue(argDeco(), "quocgia", 0);
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "");
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (tuKhoa != null) {
			q.where(QDoanVao.doanVao.tenDoanVao.like("%" + tuKhoa + "%"));
		}
		if (quocGia > 0) {
			q.where(QDoanVao.doanVao.quocGia.eq(quocGia));
		}
		if (trangThai != null && !trangThai.isEmpty()) {
			q.where(QDoanVao.doanVao.trangThaiTiepDoan.eq(TrangThaiEnum.valueOf(trangThai)));
		}

		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.between(getFixTuNgay(), getFixDenNgay()));
		}
		
		q.orderBy(QDoanVao.doanVao.ngaySua.desc());
		return q;
	}

	public List<TrangThaiEnum> getListTrangThaiTiepDoan() {
		List<TrangThaiEnum> list = new ArrayList<TrangThaiEnum>();
		list.add(null);
		list.add(TrangThaiEnum.CHUA_TIEP);
		list.add(TrangThaiEnum.DANG_TIEP);
		list.add(TrangThaiEnum.DA_TIEP);
		return list;
	}

	public DoanVao getDoanVaoById(String id) {
		if (id != null) {
			JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.id.eq(Long.valueOf(id)));
			return q.fetchFirst();
		}
		return new DoanVao();
	}
}
