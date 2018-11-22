package vn.toancauxanh.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.NoiDungCongViec;
import vn.toancauxanh.gg.model.enums.QuocGiaEnum;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.gg.model.enums.TrangThaiTiepDoanEnum;

@Entity
@Table(name = "doanvao")
public class DoanVao extends Model<DoanVao> {
	private String tenDoanVao;
	private QuocGiaEnum quocGia;
	private TrangThaiTiepDoanEnum trangThaiTiepDoan = TrangThaiTiepDoanEnum.CHUA_TIEP;
	private String tomTatNoiDungKQ = "";
	private String deXuatCVPhuTrach = "";
	private String yKienChiDao = "";
	private String noiDoanDiTham;
//	@Lob
	private String link;
	private int soNguoi;
	private Date thoiGianDenLamViec = new Date();
	private NhanVien nguoiPhuTrach = new NhanVien();
	private List<KeHoachLamViec> listKeHoachLamViec;
	private boolean checkTaiLieu;
	private ThanhVienDoan thanhVienDoanTemp = new ThanhVienDoan();
	private List<TepTin> tepTins = new ArrayList<TepTin>();
	private TepTin congVanChiDao;


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
	
	@ManyToOne
	public TepTin getCongVanChiDao() {
		return congVanChiDao;
	}

	public void setCongVanChiDao(TepTin congVanChiDao) {
		this.congVanChiDao = congVanChiDao;
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

	@Enumerated(EnumType.STRING)
	public TrangThaiTiepDoanEnum getTrangThaiTiepDoan() {
		return trangThaiTiepDoan;
	}

	public void setTrangThaiTiepDoan(TrangThaiTiepDoanEnum trangThaiTiepDoan) {
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "doanvao_teptin", joinColumns = {
			@JoinColumn(name = "doanvao_id") }, inverseJoinColumns = { @JoinColumn(name = "teptin_id") })
	public List<TepTin> getTepTins() {
		return tepTins;
	}

	public void setTepTins(List<TepTin> tepTins) {
		this.tepTins = tepTins;
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
					this.getTepTins().add(tepTin);
					this.getTepTins().forEach(obj -> {
						try {
							obj.saveFileTepTin();
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					BindUtils.postNotifyChange(null, null, this, "tepTins");
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
		DoanVao doanVao = this;
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							doanVao.getTepTins().remove(index);
							BindUtils.postNotifyChange(null, null, this, "tepTins");
							showNotification("Đã xóa", "", "success");
						}
					}
				});
	}

	@Command
	public void saveDoanVao() {
		this.getTepTins().forEach(item -> {
			item.saveNotShowNotification();
		});
		if (this.getTomTatNoiDungKQ() != null && !"".equals(this.getTomTatNoiDungKQ())) {
			this.setTrangThaiTiepDoan(TrangThaiTiepDoanEnum.DA_TIEP);
		}
		save();
		if (listThanhVienDoan != null && !listThanhVienDoan.isEmpty()) {
			listThanhVienDoan.forEach(item -> {
				item.setDoanVao(this);
				item.saveNotShowNotification();
			});
		}
		if (listXoaThanhVienDoan != null && !listXoaThanhVienDoan.isEmpty()) {
			listXoaThanhVienDoan.forEach(item -> {
				item.setDoanVao(this);
				item.setDaXoa(true);
				item.saveNotShowNotification();
			});
		}

		if (listGiaoViec != null || !listGiaoViec.isEmpty()) {
			for (GiaoViec congViec : listGiaoViec) {
				checkCongViec(congViec);
				if (checkNotAllNull && checkAllNull) {
					saveGiaoViec(congViec);
				}
				resetCheck();
			}
			
		}
		redirectPageList("/cp/quanlydoanvao", null);
	}
	
	public void saveGiaoViec(GiaoViec giaoViec) {
		giaoViec.getTaiLieu().saveNotShowNotification();
		giaoViec.setDoanVao(this);
		giaoViec.setNguoiGiaoViec(core().getNhanVien());
		giaoViec.setLoaiCongViec(LoaiCongViec.DOAN_VAO);
		thongBao(this, giaoViec, giaoViec.getNguoiDuocGiao(), giaoViec.getNguoiGiaoViec(), giaoViec.getNoiDungCongViec().getText());
		giaoViec.getNguoiDuocGiao().saveNotShowNotification();
		giaoViec.saveNotShowNotification();
	}
	
