package vn.toancauxanh.gg.model.enums;

public enum KhaNangDauTu {
	KHA_NANG_THAP("Thấp"),
	KHA_NANG_KHA("Khá"),
	KHA_NANG_CAO("Cao");
	String text;
	KhaNangDauTu(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
