package vn.toancauxanh.model;

import javax.persistence.Entity;
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
@Table(name = "danhmuc")
public class DanhMuc extends Model<DanhMuc> {
	private String ten = "";
	private String moTa = "";

	public DanhMuc() {
	}

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
	public void saveDanhMuc(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenDanhMuc() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenDanhMuc = (String) ctx.getProperty().getValue();
				String param = tenDanhMuc.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<DanhMuc> q = find(DanhMuc.class).where(QDanhMuc.danhMuc.ten.eq(tenDanhMuc));
					if (!DanhMuc.this.noId()) {
						q.where(QDanhMuc.danhMuc.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại lĩnh vực dự án này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}

}
