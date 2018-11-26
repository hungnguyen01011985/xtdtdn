package vn.toancauxanh.service;

import java.io.IOException;
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
		ExcelUtil.exportThongKeDoanVao("thongkedoanvao", "Thống kê đoàn vào", listDoanVao, "Thống kê đoàn vào");
	}
}
