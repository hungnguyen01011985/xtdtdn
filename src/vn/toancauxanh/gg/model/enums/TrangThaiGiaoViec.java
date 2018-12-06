package vn.toancauxanh.gg.model.enums;

public enum TrangThaiGiaoViec {
	HOAN_THANH("Hoàn thành"),
	CHUA_LAM("Chưa thực hiện"),
	DANG_LAM("Đang làm");
	String text;
	TrangThaiGiaoViec(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
