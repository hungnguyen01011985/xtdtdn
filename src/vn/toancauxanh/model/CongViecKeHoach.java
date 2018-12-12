package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

@Entity
@Table(name = "congvieckehoach")
public class CongViecKeHoach extends Model<CongViecKeHoach> {

	private String ten = "";
	private String moTa = "";
	
	private LoaiCongViecKeHoach loaiCongViecKeHoach;

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
	public LoaiCongViecKeHoach getLoaiCongViecKeHoach() {
		return loaiCongViecKeHoach;
	}
	
	public void setLoaiCongViecKeHoach(LoaiCongViecKeHoach loaiCongViecKeHoach) {
		this.loaiCongViecKeHoach = loaiCongViecKeHoach;
	}

	@Command
	public void saveCongViecKeHoach(@BindingParam("list") final Object listObject,
			@BindingParam("attr") final String attr, @BindingParam("wdn") final Window wdn) {
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}

	@Transient
	public AbstractValidator getValidateTenCongViecKeHoach() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenCongViecKeHoach = (String) ctx.getProperty().getValue();
				String param = tenCongViecKeHoach.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<CongViecKeHoach> q = find(CongViecKeHoach.class).where(QCongViecKeHoach.congViecKeHoach.ten.eq(tenCongViecKeHoach));
					if (!CongViecKeHoach.this.noId()) {
						q.where(QCongViecKeHoach.congViecKeHoach.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Đã tồn tại kế hoạch công việc này");
					}
				} else {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
			}
		};
	}
	

}
