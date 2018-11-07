package vn.toancauxanh.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import com.liferay.portal.model.Image;

import vn.greenglobal.core.CoreObject;

public class CompressImage extends CoreObject<Image> {
	@SuppressWarnings("rawtypes")
	public static Media reduceImageQuality(long sizeThreshold, Media media, String destImg, String type)
			throws Exception {
		float quality = 1.0f;
		IIOImage image = null;

		if ("jpg".equals(type)) {
			BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(media.getByteData()));
			image = new IIOImage(originalImage, null, null);
		} else {
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(media.getByteData()));

			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			ImageIO.write(newBufferedImage, "jpg", new File(destImg));

			File file = new File(destImg);
			FileInputStream inputStream = new FileInputStream(file);
			BufferedImage originalImage = ImageIO.read(inputStream);
			image = new IIOImage(originalImage, null, null);
			type = "jpg";
		}

		long fileSize = media.getByteData().length;
		if (fileSize <= sizeThreshold) {
			return null;
		}

		Iterator iter = ImageIO.getImageWritersByFormatName(type);
		ImageWriter writer = (ImageWriter) iter.next();
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

		float percent = 0.1f; // 10% of 1

		while (fileSize > sizeThreshold) {
			if (percent >= quality) {
				percent = percent * 0.1f;
			}

			quality -= percent;

			File fileOut = new File(destImg);
			if (fileOut.exists()) {
				fileOut.delete();
			}
			FileImageOutputStream output = new FileImageOutputStream(fileOut);

			writer.setOutput(output);

			iwp.setCompressionQuality(quality);

			writer.write(null, image, iwp);

			File fileOut2 = new File(destImg);
			long newFileSize = fileOut2.length();
			if (newFileSize == fileSize) {
				break;
			} else {
				fileSize = newFileSize;
			}
			output.close();
		}

		writer.dispose();
		AImage img = new AImage(new File(destImg));

		File fileOut = new File(destImg);
		if (fileOut.exists()) {
			fileOut.delete();
		}
		return img;
	}

}
