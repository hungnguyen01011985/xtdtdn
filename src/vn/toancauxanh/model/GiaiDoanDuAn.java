package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;

@Entity
@Table(name="giaidoanduan")
public class GiaiDoanDuAn extends Model<GiaiDoanDuAn>{
	private DuAn duAn;
	private GiaiDoanXucTien giaiDoanXucTien;
	//Thông tin giai đoạn 1
	private Date ngayGui;
	private Date ngayNhanPhanHoi;
	private TepTin taiLieuGD1;
	//Thông tin giai đoạn 2
	private Date ngayKhaoSat;
	private String ghiChu;
	private TepTin taiLieuGD2;
	private Date ngayPhatHanhCVGD2;
	private TepTin congVanGD2;
	//Thông tin giai đoạn 3
	private Date ngayGuiUBND;
	private Date ngayGuiSoXayDung;
	private Date ngayDuKienNhanPhanHoi;
	private TepTin taiLieuGD3;
	private Date ngayPhatHanhCV3;
	private TepTin congVanGD3;
	//Thông tin giai đoạn 4
	private PhuongThucLuaChonNDT phuongThucLuaChonNDT;
	private String donViChuTri;
	private boolean option = true;
	//Lập kế hoạch chi tiết 1/500
	private String donViTuVan;
	private TepTin hoSoQuyHoachLKH;
	private TepTin quyetDinhPheDuyet;
	//Xây dựng phương án đấu giá quyền sử dụng đất
	private String donViTuVanXDPA;
	private Date ngayGuiLayGopY;
	private Date ngayDuKienNhanCongVanXDPA;
	private TepTin phuongAnDauGia;
	//Quyết định đấu giá quyền sử dụng đất
	private Date ngayGuiQDDG;
	private Date ngayDuKienNhanCongVanQDDG;
	private TepTin quyetDinhQDDG;
	//Quyết định phê duyệt giá đất khởi điểm đấu giá
	private Date ngayGuiQDPD;
	private Date ngayNhanQDPD;
	private TepTin quyetDinhQDPD;
	//Gửi công văn đề nghị bổ sung địa điểm thực hiện dự án
	private Date ngayGuiCongVanDNBS;
	private Date ngayDuKienNhanCongVanDNBS;
	private TepTin phuongAnDauGiaBNBS;
	private TepTin quyetDinhBoSungDanhMucDNBS;
	//Nghị quyết phê duyệt danh mục dụ án cần thu hồi đất
	private TepTin nghiQuyetPheDuyet;
	private TepTin vanBanDinhkemNQPD;
	//Lập dự toán
	private Double duToanDoDacKhuDat;
	private Double duToanGiaiPhongMatBang;
	private TepTin vanBanDinhKem;
	private TepTin congTacDoDacLDT;
	private TepTin giaiPhongMatBangLDT;
	private TepTin quyetDinhPheDuyetLDT;
	private TepTin keHoachSuDungDatLDT;
	//Trình phê duyệt danh mục
	private Date ngayGuiTPDDM;
	private Date ngayDuKienNhanCongVanTPDDM;
	//Trình phê duyệt bổ sung kinh phí
	private Date ngayGuiTPDKP;
	private Date ngayDuKienNhanCongVanTPDKP;
	//Phương án bồi thường giải phóng mặt bằng
	private Date ngayGuiGPMB;
	private Date ngayDuKienNhanCongVanGPMB;
	//Quyết định phê duyệt giá đất khởi điểm
	private Double giaKhoiDiem;
	private Date ngayGuiGDKD;
	private Double tongMucDauTuDuAn;
	private Date ngayGuiSoKeHoachVaDauTu;
	private List<DonViDuAn> donViDuAn = new ArrayList<DonViDuAn>();

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

	public PhuongThucLuaChonNDT getPhuongThucLuaChonNDT() {
		return phuongThucLuaChonNDT;
	}

	public void setPhuongThucLuaChonNDT(PhuongThucLuaChonNDT phuongThucLuaChonNDT) {
		this.phuongThucLuaChonNDT = phuongThucLuaChonNDT;
	}

