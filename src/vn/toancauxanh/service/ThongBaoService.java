package vn.toancauxanh.service;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.QThongBao;
import vn.toancauxanh.model.ThongBao;

public class ThongBaoService extends BasicService<ThongBao>{
	public JPAQuery<ThongBao> getTargetQuery() {
		JPAQuery<ThongBao> q = find(ThongBao.class)
				.where(QThongBao.thongBao.nguoiNhan.eq(core().getNhanVien()))
				.orderBy(QThongBao.thongBao.ngaySua.desc());
		return q;
	}
	
	public Long getSizeNewNotify() {
		return getTargetQuery().where(QThongBao.thongBao.daXem.eq(false)).fetchCount();
	}

	@Command
	public void viewNotify(@BindingParam("item")ThongBao thongBao) {
		thongBao.setDaXem(true);
		thongBao.saveAndRedirect();
	}
}
