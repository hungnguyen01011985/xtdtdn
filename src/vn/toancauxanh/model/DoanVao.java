package vn.toancauxanh.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.QuocGiaEnum;
import vn.toancauxanh.gg.model.enums.TrangThaiEnum;

@Entity
@Table(name = "doanvao")
public class DoanVao extends Model<DoanVao> {
	private String tenDoanVao;
	private QuocGiaEnum quocGia;
	private TrangThaiEnum trangThaiTiepDoan = TrangThaiEnum.CHUA_TIEP;
	private String tomTatNoiDungKQ = "";
	private String deXuatCVPhuTrach = "";
	private String yKienChiDao = "";
	private String noiDoanDiTham;
	private int soNguoi;
	private Date thoiGianDenLamViec = new Date();
	private NhanVien nguoiPhuTrach = new NhanVien();
	private List<KeHoachLamViec> listKeHoachLamViec;
	private String link;
	private TepTin taiLieu;
	private TepTin congVanChiDaoUB;
	private boolean checkTaiLieu;
	private ThanhVienDoan thanhVienDoanTemp = new ThanhVienDoan();


	public DoanVao() {

	}

	public String getTenDoanVao() {
		return tenDoanVao;
	}

	public void setTenDoanVao(String tenDoanVao) {
		this.tenDoanVao = tenDoanVao;
	}

	@Enumerated(EnumType.STRING)
	public QuocGiaEnum getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(QuocGiaEnum quocGia) {
		this.quocGia = quocGia;
	}

	public int getSoNguoi() {
		return soNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		this.soNguoi = soNguoi;
	}

	public Date getThoiGianDenLamViec() {
		return thoiGianDenLamViec;
	}

	public void setThoiGianDenLamViec(Date thoiGianDenLamViec) {
		this.thoiGianDenLamViec = thoiGianDenLamViec;
	}

	@ManyToOne
	public NhanVien getNguoiPhuTrach() {
		return nguoiPhuTrach;
	}

	public void setNguoiPhuTrach(NhanVien nguoiPhuTrach) {
		this.nguoiPhuTrach = nguoiPhuTrach;
	}

	public String getNoiDoanDiTham() {
		return noiDoanDiTham;
	}

	public void setNoiDoanDiTham(String noiDoanDiTham) {
		this.noiDoanDiTham = noiDoanDiTham;
	}

