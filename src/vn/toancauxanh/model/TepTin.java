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
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

@Entity
@Table(name = "teptin")
public class TepTin extends Model<TepTin> {

	private String tenFile;
	private String pathFile;
	private Media media;
	private String typeFile;
	private String nameHash;

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

	@Transient
	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public void saveFileTepTin() throws IOException {
		if (media != null) {
			final File baseDir = new File((folderStoreFilesHome() + getPathFile()).concat(getNameHash()));
			Files.copy(baseDir, media.getStreamData());
			setMedia(null);
			BindUtils.postNotifyChange(null, null, this, "media");
		}
	}

	public void saveTepTin() throws IOException {
		saveFileTepTin();
		saveNotShowNotification();
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
	public void downLoadTepTin() throws MalformedURLException {
		if (!this.getPathFile().isEmpty()) {
			final String path = folderRoot() + this.getPathFile();
			if (new java.io.File(path).exists()) {
				try {
					Filedownload.save(
							new URL("file://" + folderRoot() + this.getPathFile() + this.getNameHash()).openStream(),
							null, this.getTenFile().concat(this.getTypeFile()));
				} catch (IOException e) {
					showNotification("Không tìm thấy file", "Thông báo", "danger");
				}
			} else {
				showNotification("Không tìm thấy file", "Thông báo", "danger");
			}
		}
	}
	
	@Command
	public void uploadFile(@BindingParam("medias") final Object medias, @BindingParam("vm") final DuAn object,
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
				this.setNameHash(tenFile);
				this.setTypeFile(tenFile.substring(tenFile.lastIndexOf(".")));
				this.setTenFile(media.getName().substring(0, media.getName().lastIndexOf(".")));
				this.setPathFile(folderStoreFilesLink() + folderStoreFilesTepTin());
				this.setMedia(media);
				System.out.println("z"+this.getTenFile());
				BindUtils.postNotifyChange(null, null, object, "*");
			}
		} else {
			showNotification("Chỉ chấp nhận các tệp nằm trong các định dạng sau : pdf, doc, docx, xls, xlsx",
					"Có tệp không đúng định dạng", "danger");
		}
	}
	@Command
	public void deleteFile(@BindingParam("vm") final DuAn vm, @BindingParam("ob") TepTin ob, @BindingParam("name") final String name) {
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
						System.out.println(vm.getId());
						BindUtils.postNotifyChange(null, null, vm, "*");
						showNotification("Đã xóa", "", "success");
					}
				}
			});
	}

}
