package vn.toancauxanh.model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.KhaNangDauTu;
import vn.toancauxanh.gg.model.enums.MucDoUuTien;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;
import vn.toancauxanh.rest.model.DuAnModel;
import vn.toancauxanh.rest.model.GiaiDoanBaModel;
import vn.toancauxanh.rest.model.GiaiDoanBonModel;
import vn.toancauxanh.rest.model.GiaiDoanHaiModel;
import vn.toancauxanh.rest.model.GiaiDoanMotModel;
import vn.toancauxanh.rest.model.GiaiDoanNamModel;
import vn.toancauxanh.service.DonViDuAnService;
import vn.toancauxanh.service.GiaiDoanService;
import vn.toancauxanh.service.HoSoKhuDatService;

@Entity
@Table(name = "duan")
public class DuAn extends Model<DuAn> {
	private String tenDuAn;
	private String diaDiem;
	private String quyMoDuAn;
	private String idNguoiLienQuan = "";
	@Lob
	private String mucTieuDuAn;
	private Double tongVonDauTu = 0.0;
	private Double dienTichSuDungDat = 0.0;
	private LinhVucDuAn linhVuc;
	private MucDoUuTien mucDoUuTien;
	private KhaNangDauTu khaNangDauTu;
	private NhanVien nguoiPhuTrach = new NhanVien();
	private GiaiDoanXucTien giaiDoanXucTien = GiaiDoanXucTien.GIAI_DOAN_MOT;
	private Date ngayBatDauXucTien = new Date();
	private GiaiDoanDuAn giaiDoanDuAn;
	private GiaoViec giaoViec = new GiaoViec();
	private TepTin taiLieuNDT;
	
	private boolean checkTab;
	

	public DuAn() {

	}

	public String getIdNguoiLienQuan() {
		return idNguoiLienQuan;
	}

