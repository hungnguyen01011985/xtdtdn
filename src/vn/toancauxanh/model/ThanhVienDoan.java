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
import vn.toancauxanh.rest.model.ThanhVienDoanModel;

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
				String param = (String) ctx.getProperty().getValue();
				if (param == null || "".equals(param) || param.trim().matches(".+@.+\\.[a-z]+")) {
					JPAQuery<ThanhVienDoan> q = find(ThanhVienDoan.class).where(QThanhVienDoan.thanhVienDoan.email
							.eq(param).and(QThanhVienDoan.thanhVienDoan.email.isNotEmpty())
							.and(QThanhVienDoan.thanhVienDoan.email.isNotNull()));
					if (!ThanhVienDoan.this.noId()) {
						q.where(QThanhVienDoan.thanhVienDoan.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Email đã được sử dụng");
					}
				} else {
					addInvalidMessage(ctx, "Email không đúng định dạng");
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
	
	@Transient
	public ThanhVienDoanModel toThanhVienDoanModel() {
		ThanhVienDoanModel rs = new ThanhVienDoanModel();
		rs.setId(getId() != null ? getId() : null);
		rs.setHoVaTen(getHoVaTen() != null ? getHoVaTen() : "");
		rs.setDonVi(getDonVi() != null ? getDonVi() : "");
		rs.setChucDanh(getChucDanh() != null ? getChucDanh() : "");
		rs.setEmail(getEmail() != null ? getEmail() : "");
		rs.setSoDienThoai(getSoDienThoai() != null ? getSoDienThoai() : "");
		rs.setQuocGia(getQuocGia() != null ? getQuocGia().getText() : "");
		return rs;
	}
}
