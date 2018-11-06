package vn.toancauxanh.gg.model.enums;

public enum LoaiDonVi {
	DON_VI_TU_VAN("Đơn vị tư vấn"),
	DON_VI_CHU_TRI("Đơn vị chủ trì"),
	DON_VI_THUC_HIEN("Đơn vị thực hiện");
	
	String text;

	LoaiDonVi(final String txt) {
		text = txt;
	}

	public String getText() {
		return text;
	}
}
