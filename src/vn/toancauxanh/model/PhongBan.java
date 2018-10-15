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
@Table(name = "phongban")
public class PhongBan extends Model<PhongBan> {
	private String ten = "";
	private String moTa = "";

	public PhongBan() {
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

	// @command('savePhongBan', list=vmArgs, attr='targetQuery', wdn=wdn)"
	@Command
	public void savePhongBan(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}

	@Transient
	public AbstractValidator getValidateTenPhongBan() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenPhongBan = (String) ctx.getProperty().getValue();
				String param = tenPhongBan.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<PhongBan> q = find(PhongBan.class).where(
							QPhongBan.phongBan.daXoa.isFalse().and(QPhongBan.phongBan.trangThai.ne(core().TT_DA_XOA))
									.and(QPhongBan.phongBan.ten.eq(tenPhongBan)));
					if (!PhongBan.this.noId()) {
						q.where(QPhongBan.phongBan.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại phòng ban này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}
}
