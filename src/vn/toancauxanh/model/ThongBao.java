package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zkoss.zk.ui.Executions;

import vn.toancauxanh.gg.model.enums.LoaiThongBao;

@Entity
@Table(name="thongbao")
public class ThongBao extends Model<ThongBao>{
	private String noiDung;
	private NhanVien nguoiGui;
	private NhanVien nguoiNhan;
	private LoaiThongBao loaiThongBao;
	private boolean daXem;
	private Long idObject;
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
	
	public void saveAndRedirect(){
		doSave();
		String urlView = "";
		urlView = urlView.concat("/cp/quanlyduan/" + this.idObject);
		Executions.getCurrent().sendRedirect(urlView);
	}

}