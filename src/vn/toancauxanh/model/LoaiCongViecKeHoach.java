package vn.toancauxanh.model;


import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

@Entity
@Table(name = "loaicongviec")
public class LoaiCongViecKeHoach extends Model<LoaiCongViecKeHoach> {
	
	private String ten = "";
	@Lob
	private String moTa = "";

	public String getTen() {
		return ten;
	}
	
	public void setTen(String ten) {
		this.ten = ten;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getMoTa() {
		return moTa;
	}
	
	@Command
	public void saveLoaiCongViecKeHoach(@BindingParam("wdn") Window wdn, @BindingParam("list") Object list,
			@BindingParam("attr") String attr) {
		this.save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, list, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenLoaiCongViecKeHoach() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenLoaiCongViecKeHoach = (String) ctx.getProperty().getValue();
				String param = tenLoaiCongViecKeHoach.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<LoaiCongViecKeHoach> q = find(LoaiCongViecKeHoach.class).where(QLoaiCongViecKeHoach.loaiCongViecKeHoach.ten.eq(tenLoaiCongViecKeHoach));
					if (!LoaiCongViecKeHoach.this.noId()) {
						q.where(QLoaiCongViecKeHoach.loaiCongViecKeHoach.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại loại kế hoạch công việc này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}
	
	

}
