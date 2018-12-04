package vn.toancauxanh.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.gg.model.enums.ThongBaoEnum;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QGiaoViec;
import vn.toancauxanh.model.ThongBao;

public class GiaoViecService extends BasicService<GiaoViec> implements Serializable{
	
	private static final long serialVersionUID = 6984673095113713236L;
	private Set<GiaoViec> selectItems = new HashSet<>();
	
	public JPAQuery<GiaoViec> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String loaiCongViec = MapUtils.getString(argDeco(), "loaiCongViec");
		String trangThaiCongViec = MapUtils.getString(argDeco(), "trangThaiCongViec");
		Long nguoiPhuTrach = (Long) argDeco().get("nguoiDuocGiao");
		JPAQuery<GiaoViec> q = find(GiaoViec.class);
		if (core().getNhanVien().getVaiTro() != null && LoaiVaiTro.VAI_TRO_CHUYEN_VIEN.equals(core().getNhanVien().getVaiTro().getLoaiVaiTro())) {
			q.where(QGiaoViec.giaoViec.nguoiDuocGiao.id.eq(core().getNhanVien().getId()));
		}
		if (param != null && !param.isEmpty()) {
			String tuKhoa = "%" + param + "%";
			q.where(QGiaoViec.giaoViec.tenCongViec.like(tuKhoa).orAllOf(QGiaoViec.giaoViec.tenNhiemVu.like(tuKhoa)));
		}
		if (loaiCongViec != null) {
			q.where(QGiaoViec.giaoViec.loaiCongViec.eq(LoaiCongViec.valueOf(loaiCongViec)));
		}
		if (trangThaiCongViec != null) {
			q.where(QGiaoViec.giaoViec.trangThaiGiaoViec.eq(TrangThaiGiaoViec.valueOf(trangThaiCongViec)));
		}
		if (nguoiPhuTrach != null) {
			q.where(QGiaoViec.giaoViec.nguoiDuocGiao.id.eq(nguoiPhuTrach));
		}
		if (getFixTuNgay() != null && getFixDenNgay() == null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.after(getFixTuNgay()));
		} else if (getFixTuNgay() == null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.before(getFixDenNgay()));
		} else if (getFixTuNgay() != null && getFixDenNgay() != null) {
			q.where(QGiaoViec.giaoViec.ngayGiao.between(getFixTuNgay(), getFixDenNgay()));
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		
		return q;
	}
	
	public JPAQuery<GiaoViec> getTargetQueryByIdDuAn() {
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		Long id = MapUtils.getLongValue(argDeco(), "nguoiPhuTrach", 0);
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "");
		Long idDuAn = MapUtils.getLongValue(argDeco(), "idDuAn");
		JPAQuery<GiaoViec> q = find(GiaoViec.class).where(QGiaoViec.giaoViec.duAn.id.eq(idDuAn));
		if (tuKhoa != null) {
			q.where(QGiaoViec.giaoViec.tenCongViec.like("%" + tuKhoa + "%"));
		}
		if (id != 0) {
			q.where(QGiaoViec.giaoViec.nguoiDuocGiao.id.eq(id));
		}
		if (!trangThai.isEmpty()) {
			q.where(QGiaoViec.giaoViec.trangThaiGiaoViec.eq(TrangThaiGiaoViec.valueOf(trangThai)));
		}
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		/*q.setHint("org.hibernate.cacheable", false);*/
		return q;
	}
	
	public JPAQuery<GiaoViec> getTargetQueryByIdDuAnNotHoanThanh() {
		Long idDuAn = MapUtils.getLongValue(argDeco(), "idDuAn", 0);
		JPAQuery<GiaoViec> q = find(GiaoViec.class);
		if (idDuAn != null && idDuAn > 0) {
			q.where(QGiaoViec.giaoViec.duAn.id.eq(idDuAn));
		}
		q.where(QGiaoViec.giaoViec.trangThaiGiaoViec.ne(TrangThaiGiaoViec.HOAN_THANH));
		q.orderBy(QGiaoViec.giaoViec.ngaySua.desc());
		q.setHint("org.hibernate.cacheable", false);
		return q;
	}
	
	@Command
	public void nhacNhoCongViec(@BindingParam("wdn") Window wdn) {
		if (selectItems.size() == 0) {
			showNotification("", "Bạn chưa chọn công việc nào", "danger");
		} else {
			selectItems.forEach(item -> {
				ThongBao thongBao = new ThongBao();
				String tenNhiemVu;
				Long id;
				if (item.getDoanVao() != null) {
					tenNhiemVu = item.getDoanVao().getTenDoanVao();
					id = item.getDoanVao().getId();
					thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DOAN_VAO);
				} else {
					tenNhiemVu = item.getDuAn().getTenDuAn();
					id = item.getDuAn().getId();
					thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DU_AN);
				}
				
				thongBao.setNoiDung(core().getNhanVien().getHoVaTen() + "@ có nhắc nhở bạn trong công việc @"
						+ item.getTenCongViec() + "@ của dự án @" + tenNhiemVu
						+ "@. Hãy hoàn thành công việc.");
				thongBao.setNguoiGui(core().getNhanVien());
				thongBao.setNguoiNhan(item.getNguoiDuocGiao());
				thongBao.setIdObject(id);
				thongBao.setLoaiThongBao(LoaiThongBao.NHAC_NHO_CONG_VIEC);
				thongBao.saveNotShowNotification();
			});
			showNotification("", "Nhắc nhở hoàn thành", "success");
			wdn.detach();
		}

	}
	
	@Command
	public void closePopup(@BindingParam("wdn") final Window wdn, @BindingParam("vm") Object vm) {
		wdn.detach();
		BindUtils.postNotifyChange(null, null, vm, "targetQueryByIdDuAn");
	}
	
	public List<TrangThaiGiaoViec> getListTrangThaiGiaoViec(){
		List<TrangThaiGiaoViec> list = new ArrayList<TrangThaiGiaoViec>();
		list.add(null);
		list.add(TrangThaiGiaoViec.CHUA_LAM);
		list.add(TrangThaiGiaoViec.DANG_LAM);
		list.add(TrangThaiGiaoViec.HOAN_THANH);
		return list;
	}
	
	public Set<GiaoViec> getSelectItems() {
		return selectItems;
	}

	public void setSelectItems(Set<GiaoViec> selectItems) {
		this.selectItems = selectItems;
	}
	
	public List<GiaoViec> getListGiaoViecTheoDoanVao(Long idDoanVao) {
		List<GiaoViec> list = new ArrayList<GiaoViec>();
		if (idDoanVao != null && idDoanVao > 0) {
			JPAQuery<GiaoViec> q = find(GiaoViec.class)
					.where(QGiaoViec.giaoViec.doanVao.id.eq(Long.valueOf(idDoanVao)));
			if (q != null && q.fetchCount() > 0) {
				return q.fetch();
			}
		}
		return list;
	}
	
	public List<TrangThaiGiaoViec> getListTrangThaiCongViec() {
		List<TrangThaiGiaoViec> list = new ArrayList<TrangThaiGiaoViec>();
		list.add(null);
		list.add(TrangThaiGiaoViec.CHUA_LAM);
		list.add(TrangThaiGiaoViec.DANG_LAM);
		list.add(TrangThaiGiaoViec.HOAN_THANH);
		return list;
	}
	
	@Command
	public void reset(@BindingParam("vm") GiaoViecService vm) {
		getArg().put("tuKhoa", null);
		getArg().put("nguoiDuocGiao", null);
		getArg().put("loaiCongViec", null);
		getArg().put("trangThaiCongViec", null);
		setTuNgay(null);
		setDenNgay(null);
		BindUtils.postNotifyChange(null, null, vm, "arg");
		BindUtils.postNotifyChange(null, null, vm, "tuNgay");
		BindUtils.postNotifyChange(null, null, vm, "denNgay");
		Clients.evalJavaScript("getFocus()");
	}
	
}
