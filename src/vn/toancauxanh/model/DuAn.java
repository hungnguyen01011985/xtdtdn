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

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.DonViChuTri;
import vn.toancauxanh.gg.model.enums.DonViTuVan;
import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.KhaNangDauTu;
import vn.toancauxanh.gg.model.enums.MucDoUuTien;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;
import vn.toancauxanh.gg.model.enums.QuyMoDuAn;

@Entity
@Table(name = "duan")
public class DuAn extends Model<DuAn> {
	private String tenDuAn;
	private LinhVucDuAn linhVuc;
	private String diaDiem;
	private QuyMoDuAn quyMoDuAn;
	private double tongVonDauTu;
	private String mucTieuDuAn;
	private int dienTichSuDungDat;
	private String mucDoCanhTranh;
	private MucDoUuTien mucDoUuTien;
	private KhaNangDauTu khaNangDauTu;
	private NhanVien nguoiPhuTrach = new NhanVien();
	private GiaiDoanXucTien giaiDoanXucTien = GiaiDoanXucTien.GIAI_DOAN_MOT;
	private Date ngayBatDauXucTien = new Date();
	private GiaiDoanDuAn giaiDoanDuAn;
	private GiaoViec giaoViec = new GiaoViec();
	private TepTin taiLieuNDT;
	private String idNguoiLienQuan = "";
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

	@Enumerated(EnumType.STRING)
	public QuyMoDuAn getQuyMoDuAn() {
		return quyMoDuAn;
	}

	public void setQuyMoDuAn(QuyMoDuAn quyMoDuAn) {
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

	@Transient
	public List<QuyMoDuAn> getListQuyMoDuAn() {
		List<QuyMoDuAn> list = new ArrayList<QuyMoDuAn>();
		list.add(QuyMoDuAn.QUY_MO_LON);
		list.add(QuyMoDuAn.QUY_MO_VUA);
		list.add(QuyMoDuAn.QUY_MO_NHO);
		return list;
	}

	@Transient
	public List<DonViChuTri> getListDonViChuTri() {
		List<DonViChuTri> list = new ArrayList<DonViChuTri>();
		list.add(DonViChuTri.TRUNG_TAM_PHAT_TRIEN_QUY_DAT);
		return list;
	}

	@Transient
	public List<DonViTuVan> getListDonViTuVan() {
		List<DonViTuVan> list = new ArrayList<DonViTuVan>();
		list.add(DonViTuVan.VIEN_QUY_HOACH_DO_THI_DA_NANG);
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
		variables.put("model", this);
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
	public void goBack(@BindingParam("task") final String task){
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		if (task != null) {
			variables.put("goTask", task);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}
	
	@Command
	public void goNext(@BindingParam("task") final String task){
		Map<String, Object> variables = new HashMap<>();
		variables.put("model", this);
		if (task != null) {
			variables.put("goTask", task);
			System.out.println("zooo");
		}
		
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);
	}

	@Transient
	public String getSrc() {
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(getGiaiDoanXucTien())) {
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
		return null;
	}

	private String srcGiaiDoan4;

	@Transient
	public String getSrcGiaiDoan4() {
		return srcGiaiDoan4;
	}

	public void setSrcGiaiDoan4(String srcGiaiDoan4) {
		this.srcGiaiDoan4 = srcGiaiDoan4;
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
				Double vonDauTu = 0d;
				try {
					vonDauTu = Double.parseDouble((String) ctx.getProperty().getValue());
				} catch (NumberFormatException e) {
					addInvalidMessage(ctx, "Bạn phải nhập số");
				}

				if (vonDauTu < 0) {
					addInvalidMessage(ctx, text + " phải lớn hơn 0");
					rs = false;
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
	
}
