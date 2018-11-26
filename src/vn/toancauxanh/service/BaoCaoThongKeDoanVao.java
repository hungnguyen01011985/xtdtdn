package vn.toancauxanh.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class BaoCaoThongKeDoanVao extends BasicService<DoanVao>{
	public JPAQuery<DoanVao> getTargetQuery() {
		JPAQuery<DoanVao> q = find(DoanVao.class);
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
	
	@Command
	public void xuatExcel(@BindingParam("list") List<DoanVao> listDoanVao) throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		listDoanVao.forEach(item -> {
			Object[] ob = new Object[5];
			ob[0] = item.getTenDoanVao();
			ob[1] = item.getSoNguoi();
			ob[2] = item.getNoiDoanDiTham();
			ob[3] = item.getThoiGianDenLamViec();
			ob[4] = item.getTomTatNoiDungKQ();
			list.add(ob);
		});
		ExcelUtil.exportThongKeDoanVao("Thống kê đoàn vào", "thongkedoanvao", "Thống kê đoàn vào", list);
	}
}