	public String getDonViChuTri() {
		return donViChuTri;
	}

	public void setDonViChuTri(String donViChuTri) {
		this.donViChuTri = donViChuTri;
	}

	public String getDonViTuVan() {
		return donViTuVan;
	}

	public void setDonViTuVan(String donViTuVan) {
		this.donViTuVan = donViTuVan;
	}
	
	@ManyToOne
	public TepTin getQuyetDinhPheDuyet() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.quyetDinhPheDuyet == null) {
				this.quyetDinhPheDuyet = new TepTin();
			}
		}
		return quyetDinhPheDuyet;
	}

	public void setQuyetDinhPheDuyet(TepTin quyetDinhPheDuyet) {
		this.quyetDinhPheDuyet = quyetDinhPheDuyet;
	}

	@ManyToOne
	public TepTin getHoSoQuyHoachLKH() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.hoSoQuyHoachLKH == null) {
				this.hoSoQuyHoachLKH = new TepTin();
			}
		}
		return hoSoQuyHoachLKH;
	}

	public void setHoSoQuyHoachLKH(TepTin hoSoQuyHoachLKH) {
		this.hoSoQuyHoachLKH = hoSoQuyHoachLKH;
	}

	public String getDonViTuVanXDPA() {
		return donViTuVanXDPA;
	}

	public void setDonViTuVanXDPA(String donViTuVanXDPA) {
		this.donViTuVanXDPA = donViTuVanXDPA;
	}

	public Date getNgayGuiLayGopY() {
		return ngayGuiLayGopY;
	}

	public void setNgayGuiLayGopY(Date ngayGuiLayGopY) {
		this.ngayGuiLayGopY = ngayGuiLayGopY;
	}

	public Date getNgayDuKienNhanCongVanXDPA() {
		return ngayDuKienNhanCongVanXDPA;
	}

	public void setNgayDuKienNhanCongVanXDPA(Date ngayDuKienNhanCongVanXDPA) {
		this.ngayDuKienNhanCongVanXDPA = ngayDuKienNhanCongVanXDPA;
	}
	
	@ManyToOne
	public TepTin getPhuongAnDauGia() {
		return phuongAnDauGia;
	}

	public void setPhuongAnDauGia(TepTin phuongAnDauGia) {
		this.phuongAnDauGia = phuongAnDauGia;
	}

	public Date getNgayGuiQDDG() {
		return ngayGuiQDDG;
	}

	public void setNgayGuiQDDG(Date ngayGuiQDDG) {
		this.ngayGuiQDDG = ngayGuiQDDG;
	}

	public Date getNgayDuKienNhanCongVanQDDG() {
		return ngayDuKienNhanCongVanQDDG;
	}

	public void setNgayDuKienNhanCongVanQDDG(Date ngayDuKienNhanCongVanQDDG) {
		this.ngayDuKienNhanCongVanQDDG = ngayDuKienNhanCongVanQDDG;
	}
	
	@ManyToOne
	public TepTin getQuyetDinhQDDG() {
		return quyetDinhQDDG;
	}

	public void setQuyetDinhQDDG(TepTin quyetDinhQDDG) {
		this.quyetDinhQDDG = quyetDinhQDDG;
	}

	public Date getNgayGuiQDPD() {
		return ngayGuiQDPD;
	}

	public void setNgayGuiQDPD(Date ngayGuiQDPD) {
		this.ngayGuiQDPD = ngayGuiQDPD;
	}

	public Date getNgayNhanQDPD() {
		return ngayNhanQDPD;
	}

	public void setNgayNhanQDPD(Date ngayNhanQDPD) {
		this.ngayNhanQDPD = ngayNhanQDPD;
	}
	
	@ManyToOne
	public TepTin getQuyetDinhQDPD() {
		return quyetDinhQDPD;
	}

	public void setQuyetDinhQDPD(TepTin quyetDinhQDPD) {
		this.quyetDinhQDPD = quyetDinhQDPD;
	}

	public Date getNgayGuiCongVanDNBS() {
		return ngayGuiCongVanDNBS;
	}

	public void setNgayGuiCongVanDNBS(Date ngayGuiCongVanDNBS) {
		this.ngayGuiCongVanDNBS = ngayGuiCongVanDNBS;
	}

	public Date getNgayDuKienNhanCongVanDNBS() {
		return ngayDuKienNhanCongVanDNBS;
	}

	public void setNgayDuKienNhanCongVanDNBS(Date ngayDuKienNhanCongVanDNBS) {
		this.ngayDuKienNhanCongVanDNBS = ngayDuKienNhanCongVanDNBS;
	}
	
	@ManyToOne
	public TepTin getPhuongAnDauGiaBNBS() {
		return phuongAnDauGiaBNBS;
	}

	public void setPhuongAnDauGiaBNBS(TepTin phuongAnDauGiaBNBS) {
		this.phuongAnDauGiaBNBS = phuongAnDauGiaBNBS;
	}
	
	@ManyToOne
	public TepTin getQuyetDinhBoSungDanhMucDNBS() {
		return quyetDinhBoSungDanhMucDNBS;
	}

	public void setQuyetDinhBoSungDanhMucDNBS(TepTin quyetDinhBoSungDanhMucDNBS) {
		this.quyetDinhBoSungDanhMucDNBS = quyetDinhBoSungDanhMucDNBS;
	}
	
	@ManyToOne
	public TepTin getNghiQuyetPheDuyet() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.nghiQuyetPheDuyet == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.nghiQuyetPheDuyet = new TepTin();
				}
			}
		}
		return nghiQuyetPheDuyet;
	}

	public void setNghiQuyetPheDuyet(TepTin nghiQuyetPheDuyet) {
		this.nghiQuyetPheDuyet = nghiQuyetPheDuyet;
	}
	
	@ManyToOne
	public TepTin getVanBanDinhkemNQPD() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.vanBanDinhkemNQPD == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.vanBanDinhkemNQPD = new TepTin();
				}
			}
		}
		return vanBanDinhkemNQPD;
	}

	public void setVanBanDinhkemNQPD(TepTin vanBanDinhkemNQPD) {
		this.vanBanDinhkemNQPD = vanBanDinhkemNQPD;
	}

	public Double getDuToanDoDacKhuDat() {
		return duToanDoDacKhuDat;
	}

	public void setDuToanDoDacKhuDat(Double duToanDoDacKhuDat) {
		this.duToanDoDacKhuDat = duToanDoDacKhuDat;
	}

	public Double getDuToanGiaiPhongMatBang() {
		return duToanGiaiPhongMatBang;
	}

	public void setDuToanGiaiPhongMatBang(Double duToanGiaiPhongMatBang) {
		this.duToanGiaiPhongMatBang = duToanGiaiPhongMatBang;
	}

	public Date getNgayGuiTPDDM() {
		return ngayGuiTPDDM;
	}

	public void setNgayGuiTPDDM(Date ngayGuiTPDDM) {
		this.ngayGuiTPDDM = ngayGuiTPDDM;
	}

	public Date getNgayDuKienNhanCongVanTPDDM() {
		return ngayDuKienNhanCongVanTPDDM;
	}

	public void setNgayDuKienNhanCongVanTPDDM(Date ngayDuKienNhanCongVanTPDDM) {
		this.ngayDuKienNhanCongVanTPDDM = ngayDuKienNhanCongVanTPDDM;
	}

	public Date getNgayGuiTPDKP() {
		return ngayGuiTPDKP;
	}

	public void setNgayGuiTPDKP(Date ngayGuiTPDKP) {
		this.ngayGuiTPDKP = ngayGuiTPDKP;
	}

	public Date getNgayDuKienNhanCongVanTPDKP() {
		return ngayDuKienNhanCongVanTPDKP;
	}

	public void setNgayDuKienNhanCongVanTPDKP(Date ngayDuKienNhanCongVanTPDKP) {
		this.ngayDuKienNhanCongVanTPDKP = ngayDuKienNhanCongVanTPDKP;
	}

	public Date getNgayGuiGPMB() {
		return ngayGuiGPMB;
	}

	public void setNgayGuiGPMB(Date ngayGuiGPMB) {
		this.ngayGuiGPMB = ngayGuiGPMB;
	}

	public Date getNgayDuKienNhanCongVanGPMB() {
		return ngayDuKienNhanCongVanGPMB;
	}

	public void setNgayDuKienNhanCongVanGPMB(Date ngayDuKienNhanCongVanGPMB) {
		this.ngayDuKienNhanCongVanGPMB = ngayDuKienNhanCongVanGPMB;
	}

	public Double getGiaKhoiDiem() {
		return giaKhoiDiem;
	}

	public void setGiaKhoiDiem(Double giaKhoiDiem) {
		this.giaKhoiDiem = giaKhoiDiem;
	}

	public Date getNgayGuiGDKD() {
		return ngayGuiGDKD;
	}

	public void setNgayGuiGDKD(Date ngayGuiGDKD) {
		this.ngayGuiGDKD = ngayGuiGDKD;
	}

	public Double getTongMucDauTuDuAn() {
		return tongMucDauTuDuAn;
	}

	public void setTongMucDauTuDuAn(Double tongMucDauTuDuAn) {
		this.tongMucDauTuDuAn = tongMucDauTuDuAn;
	}

	public Date getNgayGuiSoKeHoachVaDauTu() {
		return ngayGuiSoKeHoachVaDauTu;
	}

	public void setNgayGuiSoKeHoachVaDauTu(Date ngayGuiSoKeHoachVaDauTu) {
		this.ngayGuiSoKeHoachVaDauTu = ngayGuiSoKeHoachVaDauTu;
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}
	
	@ManyToOne
	public TepTin getVanBanDinhKem() {
		return vanBanDinhKem;
	}

	public void setVanBanDinhKem(TepTin vanBanDinhKem) {
		this.vanBanDinhKem = vanBanDinhKem;
	}

	@ManyToOne
	public TepTin getCongTacDoDacLDT() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.congTacDoDacLDT == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.congTacDoDacLDT = new TepTin();
				}
			}
		}
		return congTacDoDacLDT;
	}

	public void setCongTacDoDacLDT(TepTin congTacDoDacLDT) {
		this.congTacDoDacLDT = congTacDoDacLDT;
	}
	
	@ManyToOne
	public TepTin getGiaiPhongMatBangLDT() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.giaiPhongMatBangLDT == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.giaiPhongMatBangLDT = new TepTin();
				}
			}
		}
		return giaiPhongMatBangLDT;
	}

	public void setGiaiPhongMatBangLDT(TepTin giaiPhongMatBangLDT) {
		this.giaiPhongMatBangLDT = giaiPhongMatBangLDT;
	}

	@ManyToOne
	public TepTin getQuyetDinhPheDuyetLDT() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.quyetDinhPheDuyetLDT == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.quyetDinhPheDuyetLDT = new TepTin();
				}
			}
		}
		return quyetDinhPheDuyetLDT;
	}

	public void setQuyetDinhPheDuyetLDT(TepTin quyetDinhPheDuyetLDT) {
		this.quyetDinhPheDuyetLDT = quyetDinhPheDuyetLDT;
	}
	
	@ManyToOne
	public TepTin getKeHoachSuDungDatLDT() {
		if(this.phuongThucLuaChonNDT != null) {
			if(this.keHoachSuDungDatLDT == null) {
				if(this.getPhuongThucLuaChonNDT().ordinal() == 1) {
					this.keHoachSuDungDatLDT = new TepTin();
				}
			}
		}
		return keHoachSuDungDatLDT;
	}

	public void setKeHoachSuDungDatLDT(TepTin keHoachSuDungDatLDT) {
		this.keHoachSuDungDatLDT = keHoachSuDungDatLDT;
	}
	
	
	
}
