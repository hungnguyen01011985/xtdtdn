package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.annotation.Command;

import vn.toancauxanh.gg.model.enums.TrangThaiCongViecEnum;

@Entity
@Table(name = "kehoachlamviec")
public class KeHoachLamViec extends Model<KeHoachLamViec> {
	private TrangThaiCongViecEnum trangThaiCongViec;
	private String ghiChu = "";
	private Date thoiGian;
	private NhanVien nguoiThucHien;
	private DoanVao doanVao;
	private boolean flag;
	private String tenCongViec = "";
	private List<KeHoachLamViec> selectItems;
	private List<KeHoachLamViec> listCongViecTemp;

	@Transient
	public List<KeHoachLamViec> getListCongViecTemp() {
		if (listCongViecTemp == null) {
			listCongViecTemp = new ArrayList<KeHoachLamViec>();
			listCongViecTemp.add(new KeHoachLamViec(null, null, null, null, null, false, "Nhân sự làm việc"));
			listCongViecTemp.add(new KeHoachLamViec(null, null, null, null, null, true, "Chuyên viên"));
			listCongViecTemp
					.add(new KeHoachLamViec(null, null, null, null, null, true, "Lãnh đạo, người được phân công"));
			listCongViecTemp.add(new KeHoachLamViec(null, null, null, null, null, false, "Công tác hậu cần"));
			listCongViecTemp
					.add(new KeHoachLamViec(null, null, null, null, null, true, "Chuẩn bị phòng họp (nếu cần)"));
		}
		return listCongViecTemp;
	}

	public void setListCongViecTemp(List<KeHoachLamViec> listCongViecTemp) {
		this.listCongViecTemp = listCongViecTemp;
	}

	public KeHoachLamViec() {

	}

	public KeHoachLamViec(TrangThaiCongViecEnum trangThaiCongViec, String ghiChu, Date thoiGian, NhanVien nguoiThucHien,
			DoanVao doanVao, boolean flag, String tenCongViec) {
		this.trangThaiCongViec = trangThaiCongViec;
		this.ghiChu = ghiChu;
		this.thoiGian = thoiGian;
		this.nguoiThucHien = nguoiThucHien;
		this.flag = flag;
		this.tenCongViec = tenCongViec;
	}

	@Enumerated(EnumType.STRING)
	public TrangThaiCongViecEnum getTrangThaiCongViec() {
		return trangThaiCongViec;
	}

	public void setTrangThaiCongViec(TrangThaiCongViecEnum trangThaiCongViec) {
		this.trangThaiCongViec = trangThaiCongViec;
	}

	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
	}

	@ManyToOne
	public NhanVien getNguoiThucHien() {
		return nguoiThucHien;
	}

	public void setNguoiThucHien(NhanVien nguoiThucHien) {
		this.nguoiThucHien = nguoiThucHien;
	}

	public Date getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	@Transient
	public List<KeHoachLamViec> getSelectItems() {
		return selectItems;
	}

	public void setSelectItems(List<KeHoachLamViec> selectItems) {
		this.selectItems = selectItems;
	}

	@Transient
	public List<TrangThaiCongViecEnum> getListTrangThaiCongViec() {
		List<TrangThaiCongViecEnum> list = new ArrayList<TrangThaiCongViecEnum>();
		list.add(TrangThaiCongViecEnum.CHUA_THUC_HIEN);
		list.add(TrangThaiCongViecEnum.DANG_THUC_HIEN);
		list.add(TrangThaiCongViecEnum.DA_THUC_HIEN);
		return list;
	}

	public List<KeHoachLamViec> editKehoachLamViec(List<KeHoachLamViec> list) {
		if (doanVao != null) {
			List<KeHoachLamViec> listData = find(KeHoachLamViec.class)
					.where(QKeHoachLamViec.keHoachLamViec.doanVao.id.eq(doanVao.getId())).fetch();
			for (int i = 0; i < listData.size(); i++) {
				list.get(i).setNguoiThucHien(listData.get(i).getNguoiThucHien());
				list.get(i).setThoiGian(listData.get(i).getThoiGian());
				list.get(i).setTrangThaiCongViec(listData.get(i).getTrangThaiCongViec());
				list.get(i).setGhiChu(listData.get(i).getGhiChu());
			}
		}
		return list;
	}

	// doanvao_id,...
	@Command
	public void saveKeHoachLamViec() {
		for (KeHoachLamViec keHoachLamViec : listCongViecTemp) {
			keHoachLamViec.saveNotShowNotification();
		}
		showNotification("Lưu thành công!", "", "success");
	}
}
