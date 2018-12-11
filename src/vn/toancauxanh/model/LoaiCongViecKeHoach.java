package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

@Entity
@Table(name = "loaicongviec")
public class LoaiCongViecKeHoach extends Model<LoaiCongViecKeHoach> {
	private String ten = "";
	private String moTa = "";

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	@Command
	public void saveLoaiCongViec(@BindingParam("wdn") Window wdn, @BindingParam("list") DoanVao doanVao,
			@BindingParam("attr") String attr) {
		this.save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, doanVao, attr);
	}

}
