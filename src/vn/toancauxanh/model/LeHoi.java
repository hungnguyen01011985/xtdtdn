package vn.toancauxanh.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.service.ConvertImageFile;
import vn.toancauxanh.service.LeHoiService;
import vn.toancauxanh.service.ResizeHinhAnh;

@Entity
@Table(name = "lehoi")
public class LeHoi extends Model<LeHoi> {
	private String name;
	private String address;
	private Video video;
	private TepTin teptin;
	private String ghichu;
	private String details = "";
	private String description = "";
	private String quyetDinhXepHangLehoi;
	private vn.toancauxanh.model.BanDo banDo = new BanDo();
	private Image avatarImage = new Image();
	private Image avatarImageTemp = new Image();
	private List<Image> images = new ArrayList<>();
	private List<Image> imagesTemp = new ArrayList<>();
	private List<Video> videos = new ArrayList<>();
	private List<TepTin> tepTins = new ArrayList<>();
	private List<TepTin> tepTinsTemp = new ArrayList<>();
	private Date thoiGianBatDau;
	private Date thoiGianKetThuc;
	private LoaiLeHoi loai = new LoaiLeHoi();
	private String capTinh = "49";
	private String capHuyen;
	private String capXa;
	private DonViHanhChinh donViCapTinh;
	private DonViHanhChinh donViCapHuyen;
	private DonViHanhChinh donViCapXa;
	private List<DonViHanhChinh> listDonViCapTinh;
	private List<DonViHanhChinh> listDonViCapHuyen;
	private List<DonViHanhChinh> listDonViCapXa;

	public String getQuyetDinhXepHangLehoi() {
		return quyetDinhXepHangLehoi;
	}

	public void setQuyetDinhXepHangLehoi(String quyetDinhXepHangLehoi) {
		this.quyetDinhXepHangLehoi = quyetDinhXepHangLehoi;
	}

	@Column(columnDefinition = "TEXT")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCapTinh() {
		return capTinh;
	}

	public void setCapTinh(String capTinh) {
		this.capTinh = capTinh;
	}

	public String getCapHuyen() {
		return capHuyen;
	}

	public void setCapHuyen(String capHuyen) {
		this.capHuyen = capHuyen;
	}

	public String getCapXa() {
		return capXa;
	}

	public void setCapXa(String capXa) {
		this.capXa = capXa;
	}
	
	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	@Transient
	public DonViHanhChinh getDonViCapTinh() {
		return donViCapTinh;
	}

	public void setDonViCapTinh(DonViHanhChinh donViCapTinh) {
		this.donViCapTinh = donViCapTinh;
	}

	@Transient
	public DonViHanhChinh getDonViCapHuyen() {
		return donViCapHuyen;
	}

	public void setDonViCapHuyen(DonViHanhChinh donViCapHuyen) {
		this.donViCapHuyen = donViCapHuyen;
	}

	@Transient
	public DonViHanhChinh getDonViCapXa() {
		return donViCapXa;
	}

	public void setDonViCapXa(DonViHanhChinh donViCapXa) {
		this.donViCapXa = donViCapXa;
	}

	@Transient
	public List<DonViHanhChinh> getListDonViCapTinh() {
		return listDonViCapTinh;
	}

	public void setListDonViCapTinh(List<DonViHanhChinh> listDonViCapTinh) {
		this.listDonViCapTinh = listDonViCapTinh;
	}

	@Transient
	public List<DonViHanhChinh> getListDonViCapHuyen() {
		return listDonViCapHuyen;
	}

	public void setListDonViCapHuyen(List<DonViHanhChinh> listDonViCapHuyen) {
		this.listDonViCapHuyen = listDonViCapHuyen;
	}

	@Transient
	public List<DonViHanhChinh> getListDonViCapXa() {
		return listDonViCapXa;
	}

	public void setListDonViCapXa(List<DonViHanhChinh> listDonViCapXa) {
		this.listDonViCapXa = listDonViCapXa;
	}

