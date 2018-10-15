package vn.toancauxanh.gg.model.enums;

public enum TrangThaiTiepDoanEnum {
	CHUA_TIEP("Chưa tiếp"), DANG_TIEP("Đang tiếp"), DA_TIEP("Đã tiếp");
	
	TrangThaiTiepDoanEnum(String text) {
		this.text = text;
	}

	String text;

	public String getText() {
		return text;
	}
}
