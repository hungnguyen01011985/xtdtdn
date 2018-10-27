package vn.toancauxanh.gg.model.enums;

public enum GiaiDoanXucTien {
	GIAI_DOAN_MOT("Giới thiệu địa điểm"),
	GIAI_DOAN_HAI("Khảo sát địa điẻm"),
	GIAI_DOAN_BA("Xin chủ trương"),
	GIAI_DOAN_BON("Lựa chọn NĐT"),
	HOAN_THANH("Hoàn thành xúc tiến");
	String text;
	GiaiDoanXucTien(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
