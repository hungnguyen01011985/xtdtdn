package vn.toancauxanh.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.ThongBaoEnum;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.gg.model.enums.TrangThaiTiepDoanEnum;
import vn.toancauxanh.model.util.CustomDateSerializer;
import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.service.BaoCaoThongKeDoanVao;
import vn.toancauxanh.service.CongViecKeHoachService;
import vn.toancauxanh.service.DoanVaoService;

@Entity
@Table(name = "doanvao")
public class DoanVao extends Model<DoanVao> {
	
	@Lob
	private String tenDoanVao;
	private TrangThaiTiepDoanEnum trangThaiTiepDoan = TrangThaiTiepDoanEnum.CHUA_TIEP;
	private String tomTatNoiDungKQ = "";
	private String deXuatCVPhuTrach = "";
	private String yKienChiDao = "";
	private String noiDoanDiTham;
	private String idNguoiLienQuan = "";
	@Lob
	private String link;
	private int soNguoi;
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date thoiGianDenLamViec = new Date();
	private NhanVien nguoiPhuTrach;
	private boolean checkTaiLieu;
	private ThanhVienDoan thanhVienDoanTemp = new ThanhVienDoan();
	private List<TepTin> tepTins = new ArrayList<>();
	private TepTin congVanChiDaoUB;

	public DoanVao() {

	}

	public String getTenDoanVao() {
		return tenDoanVao;
	}

	public void setTenDoanVao(String tenDoanVao) {
		this.tenDoanVao = tenDoanVao;
	}

	public int getSoNguoi() {
		return soNguoi;
	}
	
	@Transient
	public String getSoNguoiAndText() {
		return soNguoi + " người";
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
	public TepTin getCongVanChiDaoUB() {
		return congVanChiDaoUB;
	}

	public void setCongVanChiDaoUB(TepTin congVanChiDaoUB) {
		this.congVanChiDaoUB = congVanChiDaoUB;
	}

	public String getNoiDoanDiTham() {
		return noiDoanDiTham;
	}

	public void setNoiDoanDiTham(String noiDoanDiTham) {
		this.noiDoanDiTham = noiDoanDiTham;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getIdNguoiLienQuan() {
		return idNguoiLienQuan;
	}

	public void setIdNguoiLienQuan(String idNguoiLienQuan) {
		this.idNguoiLienQuan = idNguoiLienQuan;
	}
	
	@Transient
	public String getEditURL(String link) {
		if (link != null && !link.isEmpty()) {
			if (link.contains("http://") || link.contains("https://")) {
				return link;
			} else {
				return "http://" + link;
			}
		}
		return null;
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "doanvao_teptin", joinColumns = { @JoinColumn(name = "doanvao_id") }, inverseJoinColumns = {
			@JoinColumn(name = "teptin_id") })
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
					tepTin.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
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
					Filedownload.save(new URL("file:///" + path).openStream(), null,
							object.getTenFile().concat(object.getTypeFile()));
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

	private NhanVien nguoiPhuTrachCu;

	@Command
	public void saveDoanVao() {
		if (!this.noId()) {
			nguoiPhuTrachCu = getNguoiPhuTrachCu(this);
			JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.id.eq(this.getId()));
			if (!q.fetchFirst().getTenDoanVao().equals(this.getTenDoanVao())) {
				if (listCongViecTheoDoanVao.isEmpty()) {
					JPAQuery<GiaoViec> query = find(GiaoViec.class)
							.where(QGiaoViec.giaoViec.doanVao.id.eq(this.getId()))
							.orderBy(QGiaoViec.giaoViec.cha.ten.asc()).orderBy(QGiaoViec.giaoViec.ngayTao.desc());
					query.setHint("org.hibernate.cacheable", false);
					if (q.fetchCount() > 0) {
						listCongViecTheoDoanVao.addAll(query.fetch());
					}
				}
			}
		}
		if (this.getCongVanChiDaoUB().getTenFile() == null) {
			this.setCongVanChiDaoUB(null);
		} else {
			this.getCongVanChiDaoUB().saveNotShowNotification();
		}

		this.getTepTins().forEach(item -> {
			item.saveNotShowNotification();
		});
		String param = this.getTomTatNoiDungKQ().trim().replaceAll("\\s+", " ");
		this.setTomTatNoiDungKQ(param);
		if (param != null && !"".equals(param)) {
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
				item.doDelete(true);
				item.saveNotShowNotification();
			});
		}
		
