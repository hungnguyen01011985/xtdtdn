package vn.toancauxanh.service;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.LichSuVanBan;
import vn.toancauxanh.model.QLichSuVanBan;

public class LichSuVanBanService extends BasicService<LichSuVanBan>{
	public JPAQuery<vn.toancauxanh.model.LichSuVanBan> getTargetQuery(){
		Long idDuAn = MapUtils.getLongValue(argDeco(), "idGiaiDoanDuAn");
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String giaiDoanXucTien = MapUtils.getString(argDeco(), "giaiDoanXucTien", "");
		Long nguoiNhap = (Long) argDeco().get("nhanVien");
		JPAQuery<LichSuVanBan> q = find(LichSuVanBan.class).where(QLichSuVanBan.lichSuVanBan.duAn.id.eq(idDuAn));
		if (nguoiNhap != null) {
			q.where(QLichSuVanBan.lichSuVanBan.nguoiNhap.id.eq(nguoiNhap));
		}
		if (param != null && !param.isEmpty()) {
			String tuKhoa = "%" + param + "%";
			q.where(QLichSuVanBan.lichSuVanBan.vanBan.tenTaiLieu.like(tuKhoa));
		}
		if (giaiDoanXucTien != null && !giaiDoanXucTien.isEmpty()) {
			q.where(QLichSuVanBan.lichSuVanBan.giaiDoanXucTien.eq(GiaiDoanXucTien.valueOf(giaiDoanXucTien)));
		}
		
		q.orderBy(QLichSuVanBan.lichSuVanBan.vanBan.ngayTao.desc());
		return q;
	}
	
	@Command
	public void closePopup(@BindingParam("wdn") Window wdn) {
		if (wdn != null) {
			wdn.detach();
		}
	}
	
}
