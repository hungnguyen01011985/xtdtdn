
package vn.toancauxanh.service;

import java.io.File;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import vn.toancauxanh.cms.service.ThamSoService;
import vn.toancauxanh.gg.model.QuocGia;
import vn.toancauxanh.model.ThanhVienDoan;
import vn.toancauxanh.model.VaiTro;

@SuppressWarnings({ "deprecation", "unused" })
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
	@Value("${url.donvihanhchinh}")
	public String donvihanhchinh = "";

	@Value("${url.donvihanhchinh}")
	public String DONVIHANHCHINH = "";

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
	@Value("${url.quanlyduan}" + ":" + "${action.nhacnho}")
	public String QUANLYDUANNHACNHO;

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

	@Value("${url.donvihanhchinh}" + ":" + "${action.list}")
	public String DONVIHANHCHINHLIST;
	@Value("${url.donvihanhchinh}" + ":" + "${action.them}")
	public String DONVIHANHCHINHTHEM;
	@Value("${url.donvihanhchinh}" + ":" + "${action.xem}")
	public String DONVIHANHCHINHXEM;
	@Value("${url.donvihanhchinh}" + ":" + "${action.xoa}")
	public String DONVIHANHCHINHXOA;
	@Value("${url.donvihanhchinh}" + ":" + "${action.sua}")
	public String DONVIHANHCHINHSUA;

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

	// aend
	public String[] getRESOURCES() { // Các title của vai trò
		return new String[] { NGUOIDUNG, DONVIHANHCHINH, QUANLYDUAN, QUANLYGIAOVIEC, QUANLYDOANVAO, QUANLYPHONGBAN,
				QUANLYLINHVUCDUAN }; //
	}

	public String[] getACTIONS() {
		return new String[] { LIST, XEM, THEM, SUA, XOA, GIAOVIEC, NHACNHO };
	}

	static {
		File file = new File(Labels.getLabel("filestore.root") + File.separator + Labels.getLabel("filestore.folder"));
	}
	@Autowired
	public Environment env;

	@Autowired
	DataSource dataSource;

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

	@RequestMapping(value = "/upload2")
	public String upLoad() {
		return "forward:/WEB-INF/zul/upload/upload2.zul";
	}

	@RequestMapping(value = "/cp")
	public String cp2() {
		return "forward:/WEB-INF/zul/home.zul?resource=quanlyduan&action=lietke&file=/WEB-INF/zul/quanlyduan/list.zul&macdinh=home";
	}

	@RequestMapping(value = "/cp/{path:.+$}")
	public String cp(@PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/list.zul";
	}

	@RequestMapping(value = "/cp/{rsc:.+$}/{path:.+$}")
	public String add2(@PathVariable String rsc, @PathVariable String path) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + rsc + "&action=lietke&file=/WEB-INF/zul/" + rsc + "/" + path
				+ ".zul";
	}

	@RequestMapping(value = "/cp/{path:.+$}/view/{id:\\d+}")
	public String view(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/view-page.zul&id=" + id;
	}

	@RequestMapping(value = "/cp/thongke/{path:.+$}/{id:\\d+}")
	public String viewThongKe(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=thongke&action=lietke&file=/WEB-INF/zul/thongke/" + path
				+ ".zul&id=" + id;
	}

	@RequestMapping(value = "/cp/{path:.+$}/edit/{id:\\d+}")
	public String edit2(@PathVariable String path, @PathVariable Long id) {
		return "forward:/WEB-INF/zul/home.zul?resource=" + path + "&action=lietke&file=/WEB-INF/zul/" + path
				+ "/add-page.zul&id=" + id;
	}

	// Ban xúc tiến
	// start
	public final PhongBanService getPhongBans() {
		return new PhongBanService();
	}

	public final DoanVaoService getDoanVaos() {
		return new DoanVaoService();
	}

	public final LinhVucDuAnService getLinhVucs() {
		return new LinhVucDuAnService();
	}

	public final ThanhVienDoanService getThanhVienDoans(){
		return new ThanhVienDoanService();
	}
	// end

	public final NhanVienService getNhanViens() {
		return new NhanVienService();
	}

	public final DiTichService getDitichs() {
		return new DiTichService();
	}

	public final VaiTroService getVaiTros() {
		return new VaiTroService();
	}

	public final QuocGia getQuocGias() {
		return new QuocGia();
	}

	public final Quyen getQuyen() {
		return getNhanVien().getTatCaQuyen();
	}

	public final ThamSoService getThamSos() {
		return new ThamSoService();
	}

	public final VideoService getVideos() {
		return new VideoService();
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

	public final GopYPhanMemService getGopYPhanMem() {
		return new GopYPhanMemService();
	}

	@RequestMapping(value = "/{type:.+$}/{id:\\d+}")
	public String showDetail(@PathVariable("type") String type, @PathVariable("id") String id) {
		return "forward:/frontend/home/home.zhtml?type=" + type + "&id=" + id;
	}

	@RequestMapping(value = "/")
	public String showHome() {
		return "forward:/frontend/home/home.zhtml";
	}

	@RequestMapping(value = "/search")
	public String showHomeSearch() {
		return "forward:/frontend/home/search.zhtml";
	}

	public final DonViService getDonVis() {
		return new DonViService();
	}

	public final DonViHanhChinhService getDonViHanhChinhs() {
		return new DonViHanhChinhService();
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