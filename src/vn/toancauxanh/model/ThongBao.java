package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.ThongBaoEnum;

@Entity
@Table(name = "thongbao")
public class ThongBao extends Model<ThongBao> {
	@Lob
	private String noiDung;
	private NhanVien nguoiGui;
	private NhanVien nguoiNhan;
	private LoaiThongBao loaiThongBao;
	private boolean daXem;
	private ThongBaoEnum kieuThongBao;
	private Long idObject;
	private boolean xemChiTiet;
	
	public ThongBao() {

	}

	public Long getIdObject() {
		return idObject;
	}

	public void setIdObject(Long idObject) {
		this.idObject = idObject;
	}

	public boolean isDaXem() {
		return daXem;
	}

	public void setDaXem(boolean daXem) {
		this.daXem = daXem;
	}
	
	@Enumerated(EnumType.STRING)
	public ThongBaoEnum getKieuThongBao() {
		return kieuThongBao;
	}

	public void setKieuThongBao(ThongBaoEnum kieuThongBao) {
		this.kieuThongBao = kieuThongBao;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	@ManyToOne
	public NhanVien getNguoiGui() {
		return nguoiGui;
	}

	public void setNguoiGui(NhanVien nguoiGui) {
		this.nguoiGui = nguoiGui;
	}

	@ManyToOne
	public NhanVien getNguoiNhan() {
		return nguoiNhan;
	}

	public void setNguoiNhan(NhanVien nguoiNhan) {
		this.nguoiNhan = nguoiNhan;
	}

	public LoaiThongBao getLoaiThongBao() {
		return loaiThongBao;
	}

	public void setLoaiThongBao(LoaiThongBao loaiThongBao) {
		this.loaiThongBao = loaiThongBao;
	}
	
	public boolean isXemChiTiet() {
		return xemChiTiet;
	}

	public void setXemChiTiet(boolean xemChiTiet) {
		this.xemChiTiet = xemChiTiet;
	}

	@Transient
	public String getClassColor() {
		if (this.daXem) {
			return "CHUA_LAM";
		}
		return "DANG_LAM";
	}

	public void redirect(String href) {
		String urlView = "";
		if (this.isXemChiTiet()) {
			urlView = urlView.concat(href +"chitiet/"+ this.idObject);
		} else {
			urlView = urlView.concat(href + this.idObject);
		}
		Executions.getCurrent().sendRedirect(urlView, "_blank");
	}

	@Command
	public void viewNotify(@BindingParam("vm") Object vm, @BindingParam("attr") String attr,
			@BindingParam("href") String href) {
		if (!this.daXem) {
			this.setDaXem(true);
			this.saveNotShowNotification();
			BindUtils.postNotifyChange(null, null, vm, attr);
		}
		if (ThongBaoEnum.THONG_BAO_DOAN_VAO.equals(this.getKieuThongBao())) {
			this.redirect("/cp/quanlydoanvao/edit/");
		} else if (ThongBaoEnum.THONG_BAO_DU_AN.equals(this.getKieuThongBao())) {
			this.redirect("/cp/quanlyduan/");
		}
	}

}
