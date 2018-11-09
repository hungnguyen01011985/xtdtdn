package vn.toancauxanh.gg.model.enums;

public enum LoaiCongViec {
	DU_AN("Dự án"),
	DOAN_VAO("Đoàn vào");
	String text;
	LoaiCongViec(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
