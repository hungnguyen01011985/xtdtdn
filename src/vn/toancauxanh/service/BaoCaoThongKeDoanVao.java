package vn.toancauxanh.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;

public class BaoCaoThongKeDoanVao extends BasicService<DoanVao>{
	public JPAQuery<DoanVao> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String quocGia = MapUtils.getString(argDeco(), "quocGia", "");
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (param != null && !param.isEmpty()) {
			q.where(QDoanVao.doanVao.tenDoanVao.like("%" + param + "%"));
		}
		if (quocGia != null && !quocGia.isEmpty()) {
			q.where(QDoanVao.doanVao.tenQuocGia.eq(quocGia));
		}
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QDoanVao.doanVao.thoiGianDenLamViec.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QDoanVao.doanVao.ngaySua.desc());
		setTongSoThanhVien(q.fetch().stream().map(DoanVao::getSoNguoi).mapToInt(Integer::intValue).sum());
		BindUtils.postNotifyChange(null, null, this, "tongSoThanhVien");
		return q;
	}
	
	private Integer tongSoThanhVien = 0;
	
	public void setTongSoThanhVien(Integer tongSoThanhVien) {
		this.tongSoThanhVien = tongSoThanhVien;
	}

	public Integer getTongSoThanhVien() {
		return tongSoThanhVien;
	}

	@Command
	public void xuatExcel(@BindingParam("list") List<DoanVao> listDoanVao) throws IOException {
		ExcelUtil.exportThongKeDoanVao("thongkedoanvao", "Thống kê đoàn vào", listDoanVao, "Thống kê đoàn vào");
	}
}