		if (listCongViecTheoDoanVao != null && !listCongViecTheoDoanVao.isEmpty()) {
			listCongViecTheoDoanVao.forEach(item -> {
				item.setTenNhiemVu(this.getTenDoanVao());
				checkCongViec(item);
				if (checkNotAllNull && checkAllNull) {
					checkGiaoViec(item);
				}
				resetCheck();
			});
		}
		
		if (listXoaCongViecKeHoach != null && !listXoaCongViecKeHoach.isEmpty()) {
			listXoaCongViecKeHoach.forEach(item -> {
				item.setDoanVao(this);
				item.doDelete(true);
				item.saveNotShowNotification();
			});
		}
		
		if (nguoiPhuTrachCu != null && nguoiPhuTrachCu.getId() != this.getNguoiPhuTrach().getId()) {
			thongBao(LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH, this, null, nguoiPhuTrachCu, this.getNguoiTao(), null);
			thongBao(LoaiThongBao.PHU_TRACH_CONG_VIEC, this, null, this.getNguoiPhuTrach(), this.getNguoiTao(), null);
		} else if (nguoiPhuTrachCu == null) {
			thongBao(LoaiThongBao.PHU_TRACH_CONG_VIEC, this, null, this.getNguoiPhuTrach(), this.getNguoiTao(), null);
		}
		redirectPageList("/cp/quanlydoanvao", null);
	}

	private NhanVien nguoiThucHienCu = new NhanVien();

	@Transient
	public NhanVien getNguoiThucHienCu() {
		return nguoiThucHienCu;
	}

	public void setNguoiThucHienCu(NhanVien nguoiThucHienCu) {
		this.nguoiThucHienCu = nguoiThucHienCu;
	}

	public void checkGiaoViec(GiaoViec giaoViec) {
		if (giaoViec.noId()) {
			saveCongViec(giaoViec);
			giaoViec.getNguoiDuocGiao().saveNotShowNotification();
			thongBao(LoaiThongBao.CONG_VIEC_MOI, this, giaoViec, giaoViec.getNguoiDuocGiao(), giaoViec.getNguoiGiaoViec(), giaoViec.getTenCongViec());
			giaoViec.saveNotShowNotification();
			this.setIdNguoiLienQuan(this.getIdNguoiLienQuan() + KY_TU + giaoViec.getNguoiDuocGiao().getId() + KY_TU);
			this.saveNotShowNotification();
		} else {
			this.setNguoiThucHienCu(getNguoiDuocGiaoCu(giaoViec));
			if (!this.getNguoiThucHienCu().equals(giaoViec.getNguoiDuocGiao())) {
				String resetId = removeIdInListDoanVao(giaoViec, this.getNguoiThucHienCu());
				saveCongViec(giaoViec);
				thongBao(LoaiThongBao.CONG_VIEC_MOI, this, giaoViec, giaoViec.getNguoiDuocGiao(), giaoViec.getNguoiGiaoViec(), giaoViec.getTenCongViec());
				giaoViec.saveNotShowNotification();
				this.setIdNguoiLienQuan(resetId + KY_TU + giaoViec.getNguoiDuocGiao().getId() + KY_TU);
				this.saveNotShowNotification();
			} else {
				giaoViec.saveNotShowNotification();
			}
		}
	}

	public void saveCongViec(GiaoViec giaoViec) {
		giaoViec.getTaiLieu().saveNotShowNotification();
		if (giaoViec.getTrangThaiGiaoViec() == null) {
			giaoViec.setTrangThaiGiaoViec(TrangThaiGiaoViec.CHUA_LAM);
		}
		giaoViec.setDoanVao(this);
		giaoViec.setNguoiGiaoViec(core().getNhanVien());
		giaoViec.setLoaiCongViec(LoaiCongViec.DOAN_VAO);
		giaoViec.setTenNhiemVu(this.getTenDoanVao());
		giaoViec.getNguoiDuocGiao().saveNotShowNotification();
		resetCheck();
	}

	public void thongBao(LoaiThongBao loaiThongBao, DoanVao doanVao, GiaoViec giaoViec, NhanVien nguoiNhan,
			NhanVien nguoiGui, String tenCongViec) {
		if (LoaiThongBao.PHU_TRACH_CONG_VIEC.equals(loaiThongBao)) {
			saveThongBao(LoaiThongBao.PHU_TRACH_CONG_VIEC, nguoiNhan, tenCongViec, doanVao, nguoiGui);
		}
		if (LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH.equals(loaiThongBao)) {
			saveThongBao(LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH, nguoiNhan, tenCongViec, doanVao, nguoiGui);
		}
		if (LoaiThongBao.CONG_VIEC_MOI.equals(loaiThongBao)) {
			if (giaoViec.noId()) {
				saveThongBao(LoaiThongBao.CONG_VIEC_MOI, nguoiNhan, tenCongViec, doanVao, nguoiGui);
			} else if (!this.nguoiThucHienCu.equals(giaoViec.getNguoiDuocGiao())) {
				saveThongBao(LoaiThongBao.CONG_VIEC_MOI, nguoiNhan, tenCongViec, doanVao, nguoiGui);
				saveThongBao(LoaiThongBao.CHUYEN_CONG_VIEC_DOAN_VAO, nguoiNhan, tenCongViec, doanVao, nguoiGui);
			}
		}
	}

	public void saveThongBao(LoaiThongBao loaiThongBao, NhanVien nguoiNhan, String tenCongViec, DoanVao doanVao,
			NhanVien nguoiGui) {

		if (LoaiThongBao.CONG_VIEC_MOI.equals(loaiThongBao)) {
			ThongBao thongBao = new ThongBao();
			thongBao.setNoiDung(nguoiNhan.getHoVaTen() + "@ có công việc mới @" + tenCongViec + "@ của @" + doanVao.getTenDoanVao());
			thongBao.setNguoiNhan(nguoiNhan);
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			}
			thongBao.setIdObject(doanVao.getId());
			thongBao.setLoaiThongBao(LoaiThongBao.CONG_VIEC_MOI);
			thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DOAN_VAO);
			thongBao.saveNotShowNotification();
		}
		if (LoaiThongBao.PHU_TRACH_CONG_VIEC.equals(loaiThongBao)) {
			ThongBao thongBao = new ThongBao();
			thongBao.setNoiDung("Bạn được phân công phụ trách Đoàn @" + doanVao.getTenDoanVao());
			thongBao.setNguoiNhan(nguoiNhan);
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			} else {
				thongBao.setNguoiGui(core().getNhanVien());
			}
			thongBao.setIdObject(doanVao.getId());

			thongBao.setLoaiThongBao(LoaiThongBao.PHU_TRACH_CONG_VIEC);
			thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DOAN_VAO);
			thongBao.saveNotShowNotification();
		}
		if (LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH.equals(loaiThongBao)) {
			ThongBao thongBao = new ThongBao();
			thongBao.setNoiDung("Đoàn vào @" + doanVao.getTenDoanVao() + "@ mà bạn đang phụ trách được chuyển cho @" + this.getNguoiPhuTrach().getHoVaTen() + "@ phụ trách.");
			thongBao.setNguoiNhan(nguoiNhan);
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			}
			thongBao.setIdObject(doanVao.getId());
			thongBao.setLoaiThongBao(LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH);
			thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DOAN_VAO);
			thongBao.saveNotShowNotification();
		}

		if (LoaiThongBao.CHUYEN_CONG_VIEC_DOAN_VAO.equals(loaiThongBao)) {
			ThongBao thongBao = new ThongBao();
			thongBao.setNoiDung("Công việc @" + tenCongViec + "@ của đoàn @" + doanVao.getTenDoanVao() + "@ đã được chuyển cho người khác");
			thongBao.setNguoiNhan(this.nguoiThucHienCu);
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			}
			thongBao.setIdObject(doanVao.getId());
			thongBao.setLoaiThongBao(LoaiThongBao.CHUYEN_CONG_VIEC_DOAN_VAO);
			thongBao.setKieuThongBao(ThongBaoEnum.THONG_BAO_DOAN_VAO);
			thongBao.saveNotShowNotification();
		}
	}

	@Transient
	public boolean kiemTraCongViecHoanThanh(DoanVao doanVao) {
		boolean result = true;
		JPAQuery<GiaoViec> q = find(GiaoViec.class).where(QGiaoViec.giaoViec.doanVao.eq(doanVao));
		result = q.fetch().stream().anyMatch(item -> !TrangThaiGiaoViec.HOAN_THANH.equals(item.getTrangThaiGiaoViec()));
		return result;
	}

	@Transient
	public NhanVien getNguoiDuocGiaoCu(GiaoViec giaoViec){
		JPAQuery<GiaoViec> q = find(GiaoViec.class).where(QGiaoViec.giaoViec.eq(giaoViec));
		if (q != null) {
			return q.fetchFirst().getNguoiDuocGiao();
		}
		return new NhanVien();
	}

	@Transient
	public NhanVien getNguoiPhuTrachCu(DoanVao doanVao) {
		JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.eq(doanVao));
		if (q.fetchCount() > 0) {
			return q.fetchFirst().getNguoiPhuTrach();
		}
		return new NhanVien();
	}

	@Command
	public void redirectPageList(@BindingParam("url") String url, @BindingParam("vm") DoanVao vm) {
		Executions.getCurrent().sendRedirect(url);
	}
	
	@Transient
	public boolean isCheckTaiLieu() {
		return checkTaiLieu;
	}
	
	@Command
	@NotifyChange({ "listSearch", "key" })
	public void searchKey(@BindingParam("key") String key,
			@BindingParam("vm") Object vm, @BindingParam("bandbox") Bandbox bb) {
		this.key = key;
		listSearch.clear();
		if (key == null || "".equals(key) || "".equals(key.replaceAll("\\s+", ""))) {
			listSearch.addAll(getListQuocGia());
			if (bb != null) {
				if (!bb.isOpen()) {
					bb.setOpen(true);
				} else {
					bb.setOpen(false);
				}
			}
			return;
		}
		if (vm != null) {
			if (vm instanceof DoanVaoService) {
				((DoanVaoService) vm).getArg().put("quocGia", null);
			} else if (vm instanceof BaoCaoThongKeDoanVao) {
				((BaoCaoThongKeDoanVao) vm).getArg().put("quocGia", null);
				
			}
		}
		tenQuocGia = null;
		thanhVienDoanTemp.setTenQuocGia(null);
		seachByKey(key);
		if (bb != null) {
			if (!bb.isOpen()) {
				bb.setOpen(true);
			} else {
				bb.setOpen(false);
			}
		}
	}
	
	@Command
	@NotifyChange("listSearch")
	public void searchKeyByOnClick(@BindingParam("key") String key,
			@BindingParam("vm") Object vm, @BindingParam("bandbox") Bandbox bb) {
		this.key = key;
		listSearch.clear();
		if (key == null || "".equals(key) || "".equals(key.replaceAll("\\s+", ""))) {
			listSearch.addAll(getListQuocGia());
			if (bb != null) {
				if (!bb.isOpen()) {
					bb.setOpen(true);
				} else {
					bb.setOpen(false);
				}
			}
			return;
		}
		seachByKey(key);
		if (bb != null) {
			if (!bb.isOpen()) {
				bb.setOpen(true);
			} else {
				bb.setOpen(false);
			}
		}
	}
	
	private List<String> listSearch = new ArrayList<>();
	
	@Transient
	public List<String> getListSearch() {
		return listSearch;
	}

	public void setListSearch(List<String> listSearch) {
		this.listSearch = listSearch;
	}
	
	private String key = "";
	
	@Transient
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public List<String> seachByKey(String param) {
		String keyWord = removeAccent(param).replace("\\s+", "").toLowerCase();
		List<String> list = getListQuocGia();
		List<String> listTimKiem = list.stream()
				.filter(item -> removeAccent(item).replace("\\s+", "").toLowerCase().contains(keyWord))
				.collect(Collectors.toList());
		if (!listTimKiem.isEmpty()) {
			listSearch.addAll(listTimKiem);
		}
		return new ArrayList<>();
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
	public AbstractValidator getValidateTenQuocGia() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String tenQuocGia = (String) ctx.getValidatorArg("ten");
				String param = tenQuocGia.trim().replaceAll("\\s+", "");
				if (param.isEmpty() || param == null) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				} else {
					if (!getListQuocGia().contains(tenQuocGia)) {
						addInvalidMessage(ctx, "Quốc gia này không có trong hệ thống.");
					}
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

	private List<ThanhVienDoan> listThanhVienDoan = new ArrayList<>();
	private List<ThanhVienDoan> listThanhVienTheoDoan = new ArrayList<>();
	private List<ThanhVienDoan> listTaoMoiThanhVienDoanLuuTam = new ArrayList<>();
	private List<ThanhVienDoan> listXoaThanhVienDoan = new ArrayList<>();

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
			listTaoMoiThanhVienDoanLuuTam.forEach(item -> listThanhVienDoan.add(0, item));
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
	public void notifyDoanVao(@BindingParam("notify") final DoanVao doanVao, @BindingParam("attr") final String attr) {
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
	
	private String tenQuocGia = "";
	
	@NotifyChange("listSearch")
	public String getTenQuocGia() {
		return tenQuocGia;
	}

	public void setTenQuocGia(String tenQuocGia) {
		this.tenQuocGia = tenQuocGia;
	}

	@Command
	public void saveThanhVienDoan() {
		if (flag) {
			listThanhVienDoan.set(index, thanhVienDoanTemp);
			BindUtils.postNotifyChange(null, null, this, "listThanhVienDoan");
			flag = false;
			BindUtils.postNotifyChange(null, null, this, "flag");
		} else {
			listTaoMoiThanhVienDoanLuuTam.add(this.getThanhVienDoanTemp());
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
	
	public void reset() {
		thanhVienDoanTemp = new ThanhVienDoan();
		BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
		Clients.evalJavaScript("getFocus()");
	}

	@Command
	public void reset(@BindingParam("vm") final DoanVao doanVao) {
		if (!"".equals(thanhVienDoanTemp.getHoVaTen())) {
			thanhVienDoanTemp.setHoVaTen(null);
			thanhVienDoanTemp.setChucDanh(null);
			thanhVienDoanTemp.setDonVi(null);
			thanhVienDoanTemp.setTenQuocGia(null);
			thanhVienDoanTemp.setEmail(null);
			thanhVienDoanTemp.setSoDienThoai(null);
			BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
			Clients.evalJavaScript("getFocus()");
		} else {
			thanhVienDoanTemp = new ThanhVienDoan();
			flag = false;
			BindUtils.postNotifyChange(null, null, doanVao, "flag");
			BindUtils.postNotifyChange(null, null, this, "thanhVienDoanTemp");
			Clients.evalJavaScript("getFocus()");
		}
	}
	
	private int index = 0;

	@Command
	public void editThanhVienDoan(@BindingParam("item") ThanhVienDoan thanhVienDoan, @BindingParam("index") int index) {
		this.index = index;
		thanhVienDoanTemp.setHoVaTen(thanhVienDoan.getHoVaTen());
		thanhVienDoanTemp.setChucDanh(thanhVienDoan.getChucDanh());
		thanhVienDoanTemp.setTenQuocGia(thanhVienDoan.getTenQuocGia());
		thanhVienDoanTemp.setEmail(thanhVienDoan.getEmail());
		thanhVienDoanTemp.setSoDienThoai(thanhVienDoan.getSoDienThoai());
		thanhVienDoan.doDelete(true);
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

	// ======================================================================================
	
	private List<GiaoViec> listCongViecTheoDoanVao = new ArrayList<>();
	private List<GiaoViec> listXoaCongViecKeHoach = new ArrayList<>();
	
	@Transient
	public List<GiaoViec> getListCongViecTheoDoanVao() {
		return listCongViecTheoDoanVao;
	}

	public void setListCongViecTheoDoanVao(List<GiaoViec> listCongViecTheoDoanVao) {
		this.listCongViecTheoDoanVao = listCongViecTheoDoanVao;
	}
	
	@Transient
	public List<GiaoViec> getListXoaCongViecKeHoach() {
		return listXoaCongViecKeHoach;
	}

	public void setListXoaCongViecKeHoach(List<GiaoViec> listXoaCongViecKeHoach) {
		this.listXoaCongViecKeHoach = listXoaCongViecKeHoach;
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
	
	@Command
	public void deleteCongViecKeHoach(@BindingParam("item") final GiaoViec congViec,
			@BindingParam("vm") final DoanVao doanVao) {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(Event event) throws Exception {
							if (Messagebox.ON_OK.equals(event.getName())) {
								showNotification("Xóa thành công!", "", "success");
								if (congViec != null) {
									doanVao.getListCongViecTheoDoanVao().remove(congViec);
									if (!congViec.noId()) {
										listXoaCongViecKeHoach.add(congViec);
									}
								}
								BindUtils.postNotifyChange(null, null, doanVao, "listCongViecTheoDoanVao");
								BindUtils.postNotifyChange(null, null, this, "listXoaCongViecKeHoach");
							}
						}
					});
		}
	}
	
	@Command
	public void redirectDoanVao(@BindingParam("id") Long id) {
		String url = "/cp/quanlydoanvao/edit/";
		Executions.sendRedirect(url.concat(id.toString()));
	}
	
	public void resetCheck() {
		checkNotAllNull = true;
		checkAllNull = false;
	}
	
	@Command
	public void saveKeHoachLamViec(@BindingParam("doanVao") final DoanVao doanVao,
			@BindingParam("wdn") final Window wdn) {
		listCongViecTheoDoanVao.forEach(item -> checkCongViec(item));
		if (!checkAllNull || !checkNotAllNull) {
			showNotification("", "Bạn chưa tạo công việc nào. Vui lòng tạo công việc", "danger");
			resetCheck();
		} else {
			wdn.detach();
		}
		resetCheck();
	}
	
	@Transient
	public DoanVaoModel toDoanVaoModel() {
		CongViecKeHoachService congViecKeHoach = new CongViecKeHoachService();
		List<GiaoViec> listCongViecTheoDoan = new ArrayList<>();
		listCongViecTheoDoan = congViecKeHoach.getListGiaoViecTheoDoanVao(this.getId());
		DoanVaoModel rs = new DoanVaoModel();
		rs.setId(getId() != null ? getId() : null);
		rs.setTenDoanVao(getTenDoanVao() != null ? getTenDoanVao() : "");
		rs.setQuocGia(getTenQuocGia() != null ? getTenQuocGia() : "");
		rs.setTrangThaiTiepDoan(getTrangThaiTiepDoan() != null ? getTrangThaiTiepDoan().getText() : "");
		rs.setTomTatNoiDungKq(getTomTatNoiDungKQ() != null ? getTomTatNoiDungKQ() : "");
		rs.setDeXuatCVPhuTrach(getDeXuatCVPhuTrach() != null ? getDeXuatCVPhuTrach() : "");
		rs.setyKienChiDao(getyKienChiDao() != null ? getyKienChiDao() : "");
		rs.setNoiDoanDiTham(getNoiDoanDiTham() != null ? getNoiDoanDiTham() : "");
		rs.setLink(getLink() != null ? getLink() : "");
		rs.setNguoiPhuTrach(getNguoiPhuTrach() != null ? getNguoiPhuTrach().getHoVaTen() : "");
		rs.setCongVanChiDaoUB(getCongVanChiDaoUB() != null ? getCongVanChiDaoUB().getTenFile() : "");
		rs.setIdNguoiPhuTrach(getNguoiPhuTrach() != null ? getNguoiPhuTrach().getId() : null);
		rs.setSoNguoi(getSoNguoi());
		rs.setThoiGianDenLamViec(getThoiGianDenLamViec() != null ? getThoiGianDenLamViec() : null);
		rs.setThanhVienDoans(getListThanhVienTheoDoan() != null ? getListThanhVienTheoDoan().stream().map(ThanhVienDoan::toThanhVienDoanModel).collect(Collectors.toList()) : null);
		rs.setCongViecs(listCongViecTheoDoan != null ? listCongViecTheoDoan.stream().map(GiaoViec::toGiaoViecModel).collect(Collectors.toList()) : null);
		return rs;
	}
}
