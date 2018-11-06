package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.LoaiDonVi;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;

@Entity
@Table(name = "giaidoanduan")
public class GiaiDoanDuAn extends Model<GiaiDoanDuAn> {
	private DuAn duAn;
	private GiaiDoanXucTien giaiDoanXucTien;
	// Thông tin giai đoạn 1
	private Date ngayGui;
	private Date ngayNhanPhanHoi;
	private TepTin taiLieuGD1;
	private List<DonViDuAn> donViDuAn = new ArrayList<DonViDuAn>();
	// Thông tin giai đoạn 2
	private Date ngayKhaoSat;
	private String ghiChu;
	private TepTin taiLieuGD2;
	private Date ngayPhatHanhCVGD2;
	private TepTin congVanGD2;
	// Thông tin giai đoạn 3
	private Date ngayGuiUBND;
	private Date ngayGuiSoXayDung;
	private Date ngayDuKienNhanPhanHoi;
	private TepTin taiLieuGD3;
	private Date ngayPhatHanhCV3;
	private TepTin congVanGD3;
	// Thông tin giai đoạn 4
	private PhuongThucLuaChonNDT phuongThucLuaChonNDT;
	private DonVi donViChuTri;
	private boolean option = true;
	// Lập quy hoạch chi tiết 1/500
	private DonVi donViTuVan;
	private TepTin hoSoQuyHoachLQH;
	private TepTin quyetDinhPheDuyetLQH;
	// Lập quy hoạch chi tiết 1/2000
	private DonVi donViLapKeHoach;
	private TepTin hoSoQuyHoach2000;
	private TepTin quyetDinhPheDuyet2000;
	private TepTin nghiQuyetPheDuyetCongTrinh;
	private TepTin baoCaoDoDacKhuDat;
	private TepTin pheDuyetKeHoachSuDungDat;
	private TepTin quyetDinhThuHoiDat;
	private TepTin quyetDinhPheDuyetDanhMuc;
	private TepTin quyetDinhPheDuyetBoSungKinhPhi;
	private TepTin phuongAnTaiDinhCu;
	// Phương án đấu giá quyền sử dụng đất
	private TepTin phuongAnDauGia;
	private TepTin quyetDinhPheDuyetPADG;
	// Hồ sơ các khu đất
	private TepTin quyetDinhDauGiaQSDD;
	// Quyết định phê duyệt giá đất khởi điểm đấu giá
	private long giaDatKhoiDiemDauGia;
	private TepTin quyetDinhPheDuyetGiaKhoiDiem;
	// Đơn vị thực hiện đấu giá
	private DonVi donViThucHien;
	// Gửi công văn đề nghị bổ sung địa điểm thực hiện dự án
	private TepTin vanBanDeNghiBoSung;
	private TepTin quyetDinhBoSungDanhMuc;
	// Lập hồ sơ mời tuyển
	private TepTin hoSoMoiTuyen;
	private TepTin quyetDinhPheDuyeHoSoMoiTuyen;
	private TepTin quyetDinhPheDuyetKetQuaTrungTuyen;
	// Lập kế hoạch và hồ sơ mời thầu lựa chọn nhà đầu tư
	private TepTin keHoachLuaChonNhaDauTu;
	private TepTin hoSoMoiThau;
	private TepTin quyetDinhPheDuyetMoiThau;
	// Nhận chuyển nhượng
	private TepTin vanBanChuyenMucDichSDD;
	private TepTin vanBanDeNghiThuHoiDat;
	// Giai đoạn 5
	private String tenCongTy;
	private String nguoiDaiDienPhapLy;
	private String diaChi;
	private String soDienThoai;
	private String email;
	private TepTin giayChungNhanDauTu;
	private TepTin giayChungNhanDangKyDoanhNghiep;
	private TepTin giayChungNhanQuyenSuDungDat;
	private TepTin taiLieuDinhKem;

	@ManyToOne
	public TepTin getGiayChungNhanDauTu() {
		return giayChungNhanDauTu;
	}

	public void setGiayChungNhanDauTu(TepTin giayChungNhanDauTu) {
		this.giayChungNhanDauTu = giayChungNhanDauTu;
	}

	@ManyToOne
	public TepTin getGiayChungNhanDangKyDoanhNghiep() {
		return giayChungNhanDangKyDoanhNghiep;
	}

