package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.MapUtils;
import com.querydsl.jpa.impl.JPAQuery;
import vn.toancauxanh.model.KeyApi;
import vn.toancauxanh.model.QKeyApi;

public class KeyApiService extends BasicService<KeyApi> {
	public JPAQuery<KeyApi> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<KeyApi> q = find(KeyApi.class);
		if (param !=null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%" ;
			q.where(QKeyApi.keyApi1.keyApi.like(tuKhoa).or(QKeyApi.keyApi1.ten.like(tuKhoa)));
		}
		q.orderBy(QKeyApi.keyApi1.ngayTao.desc());
		return q;
	}
	
	public List<KeyApi> getKeyApi() {
		List<KeyApi> list= new ArrayList<>();
		list.add(null);
		list = find(KeyApi.class).orderBy(QKeyApi.keyApi1.keyApi.asc()).fetch();		
		return list;
	}
}
