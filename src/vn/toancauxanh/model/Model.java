package vn.toancauxanh.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.camunda.bpm.engine.impl.pvm.PvmTransition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.Task;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Default;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.google.common.base.Strings;
import com.querydsl.core.annotations.QueryInit;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.service.BaseObject;

@MappedSuperclass
// @Cacheable(true)
public class Model<T extends Model<T>> extends BaseObject<T> {
	// private static final long serialVersionUID = 4219024869115773046L;

	private NhanVien nguoiSua;

	private NhanVien nguoiTao;

	private String trangThai = core() == null ? "ap_dung" : core().TT_AP_DUNG;

	private Long id;
	private Date ngayTao;
	private Date ngaySua;
	private boolean daXoa;

	public Date getNgaySua() {
		return this.ngaySua;
	}

	public Date getNgayTao() {
		return this.ngayTao;
	}

	public boolean isDaXoa() {
		return this.daXoa;
	}

	@Override
	public void setDaXoa(boolean deleted) {
		this.daXoa = deleted;
		if (deleted) {
			setTrangThai(core().TT_DA_XOA);
		}
	}

	public void setId(final Long _id) {
		this.id = _id != null && _id.longValue() == 0L ? null : _id;
	}

	public void setNgaySua(Date ngaySua1) {
		this.ngaySua = ngaySua1;
	}

	public void setNgayTao(Date ngayTao1) {
		this.ngayTao = ngayTao1;
	}

	@Override
	@Id
	@GeneratedValue
	public Long getId() {
		return id == null ? Long.valueOf(0) : id;
	}

	@Transient
	public boolean isCoQuyenChinhSua() {
		return core().getNhanVien().equals(nguoiTao);
	}

	@Override
	public void doSave() {
		setNgaySua(new Date());
		setNguoiSua(core().fetchNhanVien(true));
		if (noId()) {
			setNgayTao(getNgaySua());
			setNguoiTao(getNguoiSua());
		}
		super.doSave();
	}

	@Transient
	public boolean permitted() {
		return !core().TT_DA_XOA.equals(trangThai);
	}