	public void setGiayChungNhanDangKyDoanhNghiep(TepTin giayChungNhanDangKyDoanhNghiep) {
		this.giayChungNhanDangKyDoanhNghiep = giayChungNhanDangKyDoanhNghiep;
	}

	@ManyToOne
	public TepTin getGiayChungNhanQuyenSuDungDat() {
		return giayChungNhanQuyenSuDungDat;
	}

	public void setGiayChungNhanQuyenSuDungDat(TepTin giayChungNhanQuyenSuDungDat) {
		this.giayChungNhanQuyenSuDungDat = giayChungNhanQuyenSuDungDat;
	}

	@ManyToOne
	public TepTin getTaiLieuDinhKem() {
		return taiLieuDinhKem;
	}

	public void setTaiLieuDinhKem(TepTin taiLieuDinhKem) {
		this.taiLieuDinhKem = taiLieuDinhKem;
	}

	public String getTenCongTy() {
		return tenCongTy;
	}

	public void setTenCongTy(String tenCongTy) {
		this.tenCongTy = tenCongTy;
	}

	public String getNguoiDaiDienPhapLy() {
		return nguoiDaiDienPhapLy;
	}

	public void setNguoiDaiDienPhapLy(String nguoiDaiDienPhapLy) {
		this.nguoiDaiDienPhapLy = nguoiDaiDienPhapLy;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public List<DonViDuAn> getDonViDuAn() {
		return donViDuAn;
	}

	public void setDonViDuAn(List<DonViDuAn> donViDuAn) {
		this.donViDuAn = donViDuAn;
	}

	public GiaiDoanXucTien getGiaiDoanXucTien() {
		return giaiDoanXucTien;
	}

	public void setGiaiDoanXucTien(GiaiDoanXucTien giaiDoanXucTien) {
		this.giaiDoanXucTien = giaiDoanXucTien;
	}

	public Date getNgayGui() {
		return ngayGui;
	}

	public void setNgayGui(Date ngayGui) {
		this.ngayGui = ngayGui;
	}

	public Date getNgayNhanPhanHoi() {
		return ngayNhanPhanHoi;
	}

	public void setNgayNhanPhanHoi(Date ngayNhanPhanHoi) {
		this.ngayNhanPhanHoi = ngayNhanPhanHoi;
	}

	@ManyToOne
	public TepTin getTaiLieuGD1() {
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(this.getGiaiDoanXucTien())) {
			if (taiLieuGD1 == null) {
				taiLieuGD1 = new TepTin();
			}
		}
		return taiLieuGD1;
	}

	public void setTaiLieuGD1(TepTin taiLieuGD1) {
		this.taiLieuGD1 = taiLieuGD1;
	}

	public Date getNgayKhaoSat() {
		return ngayKhaoSat;
	}

	public void setNgayKhaoSat(Date ngayKhaoSat) {
		this.ngayKhaoSat = ngayKhaoSat;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@ManyToOne
	public DonVi getDonViChuTri() {
		return donViChuTri;
	}

	public void setDonViChuTri(DonVi donViChuTri) {
		this.donViChuTri = donViChuTri;
	}

	@ManyToOne
	public DonVi getDonViTuVan() {
		return donViTuVan;
	}

	public void setDonViTuVan(DonVi donViTuVan) {
		this.donViTuVan = donViTuVan;
	}

	@ManyToOne
	public DonVi getDonViLapKeHoach() {
		return donViLapKeHoach;
	}

	public void setDonViLapKeHoach(DonVi donViLapKeHoach) {
		this.donViLapKeHoach = donViLapKeHoach;
	}

	@ManyToOne
	public DonVi getDonViThucHien() {
		return donViThucHien;
	}

	public void setDonViThucHien(DonVi donViThucHien) {
		this.donViThucHien = donViThucHien;
	}

	@ManyToOne
	public TepTin getTaiLieuGD2() {
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(this.giaiDoanXucTien)) {
			if (this.taiLieuGD2 == null) {
				this.taiLieuGD2 = new TepTin();
			}
		}
		return taiLieuGD2;
	}

	public void setTaiLieuGD2(TepTin taiLieuGD2) {
		this.taiLieuGD2 = taiLieuGD2;
	}

