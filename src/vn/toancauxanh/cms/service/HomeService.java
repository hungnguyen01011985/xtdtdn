package vn.toancauxanh.cms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.util.SystemPropertyUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import vn.toancauxanh.gg.model.enums.HomeEnum;
import vn.toancauxanh.service.BasicService;

public class HomeService extends BasicService<Object> {

	public HomeService() {
		super();
		// init();
		// login();

		String key = getClass() + "." + "accessed";
		if (!Sessions.getCurrent().hasAttribute(key)) {
			Sessions.getCurrent().setAttribute(key, "1");
			getSetting().addCounter();
		}
	}

	public String formatDate(Date d) {
		return new SimpleDateFormat("dd/MM/yyyy").format(d);
	}

	public String formatDayOfCalendar(Date d) {
		return new SimpleDateFormat("dd").format(d);
	}

	public String formatMonthYearOfCalendar(Date d) {
		return new SimpleDateFormat("MM/yyyy").format(d);
	}

	@Override
	public Date getTuNgay() {
		return date(Labels.getLabel("param.tungay"));
	}

	@Override
	public Date getDenNgay() {
		return date(Labels.getLabel("param.denngay"));
	}

	public boolean isTimChinhXac() {
		boolean exactly = MapUtils.getBooleanValue(getArg(), "chinhxac");
		return exactly;
	}

	public long getCid() {
		return MapUtils.getLongValue(argDeco(), "id");
	}

