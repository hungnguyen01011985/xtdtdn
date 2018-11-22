package vn.toancauxanh.gg.model.enums;

public enum LoaiThongBao {
	CONG_VIEC_MOI("Công việc mới"),
	NHAC_NHO_CONG_VIEC("Nhắc nhở công việc"),
	TRE_CONG_VIEC("Trễ công việc"),
	DEN_HAN_CONG_VIEC("Đến hạn công việc");
	String text;
	LoaiThongBao(String text){
		this.text = text;
	}
	public String getText() {
		return text;
	}
}
