package vn.toancauxanh.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.BindUtils;

import com.querydsl.jpa.impl.JPAQuery;

@Entity
@Table(name="donviduan")
public class DonViDuAn extends Model<DonViDuAn>{
	private CapDonVi capDonVi;
	private DonViXucTien donVi;
	private Date ngayNhanTraLoi;
	private TepTin congVanTraLoi = new TepTin();
	private Date ngayNhanGiaiThich;
	private TepTin congVanGiaiThich = new TepTin();
	private GiaiDoanDuAn giaiDoanDuAn;
	
	@ManyToOne
	public CapDonVi getCapDonVi() {
		return capDonVi;
	}

	public void setCapDonVi(CapDonVi capDonVi) {
		this.capDonVi = capDonVi;
		BindUtils.postNotifyChange(null, null, this, "listDonViXucTien");
	}

	@ManyToOne
	public DonViXucTien getDonVi() {
		return donVi;
	}

	public void setDonVi(DonViXucTien donVi) {
		this.donVi = donVi;
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

	public Date getNgayNhanGiaiThich() {
		return ngayNhanGiaiThich;
	}

	public void setNgayNhanGiaiThich(Date ngayNhanGiaiThich) {
		this.ngayNhanGiaiThich = ngayNhanGiaiThich;
	}
	
	@ManyToOne
	public TepTin getCongVanGiaiThich() {
		return congVanGiaiThich;
	}

	public void setCongVanGiaiThich(TepTin congVanGiaiThich) {
		this.congVanGiaiThich = congVanGiaiThich;
	}
	
	@ManyToOne
	public GiaiDoanDuAn getGiaiDoanDuAn() {
		return giaiDoanDuAn;
	}

	public void setGiaiDoanDuAn(GiaiDoanDuAn giaiDoanDuAn) {
		this.giaiDoanDuAn = giaiDoanDuAn;
	}
	
	@Transient
	public List<CapDonVi> getListCapDonVi() {
		List<CapDonVi> list = new ArrayList<CapDonVi>();
		JPAQuery<CapDonVi> q = find(CapDonVi.class);
		list.addAll(q.fetch());
		return list;
	}
	
	@Transient
	public List<DonViXucTien> getListDonViXucTien() {
		List<DonViXucTien> list = new ArrayList<DonViXucTien>();
		JPAQuery<DonViXucTien> q = find(DonViXucTien.class);
		if (capDonVi != null) {
			q.where(QDonViXucTien.donViXucTien.capDonVi.eq(capDonVi));
		}
		list.addAll(q.fetch());
		return list;
	}
}
