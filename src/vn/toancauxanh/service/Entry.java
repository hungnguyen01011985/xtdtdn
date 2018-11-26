
package vn.toancauxanh.service;

import java.io.File;

import javax.sql.DataSource;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Object;

import vn.toancauxanh.cms.service.HomeService;
import vn.toancauxanh.cms.service.LanguageService;
import vn.toancauxanh.cms.service.PhongBanService;
import vn.toancauxanh.model.VaiTro;

@SuppressWarnings({ "unused" })
@Configuration
@Controller
public class Entry extends BaseObject<Object> {
	static Entry instance;
	@Value("${trangthai.apdung}")
	public String TT_AP_DUNG = "";
	@Value("${trangthai.daxoa}")
	public String TT_DA_XOA = "";
	@Value("${trangthai.khongapdung}")
	public String TT_KHONG_AP_DUNG = "";

	@Value("${filestore.root}")
	public String FOLDER_ROOT = "";
	@Value("${filestore.files}")
	public String FOLDER_FILES = "";
	@Value("${filestore.folder}")
	public String FOLDER_FILEFOLDER = "";

	// No image url
	public String URL_M_NOIMAGE = "/assetsfe/images/lg_noimage.png";
	public String URL_S_NOIMAGE = "/assetsfe/images/sm_noimage.png";

	@Value("${action.xem}")
	public String XEM = ""; // duoc xem bat ky
	@Value("${action.list}")
	public String LIST = ""; // duoc vao trang list search url
	@Value("${action.sua}")
	public String SUA = ""; // duoc sua bat ky
	@Value("${action.xoa}")
	public String XOA = ""; // duoc xoa bat ky
	@Value("${action.them}")
	public String THEM = ""; // duoc them
	@Value("${action.giaoviec}")
	public String GIAOVIEC = ""; // duoc giao viec
	@Value("${action.nhacnho}")
	public String NHACNHO = "";
	@Value("${action.phutrach}") // duoc cap nhat nguoi phu trach
	public String PHUTRACH = "";
	@Value("${action.duan}")
	public String DUAN = "";
	@Value("${action.doanvao}")
	public String DOANVAO = "";
	@Value("${action.congviec}")
	public String CONGVIEC = "";
	
	@Value("${url.quanlyduan}")
	public String QUANLYDUAN = "";
	@Value("${url.quanlydoanvao}")
	public String QUANLYDOANVAO = "";
	@Value("${url.quanlygiaoviec}")
	public String QUANLYGIAOVIEC = "";
	@Value("${url.linhVucDuAn}")
	public String QUANLYLINHVUCDUAN = "";
	@Value("${url.phongban}")
	public String QUANLYPHONGBAN = "";
	@Value("${url.nguoidung}")
	public String NGUOIDUNG = "";
	@Value("${url.phanquyen}")
	public String PHANQUYEN = "";
	@Value("${url.baocaothongke}")
	public String BAOCAOTHONGKE = "";

	@Value("${url.vaitro}")
	public String VAITRO = "";

	// uend
	public char CHAR_CACH = ':';
	public String CACH = CHAR_CACH + "";

	// Thêm các tùy chọn vai trò của chức năng tương ứng
	
	@Value("${url.quanlyduan}" + ":" + "${action.list}")
	public String QUANLYDUANLIST;
	@Value("${url.quanlyduan}" + ":" + "${action.xem}")
	public String QUANLYDUANXEM;
	@Value("${url.quanlyduan}" + ":" + "${action.them}")
	public String QUANLYDUANTHEM;
	@Value("${url.quanlyduan}" + ":" + "${action.sua}")
	public String QUANLYDUANSUA;
	@Value("${url.quanlyduan}" + ":" + "${action.xoa}")
	public String QUANLYDUANXOA;
	@Value("${url.quanlyduan}" + ":" + "${action.giaoviec}")
	public String QUANLYDUANGIAOVIEC;
	@Value("${url.quanlyduan}" + ":" + "${action.phutrach}")
	public String QUANLYDUANPHUTRACH;

	@Value("${url.quanlydoanvao}" + ":" + "${action.list}")
	public String QUANLYDOANVAOLIST;
	@Value("${url.quanlydoanvao}" + ":" + "${action.xem}")
	public String QUANLYDOANVAOXEM;
	@Value("${url.quanlydoanvao}" + ":" + "${action.them}")
	public String QUANLYDOANVAOTHEM;
	@Value("${url.quanlydoanvao}" + ":" + "${action.sua}")
	public String QUANLYDOANVAOSUA;
	@Value("${url.quanlydoanvao}" + ":" + "${action.xoa}")
	public String QUANLYDOANVAOXOA;
	@Value("${url.quanlydoanvao}" + ":" + "${action.nhacnho}")
	public String QUANLYDOANVAONHACNHO;

