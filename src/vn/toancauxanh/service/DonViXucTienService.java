package vn.toancauxanh.service;

import org.zkoss.bind.BindUtils;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.CapDonVi;
import vn.toancauxanh.model.DonViXucTien;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDonViXucTien;

public class DonViXucTienService extends BasicService<DuAn> {
	private CapDonVi selectedCapDonVi;

	public CapDonVi getSelectedCapDonVi() {
		return selectedCapDonVi;
	}

	public void setSelectedCapDonVi(CapDonVi selectedCapDonVi) {
		this.selectedCapDonVi = selectedCapDonVi;
		BindUtils.postNotifyChange(null, null, this, "targetQuery");
	}

	public JPAQuery<DonViXucTien> getTargetQuery() {
		JPAQuery<DonViXucTien> q = find(DonViXucTien.class);

		if (selectedCapDonVi != null) {
			q.where(QDonViXucTien.donViXucTien.capDonVi.eq(selectedCapDonVi));
		}
		return q;
	}

}
