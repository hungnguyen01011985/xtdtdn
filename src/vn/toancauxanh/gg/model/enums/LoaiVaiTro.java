package vn.toancauxanh.gg.model.enums;

public enum LoaiVaiTro {
	VAI_TRO_QUAN_TRI("Quản trị hệ thống"),
	VAI_TRO_CHUYEN_VIEN("Chuyên viên"),
	VAI_TRO_TRUONG_PHONG("Trưởng phòng"),
	VAI_TRO_LANH_DAO("Lãnh đạo");
	
	String text;

	LoaiVaiTro(final String txt) {
		text = txt;
	}

	public String getText() {
		return text;
	}
}
