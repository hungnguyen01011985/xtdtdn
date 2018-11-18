package vn.toancauxanh.gg.model.enums;

public enum TrangThaiCongViec {
	CHUA_THUC_HIEN("Chưa thực hiện"),
	DANG_THUC_HIEN("Đang thực hiện"),
	HOAN_THANH("Hoàn thành");
	
	String text;

	TrangThaiCongViec(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
