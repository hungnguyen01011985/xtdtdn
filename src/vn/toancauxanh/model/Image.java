package vn.toancauxanh.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.annotation.Command;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;

import vn.toancauxanh.service.ConvertImageFile;
import vn.toancauxanh.service.ResizeHinhAnh;

@Entity
@Table(name = "image")
public class Image extends Model<Image> {
	private String name;
	private String nameFileHash;
	private String smallImage;
	private String detailImage;
	private String bannerImage;
	private String videoImage;

	private String extension;
	private String imageUrl;
	private String description;
	private Media media;
	private boolean checkRemove;
	private Date date;

	// Transient
	private org.zkoss.image.Image imageContent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFileHash() {
		return nameFileHash;
	}

	public void setNameFileHash(String nameFileHash) {
		this.nameFileHash = nameFileHash;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public Image() {
		super();
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	private boolean flagSetImage = true;

	@Transient
	public org.zkoss.image.Image getImageContent() throws IOException {
		if (imageContent == null && !noId() && !core().TT_DA_XOA.equals(getTrangThai())) {
			if (flagSetImage) {
				String path = folderStoreFilesHome() + getImageUrl() + getNameFileHash();
				FileInputStream fis = new FileInputStream(new java.io.File(path));
				setImageContent(new AImage("", fis));
			}
		}

		return imageContent;
	}

	public void setImageContent(org.zkoss.image.Image imageContent) {
		this.imageContent = imageContent;
	}

	public Image(String name, String nameFileHash, String smallImage, String detailImage, String bannerImage,
			String videoImage, String extension, String imageUrl, String description,
			org.zkoss.image.Image imageContent) {
		super();
		this.name = name;
		this.nameFileHash = nameFileHash;
		this.smallImage = smallImage;
		this.detailImage = detailImage;
		this.bannerImage = bannerImage;
		this.videoImage = videoImage;
		this.extension = extension;
		this.imageUrl = imageUrl;
		this.description = description;
		this.imageContent = imageContent;
	}

	public Image(String name, String nameFileHash, String smallImage, String detailImage, String bannerImage,
			String videoImage, String extension, String imageUrl, String description,
			org.zkoss.image.Image imageContent, Media media, Date date) {
		super();
		this.name = name;
		this.nameFileHash = nameFileHash;
		this.smallImage = smallImage;
		this.detailImage = detailImage;
		this.bannerImage = bannerImage;
		this.videoImage = videoImage;
		this.extension = extension;
		this.imageUrl = imageUrl;
		this.description = description;
		this.media = media;
		this.date = date;
		this.imageContent = imageContent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public boolean isFlagSetImage() {
		return flagSetImage;
	}

	public void setFlagSetImage(boolean flagSetImage) {
		this.flagSetImage = flagSetImage;
	}

	@Transient
	public Media getMedia() {
		try {
			String path = folderStoreFilesHome() + getImageUrl() + getNameFileHash();
			FileInputStream fis = new FileInputStream(new java.io.File(path));
			setMedia(new AImage("", fis));
		} catch (Exception e) {
		}

		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public void saveFileImage() throws Exception {
		if (media != null) {
			final File baseDir = new File(folderStoreFilesHome() + getImageUrl() + getNameFileHash());
			Files.copy(baseDir, media.getStreamData());
			ConvertImageFile.convertImageFromPngToJpg(folderStoreFilesHome() + getImageUrl(), getNameFileHash());
			ResizeHinhAnh.saveMediumAndSmall(folderStoreFilesHome() + getImageUrl(), this);
			setMedia(null);
		}
	}

	@Command
	public void saveImage() throws Exception {
		saveFileImage();
		saveNotShowNotification();
	}

	@Transient
	public boolean isCheckRemove() {
		return checkRemove;
	}

	public void setCheckRemove(boolean checkRemove) {
		this.checkRemove = checkRemove;
	}

	@Transient
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getVideoImage() {
		return videoImage;
	}

	public void setVideoImage(String videoImage) {
		this.videoImage = videoImage;
	}

}
