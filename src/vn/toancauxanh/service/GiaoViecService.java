package vn.toancauxanh.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QGiaoViec;

public class GiaoViecService extends BasicService<GiaoViec> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2132978067148535799L;
	
	public JPAQuery<GiaoViec> getTargetQueryByIdDuAn(Long idDuAn) {
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		Long id = MapUtils.getLongValue(argDeco(), "nguoiPhuTrach" , 0);
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "");
		JPAQuery<GiaoViec> q = find(GiaoViec.class)
				/*.where(QGiaoViec.giaoViec.nguoiDuocGiao.eq(core().getNhanVien()).or(QGiaoViec.giaoViec.nguoiGiaoViec.eq(core().getNhanVien())))*/
				.where(QGiaoViec.giaoViec.trangThai.ne(core().TT_DA_XOA))
				.where(QGiaoViec.giaoViec.duAn.id.eq(idDuAn));
		if (tuKhoa != null) {
			q.where(QGiaoViec.giaoViec.tenCongViec.like("%" + tuKhoa + "%"));
		}
		if (id != 0) {
			q.where(QGiaoViec.giaoViec.nguoiDuocGiao.id.eq(id));
		}
		if (!trangThai.isEmpty()) {
			q.where(QGiaoViec.giaoViec.trangThaiGiaoViec.eq(TrangThaiGiaoViec.valueOf(trangThai)));
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		return q;
	}
	
	
	public List<TrangThaiGiaoViec> getListTrangThaiGiaoViec(){
		List<TrangThaiGiaoViec> list = new ArrayList<TrangThaiGiaoViec>();
		list.add(null);
		list.add(TrangThaiGiaoViec.CHUA_LAM);
		list.add(TrangThaiGiaoViec.DANG_LAM);
		list.add(TrangThaiGiaoViec.HOAN_THANH);
		return list;
	}
}
