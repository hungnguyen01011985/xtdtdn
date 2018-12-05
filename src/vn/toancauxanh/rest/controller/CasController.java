package vn.toancauxanh.rest.controller;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.springframework.security.authentication.Pac4jAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QNhanVien;
import vn.toancauxanh.service.BasicService;

@Controller
public class CasController extends BasicService<NhanVien>{

	@RequestMapping("/cas/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Arrays.stream(cookies).forEach(c -> System.out.println(c.getName() + "=" + c.getValue() + "=" + c.getMaxAge() + "=" + c.getDomain()));
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			Pac4jAuthenticationToken pac4j = (Pac4jAuthenticationToken) auth;
			String username = pac4j.getName();
			NhanVien nhanVien = new JPAQuery<NhanVien>(em()).from(QNhanVien.nhanVien)
					.where(QNhanVien.nhanVien.daXoa.isFalse()).where(QNhanVien.nhanVien.trangThai.ne(core().TT_DA_XOA))
					.where(QNhanVien.nhanVien.email.eq(username.trim())).fetchFirst();
			
			if (nhanVien != null) {
				String cookieToken = nhanVien
						.getCookieToken(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
				Cookie cookie = new Cookie("email", cookieToken);
				cookie.setPath("/");
				cookie.setMaxAge(1000000000);
				response.addCookie(cookie);;
				if (cookies != null) {
					for (Cookie cookie1 : cookies) {
						cookie1.setMaxAge(0);
						cookie1.setValue(null);
						cookie1.setPath("/");
						response.addCookie(cookie1);
					}
				}
				return "redirect:" + "/";
			} 
		}
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				cookie.setValue(null);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return "redirect:" + "/sso-error";
	}
}