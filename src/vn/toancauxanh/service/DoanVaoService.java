package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.util.Clients;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.QuocGiaEnum;
import vn.toancauxanh.gg.model.enums.TrangThaiTiepDoanEnum;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;
import vn.toancauxanh.model.QDuAn;

public class DoanVaoService extends BasicService<DoanVao> {

	public JPAQuery<DoanVao> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "");
		String quocGia = MapUtils.getString(argDeco(), "quocGia", "");
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (param != null && !param.isEmpty()) {
			String tuKhoa = "%" + param + "%";
			q.where(QDoanVao.doanVao.tenDoanVao.like(tuKhoa));
		}
		if (quocGia != null && !quocGia.isEmpty()) {
			q.where(QDoanVao.doanVao.quocGia.eq(QuocGiaEnum.valueOf(quocGia)));
		}
		if (trangThai != null && !trangThai.isEmpty()) {
			q.where(QDoanVao.doanVao.trangThaiTiepDoan.eq(TrangThaiTiepDoanEnum.valueOf(trangThai)));
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

	public List<TrangThaiTiepDoanEnum> getListTrangThaiTiepDoan() {
		List<TrangThaiTiepDoanEnum> list = new ArrayList<TrangThaiTiepDoanEnum>();
		list.add(null);
		list.add(TrangThaiTiepDoanEnum.CHUA_TIEP);
		list.add(TrangThaiTiepDoanEnum.DA_TIEP);
		return list;
	}

	public DoanVao getDoanVaoById(String id) {
		if (id != null && !"".equals(id)) {
			JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.id.eq(Long.valueOf(id)));
			if (q != null) {
				return q.fetchFirst();
			}
		}
		return new DoanVao();
	}
	
	@Command
	public void reset(@BindingParam("vm") final DoanVaoService vm) {
		getArg().put("tuKhoa", null);
		getArg().put("trangThai", null);
		getArg().put("quocGia", null);
		setTuNgay(null);
		setDenNgay(null);
		BindUtils.postNotifyChange(null, null, vm, "arg");
		BindUtils.postNotifyChange(null, null, vm, "tuNgay");
		BindUtils.postNotifyChange(null, null, vm, "denNgay");
		Clients.evalJavaScript("getFocus()");
	}
}
