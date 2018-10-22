package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.KhaNangDauTu;
import vn.toancauxanh.gg.model.enums.MucDoUuTien;

@Entity
@Table(name="duan")
public class DuAn extends Model<DuAn>{
	private String tenDuAn;
	private String tenCongViec;
	private String yKienChiDao;
	private String linhVuc;
	private String diaDiem;
	private String quyMoDuAn;
	private double tongVonDauTu;
	private String mucTieuDuAn;
	private int dienTichSuDungDat;
	private String mucDoCanhTranh;
	private MucDoUuTien mucDoUuTien;
	private KhaNangDauTu khaNangDauTu;
	private NhanVien nguoiPhuTrach = new NhanVien();
	private GiaiDoanXucTien giaiDoanXucTien = GiaiDoanXucTien.GIAI_DOAN_MOT;
	private Date ngayHoanThanh;
	private Date ngayBatDauXucTien;
	private TepTin taiLieu;
	private TepTin vanBanNDT;
	private GiaiDoanDuAn giaiDoanDuAn;
	private GiaoViec giaoViec;
	
	public DuAn() {
		
	}

	@Transient
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}

	public String getTenDuAn() {
		return tenDuAn;
	}

	public void setTenDuAn(String tenDuAn) {
		this.tenDuAn = tenDuAn;
	}

	@ManyToOne
	public NhanVien getNguoiPhuTrach() {
		return nguoiPhuTrach;
	}

	public void setNguoiPhuTrach(NhanVien nguoiPhuTrach) {
		this.nguoiPhuTrach = nguoiPhuTrach;
	}

	public Date getNgayBatDauXucTien() {
		return ngayBatDauXucTien;
	}

	public void setNgayBatDauXucTien(Date ngayBatDauXucTien) {
		this.ngayBatDauXucTien = ngayBatDauXucTien;
	}

	@Enumerated(EnumType.STRING)
	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public Date getNgayHoanThanh() {
		return ngayHoanThanh;
	}

	public void setNgayHoanThanh(Date ngayHoanThanh) {
		this.ngayHoanThanh = ngayHoanThanh;
	}

	public String getyKienChiDao() {
		return yKienChiDao;
	}

	public void setyKienChiDao(String yKienChiDao) {
		this.yKienChiDao = yKienChiDao;
	}

	@ManyToOne
	public TepTin getTaiLieu() {
		return taiLieu;
	}

	public void setTaiLieu(TepTin taiLieu) {
		this.taiLieu = taiLieu;
	}

	public String getLinhVuc() {
		return linhVuc;
	}

	public void setLinhVuc(String linhVuc) {
		this.linhVuc = linhVuc;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public String getQuyMoDuAn() {
		return quyMoDuAn;
	}

	public void setQuyMoDuAn(String quyMoDuAn) {
		this.quyMoDuAn = quyMoDuAn;
	}

	public double getTongVonDauTu() {
		return tongVonDauTu;
	}

	public void setTongVonDauTu(double tongVonDauTu) {
		this.tongVonDauTu = tongVonDauTu;
	}

	public String getMucTieuDuAn() {
		return mucTieuDuAn;
	}

	public void setMucTieuDuAn(String mucTieuDuAn) {
		this.mucTieuDuAn = mucTieuDuAn;
	}

	public int getDienTichSuDungDat() {
		return dienTichSuDungDat;
	}

	public void setDienTichSuDungDat(int dienTichSuDungDat) {
		this.dienTichSuDungDat = dienTichSuDungDat;
	}

	public String getMucDoCanhTranh() {
		return mucDoCanhTranh;
	}

	public void setMucDoCanhTranh(String mucDoCanhTranh) {
		this.mucDoCanhTranh = mucDoCanhTranh;
	}

	@Enumerated(EnumType.STRING)
	public MucDoUuTien getMucDoUuTien() {
		return mucDoUuTien;
	}

	public void setMucDoUuTien(MucDoUuTien mucDoUuTien) {
		this.mucDoUuTien = mucDoUuTien;
	}

	@Enumerated(EnumType.STRING)
	public KhaNangDauTu getKhaNangDauTu() {
		return khaNangDauTu;
	}

	public void setKhaNangDauTu(KhaNangDauTu khaNangDauTu) {
		this.khaNangDauTu = khaNangDauTu;
	}

	@ManyToOne
	public TepTin getVanBanNDT() {
		return vanBanNDT;
	}

	public void setVanBanNDT(TepTin vanBanNDT) {
		this.vanBanNDT = vanBanNDT;
	}
	
	@Transient
	public GiaoViec getGiaoViec() {
		return giaoViec;
	}

	public void setGiaoViec(GiaoViec giaoViec) {
		this.giaoViec = giaoViec;
	}

	@Transient
	public List<MucDoUuTien> getListMucDoUuTien() {
		List<MucDoUuTien> list = new ArrayList<MucDoUuTien>();
		list.add(MucDoUuTien.UU_TIEN_CAO);
		list.add(MucDoUuTien.UU_TIEN_TRUNG_BINH);
		list.add(MucDoUuTien.UU_TIEN_THAP);
		return list;
	}
	
	@Transient
	public List<KhaNangDauTu> getListKhaNangDauTu() {
		List<KhaNangDauTu> list = new ArrayList<KhaNangDauTu>();
		list.add(KhaNangDauTu.KHA_NANG_CAO);
		list.add(KhaNangDauTu.KHA_NANG_KHA);
		list.add(KhaNangDauTu.KHA_NANG_THAP);
		return list;
	}
	
	@Command
	public void savePhuTrach(@BindingParam("wdn") final Window wdn,@BindingParam("list") final Object list) {
		wdn.detach();
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		variables.put("list", list);
		variables.put("userPopup", "task_NguoiPhuTrach");
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}
	
	@Transient
	public String getSrc() {
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.name().equals(getGiaiDoanXucTien().name())) {
			return "quanlyduan/giaidoan1.zul";
		}
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.name().equals(getGiaiDoanXucTien().name())) {
			return "quanlyduan/giaidoan2.zul";
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.name().equals(getGiaiDoanXucTien().name())) {
			return "quanlyduan/giaidoan3.zul";
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BON.name().equals(getGiaiDoanXucTien().name())) {
			return "quanlyduan/giaidoan4.zul";
		}
		return null;
	}
}
