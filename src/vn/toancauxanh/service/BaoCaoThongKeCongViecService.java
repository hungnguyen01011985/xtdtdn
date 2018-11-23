package vn.toancauxanh.service;

import java.io.IOException;
import java.util.ArrayList;
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
		Long nguoiGiaoViec = (Long) argDeco().get("nguoiGiaoViec");
		Long nguoiPhuTrach = (Long) argDeco().get("nguoiDuocGiao");
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
		if (nguoiPhuTrach != null && nguoiPhuTrach > 0) {
			q.where(QGiaoViec.giaoViec.nguoiDuocGiao.id.eq(nguoiPhuTrach));
		}
		if (nguoiGiaoViec != null && nguoiGiaoViec > 0) {
			q.where(QGiaoViec.giaoViec.nguoiGiaoViec.id.eq(nguoiGiaoViec));
		}
		if (loaiCongViec != null && tuKhoa != null && !tuKhoa.isEmpty() && LoaiCongViec.DU_AN.equals(LoaiCongViec.valueOf(loaiCongViec))) {
			q.where(QGiaoViec.giaoViec.duAn.tenDuAn.like("%"+tuKhoa+"%"));
		}
		if (loaiCongViec != null && tuKhoa != null && !tuKhoa.isEmpty() && LoaiCongViec.DOAN_VAO.equals(LoaiCongViec.valueOf(loaiCongViec))) {
			q.where(QGiaoViec.giaoViec.doanVao.tenDoanVao.like("%"+tuKhoa+"%"));
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		return q;
	}
	
	@Command
	public void xuatExcel(@BindingParam("list") List<GiaoViec> listGiaoViec) throws IOException {
		List<Object[]> list = new ArrayList<Object[]>();
		listGiaoViec.forEach(item -> {
			Object[] ob = new Object[6];
			ob[0] = item.getTenCongViec();
			ob[1] = item.getDuAn().getTenDuAn();
			ob[2] = item.getNguoiGiaoViec().getHoVaTen();
			ob[3] = item.getNguoiDuocGiao().getHoVaTen();
			ob[4] = item.getNgayGiao();
			ob[5] = item.getHanThucHien();
			ob[6] = item.getTrangThaiGiaoViec();
			list.add(ob);
		});
		ExcelUtil.exportThongKeDuAn("Thống kê công việc", "thongkebaocaocongviec", "Thống kê công việc", list);
	}
		
}
