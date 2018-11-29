package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "donvixuctien")
public class DonViXucTien extends Model<DonViXucTien> {

	private String ten = "";
	@Lob
	private String moTa = "";
	private CapDonVi capDonVi;

	public DonViXucTien() {
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

	@ManyToOne
	public CapDonVi getCapDonVi() {
		return capDonVi;
	}

	public void setCapDonVi(CapDonVi capDonVi) {
		this.capDonVi = capDonVi;
	}
	
	@Command
	public void saveDonViXucTien(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn){
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenDonViXucTien() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenDonVi = (String) ctx.getProperty().getValue();
				String param = tenDonVi.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<DonViXucTien> q = find(DonViXucTien.class).where(QDonViXucTien.donViXucTien.ten.eq(tenDonVi));
					if (!DonViXucTien.this.noId()) {
						q.where(QDonViXucTien.donViXucTien.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại đơn vị này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}

}
