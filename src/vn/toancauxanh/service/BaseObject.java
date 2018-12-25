package vn.toancauxanh.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.persistence.Transient;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.SystemPropertyUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Default;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.greenglobal.core.CoreObject;
import vn.toancauxanh.cms.service.HomeService;
import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.gg.model.enums.TrangThaiTiepDoanEnum;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QDoanVao;
import vn.toancauxanh.model.QDuAn;
import vn.toancauxanh.model.QGiaoViec;
import vn.toancauxanh.model.QNhanVien;
import vn.toancauxanh.model.QThanhVienDoan;
import vn.toancauxanh.model.Setting;
import vn.toancauxanh.model.ThanhVienDoan;
import vn.toancauxanh.sso.Utils;

public class BaseObject<T> extends CoreObject<T> {

	private int first = 0;
	private int last = 0;

	@Transient
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	@Transient
	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

	public boolean live = true;

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	@Override
	public Map<Object, Object> getArg() {
		Map<Object, Object> arg = super.getArg();
		return arg;
	}

	public NhanVien fetchNhanVien(boolean saving) {
		if (Executions.getCurrent() == null) {
			return null;
		}
		return getNhanVien(saving, (HttpServletRequest) Executions.getCurrent().getNativeRequest(),
				(HttpServletResponse) Executions.getCurrent().getNativeResponse());
	}

	public NhanVien getNhanVien() {
		return fetchNhanVien(false);
	}

