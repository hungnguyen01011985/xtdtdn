package vn.toancauxanh.service;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.LoaiCongViecKeHoach;

public class LoaiCongViecKeHoachService extends BasicService<LoaiCongViecKeHoach> {

	public JPAQuery<LoaiCongViecKeHoach> getTargetQuery(){
		JPAQuery<LoaiCongViecKeHoach> q = find(LoaiCongViecKeHoach.class);
		return q;
	}
	
}
