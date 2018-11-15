package vn.toancauxanh.gg.model.enums;

public enum QuocGiaEnum {
	AFGHANISTAN("Afghanistan");
	
	String text;

	QuocGiaEnum(final String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
