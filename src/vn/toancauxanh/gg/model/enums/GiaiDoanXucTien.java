package vn.toancauxanh.gg.model.enums;

public enum GiaiDoanXucTien{
	GIAI_DOAN_MOT("Giới thiệu địa điểm"),
	GIAI_DOAN_HAI("Khảo sát địa điểm"),
	GIAI_DOAN_BA("Xin chủ trương"),
	GIAI_DOAN_BON("Lựa chọn NĐT"),
	GIAI_DOAN_NAM("Nhà đầu tư"),
	CHUA_HOAN_THANH("Hủy xúc tiến"),
	HOAN_THANH("Hoàn thành xúc tiến");
	String text;
	GiaiDoanXucTien(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
