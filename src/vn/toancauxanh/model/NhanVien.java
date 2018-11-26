package vn.toancauxanh.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.google.common.base.Strings;
import com.querydsl.jpa.impl.JPAQuery;

//import vn.toancauxanh.gg.model.DonVi;
import vn.toancauxanh.service.Quyen;

@Entity
@Table(name = "nhanvien")
public class NhanVien extends Model<NhanVien> {
	private String chucVu = "";
	private String diaChi = "";
	private String email = "";
	private String hoVaTen = "";
	private String matKhau = "";
	private String matKhauCu = "";
	private String salkey = "";
	private String soDienThoai = "";
	private String pathAvatar = "";
	private String matKhauUpdate = "";
	private String matKhau2 = "";
	private boolean selectedDV;
	private Date ngaySinh;
	private Set<String> quyens = new HashSet<>();
	private Set<String> tatCaQuyens = new HashSet<>();
	private Set<VaiTro> vaiTros = new HashSet<>();
	private Image avatarImage;
	private PhongBan phongBan;
	private VaiTro vaiTro;

	@ManyToOne
	public PhongBan getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(PhongBan phongBan) {
		this.phongBan = phongBan;
	}

	@Transient
	public String getMatKhauUpdate() {
		return matKhauUpdate;
	}

	public void setMatKhauUpdate(String matKhauUpdate) {
		this.matKhauUpdate = matKhauUpdate;
	}

	@Transient
	public String getMatKhauCu() {
		return matKhauCu;
	}

	public void setMatKhauCu(String matKhauCu) {
		this.matKhauCu = matKhauCu;
	}

	public String getPathAvatar() {
		return pathAvatar;
	}

	public void setPathAvatar(String pathAvatar) {
		this.pathAvatar = pathAvatar;
	}

	@Transient
	public Image getAvatarImage() throws FileNotFoundException, IOException {
		if (avatarImage == null) {
			loadImageIsView();
		}
		return avatarImage;
	}

	public void setAvatarImage(Image avatarImage) {
		this.avatarImage = avatarImage;
	}

	private Quyen quyen = new Quyen(new SimpleAccountRealm() {
		@Override
		protected AuthorizationInfo getAuthorizationInfo(final PrincipalCollection arg0) {
			final SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			info.setStringPermissions(getTatCaQuyens());
			return info;
		}
	});

	public String getSalkey() {
		return salkey;
	}

	public void setSalkey(String salkey) {
		this.salkey = salkey;
	}

	@Transient
	public String getMatKhau2() {
		return matKhau2;
	}

	public void setMatKhau2(String matKhau2) {
		this.matKhau2 = matKhau2;
	}
	
