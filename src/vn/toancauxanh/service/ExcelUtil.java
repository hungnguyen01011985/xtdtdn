package vn.toancauxanh.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.zul.Filedownload;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.PhongBan;
import vn.toancauxanh.model.VaiTro;

public class ExcelUtil {

	private static ExcelUtil instance;

	public static ExcelUtil getInStance() {
		if (instance == null) {
			instance = new ExcelUtil();
		}
		return instance;
	}

	public static String formatDouble(double number) {
		// #.###,##
		final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator(',');
		decimalFormatSymbols.setGroupingSeparator('.');
		final DecimalFormat decimalFormat = new DecimalFormat("#.###", decimalFormatSymbols);
		return decimalFormat.format(number);
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void setBorderMore(Workbook wb, Row row, Cell c, int begin, int end, int fontSize) {
		final CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setBorderLeft((short) 1);
		Font font = wb.createFont();
		font.setFontName("Times New Roman");

	}

	@SuppressWarnings({ "unused", "deprecation" })
	private static void setBorderMore(int flag, Workbook wb, Row row, Cell c, int begin, int end, int fontSize) {
		final CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setBorderLeft((short) 1);

		Font font = wb.createFont();
		font.setFontName("Times New Roman");
		for (int i = begin; i < end; i++) {
			Cell c1 = row.createCell(i);
			if (flag == 1) {
				cellStyle.setBorderTop((short) 2);
				font.setFontHeightInPoints((short) fontSize);
				cellStyle.setFont(font);
			} else {
				cellStyle.setBorderTop((short) 1);
			}
			if (flag == 2) {
				cellStyle.setBorderBottom((short) 2);
				font.setFontHeightInPoints((short) fontSize);
				cellStyle.setFont(font);
			} else {
				cellStyle.setBorderBottom((short) 1);
			}

			if (i == (end - 1)) {
				cellStyle.setBorderRight((short) 2);
			} else {
				cellStyle.setBorderRight((short) 1);
			}
			if (flag == 3) {
				cellStyle.setBorderTop((short) 1);
				cellStyle.setBorderBottom((short) 1);
				font.setFontHeightInPoints((short) fontSize);
				cellStyle.setFont(font);
				cellStyle.setBorderRight((short) 1);
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			}
			c1.setCellStyle(cellStyle);
		}
	}

	@SuppressWarnings("deprecation")
	public static CellStyle setBorderAndFont(final Workbook workbook, final int borderSize, final boolean isTitle,
			final int fontSize, final String fontColor, final String textAlign) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);

		cellStyle.setBorderTop((short) borderSize); // single line border
		cellStyle.setBorderBottom((short) borderSize); // single line border
		cellStyle.setBorderLeft((short) borderSize); // single line border
		cellStyle.setBorderRight((short) borderSize); // single line border
		cellStyle.setAlignment(CellStyle.VERTICAL_CENTER);

