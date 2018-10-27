package vn.toancauxanh.gg.model.enums;

public enum DonViTuVan {
	VIEN_QUY_HOACH_DO_THI_DA_NANG("Viện quy hoạch đô thị Đà Nẵng");
	String text;
	DonViTuVan(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
