package vn.toancauxanh.gg.model.enums;

public enum ThoiHanConLai {
	DA_QUA_HAN("Đã quá hạn"),
	DA_DEN_HAN("Đã đến hạn"),
	CHUA_DEN_HAN("Chưa đến hạn");
	String text;
	ThoiHanConLai(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