	@ManyToOne
	public VaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(VaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	@CollectionTable(name = "nhanvien_quyens", joinColumns = { @JoinColumn(name = "nhanVien_id") })
	public Set<String> getQuyens() {
		return quyens;
	}

	@Transient
	public Set<String> getTatCaQuyens() {
		if (tatCaQuyens.isEmpty()) {
			tatCaQuyens.addAll(quyens);
			for (VaiTro vaiTro : vaiTros) {
				if (!vaiTro.getAlias().isEmpty()) {
					// tatCaQuyens.add(vaiTro.getAlias());
				}
				tatCaQuyens.addAll(vaiTro.getQuyens());
			}
			if (Labels.getLabel("email.superuser").equals(email)) {
				tatCaQuyens.add("*");
			}
		}
		return tatCaQuyens;
	}

	public void setQuyens(final Set<String> dsChoPhep) {
		quyens = dsChoPhep;
	}

	@Transient
	public String getVaiTroText() {
		String result = "";
		for (VaiTro vt : getVaiTros()) {
			result += (result.isEmpty() ? "" : ", ") + vt.getTenVaiTro();
		}
		return result;
	}

	@Transient
	public String getFirstAlias() {
		String result = "";
		for (VaiTro vt : getVaiTros()) {
			result = vt.getAlias();
			break;
		}
		return result;
	}

	public NhanVien() {
		super();
	}

	public NhanVien(final String email_, final String _hoTen) {
		super();
		email = email_;
		hoVaTen = _hoTen;
	}

	@Override
	public void doSave() {
		super.doSave();
	}

	public String getChucVu() {
		return chucVu;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public String getEmail() {
		return email;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public boolean changePass;

	@Transient
	public boolean isChangePass() {
		return changePass;
	}

	public void setChangePass(boolean changePass) {
		this.changePass = changePass;
	}

	public String matKhauEdit;

	@Transient
	public String getMatKhauEdit() {
		return matKhauEdit;
	}

	public void setMatKhauEdit(String matKhauEdit) {
		this.matKhauEdit = matKhauEdit;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "nhanvien_vaitro", joinColumns = { @JoinColumn(name = "nhanvien_id") }, inverseJoinColumns = {
			@JoinColumn(name = "vaitros_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public Set<VaiTro> getVaiTros() {
		return vaiTros;
	}

	public void setChucVu(final String _chuVu) {
		chucVu = Strings.nullToEmpty(_chuVu);
	}

	public void setDiaChi(final String _diaChi) {
		diaChi = Strings.nullToEmpty(_diaChi);
	}

	public void setEmail(final String _email) {
		email = Strings.nullToEmpty(_email);
	}

	public void setHoVaTen(final String _hoVaTen) {
		hoVaTen = Strings.nullToEmpty(_hoVaTen);
	}

	public void setMatKhau(final String _matKhau) {
		matKhau = Strings.nullToEmpty(_matKhau);
	}

	public void setNgaySinh(final Date _ngaySinh) {
		ngaySinh = _ngaySinh;
	}

	public void setSoDienThoai(final String _soDienThoai) {
		soDienThoai = Strings.nullToEmpty(_soDienThoai);
	}

	public void setVaiTros(final Set<VaiTro> vaiTros1) {
		vaiTros = vaiTros1;
	}

	@Transient
	public Quyen getTatCaQuyen() {
		return quyen;
	}

	public boolean isSelectedDV() {
		return selectedDV;
	}

	public void setSelectedDV(boolean selectedDV) {
		this.selectedDV = selectedDV;
	}

	@Transient // Khai báo k khởi tạo xuống db
	public AbstractValidator getValidator() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				// if (getVaiTros().size() == 0) {
				// addInvalidMessage(ctx, "lblErrVaiTros", "Bạn phải chọn vai
				// trò cho người dùng");
				// }
			}
		};
	}

	@Transient
	public AbstractValidator getValidator(boolean isBackend) {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				if (isBackend && getVaiTros() != null && getVaiTros().size() == 0) {
					addInvalidMessage(ctx, "lblErrVaiTros", "Bạn phải chọn vai trò cho người dùng.");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidatePassword() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				final Object mKhau = ctx.getValidatorArg("password");
				if (mKhau == null) {
				} else {
					Object pass = ctx.getProperty().getValue();
					if (pass == null) {
						pass = "";
					}
					if (mKhau.equals(pass)) {
					} else {
						addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp!");
					}
				}
			}
		};
	}

	@Command
	public void khoaThanhVien(@BindingParam("vm") final Object vm) {
		if ("admin@greenglobal.vn".equals(getEmail())) {
			showNotification("Không thể khóa SuperUser", "", "warning");
		} else {
			Messagebox.show("Bạn muốn khóa nhân viên này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
			Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) {
					if (Messagebox.ON_OK.equals(event.getName())) {
						setCheckApDung(false);
						save();
						BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
					}
				}
			});
		}
	}

	@Command
	public void moKhoaThanhVien(@BindingParam("vm") final Object vm) {
		Messagebox.show("Bạn muốn mở khóa nhân viên này?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
		Messagebox.QUESTION, new EventListener<Event>() {
			@Override
			public void onEvent(final Event event) {
				if (Messagebox.ON_OK.equals(event.getName())) {
					setCheckApDung(true);
					save();
					BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
				}
			}
		});
	}

	private boolean checkKichHoat;

	public boolean isCheckKichHoat() {
		return checkKichHoat;
	}

	public void setCheckKichHoat(boolean checkKichHoat) {
		this.checkKichHoat = checkKichHoat;
	}

	@Command
	public void toggleLock(@BindingParam("list") final Object obj) {
		String dialogText = "";
		if (!checkKichHoat) {
			dialogText = "Bạn muốn ngưng kích hoạt người dùng đã chọn?";
		} else {
			dialogText = "Bạn muốn kích hoạt người dùng đã chọn?";
		}
		Messagebox.show(dialogText, "Xác nhận", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
			new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) {
					if (Messagebox.ON_OK.equals(event.getName())) {
						if (checkKichHoat) {
							setCheckKichHoat(false);
						} else {
							setCheckKichHoat(true);
						}
						save();
						BindUtils.postNotifyChange(null, null, obj, "targetQueryNhanVien");
					}
				}
			});
	}

	@Command
	public void deleteNhanVienInListVaiTro(@BindingParam("vaitro") final VaiTro vt,
			@BindingParam("nhanvien") final NhanVien nv) {
		Messagebox.show("Bạn có chắc chắn muốn xóa vai trò " + vt.getTenVaiTro() + " của nhân viên " + nv.getHoVaTen(),
			"Xác nhận", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) {
					if (Messagebox.ON_OK.equals(event.getName())) {
						vaiTros.remove(vt);

						save();
						BindUtils.postNotifyChange(null, null, vt, "listNhanVien");
					}
				}
			});
	}

	@Command
	public void attachImages(@BindingParam("media") final Media media,
			@BindingParam("vmsgs") final ValidationMessages vmsgs) {
		if (media instanceof org.zkoss.image.Image) {
			if (media.getName().toLowerCase().endsWith(".png") || media.getName().toLowerCase().endsWith(".jpg")) {
				int lengthOfImage = media.getByteData().length;
				if (lengthOfImage > 10000000) {
					showNotification("Chọn hình ảnh có dung lượng nhỏ hơn 10MB.", "", "danger");
					return;
				} else {
					String tenFile = media.getName();

					tenFile = tenFile.replace(" ", "");
					tenFile = unAccent(tenFile.substring(0, tenFile.lastIndexOf("."))) + "_"
							+ Calendar.getInstance().getTimeInMillis() + tenFile.substring(tenFile.lastIndexOf("."));
					setAvatarImage((org.zkoss.image.Image) media);
					setPathAvatar(tenFile);
					if (vmsgs != null) {
						vmsgs.clearKeyMessages("errLabel");
					}
					BindUtils.postNotifyChange(null, null, this, "avatarImage");
					BindUtils.postNotifyChange(null, null, this, "pathAvatar");
				}
			} else {
				showNotification("Chọn hình ảnh theo đúng định dạng (*.png, *.jpg)", "", "danger");
			}
		} else {
			showNotification("Không phải hình ảnh", "", "danger");
		}
	}

	@Command
	public void deleteAvatarImage(@BindingParam("vm") NhanVien nhanVien) {
		Messagebox.show("Bạn có chắc chắn muốn xóa hình ảnh này ", "Xác nhận", Messagebox.OK | Messagebox.CANCEL,
			Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) {
					if (Messagebox.ON_OK.equals(event.getName())) {
						setAvatarImage(null);
						setPathAvatar(null);
						BindUtils.postNotifyChange(null, null, nhanVien, "avatarImage");
						showNotification("Đã xóa", "", "success");
					}
				}
			});
	}

	protected void saveImage() throws IOException {
		Image imageContent = getAvatarImage();
		if (imageContent != null) {
			String path = this.folderStoreImage();
			final File baseDir = new File(path.concat(getPathAvatar()));
			Files.copy(baseDir, imageContent.getStreamData());
			setAvatarImage(null);
		}
	}

	private void loadImageIsView() throws FileNotFoundException, IOException {
		if (getPathAvatar() != null && !"".equals(getPathAvatar())) {
			String root = ctx().getEnvironment().getProperty("filestore.root");
			String folder = root + ctx().getEnvironment().getProperty("filestore.folder");
			String filesFolder = folder + ctx().getEnvironment().getProperty("filestore.files");
			String imageFolder = filesFolder + ctx().getEnvironment().getProperty("filestore.folderimage");
			String path = imageFolder + getPathAvatar();
			if (new File(path).exists()) {
				String pathCompare = path.substring(0, path.lastIndexOf(File.separator) + 1)
						+ path.substring(path.lastIndexOf(File.separator) + 1);
				String imgName = pathCompare.substring(path.lastIndexOf(File.separator) + 1);
				try (FileInputStream fis = new FileInputStream(new File(path));) {
					setAvatarImage(new AImage(imgName, fis));
				}
			}
		}
	}

	@Command
	public void saveNhanVien(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) throws IOException {
		if (matKhauUpdate != null && !"".equals(matKhauUpdate)) {
			matKhau2 = matKhauUpdate;
		}
		if (matKhau2 != null && !matKhau2.isEmpty()) {
			updatePassword(matKhau2);
		}
		if (vaiTro != null) {
			vaiTros = new HashSet<VaiTro>();
			getVaiTros().add(vaiTro);
		}
		saveImage();
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, this, "*");
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}

	public String getCookieToken(long expire) {
		String token = getId() + ":" + expire + ":";
		return Base64.encodeBase64String(token.concat(DigestUtils.md5Hex(token + matKhau + ":" + salkey)).getBytes());
	}

	public void updatePassword(String pass) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		String salkey = getSalkey();

		if (salkey == null || salkey.equals("")) {
			salkey = encryptor.encryptPassword((new Date()).toString());
		}

		String passNoHash = pass + salkey;
		String passHash = encryptor.encryptPassword(passNoHash);
		setSalkey(salkey);
		setMatKhau(passHash);
	}

	@Transient
	public List<Object> getListThongBao() {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	@Transient
	public AbstractValidator getValidatorEmail() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (param == null || "".equals(param) || param.isEmpty()) {
					addInvalidMessage(ctx, "error", "Không được để trống trường này");
				} else if (!value.trim().matches(".+@.+\\.[a-z]+")) {
					addInvalidMessage(ctx, "error", "Email không đúng định dạng");
				} else {
					JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.email.eq(value))
							.where(QNhanVien.nhanVien.trangThai.ne(core().TT_DA_XOA));
					if (!NhanVien.this.noId()) {
						q.where(QNhanVien.nhanVien.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "error", "Email đã được sử dụng");
					}
				}
			}
		};
	}

	public boolean change = false;
	public boolean editable = false;

	@Transient
	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}

	@Transient
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Command
	public void ChangePassword() {
		setChange(isChange() ? false : true);
		BindUtils.postNotifyChange(null, null, this, "change");
	}

	@Command
	public void saveTaiKhoan() {
		if (matKhau2 != null && !matKhau2.isEmpty()) {
			updatePassword(matKhau2);
		}
		save();
		setChange(false);
		setEditable(false);
		BindUtils.postNotifyChange(null, null, this, "change");
		BindUtils.postNotifyChange(null, null, this, "editable");
	}

	@Command
	public void editableStatus() {
		setEditable(true);
		setChange(true);

		BindUtils.postNotifyChange(null, null, this, "editable");
		BindUtils.postNotifyChange(null, null, this, "change");
	}

	@Command
	public void lockNhanVien(@BindingParam("vm") Object vm, @BindingParam("object") NhanVien object) {
		Messagebox.show("Bạn có muốn khóa nhân viên này ?", "Khóa nhân viên", Messagebox.OK | Messagebox.CANCEL,
			Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(Event event) throws Exception {
					if (Messagebox.ON_OK.equals(event.getName())) {
						setCheckApDung(false);
						save();
						BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
					}
	
				}
	
			});
	}

	@Command
	public void openLockNhanVien(@BindingParam("vm") Object vm) {
		Messagebox.show("Bạn có muốn mở khóa nhân viên này ?", "Mở khóa nhân viên", Messagebox.OK | Messagebox.CANCEL,
			Messagebox.QUESTION, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					if (Messagebox.ON_OK.equals(event.getName())) {
						setCheckApDung(true);
						save();
						BindUtils.postNotifyChange(null, null, vm, "targetQueryNhanVien");
					}

				}

			});
	}

	@Transient
	public AbstractValidator getValidateSoDienThoai() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (!value.isEmpty() && !value.trim()
						.matches("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$")) {
					addInvalidMessage(ctx, "error", "Số điện thoại không đúng định dạng.");
				}
			}
		};
	}
	
	@Transient
	public AbstractValidator getValidateMatKhauCu() {
		NhanVien nhanVien = this;
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
				String passNoHash = value + nhanVien.getSalkey();
				if (!encryptor.checkPassword(passNoHash, nhanVien.getMatKhau())) {
					addInvalidMessage(ctx, "error", "Mật khẩu cũ không chính xác");
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateVaiTro() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				Object value = (Object) ctx.getProperty().getValue();
				if (value == null) {
					addInvalidMessage(ctx, "error", "Bạn chưa chọn vai trò người dùng");
				}
			}
		};
	}
	
	@Transient
	public AbstractValidator getValidatePhongBan() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				Object value = (Object) ctx.getProperty().getValue();
				if (value == null) {
					addInvalidMessage(ctx, "error", "Bạn chưa chọn phòng ban");
				}
			}
		};
	}

	@Command
	public void cancelNoVm(@BindingParam("wdn") Window wdn) {
		wdn.detach();
	}

	@Transient
	public AbstractValidator getValidateMatKhauMoiEdit() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = ((String) ctx.getProperty().getValue()).trim().replaceAll("\\s+", "");
				if (isChangePass() == true) {
					if (value.isEmpty() || "".equals(value) || value == null) {
						addInvalidMessage(ctx, "Mật khẩu mới không để trống");
					}
				}
			}
		};
	}

	@Transient
	public AbstractValidator getValidateMatKhauMoi() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String pass = (String) ctx.getBindContext().getValidatorArg("pass");
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (isChangePass() == true) {
					if (param.isEmpty() || "".equals(param) || param == null) {
						addInvalidMessage(ctx, "Xác nhận mật khẩu không để trống");
					} else {
						if (!value.equals(pass)) {
							addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp");
						}
					}
				}
			}
		};
	}

	@Command
	public void updateNhanVien(@BindingParam("vm") NhanVien vm, @BindingParam("wdn") Window wdn,
			@BindingParam("vm2") NhanVien vm2) throws IOException {
		if (matKhauUpdate != null && !"".equals(matKhauUpdate)) {
			matKhau2 = matKhauUpdate;
			updatePassword(matKhau2);
		}
		vm2.setAvatarImage(vm.getAvatarImage());
		saveImage();
		save();
		updateTokenNguoiDung();
		BindUtils.postNotifyChange(null, null, vm2, "*");
		wdn.detach();
	}

	// đã sửa đây
	public void updateTokenNguoiDung() {
		String cookieToken = this
				.getCookieToken(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
		Session zkSession = Sessions.getCurrent();
		zkSession.setAttribute("email", cookieToken);
		HttpServletResponse res = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
		Cookie cookie = new Cookie("email", cookieToken);
		cookie.setPath("/");
		cookie.setMaxAge(1000000000);
		res.addCookie(cookie);
	}

	@Command
	public void close(@BindingParam("wdn") Window wdn, @BindingParam("vm") NhanVien nhanVien)
			throws FileNotFoundException, IOException {
		JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.eq(nhanVien));
		nhanVien = q.fetchOne();
		BindUtils.postNotifyChange(null, null, nhanVien, "*");
		wdn.detach();
	}

	@Command
	public void redirectEditUser(@BindingParam("zul") String zul, @BindingParam("vm") NhanVien vm) {
		NhanVien nhanVienEdit = find(NhanVien.class).where(QNhanVien.nhanVien.eq(vm)).fetchFirst();
		Map<String, Object> args = new HashMap<>();
		args.put("vm", nhanVienEdit);
		args.put("vm2", vm);
		Executions.createComponents(zul, null, args);

	}
	
	@Transient
	public List<String> getListNhom() {
		List<String> listNhom = new ArrayList<>();
		for (VaiTro vaiTro : getVaiTros()) {
			listNhom.add(vaiTro.getAlias());
		}
		if (listNhom.isEmpty()) {
			listNhom.add("*");
		}
		return listNhom;
	}
}
