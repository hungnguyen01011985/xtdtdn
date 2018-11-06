package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiDonVi;

@Entity
@Table(name = "donvi")
public class DonVi extends Model<DonVi> {

	private String ten = "";
//	@Lob
	private String moTa = "";
	private LoaiDonVi loaiDonVi;

	public DonVi() {
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

	@Enumerated(EnumType.STRING)
	public LoaiDonVi getLoaiDonVi() {
		return loaiDonVi;
	}

	public void setLoaiDonVi(LoaiDonVi loaiDonVi) {
		this.loaiDonVi = loaiDonVi;
	}
	
	@Transient
	public List<LoaiDonVi> getListDonVi(){
		List<LoaiDonVi> list = new ArrayList<LoaiDonVi>();
		list.add(LoaiDonVi.DON_VI_TU_VAN);
		list.add(LoaiDonVi.DON_VI_CHU_TRI);
		list.add(LoaiDonVi.DON_VI_THUC_HIEN);
		return list;
	}
	
	@Command
	public void saveDonVi(@BindingParam("list") final Object listObject, @BindingParam("attr") final String attr,
			@BindingParam("wdn") final Window wdn){
		setTen(getTen().trim().replaceAll("\\s+", " "));
		save();
		wdn.detach();
		BindUtils.postNotifyChange(null, null, listObject, attr);
	}
	
	@Transient
	public AbstractValidator getValidateTenDonVi() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				String tenDonVi = (String) ctx.getProperty().getValue();
				String param = tenDonVi.trim().replaceAll("\\s+", "");
				if (!"".equals(param) && param != null && !param.isEmpty()) {
					JPAQuery<DonVi> q = find(DonVi.class).where(QDonVi.donVi.ten.eq(tenDonVi));
					if (!DonVi.this.noId()) {
						q.where(QDonVi.donVi.id.ne(getId()));
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
