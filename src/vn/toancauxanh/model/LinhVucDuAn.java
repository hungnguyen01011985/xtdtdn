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
@Table(name = "linhvucduan")
public class LinhVucDuAn extends Model<LinhVucDuAn> {
	private String ten = "";
	private String moTa = "";

	public LinhVucDuAn() {
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
	public void saveLinhVucDuAn(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn) {
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenLinhVucDuAn() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenDanhMuc = (String) ctx.getProperty().getValue();
				String param = tenDanhMuc.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<LinhVucDuAn> q = find(LinhVucDuAn.class).where(QLinhVucDuAn.linhVucDuAn.ten.eq(tenDanhMuc));
					if (!LinhVucDuAn.this.noId()) {
						q.where(QLinhVucDuAn.linhVucDuAn.id.ne(getId()));
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
