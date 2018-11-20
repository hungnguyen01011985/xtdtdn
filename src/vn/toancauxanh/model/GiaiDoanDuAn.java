package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import javax.persistence.JoinColumn;

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
	@Lob
	private String ghiChu;
	private TepTin taiLieuGD2;
	private Date ngayPhatHanhCVGD2;
	private TepTin congVanGD2;
	// Thông tin giai đoạn 3
	private Date ngayGuiUBND;
	private Date ngayDuKienNhanPhanHoi;
	private Date ngayPhatHanhCV3;
	private TepTin taiLieuGD3;
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
	// Hồ sơ khu đất
	private List<HoSoKhuDat> hoSoKhuDats = new ArrayList<HoSoKhuDat>();
	private List<HoSoKhuDat> listXoaHoSoKhuDat = new ArrayList<HoSoKhuDat>();
	private TepTin quyetDinhDauGiaQSDD;
	// Quyết định phê duyệt giá đất khởi điểm đấu giá
	private Double giaDatKhoiDiemDauGia = 0.0;
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
	private Date ngayThongBaoOld;
	private boolean kiemTraThongBao = true;
	private List<TepTin> tepTins = new ArrayList<TepTin>();
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
	
	@Transient
	public List<HoSoKhuDat> getHoSoKhuDats() {
		return hoSoKhuDats;
	}

	public void setHoSoKhuDats(List<HoSoKhuDat> hoSoKhuDats) {
		this.hoSoKhuDats = hoSoKhuDats;
	}

	@Enumerated(EnumType.STRING)
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

	public Date getNgayDuKienNhanPhanHoi() {
		return ngayDuKienNhanPhanHoi;
	}

	public void setNgayDuKienNhanPhanHoi(Date ngayDuKienNhanPhanHoi) {
		this.ngayDuKienNhanPhanHoi = ngayDuKienNhanPhanHoi;
	}

	@ManyToOne
	public TepTin getTaiLieuGD3() {
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

	@Enumerated(EnumType.STRING)
	public PhuongThucLuaChonNDT getPhuongThucLuaChonNDT() {
		return phuongThucLuaChonNDT;
	}

	public void setPhuongThucLuaChonNDT(PhuongThucLuaChonNDT phuongThucLuaChonNDT) {
		this.phuongThucLuaChonNDT = phuongThucLuaChonNDT;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetLQH() {
		return quyetDinhPheDuyetLQH;
	}

	public void setQuyetDinhPheDuyetLQH(TepTin quyetDinhPheDuyetLQH) {
		this.quyetDinhPheDuyetLQH = quyetDinhPheDuyetLQH;
	}

	@ManyToOne
	public TepTin getHoSoQuyHoachLQH() {
		return hoSoQuyHoachLQH;
	}

	public void setHoSoQuyHoachLQH(TepTin hoSoQuyHoachLQH) {
		this.hoSoQuyHoachLQH = hoSoQuyHoachLQH;
	}

	@ManyToOne
	public TepTin getPhuongAnDauGia() {
		return phuongAnDauGia;
	}
	
	public void setPhuongAnDauGia(TepTin phuongAnDauGia) {
		this.phuongAnDauGia = phuongAnDauGia;
	}
	
	@ManyToOne
	public TepTin getQuyetDinhDauGiaQSDD() {
		return quyetDinhDauGiaQSDD;
	}

	public void setQuyetDinhDauGiaQSDD(TepTin quyetDinhDauGiaQSDD) {
		this.quyetDinhDauGiaQSDD = quyetDinhDauGiaQSDD;
	}

	public Double getGiaDatKhoiDiemDauGia() {
		return giaDatKhoiDiemDauGia;
	}

	public void setGiaDatKhoiDiemDauGia(Double giaDatKhoiDiemDauGia) {
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
		return hoSoQuyHoach2000;
	}

	public void setHoSoQuyHoach2000(TepTin hoSoQuyHoach2000) {
		this.hoSoQuyHoach2000 = hoSoQuyHoach2000;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyet2000() {
		return quyetDinhPheDuyet2000;
	}

	public void setQuyetDinhPheDuyet2000(TepTin quyetDinhPheDuyet2000) {
		this.quyetDinhPheDuyet2000 = quyetDinhPheDuyet2000;
	}

	@ManyToOne
	public TepTin getNghiQuyetPheDuyetCongTrinh() {
		return nghiQuyetPheDuyetCongTrinh;
	}

	public void setNghiQuyetPheDuyetCongTrinh(TepTin nghiQuyetPheDuyetCongTrinh) {
		this.nghiQuyetPheDuyetCongTrinh = nghiQuyetPheDuyetCongTrinh;
	}

	@ManyToOne
	public TepTin getBaoCaoDoDacKhuDat() {
		return baoCaoDoDacKhuDat;
	}

	public void setBaoCaoDoDacKhuDat(TepTin baoCaoDoDacKhuDat) {
		this.baoCaoDoDacKhuDat = baoCaoDoDacKhuDat;
	}

	@ManyToOne
	public TepTin getPheDuyetKeHoachSuDungDat() {
		return pheDuyetKeHoachSuDungDat;
	}

	public void setPheDuyetKeHoachSuDungDat(TepTin pheDuyetKeHoachSuDungDat) {
		this.pheDuyetKeHoachSuDungDat = pheDuyetKeHoachSuDungDat;
	}

	@ManyToOne
	public TepTin getQuyetDinhThuHoiDat() {
		return quyetDinhThuHoiDat;
	}

	public void setQuyetDinhThuHoiDat(TepTin quyetDinhThuHoiDat) {
		this.quyetDinhThuHoiDat = quyetDinhThuHoiDat;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetDanhMuc() {
		return quyetDinhPheDuyetDanhMuc;
	}

	public void setQuyetDinhPheDuyetDanhMuc(TepTin quyetDinhPheDuyetDanhMuc) {
		this.quyetDinhPheDuyetDanhMuc = quyetDinhPheDuyetDanhMuc;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetBoSungKinhPhi() {
		return quyetDinhPheDuyetBoSungKinhPhi;
	}

	public void setQuyetDinhPheDuyetBoSungKinhPhi(TepTin quyetDinhPheDuyetBoSungKinhPhi) {
		this.quyetDinhPheDuyetBoSungKinhPhi = quyetDinhPheDuyetBoSungKinhPhi;
	}

	@ManyToOne
	public TepTin getPhuongAnTaiDinhCu() {
		return phuongAnTaiDinhCu;
	}

	public void setPhuongAnTaiDinhCu(TepTin phuongAnTaiDinhCu) {
		this.phuongAnTaiDinhCu = phuongAnTaiDinhCu;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetPADG() {
		return quyetDinhPheDuyetPADG;
	}

	@Transient
	public boolean checkTaiLieuByPhuongThuc(PhuongThucLuaChonNDT phuongThuc) {
		return false;
	}

	public void setQuyetDinhPheDuyetPADG(TepTin quyetDinhPheDuyetPADG) {
		this.quyetDinhPheDuyetPADG = quyetDinhPheDuyetPADG;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetGiaKhoiDiem() {
		return quyetDinhPheDuyetGiaKhoiDiem;
	}

	public void setQuyetDinhPheDuyetGiaKhoiDiem(TepTin quyetDinhPheDuyetGiaKhoiDiem) {
		this.quyetDinhPheDuyetGiaKhoiDiem = quyetDinhPheDuyetGiaKhoiDiem;
	}

	@ManyToOne
	public TepTin getVanBanDeNghiBoSung() {
		return vanBanDeNghiBoSung;
	}

	public void setVanBanDeNghiBoSung(TepTin vanBanDeNghiBoSung) {
		this.vanBanDeNghiBoSung = vanBanDeNghiBoSung;
	}

	@ManyToOne
	public TepTin getQuyetDinhBoSungDanhMuc() {
		return quyetDinhBoSungDanhMuc;
	}

	public void setQuyetDinhBoSungDanhMuc(TepTin quyetDinhBoSungDanhMuc) {
		this.quyetDinhBoSungDanhMuc = quyetDinhBoSungDanhMuc;
	}

	@ManyToOne
	public TepTin getHoSoMoiTuyen() {
		return hoSoMoiTuyen;
	}

	public void setHoSoMoiTuyen(TepTin hoSoMoiTuyen) {
		this.hoSoMoiTuyen = hoSoMoiTuyen;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyeHoSoMoiTuyen() {
		return quyetDinhPheDuyeHoSoMoiTuyen;
	}

	public void setQuyetDinhPheDuyeHoSoMoiTuyen(TepTin quyetDinhPheDuyeHoSoMoiTuyen) {
		this.quyetDinhPheDuyeHoSoMoiTuyen = quyetDinhPheDuyeHoSoMoiTuyen;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetKetQuaTrungTuyen() {
		return quyetDinhPheDuyetKetQuaTrungTuyen;
	}

	public void setQuyetDinhPheDuyetKetQuaTrungTuyen(TepTin quyetDinhPheDuyetKetQuaTrungTuyen) {
		this.quyetDinhPheDuyetKetQuaTrungTuyen = quyetDinhPheDuyetKetQuaTrungTuyen;
	}

	@ManyToOne
	public TepTin getKeHoachLuaChonNhaDauTu() {
		return keHoachLuaChonNhaDauTu;
	}

	public void setKeHoachLuaChonNhaDauTu(TepTin keHoachLuaChonNhaDauTu) {
		this.keHoachLuaChonNhaDauTu = keHoachLuaChonNhaDauTu;
	}

	@ManyToOne
	public TepTin getHoSoMoiThau() {
		return hoSoMoiThau;
	}

	public void setHoSoMoiThau(TepTin hoSoMoiThau) {
		this.hoSoMoiThau = hoSoMoiThau;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetMoiThau() {
		return quyetDinhPheDuyetMoiThau;
	}

	public void setQuyetDinhPheDuyetMoiThau(TepTin quyetDinhPheDuyetMoiThau) {
		this.quyetDinhPheDuyetMoiThau = quyetDinhPheDuyetMoiThau;
	}

	@ManyToOne
	public TepTin getVanBanChuyenMucDichSDD() {
		return vanBanChuyenMucDichSDD;
	}

	public void setVanBanChuyenMucDichSDD(TepTin vanBanChuyenMucDichSDD) {
		this.vanBanChuyenMucDichSDD = vanBanChuyenMucDichSDD;
	}

	@ManyToOne
	public TepTin getVanBanDeNghiThuHoiDat() {
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
	
	@Transient
	public List<HoSoKhuDat> getListXoaHoSoKhuDat() {
		return listXoaHoSoKhuDat;
	}

	public void setListXoaHoSoKhuDat(List<HoSoKhuDat> listXoaHoSoKhuDat) {
		this.listXoaHoSoKhuDat = listXoaHoSoKhuDat;
	}

	public Date getNgayThongBaoOld() {
		return ngayThongBaoOld;
	}

	public void setNgayThongBaoOld(Date ngayThongBaoOld) {
		this.ngayThongBaoOld = ngayThongBaoOld;
	}

	public boolean isKiemTraThongBao() {
		return kiemTraThongBao;
	}

	public void setKiemTraThongBao(boolean kiemTraThongBao) {
		this.kiemTraThongBao = kiemTraThongBao;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "giaidoanduan_teptin", joinColumns = {
			@JoinColumn(name = "giaidoanduan_id") }, inverseJoinColumns = { @JoinColumn(name = "teptin_id") })
	public List<TepTin> getTepTins() {
		return tepTins;
	}

	public void setTepTins(List<TepTin> tepTins) {
		this.tepTins = tepTins;
	}
	
	@Transient
	public AbstractValidator getValidatorEmail() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (param == null || "".equals(param) || param.isEmpty()) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				} else if (!value.trim().matches(".+@.+\\.[a-z]+")) {
					addInvalidMessage(ctx, "Email không đúng định dạng");
				} else {
					JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
							.where(QGiaiDoanDuAn.giaiDoanDuAn.email.eq(value));
					if (!GiaiDoanDuAn.this.noId()) {
						q.where(QGiaiDoanDuAn.giaiDoanDuAn.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Email đã được sử dụng");
					}
				}
			}
		};
	}
	
	@Transient
	public AbstractValidator getValidateSoDienThoai() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (!value.isEmpty() && !value.trim()
						.matches("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$")) {
					addInvalidMessage(ctx, "Số điện thoại không đúng định dạng.");
				}
			}
		};
	}
}