		if (textAlign.equals("RIGHT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		} else if (textAlign.equals("CENTER")) {
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		} else if (textAlign.equals("LEFT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		}
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		final Font font = workbook.createFont();
		font.setFontName("Times New Roman");
		if (isTitle) {

			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		}
		if (fontColor.equals("RED")) {
			font.setColor(Font.COLOR_RED);
		} else if (fontColor.equals("BLUE")) {
			font.setColor(HSSFColor.BLUE.index);
		} else if (fontColor.equals("ORANGE")) {
			font.setColor(HSSFColor.ORANGE.index);
		} else {

		}
		font.setFontHeightInPoints((short) fontSize);
		cellStyle.setFont(font);

		return cellStyle;
	}

	public static void exportChiDaoDieuHanh(String title, String fileName, String sheetname, List<Object[]> list)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetname);
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue(title);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(1);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(2);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(3);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(4);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			// set column width
			sheet1.setColumnWidth(0, 16 * 256);
			sheet1.setColumnWidth(1, 30 * 256);
			sheet1.setColumnWidth(2, 36 * 256);
			sheet1.setColumnWidth(3, 16 * 256);
			sheet1.setColumnWidth(4, 16 * 256);
			// Generate rows header of grid
			idx++;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("STT");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(1);
			c.setCellValue("Nội dung chỉ đạo");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(2);
			c.setCellValue("Đơn vị thực hiện");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(3);
			c.setCellValue("Thời hạn báo cáo");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(4);
			c.setCellValue("Tình trạng xử lý");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));

			idx++;
			int i = 1;
			CellStyle cellStyleLeft = setBorderAndFont(wb, 1, i == list.size(), 11, "", "LEFT");
			CellStyle cellStyleCenter = setBorderAndFont(wb, 1, i == list.size(), 11, "", "CENTER");
			for (Object[] ob : list) {
				row = sheet1.createRow(idx);
				c = row.createCell(0);
				c.setCellValue(ob[0] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(1);
				c.setCellValue(ob[1] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(2);
				c.setCellValue(ob[2] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(3);
				c.setCellValue(ob[3] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(4);
				c.setCellValue(ob[4] + "");
				c.setCellStyle(cellStyleCenter);
				i++;
				idx++;
			}

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}

	public static void exportDonThu(String title, String fileName, String sheetname, List<Object[]> list)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetname);
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue(title);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(1);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(2);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(3);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(4);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(5);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(6);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(7);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
			// set column width
			sheet1.setColumnWidth(0, 7 * 256);
			sheet1.setColumnWidth(1, 30 * 256);
			sheet1.setColumnWidth(2, 16 * 256);
			sheet1.setColumnWidth(3, 16 * 256);
			sheet1.setColumnWidth(4, 20 * 256);
			sheet1.setColumnWidth(5, 30 * 256);
			sheet1.setColumnWidth(6, 16 * 256);
			sheet1.setColumnWidth(7, 16 * 256);
			// Generate rows header of grid
			idx++;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("STT");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(1);
			c.setCellValue("Nội dung đơn thư");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(2);
			c.setCellValue("Ngày nhận");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(3);
			c.setCellValue("Người đứng đơn");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(4);
			c.setCellValue("Lĩnh vực đơn");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(5);
			c.setCellValue("Đơn vị");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(6);
			c.setCellValue("Thời hạn báo cáo");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(7);
			c.setCellValue("Tình trạng xử lý");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));

			idx++;
			CellStyle cellStyleLeft = setBorderAndFont(wb, 1, false, 11, "", "LEFT");
			CellStyle cellStyleCenter = setBorderAndFont(wb, 1, false, 11, "", "CENTER");
			for (Object[] ob : list) {
				row = sheet1.createRow(idx);
				c = row.createCell(0);
				c.setCellValue(ob[0] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(1);
				c.setCellValue(ob[1] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(2);
				c.setCellValue(ob[2] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(3);
				c.setCellValue(ob[3] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(4);
				c.setCellValue(ob[4] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(5);
				c.setCellValue(ob[5] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(6);
				c.setCellValue(ob[6] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(7);
				c.setCellValue(ob[7] + "");
				c.setCellStyle(cellStyleCenter);
				idx++;
			}

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}

	public static void exportYKienPhanAnh(String title, String fileName, String sheetname, List<Object[]> list)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetname);
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue(title);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(1);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(2);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(3);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			c = row.createCell(4);
			c.setCellStyle(setBorderAndFont(wb, 1, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			// set column width
			sheet1.setColumnWidth(0, 7 * 256);
			sheet1.setColumnWidth(1, 30 * 256);
			sheet1.setColumnWidth(2, 30 * 256);
			sheet1.setColumnWidth(3, 16 * 256);
			sheet1.setColumnWidth(4, 16 * 256);
			// Generate rows header of grid
			idx++;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("STT");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(1);
			c.setCellValue("Nội dung kiến nghị");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(2);
			c.setCellValue("Đơn vị thực hiện");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(3);
			c.setCellValue("Thời hạn báo cáo");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));
			c = row.createCell(4);
			c.setCellValue("Tình trạng xử lý");
			c.setCellStyle(setBorderAndFont(wb, 1, true, 11, "", "CENTER"));

			idx++;
			CellStyle cellStyleLeft = setBorderAndFont(wb, 1, false, 11, "", "LEFT");
			CellStyle cellStyleCenter = setBorderAndFont(wb, 1, false, 11, "", "CENTER");
			for (Object[] ob : list) {
				row = sheet1.createRow(idx);
				c = row.createCell(0);
				c.setCellValue(ob[0] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(1);
				c.setCellValue(ob[1] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(2);
				c.setCellValue(ob[2] + "");
				c.setCellStyle(cellStyleLeft);
				c = row.createCell(3);
				c.setCellValue(ob[3] + "");
				c.setCellStyle(cellStyleCenter);
				c = row.createCell(4);
				c.setCellValue(ob[4] + "");
				c.setCellStyle(cellStyleCenter);
				idx++;
			}

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}

	/* HÀM XUẤT EXCEL BXTDTDN */
	/* begin */

	public static void exportDanhSachPhongBan(String fileName, String sheetName, List<PhongBan> list, String title)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetName);
			sheet1.getPrintSetup().setLandscape(true);
			sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			sheet1.createFreezePane(0, 3);

			CellStyle styleLeft = setBorderAndFont(wb, 1, false, 8, "", "LEFT");
			CellStyle styleLeftHeader = setBorderAndFont(wb, 1, true, 9, "", "LEFT");
			CellStyle styleCenter = setBorderAndFont(wb, 1, false, 8, "", "CENTER");
			CellStyle styleCenterHeader = setBorderAndFont(wb, 1, true, 9, "", "CENTER");
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("DANH SÁCH " + title.toUpperCase());
			c.setCellStyle(setBorderAndFont(wb, 0, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

			// set column width

			sheet1.setColumnWidth(0, 10 * 256);
			sheet1.setColumnWidth(1, 30 * 256);
			sheet1.setColumnWidth(2, 90 * 256);

			// Generate rows header of grid
			idx++;
			idx++;
			row = sheet1.createRow(idx);
			idx++;

			int sttHeader = 0;

			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("STT");
			c.setCellStyle(styleCenterHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Phòng ban");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Mô tả");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			int i = 1;

			for (PhongBan phongBan : list) {
				row = sheet1.createRow(idx);
				int sttData = 0;

				c = row.createCell(sttData);
				sttData++;
				c.setCellValue(i);
				c.setCellStyle(styleCenter);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(phongBan.getTen());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(phongBan.getMoTa());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;

				i++;
				idx++;
			}

			idx++;
			idx++;

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}

	public static void exportDanhSachVaiTro(String fileName, String sheetName, List<VaiTro> list, String title)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetName);
			sheet1.getPrintSetup().setLandscape(true);
			sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			sheet1.createFreezePane(0, 3);

			CellStyle styleLeft = setBorderAndFont(wb, 1, false, 8, "", "LEFT");
			CellStyle styleLeftHeader = setBorderAndFont(wb, 1, true, 9, "", "LEFT");
			CellStyle styleCenter = setBorderAndFont(wb, 1, false, 8, "", "CENTER");
			CellStyle styleCenterHeader = setBorderAndFont(wb, 1, true, 9, "", "CENTER");
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("DANH SÁCH " + title.toUpperCase());
			c.setCellStyle(setBorderAndFont(wb, 0, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

			// set column width

			sheet1.setColumnWidth(0, 10 * 256);
			sheet1.setColumnWidth(1, 30 * 256);
			sheet1.setColumnWidth(2, 90 * 256);

			// Generate rows header of grid
			idx++;
			idx++;
			row = sheet1.createRow(idx);
			idx++;

			int sttHeader = 0;

			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("STT");
			c.setCellStyle(styleCenterHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Tên vai trò");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Ngày cập nhật");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			int i = 1;
			DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			for (VaiTro vaiTro : list) {
				row = sheet1.createRow(idx);
				int sttData = 0;

				c = row.createCell(sttData);
				sttData++;
				c.setCellValue(i);
				c.setCellStyle(styleCenter);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(vaiTro.getTenVaiTro());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(df.format(vaiTro.getNgaySua()));
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;

				i++;
				idx++;
			}

			idx++;
			idx++;

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}
	
	public static void exportThongKeCongViec(String fileName, String sheetName, List<GiaoViec> list, String title)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetName);
			sheet1.getPrintSetup().setLandscape(true);
			sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			sheet1.createFreezePane(0, 3);

			CellStyle styleLeft = setBorderAndFont(wb, 1, false, 12, "", "LEFT");
			CellStyle styleLeftHeader = setBorderAndFont(wb, 1, true, 13, "", "LEFT");
			CellStyle styleCenter = setBorderAndFont(wb, 1, false, 12, "", "CENTER");
			CellStyle styleCenterHeader = setBorderAndFont(wb, 1, true, 13, "", "CENTER");
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("DANH SÁCH " + title.toUpperCase());
			c.setCellStyle(setBorderAndFont(wb, 0, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

			// set column width

			sheet1.setColumnWidth(0, 10 * 256);
			sheet1.setColumnWidth(1, 40 * 256);
			sheet1.setColumnWidth(2, 40 * 256);
			sheet1.setColumnWidth(3, 40 * 256);
			sheet1.setColumnWidth(4, 40 * 256);
			sheet1.setColumnWidth(5, 20 * 256);
			sheet1.setColumnWidth(6, 20 * 256);
			sheet1.setColumnWidth(7, 20 * 256);

			// Generate rows header of grid
			idx++;
			idx++;
			row = sheet1.createRow(idx);
			idx++;

			int sttHeader = 0;

			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("STT");
			c.setCellStyle(styleCenterHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Tên công việc");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Tên dự án/ Tên đoàn vào");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Người giao việc");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			
			c.setCellValue("Người phụ trách");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Ngày giao việc");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Hạn thực hiện");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Trạng thái");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			int i = 1;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for (GiaoViec giaoViec : list) {
				row = sheet1.createRow(idx);
				int sttData = 0;

				c = row.createCell(sttData);
				sttData++;
				c.setCellValue(i);
				c.setCellStyle(styleCenter);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(giaoViec.getTenCongViec());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				if (LoaiCongViec.DU_AN.equals(giaoViec.getLoaiCongViec())) {
					c.setCellValue(giaoViec.getDuAn().getTenDuAn());
				} else {
					c.setCellValue(giaoViec.getDoanVao().getTenDoanVao());
				}
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaoViec.getNguoiGiaoViec().getHoVaTen());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaoViec.getNguoiDuocGiao().getHoVaTen());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(df.format(giaoViec.getHanThucHien()));
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				
				sttData++;
				
				c.setCellValue(df.format(giaoViec.getNgayGiao()));
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaoViec.getTrangThaiGiaoViec().getText());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				i++;
				idx++;
			}

			idx++;
			idx++;

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}
	
	public static void exportThongKeDuAn(String fileName, String sheetName, List<GiaiDoanDuAn> list, String title)
			throws IOException {
		// New Workbook
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			// New Sheet
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetName);
			sheet1.getPrintSetup().setLandscape(true);
			sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			sheet1.createFreezePane(0, 3);

			CellStyle styleLeft = setBorderAndFont(wb, 1, false, 12, "", "LEFT");
			CellStyle styleLeftHeader = setBorderAndFont(wb, 1, true, 13, "", "LEFT");
			CellStyle styleCenter = setBorderAndFont(wb, 1, false, 12, "", "CENTER");
			CellStyle styleCenterHeader = setBorderAndFont(wb, 1, true, 13, "", "CENTER");
			// Row and column indexes
			int idx = 0;
			// Generate column headings
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("DANH SÁCH " + title.toUpperCase());
			c.setCellStyle(setBorderAndFont(wb, 0, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

			// set column width

			sheet1.setColumnWidth(0, 10 * 256);
			sheet1.setColumnWidth(1, 40 * 256);
			sheet1.setColumnWidth(2, 40 * 256);
			sheet1.setColumnWidth(3, 40 * 256);
			sheet1.setColumnWidth(4, 40 * 256);
			sheet1.setColumnWidth(5, 20 * 256);
			sheet1.setColumnWidth(6, 20 * 256);

			// Generate rows header of grid
			idx++;
			idx++;
			row = sheet1.createRow(idx);
			idx++;

			int sttHeader = 0;

			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("STT");
			c.setCellStyle(styleCenterHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Tên dự án");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Chủ đầu tư");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Địa điểm, diện tích đất dự kiến");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Tổng vốn đầu tư (USD)");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Mục tiêu dự án");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Tiến độ xúc tiến");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			int i = 1;
			for (GiaiDoanDuAn giaiDoanDuAn : list) {
				row = sheet1.createRow(idx);
				int sttData = 0;

				c = row.createCell(sttData);
				sttData++;
				c.setCellValue(i);
				c.setCellStyle(styleCenter);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(giaiDoanDuAn.getDuAn().getTenDuAn());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaiDoanDuAn.getTenCongTy());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaiDoanDuAn.getDuAn().getDiaDiemAndDienTich());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaiDoanDuAn.getDuAn().getTongVonDauTu());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(giaiDoanDuAn.getDuAn().getMucTieuDuAn());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				
				sttData++;
				
				c.setCellValue(giaiDoanDuAn.getDuAn().getGiaiDoanXucTien().getText());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				i++;
				idx++;
			}

			idx++;
			idx++;

			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}
	
	public static void exportThongKeDoanVao(String fileName, String sheetName, List<DoanVao> list, String title)
			throws IOException {
		Workbook wb = new XSSFWorkbook();
		try {
			Cell c = null;
			Sheet sheet1 = null;
			sheet1 = wb.createSheet(sheetName);
			sheet1.getPrintSetup().setLandscape(true);
			sheet1.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
			sheet1.createFreezePane(0, 3);
			CellStyle styleLeft = setBorderAndFont(wb, 1, false, 12, "", "LEFT");
			CellStyle styleLeftHeader = setBorderAndFont(wb, 1, true, 13, "", "LEFT");
			CellStyle styleCenter = setBorderAndFont(wb, 1, false, 12, "", "CENTER");
			CellStyle styleCenterHeader = setBorderAndFont(wb, 1, true, 13, "", "CENTER");
			int idx = 0;
			Row row;
			row = sheet1.createRow(idx);
			c = row.createCell(0);
			c.setCellValue("DANH SÁCH " + title.toUpperCase());
			c.setCellStyle(setBorderAndFont(wb, 0, true, 14, "BLUE", "CENTER"));
			sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
			sheet1.setColumnWidth(0, 10 * 256);
			sheet1.setColumnWidth(1, 40 * 256);
			sheet1.setColumnWidth(2, 30 * 256);
			sheet1.setColumnWidth(3, 20 * 256);
			sheet1.setColumnWidth(4, 40 * 256);
			sheet1.setColumnWidth(5, 40 * 256);
			sheet1.setColumnWidth(6, 40 * 256);
			sheet1.setColumnWidth(7, 40 * 256);
			idx++;
			idx++;
			row = sheet1.createRow(idx);
			idx++;

			int sttHeader = 0;

			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("STT");
			c.setCellStyle(styleCenterHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Tên đoàn");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;

			c.setCellValue("Thành phần đoàn (người)");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Thời gian");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Quốc gia");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Nơi đoàn đi thăm");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			
			c.setCellValue("Tóm tắt nội dung - Kết quả làm việc");
			c.setCellStyle(styleLeftHeader);
			c = row.createCell(sttHeader);
			sttHeader++;
			int i = 1;
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			for (DoanVao doanVao : list) {
				row = sheet1.createRow(idx);
				int sttData = 0;

				c = row.createCell(sttData);
				sttData++;
				c.setCellValue(i);
				c.setCellStyle(styleCenter);
				c = row.createCell(sttData);
				sttData++;

				c.setCellValue(doanVao.getTenDoanVao());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(doanVao.getSoNguoiAndText());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(df.format(doanVao.getThoiGianDenLamViec()));
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(doanVao.getTenQuocGia());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(doanVao.getNoiDoanDiTham());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				sttData++;
				
				c.setCellValue(doanVao.getTomTatNoiDungKQ());
				c.setCellStyle(styleLeft);
				c = row.createCell(sttData);
				
				i++;
				idx++;
			}
			idx++;
			idx++;
			ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
			wb.write(fileOut);
			Filedownload.save(new ByteArrayInputStream(fileOut.toByteArray()), "application/octet-stream",
					fileName + ".xlsx");
		} finally {
			wb.close();
		}
	}
}