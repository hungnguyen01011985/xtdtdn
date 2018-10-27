package vn.toancauxanh.gg.model.enums;

public enum PhuongThucLuaChonNDT {
	DAU_GIA_QUYEN_SU_DUNG_DAT("Đấu giá quyền sử dụng đất"),
	DAU_THAU_DU_AN_CO_SU_DUNG_DAT("Đấu thầu dự án có sử dụng đất"),
	LUA_CHON_NHA_DAU_TU("Lựa chọn nhà đầu tư");
	String text;
	PhuongThucLuaChonNDT(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
