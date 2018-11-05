package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.CapDonVi;
import vn.toancauxanh.model.DuAn;

public class CapDonViSerVice extends BasicService<DuAn> {
	public JPAQuery<CapDonVi> getTargetQuery(){
		JPAQuery<CapDonVi> q = find(CapDonVi.class);
		return q;
	}
}
