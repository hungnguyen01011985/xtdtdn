package vn.toancauxanh.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
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

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.KhaNangDauTu;
import vn.toancauxanh.gg.model.enums.MucDoUuTien;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;

@Entity
@Table(name = "duan")
public class DuAn extends Model<DuAn> {
	private String tenDuAn;
	private String diaDiem;
	private String quyMoDuAn;
	private String idNguoiLienQuan = "";
	//@Lob
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
		BindUtils.postNotifyChange(null, null, duAn, "*");
	}

	@Transient
	public AbstractValidator getValidator() {
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
					if (checkDate != null && endDate != null && checkDate.compareTo(endDate) > 0) {
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
					if (dateStart != null && checkDate != null && dateStart.compareTo(checkDate) > 0) {
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
		this.save();
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
					tepTin.setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
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
				tepTin.setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
				tepTin.setMedia(media);
				giaiDoanDuAn.getTepTins().set(index, tepTin);
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
					BindUtils.postNotifyChange(null, null, duAn , "*");
				}
			}
		});
	}
}
