package vn.toancauxanh.gg.model.enums;

public enum TrangThaiEnum {
	CHUA_TIEP("Chưa tiếp"),
	DANG_TIEP("Đang tiếp"),
	DA_TIEP("Đã tiếp");
	String text;
	TrangThaiEnum(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
