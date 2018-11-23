package vn.toancauxanh.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class BaoCaoThongKeDuAnService extends BasicService<GiaiDoanDuAn>{
	public JPAQuery<GiaiDoanDuAn> getTargetQuery() {
		String tienDoXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien");
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
				.where(QGiaiDoanDuAn.giaiDoanDuAn.daQuaGiaiDoan.ne(false));
		if (tienDoXucTien != null && !tienDoXucTien.isEmpty()) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.giaiDoanXucTien.eq(GiaiDoanXucTien.valueOf(tienDoXucTien)));
		}
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.ngayBatDauXucTien.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.ngaySua.desc());
		return q;
	}
	
	@Command
	public void xuatExcel(@BindingParam("list") List<GiaiDoanDuAn> listGiaiDoanDuAn) throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		listGiaiDoanDuAn.forEach(item -> {
			Object[] ob = new Object[6];
			ob[0] = item.getDuAn().getTenDuAn();
			ob[1] = item.getTenCongTy();
			ob[2] = item.getDuAn().getDiaDiem();
			ob[3] = item.getDuAn().getTongVonDauTu();
			ob[4] = item.getDuAn().getMucTieuDuAn();
			ob[5] = item.getGiaiDoanXucTien();
			list.add(ob);
		});
		ExcelUtil.exportThongKeDuAn("Thống kê dự án", "thongkebaocaoduan", "Thống kê dự án", list);
	}
	
}
