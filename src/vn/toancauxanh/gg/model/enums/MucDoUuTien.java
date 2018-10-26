package vn.toancauxanh.gg.model.enums;

public enum MucDoUuTien {
	UU_TIEN_CAO("Độ ưu tiên cao"),
	UU_TIEN_TRUNG_BINH("Độ ưu tiên trung bình"),
	UU_TIEN_THAP("Độ ưu tiên thấp");
	
	String text;
	MucDoUuTien(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
