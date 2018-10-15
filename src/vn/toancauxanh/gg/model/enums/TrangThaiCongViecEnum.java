package vn.toancauxanh.gg.model.enums;

public enum TrangThaiCongViecEnum {
	CHUA_THUC_HIEN("Chưa thực hiện"), DANG_THUC_HIEN("Đang thực hiện"), DA_THUC_HIEN("Đã thực hiện");
	
	TrangThaiCongViecEnum(String text) {
		this.text = text;
	}

	String text;

	public String getText() {
		return text;
	}
}