	public NhanVien getNhanVien(boolean isSave, HttpServletRequest req, HttpServletResponse res) {
		NhanVien nhanVien = null;
		String key = getClass() + "." + NhanVien.class;
		nhanVien = (NhanVien) req.getAttribute(key);
		if (nhanVien == null || nhanVien.noId()) {
			Object token = null;
			Cookie[] cookies = req.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("email".equals(c.getName())) {
						token = c.getValue();
						break;
					}
				}
			}
			if (token == null) {
				HttpSession ses = req.getSession();
				token = ses.getAttribute("email");
			}
			if (token != null) {
				String[] parts = new String(Base64.decodeBase64(token.toString())).split(":");
				NhanVien nhanVienLogin = em().find(NhanVien.class, NumberUtils.toLong(parts[0], 0));
				if (parts.length == 3 && nhanVienLogin != null) {
					long expire = NumberUtils.toLong(parts[1], 0);
					if (expire > System.currentTimeMillis() && token.equals(nhanVienLogin.getCookieToken(expire))) {
						nhanVien = nhanVienLogin;
					}
				}
			}
			if (!isSave && (nhanVien == null)) {
				if (nhanVien == null) {
					bootstrapNhanVien();
				}
				nhanVien = new NhanVien();
				if (token != null) {
					req.getSession().removeAttribute("email");
				}
				redirectLogin(req, res);
			}
			req.setAttribute(key, nhanVien);
		}

		return isSave && nhanVien != null && nhanVien.noId() ? null : nhanVien;
	}

	public void setActivePage(int value) {
		getArg().put(SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE), value + 1);
	}

	public <A> JPAQuery<A> page1(JPAQuery<A> q) {
		String kPage = SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE);
		int len = MapUtils.getIntValue(getArg(), SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGESIZE));
		int page = Math.max(0, MapUtils.getIntValue(getArg(), kPage) - 1);
		if (q.fetchCount() <= page * len) {
			getArg().put(kPage, page = 0);
			BindUtils.postNotifyChange(null, null, getArg(), kPage);
		}
		return q.offset(page * len).limit(len);
	}

	public <A> JPAQuery<A> pageVideo(JPAQuery<A> q) {
		int len = 9;
		String kPage = SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGE);
		int page = Math.max(0, MapUtils.getIntValue(getArg(), kPage) - 1);
		if (q.fetchCount() <= page * len) {
			getArg().put(kPage, page = 0);
			BindUtils.postNotifyChange(null, null, getArg(), kPage);
		}
		return q.offset(page * len).limit(len);
	}

	@Command
	public final void cmd(@BindingParam("ten") @Default(value = "") final String ten,
			@BindingParam("notify") Object beanObject, @BindingParam("attr") @Default(value = "*") String fields) {
		invoke(null, ten, null, beanObject, fields, null, false);
	}

	public String removeAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	@Command
	public final void cmdLoadPageFrontEnd(@BindingParam("ten") @Default(value = "") final String ten,
			@BindingParam("notify") Object beanObject, @BindingParam("attr") @Default(value = "*") String fields) {
		invoke(null, ten, null, beanObject, "detail", null, false);
	}

	@Transient
	public Entry core() {
		return Entry.instance;
	}

	public Date date(Object key) {
		Object result = argDeco().get(key);
		if (!(result instanceof Date) && result != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CoreObject.DATE_FORMAT);
			result = simpleDateFormat.parse(result.toString(), new ParsePosition(0));
		}
		if (result != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) result);
			cal.add(Calendar.HOUR, 7);
			result = cal.getTime();
		}
		return (Date) result;
	}

	@Transient
	public final HomeService getHomeService() {
		return new HomeService();
	}

	public NhanVien fetchNhanVienSaving() {
		return fetchNhanVien(true);
	}

	public void redirectLogin(HttpServletRequest req, HttpServletResponse res) {
		StringBuilder url = new StringBuilder();
		url.append(req.getScheme()) // http (https)
				.append("://") // ://
				.append(req.getServerName()); // localhost (domain name)
		if (req.getServerPort() != 80 && req.getServerPort() != 443) {
			url.append(":").append(req.getServerPort()); // app name
		}
		try {
			if (live) {
				res.sendRedirect(url + req.getContextPath() + "/login");
			} else {
				res.sendRedirect(Utils.getLogoutCasUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bootstrapNhanVien() {
		JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.daXoa.isFalse())
				.where(QNhanVien.nhanVien.trangThai.eq(core().TT_AP_DUNG));
		if (q.fetchCount() == 0) {
			final NhanVien nhanVien = new NhanVien("admin@greenglobal.vn", "Super Admin");
			nhanVien.getQuyens().add("*");
			nhanVien.updatePassword("tcx@123");
			nhanVien.saveNotShowNotification();
		}
	}

	@Transient
	public Setting getSetting() {
		String key = BaseObject.class + "." + Setting.class;
		Setting result = (Setting) Executions.getCurrent().getAttribute(key);
		if (result == null || result.noId()) {
			result = find(Setting.class).fetchFirst();
			if (result == null) {
				result = new Setting();
				result.save();
			}
			Executions.getCurrent().setAttribute(key, result);
		}
		return result;
	}

	@Transient
	public boolean isNhanVienDaKhoa() {
		return !getNhanVien().isCheckApDung();
	}

	@Transient
	public boolean isNhanVienDaKichHoat() {
		return !getNhanVien().isCheckKichHoat();
	}

	@Command
	public void redirectQuanLyDuAn() {
		Executions.sendRedirect("/cp/quanlyduan");
	}

	@Command
	public void redirectQuanLyDoanVao() {
		Executions.sendRedirect("/cp/quanlydoanvao");
	}

	@Command
	public void redirectPage(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageDoanVao(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageAction(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom,
			@BindingParam("readOnly") boolean readOnly) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		args.put("readOnly", readOnly);
		Executions.createComponents(zul, null, args);
	}

	@Command
	public void redirectPageActionGiaoViec(@BindingParam("zul") String zul, @BindingParam("vmArgs") Object vmArgs,
			@BindingParam("vm") Object vm, @BindingParam("nhom") Object nhom,
			@BindingParam("loaiCongViec") LoaiCongViec loaiCongViec, @BindingParam("readOnly") boolean readOnly,
			@BindingParam("title") String title, @BindingParam("attr") String attr) {
		Map<String, Object> args = new HashMap<>();
		args.put("vmArgs", vmArgs);
		args.put("vm", vm);
		args.put("nhom", nhom);
		args.put("readOnly", readOnly);
		args.put("title", title);
		args.put("loaiCongViec", loaiCongViec);
		args.put("attr", attr);
		Executions.createComponents(zul, null, args);
	}

	@SuppressWarnings("deprecation")
	protected CellStyle setBorderAndFont(final Workbook workbook, final int borderSize, final boolean isTitle,
			final int fontSize, final String fontColor, final String textAlign, final boolean boil) {
		final CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop((short) borderSize); // single line border
		cellStyle.setBorderBottom((short) borderSize); // single line border
		cellStyle.setBorderLeft((short) borderSize); // single line border
		cellStyle.setBorderRight((short) borderSize); // single line border

		if (textAlign.equals("RIGHT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		} else if (textAlign.equals("CENTER")) {
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		} else if (textAlign.equals("LEFT")) {
			cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
		} else {
			// do nothing
		}

		if (boil) {
			final Font font = workbook.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			if (isTitle) {
				if (fontColor.equals("RED")) {
					font.setColor(Font.COLOR_RED);
				} else if (fontColor.equals("BLUE")) {
					font.setColor((short) 4);
				} else {
					// do no thing
				}
			}
			font.setFontHeightInPoints((short) fontSize);
			cellStyle.setFont(font);
		}
		return cellStyle;
	}

	public List<Long> listSubStringId(String text) {
		List<Long> list = new ArrayList<Long>();
		int start = text.indexOf("@");
		int close = text.indexOf("@", start + 1);
		while (start != -1 && close != -1) {
			list.add(Long.valueOf(text.substring(start + 1, close)));
			start = text.indexOf("@", close + 1);
			close = text.indexOf("@", start + 1);
		}
		return list;
	}

	public Date resetHourMinuteSecondMilli(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public String subStringThongBao(String text, int index) {
		int i = 0;
		for (String txt : text.split("@", 0)) {
			if (index == i) {
				return txt;
			}
			i++;
		}
		return null;
	}

	public String subStringThongBaoNoIndex(String text) {
		StringBuilder builder = new StringBuilder();
		for (String txt : text.split("@", 0)) {
			builder.append(txt);
		}
		return builder.toString();
	}

	public void removeIdInList(GiaoViec giaoViec) {
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.eq(giaoViec.getDuAn()));
		DuAn duAn = q.fetchFirst();
		duAn.setIdNguoiLienQuan(
				duAn.getIdNguoiLienQuan().replaceFirst(KY_TU + giaoViec.getNguoiDuocGiao().getId() + KY_TU, ""));
		duAn.saveNotShowNotification();
	}

	public String removeIdInListDoanVao(GiaoViec giaoViec, NhanVien nguoiCu) {
		JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.eq(giaoViec.getDoanVao()));
		if (q != null) {
			DoanVao doanVao = q.fetchFirst();
			doanVao.setIdNguoiLienQuan(doanVao.getIdNguoiLienQuan().replaceFirst(KY_TU + nguoiCu.getId() + KY_TU, ""));
			return doanVao.getIdNguoiLienQuan();
		}
		return "";
	}

	public static final String KY_TU = "@";

	public String unAccent(String s) {
		String temp = Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll(" ", "")
				.replaceAll("[^a-zA-Z0-9 -]", "");
	}

	public void showNotification(String title, String content, String type) {
		switch (type) {
		case "success": {
			Clients.evalJavaScript("toastrSuccess('" + title + "', '" + content + "')");
			break;
		}
		case "info": {
			Clients.evalJavaScript("toastrInfo('" + title + "', '" + content + "')");
			break;
		}
		case "warning": {
			Clients.evalJavaScript("toastrWarning('" + title + "', '" + content + "')");
			break;
		}
		case "danger": {
			Clients.evalJavaScript("toastrError('" + title + "', '" + content + "')");
			break;
		}
		}

	}

	@Command
	public void invokeGG(@BindingParam("notify") Object vmArgs, @BindingParam("attr") String attrs,
			@BindingParam("detach") final Window wdn) {
		for (final String field : attrs.split("\\|")) {
			if (!field.isEmpty()) {
				BindUtils.postNotifyChange(null, null, vmArgs, field);
			}
		}
		if (wdn != null) {
			wdn.detach();
		}
	}

	public String getHomeUrl(String code) {
		String url = Executions.getCurrent().getHeader("host");
		if ("en".equals(code)) {
			return "http://" + url + "/web/en";
		}
		return "http://" + url;
	}

	@Transient
	public Date getBeginToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@Transient
	public Date getBeginDateOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getEndToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getEndDateOf(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	@Transient
	public Date getToday() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	@Command
	public void notificate(String title, String content) {
		Map<String, Object> args = new HashMap<>();
		args.put("title", title);
		args.put("content", content);
		Executions.createComponents("/frontend/common/notification.zul", null, args);
	}

	@Command
	public void notificateError(String title, String content) {
		Map<String, Object> args = new HashMap<>();
		args.put("title", title);
		args.put("content", content);
		Executions.createComponents("/frontend/common/notification-error.zul", null, args);
	}

	@Transient
	public List<String> getListQuocGia() {
		List<String> list = new ArrayList<String>();
		list.add("Afghanistan");
		list.add("Ai Cập");
		list.add("Albania");
		list.add("Algérie");
		list.add("Andorra");
		list.add("Angola");
		list.add("Vương quốc Liên hiệp Anh và Bắc Ireland");
		list.add("Antigua và Barbuda");
		list.add("Áo");
		list.add("Ả Rập Xê Út");
		list.add("Armenia");
		list.add("Argentina");
		list.add("Azerbaijan");
		list.add("Ấn Độ");
		list.add("Bahamas");
		list.add("Bahrain");
		list.add("Ba Lan");
		list.add("Bangladesh");
		list.add("Barbados");
		list.add("Belarus");
		list.add("Belize");
		list.add("Bénin");
		list.add("Bhutan");
		list.add("Bỉ");
		list.add("Bolivia");
		list.add("Bosna và Hercegovina");
		list.add("Botswana");
		list.add("Bồ Đào Nha");
		list.add("Bờ Biển Ngà");
		list.add("Brasil");
		list.add("Brunei");
		list.add("Bulgaria");
		list.add("Burkina Faso");
		list.add("Burundi");
		list.add("Cabo Verde");
		list.add("UAE");
		list.add("Cameroon");
		list.add("Campuchia");
		list.add("Canada");
		list.add("Chile");
		list.add("Colombia");
		list.add("Comoros");
		list.add("Cộng hòa Congo");
		list.add("Cộng hòa Dân chủ Congo");
		list.add("Costa Rica");
		list.add("Croatia");
		list.add("Cuba");
		list.add("Djibouti");
		list.add("Dominica");
		list.add("Cộng hòa Dominica");
		list.add("Đan Mạch");
		list.add("Đông Timor");
		list.add("Đức");
		list.add("Ecuador");
		list.add("El Salvador");
		list.add("Eritrea");
		list.add("Estonia");
		list.add("Fiji");
		list.add("Gabon");
		list.add("Gambia");
		list.add("Ghana");
		list.add("Grenada");
		list.add("Gruzia");
		list.add("Guatemala");
		list.add("Guiné-Bissau");
		list.add("Guinea Xích Đạo");
		list.add("Guinée");
		list.add("Guyana");
		list.add("Haiti");
		list.add("Hà Lan");
		list.add("Hàn Quốc");
		list.add("Hoa Kỳ");
		list.add("Honduras");
		list.add("Hungary");
		list.add("Hy Lạp");
		list.add("Iceland");
		list.add("Indonesia");
		list.add("Iran");
		list.add("Iraq");
		list.add("Ireland");
		list.add("Israel");
		list.add("Jamaica");
		list.add("Jordan");
		list.add("Kazakhstan");
		list.add("Kenya");
		list.add("Kiribati");
		list.add("Kosovo");
		list.add("Kuwait");
		list.add("Kyrgyzstan");
		list.add("Lào");
		list.add("Latvia");
		list.add("Lesotho");
		list.add("Liban");
		list.add("Liberia");
		list.add("Libya");
		list.add("Liechtenstein");
		list.add("Litva");
		list.add("Luxembourg");
		list.add("Macedonia");
		list.add("Madagascar");
		list.add("Malawi");
		list.add("Malaysia");
		list.add("Maldives");
		list.add("Mali");
		list.add("Malta");
		list.add("Maroc");
		list.add("Quần đảo Marshall");
		list.add("Mauritanie");
		list.add("Mauritius");
		list.add("México");
		list.add("Micronesia");
		list.add("Moldova");
		list.add("Monaco");
		list.add("Mông Cổ");
		list.add("Montenegro");
		list.add("Mozambique");
		list.add("Myanmar");
		list.add("Namibia");
		list.add("Nam Sudan");
		list.add("Nam Phi");
		list.add("Nauru");
		list.add("Na Uy");
		list.add("Nepal");
		list.add("New Zealand");
		list.add("Nicaragua");
		list.add("Niger");
		list.add("Nigeria");
		list.add("Nga");
		list.add("Nhật Bản");
		list.add("Oman");
		list.add("Pakistan");
		list.add("Palau");
		list.add("Palestine");
		list.add("Panama");
		list.add("Papua New Guinea");
		list.add("Paraguay");
		list.add("Peru");
		list.add("Pháp");
		list.add("Phần Lan");
		list.add("Philippines");
		list.add("Qatar");
		list.add("România");
		list.add("Rwanda");
		list.add("Saint Kitts và Nevis");
		list.add("Saint Lucia");
		list.add("Saint Vincent và Grenadines");
		list.add("Samoa");
		list.add("San Marino");
		list.add("São Tomé và Príncipe");
		list.add("Séc");
		list.add("Sénégal");
		list.add("Serbia");
		list.add("Seychelles");
		list.add("Sierra Leone");
		list.add("Singapore");
		list.add("Síp");
		list.add("Slovakia");
		list.add("Slovenia");
		list.add("Solomon");
		list.add("Somalia");
		list.add("Sri Lanka");
		list.add("Sudan");
		list.add("Suriname");
		list.add("Swaziland");
		list.add("Syria");
		list.add("Tajikistan");
		list.add("Tây Ban Nha");
		list.add("Tchad");
		list.add("Thái Lan");
		list.add("Thổ Nhĩ Kỳ");
		list.add("Thụy Điển");
		list.add("Thụy Sĩ");
		list.add("Togo");
		list.add("Tonga");
		list.add("Triều Tiên");
		list.add("Trinidad và Tobago");
		list.add("Trung Quốc");
		list.add("Trung Phi");
		list.add("Tunisia");
		list.add("Turkmenistan");
		list.add("Tuvalu");
		list.add("Úc");
		list.add("Uganda");
		list.add("Ukraina");
		list.add("Uruguay");
		list.add("Uzbekistan");
		list.add("Vanuatu");
		list.add("Vatican");
		list.add("Venezuela");
		list.add("Việt Nam");
		list.add("Ý");
		list.add("Yemen");
		list.add("Zambia");
		list.add("Zimbabwe");

		return list;
	}

	@Transient
	public List<String> getListQuocGiaAndNull() {
		List<String> list = new ArrayList<String>();
		list.add(null);
		list.addAll(getListQuocGia());
		return list;
	}

	@Transient
	public List<NhanVien> getListNguoiPhuTrachDoanVaoAndNull() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		list.add(new NhanVien());
		list.addAll(getListNguoiPhuTrach());
		return list;
	}

	@Transient
	public List<NhanVien> getListNguoiPhuTrach() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		JPAQuery<NhanVien> q = find(NhanVien.class)
				.where(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN)
						.or(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_TRUONG_PHONG)));
		if (q != null) {
			list.addAll(q.fetch());
			return list;
		}
		return list;
	}

	public boolean checkEdit(Long idNV, String id, GiaiDoanXucTien giaiDoanXucTien, NhanVien nguoiTao,
			NhanVien nguoiPhuTrach) {
		if (id == null || idNV == null || id.trim().isEmpty()) {
			return false;
		}
		if (GiaiDoanXucTien.CHUA_HOAN_THANH.equals(giaiDoanXucTien)
				|| GiaiDoanXucTien.HOAN_THANH.equals(giaiDoanXucTien)) {
			return false;
		}
		if (nguoiTao.equals(core().getNhanVien()) || nguoiPhuTrach.equals(core().getNhanVien())) {
			return true;
		}
		return id.contains("@" + String.valueOf(idNV) + "@");
	}

	public boolean checkNguoiLienQuan(Long idNV, String id, NhanVien nguoiTao, NhanVien nguoiPhuTrach) {
		if (idNV == null) {
			return false;
		}
		if (nguoiTao.equals(core().getNhanVien()) || nguoiPhuTrach.equals(core().getNhanVien())) {
			return true;
		}
		if (id != null && !"".equals(id)) {
			return listSubStringId(id).contains(idNV);
		}
		return false;
	}

	public boolean checkNguoiPhuTrach(NhanVien nguoiTao, NhanVien nguoiPhuTrach, Long idNV) {
		if (idNV == null) {
			return false;
		}
		if ((nguoiTao != null && nguoiTao.equals(core().getNhanVien()))
				|| (nguoiPhuTrach != null && nguoiPhuTrach.equals(core().getNhanVien()))) {
			return true;
		}
		return false;
	}

	public boolean checkOnlyNguoiPhuTrach(NhanVien nguoiPhuTrach, Long idNV) {
		if (idNV == null) {
			return false;
		}
		if (nguoiPhuTrach != null && nguoiPhuTrach.equals(core().getNhanVien())) {
			return true;
		}
		return false;
	}

	public boolean checkOnlyNguoiLienQuan(Long idNV, String id) {
		if (idNV == null) {
			return false;
		}
		if (id != null && !id.isEmpty()) {
			return listSubStringId(id).contains(idNV);
		}
		return false;
	}

	public boolean checkDeleteDoanVao(NhanVien nguoiTao, TrangThaiTiepDoanEnum trangThaiTiepDoan) {
		if (TrangThaiTiepDoanEnum.DA_TIEP.equals(trangThaiTiepDoan)) {
			return false;
		}
		if (nguoiTao.equals(core().getNhanVien())) {
			return true;
		}
		return false;
	}

	@Override
	public String subString(String text, int size) {
		int l = text.length();
		int index = size > l ? l : size;
		while (index < l && ' ' != text.charAt(index)) {
			index++;
		}
		String tail = index < l ? " ..." : "";
		return text.substring(0, index) + tail;
	}

	public List<NhanVien> getListNguoiPhuTrachAndNull() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		list.add(null);
		JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.phongBan.id.eq(1l))
				.where(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN));
		if (q != null) {
			list.addAll(q.fetch());
			return list;
		}
		return list;
	}

	public List<GiaiDoanXucTien> getListGiaiDoanXucTienAndNull() {
		List<GiaiDoanXucTien> list = new ArrayList<>();
		list.add(null);
		list.add(GiaiDoanXucTien.GIAI_DOAN_MOT);
		list.add(GiaiDoanXucTien.GIAI_DOAN_HAI);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BA);
		list.add(GiaiDoanXucTien.GIAI_DOAN_BON);
		list.add(GiaiDoanXucTien.GIAI_DOAN_NAM);
		list.add(GiaiDoanXucTien.CHUA_HOAN_THANH);
		list.add(GiaiDoanXucTien.HOAN_THANH);
		return list;
	}

	public boolean checkQuyenBaoCao(TrangThaiGiaoViec trangThai, Long id) {
		if (TrangThaiGiaoViec.DANG_LAM.equals(trangThai) && core().getNhanVien().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public boolean checkQuyenNhanViec(TrangThaiGiaoViec trangThai, Long id) {
		if (TrangThaiGiaoViec.CHUA_LAM.equals(trangThai) && core().getNhanVien().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public boolean checkQuyenSuaXoa(TrangThaiGiaoViec trangThai, Long id) {
		if (!TrangThaiGiaoViec.HOAN_THANH.equals(trangThai) && core().getNhanVien().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public boolean checkQuyenSuaXoaKeCongViec(Long id) {
		if (core().getNhanVien().getId().equals(id)) {
			return true;
		}
		return false;
	}

	public String thoiHanConLai(Date thoiHan, TrangThaiGiaoViec trangThai) {
		if (TrangThaiGiaoViec.HOAN_THANH.equals(trangThai)) {
			return null;
		}
		if (thoiHan.compareTo(resetHourMinuteSecondMilli(new Date())) > 0) {
			StringBuilder txt = new StringBuilder();
			txt.append("<span class='color-txt-blue'>(Còn ");
			txt.append((thoiHan.getTime() - resetHourMinuteSecondMilli(new Date()).getTime()) / (24 * 60 * 60 * 1000));
			txt.append(" ngày)</span>");
			return txt.toString();
		} else if (thoiHan.compareTo(resetHourMinuteSecondMilli(new Date())) == 0) {
			return "<span class='color-txt-orange'>(Đã đến hạn)</span>";
		} else {
			return "<span class='color-txt-red'>(Đã quá hạn)</span>";
		}
	}

	public List<NhanVien> getListNhanVienTruongPhongAndLanhDaoAndNull() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		list.add(null);
		JPAQuery<NhanVien> q = find(NhanVien.class)
				.where(QNhanVien.nhanVien.vaiTro.loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_TRUONG_PHONG)
						.or(QNhanVien.nhanVien.vaiTro.loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_LANH_DAO)));
		list.addAll(q.fetch());
		return list;
	}

	public List<NhanVien> getListNhanVienTruongPhongAndChuyenVienNull() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		list.add(null);
		JPAQuery<NhanVien> q = find(NhanVien.class)
				.where(QNhanVien.nhanVien.vaiTro.loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_TRUONG_PHONG)
						.or(QNhanVien.nhanVien.vaiTro.loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN)));
		list.addAll(q.fetch());
		return list;
	}

	public List<LoaiCongViec> getListLoaiCongViec() {
		List<LoaiCongViec> list = new ArrayList<LoaiCongViec>();
		list.add(null);
		list.add(LoaiCongViec.DU_AN);
		list.add(LoaiCongViec.DOAN_VAO);
		return list;
	}

	@Transient
	public void xoaCongViecLienQuan(Long id, LoaiCongViec loaiCongViec) {
		JPAQuery<GiaoViec> q = find(GiaoViec.class);
		if (LoaiCongViec.DOAN_VAO.equals(loaiCongViec)) {
			q.where(QGiaoViec.giaoViec.doanVao.id.eq(Long.valueOf(id)));
			JPAQuery<ThanhVienDoan> query = find(ThanhVienDoan.class)
					.where(QThanhVienDoan.thanhVienDoan.doanVao.id.eq(Long.valueOf(id)));
			query.fetch().forEach(item -> item.doDelete(true));
		} else if (LoaiCongViec.DU_AN.equals(loaiCongViec)) {
			q = find(GiaoViec.class).where(QGiaoViec.giaoViec.duAn.id.eq(Long.valueOf(id)));
		}
		q.fetch().forEach(item -> item.doDelete(true));
	}

	public Document getDOMDocument(String output) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse(new ByteArrayInputStream(output.getBytes()));
		dom.getDocumentElement().normalize();
		return dom;
	}
}