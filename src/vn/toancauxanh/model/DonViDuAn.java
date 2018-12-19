package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.rest.model.DonViDuAnModel;

@Entity
@Table(name="donviduan")
public class DonViDuAn extends Model<DonViDuAn>{
	private DonViXucTien donVi = new DonViXucTien();
	private Date ngayNhanTraLoi;
	private TepTin congVanTraLoi;
	private GiaiDoanDuAn giaiDoanDuAn;
	
	@ManyToOne
	public DonViXucTien getDonVi() {
		return donVi;
	}

	public void setDonVi(DonViXucTien donVi) {
		this.donVi = donVi;
	}
	
	@Transient
	public List<CapDonVi> getListCapDonVi() {
		List<CapDonVi> list = new ArrayList<>();
		JPAQuery<CapDonVi> q = find(CapDonVi.class);
		q.orderBy(QCapDonVi.capDonVi.ten.desc());
		list.addAll(q.fetch());
		return list;
	}
	
	public Date getNgayNhanTraLoi() {
		return ngayNhanTraLoi;
	}

	public void setNgayNhanTraLoi(Date ngayNhanTraLoi) {
		this.ngayNhanTraLoi = ngayNhanTraLoi;
	}
	
	@ManyToOne
	public TepTin getCongVanTraLoi() {
		return congVanTraLoi;
	}

	public void setCongVanTraLoi(TepTin congVanTraLoi) {
		this.congVanTraLoi = congVanTraLoi;
	}
	
	@ManyToOne
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}

	@Transient
	public DonViDuAnModel toDonViDuAnModel() {
		DonViDuAnModel rs = new DonViDuAnModel();
		rs.setTenDonViXucTien(this.getDonVi() != null ? this.getDonVi().getTen() : "");
		rs.setNgayNhanTraLoi(this.getNgayNhanTraLoi() != null ? this.getNgayNhanTraLoi() : null);
		return rs;
	}
}