	public Date getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(Date thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public Date getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Date thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	@ManyToOne
	public LoaiLeHoi getLoai() {
		return loai;
	}

	public void setLoai(LoaiLeHoi loai) {
		this.loai = loai;
	}

	@Transient
	public TepTin getTeptin() {
		return teptin;
	}

	public void setTeptin(TepTin teptin) {
		this.teptin = teptin;
	}

	@Transient
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@ManyToOne
	public vn.toancauxanh.model.BanDo getBanDo() {
		return banDo;
	}

	public void setBanDo(vn.toancauxanh.model.BanDo banDo) {
		this.banDo = banDo;
	}

	@ManyToOne
	public Image getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(Image avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(columnDefinition = "TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LeHoi() {
		super();
	}

	@Command
	public void saveLeHoi(@BindingParam("list") Object list, @BindingParam("vm") LeHoi vm) throws Exception {
		// save ảnh đại diện của di tích
		avatarImage = (avatarImage == null) ? new Image() : avatarImage;
		if (!(avatarImage.noId()
				&& (avatarImage.getNameFileHash() == null || avatarImage.getNameFileHash().isEmpty()))) {
			avatarImage.saveNotShowNotification();
			saveImageAvatarToSever();
		} else {
			setAvatarImage(null);
		}
		// save danh sách hình ảnh
		for (Image image : getImages()) {
			image.saveImage();
		}
		for (Video video : getVideos()) {
			video.saveVideo();
		}
		for (TepTin teptin : getTepTins()) {
			teptin.saveTepTin();
		}

		// save bản đồ
		getBanDo().saveNotShowNotification();
		save();
		redirectBackPageListLeHoi("/cp/lehoi", null);
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lehoi_images", joinColumns = { @JoinColumn(name = "lehoi_id")

	}, inverseJoinColumns = { @JoinColumn(name = "images_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<Image> getImages() {
		for (Image img : images) {
			if (img.getNgayTao() != null) {
				img.setDate(img.getNgayTao());
			} else {
				img.setDate(new Date());
			}
		}
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lehoi_videos", joinColumns = { @JoinColumn(name = "lehoi_id") }, inverseJoinColumns = {
			@JoinColumn(name = "videos_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lehoi_teptins", joinColumns = { @JoinColumn(name = "lehoi_id") }, inverseJoinColumns = {
			@JoinColumn(name = "teptins_id") })
	@Fetch(value = FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
	public List<TepTin> getTepTins() {
		return tepTins;
	}

	@Command
	public void redirectPageLeHoi(@BindingParam("url") String url, @BindingParam("vm") LeHoi vm, @BindingParam("service") LeHoiService leHoiService) {
		if (vm != null) {
			url = url.concat("/" + vm.getId());
		}
		leHoiService.putArgSessionPage();
		Executions.getCurrent().sendRedirect(url);
	}
	
	@Command
	public void redirectBackPageListLeHoi(@BindingParam("url") String url, @BindingParam("vm") LeHoi vm) {
		if (vm != null) {
			url = url.concat("/" + vm.getId());
		}
		Executions.getCurrent().sendRedirect(url);
	}

	@Command
	public void uploadImage(@BindingParam("image") Object[] objectImage, @BindingParam("vm") Object vm)
			throws IOException {
		for (Object item : objectImage) {
			Media media = (Media) item;
			if (media instanceof org.zkoss.image.Image) {
				if (media.getName().toLowerCase().endsWith(".png") || media.getName().toLowerCase().endsWith(".jpeg")
						|| media.getName().toLowerCase().endsWith(".jpg")) {
					String tenFile = media.getName().substring(0, media.getName().lastIndexOf(".")) + "_"
							+ Calendar.getInstance().getTimeInMillis()
							+ media.getName().substring(media.getName().lastIndexOf("."));
					Image itemImage = new Image();
					itemImage.setName(media.getName());
					itemImage.setNameFileHash(tenFile);
					itemImage.setDetailImage("d_" + tenFile);
					itemImage.setSmallImage("s_" + tenFile);
					itemImage.setBannerImage("b_" + tenFile);
					itemImage.setVideoImage("v_" + tenFile);
					itemImage.setExtension(media.getName().substring(media.getName().lastIndexOf(".")));
					itemImage.setImageUrl(folderStoreFilesLink()+folderStoreFilesLeHoi()+folderStoreFilesImage());
					itemImage.setMedia(media);
					itemImage.setDate(new Date());
					// Add 1 list<Media> để lưu các media này
					imagesTemp.add(itemImage);
				} else {
					showNotification("Chọn hình ảnh theo đúng định dạng (*.png, *.jpg, *.jpeg)", "", "danger");
				}
			} else {
				showNotification("File tải lên không phải hình ảnh!", "", "danger");
			}
		}

		BindUtils.postNotifyChange(null, null, vm, "imagesTemp");
	}

	@Command
	public void deleteImage(@BindingParam("image") Image image, @BindingParam("vm") Object vm) {
		Messagebox.show("Bạn muốn xóa hình ảnh này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							checkRemoveImage = false;
							imagesTemp.remove(image);
							BindUtils.postNotifyChange(null, null, vm, "imagesTemp");
							BindUtils.postNotifyChange(null, null, vm, "checkShowButtonRemovMultiImage");
						}

					}
				});
	}

	@Command
	public void removeMultiImages(@BindingParam("imagesTemp") List<Image> imagesTemp, @BindingParam("vm") Object vm) {
		Messagebox.show("Bạn muốn xóa hình ảnh này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							int i = 0;
							// Tạo một hash map để lưu tạm list . Nếu trùng thì
							// xóa các phần tử của list images
							Map<Integer, Image> map = new HashMap<>();
							for (Image itemImage : imagesTemp) {
								if (itemImage.isCheckRemove()) {
									map.put(i, itemImage);
									i++;
								}
							}
							for (Map.Entry<Integer, Image> mapTemp : map.entrySet()) {
								imagesTemp.remove(mapTemp.getValue());
							}

							BindUtils.postNotifyChange(null, null, vm, "imagesTemp");
							BindUtils.postNotifyChange(null, null, vm, "checkShowButtonRemovMultiImage");
						}

					}

				});

	}

	private boolean checkLoadGallaryImage = true;

	@Transient
	public boolean isCheckLoadGallaryImage() {
		return checkLoadGallaryImage;
	}

	public void setCheckLoadGallaryImage(boolean checkLoadGallaryImage) {
		this.checkLoadGallaryImage = checkLoadGallaryImage;
	}

	public void saveImageAvatarToSever() throws Exception {
		java.io.File baseSaveFile = new java.io.File(folderStore() + folderStoreFilesImage() + avatarImage.getNameFileHash());
		// lưu vào sever
		Files.copy(baseSaveFile, avatarImage.getImageContent().getStreamData());
		ConvertImageFile.convertImageFromPngToJpg(folderStore() + folderStoreFilesImage(),  avatarImage.getNameFileHash());
		ResizeHinhAnh.saveMediumAndSmall(folderStore() + folderStoreFilesImage(), avatarImage);
	}

	@Command
	public void closePopupImages(@BindingParam("wdn") Window window, @BindingParam("vm") LeHoi vm) {
		Messagebox.show("Bạn muốn hủy bỏ tất cả thay đổi ?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							imagesTemp.clear();
							checkRemoveImage = true;
							avatarImageTemp = null;
							checkRemoveAvatarImage = true;
							window.detach();
						}
					}
				});
	}

	@Command
	public void closePopupVideo(@BindingParam("wdn") Window window, @BindingParam("vm") LeHoi vm) {
		Messagebox.show("Bạn muốn hủy bỏ tất cả thay đổi ?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							window.detach();
						}
					}
				});
	}
	
	@Command
	public void deleteVideo(@BindingParam("item") Video video, @BindingParam("vmArgs") Object vm) {
		Messagebox.show("Bạn muốn xóa video này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							videos.remove(video);
							BindUtils.postNotifyChange(null, null, vm, "videos");
							showNotification("Đã xóa video", "", "success");
						}

					}
				});
	}

	@Command
	public void clickImageToRemoveMulti(@BindingParam("vm") Object vm) {
		BindUtils.postNotifyChange(null, null, vm, "checkShowButtonRemovMultiImage");
	}

	private boolean checkShowButtonRemovMultiImage;

	@Transient
	public boolean isCheckShowButtonRemovMultiImage() {
		checkShowButtonRemovMultiImage = false;
		for (Image item : imagesTemp) {
			if (item.isCheckRemove()) {
				checkShowButtonRemovMultiImage = true;
				break;
			}
		}
		return checkShowButtonRemovMultiImage;
	}

	public void setCheckShowButtonRemovMultiImage(boolean checkShowButtonRemovMultiImage) {
		this.checkShowButtonRemovMultiImage = checkShowButtonRemovMultiImage;
	}

	@Command
	public void addVideoToList(@BindingParam("item") Video video, @BindingParam("wdn") Window wdn,
			@BindingParam("list") LeHoi leHoi) {
		videos.add(video);
		BindUtils.postNotifyChange(null, null, leHoi, "videos");
		close(wdn);
	}

	@Command
	public void close(@BindingParam("wdn") Window wdn) {
		showNotification("Đã lưu", "", "success");
		wdn.detach();
	}
	
	@Command
	public void closeView(@BindingParam("wdn") Window wdn) {
		wdn.detach();
	}

	@Transient
	public AbstractValidator getValidatorNameLeHoi() {
		return new AbstractValidator() {

			@Override
			public void validate(ValidationContext ctx) {
				String nameTemp = (String) ctx.getProperty().getValue();
				if (nameTemp.isEmpty()) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				} else {
					JPAQuery<LeHoi> q = find(LeHoi.class).where(QLeHoi.leHoi.daXoa.isFalse())
							.where(QLeHoi.leHoi.trangThai.ne(core().TT_DA_XOA)).where(QLeHoi.leHoi.name.eq(nameTemp));
					if (!LeHoi.this.noId()) {
						q.where(QLeHoi.leHoi.id.ne(getId()));
					}

					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại lễ hội này");
					}
				}
			}
		};
	}

	@Command
	public void closeFile(@BindingParam("wdn") Window wdn) {
		tepTins = tepTinsTemp;
		wdn.detach();
	}

	@GlobalCommand
	public void updateVideo(@BindingParam("list") LeHoi leHoi, @BindingParam("item") Video video) {
		List<Video> listVideoRecent = leHoi.getVideos();
		int index = listVideoRecent.indexOf(video);
		if (index > -1) {
			listVideoRecent.set(index, video);
		} else {
			listVideoRecent.add(video);
		}
		Collections.reverse(listVideoRecent);
		BindUtils.postNotifyChange(null, null, leHoi, "videos");
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
					tepTin.setPathFile(folderStoreFilesLink()+folderStoreFilesLeHoi()+folderStoreFilesTepTin());
					tepTin.setMedia(media);
					tepTinsTemp.add(tepTin);
					BindUtils.postNotifyChange(null, null, this, "tepTinsTemp");
				}
			} else {
				showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
						"Có tệp không đúng định dạng", "danger");
			}
		}
	}

	@Command

	public void deleteFile(@BindingParam("item") TepTin tepTin, @BindingParam("vm") LeHoi leHoi) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							tepTinsTemp.remove(tepTin);
							BindUtils.postNotifyChange(null, null, leHoi, "tepTinsTemp");
							showNotification("Đã xóa", "", "success");
						}
					}
				});
	}

	@Command
	public void saveImage(@BindingParam("wdn") Window wdn) {
		images.clear();
		for (Image img : imagesTemp) {
			images.add(img);
		}
		imagesTemp.clear();
		checkRemoveImage = true;
		avatarImage = null;
		avatarImage = avatarImageTemp;
		avatarImageTemp = null;
		checkRemoveAvatarImage = true;
		showNotification("Đã lưu", "", "success");
		wdn.detach();
	}

	public void deleteFileLeHoi(@BindingParam("item") TepTin tepTin, @BindingParam("vm") LeHoi leHoi) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							buttonRemove = true;
							tepTinsTemp.remove(tepTin);
							BindUtils.postNotifyChange(null, null, leHoi, "tepTinsTemp");
							showNotification("Đã xóa", "", "success");
							buttonRemove = false;
						}
					}
				});
	}

	@Command
	public void viewFile(@BindingParam("item") TepTin tepTin) throws FileNotFoundException, IOException {
		if (tepTin.getMedia() != null) {
			Filedownload.save(tepTin.getMedia());
		} else {
			loadFileData(tepTin);
		}

	}

	private void loadFileData(TepTin tepTin) throws FileNotFoundException, IOException {
		String s1 = ctx().getEnvironment().getProperty("filestore.root");
		String path = s1 + tepTin.getPathFile() + tepTin.getNameHash();
		if (new File(path).exists()) {
			File file = new File(path);
			org.zkoss.zul.Filedownload.save(file, null);
		} else {
			showNotification("File không tồn tại trên hệ thống", "", "error");
		}
	}

	@Command
	public void saveEditFile(@BindingParam("wdn") Window wdn) {
		tepTins.clear();
		for (TepTin tepTin : getTepTinsTemp()) {
			tepTins.add(tepTin);
		}
		tepTinsTemp.clear();
		setButtonRemove(true);
		wdn.detach();
	}
	
	@Command
	public void redirectPageViewLeHoi(@BindingParam("url") String url, @BindingParam("vm") LeHoi vm) {
		url = url.concat("/" + vm.getId());
		Executions.getCurrent().sendRedirect(url);
	}
	
	@Command
	public void cancelEditFile(@BindingParam("wdn") Window window) {
		Messagebox.show("Bạn muốn hủy bỏ tất cả thay đổi ?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							tepTinsTemp.clear();
							setButtonRemove(true);
							window.detach();
						}
					}
				});
	}

	private boolean checkRemoveImage = true;
	private boolean checkRemoveAvatarImage = true;

	@Transient
	public Image getAvatarImageTemp() {
		if (checkRemoveAvatarImage) {
			avatarImageTemp = avatarImage;
		}
		checkRemoveAvatarImage = false;

		return avatarImageTemp;
	}

	public void setAvatarImageTemp(Image avatarImageTemp) {
		this.avatarImageTemp = avatarImageTemp;
	}

	@Transient
	public List<Image> getImagesTemp() throws IOException {
		if (checkRemoveImage) {
			for (Image image : getImages()) {
				Image itemImg = new Image(image.getName(), image.getNameFileHash(), image.getSmallImage(),
						image.getDetailImage(), image.getBannerImage(), image.getVideoImage(), image.getExtension(),
						image.getImageUrl(), image.getDescription(), image.getImageContent(), image.getMedia(),
						image.getDate());
				imagesTemp.add(itemImg);
			}
		}
		checkRemoveImage = false;

		// Sort list ảnh, khi ảnh tạo mới sẽ hiển thị đầu danh sách
		Collections.sort(imagesTemp, new Comparator<Image>() {
			@Override
			public int compare(final Image object1, final Image object2) {
				return object2.getDate().compareTo(object1.getDate());
			}
		});

		return imagesTemp;
	}

	public void setImagesTemp(List<Image> imagesTemp) {
		this.imagesTemp = imagesTemp;
	}

	private boolean buttonRemove = true;

	public boolean isButtonRemove() {
		return buttonRemove;
	}

	public void setButtonRemove(boolean buttonRemove) {
		this.buttonRemove = buttonRemove;
	}

	@Transient
	public List<TepTin> getTepTinsTemp() {
		if (buttonRemove) {
			for (TepTin tepTin : tepTins) {
				TepTin tepTinNew = new TepTin();
				if (!tepTin.noId()) {
					tepTinNew.setId(tepTin.getId());
					tepTinNew.setNgayTao(tepTin.getNgayTao());
					tepTinNew.setNguoiTao(tepTin.getNguoiTao());
				}
				tepTinNew.setNameHash(tepTin.getNameHash());
				tepTinNew.setTenFile(tepTin.getTenFile());
				tepTinNew.setPathFile(tepTin.getPathFile());
				tepTinNew.setTypeFile(tepTin.getTypeFile());
				tepTinNew.setMedia(tepTin.getMedia());
				tepTinsTemp.add(tepTinNew);
			}
		}
		setButtonRemove(false);
		return tepTinsTemp;
	}

	public void setTepTinsTemp(List<TepTin> tepTinsTemp) {
		this.tepTinsTemp = tepTinsTemp;
	}

	@Command
	public void editNameFile(@BindingParam("item") TepTin tepTin) {
		int postion = tepTinsTemp.indexOf(tepTin);
		tepTinsTemp.set(postion, tepTin);
		BindUtils.postNotifyChange(null, null, this, "tepTinsTemp");
	}

	public void setTepTins(List<TepTin> tepTins) {
		this.tepTins = tepTins;
	}

	@Transient
	public AbstractValidator getValidatorDateKetThuc() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				Date date = (Date) ctx.getProperty().getValue();
				final Date dateStart = (Date) ctx.getValidatorArg("date");
				if (dateStart != null) {
					if (date == null) {
						addInvalidMessage(ctx, "Bạn chưa chọn ngày kết thúc");
					} else {
						if (date.before(dateStart)) {
							addInvalidMessage(ctx, "Ngày kết thúc chưa đúng");
						}
					}
				}
			}
		};
	}

	@Transient
	public List<Image> getList5Images() {
		List<Image> list = new ArrayList<>();
		int limit = (images.size() > 5) ? 5 : images.size();

		// Nếu size > 0 thì hiển thị list images tương ứng
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				list.add(images.get(i));
			}
		} else {
			// Nếu chưa có ảnh thì set ảnh bằng ảnh đại diện
			if (avatarImage != null) {
				Image image = avatarImage;
				list.add(image);
			}
		}

		return list;
	}

	@Transient
	public List<Image> getList5ImageSlider() {
		List<Image> list = new ArrayList<>();
		int limit = (images.size() > 5) ? 5 : images.size();

		// Nếu size > 0 thì hiển thị list images tương ứng
		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				list.add(images.get(i));
			}
		} else {
			// Nếu chưa có ảnh thì set ảnh bằng ảnh đại diện
			if (avatarImage != null) {
				Image image = avatarImage;
				list.add(image);
			}
		}
		Collections.reverse(list);
		return list;
	}

	
}
