package vn.toancauxanh.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MapUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.TrangThaiEnum;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class DoanVaoService extends BasicService<DoanVao> {

	public JPAQuery<DoanVao> getTargetQuery() {
		SimpleDateFormat parseFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		int quocGia = MapUtils.getIntValue(argDeco(), "quocgia", 0);
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "");
		String tuNgay = MapUtils.getString(argDeco(), "tuNgay", "");
		String denNgay = MapUtils.getString(argDeco(), "denNgay", "");
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

		if (tuNgay != null && !tuNgay.isEmpty()) {
			try {
				Date date = parseFormat.parse(tuNgay);
				setTuNgay(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.after(getFixTuNgay()));
		}
		if (denNgay != null && !denNgay.isEmpty()) {
			try {
				Date date = parseFormat.parse(denNgay);
				setDenNgay(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.before(getFixDenNgay()));
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
		return new DoanVao();
	}
}
