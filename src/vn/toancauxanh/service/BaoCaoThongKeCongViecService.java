package vn.toancauxanh.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QGiaoViec;

public class BaoCaoThongKeCongViecService extends BasicService<GiaoViec>{
	
	public JPAQuery<GiaoViec> getTargetQuery() {
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String loaiCongViec = MapUtils.getString(argDeco(), "loaiCongViec");
		JPAQuery<GiaoViec> q = find(GiaoViec.class);
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.between(getFixTuNgay(), getFixDenNgay()));
		}
		if (loaiCongViec != null) {
			q.where(QGiaoViec.giaoViec.loaiCongViec.eq(LoaiCongViec.valueOf(loaiCongViec)));
			if (tuKhoa != null && !tuKhoa.isEmpty() && LoaiCongViec.DU_AN.equals(LoaiCongViec.valueOf(loaiCongViec))) {
				q.where(QGiaoViec.giaoViec.duAn.tenDuAn.like("%"+tuKhoa+"%"));
			}
			if (tuKhoa != null && !tuKhoa.isEmpty() && LoaiCongViec.DOAN_VAO.equals(LoaiCongViec.valueOf(loaiCongViec))) {
				q.where(QGiaoViec.giaoViec.doanVao.tenDoanVao.like("%"+tuKhoa+"%"));
			}
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		return q;
	}
	
	@Command
	public void xuatExcel(@BindingParam("list") List<GiaoViec> listGiaoViec) throws IOException {
		ExcelUtil.exportThongKeCongViec("thongkebaocaocongviec", "Thống kê công việc", listGiaoViec, "Thống kê công việc");
	}
		
}