	public Date getNgayPhatHanhCVGD2() {
		return ngayPhatHanhCVGD2;
	}

	public void setNgayPhatHanhCVGD2(Date ngayPhatHanhCVGD2) {
		this.ngayPhatHanhCVGD2 = ngayPhatHanhCVGD2;
	}

	@ManyToOne
	public TepTin getCongVanGD2() {
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(this.giaiDoanXucTien)) {
			if (this.congVanGD2 == null) {
				this.congVanGD2 = new TepTin();
			}
		}
		return congVanGD2;
	}

	public void setCongVanGD2(TepTin congVanGD2) {
		this.congVanGD2 = congVanGD2;
	}

	public Date getNgayGuiUBND() {
		return ngayGuiUBND;
	}

	public void setNgayGuiUBND(Date ngayGuiUBND) {
		this.ngayGuiUBND = ngayGuiUBND;
	}

	public Date getNgayGuiSoXayDung() {
		return ngayGuiSoXayDung;
	}

	public void setNgayGuiSoXayDung(Date ngayGuiSoXayDung) {
		this.ngayGuiSoXayDung = ngayGuiSoXayDung;
	}

	public Date getNgayDuKienNhanPhanHoi() {
		return ngayDuKienNhanPhanHoi;
	}

	public void setNgayDuKienNhanPhanHoi(Date ngayDuKienNhanPhanHoi) {
		this.ngayDuKienNhanPhanHoi = ngayDuKienNhanPhanHoi;
	}

	@ManyToOne
	public TepTin getTaiLieuGD3() {
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(this.giaiDoanXucTien)) {
			if (this.taiLieuGD3 == null) {
				this.taiLieuGD3 = new TepTin();
			}
		}
		return taiLieuGD3;
	}

	public void setTaiLieuGD3(TepTin taiLieuGD3) {
		this.taiLieuGD3 = taiLieuGD3;
	}

	public Date getNgayPhatHanhCV3() {
		return ngayPhatHanhCV3;
	}

	public void setNgayPhatHanhCV3(Date ngayPhatHanhCV3) {
		this.ngayPhatHanhCV3 = ngayPhatHanhCV3;
	}

	@ManyToOne
	public TepTin getCongVanGD3() {
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(this.giaiDoanXucTien)) {
			if (this.congVanGD3 == null) {
				this.congVanGD3 = new TepTin();
			}
		}
		return congVanGD3;
	}

	public void setCongVanGD3(TepTin congVanGD3) {
		this.congVanGD3 = congVanGD3;
	}

	@ManyToOne
	public DuAn getDuAn() {
		return duAn;
	}

	public void setDuAn(DuAn duAn) {
		this.duAn = duAn;
	}

	public PhuongThucLuaChonNDT getPhuongThucLuaChonNDT() {
		return phuongThucLuaChonNDT;
	}

	public void setPhuongThucLuaChonNDT(PhuongThucLuaChonNDT phuongThucLuaChonNDT) {
		this.phuongThucLuaChonNDT = phuongThucLuaChonNDT;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetLQH() {
		if (this.phuongThucLuaChonNDT != null) {
			if (this.quyetDinhPheDuyetLQH == null) {
				this.quyetDinhPheDuyetLQH = new TepTin();
			}
		}
		return quyetDinhPheDuyetLQH;
	}

	public void setQuyetDinhPheDuyetLQH(TepTin quyetDinhPheDuyetLQH) {
		this.quyetDinhPheDuyetLQH = quyetDinhPheDuyetLQH;
	}

	@ManyToOne
	public TepTin getHoSoQuyHoachLQH() {
		if (this.phuongThucLuaChonNDT != null) {
			if (this.hoSoQuyHoachLQH == null) {
				this.hoSoQuyHoachLQH = new TepTin();
			}
		}
		return hoSoQuyHoachLQH;
	}

	public void setHoSoQuyHoachLQH(TepTin hoSoQuyHoachLQH) {
		this.hoSoQuyHoachLQH = hoSoQuyHoachLQH;
	}

	@ManyToOne
	public TepTin getPhuongAnDauGia() {
		if (this.phuongThucLuaChonNDT != null) {
			if (this.phuongAnDauGia == null) {
				if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(this.getPhuongThucLuaChonNDT())) {
					this.phuongAnDauGia = new TepTin();
				}
			}
		}
		return phuongAnDauGia;
	}

	public void setPhuongAnDauGia(TepTin phuongAnDauGia) {
		this.phuongAnDauGia = phuongAnDauGia;
	}

	public long getGiaDatKhoiDiemDauGia() {
		return giaDatKhoiDiemDauGia;
	}

	public void setGiaDatKhoiDiemDauGia(long giaDatKhoiDiemDauGia) {
		this.giaDatKhoiDiemDauGia = giaDatKhoiDiemDauGia;
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	@ManyToOne
	public TepTin getHoSoQuyHoach2000() {
		if (this.hoSoQuyHoach2000 == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				hoSoQuyHoach2000 = new TepTin();
			}
		}
		return hoSoQuyHoach2000;
	}

	public void setHoSoQuyHoach2000(TepTin hoSoQuyHoach2000) {
		this.hoSoQuyHoach2000 = hoSoQuyHoach2000;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyet2000() {
		if (this.quyetDinhPheDuyet2000 == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyet2000 = new TepTin();
			}
		}
		return quyetDinhPheDuyet2000;
	}

	public void setQuyetDinhPheDuyet2000(TepTin quyetDinhPheDuyet2000) {
		this.quyetDinhPheDuyet2000 = quyetDinhPheDuyet2000;
	}

	@ManyToOne
	public TepTin getNghiQuyetPheDuyetCongTrinh() {
		if (this.nghiQuyetPheDuyetCongTrinh == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				nghiQuyetPheDuyetCongTrinh = new TepTin();
			}
		}
		return nghiQuyetPheDuyetCongTrinh;
	}

	public void setNghiQuyetPheDuyetCongTrinh(TepTin nghiQuyetPheDuyetCongTrinh) {
		this.nghiQuyetPheDuyetCongTrinh = nghiQuyetPheDuyetCongTrinh;
	}

	@ManyToOne
	public TepTin getBaoCaoDoDacKhuDat() {
		if (this.baoCaoDoDacKhuDat == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				baoCaoDoDacKhuDat = new TepTin();
			}
		}
		return baoCaoDoDacKhuDat;
	}

	public void setBaoCaoDoDacKhuDat(TepTin baoCaoDoDacKhuDat) {
		this.baoCaoDoDacKhuDat = baoCaoDoDacKhuDat;
	}

	@ManyToOne
	public TepTin getPheDuyetKeHoachSuDungDat() {
		if (this.pheDuyetKeHoachSuDungDat == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				pheDuyetKeHoachSuDungDat = new TepTin();
			}
		}
		return pheDuyetKeHoachSuDungDat;
	}

	public void setPheDuyetKeHoachSuDungDat(TepTin pheDuyetKeHoachSuDungDat) {
		this.pheDuyetKeHoachSuDungDat = pheDuyetKeHoachSuDungDat;
	}

	@ManyToOne
	public TepTin getQuyetDinhThuHoiDat() {
		if (this.quyetDinhThuHoiDat == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhThuHoiDat = new TepTin();
			}
		}
		return quyetDinhThuHoiDat;
	}

	public void setQuyetDinhThuHoiDat(TepTin quyetDinhThuHoiDat) {
		this.quyetDinhThuHoiDat = quyetDinhThuHoiDat;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetDanhMuc() {
		if (this.quyetDinhPheDuyetDanhMuc == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyetDanhMuc = new TepTin();
			}
		}
		return quyetDinhPheDuyetDanhMuc;
	}

	public void setQuyetDinhPheDuyetDanhMuc(TepTin quyetDinhPheDuyetDanhMuc) {
		this.quyetDinhPheDuyetDanhMuc = quyetDinhPheDuyetDanhMuc;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetBoSungKinhPhi() {
		if (this.quyetDinhPheDuyetBoSungKinhPhi == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyetBoSungKinhPhi = new TepTin();
			}
		}
		return quyetDinhPheDuyetBoSungKinhPhi;
	}

	public void setQuyetDinhPheDuyetBoSungKinhPhi(TepTin quyetDinhPheDuyetBoSungKinhPhi) {
		this.quyetDinhPheDuyetBoSungKinhPhi = quyetDinhPheDuyetBoSungKinhPhi;
	}

	@ManyToOne
	public TepTin getPhuongAnTaiDinhCu() {
		if (this.phuongAnTaiDinhCu == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				phuongAnTaiDinhCu = new TepTin();
			}
		}
		return phuongAnTaiDinhCu;
	}

	public void setPhuongAnTaiDinhCu(TepTin phuongAnTaiDinhCu) {
		this.phuongAnTaiDinhCu = phuongAnTaiDinhCu;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetPADG() {
		if (this.quyetDinhPheDuyetPADG == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT)) {
				quyetDinhPheDuyetPADG = new TepTin();
			}
		}
		return quyetDinhPheDuyetPADG;
	}

	@Transient
	public boolean checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT phuongThuc) {
		if (this.getPhuongThucLuaChonNDT() != null) {
			if (phuongThuc.equals(this.getPhuongThucLuaChonNDT())) {
				return true;
			}
		}
		return false;
	}

	public void setQuyetDinhPheDuyetPADG(TepTin quyetDinhPheDuyetPADG) {
		this.quyetDinhPheDuyetPADG = quyetDinhPheDuyetPADG;
	}

	@ManyToOne
	public TepTin getQuyetDinhDauGiaQSDD() {
		if (this.quyetDinhDauGiaQSDD == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT)) {
				quyetDinhDauGiaQSDD = new TepTin();
			}
		}
		return quyetDinhDauGiaQSDD;
	}

	public void setQuyetDinhDauGiaQSDD(TepTin quyetDinhDauGiaQSDD) {
		this.quyetDinhDauGiaQSDD = quyetDinhDauGiaQSDD;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetGiaKhoiDiem() {
		if (this.quyetDinhPheDuyetGiaKhoiDiem == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT)) {
				quyetDinhPheDuyetGiaKhoiDiem = new TepTin();
			}
		}
		return quyetDinhPheDuyetGiaKhoiDiem;
	}

	public void setQuyetDinhPheDuyetGiaKhoiDiem(TepTin quyetDinhPheDuyetGiaKhoiDiem) {
		this.quyetDinhPheDuyetGiaKhoiDiem = quyetDinhPheDuyetGiaKhoiDiem;
	}

	@ManyToOne
	public TepTin getVanBanDeNghiBoSung() {
		if (this.vanBanDeNghiBoSung == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT)) {
				if (!this.option) {
					this.vanBanDeNghiBoSung = new TepTin();
				}
			}
		}
		return vanBanDeNghiBoSung;
	}

	public void setVanBanDeNghiBoSung(TepTin vanBanDeNghiBoSung) {
		this.vanBanDeNghiBoSung = vanBanDeNghiBoSung;
	}

	@ManyToOne
	public TepTin getQuyetDinhBoSungDanhMuc() {
		if (this.quyetDinhBoSungDanhMuc == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT)) {
				if (!this.option) {
					this.quyetDinhBoSungDanhMuc = new TepTin();
				}
			}
		}
		return quyetDinhBoSungDanhMuc;
	}

	public void setQuyetDinhBoSungDanhMuc(TepTin quyetDinhBoSungDanhMuc) {
		this.quyetDinhBoSungDanhMuc = quyetDinhBoSungDanhMuc;
	}

	@ManyToOne
	public TepTin getHoSoMoiTuyen() {
		if (this.hoSoMoiTuyen == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				hoSoMoiTuyen = new TepTin();
			}
		}
		return hoSoMoiTuyen;
	}

	public void setHoSoMoiTuyen(TepTin hoSoMoiTuyen) {
		this.hoSoMoiTuyen = hoSoMoiTuyen;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyeHoSoMoiTuyen() {
		if (this.quyetDinhPheDuyeHoSoMoiTuyen == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyeHoSoMoiTuyen = new TepTin();
			}
		}
		return quyetDinhPheDuyeHoSoMoiTuyen;
	}

	public void setQuyetDinhPheDuyeHoSoMoiTuyen(TepTin quyetDinhPheDuyeHoSoMoiTuyen) {
		this.quyetDinhPheDuyeHoSoMoiTuyen = quyetDinhPheDuyeHoSoMoiTuyen;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetKetQuaTrungTuyen() {
		if (this.quyetDinhPheDuyetKetQuaTrungTuyen == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyetKetQuaTrungTuyen = new TepTin();
			}
		}
		return quyetDinhPheDuyetKetQuaTrungTuyen;
	}

	public void setQuyetDinhPheDuyetKetQuaTrungTuyen(TepTin quyetDinhPheDuyetKetQuaTrungTuyen) {
		this.quyetDinhPheDuyetKetQuaTrungTuyen = quyetDinhPheDuyetKetQuaTrungTuyen;
	}

	@ManyToOne
	public TepTin getKeHoachLuaChonNhaDauTu() {
		if (this.keHoachLuaChonNhaDauTu == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				keHoachLuaChonNhaDauTu = new TepTin();
			}
		}
		return keHoachLuaChonNhaDauTu;
	}

	public void setKeHoachLuaChonNhaDauTu(TepTin keHoachLuaChonNhaDauTu) {
		this.keHoachLuaChonNhaDauTu = keHoachLuaChonNhaDauTu;
	}

	@ManyToOne
	public TepTin getHoSoMoiThau() {
		if (this.hoSoMoiThau == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				hoSoMoiThau = new TepTin();
			}
		}
		return hoSoMoiThau;
	}

	public void setHoSoMoiThau(TepTin hoSoMoiThau) {
		this.hoSoMoiThau = hoSoMoiThau;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetMoiThau() {
		if (this.quyetDinhPheDuyetMoiThau == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT)) {
				quyetDinhPheDuyetMoiThau = new TepTin();
			}
		}
		return quyetDinhPheDuyetMoiThau;
	}

	public void setQuyetDinhPheDuyetMoiThau(TepTin quyetDinhPheDuyetMoiThau) {
		this.quyetDinhPheDuyetMoiThau = quyetDinhPheDuyetMoiThau;
	}

	@ManyToOne
	public TepTin getVanBanChuyenMucDichSDD() {
		if (this.vanBanChuyenMucDichSDD == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG)) {
				vanBanChuyenMucDichSDD = new TepTin();
			}
		}
		return vanBanChuyenMucDichSDD;
	}

	public void setVanBanChuyenMucDichSDD(TepTin vanBanChuyenMucDichSDD) {
		this.vanBanChuyenMucDichSDD = vanBanChuyenMucDichSDD;
	}

	@ManyToOne
	public TepTin getVanBanDeNghiThuHoiDat() {
		if (this.vanBanDeNghiThuHoiDat == null) {
			if (checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG)) {
				vanBanDeNghiThuHoiDat = new TepTin();
			}
		}
		return vanBanDeNghiThuHoiDat;
	}

	public void setVanBanDeNghiThuHoiDat(TepTin vanBanDeNghiThuHoiDat) {
		this.vanBanDeNghiThuHoiDat = vanBanDeNghiThuHoiDat;
	}
	
	@Transient
	public List<DonVi> getListDonViTuVan() {
		List<DonVi> list = new ArrayList<DonVi>();
		JPAQuery<DonVi> q = find(DonVi.class).where(QDonVi.donVi.loaiDonVi.eq(LoaiDonVi.DON_VI_TU_VAN));
		q.orderBy(QDonVi.donVi.loaiDonVi.desc()).orderBy(QDonVi.donVi.ngayTao.desc());
		if (q != null) {
			list.addAll(q.fetch());
		}
		return list;
	}
	
	@Transient
	public List<DonVi> getListDonViChuTri() {
		List<DonVi> list = new ArrayList<DonVi>();
		JPAQuery<DonVi> q = find(DonVi.class).where(QDonVi.donVi.loaiDonVi.eq(LoaiDonVi.DON_VI_CHU_TRI));
		q.orderBy(QDonVi.donVi.loaiDonVi.desc()).orderBy(QDonVi.donVi.ngayTao.desc());
		if (q != null) {
			list.addAll(q.fetch());
		}
		return list;
	}
	
	@Transient
	public List<DonVi> getListDonViThucHien() {
		List<DonVi> list = new ArrayList<DonVi>();
		JPAQuery<DonVi> q = find(DonVi.class).where(QDonVi.donVi.loaiDonVi.eq(LoaiDonVi.DON_VI_THUC_HIEN));
		q.orderBy(QDonVi.donVi.loaiDonVi.desc()).orderBy(QDonVi.donVi.ngayTao.desc());
		if (q != null) {
			list.addAll(q.fetch());
		}
		return list;
	}
}
