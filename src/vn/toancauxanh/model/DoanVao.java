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

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import vn.toancauxanh.gg.model.QuocGia;
import vn.toancauxanh.gg.model.enums.TrangThaiEnum;

@Entity
@Table(name = "doanvao")
public class DoanVao extends Model<DoanVao> {
	private String tenDoanVao;
	private int quocGia;
	private TrangThaiEnum trangThaiTiepDoan = TrangThaiEnum.CHUA_TIEP;
	private String tomTatNoiDungKQ = "";
	private String deXuatCVPhuTrach = "";
	private String yKienChiDao = "";
	private String noiDoanDiTham;
	private int soNguoi;
	private Date thoiGianDenLamViec = new Date();
	private NhanVien nguoiPhuTrach = new NhanVien();
	private List<KeHoachLamViec> listKeHoachLamViec;
	private List<ThanhVien> listThanhVien;
	private String link;
	private TepTin taiLieu;
	private TepTin congVanChiDaoUB;
	private boolean checkTaiLieu;
	private QuocGia quocGiaTemp;
	
	public void setQuocGiaTemp(QuocGia quocGiaTemp) {
		this.quocGiaTemp = quocGiaTemp;
	}

	public DoanVao() {

	}

	public String getTenDoanVao() {
		return tenDoanVao;
	}

	public void setTenDoanVao(String tenDoanVao) {
		this.tenDoanVao = tenDoanVao;
	}

	public int getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(int quocGia) {
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

	public void setListThanhVien(List<ThanhVien> listThanhVien) {
		this.listThanhVien = listThanhVien;
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
	public void selectQuocGia(@BindingParam("quocgia") QuocGia ob) {
		if (getQuocGiaTemp() != null) {
			setQuocGia(getQuocGiaTemp().getId());
		}
	}
	
	@Command
	public void loadQuocGia() {
		QuocGia qg = new QuocGia();
		List<QuocGia> list = new ArrayList<QuocGia>();
		list.addAll(qg.getBootstrap());
		if (getQuocGia() != 0) {
			setQuocGiaTemp(list.get(getQuocGia()));
			BindUtils.postNotifyChange(null, null, this, "quocGiaTemp");
		}
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
				tepTin.setPathFile(folderStoreFilesLink() + folderStoreFilesLeHoi() + folderStoreFilesTepTin());
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
		if (getTaiLieu() != null) {
			getTaiLieu().save();
		}
		save();
		redirectPageList("/cp/quanlydoanvao", null);
	}
	
	@Command
	public void redirectPageList(@BindingParam("url") String url, @BindingParam("vm") DoanVao vm) {
		Executions.getCurrent().sendRedirect(url);
	}
	
	@Transient
	public QuocGia getQuocGiaTemp() {
		return quocGiaTemp;
	}
	
	@Transient
	public List<KeHoachLamViec> getListKeHoachLamViec() {
		return listKeHoachLamViec;
	}
	
	@Transient
	public List<ThanhVien> getListThanhVien() {
		return listThanhVien;
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
				Integer soNguoi = Integer.parseInt((String) ctx.getProperty().getValue());
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
}
