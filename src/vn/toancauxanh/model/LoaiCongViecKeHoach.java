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
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Command
	public void saveLoaiCongViec(@BindingParam("wdn") Window wdn, @BindingParam("list") DoanVao doanVao, @BindingParam("attr") String attr){
		this.save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, doanVao, attr);
	}

}
