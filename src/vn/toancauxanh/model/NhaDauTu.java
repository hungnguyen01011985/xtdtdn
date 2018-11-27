package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

@Entity
@Table(name="nhadautu")
public class NhaDauTu extends Model<NhaDauTu>{
	private String tenNhaDauTu;
	private String nguoiDaiDienPhapLy;
	private String diaChi;
	private String soDienThoai;
	private String email;

	public String getTenNhaDauTu() {
		return tenNhaDauTu;
	}

	public void setTenNhaDauTu(String tenNhaDauTu) {
		this.tenNhaDauTu = tenNhaDauTu;
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

	@Command
	public void saveNhaDauTu(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn){
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
}