	public int getPageSize() {
		int pagesize = MapUtils.getIntValue(getArg(), SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGESIZE),
				defaultPageSize());
		if (pagesize == 0) {
			pagesize = 10;
		}
		return pagesize;
	}

	public int getPageSizeImage() {
		int pagesize = MapUtils.getIntValue(getArg(), SystemPropertyUtils.resolvePlaceholders(PH_KEYPAGESIZE),
				defaultPageSize());
		if (pagesize == 0) {
			pagesize = 9;
		}
		return pagesize;
	}

	public int getPage() {
		int trang = MapUtils.getIntValue(getArg(), "page");
		return trang;
	}

	private int numberResultBegin = 0;

	private int numberResultEnd = 0;

	private String searchByKey = "";

	private double lngPlace;

	private double latPlace;

	private String placeSelected = "";

	public boolean isCheckSubmitSearch() {
		return checkSubmitSearch;
	}

	public void setCheckSubmitSearch(boolean checkSubmitSearch) {
		this.checkSubmitSearch = checkSubmitSearch;
	}

	private boolean checkSubmitSearch = false;

	public String getPlaceSelected() {
		return placeSelected;
	}

	public void setPlaceSelected(String placeSelected) {
		this.placeSelected = placeSelected;
	}

	public double getLngPlace() {
		return lngPlace;
	}

	public void setLngPlace(double lngPlace) {
		this.lngPlace = lngPlace;
	}

	public double getLatPlace() {
		return latPlace;
	}

	public void setLatPlace(double latPlace) {
		this.latPlace = latPlace;
	}

	public String getSearchByKey() {
		return searchByKey;
	}

	public void setSearchByKey(String searchByKey) {
		this.searchByKey = searchByKey;
	}

	private String checkShow = HomeEnum.SHOWMAP.getText();

	public String getCheckShow() {
		return checkShow;
	}

	public void setCheckShow(String checkShow) {
		this.checkShow = checkShow;
	}

	@Command
	public void showDetail() {
		setCheckShow(HomeEnum.HIDDENMAP.getText());
		setUrlPageDetail("/frontend/home/thongtin.zhtml");
		Clients.evalJavaScript("setHereNowTrue()");
		/*
		 * Clients.evalJavaScript("	.pushState(null, null, '"+Executions.
		 * getCurrent().getContextPath()+"');");
		 */
		BindUtils.postNotifyChange(null, null, this, "checkShow");
		BindUtils.postNotifyChange(null, null, this, "urlPageDetail");
		BindUtils.postNotifyChange(null, null, this, "offsetReSultLienQuans");
		BindUtils.postNotifyChange(null, null, this, "nameDoiTuongLienQuan");
		Clients.evalJavaScript("showGioiThieu()");
	}

	public void resetPage() {
		Map<Object, Object> arg = super.getArg();
		arg.put("page", 0);
		BindUtils.postNotifyChange(null, null, this, "page");
	}
	
	private String urlPageDetail;

	public String getUrlPageDetail() {
		return urlPageDetail;
	}

	public void setUrlPageDetail(String urlPageDetail) {
		this.urlPageDetail = urlPageDetail;
	}

	@Command
	public void showMoreDetail(@BindingParam("url") String url) {
		resetPage();
		setUrlPageDetail(url);
		BindUtils.postNotifyChange(null, null, this, "urlPageDetail");
		Clients.evalJavaScript("scrollLocation()");
	}

	private static final int limitResult = 10;

	private int offset = 0;
	private long sizeLienQuan = 0;

	public long getSizeLienQuan() {
		return sizeLienQuan;
	}

	public void setSizeLienQuan(long sizeLienQuan) {
		this.sizeLienQuan = sizeLienQuan;
	}

	private String searchCategory = HomeEnum.DITICH.getText();
	private String searchCategoryTemp = HomeEnum.DITICH.getText();

	public String getSearchCategoryTemp() {
		return searchCategoryTemp;
	}

	public void setSearchCategoryTemp(String searchCategoryTemp) {
		this.searchCategoryTemp = searchCategoryTemp;
	}

	private ListModelList<Object> listObject = new ListModelList<>();

	public void setListObject(ListModelList<Object> listObject) {
		this.listObject = listObject;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	@Command
	public void chooseSearch(@BindingParam("searchCategory") String searchCategory) {
		Map<Object, Object> arg = this.getArg();
		arg.put("loai", null);
		BindUtils.postNotifyChange(null, null, this, "arg");
		setSearchCategoryTemp(searchCategory);
		// setNumberResultBegin(0);
		// setNumberResultEnd(0);
		BindUtils.postNotifyChange(null, null, this, "listObjectLoai");
		// BindUtils.postNotifyChange(null, null, this, "listObject");
		BindUtils.postNotifyChange(null, null, this, "searchLoai");
		// BindUtils.postNotifyChange(null, null, this, "numberResultEnd");
		// BindUtils.postNotifyChange(null, null, this, "numberResultBegin");
		// BindUtils.postNotifyChange(null, null, this, "numberSizeList");
		// Clients.evalJavaScript("addingMarker()");
		// Clients.evalJavaScript("resetInputId()");
		// Clients.evalJavaScript("setHeightSearch()");
	}

	private boolean hiddenSearch;

	public boolean isHiddenSearch() {
		return hiddenSearch;
	}

	public void setHiddenSearch(boolean hiddenSearch) {
		this.hiddenSearch = hiddenSearch;
	}

	public String getSearchLoai() {
		String loai = "";
		if (HomeEnum.DITICH.getText().equals(searchCategoryTemp)) {
			loai = "Loại di tích và danh thắng";
		} else if (HomeEnum.LEHOI.getText().equals(searchCategoryTemp)) {
			loai = "Loại bảo tàng";
		} else if (HomeEnum.DISAN.getText().equals(searchCategoryTemp)) {
			loai = "Loại di sản";
		}
		return loai;
	}

	public String getNameDoiTuong() {
		String loai = "";
		if (HomeEnum.DITICH.getText().equals(searchCategory)) {
			loai = "Di tích và danh thắng";
		} else if (HomeEnum.LEHOI.getText().equals(searchCategory)) {
			loai = "Bảo tàng";
		} else if (HomeEnum.DISAN.getText().equals(searchCategory)) {
			loai = "Di sản";
		}
		return loai;
	}

	@Command
	public void showView(@BindingParam("vm") Object vm, @BindingParam("objectDetail") Object objectDetail,
			@BindingParam("url") String url, @BindingParam("index") int index,
			@BindingParam("list") List<Object> list) {
		Map<String, Object> map = new HashMap<>();
		map.put("vm", vm);
		map.put("objectDetail", objectDetail);
		map.put("index", index - 1);
		map.put("list", list);
		Executions.createComponents(url, null, map);
	}

	public int getNumberResultBegin() {
		numberResultBegin = (listObject.isEmpty()) ? 0 : offset + 1;
		return numberResultBegin;
	}

	public void setNumberResultBegin(int numberResultBegin) {
		this.numberResultBegin = numberResultBegin;
	}

	public int getNumberResultEnd() {
		numberResultEnd = (limitResult > listObject.size()) ? offset + listObject.size() : offset + limitResult;
		return numberResultEnd;
	}

	public void setNumberResultEnd(int numberResultEnd) {
		this.numberResultEnd = numberResultEnd;
	}

	private int limitReSultDetailLienQuans = 10;

	public int getLimitReSultDetailLienQuans() {
		return limitReSultDetailLienQuans;
	}

	public void setLimitReSultDetailLienQuans(int limitReSultDetailLienQuans) {
		this.limitReSultDetailLienQuans = limitReSultDetailLienQuans;
	}

	private int offsetReSultLienQuans = 0;

	ListModelList<Object> listLienQuanByLoai = new ListModelList<>();

	public int getOffsetReSultLienQuans() {
		return offsetReSultLienQuans;
	}

	public void setOffsetReSultLienQuans(int offsetReSultLienQuans) {
		this.offsetReSultLienQuans = offsetReSultLienQuans;
	}

}