	@Value("${url.quanlygiaoviec}" + ":" + "${action.list}")
	public String QUANLYGIAOVIECLIST;
	@Value("${url.quanlygiaoviec}" + ":" + "${action.xem}")
	public String QUANLYGIAOVIECXEM;
	@Value("${url.quanlygiaoviec}" + ":" + "${action.sua}")
	public String QUANLYGIAOVIECSUA;
	@Value("${url.quanlygiaoviec}" + ":" + "${action.xoa}")
	public String QUANLYGIAOVIECXOA;
	@Value("${url.quanlygiaoviec}" + ":" + "${action.them}")
	public String QUANLYGIAOVIECTHEM;
	

	@Value("${url.phongban}" + ":" + "${action.list}")
	public String QUANLYPHONGBANLIST;
	@Value("${url.phongban}" + ":" + "${action.them}")
	public String QUANLYPHONGBANTHEM;
	@Value("${url.phongban}" + ":" + "${action.xem}")
	public String QUANLYPHONGBANXEM;
	@Value("${url.phongban}" + ":" + "${action.sua}")
	public String QUANLYPHONGBANSUA;
	@Value("${url.phongban}" + ":" + "${action.xoa}")
	public String QUANLYPHONGBANXOA;

	@Value("${url.nguoidung}" + ":" + "${action.list}")
	public String NGUOIDUNGLIST;
	@Value("${url.nguoidung}" + ":" + "${action.them}")
	public String NGUOIDUNGTHEM;
	@Value("${url.nguoidung}" + ":" + "${action.sua}")
	public String NGUOIDUNGSUA;
	@Value("${url.nguoidung}" + ":" + "${action.xoa}")
	public String NGUOIDUNGXOA;
	@Value("${url.nguoidung}" + ":" + "${action.xem}")
	public String NGUOIDUNGXEM;

	@Value("${url.linhVucDuAn}" + ":" + "${action.list}")
	public String LINHVUCDUANLIST;
	@Value("${url.linhVucDuAn}" + ":" + "${action.them}")
	public String LINHVUCDUANTHEM;
	@Value("${url.linhVucDuAn}" + ":" + "${action.sua}")
	public String LINHVUCDUANSUA;
	@Value("${url.linhVucDuAn}" + ":" + "${action.xoa}")
	public String LINHVUCDUANXOA;
	@Value("${url.linhVucDuAn}" + ":" + "${action.xem}")
	public String LINHVUCDUANXEM;

	@Value("${url.phanquyen}" + ":" + "${action.xem}")
	public String PHANQUYENXEM;
	@Value("${url.phanquyen}" + ":" + "${action.them}")
	public String PHANQUYENTHEM;
	@Value("${url.phanquyen}" + ":" + "${action.sua}")
	public String PHANQUYENSUA;
	@Value("${url.phanquyen}" + ":" + "${action.xoa}")
	public String PHANQUYENXOA;
	@Value("${url.phanquyen}" + ":" + "${action.list}")
	public String PHANQUYENLIETKE;
	@Value("${url.phanquyen}" + ":" + "${action.tim}")
	public String PHANQUYENTIMKIEM;

	@Value("${url.vaitro}" + ":" + "${action.xem}")
	public String VAITROXEM = "";
	@Value("${url.vaitro}" + ":" + "${action.them}")
	public String VAITROTHEM = "";
	@Value("${url.vaitro}" + ":" + "${action.list}")
	public String VAITROLIST = "";
	@Value("${url.vaitro}" + ":" + "${action.xoa}")
	public String VAITROXOA = "";
	@Value("${url.vaitro}" + ":" + "${action.sua}")
	public String VAITROSUA = "";
	@Value("${url.vaitro}" + ":" + "${action.tim}")
	public String VAITROTIMKIEM;
	
	@Value("${url.baocaothongke}" + ":" + "${action.duan}")
	public String BAOCAOTHONGKEDUAN = "";
	@Value("${url.baocaothongke}" + ":" + "${action.doanvao}")
	public String BAOCAOTHONGKEDOANVAO = "";
	@Value("${url.baocaothongke}" + ":" + "${action.congviec}")
	public String BAOCAOTHONGKECONGVIEC = "";
	// aend
	public String[] getRESOURCES() { // Các title của vai trò
		return new String[] { NGUOIDUNG, QUANLYDUAN, QUANLYGIAOVIEC, QUANLYDOANVAO, QUANLYPHONGBAN,
				QUANLYLINHVUCDUAN, BAOCAOTHONGKE}; //
	}

	public String[] getACTIONS() {
		return new String[] { LIST, XEM, THEM, SUA, XOA, GIAOVIEC, NHACNHO, PHUTRACH, DUAN, DOANVAO, CONGVIEC };
	}

	static {
		File file = new File(Labels.getLabel("filestore.root") + File.separator + Labels.getLabel("filestore.folder"));
	}
	@Autowired
	public Environment env;

	@Autowired
	DataSource dataSource;

	@Autowired
	@Lazy
	ProcessEngine processEngine;

	public Entry() {
		super();
		setCore();
		instance = this;
	}

