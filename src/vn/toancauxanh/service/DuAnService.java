package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QDuAn;
import vn.toancauxanh.model.QNhanVien;

public class DuAnService extends BasicService<DuAn> {
	public DuAn getDuAnById(String id) {
		if (id != null) {
			JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(Long.parseLong(id)));
			if (q.fetchCount() > 0) {
				return q.fetchFirst();
			} else {
				return null;
			}
		}
		return new DuAn();
	}

	public JPAQuery<DuAn> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String giaiDoanXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien", "");
		Long nguoiPhuTrach = (Long) argDeco().get("nhanVien");
		Long linhVuc = (Long) argDeco().get("linhVuc");
		JPAQuery<DuAn> q = find(DuAn.class);
		if (param != null && !param.isEmpty()) {
			String tuKhoa = "%" + param + "%";
			q.where(QDuAn.duAn.tenDuAn.like(tuKhoa));
		}
		if (giaiDoanXucTien != null && !giaiDoanXucTien.isEmpty()) {
			q.where(QDuAn.duAn.giaiDoanXucTien.eq(GiaiDoanXucTien.valueOf(giaiDoanXucTien)));
		}
		if (linhVuc != null) {
			q.where(QDuAn.duAn.linhVuc.id.eq(linhVuc));
		}
		if (nguoiPhuTrach != null) {
			q.where(QDuAn.duAn.nguoiPhuTrach.id.eq(nguoiPhuTrach));
		}
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QDuAn.duAn.ngayBatDauXucTien.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QDuAn.duAn.ngayBatDauXucTien.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QDuAn.duAn.ngayBatDauXucTien.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QDuAn.duAn.ngaySua.desc());
		return q;
	}

	public boolean checkDelete(GiaiDoanXucTien giaiDoanXucTien, NhanVien nguoiTao) {
		if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(giaiDoanXucTien)
				|| GiaiDoanXucTien.HOAN_THANH.equals(giaiDoanXucTien)) {
			return false;
		}
		if (nguoiTao.equals(core().getNhanVien())) {
			return true;
		} else {
			return false;
		}
	}

	@Command
	public void reset(@BindingParam("vm") final DuAnService vm) {
		getArg().put("tuKhoa", null);
		getArg().put("giaiDoanXucTien", null);
		getArg().put("linhVuc", null);
		getArg().put("nhanVien", null);
		setTuNgay(null);
		setDenNgay(null);
		BindUtils.postNotifyChange(null, null, vm, "arg");
		BindUtils.postNotifyChange(null, null, vm, "tuNgay");
		BindUtils.postNotifyChange(null, null, vm, "denNgay");
		Clients.evalJavaScript("getFocus()");
	}

	public List<NhanVien> getListNguoiPhuTrach() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		JPAQuery<NhanVien> q = find(NhanVien.class)
				.where(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN));
		if (core().getNhanVien().getPhongBan() != null) {
			q.where(QNhanVien.nhanVien.phongBan.eq(core().getNhanVien().getPhongBan()));
		}
		if (q != null) {
			list.addAll(q.fetch());
			return list;
		}
		return list;
	}
	
}
