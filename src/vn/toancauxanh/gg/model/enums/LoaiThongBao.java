package vn.toancauxanh.gg.model.enums;

public enum LoaiThongBao {
	CONG_VIEC_MOI("Công việc mới"),
	TRE_CONG_VIEC("Trễ công việc");
	String text;
	LoaiThongBao(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
