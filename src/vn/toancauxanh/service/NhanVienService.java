package vn.toancauxanh.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.MapUtils;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Default;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QNhanVien;
import vn.toancauxanh.model.QVaiTro;
import vn.toancauxanh.model.VaiTro;
import vn.toancauxanh.sso.Utils;

public final class NhanVienService extends BasicService<NhanVien> {

	private boolean remember;

	public void setTacGiasTimKiem(List<NhanVien> tacGiasTimKiem) {
		this.tacGiasTimKiem = tacGiasTimKiem;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public NhanVien getNhanVien(boolean saving) {
		if (Executions.getCurrent() == null) {
			return null;
		}
		return getNhanVien(saving, (HttpServletRequest) Executions.getCurrent().getNativeRequest(),
				(HttpServletResponse) Executions.getCurrent().getNativeResponse());
	}

	public JPAQuery<NhanVien> getTargetQueryNhanVien() {
		String paramtrangThai = MapUtils.getString(argDeco(), "trangThai", "").trim();
		String tuKhoa = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		Long paramVaiTro = (Long) argDeco().get(Labels.getLabel("param.vaitro"));
		Long paramPhongBan = (Long) argDeco().get("phongBan");

		JPAQuery<NhanVien> q = find(NhanVien.class);
		q.where(QNhanVien.nhanVien.email.ne("admin@greenglobal.vn"));

		if (tuKhoa != null && !tuKhoa.isEmpty()) {
			q.where(QNhanVien.nhanVien.hoVaTen.containsIgnoreCase(tuKhoa));
		}

		if (paramVaiTro != null) {
			VaiTro vaiTro = find(VaiTro.class).where(QVaiTro.vaiTro.id.eq(paramVaiTro)).fetchFirst();
			q.where(QNhanVien.nhanVien.vaiTros.contains(vaiTro));
		}

		if (paramPhongBan != null) {
			q.where(QNhanVien.nhanVien.phongBan.id.eq(paramPhongBan));
		}

		if (paramtrangThai != null && !paramtrangThai.isEmpty()) {
			q.where(QNhanVien.nhanVien.trangThai.eq(paramtrangThai));
		}
		q.orderBy(QNhanVien.nhanVien.trangThai.asc());
		return q.orderBy(QNhanVien.nhanVien.ngaySua.desc());
	}

	@Command
	public void login(@BindingParam("email") final String email, @BindingParam("password") final String password) {
		NhanVien nhanVien = new JPAQuery<NhanVien>(em()).from(QNhanVien.nhanVien)
				.where(QNhanVien.nhanVien.daXoa.isFalse()).where(QNhanVien.nhanVien.trangThai.ne(core().TT_DA_XOA))
				.where(QNhanVien.nhanVien.email.eq(email)).fetchFirst();
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		if (nhanVien != null
				&& encryptor.checkPassword(password.trim() + nhanVien.getSalkey(), nhanVien.getMatKhau())) {
			String cookieToken = nhanVien
					.getCookieToken(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
			Session zkSession = Sessions.getCurrent();
			zkSession.setAttribute("email", cookieToken);
			HttpServletResponse res = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
			Cookie cookie = new Cookie("email", cookieToken);
			cookie.setPath("/");
			cookie.setMaxAge(1000000000);
			res.addCookie(cookie);
			Executions.sendRedirect("/");
		} else {
			showNotification("Đăng nhập không thành công", "", "danger");
		}
	}

	@Command
	public void logout() {
		NhanVien NhanVienLogin = getNhanVien(true);
		if (NhanVienLogin != null && !NhanVienLogin.noId()) {
			Session zkSession = Sessions.getCurrent();
			zkSession.removeAttribute("email");
			HttpServletResponse res = (HttpServletResponse) Executions.getCurrent().getNativeResponse();
			Cookie cookie = new Cookie("email", null);
			cookie.setPath("/");
			cookie.setMaxAge(0);
			res.addCookie(cookie);
			Executions.sendRedirect("/login");
		}
	}

	public List<NhanVien> getTacGias() {
		// TODO add them dk nhan vien là tác giả
		return getTargetQueryNhanVien().fetch();
	}

	public List<NhanVien> getTacGiasAndNull() {
		// TODO add them dk nhan vien là tác giả
		List<NhanVien> list = new ArrayList<>();
		list.add(null);
		list.addAll(getTargetQueryNhanVien().fetch());
		return list;
	}

	private List<NhanVien> tacGiasTimKiem = new ArrayList<>();

	public List<NhanVien> getTacGiasTimKiem() {
		if (tacGiasTimKiem.size() == 0) {
			tacGiasTimKiem = getTacGiasAndNull();
		}
		return tacGiasTimKiem;
	}

	@Command
	public void timKiems(@BindingParam("hoTenTacGia") @Default(value = "") final String name,
			@BindingParam("baiViet") final Object bv) {

		if (name.isEmpty()) {
			tacGiasTimKiem = getTacGiasAndNull();
		} else {
			tacGiasTimKiem.clear();
			tacGiasTimKiem.addAll(find(NhanVien.class).where(QNhanVien.nhanVien.trangThai.ne(core().TT_DA_XOA))
					.where(QNhanVien.nhanVien.hoVaTen.like("%" + name + "%")).orderBy(QNhanVien.nhanVien.hoVaTen.asc())
					.fetch());
		}
		BindUtils.postNotifyChange(null, null, this, "tacGiasTimKiem");
	}
	
	@Command
	public void logoutNotRedirect(HttpServletRequest req, HttpServletResponse res) {
		NhanVien nhanVienLogin = getNhanVien(false, false, req, res);
		if (nhanVienLogin != null && !nhanVienLogin.noId()) {
			
			HttpSession zkSession=req.getSession();
 			//Session zkSession = Sessions.getCurrent();
 			zkSession.removeAttribute("email");
 			Cookie cookie = new Cookie("email", null);
 			cookie.setPath("/");
 			cookie.setMaxAge(0);
 			res.addCookie(cookie);
 			/*Date thoiGian = new Date();
 			LichSuNguoiDung lichSuNguoiDung = new LichSuNguoiDung();
 			lichSuNguoiDung.setNhanVien(nhanVienLogin);
 			lichSuNguoiDung.setNgayGio(thoiGian);
 			lichSuNguoiDung.setLoaiHanhDong(LoaiHanhDongEnum.LOGOUT);
 			lichSuNguoiDung.saveNotShowNotification();*/
			try {
				if (live) {
					res.sendRedirect(req.getContextPath()+"/login");
				} else {
					res.sendRedirect(Utils.getLogoutCasUrl());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				res.sendRedirect(req.getContextPath()+ "/cas/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