	public void thongBao(DoanVao doanVao, GiaoViec giaoViec, NhanVien nguoiNhan, NhanVien nguoiGui, String tenCongViec) {
		ThongBao thongBao = new ThongBao();
		if (giaoViec.noId()) {
			thongBao.setNoiDung(nguoiNhan.getHoVaTen() + "@ có công việc mới @" + tenCongViec + "@ của đoàn vào @" + doanVao.getTenDoanVao());
			thongBao.setNguoiNhan(nguoiNhan);
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			}
			thongBao.setIdObject(doanVao.getId());
			thongBao.setLoaiThongBao(LoaiThongBao.CONG_VIEC_MOI);
			thongBao.saveNotShowNotification();
		}
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
				if (soNguoi < 0) {
					addInvalidMessage(ctx, "Số người không được nhỏ hơn 0");
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
			soThanhVienDoan = listThanhVienDoan.size();
			BindUtils.postNotifyChange(null, null, this, "soThanhVienDoan");
			bind = true;
		} else {
			listThanhVienDoan.addAll(getListTaoMoiThanhVienDoanLuuTam());
			listTaoMoiThanhVienDoanLuuTam.removeAll(getListTaoMoiThanhVienDoanLuuTam());
			soThanhVienDoan = listThanhVienDoan.size();
			BindUtils.postNotifyChange(null, null, this, "soThanhVienDoan");
		}
		return listThanhVienDoan;
	}

	public void setListThanhVienDoan(List<ThanhVienDoan> listThanhVienDoan) {
		this.listThanhVienDoan = listThanhVienDoan;
	}
	
	@Command
	public void notifyDoanVao(@BindingParam("notify") final DoanVao doanVao, @BindingParam("attr") final String attr){
		BindUtils.postNotifyChange(null, null, doanVao, attr);
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
	public void redirectXemChiTietDoanVao(@BindingParam("id") Long id) {
		String url = "/cp/quanlydoanvao/detail/";
		Executions.sendRedirect(url.concat(id.toString()));
	}
	
	private int soThanhVienDoan = 0;
	
	@Transient
	public int getSoThanhVienDoan() {
		return soThanhVienDoan;
	}

	public void setSoThanhVienDoan(int soThanhVienDoan) {
		this.soThanhVienDoan = soThanhVienDoan;
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
	
	//======================================================================================

	private GiaoViec titleNhanSuLamViec = new GiaoViec(NoiDungCongViec.TITLE_NHAN_SU_LAM_VIEC, new NhanVien(), null, null, null);
	private GiaoViec congViecNguoiDuocPhanCong = new GiaoViec(NoiDungCongViec.CONG_VIEC_NGUOI_DUOC_PHAN_CONG, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecChuyenVien = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUYEN_VIEN, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec titleCongTacHauCan = new GiaoViec(NoiDungCongViec.TITLE_CONG_TAC_HAU_CAN, new NhanVien(), null, null, null);
	private GiaoViec congViecChuanBiPhongHop = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_PHONG_HOP, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecChuanBiHoaQua = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_HOA_QUA, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecChuanBiThietBi = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_THIET_BI, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecChuanBiTaiLieu = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_TAI_LIEU, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec titleCongTacKhac = new GiaoViec(NoiDungCongViec.TITLE_CONG_TAC_KHAC, new NhanVien(), null, null, null);
	private GiaoViec congViecXayDungChuongTrinh = new GiaoViec(NoiDungCongViec.CONG_VIEC_XAY_DUNG_CHUONG_TRINH, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecChuanBiBaiGioiThieu = new GiaoViec(NoiDungCongViec.CONG_VIEC_CHUAN_BI_BAI_GIOI_THIEU, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecXacNhanLaiThongTin = new GiaoViec(NoiDungCongViec.CONG_VIEC_XAC_NHAN_LAI_THONG_TIN, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecGhiBienBan = new GiaoViec(NoiDungCongViec.CONG_VIEC_GHI_BIEN_BAN, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	private GiaoViec congViecKiemTraLaiCongTacChuanBi = new GiaoViec(NoiDungCongViec.CONG_VIEC_KIEM_TRA_LAI_CONG_TAC_CHUAN_BI, new NhanVien(), null, TrangThaiGiaoViec.CHUA_LAM, null);
	
	@Transient
	public GiaoViec getTitleNhanSuLamViec() {
		return titleNhanSuLamViec;
	}

	@Transient
	public GiaoViec getCongViecNguoiDuocPhanCong() {
		return congViecNguoiDuocPhanCong;
	}

	@Transient
	public GiaoViec getCongViecChuyenVien() {
		return congViecChuyenVien;
	}

	@Transient
	public GiaoViec getTitleCongTacHauCan() {
		return titleCongTacHauCan;
	}

	@Transient
	public GiaoViec getCongViecChuanBiPhongHop() {
		return congViecChuanBiPhongHop;
	}

	@Transient
	public GiaoViec getCongViecChuanBiHoaQua() {
		return congViecChuanBiHoaQua;
	}

	@Transient
	public GiaoViec getCongViecChuanBiThietBi() {
		return congViecChuanBiThietBi;
	}

	@Transient
	public GiaoViec getCongViecChuanBiTaiLieu() {
		return congViecChuanBiTaiLieu;
	}

	@Transient
	public GiaoViec getTitleCongTacKhac() {
		return titleCongTacKhac;
	}

	@Transient
	public GiaoViec getCongViecXayDungChuongTrinh() {
		return congViecXayDungChuongTrinh;
	}

	@Transient
	public GiaoViec getCongViecChuanBiBaiGioiThieu() {
		return congViecChuanBiBaiGioiThieu;
	}

	@Transient
	public GiaoViec getCongViecXacNhanLaiThongTin() {
		return congViecXacNhanLaiThongTin;
	}

	@Transient
	public GiaoViec getCongViecGhiBienBan() {
		return congViecGhiBienBan;
	}

	@Transient
	public GiaoViec getCongViecKiemTraLaiCongTacChuanBi() {
		return congViecKiemTraLaiCongTacChuanBi;
	}


	public void setTitleNhanSuLamViec(GiaoViec titleNhanSuLamViec) {
		this.titleNhanSuLamViec = titleNhanSuLamViec;
	}

	public void setCongViecNguoiDuocPhanCong(GiaoViec congViecNguoiDuocPhanCong) {
		this.congViecNguoiDuocPhanCong = congViecNguoiDuocPhanCong;
	}

	public void setCongViecChuyenVien(GiaoViec congViecChuyenVien) {
		this.congViecChuyenVien = congViecChuyenVien;
	}

	public void setTitleCongTacHauCan(GiaoViec titleCongTacHauCan) {
		this.titleCongTacHauCan = titleCongTacHauCan;
	}

	public void setCongViecChuanBiPhongHop(GiaoViec congViecChuanBiPhongHop) {
		this.congViecChuanBiPhongHop = congViecChuanBiPhongHop;
	}

	public void setCongViecChuanBiHoaQua(GiaoViec congViecChuanBiHoaQua) {
		this.congViecChuanBiHoaQua = congViecChuanBiHoaQua;
	}

	public void setCongViecChuanBiThietBi(GiaoViec congViecChuanBiThietBi) {
		this.congViecChuanBiThietBi = congViecChuanBiThietBi;
	}

	public void setCongViecChuanBiTaiLieu(GiaoViec congViecChuanBiTaiLieu) {
		this.congViecChuanBiTaiLieu = congViecChuanBiTaiLieu;
	}

	public void setTitleCongTacKhac(GiaoViec titleCongTacKhac) {
		this.titleCongTacKhac = titleCongTacKhac;
	}

	public void setCongViecXayDungChuongTrinh(GiaoViec congViecXayDungChuongTrinh) {
		this.congViecXayDungChuongTrinh = congViecXayDungChuongTrinh;
	}


	public void setCongViecChuanBiBaiGioiThieu(GiaoViec congViecChuanBiBaiGioiThieu) {
		this.congViecChuanBiBaiGioiThieu = congViecChuanBiBaiGioiThieu;
	}

	public void setCongViecXacNhanLaiThongTin(GiaoViec congViecXacNhanLaiThongTin) {
		this.congViecXacNhanLaiThongTin = congViecXacNhanLaiThongTin;
	}

	public void setCongViecGhiBienBan(GiaoViec congViecGhiBienBan) {
		this.congViecGhiBienBan = congViecGhiBienBan;
	}

	public void setCongViecKiemTraLaiCongTacChuanBi(GiaoViec congViecKiemTraLaiCongTacChuanBi) {
		this.congViecKiemTraLaiCongTacChuanBi = congViecKiemTraLaiCongTacChuanBi;
	}
	
	private boolean checkNotAllNull = true;
	private boolean checkAllNull = false;
	
	public void checkCongViec(final GiaoViec giaoViec) {
		if (giaoViec.getNguoiDuocGiao() == null) {
			giaoViec.setNguoiDuocGiao(new NhanVien());
		}
		if (giaoViec.getHanThucHien() == null && "".equals(giaoViec.getNguoiDuocGiao().getHoVaTen())) {
			// pass
		} else {
			if ((giaoViec.getHanThucHien() != null && "".equals(giaoViec.getNguoiDuocGiao().getHoVaTen()))
					|| (giaoViec.getHanThucHien() == null && !"".equals(giaoViec.getNguoiDuocGiao().getHoVaTen()))) {
				checkNotAllNull = false;
			} else {
				checkAllNull = true;
			}
		}
	}
	
	public void resetCheck() {
		checkNotAllNull = true;
		checkAllNull = false;
	}
	
	private List<GiaoViec> listGiaoViec = new ArrayList<GiaoViec>();

	@Transient
	public List<GiaoViec> getListGiaoViec() {
		listGiaoViec.add(congViecNguoiDuocPhanCong);
		listGiaoViec.add(congViecChuyenVien);
		listGiaoViec.add(congViecChuanBiPhongHop);
		listGiaoViec.add(congViecChuanBiHoaQua);
		listGiaoViec.add(congViecChuanBiThietBi);
		listGiaoViec.add(congViecChuanBiTaiLieu);
		listGiaoViec.add(congViecGhiBienBan);
		listGiaoViec.add(congViecXayDungChuongTrinh);
		listGiaoViec.add(congViecChuanBiBaiGioiThieu);
		listGiaoViec.add(congViecXacNhanLaiThongTin);
		listGiaoViec.add(congViecGhiBienBan);
		listGiaoViec.add(congViecKiemTraLaiCongTacChuanBi);
		return listGiaoViec;
	}
	
	
	public void setListGiaoViec(List<GiaoViec> listGiaoViec) {
		this.listGiaoViec = listGiaoViec;
	}

	@Command
	public void saveKeHoachLamViec(@BindingParam("doanVao") final DoanVao doanVao,
			@BindingParam("wdn") final Window wdn) {
		for (GiaoViec item : getListGiaoViec()) {
			checkCongViec(item);
		}
		if (!checkNotAllNull || !checkAllNull) {
			showNotification("", "Dữ liệu nhập vào chưa đúng. Vui lòng nhập lại", "danger");
			resetCheck();
		} else {
			resetCheck();
			showNotification("Lưu thành công!", "", "success");
			wdn.detach();
		}
	}
	
	private List<GiaoViec> listGiaoViecTheoDoan = new ArrayList<GiaoViec>();
	
	@Transient
	public List<GiaoViec> getListGiaoViecTheoDoan() {
		return listGiaoViecTheoDoan;
	}

	public void setListGiaoViecTheoDoan(List<GiaoViec> listGiaoViecTheoDoan) {
		this.listGiaoViecTheoDoan = listGiaoViecTheoDoan;
	}

	@Transient
	public void loadListGiaoViecTheoDoan() {
		for (GiaoViec giaoViec : getListGiaoViec()) {
			for (GiaoViec item : listGiaoViecTheoDoan) {
				if (item.getNoiDungCongViec().equals(giaoViec.getNoiDungCongViec())) {
					switch (item.getNoiDungCongViec()) {
					case CONG_VIEC_NGUOI_DUOC_PHAN_CONG:
						congViecNguoiDuocPhanCong = item;
						break;
					case CONG_VIEC_CHUYEN_VIEN:
						congViecChuyenVien = item;
						break;
					case CONG_VIEC_CHUAN_BI_PHONG_HOP:
						congViecChuanBiPhongHop = item;
						break;
					case CONG_VIEC_CHUAN_BI_HOA_QUA:
						congViecChuanBiHoaQua = item;
						break;
					case CONG_VIEC_CHUAN_BI_THIET_BI:
						congViecChuanBiThietBi = item;
						break;
					case CONG_VIEC_CHUAN_BI_TAI_LIEU:
						congViecChuanBiTaiLieu = item;
						break;
					case CONG_VIEC_XAY_DUNG_CHUONG_TRINH:
						congViecXayDungChuongTrinh = item;
						break;
					case CONG_VIEC_CHUAN_BI_BAI_GIOI_THIEU:
						congViecChuanBiBaiGioiThieu = item;
						break;
					case CONG_VIEC_XAC_NHAN_LAI_THONG_TIN:
						congViecXacNhanLaiThongTin = item;
						break;
					case CONG_VIEC_GHI_BIEN_BAN:
						congViecGhiBienBan = item;
						break;
					case CONG_VIEC_KIEM_TRA_LAI_CONG_TAC_CHUAN_BI:
						congViecKiemTraLaiCongTacChuanBi = item;
						break;
					default:
						break;
					}
				}
			}
		}
	}
}