	@Bean
	public FilterRegistrationBean cacheFilter() {
		FilterRegistrationBean rs = new FilterRegistrationBean(new CacheFilter());
		rs.addUrlPatterns("*.css");
		rs.addUrlPatterns("*.js");
		rs.addUrlPatterns("*.wpd");
		rs.addUrlPatterns("*.wcs");
		rs.addUrlPatterns("*.jpg");
		rs.addUrlPatterns("*.jpeg");
		rs.addUrlPatterns("*.png");
		rs.addUrlPatterns("*.svg");
		rs.addUrlPatterns("*.gif");
		rs.addUrlPatterns("*.tff");
		return rs;
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "forward:/WEB-INF/zul/login.zul";
	}

	@RequestMapping(value = "/cp")
	public String cp() {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/list.zul";
	}

	@RequestMapping(value = "/cp/{path:.+$}")
	public String cp(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/list.zul";
	}
	
	@RequestMapping(value = "/cp/baocaothongke/{path:.+$}")
	public String add2(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=baocaothongke&action="+path+"&file=/WEB-INF/zul/baocaothongke/" + path
				+ ".zul";
	}
	
	@RequestMapping(value = "/cp/{rsc:.+$}/{path:.+$}")
	public String add2(@PathVariable String rsc, @PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + rsc + "&action=them&file=/WEB-INF/zul/" + rsc + "/" + path
				+ ".zul";
	}
	
	@RequestMapping(value = "/cp/{path:.+$}/edit/{id:\\d+}")
	public String edit2(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=sua&file=/WEB-INF/zul/" + path
				+ "/show-doan-vao.zul&id=" + id;
	}
	
	@RequestMapping(value = "/cp/{path:.+$}/detail/{id:\\d+}")
	public String detail(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=xem&file=/WEB-INF/zul/" + path
				+ "/show-chi-tiet-doan-vao.zul&id=" + id;
	}

	@RequestMapping(value = "/cp/quanlyduan/{id:\\d+}")
	public String giaidoan(@PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/add-giai-doan.zul&id="
				+ id;
	}
	
	@RequestMapping(value = "/cp/quanlyduan/chitiet/{id:\\d+}")
	public String xemChiTiet(@PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/view-giai-doan.zul&id="
				+ id;
	}

	public final ProcessEngine getProcess() {
		return processEngine;
	}

	public final ProcessService getProcessService() {
		return new ProcessService();
	}

	public final PhongBanService getPhongBans() {
		return new PhongBanService();
	}
	
	public final DuAnService getDuAns(){
		return new DuAnService();
	}
	
	public final DonViService getDonVis(){
		return new DonViService();
	}
	
	public final CapDonViService getCapDonVis(){
		return new CapDonViService();
	}

	public final DoanVaoService getDoanVaos() {
		return new DoanVaoService();
	}

	public final LinhVucDuAnService getLinhVucs() {
		return new LinhVucDuAnService();
	}

	public final ThanhVienDoanService getThanhVienDoans() {
		return new ThanhVienDoanService();
	}
	// end

	public final NhanVienService getNhanViens() {
		return new NhanVienService();
	}

	public final VaiTroService getVaiTros() {
		return new VaiTroService();
	}

	public final Quyen getQuyen() {
		return getNhanVien().getTatCaQuyen();
	}

	public final ThucTrangDitichService getThucTrangDitichs() {
		return new ThucTrangDitichService();
	}

	public final LoaiDiTichService getLoaiDiTichs() {
		return new LoaiDiTichService();
	}

	public final LoaiLeHoiService getLoaiLeHois() {
		return new LoaiLeHoiService();
	}

	public final LoaiDiSanService getLoaiDiSans() {
		return new LoaiDiSanService();
	}

	public final HomeService getHomes() {
		return new HomeService();
	}

	public final LanguageService getLanguages() {
		return new LanguageService();
	}

	public boolean checkVaiTro(String vaiTro) {
		if (vaiTro == null || vaiTro.isEmpty()) {
			return false;
		}
		boolean rs = false;
		for (VaiTro vt : getNhanVien().getVaiTros()) {
			if (vaiTro.equals(vt.getAlias())) {
				rs = true;
				break;
			}
		}
		return rs;// || getQuyen().get(vaiTro);
	}

	@Configuration
	@EnableWebMvc
	public static class MvcConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/files/**").addResourceLocations("file:/home/hdndhoavangdata/hdndfiles/");
			registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
			registry.addResourceHandler("/backend/**").addResourceLocations("/backend/");
			registry.addResourceHandler("/img/**").addResourceLocations("/img/");
			registry.addResourceHandler("/login/**").addResourceLocations("/login/");
		}

		@Override
		public void configureViewResolvers(final ViewResolverRegistry registry) {
			registry.jsp("/WEB-INF/", "*");
		}

		@ExceptionHandler(ResourceNotFoundException.class)
		@ResponseStatus(HttpStatus.NOT_FOUND)
		public String handleResourceNotFoundException() {
			return "forward:/WEB-INF/zul/notfound.zul";
		}
	}

}