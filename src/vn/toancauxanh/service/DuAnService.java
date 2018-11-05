package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDuAn;

public class DuAnService extends BasicService<DuAn> {
	public DuAn getDuAnById(String id) {
		if (id != null) {
			return find(DuAn.class).where(QDuAn.duAn.id.eq(Long.parseLong(id))).fetchOne();
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

	public boolean checkView(Long idNV, String id) {
		if (id == null || idNV == null || id.trim().isEmpty()) {
			return false;
		}
		return subString(id).contains(idNV);
	}

	public List<GiaiDoanXucTien> getListGiaiDoanXucTienAndNull() {
		List<GiaiDoanXucTien> list = new ArrayList<>();
		list.add(null);
		list.add(GiaiDoanXucTien.GIAI_DOAN_MOT);
		list.add(GiaiDoanXucTien.GIAI_DOAN_HAI);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BA);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BON);
		return list;
	}

	@Command
	public void reset(@BindingParam("vm") final DuAnService vm) {
		Executions.sendRedirect("/cp/quanlyduan");
	}

	/*public List<NhanVien> getListNguoiPhuTrachAndNull() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		list.add(null);
		JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.phongBan.id.eq(1l))
				.where(QNhanVien.nhanVien.vaiTros.any().vaiTroNguoiDung.eq(VaiTroNguoiDung.VAI_TRO_CHUYEN_VIEN));
		if (q != null) {
			list.addAll(q.fetch());
			return list;
		}
		return list;
	}*/
}
