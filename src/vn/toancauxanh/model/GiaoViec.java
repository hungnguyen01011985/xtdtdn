package vn.toancauxanh.model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

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
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.rest.model.CongViecModel;

@Entity
@Table(name = "giaoviec")
public class GiaoViec extends Model<GiaoViec> {
	private DuAn duAn;
	private DoanVao doanVao;
	private String tenCongViec;
	@Lob
	private String yKienChiDao;
	@Lob
	private String ketQua;
	@Lob
	private String ghiChu;
	private NhanVien nguoiGiaoViec = new NhanVien();
	private NhanVien nguoiDuocGiao = new NhanVien();
	private Date ngayGiao = new Date();
	private Date hanThucHien;
	private Date ngayHoanThanh;
	private GiaiDoanXucTien giaiDoanXucTien;
	private TrangThaiGiaoViec trangThaiGiaoViec = TrangThaiGiaoViec.CHUA_LAM;
	private TepTin taiLieu = new TepTin();
	private TepTin taiLieuKetQua;
	private LoaiCongViec loaiCongViec;
	private String tenNhiemVu;
	private PhongBan phongBan;
	private LoaiCongViecKeHoach cha;
	
	public GiaoViec() {
	}

	public String getTenNhiemVu() {
		return tenNhiemVu;
	}

	public void setTenNhiemVu(String tenNhiemVu) {
		this.tenNhiemVu = tenNhiemVu;
	}

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
	
	@ManyToOne
	public LoaiCongViecKeHoach getCha() {
		return cha;
	}

	public void setCha(LoaiCongViecKeHoach cha) {
		this.cha = cha;
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
		return taiLieuKetQua;
	}

	public void setTaiLieuKetQua(TepTin taiLieuKetQua) {
		this.taiLieuKetQua = taiLieuKetQua;
	}
	
	@Enumerated(EnumType.STRING)
	public LoaiCongViec getLoaiCongViec() {
		return loaiCongViec;
	}

	public void setLoaiCongViec(LoaiCongViec loaiCongViec) {
		this.loaiCongViec = loaiCongViec;
	}

	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	@ManyToOne
	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	@Command
	public void saveGiaoViec(@BindingParam("vmArgs") final Object ob, @BindingParam("attr") final String attr,
			@BindingParam("vm") final Object vm, @BindingParam("wdn") final Window wd) {
		wd.detach();
		if (taiLieu != null) {
			taiLieu.saveNotShowNotification();
		}
		save();
		BindUtils.postNotifyChange(null, null, ob, attr);
	}
	
