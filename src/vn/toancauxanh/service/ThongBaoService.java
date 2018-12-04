package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.ThongBaoEnum;
import vn.toancauxanh.model.QThongBao;
import vn.toancauxanh.model.ThongBao;

public class ThongBaoService extends BasicService<ThongBao> {
	public JPAQuery<ThongBao> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		String loaiThongBao = MapUtils.getString(argDeco(), "loaiThongBao", "");
		String trangThai = MapUtils.getString(argDeco(), "trangThai", "").trim();
		JPAQuery<ThongBao> q = find(ThongBao.class).where(QThongBao.thongBao.nguoiNhan.eq(core().getNhanVien()))
				.orderBy(QThongBao.thongBao.ngayTao.desc());
		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QThongBao.thongBao.noiDung.like(tuKhoa));
		}
		if (loaiThongBao != null && !loaiThongBao.isEmpty()) {
			q.where(QThongBao.thongBao.loaiThongBao.eq(LoaiThongBao.valueOf(loaiThongBao)));
		}

		if ("true".equals(trangThai)) {
			q.where(QThongBao.thongBao.daXem.eq(true));
		} else if ("false".equals(trangThai)) {
			q.where(QThongBao.thongBao.daXem.eq(false));
		}
		return q;
	}

	public Long getSizeNewNotify() {
		return getTargetQuery().where(QThongBao.thongBao.daXem.eq(false)).fetchCount();
	}

	@Command
	public void viewNotify(@BindingParam("item") ThongBao thongBao) {
		if (!thongBao.isDaXem()) {
			thongBao.setDaXem(true);
			thongBao.saveNotShowNotification();
		}
		if (!LoaiThongBao.HUY_CONG_VIEC.equals(thongBao.getLoaiThongBao()) && !LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH.equals(thongBao.getLoaiThongBao())) {
			if (ThongBaoEnum.THONG_BAO_DOAN_VAO.equals(thongBao.getKieuThongBao())) {
				thongBao.redirect("/cp/quanlydoanvao/edit/");
			} else if (ThongBaoEnum.THONG_BAO_DU_AN.equals(thongBao.getKieuThongBao())) {
				thongBao.redirect("/cp/quanlyduan/");
			}
		}
		
	}

	public List<LoaiThongBao> getListThongBaoAndNull() {
		List<LoaiThongBao> list = new ArrayList<LoaiThongBao>();
		list.add(null);
		list.add(LoaiThongBao.CONG_VIEC_MOI);
		list.add(LoaiThongBao.TRE_CONG_VIEC);
		list.add(LoaiThongBao.DEN_HAN_CONG_VIEC);
		list.add(LoaiThongBao.CHUYEN_CONG_VIEC_DOAN_VAO);
		list.add(LoaiThongBao.CHUYEN_NGUOI_PHU_TRACH);
		list.add(LoaiThongBao.HUY_CONG_VIEC);
		list.add(LoaiThongBao.NHAC_NHO_CONG_VIEC);
		list.add(LoaiThongBao.PHU_TRACH_CONG_VIEC);
		list.add(LoaiThongBao.TRE_CONG_VIEC);
		return list;
	}

	public Map<String, String> getTrangThaiThongBao() {
		HashMap<String, String> result = new HashMap<>();
		result.put(null, null);
		result.put("false", "Chưa xem");
		result.put("true", "Đã xem");
		return result;
	}
	
}
