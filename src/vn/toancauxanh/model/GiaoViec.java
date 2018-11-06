package vn.toancauxanh.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;

@Entity
@Table(name="giaoviec")
public class GiaoViec extends Model<GiaoViec>{
	private DuAn duAn;
	private String tenCongViec;
	private NhanVien nguoiGiaoViec = new NhanVien();
	private NhanVien nguoiDuocGiao = new NhanVien();
	private Date ngayGiao;
	private Date hanThucHien;
	private GiaiDoanXucTien giaiDoanXucTien;
	private TrangThaiGiaoViec trangThaiGiaoViec = TrangThaiGiaoViec.CHUA_LAM;
	private String yKienChiDao;
	private TepTin taiLieu = new TepTin();
	private Date ngayHoanThanh;
	private String ketQua;
	private TepTin taiLieuKetQua;
	
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

	@Command
	public void saveGiaoViec(@BindingParam("vmArgs") final Object ob,@BindingParam("attr") final String attr,
			@BindingParam("vm") final Object vm,@BindingParam("wdn") final Window wd) {
		wd.detach();
		if(taiLieu!=null) {
			taiLieu.saveNotShowNotification();
		}
		save();
		
		BindUtils.postNotifyChange(null, null, ob, attr);
	}
	
	@Command
	public void saveNhanViec(@BindingParam("item") final GiaoViec ob,
			@BindingParam("vm") final Object vm, @BindingParam("wdn") final Window wdn) {
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
		this.setTrangThaiGiaoViec(TrangThaiGiaoViec.HOAN_THANH);
		if(getTaiLieuKetQua() !=null) {
			this.getTaiLieuKetQua().saveNotShowNotification();
		}
		this.save();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, ob, "*");
	}
	
	@Command
	public void uploadFile(@BindingParam("medias") final Object medias, @BindingParam("vm") final Object object,
			@BindingParam("name") final String name) {
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
				taiLieuKetQua.setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
				taiLieuKetQua.setMedia(media);
				BindUtils.postNotifyChange(null, null, object, name);
	
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	
	@Command
	public void uploadFileTaiLieu(@BindingParam("medias") final Object medias) {
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
				getTaiLieu().setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
				getTaiLieu().setMedia(media);
				BindUtils.postNotifyChange(null, null, this, "taiLieu");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	
	@Command
	public void deleteFile(@BindingParam("vm") final Object vm, @BindingParam("ob") TepTin ob) {
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
						BindUtils.postNotifyChange(null, null, vm, "taiLieu");
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
	
}