	public void setIdNguoiLienQuan(String idNguoiLienQuan) {
		this.idNguoiLienQuan = idNguoiLienQuan;
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

	@ManyToOne
	public LinhVucDuAn getLinhVuc() {
		return linhVuc;
	}

	public void setLinhVuc(LinhVucDuAn linhVuc) {
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

	public String getMucTieuDuAn() {
		return mucTieuDuAn;
	}

	public void setMucTieuDuAn(String mucTieuDuAn) {
		this.mucTieuDuAn = mucTieuDuAn;
	}

	public Double getTongVonDauTu() {
		return tongVonDauTu;
	}

	public void setTongVonDauTu(Double tongVonDauTu) {
		this.tongVonDauTu = tongVonDauTu;
	}

	public Double getDienTichSuDungDat() {
		return dienTichSuDungDat;
	}

	public void setDienTichSuDungDat(Double dienTichSuDungDat) {
		this.dienTichSuDungDat = dienTichSuDungDat;
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

	@Transient
	public GiaoViec getGiaoViec() {
		return giaoViec;
	}

	public void setGiaoViec(GiaoViec giaoViec) {
		this.giaoViec = giaoViec;
	}
	
	@DependsOn({"diaDiem", "dienTichSuDungDat"})
	@Transient
	public String getDiaDiemAndDienTich() {
		if (diaDiem != null && dienTichSuDungDat != 0) {
			return diaDiem + ", " + dienTichSuDungDat.intValue();
		}
		if (diaDiem == null && dienTichSuDungDat == 0) {
			return "Chưa xác định";
		}
		return (diaDiem == null ? dienTichSuDungDat.intValue() + "" : diaDiem );
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

	@Transient
	public List<PhuongThucLuaChonNDT> getListPhuongThucLuaChonNDT() {
		List<PhuongThucLuaChonNDT> list = new ArrayList<PhuongThucLuaChonNDT>();
		list.add(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT);
		list.add(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT);
		list.add(PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG);
		return list;
	}

	@Command
	public void savePhuTrach(@BindingParam("wdn") final Window wdn, @BindingParam("list") final Object list,
			@BindingParam("attr") final String attr) {
		wdn.detach();
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		variables.put("list", list);
		variables.put("attr", attr);
		variables.put("goTask", "task_nguoiPhuTrach");
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	@Command
	public void saveGiaoViec(@BindingParam("wdn") final Window wdn, @BindingParam("list") final Object list,
			@BindingParam("attr") final String attr) {
		wdn.detach();
		Map<String, Object> variables = new HashMap<>();
		variables.put("giaoViec", this.getGiaoViec());
		variables.put("goTask", "task_giaoViec");
		variables.put("list", list);
		variables.put("attr", attr);
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	@Command
	public void goTask(@BindingParam("task") final String task) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		variables.put("thoiHanGiaiDoanMot", new Date());
		if (task != null) {
			variables.put("goTask", task);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	// hàm set task khi ấn quay lại giai đoạn 1
	@Command
	public void goBack(@BindingParam("task") final String task) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		if (task != null) {
			variables.put("goTask", task);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	@Command
	public void goNext(@BindingParam("task") final String task) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		if (task != null) {
			variables.put("goTask", task);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}
	
	@Command
	public void goKetThuc(@BindingParam("task") final String task) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		if (task != null) {
			variables.put("goTask", task);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	private String srcGiaiDoanDuAn;
	
	@Transient
	public String getSrcGiaiDoanDuAn() {
		if (srcGiaiDoanDuAn == null || srcGiaiDoanDuAn.isEmpty()) {
			if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(getGiaiDoanXucTien()) || GiaiDoanXucTien.CHUA_HOAN_THANH.equals(getGiaiDoanXucTien()) || GiaiDoanXucTien.HOAN_THANH.equals(getGiaiDoanXucTien())) {
				return "quanlyduan/giaidoan1.zul";
			}
			if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(getGiaiDoanXucTien())) {
				return "quanlyduan/giaidoan2.zul";
			}
			if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(getGiaiDoanXucTien())) {
				return "quanlyduan/giaidoan3.zul";
			}
			if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(getGiaiDoanXucTien())) {
				return "quanlyduan/giaidoan4.zul";
			}
			if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(getGiaiDoanXucTien())) {
				return "quanlyduan/giaidoan5.zul";
			}
		}
		return srcGiaiDoanDuAn;
	}
	
	@Transient
	public String getCssPlan(GiaiDoanXucTien giaiDoan, String type, boolean check) {
		if (type.equals("cssNumber")) {
			if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(this.getGiaiDoanXucTien())) {
				if (giaiDoan.ordinal() > GiaiDoanXucTien.GIAI_DOAN_BA.ordinal()) {
					return "";
				}
			}
			if (giaiDoan.equals(this.giaiDoanXucTien)) {
				return "plan-number-active";
			}
			if (giaiDoan.ordinal() < this.getGiaiDoanXucTien().ordinal()) {
				return "plan-number-completed";
			}
			return "";
		}
		if (type.equals("cssTitle")) {
			if (giaiDoan.equals(this.giaiDoanXucTien)) {
				return "plan-title-active";
			}
			return "";
		}
		if (type.equals("imageOrNumber")) {
			if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(this.getGiaiDoanXucTien())) {
				if (giaiDoan.ordinal() > GiaiDoanXucTien.GIAI_DOAN_BA.ordinal()) {
					if (check) {
						return "";
					}
					if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(giaiDoan)) {
						return "4";
					}
					if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(giaiDoan)) {
						return "5";
					}
				}
			}
			if (giaiDoan.ordinal() < this.getGiaiDoanXucTien().ordinal()) {
				if (!check) {
					return "";
				}
				return "/assets/icon-bxtdn/check-qua-giai-doan.svg";
			} else {
				if (check) {
					return "";
				}
				if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(giaiDoan)) {
					return "1";
				}
				if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(giaiDoan)) {
					return "2";
				}
				if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(giaiDoan)) {
					return "3";
				}
				if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(giaiDoan)) {
					return "4";
				}
				if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(giaiDoan)) {
					return "5";
				}
			}
		}
		return "";
	}
	
	@Command
	public void redirectXemChiTietDuAn(@BindingParam("id") Long id) {
		String url = "/cp/quanlyduan/chitiet/";
		Executions.sendRedirect(url.concat(id.toString()));
	}
	
	@Command
	public void redirectXuLyDuAn(@BindingParam("id") Long id) {
		String url = "/cp/quanlyduan/";
		Executions.sendRedirect(url.concat(id.toString()));
	}
	
	@Command
	public void redirectGiaiDoanDuAn(@BindingParam("giaiDoan") GiaiDoanXucTien giaiDoan) {
		int index = -1;
		if (giaiDoan.ordinal() > this.getGiaiDoanXucTien().ordinal()) {
			return;
		}
		if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(this.getGiaiDoanXucTien())) {
			if (giaiDoan.ordinal() > GiaiDoanXucTien.GIAI_DOAN_BA.ordinal()) {
				return ;
			}
		}
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(giaiDoan)) {
			setSrcGiaiDoanDuAn("quanlyduan/giaidoan1.zul");
			index = 0;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(giaiDoan)) {
			setSrcGiaiDoanDuAn("quanlyduan/giaidoan2.zul");
			index = 1;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(giaiDoan)) {
			setSrcGiaiDoanDuAn("quanlyduan/giaidoan3.zul");
			index = 2;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(giaiDoan)) {
			setSrcGiaiDoanDuAn("quanlyduan/giaidoan4.zul");
			index = 3;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(giaiDoan)) {
			setSrcGiaiDoanDuAn("quanlyduan/giaidoan5.zul");
			index = 4;
		}
		if (index != -1) {
			Clients.evalJavaScript("removeTitleCss("+index+")");
		}
		BindUtils.postNotifyChange(null, null, this, "srcGiaiDoanDuAn");
	}
	
	public boolean checkDangOGiaiDoan(GiaiDoanXucTien giaiDoan) {
		if (giaiDoan.equals(this.getGiaiDoanXucTien())) {
			return true;
		}
		return false;
	}
	
	public void setSrcGiaiDoanDuAn(String srcGiaiDoanDuAn) {
		this.srcGiaiDoanDuAn = srcGiaiDoanDuAn;
	}

	private String srcGiaiDoan4;

	@Transient
	public String getSrcGiaiDoan4() {
		return srcGiaiDoan4;
	}

	public void setSrcGiaiDoan4(String srcGiaiDoan4) {
		this.srcGiaiDoan4 = srcGiaiDoan4;
	}
	
	@Transient
	public boolean isCheckTab() {
		return checkTab;
	}

	public void setCheckTab(boolean checkTab) {
		this.checkTab = checkTab;
	}
	
	@ManyToOne
	public TepTin getTaiLieuNDT() {
		return taiLieuNDT;
	}

	public void setTaiLieuNDT(TepTin taiLieuNDT) {
		this.taiLieuNDT = taiLieuNDT;
	}

	@Command
	public void srcGiaiDoanBon(@BindingParam("giatri") boolean giatri, @BindingParam("vmArgs") DuAn duAn) {
		if (getGiaiDoanDuAn().getPhuongThucLuaChonNDT() == null) {
			return;
		}
		if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			if (giatri) {
				setSrcGiaiDoan4("quanlyduan/dau-gia-co.zul");
			} else {
				setSrcGiaiDoan4("quanlyduan/dau-gia-chua.zul");
			}
		}
		if (PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT.equals(getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			setSrcGiaiDoan4("quanlyduan/dau-thau.zul");
		}
		if (PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG.equals(getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			setSrcGiaiDoan4("quanlyduan/nhan-chuyen-nhuong.zul");
		}
		BindUtils.postNotifyChange(null, null, duAn, "srcGiaiDoan4");
		BindUtils.postNotifyChange(null, null, duAn, "option");
	}

	@Command
	public void addNewDonVi(@BindingParam("vm") DuAn duAn) {
		duAn.getGiaiDoanDuAn().getDonViDuAn().add(new DonViDuAn());
		BindUtils.postNotifyChange(null, null, duAn.getGiaiDoanDuAn().getDonViDuAn(), "*");
	}

	@Transient
	public AbstractValidator getValidatorNgay() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				final ValidationMessages vmsgs = (ValidationMessages) ctx.getValidatorArg("vmsg");
				if (vmsgs != null) {
					vmsgs.clearKeyMessages(Throwable.class.getSimpleName());
					vmsgs.clearMessages(ctx.getBindContext().getComponent());
				}
				validateNgayHoanThanh(ctx);
			}

			private boolean validateNgayHoanThanh(final ValidationContext ctx) {
				Boolean type = (Boolean) ctx.getValidatorArg("type");
				String firstText = (String) ctx.getValidatorArg("firstText");
				String secondText = (String) ctx.getValidatorArg("secondText");
				Date endDate = (Date) ctx.getValidatorArg("endDate");
				Date dateStart = (Date) ctx.getValidatorArg("dateStart");
				
				boolean result = true;
				if (type) {
					Date checkDate = (Date) ctx.getProperty().getValue();
					if (checkDate == null) {
						addInvalidMessage(ctx, "dateStart", firstText + " không được để trống");
						result = false;
					}
					if (checkDate != null && endDate != null && resetHourMinuteSecondMilli(checkDate).compareTo(endDate) > 0) {
						addInvalidMessage(ctx, "dateEnd",
								secondText + " phải lớn hơn hoặc bằng " + firstText.toLowerCase());
						result = false;
					}
				} else {
					Date checkDate = (Date) ctx.getProperty().getValue();
					if (checkDate == null) {
						addInvalidMessage(ctx, "dateEnd", secondText + " không được để trống");
						result = false;
					}
					if (dateStart != null && checkDate != null && resetHourMinuteSecondMilli(dateStart).compareTo(checkDate) > 0) {
						addInvalidMessage(ctx, "dateEnd",
								secondText + " phải lớn hơn hoặc bằng " + firstText.toLowerCase());
						result = false;
					}
				}
				return result;
			}
		};
	}

	@Transient
	public AbstractValidator getValidatorVonDauTu() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				final ValidationMessages vmsgs = (ValidationMessages) ctx.getValidatorArg("vmsg");
				if (vmsgs != null) {
					vmsgs.clearKeyMessages(Throwable.class.getSimpleName());
					vmsgs.clearMessages(ctx.getBindContext().getComponent());
				}
				validateNumber(ctx);
			}

			private boolean validateNumber(ValidationContext ctx) {
				boolean rs = true;
				String text = (String) ctx.getValidatorArg("text");
				Boolean type = (Boolean) ctx.getValidatorArg("type");
				Double vonDauTu = 0.0;
				try {
					vonDauTu = Double.parseDouble(ctx.getProperty().getValue().toString()) ;
				} catch (NumberFormatException e) {
					addInvalidMessage(ctx, "Bạn phải nhập số");
				} catch (NullPointerException e) {
					addInvalidMessage(ctx, "Bạn phải nhập số");
				}
				if (type != null) {
					if (vonDauTu <= 0) {
						addInvalidMessage(ctx, text + " phải lớn hơn 0");
						rs = false;
					}
				} else {
					if (vonDauTu < 0) {
						addInvalidMessage(ctx, text + " phải lớn hơn bằng 0");
						rs = false;
					}
				}
				return rs;
			}
		};
	}

	@Transient
	public AbstractValidator getValidatorTepTin() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String text = (String) ctx.getValidatorArg("text");
				String mess = (String) ctx.getValidatorArg("sms");
				Date dateCheck = (Date) ctx.getValidatorArg(text);
				if (dateCheck != null) {
					final ValidationMessages vmsgs = (ValidationMessages) ctx.getValidatorArg("vmsg");
					if (vmsgs != null) {
						vmsgs.clearKeyMessages(Throwable.class.getSimpleName());
						vmsgs.clearMessages(ctx.getBindContext().getComponent());
					}
					TepTin file = (TepTin) ctx.getProperty().getValue();
					if (file.getTenFile() == null || file.getTenFile().isEmpty()) {
						addInvalidMessage(ctx, mess, "Chưa tải tài liệu");
					}
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidatorTepTinDonVi() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String text = (String) ctx.getValidatorArg("text");
				Date dateCheck = (Date) ctx.getValidatorArg(text);
				if (dateCheck != null) {
					final ValidationMessages vmsgs = (ValidationMessages) ctx.getValidatorArg("vmsg");
					if (vmsgs != null) {
						vmsgs.clearKeyMessages(Throwable.class.getSimpleName());
						vmsgs.clearMessages(ctx.getBindContext().getComponent());
					}
					TepTin file = (TepTin) ctx.getProperty().getValue();
					if (file.getTenFile() == null || file.getTenFile().isEmpty()) {
						addInvalidMessage(ctx, "Chưa tải tài liệu");
					}
				}
			}
		};
	}
	
	@Command
	public void saveThongTinDuAn(){
		this.getTaiLieuNDT().saveNotShowNotification();
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(this.getId()));
		if (!q.fetchFirst().getTenDuAn().equals(this.getTenDuAn())) {
			JPAQuery<GiaoViec> q1 = find(GiaoViec.class).where(QGiaoViec.giaoViec.duAn.id.eq(this.getId()));
			q1.fetch().forEach(item -> {
				item.setTenNhiemVu(this.getTenDuAn());
				item.saveNotShowNotification();
			});
		}
		this.save();
		BindUtils.postNotifyChange(null, null, this, "taiLieuNDT");
	}
	
	@Command
	public void uploadFile(@BindingParam("medias") Object[] medias) {
		for (Object item : medias) {
			Media media = (Media) item;
			if (media.getName().toLowerCase().endsWith(".pdf") || media.getName().toLowerCase().endsWith(".doc")
					|| media.getName().toLowerCase().endsWith(".docx") || media.getName().toLowerCase().endsWith(".xls")
					|| media.getName().toLowerCase().endsWith(".xlsx")) {
				if (media.getByteData().length > 50000000) {
					showNotification("Tệp tin quá 50 MB", "Tệp tin quá lớn", "error");
				} else {
					String tenFile = media.getName().substring(0, media.getName().lastIndexOf(".")) + "_"
							+ Calendar.getInstance().getTimeInMillis()
							+ media.getName().substring(media.getName().lastIndexOf(".")).toLowerCase();
					TepTin tepTin = new TepTin();
					tepTin.setNameHash(tenFile);
					tepTin.setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
					tepTin.setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
					tepTin.setTenTaiLieu(media.getName().substring(0, media.getName().lastIndexOf(".")));
					tepTin.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
					tepTin.setMedia(media);
					this.giaiDoanDuAn.getTepTins().add(tepTin);
					this.giaiDoanDuAn.getTepTins().forEach(obj -> {
						try {
							obj.saveFileTepTin();
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					BindUtils.postNotifyChange(null, null, this.giaiDoanDuAn, "tepTins");
				}
			} else {
				showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
						"Có tệp không đúng định dạng", "danger");
			}
		}
	}
	
	@Command
	public void downLoadTepTin(@BindingParam("ob") final TepTin object) throws MalformedURLException {
		if (!object.getPathFile().isEmpty()) {
			final String path = folderStoreTaiLieu() + object.getNameHash();
			if (new java.io.File(path).exists()) {
				try {
					Filedownload.save(new URL("file:///" + path)
							.openStream(), null, object.getTenFile().concat(object.getTypeFile()));
				} catch (IOException e) {
					showNotification("Không tìm thấy file", "Thông báo", "danger");
				}
			} else {
				showNotification("Không tìm thấy file", "Thông báo", "danger");
			}
		}
	}
	
	@Command
	public void deleteFile(@BindingParam("index") final int index) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							giaiDoanDuAn.getTepTins().remove(index);
							BindUtils.postNotifyChange(null, null, giaiDoanDuAn, "tepTins");
							showNotification("Đã xóa", "", "success");
						}
					}
				});
	}
	
	@Command
	public void reUploadFile(@BindingParam("medias") final Object medias, @BindingParam("index") final int index) {
		Media media = (Media) medias;
		if (media.getName().toLowerCase().endsWith(".pdf") || media.getName().toLowerCase().endsWith(".doc")
				|| media.getName().toLowerCase().endsWith(".docx") || media.getName().toLowerCase().endsWith(".xls")
				|| media.getName().toLowerCase().endsWith(".xlsx")) {
			if (media.getByteData().length > 50000000) {
				showNotification("Tệp tin quá 50 MB", "Tệp tin quá lớn", "error");
			} else {
				String tenFile = media.getName().substring(0, media.getName().lastIndexOf(".")) + "_"
						+ Calendar.getInstance().getTimeInMillis()
						+ media.getName().substring(media.getName().lastIndexOf(".")).toLowerCase();
				TepTin tepTin = new TepTin();
				tepTin.setNameHash(tenFile);
				tepTin.setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
				tepTin.setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
				tepTin.setTenTaiLieu(media.getName().substring(0, media.getName().lastIndexOf(".")));
				tepTin.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
				tepTin.setMedia(media);
				giaiDoanDuAn.getTepTins().set(index, tepTin);
				this.giaiDoanDuAn.getTepTins().forEach(obj -> {
					try {
						obj.saveFileTepTin();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				BindUtils.postNotifyChange(null, null, this.giaiDoanDuAn, "tepTins");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	
	@Command
	public void swap() {
		checkTab = !checkTab;
		Clients.evalJavaScript("toggleTabThongTinDuAn()");
		BindUtils.postNotifyChange(null, null, this, "checkTab");
	}
	
	@Command
	public void addNewHoSoKhuDat(@BindingParam("vm") DuAn duAn) {
		duAn.getGiaiDoanDuAn().getHoSoKhuDats().add(new HoSoKhuDat());
		BindUtils.postNotifyChange(null, null, duAn , "*");
	}
	
	@Command
	public void deleteHoSoKhuDat(@BindingParam("obj") final HoSoKhuDat item, @BindingParam("vm") DuAn duAn){
		Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
				new EventListener<Event>() {
			@Override
			public void onEvent(final Event event) {
				if (Messagebox.ON_OK.equals(event.getName())) {
					duAn.getGiaiDoanDuAn().getHoSoKhuDats().remove(item);
					duAn.getGiaiDoanDuAn().getListXoaHoSoKhuDat().add(item);
					BindUtils.postNotifyChange(null, null, duAn.getGiaiDoanDuAn().getHoSoKhuDats() , "*");
				}
			}
		});
	}
	
	@Command
	public void redirect(@BindingParam("ob") TepTin ob) {
		String serverName = "";
		String href = "";
		int serverPort = 0;
		serverName = Executions.getCurrent().getServerName();
		serverPort = Executions.getCurrent().getServerPort();
		if (serverName != null) {
			String url = "";
			if (serverName.contains("192.168.1.247") || serverName.contains("projects.greenglobal.vn")) {
				url = "http://projects.greenglobal.vn:6782";
				href = (url + "/" + ob.getPathFile() + ob.getNameHash()).replace(File.separatorChar, '/');
			} else if ("localhost".equals(serverName) || "192.168.1.14".equals(serverName)) {
				url = serverName.concat(":" + serverPort);
				href = ("http://" + url + "/" + ob.getPathFile() + ob.getNameHash()).replace(File.separatorChar, '/');
			} else {
				href = ("/" + ob.getPathFile() + ob.getNameHash()).replace(File.separatorChar, '/');
			}
		}
		Executions.getCurrent().sendRedirect(href, "_blank");
	}
	
	@Command
	public void removeDonViDuAn(@BindingParam("vm") GiaiDoanDuAn giaiDoanDuAn, @BindingParam("item") DonViDuAn donViDuAn) {
		Messagebox.show("Bạn muốn xóa đơn vị này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
				new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) {
						if (Messagebox.ON_OK.equals(event.getName())) {
							if (!donViDuAn.noId()) {
								if (donViDuAn.getCongVanTraLoi() == null || donViDuAn.getCongVanTraLoi().getNameHash() == null) {
									donViDuAn.setCongVanTraLoi(null);
								}
								donViDuAn.doDelete(true);
							}
							giaiDoanDuAn.getDonViDuAn().remove(donViDuAn);
							BindUtils.postNotifyChange(null, null, giaiDoanDuAn, "donViDuAn");
						}
					}
				});
	}
	
	@Transient
	public boolean checkXoaDonViDuAn(DonViDuAn donViDuAn) {
		if (!donViDuAn.noId() && donViDuAn.getNgayNhanTraLoi() != null && donViDuAn.getCongVanTraLoi() != null) {
			return false;
		}
		return true;
	}
	
	public DuAnModel toDuAnModel() {
		DuAnModel rs = new DuAnModel();
		GiaiDoanService giaiDoans = new GiaiDoanService();
		List<GiaiDoanDuAn> listGiaiDoanDuAn = new ArrayList<>();
		listGiaiDoanDuAn = giaiDoans.getListGiaiDoanDuAnById(this.getId());
		rs.setId(this.getId() != null ? this.getId() : null);
		rs.setIdNguoiPhuTrach(this.getNguoiPhuTrach() != null ? this.getNguoiPhuTrach().getId() : null);
		rs.setTenDuAn(this.getTenDuAn() != null ? this.getTenDuAn() : "");
		rs.setQuyMoDuAn(this.getQuyMoDuAn() != null ? this.getQuyMoDuAn() : "");
		rs.setGiaiDoanXucTien(this.getGiaiDoanXucTien() != null ? this.getGiaiDoanXucTien().getText() : null);
		rs.setMucTieuDuAn(this.getMucTieuDuAn() != null ? this.getMucTieuDuAn() : "");
		rs.setTenLinhVucDuAn(this.getLinhVuc() != null ? this.getLinhVuc().getTen() : "");
		rs.setMucDoUuTien(this.getMucDoUuTien() != null ? this.getMucDoUuTien().getText() : "");
		rs.setKhaNangDauTu(this.getKhaNangDauTu() != null ? this.getKhaNangDauTu().getText() : "");
		rs.setHoTenNguoiPhuTrach(this.getNguoiPhuTrach() != null ? this.getNguoiPhuTrach().getHoVaTen() : "");
		rs.setTongVonDauTu(this.getTongVonDauTu());
		rs.setDienTichSuDungDat(this.getDienTichSuDungDat());
		rs.setNgayBatDauXucTien(this.getNgayBatDauXucTien() != null ? this.getNgayBatDauXucTien() : null);
		if (!listGiaiDoanDuAn.isEmpty()) {
			listGiaiDoanDuAn.forEach(item -> {
				if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanMot(setDuLieuGiaiDoanMot(item));
				} else if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanHai(setDuLieuGiaiDoanHai(item));
				} else if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanBa(setDuLieuGiaiDoanBa(item));
				} else if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanBon(setDuLieuGiaiDoanBon(item));
				} else if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanNam(setDuLieuGiaiDoanNam(item));
				} else if (GiaiDoanXucTien.HOAN_THANH.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanMot(setDuLieuGiaiDoanMot(item));
					rs.setGiaiDoanHai(setDuLieuGiaiDoanHai(item));
					rs.setGiaiDoanBa(setDuLieuGiaiDoanBa(item));
					rs.setGiaiDoanBon(setDuLieuGiaiDoanBon(item));
					rs.setGiaiDoanNam(setDuLieuGiaiDoanNam(item));
				} else if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(item.getGiaiDoanXucTien())) {
					rs.setGiaiDoanMot(setDuLieuGiaiDoanMot(item));
					rs.setGiaiDoanHai(setDuLieuGiaiDoanHai(item));
					rs.setGiaiDoanBa(setDuLieuGiaiDoanBa(item));
				}
			});
		}
		return rs;
	}
	
	public GiaiDoanMotModel setDuLieuGiaiDoanMot(GiaiDoanDuAn giaiDoanDuAn) {
		GiaiDoanMotModel giaiDoanMot = new GiaiDoanMotModel();
		
		// giai doan mot
		DonViDuAnService donViDuAns = new DonViDuAnService();
		List<DonViDuAn> listDonViDuAn = new ArrayList<>();
		listDonViDuAn = donViDuAns.getListDonViById(giaiDoanDuAn.getId());
		giaiDoanMot.setNgayGui(giaiDoanDuAn.getNgayGui() != null ? giaiDoanDuAn.getNgayGui() : null);
		giaiDoanMot.setNgayNhanPhanHoi(giaiDoanDuAn.getNgayNhanPhanHoi() != null ? giaiDoanDuAn.getNgayNhanPhanHoi() : null);
		giaiDoanMot.setDonViDuAns(listDonViDuAn != null ? listDonViDuAn.stream().map(DonViDuAn::toDonViDuAnModel).collect(Collectors.toList()) : null);
		return giaiDoanMot;
	}
	
	public GiaiDoanHaiModel setDuLieuGiaiDoanHai(GiaiDoanDuAn giaiDoanDuAn) {
		GiaiDoanHaiModel giaiDoanHai = new GiaiDoanHaiModel();
		// giai doan hai
		giaiDoanHai.setNgayKhaoSat(giaiDoanDuAn.getNgayKhaoSat() != null ? giaiDoanDuAn.getNgayKhaoSat() : null);
		giaiDoanHai.setNgayPhatHanhCVGD2(giaiDoanDuAn.getNgayPhatHanhCVGD2() != null ? giaiDoanDuAn.getNgayPhatHanhCVGD2() : null);
		giaiDoanHai.setGhiChu(giaiDoanDuAn.getGhiChu() != null ? giaiDoanDuAn.getGhiChu() : "");
		return giaiDoanHai;
	}
	
	public GiaiDoanBaModel setDuLieuGiaiDoanBa(GiaiDoanDuAn giaiDoanDuAn) {
		GiaiDoanBaModel giaiDoanBa = new GiaiDoanBaModel();
		// giai doan ba
		giaiDoanBa.setNgayGuiUBND(giaiDoanDuAn.getNgayGuiUBND() != null ? giaiDoanDuAn.getNgayGuiUBND() : null);
		giaiDoanBa.setNgayDuKienNhanPhanHoi(giaiDoanDuAn.getNgayDuKienNhanPhanHoi() != null ? giaiDoanDuAn.getNgayDuKienNhanPhanHoi() : null);
		giaiDoanBa.setNgayPhatHanhCV3(giaiDoanDuAn.getNgayPhatHanhCV3() != null ? giaiDoanDuAn.getNgayPhatHanhCV3() : null);
		return giaiDoanBa;
	}
	
	public GiaiDoanBonModel setDuLieuGiaiDoanBon(GiaiDoanDuAn giaiDoanDuAn) {
		GiaiDoanBonModel giaiDoanBon = new GiaiDoanBonModel();
		// Giai doan bon
		HoSoKhuDatService hoSoKhuDats = new HoSoKhuDatService();
		List<HoSoKhuDat> list = new ArrayList<>();
		list = hoSoKhuDats.getListHoSoKhuDatById(giaiDoanDuAn.getId());
		giaiDoanBon.setPhuongThucChonNhaDauTu(giaiDoanDuAn.getPhuongThucLuaChonNDT() != null ? giaiDoanDuAn.getPhuongThucLuaChonNDT().getText() : "");
		giaiDoanBon.setTenDonViChuTri(giaiDoanDuAn.getDonViChuTri() != null ? giaiDoanDuAn.getDonViChuTri().getTen() : "");
		giaiDoanBon.setIdDonViChuTri(giaiDoanDuAn.getDonViChuTri() != null ? giaiDoanDuAn.getDonViChuTri().getId() : null);
		giaiDoanBon.setTenDonViTuVan(giaiDoanDuAn.getDonViTuVan() != null ? giaiDoanDuAn.getDonViTuVan().getTen() : "");
		giaiDoanBon.setIdDonViTuVan(giaiDoanDuAn.getDonViTuVan() != null ? giaiDoanDuAn.getDonViTuVan().getId() : null);
		giaiDoanBon.setTenDonViLapKeHoach(giaiDoanDuAn.getDonViLapKeHoach() != null ? giaiDoanDuAn.getDonViLapKeHoach().getTen() : "");
		giaiDoanBon.setIdDonViLapKeHoach(giaiDoanDuAn.getDonViLapKeHoach() != null ? giaiDoanDuAn.getDonViLapKeHoach().getId() : null);
		giaiDoanBon.setTenDonViThucHien(giaiDoanDuAn.getDonViThucHien() != null ? giaiDoanDuAn.getDonViThucHien().getTen() : "");
		giaiDoanBon.setIdDonViThucHien(giaiDoanDuAn.getDonViThucHien() != null ? giaiDoanDuAn.getDonViThucHien().getId() : null);
		giaiDoanBon.setGiaDatKhoiDiemDauGia(giaiDoanDuAn.getGiaDatKhoiDiemDauGia());
		giaiDoanBon.setHoSoKhuDats(list != null ? list.stream().map(HoSoKhuDat::toHoSoKhuDatModel).collect(Collectors.toList()) : null);
		return giaiDoanBon;
	}
	
	public GiaiDoanNamModel setDuLieuGiaiDoanNam(GiaiDoanDuAn giaiDoanDuAn) {
		GiaiDoanNamModel giaiDoanNam = new GiaiDoanNamModel();
		// giai doan nam
		giaiDoanNam.setTenCongTy(giaiDoanDuAn.getTenCongTy() != null ? giaiDoanDuAn.getTenCongTy() : "");
		giaiDoanNam.setNguoiDaiDienPhapLy(giaiDoanDuAn.getNguoiDaiDienPhapLy() != null ? giaiDoanDuAn.getNguoiDaiDienPhapLy() : "");
		giaiDoanNam.setDiaChi(giaiDoanDuAn.getDiaChi() != null ? giaiDoanDuAn.getDiaChi() : "");
		giaiDoanNam.setSoDienThoai(giaiDoanDuAn.getSoDienThoai() != null ? giaiDoanDuAn.getSoDienThoai() : "");
		giaiDoanNam.setEmail(giaiDoanDuAn.getEmail() != null ? giaiDoanDuAn.getEmail() : "");
		return giaiDoanNam;
	}
}
