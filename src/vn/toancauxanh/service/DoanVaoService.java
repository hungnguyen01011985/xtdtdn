package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.TrangThaiEnum;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class DoanVaoService extends BasicService<DoanVao>{
	
	public JPAQuery<DoanVao> getTargetQuery() {
		String tukhoa = MapUtils.getString(argDeco(), "tukhoa", "").trim();
		int quocGia = MapUtils.getIntValue(argDeco(), "quocgia", 0);
		String trangthai = MapUtils.getString(argDeco(), "trangthai", "");
		Date from = (Date) argDeco().get("tungay");
		Date to = (Date) argDeco().get("denngay");
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (tukhoa != null) {
			q.where(QDoanVao.doanVao.tenDoanVao.like("%" + tukhoa + "%"));
		}
		if (quocGia > 0) {
			q.where(QDoanVao.doanVao.quocGia.eq(quocGia));
		}
		if (TrangThaiEnum.CHUA_TIEP.name().equals(trangthai.toString())) {
			q.where(QDoanVao.doanVao.trangThaiTiepDoan.eq(TrangThaiEnum.CHUA_TIEP));
		}
		if (TrangThaiEnum.DANG_TIEP.name().equals(trangthai.toString())) {
			q.where(QDoanVao.doanVao.trangThaiTiepDoan.eq(TrangThaiEnum.DANG_TIEP));
		}
		if (TrangThaiEnum.DA_TIEP.name().equals(trangthai.toString())) {
			q.where(QDoanVao.doanVao.trangThaiTiepDoan.eq(TrangThaiEnum.DA_TIEP));
		}
		if (from != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.goe(from));
		}
		if (to != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.loe(to));
		}
		q.orderBy(QDoanVao.doanVao.ngaySua.desc());
		return q;
	}
	
	public List<TrangThaiEnum> getListTrangThaiTiepDoan(){
		List<TrangThaiEnum> list = new ArrayList<TrangThaiEnum>();
		list.add(null);
		list.add(TrangThaiEnum.CHUA_TIEP);
		list.add(TrangThaiEnum.DANG_TIEP);
		list.add(TrangThaiEnum.DA_TIEP);
		return list;
	}
	
	public DoanVao getDoanVaoById(String id) {
		return new DoanVao();
	}
}
