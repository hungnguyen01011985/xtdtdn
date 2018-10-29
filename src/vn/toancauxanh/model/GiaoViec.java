package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;

@Entity
@Table(name="giaoviec")
public class GiaoViec extends Model<GiaoViec>{
	private DuAn duAn;
	private String tenCongViec;
	private NhanVien nguoiGiaoViec = new NhanVien();
	private NhanVien nguoiDuocGiao = new NhanVien();
	private Date ngayGiao;
	private Date hanThucHien;
	private GiaiDoanXucTien giaiDoanXucTien;
	private TrangThaiGiaoViec trangThaiGiaoViec = TrangThaiGiaoViec.CHUA_LAM;
	private String yKienChiDao;
	private TepTin taiLieu = new TepTin();
	private Date ngayHoanThanh;
	private String ketQua;
	private TepTin taiLieuKetQua;
	
	public String getyKienChiDao() {
		return yKienChiDao;
	}

	public void setyKienChiDao(String yKienChiDao) {
		this.yKienChiDao = yKienChiDao;
	}

	@ManyToOne
	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}
	
	@ManyToOne
	public NhanVien getNguoiGiaoViec() {
		return nguoiGiaoViec;
	}

	public void setNguoiGiaoViec(NhanVien nguoiGiaoViec) {
		this.nguoiGiaoViec = nguoiGiaoViec;
	}
	
	@ManyToOne
	public NhanVien getNguoiDuocGiao() {
		return nguoiDuocGiao;
	}

	public void setNguoiDuocGiao(NhanVien nguoiDuocGiao) {
		this.nguoiDuocGiao = nguoiDuocGiao;
	}

	public Date getNgayGiao() {
		return ngayGiao;
	}

	public void setNgayGiao(Date ngayGiao) {
		this.ngayGiao = ngayGiao;
	}

	public Date getHanThucHien() {
		return hanThucHien;
	}

	public void setHanThucHien(Date hanThucHien) {
		this.hanThucHien = hanThucHien;
	}
	
	@Enumerated(EnumType.STRING)
	public TrangThaiGiaoViec getTrangThaiGiaoViec() {
		return trangThaiGiaoViec;
	}

	public void setTrangThaiGiaoViec(TrangThaiGiaoViec trangThaiGiaoViec) {
		this.trangThaiGiaoViec = trangThaiGiaoViec;
	}
	
	@Enumerated(EnumType.STRING)
	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

	@ManyToOne
	public TepTin getTaiLieu() {
		return taiLieu;
	}

	public void setTaiLieu(TepTin taiLieu) {
		this.taiLieu = taiLieu;
	}
	
	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

	public String getKetQua() {
		return ketQua;
	}

	public void setKetQua(String ketQua) {
		this.ketQua = ketQua;
	}
	
	@ManyToOne
	public TepTin getTaiLieuKetQua() {
		if (TrangThaiGiaoViec.DANG_LAM.equals(trangThaiGiaoViec)) {
			if (this.taiLieuKetQua == null) {
				System.out.println("vao zzzzzzzzzzzz");
				taiLieuKetQua = new TepTin();
			}
		}
		return taiLieuKetQua;
	}

	public void setTaiLieuKetQua(TepTin taiLieuKetQua) {
		this.taiLieuKetQua = taiLieuKetQua;
	}

	@Command
	public void saveGiaoViec(@BindingParam("vmArgs") final Object ob,
			@BindingParam("vm") final Object vm,@BindingParam("wdn") final Window wd) {
		wd.detach();
		save();
		BindUtils.postNotifyChange(null, null, this, "*");
	}
	
	@Command
	public void nhanViec(@BindingParam("item") final GiaoViec ob,
			@BindingParam("vm") final Object vm) {
		ob.setTrangThaiGiaoViec(TrangThaiGiaoViec.DANG_LAM);
		this.getTaiLieuKetQua().saveNotShowNotification();
		ob.save();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, vm, "*");
	}
	
	@Command
	public void saveBaoCao(@BindingParam("vmArgs") final Object ob,
			@BindingParam("wdn") final Window wd) {
		wd.detach();
		this.setTrangThaiGiaoViec(TrangThaiGiaoViec.HOAN_THANH);
		this.getTaiLieuKetQua().saveNotShowNotification();
		this.save();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, ob, "*");
	}
	
	@Command
	public void delete(@BindingParam("item") final GiaoViec giaoViec,
			@BindingParam("vm") final Object vm,@BindingParam("attr") String attr) {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (Messagebox.ON_OK.equals(event.getName())) {
								showNotification("Xóa thành công!", "", "success");
								if(giaoViec != null ) {
									if(!giaoViec.noId()) {
										giaoViec.doDelete(true);
									}
								}
								BindUtils.postNotifyChange(null, null, this, "*");
								BindUtils.postNotifyChange(null, null, vm, attr);
							}
						}
					});
		}
	}
	
}
