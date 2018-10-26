package vn.toancauxanh.gg.model.enums;

public enum QuyMoDuAn {
	QUY_MO_LON("Quy mô lớn"),
	QUY_MO_VUA("Quy mô vừa"),
	QUY_MO_NHO("Quy mô nhỏ");
	
	String text;
	
	QuyMoDuAn(final String txt) {
		text = txt;
	}
	public String getText() {
		return text;
	}
}
