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
@Table(name = "capdonvi")
public class CapDonVi extends Model<CapDonVi> {

	private String ten = "";
	
	@Lob
	private String moTa = "";

	public CapDonVi() {
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
	public void saveCapDonVi(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn){
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenCapDonVi() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenCapDonVi = (String) ctx.getProperty().getValue();
				String param = tenCapDonVi.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<CapDonVi> q = find(CapDonVi.class).where(QCapDonVi.capDonVi.ten.eq(tenCapDonVi));
					if (!CapDonVi.this.noId()) {
						q.where(QCapDonVi.capDonVi.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại cấp đơn vị này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}
}
