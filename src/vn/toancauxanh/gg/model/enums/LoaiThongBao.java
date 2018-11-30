package vn.toancauxanh.gg.model.enums;

public enum LoaiThongBao {
	CONG_VIEC_MOI("Công việc mới"),
	NHAC_NHO_CONG_VIEC("Nhắc nhở công việc"),
	TRE_CONG_VIEC("Trễ công việc"),
	PHU_TRACH_CONG_VIEC("Phụ trách công việc"),
	CHUYEN_CONG_VIEC_DOAN_VAO("Chuyển công việc"),
	CHUYEN_NGUOI_PHU_TRACH("Chuyển người phụ trách"),
	DEN_HAN_CONG_VIEC("Đến hạn công việc"),
	HUY_CONG_VIEC("Hủy công việc");
	String text;

	LoaiThongBao(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
