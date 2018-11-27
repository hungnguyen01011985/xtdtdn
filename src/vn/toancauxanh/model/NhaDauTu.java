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
	
	@Transient
	public AbstractValidator getValidateSoDienThoai() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (!value.isEmpty() && !value.trim()
						.matches("^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$")) {
					addInvalidMessage(ctx, "Số điện thoại không đúng định dạng.");
				}
			}
		};
	}
	
	@Transient
	public AbstractValidator getValidatorEmail() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				String param = value.trim().replaceAll("\\s+", "");
				if (param == null || "".equals(param) || param.isEmpty()) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				} else if (!value.trim().matches(".+@.+\\.[a-z]+")) {
					addInvalidMessage(ctx, "Email không đúng định dạng");
				} else {
					JPAQuery<NhaDauTu> q = find(NhaDauTu.class)
							.where(QNhaDauTu.nhaDauTu.email.eq(value));
					if (!noId()) {
						q.where(QNhaDauTu.nhaDauTu.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Email đã được sử dụng");
					}
				}
			}
		};
	}
	
}
