package vn.toancauxanh.service;

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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.SystemPropertyUtils;
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
import vn.toancauxanh.gg.model.enums.QuocGiaEnum;
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

	public List<Long> subString(String text) {
		List<Long> list = new ArrayList<Long>();
		for (String w : text.split("@", 0)) {
			try {
				list.add(Long.parseLong(w));
			} catch (Exception ex) {
				return null;
			}
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
				duAn.getIdNguoiLienQuan().replaceFirst(giaoViec.getNguoiDuocGiao().getId() + KY_TU, ""));
		duAn.saveNotShowNotification();
	}

	public String removeIdInListDoanVao(GiaoViec giaoViec, NhanVien nguoiCu) {
		JPAQuery<DoanVao> q = find(DoanVao.class).where(QDoanVao.doanVao.eq(giaoViec.getDoanVao()));
		if (q != null) {
			DoanVao doanVao = q.fetchFirst();
			doanVao.setIdNguoiLienQuan(doanVao.getIdNguoiLienQuan().replaceFirst(nguoiCu.getId() + KY_TU, ""));
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
	public List<QuocGiaEnum> getListQuocGia() {
		List<QuocGiaEnum> list = new ArrayList<QuocGiaEnum>();
		list.add(QuocGiaEnum.AFGHANISTAN);
		list.add(QuocGiaEnum.AI_CAP);
		list.add(QuocGiaEnum.ALBANIA);
		list.add(QuocGiaEnum.ALGIERI);
		list.add(QuocGiaEnum.ANDORRA);
		list.add(QuocGiaEnum.ANGOLA);
		list.add(QuocGiaEnum.VUONG_QUOC_ANH);
		list.add(QuocGiaEnum.ANTIGUA);
		list.add(QuocGiaEnum.AO);
		list.add(QuocGiaEnum.A_RAP);
		list.add(QuocGiaEnum.ARMENIA);
		list.add(QuocGiaEnum.ARGHENTINA);
		list.add(QuocGiaEnum.AZERBAIJAN);
		list.add(QuocGiaEnum.AN_DO);
		list.add(QuocGiaEnum.BAHAMAS);
		list.add(QuocGiaEnum.BAHRAIN);
		list.add(QuocGiaEnum.BA_LAN);
		list.add(QuocGiaEnum.BANGLADESH);
		list.add(QuocGiaEnum.BARBADOS);
		list.add(QuocGiaEnum.BELARUS);
		list.add(QuocGiaEnum.BELIZE);
		list.add(QuocGiaEnum.BENIN);
		list.add(QuocGiaEnum.BHUTAN);
		list.add(QuocGiaEnum.BI);
		list.add(QuocGiaEnum.BOLIVIA);
		list.add(QuocGiaEnum.BOSNA_AND_HERCEGOVIA);
		list.add(QuocGiaEnum.BOTSWANA);
		list.add(QuocGiaEnum.BO_DAO_NHA);
		list.add(QuocGiaEnum.BO_BIEN_NGA);
		list.add(QuocGiaEnum.BRASIL);
		list.add(QuocGiaEnum.BRUNEI);
		list.add(QuocGiaEnum.BULGARIA);
		list.add(QuocGiaEnum.BURKIRA_FASO);
		list.add(QuocGiaEnum.BURUNDI);
		list.add(QuocGiaEnum.CABO_VERDE);
		list.add(QuocGiaEnum.UAE);
		list.add(QuocGiaEnum.CAMEROON);
		list.add(QuocGiaEnum.CAMPUCHIA);
		list.add(QuocGiaEnum.CANADA);
		list.add(QuocGiaEnum.CHILE);
		list.add(QuocGiaEnum.COLOMBIA);
		list.add(QuocGiaEnum.COMOROS);
		list.add(QuocGiaEnum.CONGO);
		list.add(QuocGiaEnum.CONG_HOA_CONGO);
		list.add(QuocGiaEnum.COSTA_RICA);
		list.add(QuocGiaEnum.CROATIA);
		list.add(QuocGiaEnum.CUBA);
		list.add(QuocGiaEnum.DJIBOUTI);
		list.add(QuocGiaEnum.DOMINICA);
		list.add(QuocGiaEnum.CONG_HOA_DOMINICA);
		list.add(QuocGiaEnum.DAN_MACH);
		list.add(QuocGiaEnum.DONG_TIMOR);
		list.add(QuocGiaEnum.DUC);
		list.add(QuocGiaEnum.ECUADOR);
		list.add(QuocGiaEnum.EL_SALVADOR);
		list.add(QuocGiaEnum.ERITREA);
		list.add(QuocGiaEnum.ESTONIA);
		list.add(QuocGiaEnum.FIJI);
		list.add(QuocGiaEnum.GABON);
		list.add(QuocGiaEnum.GAMBIA);
		list.add(QuocGiaEnum.GHANA);
		list.add(QuocGiaEnum.GRENADA);
		list.add(QuocGiaEnum.GRUZIA);
		list.add(QuocGiaEnum.GUATEMALA);
		list.add(QuocGiaEnum.GUINE_BISSAU);
		list.add(QuocGiaEnum.GUINE_XICH_DAO);
		list.add(QuocGiaEnum.GUINEE);
		list.add(QuocGiaEnum.GUYANA);
		list.add(QuocGiaEnum.HAITI);
		list.add(QuocGiaEnum.HA_LAN);
		list.add(QuocGiaEnum.HAN_QUOC);
		list.add(QuocGiaEnum.HOA_KY);
		list.add(QuocGiaEnum.HONDURAS);
		list.add(QuocGiaEnum.HUNGARY);
		list.add(QuocGiaEnum.HY_LAP);
		list.add(QuocGiaEnum.ICELAND);
		list.add(QuocGiaEnum.INDONESIA);
		list.add(QuocGiaEnum.IRAN);
		list.add(QuocGiaEnum.IRAQ);
		list.add(QuocGiaEnum.IRELAND);
		list.add(QuocGiaEnum.ISRAEL);
		list.add(QuocGiaEnum.JAMAICA);
		list.add(QuocGiaEnum.JORDAN);
		list.add(QuocGiaEnum.KAZAKHSTAN);
		list.add(QuocGiaEnum.KENYA);
		list.add(QuocGiaEnum.KIRIBATI);
		list.add(QuocGiaEnum.KOSOVO);
		list.add(QuocGiaEnum.KUWAIT);
		list.add(QuocGiaEnum.KYRGYZSTAN);
		list.add(QuocGiaEnum.LAO);
		list.add(QuocGiaEnum.LATVIA);
		list.add(QuocGiaEnum.LESOTHO);
		list.add(QuocGiaEnum.LIBAN);
		list.add(QuocGiaEnum.LIBERIA);
		list.add(QuocGiaEnum.LIBYA);
		list.add(QuocGiaEnum.LIECHTENSTEIN);
		list.add(QuocGiaEnum.LITVA);
		list.add(QuocGiaEnum.LUXEMBOURG);
		list.add(QuocGiaEnum.MACEDONIA);
		list.add(QuocGiaEnum.MADAGASCAR);
		list.add(QuocGiaEnum.MALAWI);
		list.add(QuocGiaEnum.MALAYSIA);
		list.add(QuocGiaEnum.MALDIVES);
		list.add(QuocGiaEnum.MALI);
		list.add(QuocGiaEnum.MALTA);
		list.add(QuocGiaEnum.MAROC);
		list.add(QuocGiaEnum.MARSHALL);
		list.add(QuocGiaEnum.MAURITANIE);
		list.add(QuocGiaEnum.MAURITIUS);
		list.add(QuocGiaEnum.MEXICO);
		list.add(QuocGiaEnum.MICRONESIA);
		list.add(QuocGiaEnum.MOLDOVA);
		list.add(QuocGiaEnum.MONACO);
		list.add(QuocGiaEnum.MONG_CO);
		list.add(QuocGiaEnum.MONTENEGRO);
		list.add(QuocGiaEnum.MOZAMBIQUE);
		list.add(QuocGiaEnum.MYANMAR);
		list.add(QuocGiaEnum.NAMIBIA);
		list.add(QuocGiaEnum.NAM_SUDAN);
		list.add(QuocGiaEnum.NAM_PHI);
		list.add(QuocGiaEnum.NAURU);
		list.add(QuocGiaEnum.NA_UY);
		list.add(QuocGiaEnum.NEPAL);
		list.add(QuocGiaEnum.NEW_ZEALAND);
		list.add(QuocGiaEnum.NICARAGUA);
		list.add(QuocGiaEnum.NIGER);
		list.add(QuocGiaEnum.NIGERIA);
		list.add(QuocGiaEnum.NGA);
		list.add(QuocGiaEnum.NHAT_BAN);
		list.add(QuocGiaEnum.OMAN);
		list.add(QuocGiaEnum.PAKISTAN);
		list.add(QuocGiaEnum.PALAU);
		list.add(QuocGiaEnum.PALESTINE);
		list.add(QuocGiaEnum.PANAMA);
		list.add(QuocGiaEnum.PAPUA_NEW_GUINEA);
		list.add(QuocGiaEnum.PARAGUAY);
		list.add(QuocGiaEnum.PERU);
		list.add(QuocGiaEnum.PHAP);
		list.add(QuocGiaEnum.PHAN_LAN);
		list.add(QuocGiaEnum.PHILIPPINES);
		list.add(QuocGiaEnum.QUATAR);
		list.add(QuocGiaEnum.ROMANIA);
		list.add(QuocGiaEnum.RWANDA);
		list.add(QuocGiaEnum.SAINT_KITTS_NEVIS);
		list.add(QuocGiaEnum.SAINT_LUCIA);
		list.add(QuocGiaEnum.SAINT_VINCENT_GRENADINES);
		list.add(QuocGiaEnum.SAMOA);
		list.add(QuocGiaEnum.SAN_MARINO);
		list.add(QuocGiaEnum.SAO_TOME_PRINCIPE);
		list.add(QuocGiaEnum.SEC);
		list.add(QuocGiaEnum.SENEGAL);
		list.add(QuocGiaEnum.SERBIA);
		list.add(QuocGiaEnum.SEYCHELLES);
		list.add(QuocGiaEnum.SIERRA_LEONE);
		list.add(QuocGiaEnum.SINGAPORE);
		list.add(QuocGiaEnum.SIP);
		list.add(QuocGiaEnum.SLOVAKIA);
		list.add(QuocGiaEnum.SLOVENIA);
		list.add(QuocGiaEnum.SOLOMON);
		list.add(QuocGiaEnum.SOMALIA);
		list.add(QuocGiaEnum.SRI_LANKA);
		list.add(QuocGiaEnum.SUDAN);
		list.add(QuocGiaEnum.SURINAME);
		list.add(QuocGiaEnum.SWAZILAND);
		list.add(QuocGiaEnum.SYRIA);
		list.add(QuocGiaEnum.TAJIKISTAN);
		list.add(QuocGiaEnum.TAY_BAN_NHA);
		list.add(QuocGiaEnum.TCHAD);
		list.add(QuocGiaEnum.THAI_LAN);
		list.add(QuocGiaEnum.THO_NHI_KY);
		list.add(QuocGiaEnum.THUY_DIEN);
		list.add(QuocGiaEnum.THUY_SI);
		list.add(QuocGiaEnum.TOGO);
		list.add(QuocGiaEnum.TONGA);
		list.add(QuocGiaEnum.TRIEU_TIEN);
		list.add(QuocGiaEnum.TRINIDAD_VA_TOBAGO);
		list.add(QuocGiaEnum.TRUNG_QUOC);
		list.add(QuocGiaEnum.TRUNG_PHI);
		list.add(QuocGiaEnum.TUNISIA);
		list.add(QuocGiaEnum.TURKMENISTAN);
		list.add(QuocGiaEnum.TUVALU);
		list.add(QuocGiaEnum.UC);
		list.add(QuocGiaEnum.UGANDA);
		list.add(QuocGiaEnum.UKRAINA);
		list.add(QuocGiaEnum.URUGUAY);
		list.add(QuocGiaEnum.UZBEKISTAN);
		list.add(QuocGiaEnum.VANUATU);
		list.add(QuocGiaEnum.VATICAN);
		list.add(QuocGiaEnum.VENEZUELA);
		list.add(QuocGiaEnum.VIET_NAM);
		list.add(QuocGiaEnum.Y);
		list.add(QuocGiaEnum.YEMEN);
		list.add(QuocGiaEnum.ZAMBIA);
		list.add(QuocGiaEnum.ZIMBABWE);
		return list;
	}

	@Transient
	public List<QuocGiaEnum> getListQuocGiaAndNull() {
		List<QuocGiaEnum> list = new ArrayList<QuocGiaEnum>();
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
				.where(QNhanVien.nhanVien.phongBan.id.eq(1l).or(QNhanVien.nhanVien.phongBan.id.eq(2l)))
				.where(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_CHUYEN_VIEN));
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
		return subString(id).contains(idNV);
	}

	public boolean checkNguoiLienQuan(Long idNV, String id, NhanVien nguoiTao, NhanVien nguoiPhuTrach) {
		if (idNV == null) {
			return false;
		}
		if (nguoiTao.equals(core().getNhanVien()) || nguoiPhuTrach.equals(core().getNhanVien())) {
			return true;
		}
		if (id != null && !"".equals(id)) {
			return subString(id).contains(idNV);
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
		if (id != null && !"".equals(id)) {
			return subString(id).contains(idNV);
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
		if (TrangThaiGiaoViec.DANG_LAM.equals(trangThai) && core().getNhanVien().getId() == id) {
			return true;
		}
		return false;
	}

	public boolean checkQuyenNhanViec(TrangThaiGiaoViec trangThai, Long id) {
		if (TrangThaiGiaoViec.CHUA_LAM.equals(trangThai) && core().getNhanVien().getId() == id) {
			return true;
		}
		return false;
	}
	
	public boolean checkQuyenSuaXoa(TrangThaiGiaoViec trangThai, Long id) {
		if (!TrangThaiGiaoViec.HOAN_THANH.equals(trangThai) && core().getNhanVien().getId() == id) {
			return true;
		}
		return false;
	}
	
	public String thoiHanConLai(Date thoiHan, TrangThaiGiaoViec trangThai) {
		if (TrangThaiGiaoViec.HOAN_THANH.equals(trangThai)) {
			return null;
		}
		if (thoiHan.compareTo(resetHourMinuteSecondMilli(new Date())) > 0){
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
			JPAQuery<ThanhVienDoan> query = find(ThanhVienDoan.class).where(QThanhVienDoan.thanhVienDoan.doanVao.id.eq(Long.valueOf(id)));
			query.fetch().forEach(item -> item.doDelete(true));
		} else if (LoaiCongViec.DU_AN.equals(loaiCongViec)) {
			q = find(GiaoViec.class).where(QGiaoViec.giaoViec.duAn.id.eq(Long.valueOf(id)));
		}
		q.fetch().forEach(item -> item.doDelete(true));
	}
}