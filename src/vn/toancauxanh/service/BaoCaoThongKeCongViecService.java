package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QDoanVao;
import vn.toancauxanh.model.QDuAn;
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
	public void checkAndSearch() {
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa").trim();
		if (tuKhoa.length() > 5) {
			BindUtils.postNotifyChange(null, null, this, "listDuAnAndDoanVao");
		}
	}
	
	private boolean checkDuAn;
	
	private boolean checkDoanVao;
	
	public boolean isCheckDuAn() {
		return checkDuAn;
	}

	public void setCheckDuAn(boolean checkDuAn) {
		this.checkDuAn = checkDuAn;
	}

	public boolean isCheckDoanVao() {
		return checkDoanVao;
	}

	public void setCheckDoanVao(boolean checkDoanVao) {
		this.checkDoanVao = checkDoanVao;
	}

	@Command
	public void checkLoaiCongViec() {
		String loaiCongViec = MapUtils.getString(argDeco(), "loaiCongViec");
		if (loaiCongViec == null ) {
			checkDuAn = false;
			checkDoanVao = false;
		} else if (LoaiCongViec.DU_AN.equals(LoaiCongViec.valueOf(loaiCongViec))) {
			checkDuAn = true;
			checkDoanVao = false;
		} else {
			checkDuAn = false;
			checkDoanVao = true;
		}
		BindUtils.postNotifyChange(null, null, this, "checkDuAn");
		BindUtils.postNotifyChange(null, null, this, "checkDoanVao");
	}
	
	@Command
	public void searchKey(@BindingParam("key") String key) {
		System.out.println("tukhoa"+key);
		this.getArg().put("tuKhoa", key);
		if (checkDuAn) {
			BindUtils.postNotifyChange(null, null, this, "targetQueryDuAn");
			System.out.println("zooooo day nha");
		}
		if (checkDoanVao) {
			BindUtils.postNotifyChange(null, null, this, "targetQueryDoanVao");
		}
	}
	
	public JPAQuery<DuAn> getTargetQueryDuAn() {
		System.out.println("zoo du an");
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa").trim();
		JPAQuery<DuAn> q = find(DuAn.class);
		if (tuKhoa != null && !tuKhoa.isEmpty()) {
			q.where(QDuAn.duAn.tenDuAn.like("%"+tuKhoa+"%"));
		}
		return q;
	}
	
	public JPAQuery<DoanVao> getTargetQueryDoanVao() {
		System.out.println("zoo doan vao");
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa").trim();
		JPAQuery<DoanVao> q = find(DoanVao.class);
		if (tuKhoa != null && !tuKhoa.isEmpty()) {
			q.where(QDoanVao.doanVao.tenDoanVao.like("%"+tuKhoa+"%"));
		}
		return q;
	}
}