	public void setListKeHoachLamViec(List<KeHoachLamViec> listKeHoachLamViec) {
		this.listKeHoachLamViec = listKeHoachLamViec;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@ManyToOne
	public TepTin getTaiLieu() {
		return taiLieu;
	}

	public void setTaiLieu(TepTin taiLieu) {
		this.taiLieu = taiLieu;
	}

	public void setCongVanChiDaoUB(TepTin congVanChiDaoUB) {
		this.congVanChiDaoUB = congVanChiDaoUB;
	}

	@ManyToOne
	public TepTin getCongVanChiDaoUB() {
		return congVanChiDaoUB;
	}

	@Enumerated(EnumType.STRING)
	public TrangThaiEnum getTrangThaiTiepDoan() {
		return trangThaiTiepDoan;
	}

	public void setTrangThaiTiepDoan(TrangThaiEnum trangThaiTiepDoan) {
		this.trangThaiTiepDoan = trangThaiTiepDoan;
	}

	public String getTomTatNoiDungKQ() {
		return tomTatNoiDungKQ;
	}

	public void setTomTatNoiDungKQ(String tomTatNoiDungKQ) {
		this.tomTatNoiDungKQ = tomTatNoiDungKQ;
	}

	public String getDeXuatCVPhuTrach() {
		return deXuatCVPhuTrach;
	}

	public void setDeXuatCVPhuTrach(String deXuatCVPhuTrach) {
		this.deXuatCVPhuTrach = deXuatCVPhuTrach;
	}

	public String getyKienChiDao() {
		return yKienChiDao;
	}

	public void setyKienChiDao(String yKienChiDao) {
		this.yKienChiDao = yKienChiDao;
	}

	public void setCheckTaiLieu(boolean checkTaiLieu) {
		this.checkTaiLieu = checkTaiLieu;
	}

	@Command
	public void uploadFile(@BindingParam("medias") final Object medias) {
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
				tepTin.setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
				tepTin.setMedia(media);
				setTaiLieu(tepTin);
				setCheckTaiLieu(true);
				BindUtils.postNotifyChange(null, null, this, "taiLieu");
				BindUtils.postNotifyChange(null, null, this, "checkTaiLieu");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}

	@Command
	public void deleteFile(@BindingParam("vm") DoanVao ob) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							setTaiLieu(null);
							setCheckTaiLieu(false);
							BindUtils.postNotifyChange(null, null, ob, "taiLieu");
							BindUtils.postNotifyChange(null, null, ob, "checkTaiLieu");
							showNotification("Đã xóa", "", "success");
						}
					}
				});
	}

	@Command
	public void saveDoanVao() {
		DoanVao doanVao = this;
		transactioner().execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				if (!listThanhVienDoan.isEmpty() && listThanhVienDoan != null) {
					for (ThanhVienDoan thanhVienDoan : listThanhVienDoan) {
						thanhVienDoan.setDoanVao(doanVao);
						thanhVienDoan.saveNotShowNotification();
					}
				}
				if (!listXoaThanhVienDoan.isEmpty() && listXoaThanhVienDoan != null) {
					for (ThanhVienDoan thanhVienDoan : listXoaThanhVienDoan) {
						thanhVienDoan.setDoanVao(doanVao);
						thanhVienDoan.setDaXoa(true);
						thanhVienDoan.saveNotShowNotification();
					}
				}
				if (getTaiLieu() != null) {
					getTaiLieu().save();
				}
				save();
				redirectPageList("/cp/quanlydoanvao", null);
			}
		});
	}

	@Command
	public void redirectPageList(@BindingParam("url") String url, @BindingParam("vm") DoanVao vm) {
		Executions.getCurrent().sendRedirect(url);
	}

	@Transient
	public List<KeHoachLamViec> getListKeHoachLamViec() {
		return listKeHoachLamViec;
	}

	@Transient
	public boolean isCheckTaiLieu() {
		return checkTaiLieu;
	}

	@Transient
	public AbstractValidator getValidatorTenDoan() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String tenDoan = (String) ctx.getProperty().getValue();
				if (tenDoan.trim().isEmpty()) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidatorSoNguoi() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				Integer soNguoi = 0;
				try {
					soNguoi = Integer.parseInt((String) ctx.getProperty().getValue());
				} catch (NumberFormatException e) {
					addInvalidMessage(ctx, "Bạn phải nhập số");
				}
				if (soNguoi < 1) {
					addInvalidMessage(ctx, "Số người không được nhỏ hơn 1");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidatorNoiDoanDiTham() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String noiDoanDiTham = (String) ctx.getProperty().getValue();
				if (noiDoanDiTham.trim().isEmpty()) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}

	@Transient
	public ThanhVienDoan getThanhVienDoanTemp() {
		return thanhVienDoanTemp;
	}

	public void setThanhVienDoanTemp(ThanhVienDoan thanhVienDoanTemp) {
		this.thanhVienDoanTemp = thanhVienDoanTemp;
	}

	private List<ThanhVienDoan> listThanhVienDoan = new ArrayList<ThanhVienDoan>();
	private List<ThanhVienDoan> listThanhVienTheoDoan = new ArrayList<ThanhVienDoan>();
	private List<ThanhVienDoan> listTaoMoiThanhVienDoanLuuTam = new ArrayList<ThanhVienDoan>();
	private List<ThanhVienDoan> listXoaThanhVienDoan = new ArrayList<ThanhVienDoan>();

	@Transient
	public List<ThanhVienDoan> getListXoaThanhVienDoan() {
		return listXoaThanhVienDoan;
	}

	public void setListXoaThanhVienDoan(List<ThanhVienDoan> listXoaThanhVienDoan) {
		this.listXoaThanhVienDoan = listXoaThanhVienDoan;
	}

	private boolean flag;

	@Transient
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private boolean bind;

	@Transient
	public List<ThanhVienDoan> getListThanhVienDoan() {
		if (!bind) {
			listThanhVienDoan.addAll(getListThanhVienTheoDoan());
			bind = true;
		} else {
			listThanhVienDoan.addAll(getListTaoMoiThanhVienDoanLuuTam());
			listTaoMoiThanhVienDoanLuuTam.removeAll(getListTaoMoiThanhVienDoanLuuTam());
		}
		return listThanhVienDoan;
	}

	public void setListThanhVienDoan(List<ThanhVienDoan> listThanhVienDoan) {
		this.listThanhVienDoan = listThanhVienDoan;
	}

	@Transient
	public List<ThanhVienDoan> getListThanhVienTheoDoan() {
		if (getId() != null) {
			listThanhVienTheoDoan = find(ThanhVienDoan.class).where(QThanhVienDoan.thanhVienDoan.doanVao.id.eq(getId()))
					.orderBy(QThanhVienDoan.thanhVienDoan.ngaySua.desc()).fetch();
		}
		return listThanhVienTheoDoan;
	}

	@Transient
	public List<ThanhVienDoan> getListTaoMoiThanhVienDoanLuuTam() {
		return listTaoMoiThanhVienDoanLuuTam;
	}

	public void setListTaoMoiThanhVienDoanLuuTam(List<ThanhVienDoan> listTaoMoiThanhVienDoanLuuTam) {
		this.listTaoMoiThanhVienDoanLuuTam = listTaoMoiThanhVienDoanLuuTam;
	}

	@Command
	public void saveThanhVienDoan() {
		if (flag) {
			BindUtils.postNotifyChange(null, null, this, "listThanhVienDoan");
			flag = false;
			BindUtils.postNotifyChange(null, null, this, "flag");
		} else {
			List<ThanhVienDoan> listThanhVienDoan = getListTaoMoiThanhVienDoanLuuTam();
			listThanhVienDoan.add(this.getThanhVienDoanTemp());
			System.out.println(this.getThanhVienDoanTemp().getQuocGia());
			BindUtils.postNotifyChange(null, null, this, "listThanhVienDoan");
		}
		reset();
	}

	@Command
	public void deleteThanhVienDoan(@BindingParam("item") final ThanhVienDoan thanhVienDoan,
			@BindingParam("vm") final DoanVao doanVao) {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (Messagebox.ON_OK.equals(event.getName())) {
								showNotification("Xóa thành công!", "", "success");
								if (thanhVienDoan != null) {
									doanVao.getListThanhVienDoan().remove(thanhVienDoan);
									if (!thanhVienDoan.noId()) {
										listXoaThanhVienDoan.add(thanhVienDoan);
									}
								}
								BindUtils.postNotifyChange(null, null, doanVao, "listThanhVienDoan");
								BindUtils.postNotifyChange(null, null, this, "listXoaThanhVienDoan");
							}
						}
					});
		}
	}
	
	public void reset(){
		thanhVienDoanTemp = new ThanhVienDoan();
		BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
		Clients.evalJavaScript("getFocus()");
	}

	@Command
	public void reset(@BindingParam("vm") final DoanVao doanVao) {
		thanhVienDoanTemp = new ThanhVienDoan();
		flag = false;
		BindUtils.postNotifyChange(null, null, doanVao, "flag");
		BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
		Clients.evalJavaScript("getFocus()");
	}

	@Command
	public void editThanhVienDoan(@BindingParam("item") ThanhVienDoan thanhVienDoan) {
		thanhVienDoanTemp = thanhVienDoan;
		flag = true;
		BindUtils.postNotifyChange(null, null, this, "flag");
		BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
		Clients.evalJavaScript("getFocus()");
	}

	@Command
	public void saveDanhSachThanhVienDoan(@BindingParam("wdn") final Window wdn) {
		showNotification("Lưu thành công!", "", "success");
		wdn.detach();
	}
	
	@Transient
	public List<QuocGiaEnum> getListQuocGia() {
		List<QuocGiaEnum> list = new ArrayList<QuocGiaEnum>();
		list.add(QuocGiaEnum.AFGHANISTAN);
		list.add(QuocGiaEnum.AI_CAP);
		list.add(QuocGiaEnum.ALBANIA);
		list.add(QuocGiaEnum.ALGIERI);
		list.add(QuocGiaEnum.ANDORRA);
		list.add(QuocGiaEnum.ANGOLA);
		list.add(QuocGiaEnum.VUONG_QUOC_ANH);
		list.add(QuocGiaEnum.ANTIGUA);
		list.add(QuocGiaEnum.AO);
		list.add(QuocGiaEnum.A_RAP);
		list.add(QuocGiaEnum.ARMENIA);
		list.add(QuocGiaEnum.ARGHENTINA);
		list.add(QuocGiaEnum.AZERBAIJAN);
		list.add(QuocGiaEnum.AN_DO);
		list.add(QuocGiaEnum.BAHAMAS);
		list.add(QuocGiaEnum.BAHRAIN);
		list.add(QuocGiaEnum.BA_LAN);
		list.add(QuocGiaEnum.BANGLADESH);
		list.add(QuocGiaEnum.BARBADOS);
		list.add(QuocGiaEnum.BELARUS);
		list.add(QuocGiaEnum.BELIZE);
		list.add(QuocGiaEnum.BENIN);
		list.add(QuocGiaEnum.BHUTAN);
		list.add(QuocGiaEnum.BI);
		list.add(QuocGiaEnum.BOLIVIA);
		list.add(QuocGiaEnum.BOSNA_AND_HERCEGOVIA);
		list.add(QuocGiaEnum.BOTSWANA);
		list.add(QuocGiaEnum.BO_DAO_NHA);
		list.add(QuocGiaEnum.BO_BIEN_NGA);
		list.add(QuocGiaEnum.BRASIL);
		list.add(QuocGiaEnum.BRUNEI);
		list.add(QuocGiaEnum.BULGARIA);
		list.add(QuocGiaEnum.BURKIRA_FASO);
		list.add(QuocGiaEnum.BURUNDI);
		list.add(QuocGiaEnum.CABO_VERDE);
		list.add(QuocGiaEnum.UAE);
		list.add(QuocGiaEnum.CAMEROON);
		list.add(QuocGiaEnum.CAMPUCHIA);
		list.add(QuocGiaEnum.CANADA);
		list.add(QuocGiaEnum.CHILE);
		list.add(QuocGiaEnum.COLOMBIA);
		list.add(QuocGiaEnum.COMOROS);
		list.add(QuocGiaEnum.CONGO);
		list.add(QuocGiaEnum.CONG_HOA_CONGO);
		list.add(QuocGiaEnum.COSTA_RICA);
		list.add(QuocGiaEnum.CROATIA);
		list.add(QuocGiaEnum.CUBA);
		list.add(QuocGiaEnum.DJIBOUTI);
		list.add(QuocGiaEnum.DOMINICA);
		list.add(QuocGiaEnum.CONG_HOA_DOMINICA);
		list.add(QuocGiaEnum.DAN_MACH);
		list.add(QuocGiaEnum.DONG_TIMOR);
		list.add(QuocGiaEnum.DUC);
		list.add(QuocGiaEnum.ECUADOR);
		list.add(QuocGiaEnum.EL_SALVADOR);
		list.add(QuocGiaEnum.ERITREA);
		list.add(QuocGiaEnum.ESTONIA);
		list.add(QuocGiaEnum.FIJI);
		list.add(QuocGiaEnum.GABON);
		list.add(QuocGiaEnum.GAMBIA);
		list.add(QuocGiaEnum.GHANA);
		list.add(QuocGiaEnum.GRENADA);
		list.add(QuocGiaEnum.GRUZIA);
		list.add(QuocGiaEnum.GUATEMALA);
		list.add(QuocGiaEnum.GUINE_BISSAU);
		list.add(QuocGiaEnum.GUINE_XICH_DAO);
		list.add(QuocGiaEnum.GUINEE);
		list.add(QuocGiaEnum.GUYANA);
		list.add(QuocGiaEnum.HAITI);
		list.add(QuocGiaEnum.HA_LAN);
		list.add(QuocGiaEnum.HAN_QUOC);
		list.add(QuocGiaEnum.HOA_KY);
		list.add(QuocGiaEnum.HONDURAS);
		list.add(QuocGiaEnum.HUNGARY);
		list.add(QuocGiaEnum.HY_LAP);
		list.add(QuocGiaEnum.ICELAND);
		list.add(QuocGiaEnum.INDONESIA);
		list.add(QuocGiaEnum.IRAN);
		list.add(QuocGiaEnum.IRAQ);
		list.add(QuocGiaEnum.IRELAND);
		list.add(QuocGiaEnum.ISRAEL);
		list.add(QuocGiaEnum.JAMAICA);
		list.add(QuocGiaEnum.JORDAN);
		list.add(QuocGiaEnum.KAZAKHSTAN);
		list.add(QuocGiaEnum.KENYA);
		list.add(QuocGiaEnum.KIRIBATI);
		list.add(QuocGiaEnum.KOSOVO);
		list.add(QuocGiaEnum.KUWAIT);
		list.add(QuocGiaEnum.KYRGYZSTAN);
		list.add(QuocGiaEnum.LAO);
		list.add(QuocGiaEnum.LATVIA);
		list.add(QuocGiaEnum.LESOTHO);
		list.add(QuocGiaEnum.LIBAN);
		list.add(QuocGiaEnum.LIBERIA);
		list.add(QuocGiaEnum.LIBYA);
		list.add(QuocGiaEnum.LIECHTENSTEIN);
		list.add(QuocGiaEnum.LITVA);
		list.add(QuocGiaEnum.LUXEMBOURG);
		list.add(QuocGiaEnum.MACEDONIA);
		list.add(QuocGiaEnum.MADAGASCAR);
		list.add(QuocGiaEnum.MALAWI);
		list.add(QuocGiaEnum.MALAYSIA);
		list.add(QuocGiaEnum.MALDIVES);
		list.add(QuocGiaEnum.MALI);
		list.add(QuocGiaEnum.MALTA);
		list.add(QuocGiaEnum.MAROC);
		list.add(QuocGiaEnum.MARSHALL);
		list.add(QuocGiaEnum.MAURITANIE);
		list.add(QuocGiaEnum.MAURITIUS);
		list.add(QuocGiaEnum.MEXICO);
		list.add(QuocGiaEnum.MICRONESIA);
		list.add(QuocGiaEnum.MOLDOVA);
		list.add(QuocGiaEnum.MONACO);
		list.add(QuocGiaEnum.MONG_CO);
		list.add(QuocGiaEnum.MONTENEGRO);
		list.add(QuocGiaEnum.MOZAMBIQUE);
		list.add(QuocGiaEnum.MYANMAR);
		list.add(QuocGiaEnum.NAMIBIA);
		list.add(QuocGiaEnum.NAM_SUDAN);
		list.add(QuocGiaEnum.NAM_PHI);
		list.add(QuocGiaEnum.NAURU);
		list.add(QuocGiaEnum.NA_UY);
		list.add(QuocGiaEnum.NEPAL);
		list.add(QuocGiaEnum.NEW_ZEALAND);
		list.add(QuocGiaEnum.NICARAGUA);
		list.add(QuocGiaEnum.NIGER);
		list.add(QuocGiaEnum.NIGERIA);
		list.add(QuocGiaEnum.NGA);
		list.add(QuocGiaEnum.NHAT_BAN);
		list.add(QuocGiaEnum.OMAN);
		list.add(QuocGiaEnum.PAKISTAN);
		list.add(QuocGiaEnum.PALAU);
		list.add(QuocGiaEnum.PALESTINE);
		list.add(QuocGiaEnum.PANAMA);
		list.add(QuocGiaEnum.PAPUA_NEW_GUINEA);
		list.add(QuocGiaEnum.PARAGUAY);
		list.add(QuocGiaEnum.PERU);
		list.add(QuocGiaEnum.PHAP);
		list.add(QuocGiaEnum.PHAN_LAN);
		list.add(QuocGiaEnum.PHILIPPINES);
		list.add(QuocGiaEnum.QUATAR);
		list.add(QuocGiaEnum.ROMANIA);
		list.add(QuocGiaEnum.RWANDA);
		list.add(QuocGiaEnum.SAINT_KITTS_NEVIS);
		list.add(QuocGiaEnum.SAINT_LUCIA);
		list.add(QuocGiaEnum.SAINT_VINCENT_GRENADINES);
		list.add(QuocGiaEnum.SAMOA);
		list.add(QuocGiaEnum.SAN_MARINO);
		list.add(QuocGiaEnum.SAO_TOME_PRINCIPE);
		list.add(QuocGiaEnum.SEC);
		list.add(QuocGiaEnum.SENEGAL);
		list.add(QuocGiaEnum.SERBIA);
		list.add(QuocGiaEnum.SEYCHELLES);
		list.add(QuocGiaEnum.SIERRA_LEONE);
		list.add(QuocGiaEnum.SINGAPORE);
		list.add(QuocGiaEnum.SIP);
		list.add(QuocGiaEnum.SLOVAKIA);
		list.add(QuocGiaEnum.SLOVENIA);
		list.add(QuocGiaEnum.SOLOMON);
		list.add(QuocGiaEnum.SOMALIA);
		list.add(QuocGiaEnum.SRI_LANKA);
		list.add(QuocGiaEnum.SUDAN);
		list.add(QuocGiaEnum.SURINAME);
		list.add(QuocGiaEnum.SWAZILAND);
		list.add(QuocGiaEnum.SYRIA);
		list.add(QuocGiaEnum.TAJIKISTAN);
		list.add(QuocGiaEnum.TAY_BAN_NHA);
		list.add(QuocGiaEnum.TCHAD);
		list.add(QuocGiaEnum.THAI_LAN);
		list.add(QuocGiaEnum.THO_NHI_KY);
		list.add(QuocGiaEnum.THUY_DIEN);
		list.add(QuocGiaEnum.THUY_SI);
		list.add(QuocGiaEnum.TOGO);
		list.add(QuocGiaEnum.TONGA);
		list.add(QuocGiaEnum.TRIEU_TIEN);
		list.add(QuocGiaEnum.TRINIDAD_VA_TOBAGO);
		list.add(QuocGiaEnum.TRUNG_QUOC);
		list.add(QuocGiaEnum.TRUNG_PHI);
		list.add(QuocGiaEnum.TUNISIA);
		list.add(QuocGiaEnum.TURKMENISTAN);
		list.add(QuocGiaEnum.TUVALU);
		list.add(QuocGiaEnum.UC);
		list.add(QuocGiaEnum.UGANDA);
		list.add(QuocGiaEnum.UKRAINA);
		list.add(QuocGiaEnum.URUGUAY);
		list.add(QuocGiaEnum.UZBEKISTAN);
		list.add(QuocGiaEnum.VANUATU);
		list.add(QuocGiaEnum.VATICAN);
		list.add(QuocGiaEnum.VENEZUELA);
		list.add(QuocGiaEnum.VIET_NAM);
		list.add(QuocGiaEnum.Y);
		list.add(QuocGiaEnum.YEMEN);
		list.add(QuocGiaEnum.ZAMBIA);
		list.add(QuocGiaEnum.ZIMBABWE);
		return list;
	}

	@Transient
	public List<QuocGiaEnum> getListQuocGiaAndNull() {
		List<QuocGiaEnum> list = new ArrayList<QuocGiaEnum>();
		list.add(null);
		list.addAll(getListQuocGia());
		return list;
	}
	
}
