package vn.toancauxanh.model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;

@Entity
@Table(name = "teptin")
public class TepTin extends Model<TepTin> {

	private String tenFile;
	private String pathFile;
	private String typeFile;
	private String nameHash;
	private String tenTaiLieu;
	
	private Media media;

	// ============

	public String getNameHash() {
		return nameHash;
	}

	public void setNameHash(String nameHash) {
		this.nameHash = nameHash;
	}

	public String getTypeFile() {
		return typeFile;
	}

	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}

	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getTenTaiLieu() {
		return tenTaiLieu;
	}

	public void setTenTaiLieu(String tenTaiLieu) {
		this.tenTaiLieu = tenTaiLieu;
	}

	@Transient
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public void saveFileTepTin() throws IOException {
		if (media != null) {
			final File baseDir = new File(folderStoreTaiLieu() + getNameHash());
			System.out.println("Ghi dữ liệu xuống ổ cứng: " + folderStoreTaiLieu() + getNameHash());
			Files.copy(baseDir, media.getStreamData());
			setMedia(null);
			BindUtils.postNotifyChange(null, null, this, "media");
		}
	}

	public void saveTepTin() throws IOException {
		saveFileTepTin();
		saveNotShowNotification();
	}
	
	@Command
	public void deleteFileDoanVao(@BindingParam("index") final int index, @BindingParam("vm") final DoanVao doanVao) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
				Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(final Event event) throws IOException {
						if (Messagebox.ON_OK.equals(event.getName())) {
							doanVao.getTepTins().remove(index);
							BindUtils.postNotifyChange(null, null, doanVao, "tepTins");
							showNotification("Đã xóa", "", "success");
						}
					}
				});
	}
	
	@Command
	public void reUploadFile(@BindingParam("medias") final Object medias, @BindingParam("index") final int index, @BindingParam("vm") final DoanVao doanVao) {
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
				tepTin.setTenTaiLieu(media.getName().substring(0, media.getName().lastIndexOf(".")));
				tepTin.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
				tepTin.setMedia(media);
				doanVao.getTepTins().set(index, tepTin);
				doanVao.getTepTins().forEach(obj -> {
					try {
						obj.saveFileTepTin();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				BindUtils.postNotifyChange(null, null, doanVao, "tepTins");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}

	@Transient
	public AbstractValidator getValidaTenFile() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if ("".equals(value)) {
					addInvalidMessage(ctx, "error", "Bạn chưa upload video");
					showNotification("Tên tệp tin không để trống", "", "danger");
				}
			}
		};
	}

	@Command
	public void downLoadTepTin(@BindingParam("ob") final TepTin object) throws MalformedURLException {
		if (!object.getPathFile().isEmpty()) {
			final String path = folderStoreTaiLieu() + object.getNameHash();
			System.out.println("Path download file: " + folderStoreTaiLieu() + object.getNameHash());
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
	public void uploadFile(@BindingParam("medias") final Object medias, @BindingParam("vm") final Object object,
			@BindingParam("name") final String name, @BindingParam("error") Label error) throws IOException {
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

				this.setNameHash(tenFile);
				this.setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
				this.setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
				this.setPathFile(folderStoreFilesLink() + folderStoreTepTin());
				this.setMedia(media);
				this.setNgayTao(null);
				System.out.println("PathFile lưu vào database: " + this.getPathFile());
				if (error != null) {
					error.setValue("");
				}
				saveFileTepTin();
				BindUtils.postNotifyChange(null, null, this, "*");
				BindUtils.postNotifyChange(null, null, object, name);
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}

	@Command
	public void deleteFile(@BindingParam("vm") final Object object, @BindingParam("ob") TepTin tepTin,
			@BindingParam("name") final String name) {
		Messagebox.show("Bạn muốn xóa tệp tin này không?", "Xác nhận", Messagebox.CANCEL | Messagebox.OK,
			Messagebox.QUESTION, new EventListener<Event>() {
				@Override
				public void onEvent(final Event event) throws IOException {
					if (Messagebox.ON_OK.equals(event.getName())) {
						tepTin.setNameHash(null);
						tepTin.setTypeFile(null);
						tepTin.setTenFile(null);
						tepTin.setPathFile(null);
						tepTin.setMedia(null);
						tepTin.setNgayTao(null);
						BindUtils.postNotifyChange(null, null, tepTin, "*");
						BindUtils.postNotifyChange(null, null, object, name);
						showNotification("Đã xóa", "", "success");
					}
				}
			});
	}
	
	@Command
	public void redirect(@BindingParam("ob") TepTin ob) {
		String serverName = "";
		String href = "";
		String ipBrowser = "";
		int serverPort = 0;
		serverName = Executions.getCurrent().getServerName();
		serverPort = Executions.getCurrent().getServerPort();
		ipBrowser = Executions.getCurrent().getContextPath();
		System.out.println("serverName: " + serverName);
		System.out.println("serverPort: " + serverPort);
		System.out.println("ipBrowser: " + ipBrowser);
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

}
