package vn.toancauxanh.gg.model.enums;

public enum NoiDungCongViec {
	TITLE_NHAN_SU_LAM_VIEC("Nhân sự làm việc"),
	CONG_VIEC_NGUOI_DUOC_PHAN_CONG("Lãnh đạo, người được phân công"),
	CONG_VIEC_CHUYEN_VIEN("Chuyên viên"),
	TITLE_CONG_TAC_HAU_CAN("Công tác hậu cần"),
	CONG_VIEC_CHUAN_BI_PHONG_HOP("Chuẩn bị phòng họp (nếu cần)"),
	CONG_VIEC_CHUAN_BI_HOA_QUA("Nước, hoa, quà (nếu cần)"),
	CONG_VIEC_CHUAN_BI_THIET_BI("Thiết bị phục vụ phòng họp (âm thanh LCD màn hình máy tính,...) (nếu cần)"),
	CONG_VIEC_CHUAN_BI_TAI_LIEU("Chuẩn bị tài liệu giới thiệu (Sách giới thiệu Đà Nẵng)"),
	TITLE_CONG_TAC_KHAC("Công tác khác"),
	CONG_VIEC_XAY_DUNG_CHUONG_TRINH("Xây dựng chương trình làm việc cho đoàn (nếu cần)"),
	CONG_VIEC_CHUAN_BI_BAI_GIOI_THIEU("Xây dựng bài giới thiệu"),
	CONG_VIEC_XAC_NHAN_LAI_THONG_TIN("Xác nhận lại thông tin và thời gian làm việc với đoàn"),
	CONG_VIEC_GHI_BIEN_BAN("Ghi biên bản nội dung làm việc"),
	CONG_VIEC_KIEM_TRA_LAI_CONG_TAC_CHUAN_BI("Kiểm tra lại công tác chuẩn bị");
	
	String text;

	NoiDungCongViec(final String txt) {
		text = txt;
	}

	public String getText() {
		return text;
	}
}
