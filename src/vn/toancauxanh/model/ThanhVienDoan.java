package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.QuocGiaEnum;

@Entity
@Table(name = "thanhviendoan")
public class ThanhVienDoan extends Model<ThanhVienDoan> {
	private String hoVaTen = "";
	private String donVi = "";
	private String chucDanh = "";
	private QuocGiaEnum quocGia;
	private String email = "";
	private String soDienThoai = "";
	private DoanVao doanVao;

	public ThanhVienDoan() {
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getChucDanh() {
		return chucDanh;
	}

	public void setChucDanh(String chucDanh) {
		this.chucDanh = chucDanh;
	}

	@Enumerated(EnumType.STRING)
	public QuocGiaEnum getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(QuocGiaEnum quocGia) {
		this.quocGia = quocGia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	@ManyToOne
	public DoanVao getDoanVao() {
		return doanVao;
	}

	public void setDoanVao(DoanVao doanVao) {
		this.doanVao = doanVao;
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
					JPAQuery<ThanhVienDoan> q = find(ThanhVienDoan.class)
							.where(QThanhVienDoan.thanhVienDoan.email.eq(value));
					if (!ThanhVienDoan.this.noId()) {
						q.where(QThanhVienDoan.thanhVienDoan.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Email đã được sử dụng");
					}
				}
			}
		};
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
}
