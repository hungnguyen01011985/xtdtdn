package vn.toancauxanh.gg.model.enums;

public enum DonViChuTri {
	TRUNG_TAM_PHAT_TRIEN_QUY_DAT("Trung tâm phát triển quỹ đất");
	String text;
	DonViChuTri(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