	@Command
	public void deleteTrangThaiConfirmAndNotify(final @BindingParam("notify") Object beanObject,
			final @BindingParam("attr") @Default(value = "*") String attr) {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(final Event event) {
							if (Messagebox.ON_OK.equals(event.getName())) {
								doDelete(true);
								showNotification("Xóa thành công!", "", "success");
								if (beanObject != null) {
									BindUtils.postNotifyChange(null, null, beanObject, attr);
									if (beanObject != Model.this) {
										BindUtils.postNotifyChange(null, null, Model.this, "*");
									}
								}
							}
						}
					});
		}

	}
	
	@Command
	public void deleteObjectAndNotify(final @BindingParam("notify") Object beanObject,
			final @BindingParam("attr") @Default(value = "*") String attr) {
		Object obj = this;
		String tenNhiemVu = "";
		if (obj instanceof DoanVao) {
			tenNhiemVu = "đoàn vào " + ((DoanVao) obj).getTenDoanVao();
		} else {
			tenNhiemVu = "dự án " + ((DuAn) obj).getTenDuAn();
		}
		tenNhiemVu.concat(" không ?");
		if (!checkInUse()) {
			Messagebox.show("Bạn có chắc muốn xóa " + tenNhiemVu, "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(final Event event) {
							if (Messagebox.ON_OK.equals(event.getName())) {
								doDelete(true);
								showNotification("Xóa thành công!", "", "success");
								if (obj instanceof DoanVao) {
									xoaCongViecLienQuan(((DoanVao) obj).getId(), LoaiCongViec.DOAN_VAO);
									notifyNguoiLienQuan(((DoanVao) obj).getIdNguoiLienQuan(), ((DoanVao) obj).getTenDoanVao());
								} else if (obj instanceof DuAn) {
									xoaCongViecLienQuan(((DuAn) obj).getId(), LoaiCongViec.DU_AN);
									notifyNguoiLienQuan(((DuAn) obj).getIdNguoiLienQuan(), ((DuAn) obj).getTenDuAn());
									find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(((DuAn) obj).getId())).fetch().forEach(item -> item.doDelete(true));
								}
								if (beanObject != null) {
									BindUtils.postNotifyChange(null, null, beanObject, attr);
									if (beanObject != Model.this) {
										BindUtils.postNotifyChange(null, null, Model.this, "*");
									}
								}
							}
						}
					});
		}

	}
	
	public void notifyNguoiLienQuan(String idNguoiLienQuan, String tenNhiemVu) {
		listSubStringId(idNguoiLienQuan).stream().distinct().forEach(item -> {
			ThongBao thongBao = new ThongBao();
			NhanVien nV = new NhanVien();
			nV.setId(item);
			thongBao.setNguoiNhan(nV);
			thongBao.setLoaiThongBao(LoaiThongBao.HUY_CONG_VIEC);
			thongBao.setNoiDung(tenNhiemVu + "@ đã được huỷ. Bạn không cần thực hiện các công việc liên quan đến @" + tenNhiemVu + "@ nãy nữa.");
			thongBao.saveNotShowNotification();
		});
	}
	
	@Command
	public void deleteTrangThaiConfirmAndNotifyAndCheck(final @BindingParam("notify") Object beanObject,
			final @BindingParam("attr") @Default(value = "*") String attr, @BindingParam("type") final String type) {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(final Event event) {
							if (Messagebox.ON_OK.equals(event.getName())) {
								Long count = 0l;
								if ("phongban".equals(type)) {
									count = find(NhanVien.class).where(QNhanVien.nhanVien.phongBan.id.eq(Model.this.id))
											.fetchCount();
								}
								if ("vaitro".equals(type)) {
									count = find(NhanVien.class)
											.where(QNhanVien.nhanVien.vaiTros.contains((VaiTro) Model.this))
											.fetchCount();
								}
								if ("donvi".equals(type)) {
									count = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.donViChuTri
											.eq((DonVi) Model.this)
											.or(QGiaiDoanDuAn.giaiDoanDuAn.donViThucHien.eq((DonVi) Model.this))
											.or(QGiaiDoanDuAn.giaiDoanDuAn.donViTuVan.eq((DonVi) Model.this))
											.or(QGiaiDoanDuAn.giaiDoanDuAn.donViLapKeHoach.eq((DonVi) Model.this))
											.or(QGiaiDoanDuAn.giaiDoanDuAn.donViChuTri.eq((DonVi) Model.this)))
											.fetchCount();
								}
								if ("nguoidung".equals(type)) {
									count = find(DuAn.class).where(QDuAn.duAn.idNguoiLienQuan.contains("@"+((NhanVien) Model.this).getId()+"@").or(QDuAn.duAn.nguoiTao.eq((NhanVien) Model.this))).fetchCount();
									count += find(DoanVao.class)
											.where(QDoanVao.doanVao.idNguoiLienQuan
													.contains("@" + ((NhanVien) Model.this).getId() + "@")
													.or(QDoanVao.doanVao.nguoiTao.eq((NhanVien) Model.this))
													.or(QDoanVao.doanVao.nguoiPhuTrach.eq((NhanVien) Model.this)))
											.fetchCount();
								}
								if ("linhvucduan".equals(type)) {
									count = find(DuAn.class).where(QDuAn.duAn.linhVuc.eq((LinhVucDuAn) Model.this)).fetchCount();
								}
								if ("capdonvi".equals(type)) {
									count = find(DonViXucTien.class).where(QDonViXucTien.donViXucTien.capDonVi.eq((CapDonVi) Model.this)).fetchCount();
								}
								if (count == 0) {
									doDelete(true);
									showNotification("Xóa thành công!", "", "success");
									if (beanObject != null) {
										BindUtils.postNotifyChange(null, null, beanObject, attr);
										if (beanObject != Model.this) {
											BindUtils.postNotifyChange(null, null, Model.this, "*");
										}
									}
								} else {
									showNotification("Xóa không thành công. Dữ liệu đang được sử dụng!", "", "warning");
								}
							}
						}
					});
		}

	}

	@Command
	public void deleteRemoveConfirm() {
		if (!checkInUse()) {
			Messagebox.show("Bạn muốn xóa mục này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK, Messagebox.QUESTION,
					new EventListener<Event>() {
						@Override
						public void onEvent(final Event event) {
							if (Messagebox.ON_OK.equals(event.getName())) {
								doDelete(false);
								showNotification("Xóa thành công!", "", "success");
							}
						}
					});
		}
	}

	@Transient
	public String setStyleTrangThai(String strStyle) {
		if (core().TT_KHONG_AP_DUNG.equals(strStyle)) {
			return "label label-default width-110px";
		} else if (core().TT_AP_DUNG.equals(strStyle)) {
			return "label label-success width-90px";
		} else {
			return "";
		}
	}

	@QueryInit("*.*.*.*")
	@ManyToOne
	public NhanVien getNguoiSua() {
		return nguoiSua;
	}

	@QueryInit("*.*.*.*")
	@ManyToOne
	public NhanVien getNguoiTao() {
		return nguoiTao;
	}

	@Transient
	public NhanVien getOrNewNguoiTao() {
		NhanVien result = nguoiTao;
		if (result == null) {
			result = new NhanVien();
		}
		return result;
	}

	@Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.NO, store = Store.NO)
	public String getTrangThai() {
		return trangThai;
	}

	/*
	 * @Transient public boolean isDuocLuu() { return
	 * core().getQuyen().get(core().BAIVIETTHEM) ||
	 * core().getQuyen().get(core().BAIVIETSUA); }
	 * 
	 * @Transient public boolean isDuocSua() { return
	 * core().getQuyen().get(core().BAIVIETSUA); }
	 */

	@Transient
	public String getLoaiNoiDung() {
		return getClass().getSimpleName();
	}

	@Transient
	public String getTrangThaiSoan() {
		return trangThai;
	}

	@Transient
	public String setStylePublishStatus(boolean publishStatus) {
		if (publishStatus) {
			return "label label-success";
		}
		return "label label-default";
	}

	public String businessKey() {
		return getClass().getName() + "@" + getId();
	}

	@Transient
	public boolean isApDung() {
		return core().TT_AP_DUNG.equals(trangThai);
	}

	@Command
	public void saveAndContinue() {
		saveNotRedirect();
		// getContextResource().openAddNoConfirm();
	}

	@Command
	public void saveNotRedirect() {
		saveValidate();
		showNotification("Đã lưu thành công!", "", "success");
	}

	@Command
	public void saveRedirect() {
		saveNotRedirect();
		// getContextResource().openListNoConfirm();
	}

	public void saveValidate() {
		validate();
		save();
	}

	@Override
	public void save() {
		transactioner().execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				Logger.getGlobal().info(getId() + "");
				boolean noid = noId();
				doSave();
				if (noid) {
					showNotification("Đã lưu thành công!", "", "success");
				} else {
					showNotification("Đã cập nhật thành công!", "", "success");
				}
				Logger.getGlobal().info(getId() + "");
			}
		});
	}

	public void saveNotShowNotification() {
		doSave();
	}

	public void setApDung(final boolean isApdung) {
		if (isApdung != isApDung()) {
			trangThai = isApdung ? core().TT_AP_DUNG : core().TT_KHONG_AP_DUNG;
		}
	}

	public void setNguoiSua(final NhanVien _nguoiSua) {
		nguoiSua = _nguoiSua;
	}

	public void setNguoiTao(final NhanVien _nguoiTao) {
		nguoiTao = _nguoiTao;
	}

	public void setTrangThai(final String _trangThai) {
		trangThai = Strings.nullToEmpty(_trangThai);
	}

	public void validate() {
		//
	}

	@Transient
	public String getDocumentType() {
		return "";
	}

	@Command
	public void cancelPopup(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}

	private boolean checkApDung;

	@Transient
	public boolean isCheckApDung() {
		checkApDung = false;
		if (core().TT_AP_DUNG.equals(getTrangThai())) {
			checkApDung = true;
		}
		return checkApDung;
	}

	public void setCheckApDung(final boolean _isCheckApDung) {
		if (_isCheckApDung) {
			setTrangThai(core().TT_AP_DUNG);
		} else {
			setTrangThai(core().TT_KHONG_AP_DUNG);
		}
		this.checkApDung = _isCheckApDung;
	}

	@Transient
	public String getTime2String(Date date) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		String out = "";
		if (date != null) {
			out = dateFormatGmt.format(date);
		}
		return out;
	}

	@Transient
	public String getDate2String(Date date) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		String out = "";
		if (date != null) {
			out = dateFormatGmt.format(date);
		}
		return out;
	}

	@Transient
	public String getDateTime2String(Date date) {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		String out = "";
		if (date != null) {
			out = dateFormatGmt.format(date);
		}
		return out;
	}

	@Transient
	public String getExtension(final String strFileName) {
		String strExtension = "";
		if (strFileName.toLowerCase().endsWith(".doc")) {
			strExtension = "doc";
		} else if (strFileName.toLowerCase().endsWith(".docx")) {
			strExtension = "docx";
		} else if (strFileName.toLowerCase().endsWith(".pdf")) {
			strExtension = "pdf";
		} else if (strFileName.toLowerCase().endsWith(".xls")) {
			strExtension = "xls";
		} else if (strFileName.toLowerCase().endsWith(".xlsx")) {
			strExtension = "xlsx";
		} else if (strFileName.toLowerCase().endsWith(".zip")) {
			strExtension = "zip";
		} else if (strFileName.toLowerCase().endsWith(".rar")) {
			strExtension = "rar";
		}
		return strExtension;
	}

	@Transient
	public String getHomeUrl(String code) {
		String url = Executions.getCurrent().getHeader("host");
		if ("en".equals(code)) {
			return "http://" + url + "/web/en";
		}
		return "http://" + url;
	}

	public void savePublishStatus(final boolean publishStatus) {
		transactioner().execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				doSave();
				showNotification("Đã cập nhật thành công!", "", "success");
			}
		});
	}

	public @Nullable Date fixNgay(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			date = cal.getTime();
		}
		return date;
	}

	@Transient
	public Task getCurrentTask() {
		List<Task> listPage = core().getProcess().getTaskService().createTaskQuery()
				.processInstanceBusinessKey(businessKey()).orderByTaskCreateTime().desc().listPage(0, 1);
		return listPage.isEmpty() ? null : listPage.get(0);
	}

	@Transient
	public boolean isMyTask() {
		if (getCurrentTask() != null) {
			List<IdentityLink> identities = core().getProcess().getTaskService()
					.getIdentityLinksForTask(getCurrentTask().getId());
			for (IdentityLink identity : identities) {
				if (core().getNhanVien().getListNhom().contains(identity.getGroupId())) {
					return true;
				}
			}
		}
		return false;
	}

	@Command
	public void doAction(@BindingParam("flow") final PvmTransition flow, @BindingParam("list") final Object listObject,
			@BindingParam("attr") final String attr, @BindingParam("vm") final Object vm,
			@BindingParam("wdn") final Window wdn) {
		if (wdn != null) {
			wdn.detach();
		}
		Map<String, Object> variables = new HashMap<>();
		variables.put("flow", flow.getId());
		variables.put("model", this);
		variables.put("list", listObject);
		variables.put("attr", attr);
		if (vm != null) {
			variables.put("vm", vm);
		}
		core().getProcess().getTaskService().complete(getCurrentTask().getId(), variables);

	}

	@Command
	public void doActionWithKey(@BindingParam("flow") final PvmTransition flow,
			@BindingParam("processDefinitionKey") final String processDefinitionKey,
			@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		Task task = null;
		if (getCurrentTask() == null) {
			save();
			ProcessInstance processInstance = core().getProcess().getRuntimeService()
					.startProcessInstanceByKey(processDefinitionKey, businessKey());
			task = core().getProcess().getTaskService().createTaskQuery().processInstanceId(processInstance.getId())
					.singleResult();
		} else {
			task = getCurrentTask();
		}
		Map<String, Object> variables = new HashMap<>();
		variables.put("flow", flow.getId());
		variables.put("model", this);
		variables.put("list", listObject);
		variables.put("attr", attr);
		core().getProcess().getTaskService().complete(task.getId(), variables);
	}
}