	@Command
	public void saveNhanViec(@BindingParam("item") final GiaoViec ob,
			@BindingParam("vm") final Object vm, @BindingParam("wdn") final Window wdn) {
		if (this.getNguoiDuocGiao().getId() != core().getNhanVien().getId() || !TrangThaiGiaoViec.CHUA_LAM.equals(this.getTrangThaiGiaoViec())) {
			return;
		}
		if (wdn != null) {
			wdn.detach();
		}
		ob.setTrangThaiGiaoViec(TrangThaiGiaoViec.DANG_LAM);
		ob.save();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, vm, "*");
	}
	
	@Command
	public void saveBaoCao(@BindingParam("vmArgs") final Object ob,
			@BindingParam("wdn") final Window wd) {
		wd.detach();
		if (this.getNguoiDuocGiao().getId() != core().getNhanVien().getId() || !TrangThaiGiaoViec.DANG_LAM.equals(this.getTrangThaiGiaoViec())) {
			return;
		}
		this.setTrangThaiGiaoViec(TrangThaiGiaoViec.HOAN_THANH);
		if (getTaiLieuKetQua() != null && getTaiLieuKetQua().getNameHash() != null) {
			this.getTaiLieuKetQua().saveNotShowNotification();
		}
		this.save();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, ob, "*");
	}
	
	public boolean checkQuyenSua(NhanVien nguoiDuocGiao, Date hanThucHien){
		if (!"".equals(nguoiDuocGiao.getHoVaTen()) && hanThucHien != null) {
			return true;
		}
		return false;
	}
	
	@Command
	public void saveCongViec(@BindingParam("list") DoanVao doanVao, @BindingParam("attr") String attr,
			@BindingParam("wdn") Window wdn, @BindingParam("isAdd") boolean isAdd) {
		if (isAdd) {
			this.setNguoiTao(core().fetchNhanVien(true));
			this.setTrangThaiGiaoViec(null);
			doanVao.getListCongViecTheoDoanVao().add(0, this);
			BindUtils.postNotifyChange(null, null, doanVao, attr);
			wdn.detach();
			return;
		}
		BindUtils.postNotifyChange(null, null, doanVao, attr);
		wdn.detach();
	}
	
	@Command
	public void uploadFile(@BindingParam("medias") final Object medias, @BindingParam("vm") final Object object,
			@BindingParam("name") final String name) throws IOException {
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
				
				if (getTaiLieuKetQua() == null) {
					taiLieuKetQua = new TepTin();
				}
				taiLieuKetQua.setNameHash(tenFile);
				taiLieuKetQua.setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
				taiLieuKetQua.setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
				taiLieuKetQua.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
				taiLieuKetQua.setMedia(media);
				taiLieuKetQua.saveFileTepTin();
				BindUtils.postNotifyChange(null, null, object, name);
	
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	
	@Command
	public void uploadFileTaiLieu(@BindingParam("medias") final Object medias) throws IOException {
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
				if (taiLieu == null) {
					taiLieu = new TepTin();
				}
				getTaiLieu().setNameHash(tenFile);
				getTaiLieu().setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
				getTaiLieu().setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
				getTaiLieu().setPathFile(folderStoreFilesLink() + folderStoreTepTin());
				getTaiLieu().setMedia(media);
				getTaiLieu().saveFileTepTin();
				BindUtils.postNotifyChange(null, null, this, "taiLieu");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	
	@Command
	public void deleteFile(@BindingParam("vm") final Object vm, @BindingParam("ob") TepTin ob,
			@BindingParam("name") final String name) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
			Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) throws IOException {
					if (Messagebox.ON_OK.equals(event.getName())) {
						ob.setNameHash("");
						ob.setTypeFile("");
						ob.setTenFile("");
						ob.setPathFile("");
						ob.setMedia(null);
						BindUtils.postNotifyChange(null, null, ob, "*");
						BindUtils.postNotifyChange(null, null, vm, name);
						showNotification("Đã xóa", "", "success");
					}
				}
			});
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
								if (giaoViec != null) {
									if (!giaoViec.noId()) {
										giaoViec.doDelete(true);
										removeIdInList(giaoViec);
									}
								}
								BindUtils.postNotifyChange(null, null, this, "*");
								BindUtils.postNotifyChange(null, null, vm, attr);
							}
						}
					});
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

	public CongViecModel toGiaoViecModel() {
		CongViecModel rs = new CongViecModel();
		rs.setId(getId() != null ? getId() : null);
		rs.setIdDoanVao(this.getDoanVao() != null ? this.getDoanVao().getId() : null);
		rs.setTenDoanVao(this.getDoanVao() != null ? this.getDoanVao().getTenDoanVao() : "");
		rs.setIdDuAn(this.getDuAn() != null ? this.getDuAn().getId() : null);
		rs.setTenDuAn(this.getDuAn() != null ? this.getDuAn().getTenDuAn() : "");
		rs.setTenCongViec(getTenCongViec() != null ? getTenCongViec() : "");
		rs.setyKienChiDao(getyKienChiDao() != null ? getyKienChiDao() : "");
		rs.setKetQua(getKetQua() != null ? getKetQua() : "");
		rs.setGhiChu(getGhiChu() != null ? getGhiChu() : "");
		rs.setIdNguoiGiaoViec(this.getNguoiGiaoViec() != null ? this.getNguoiGiaoViec().getId() : null);
		rs.setNguoiGiaoViec(getNguoiGiaoViec() != null ? getNguoiGiaoViec().getHoVaTen() : "");
		rs.setIdNguoiDuocGiao(this.getNguoiDuocGiao() != null ? this.getNguoiDuocGiao().getId() : null);
		rs.setNguoiDuocGiao(getNguoiDuocGiao() != null ? getNguoiDuocGiao().getHoVaTen() : "");
		rs.setGiaiDoanXucTien(getGiaiDoanXucTien() != null ? getGiaiDoanXucTien().getText() : "");
		rs.setTrangThaiCongViec(getTrangThaiGiaoViec() != null ? getTrangThaiGiaoViec().getText() : "");
		rs.setLoaiCongViec(getLoaiCongViec() != null ? getLoaiCongViec().getText() : "");
		rs.setNgayGiao(getNgayGiao() != null ? getNgayGiao() : null);
		rs.setHanThucHien(getHanThucHien() != null ? getHanThucHien() : null);
		rs.setNgayHoanThanh(getNgayHoanThanh() != null ? getNgayHoanThanh() : null);
		rs.setIdLoaiCongViecKeHoach(this.getCha() != null ? this.getCha().getId() : null);
		rs.setTenLoaiCongViecKeHoach(this.getCha() != null ? this.getCha().getTen() : "");
		return rs;
	}
}
