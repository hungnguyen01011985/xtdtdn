package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.MapUtils;
import com.querydsl.jpa.impl.JPAQuery;
import vn.toancauxanh.model.LoaiCongViecKeHoach;
import vn.toancauxanh.model.QLoaiCongViecKeHoach;

public class LoaiCongViecKeHoachService extends BasicService<LoaiCongViecKeHoach> {
	
	public JPAQuery<LoaiCongViecKeHoach> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<LoaiCongViecKeHoach> q = find(LoaiCongViecKeHoach.class);
		if (param !=null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%" ;
			q.where(QLoaiCongViecKeHoach.loaiCongViecKeHoach.ten.like(tuKhoa));
		}
		q.orderBy(QLoaiCongViecKeHoach.loaiCongViecKeHoach.ngayTao.desc());
		return q;
	}
	
	public List<LoaiCongViecKeHoach> getLoaiCongViecKeHoach() {
		List<LoaiCongViecKeHoach> list= new ArrayList<>();
		list.add(null);
		list = find(LoaiCongViecKeHoach.class).where(QLoaiCongViecKeHoach.loaiCongViecKeHoach.daXoa.isFalse()).fetch();		
		return list;
	}
	
}
