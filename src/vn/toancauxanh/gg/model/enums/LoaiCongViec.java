package vn.toancauxanh.gg.model.enums;

public enum LoaiCongViec {
	DU_AN("Quản lý dự án"),
	DOAN_VAO("Quản lý đoàn vào");
	String text;
	LoaiCongViec(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
