package vn.toancauxanh.gg.model.enums;

public enum ThongBaoEnum {
	THONG_BAO_DOAN_VAO("Thông báo đoàn vào"),
	THONG_BAO_DU_AN("Thông báo dự án");
	
	String text;

	ThongBaoEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
