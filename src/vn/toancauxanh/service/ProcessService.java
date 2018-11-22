package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.camunda.bpm.engine.impl.RepositoryServiceImpl;
import org.camunda.bpm.engine.impl.bpmn.behavior.ExclusiveGatewayActivityBehavior;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;
import org.camunda.bpm.engine.impl.pvm.PvmTransition;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.Executions;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.LichSuVanBan;
import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;
import vn.toancauxanh.model.QGiaoViec;
import vn.toancauxanh.model.QNhanVien;
import vn.toancauxanh.model.TepTin;
import vn.toancauxanh.model.ThongBao;

public class ProcessService extends BasicService<Object> {
	
	private List<TepTin> listTepTins = new ArrayList<TepTin>();
	
	public void validateDuLieuThongTinDuAn(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuThongTinDuAnHopLe", true);
	}

	public void luuDuLieuDuAnVaBatDauXucTien(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		String idList = model.getNguoiPhuTrach().getId() + KY_TU;
		model.setIdNguoiLienQuan(idList);
		model.saveNotShowNotification();
		model.getGiaoViec().setDuAn(model);
		model.getGiaoViec().setNgayGiao(new Date());
		model.getGiaoViec().setNguoiGiaoViec(model.getNguoiTao());
		model.getGiaoViec().setNguoiDuocGiao(model.getNguoiPhuTrach());
		model.getGiaoViec().setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		model.getGiaoViec().getTaiLieu().saveNotShowNotification();
		model.getGiaoViec().setLoaiCongViec(LoaiCongViec.DU_AN);
		model.getGiaoViec().saveNotShowNotification();
		thongBao(model, LoaiThongBao.CONG_VIEC_MOI, model.getGiaoViec().getNguoiDuocGiao(), model.getGiaoViec().getNguoiGiaoViec(), model.getGiaoViec().getTenCongViec());
		((ExecutionEntity) execution).setVariable("duAnId", model.getId());
		redirectQuanLyDuAn();
	}

	public void validateDuLieuGiaiDoanMot(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanMotHopLe", true);
	}

	public void luuDuLieuGiaiDoanMot(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getTaiLieuNDT().saveNotShowNotification();
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_MOT, false);
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_MOT, "thoiHanGiaiDoanMot", model, "thoiHanGiaiDoanMotOld");
	}
	
	public void validateDuLieuGiaiDoanMotVaTiepTucGiaiDoanHai(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanHaiHopLe", !result);
	}

	public void luuDuLieuGiaiDoanMotVaTiepTucGiaiDoanHai(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getTaiLieuNDT().saveNotShowNotification();
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_MOT, true);
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_HAI, GiaiDoanXucTien.GIAI_DOAN_MOT);
	}
	
	public boolean kiemTraCongViecHoanThanh(DuAn duAn) {
		boolean result = true;
		JPAQuery<GiaoViec> q = find(GiaoViec.class).where(QGiaoViec.giaoViec.duAn.eq(duAn));
		result = q.fetch().stream().anyMatch(item -> !TrangThaiGiaoViec.HOAN_THANH.equals(item.getTrangThaiGiaoViec()));
		return result;
	}

	public void capNhatNguoiPhuTrach(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		Object object = ((ExecutionEntity) execution).getVariable("list");
		String attr = (String) ((ExecutionEntity) execution).getVariable("attr");
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		duAn.setNguoiPhuTrach(model.getNguoiPhuTrach());
		duAn.save();
		if (object != null) {
			BindUtils.postNotifyChange(null, null, object, attr);
		}
	}

	public void giaoViecDuAn(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		GiaoViec giaoViec = (GiaoViec) ((ExecutionEntity) execution).getVariable("giaoViec");
		Object object = ((ExecutionEntity) execution).getVariable("list");
		String attr = (String) ((ExecutionEntity) execution).getVariable("attr");
		giaoViec.setDuAn(duAn);
		giaoViec.setGiaiDoanXucTien(duAn.getGiaiDoanXucTien());
		giaoViec.setNguoiGiaoViec(core().getNhanVien());
		giaoViec.getTaiLieu().saveNotShowNotification();
		giaoViec.setLoaiCongViec(LoaiCongViec.DU_AN);
		giaoViec.saveNotShowNotification();
		duAn.setIdNguoiLienQuan(duAn.getIdNguoiLienQuan() + giaoViec.getNguoiDuocGiao().getId() + KY_TU);
		duAn.saveNotShowNotification();
		thongBao(duAn, LoaiThongBao.CONG_VIEC_MOI, giaoViec.getNguoiDuocGiao(), giaoViec.getNguoiGiaoViec(), giaoViec.getTenCongViec());
		if (object != null) {
			BindUtils.postNotifyChange(null, null, object, attr);
		}
		showNotification("", "Giao việc thành công", "success");
	}

	public void kiemTraDangOGiaiDoanMot(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		boolean kiemTraGiaiDoan = kiemTraGiaiDoan(execution, GiaiDoanXucTien.GIAI_DOAN_MOT);
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(duAnId))
				.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.id.desc());
		GiaiDoanDuAn giaiDoanDuAn = q.fetchFirst();
		if (kiemTraNgay(giaiDoanDuAn.getNgayNhanPhanHoi(), giaiDoanDuAn.getNgayThongBaoOld()) && kiemTraGiaiDoan) {
			((ExecutionEntity) execution).setVariable("isDangOGiaiDoanMot", true);
			return;
		}
		((ExecutionEntity) execution).setVariable("isDangOGiaiDoanMot", false);
	}

	public void validateDuLieuGiaiDoanHaiVaTiepTucGiaiDoanBa(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanBaHopLe", !result);
	}

	public void validateDuLieuGiaiDoanBaVaKetThucDuAn(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBaVaKetThucDuAn", !result);
	}
	
	public void validateDuLieuQuayLaiGiaiDoanMot(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuQuayLaiGIaiDoanMotHopLe", !result);
	}
	
	public void luuDuLieuQuayLaiGiaiDoanMot(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn model = q.fetchFirst();
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		duAn.getGiaiDoanDuAn().setGiaiDoanXucTien(model.getGiaiDoanXucTien());
		duAn.getGiaiDoanDuAn().saveNotShowNotification();
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(model.getGiaiDoanXucTien())) {
			saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_HAI, true);
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(model.getGiaiDoanXucTien())) {
			saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, true);
		}
		luuTaiLieuKhac(duAn.getGiaiDoanDuAn(), true);
		removeGiaiDoanDuAnList(duAn);
		duAn.setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		duAn.saveNotShowNotification();
		redirectGiaiDoanDuAnById(duAn.getId());
		showNotification("", "Cập nhật thành công", "success");
	}
	
	public void removeGiaiDoanDuAnList(DuAn duAn) {
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.eq(duAn));
		q.fetch().forEach(item -> item.doDelete(true));
	}

	public void luuDuLieuGIaiDoanHaiVaTiepTucGiaiDoanBa(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_HAI, true);
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BA, GiaiDoanXucTien.GIAI_DOAN_HAI);
	}
	
	public void luuDuLieuGiaiDoanHai(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_HAI, false);
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_HAI, null, model, null);
	}
	
	public void validateDuLieuGiaiDoanBaVaTiepTucGiaiDoanBon(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanBonHopLe", !result);
	}

	public void luuDuLieuGIaiDoanBaVaTiepTucGiaiDoanBon(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, true);
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BON, GiaiDoanXucTien.GIAI_DOAN_BA);
	}
	
	public void validateDuLieuGiaiDoanBon(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBonHopLe", true);
	}

	public void luuDuLieuGiaiDoanBon(Execution execution) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BON, false);
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BON, null, duAn, null);
	}

	public void validateDuLieuGiaiDoanBa(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBaHopLe", true);
	}

	public void luuDuLieuGiaiDoanBa(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, false);
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BA, "thoiHanGiaiDoanBa", model, "thoiHanGiaiDoanBaOld");
	}
	
	public void kiemTraDangOGiaiDoanBa(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		boolean kiemTraGiaiDoan = kiemTraGiaiDoan(execution, GiaiDoanXucTien.GIAI_DOAN_BA);
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(duAnId))
				.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.id.desc());
		GiaiDoanDuAn giaiDoanDuAn = q.fetchFirst();
		if (kiemTraNgay(giaiDoanDuAn.getNgayDuKienNhanPhanHoi(), giaiDoanDuAn.getNgayThongBaoOld()) && kiemTraGiaiDoan) {
			((ExecutionEntity) execution).setVariable("isDangOGiaiDoanBa", true);
			return;
		}
		((ExecutionEntity) execution).setVariable("isDangOGiaiDoanBa", false);
	}
	
	public void thongBaoTreHanGiaiDoanBa(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		thongBaoTreCongViec(duAn);
	}
	
	public void luuDuLieuKetThucDuAn(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			model.setGiaiDoanXucTien(GiaiDoanXucTien.CHUA_HOAN_THANH);
			saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, true);
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			model.setGiaiDoanXucTien(GiaiDoanXucTien.HOAN_THANH);
			saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_NAM, true);
		}
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		luuTaiLieuKhac(model.getGiaiDoanDuAn(), true);
		model.getGiaiDoanDuAn().saveNotShowNotification();
		luuLichSuVanBan(model);
		showNotification("", "Cập nhật thành công", "success");
		redirectList();
	}
	
	public void validateDuLieuGiaiDoanBonVaTiepTucGiaiDoanNam(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuTiepTucGiaiDoanNam", !result);
	}
	
	public void luuDuLieuGIaiDoanBonVaTiepTucGiaiDoanNam(Execution execution) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BON, true);
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_NAM, GiaiDoanXucTien.GIAI_DOAN_BON);
	}
	
	private void luuLichSuVanBan(DuAn duAn) {
		listTepTins.forEach(item -> {
			LichSuVanBan lichSuVanBan = new LichSuVanBan();
			lichSuVanBan.setDuAn(duAn);
			lichSuVanBan.setGiaiDoanXucTien(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien());
			lichSuVanBan.setVanBan(item);
			lichSuVanBan.setNguoiNhap(item.getNguoiTao());
			lichSuVanBan.saveNotShowNotification();
		});
	}
	
	public void validateDuLieuGiaiDoanNam(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanNamHopLe", true);
	}
	
	public void validateDuLieuGiaiDoanNamVaKetThucDuAn(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		boolean result = kiemTraCongViecHoanThanh(duAn);
		if (result) {
			showNotification("", "Công việc chưa được hoàn thành", "danger");
		}
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeKetThucDuAnHopLe", !result);
	}
	
	public void luuDuLieuGiaiDoanNam(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_NAM, false);
		model.saveNotShowNotification();
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_NAM, null, model, null);
	}
	
	public void luuDuLieuAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien, String thoiHan, DuAn duAn, String thoiHanOld) {
		duAn.getTaiLieuNDT().saveNotShowNotification();
		duAn.saveNotShowNotification();
		duAn.getGiaiDoanDuAn().setDuAn(duAn);
		duAn.getGiaiDoanDuAn().setGiaiDoanXucTien(giaiDoanXucTien);
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			if (duAn.getGiaiDoanDuAn().isKiemTraThongBao()) {
				duAn.getGiaiDoanDuAn().setKiemTraThongBao(false);
			} else {
				JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.id.eq(duAn.getGiaiDoanDuAn().getId()));
				duAn.getGiaiDoanDuAn().setNgayThongBaoOld(q.fetchFirst().getNgayNhanPhanHoi());
			}
			if (duAn.getGiaiDoanDuAn().getNgayNhanPhanHoi() != null && thoiHan != null && !thoiHan.isEmpty()) {
				 ((ExecutionEntity) execution).setVariable(thoiHan, duAn.getGiaiDoanDuAn().getNgayNhanPhanHoi());			
			}
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			if (duAn.getGiaiDoanDuAn().isKiemTraThongBao()) {
				duAn.getGiaiDoanDuAn().setKiemTraThongBao(false);
			} else {
				JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.id.eq(duAn.getGiaiDoanDuAn().getId()));
				duAn.getGiaiDoanDuAn().setNgayThongBaoOld(q.fetchFirst().getNgayDuKienNhanPhanHoi());
			}
			if (duAn.getGiaiDoanDuAn().getNgayDuKienNhanPhanHoi() != null && thoiHan != null && !thoiHan.isEmpty()) {
				 ((ExecutionEntity) execution).setVariable(thoiHan, duAn.getGiaiDoanDuAn().getNgayDuKienNhanPhanHoi());			
			}
		}
		luuTaiLieuKhac(duAn.getGiaiDoanDuAn(), false);
		duAn.getGiaiDoanDuAn().saveNotShowNotification();
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			luuDuLieuDonVi(duAn.getGiaiDoanDuAn(), false);
		}
		redirectGiaiDoanDuAnById(duAn.getId());
		showNotification("", "Cập nhật thành công", "success");
	}

	public void luuTaiLieuKhac(GiaiDoanDuAn giaiDoan, boolean luuLichSu) {
		giaiDoan.getTepTins().forEach(item -> {
			if (item.getNgayTao() == null) {
				item.setNgayTao(new Date());
			}
			item.saveNotShowNotification();
			if (luuLichSu) {
				listTepTins.add(item);
			}
		});
	}
	public void luuDuLieuTiepTucAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien, GiaiDoanXucTien giaiDoan) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		removeGiaiDoanOld(model.getId());
		model.setGiaiDoanXucTien(giaiDoanXucTien);
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		model.getGiaiDoanDuAn().setGiaiDoanXucTien(giaiDoan);
		luuTaiLieuKhac(model.getGiaiDoanDuAn(), true);
		model.getGiaiDoanDuAn().saveNotShowNotification();
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			luuDuLieuDonVi(model.getGiaiDoanDuAn(), true);
		}
		luuLichSuVanBan(model);
		redirectGiaiDoanDuAnById(model.getId());
		showNotification("", "Cập nhật thành công", "success");
	}

	public void redirectGiaiDoanDuAnById(Long id) {
		Executions.sendRedirect("/cp/quanlyduan/" + id);
	}

	public void redirectList() {
		Executions.sendRedirect("/cp/quanlyduan");
	}

	public void luuDuLieuDonVi(GiaiDoanDuAn giaiDoanDuAn, boolean luuLichSu) {
		giaiDoanDuAn.getDonViDuAn().forEach(item -> {
			item.setGiaiDoanDuAn(giaiDoanDuAn);
			if (item.getCongVanTraLoi().getNameHash() == null) {
				item.setCongVanTraLoi(null);
			} else {
				if (item.getCongVanTraLoi().getNgayTao() == null) {
					item.getCongVanTraLoi().setNgayTao(new Date());
					item.getCongVanTraLoi().setTenTaiLieu("Công văn trả lời đơn vị");
				}
				item.getCongVanTraLoi().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(item.getCongVanTraLoi());
				}
			}
			item.saveNotShowNotification();
		});
	}

	public boolean kiemTraGiaiDoan(Execution execution, GiaiDoanXucTien giaiDoan) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		if (duAn != null && giaiDoan != null && duAn.getGiaiDoanXucTien() != null) {
			if (giaiDoan.equals(duAn.getGiaiDoanXucTien())) {
				return true;
			}
		}
		return false;
		/*((ExecutionEntity) execution).setVariable(varriable, false);*/
	}
	
	public void luuDuHoSoKhuDat(GiaiDoanDuAn giaiDoanDuAn, boolean luuLichSu) {
		giaiDoanDuAn.getListXoaHoSoKhuDat().forEach(item ->{
			item.setDaXoa(true);
			item.setTaiLieu(null);
			item.saveNotShowNotification();
		});
		giaiDoanDuAn.getHoSoKhuDats().forEach(item -> {
			if (item.getTaiLieu().getNameHash() == null) {
				item.setTaiLieu(null);
			} else {
				item.getTaiLieu().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(item.getTaiLieu());
				}
			}
			item.setGiaiDoanDuAn(giaiDoanDuAn);
			item.saveNotShowNotification();
		});
	}

	public boolean kiemTraNgay(Date thoiHan, Date thoiHanOld) {
		if (thoiHanOld == null) {
			return true;
		}
		if (thoiHan.compareTo(thoiHanOld) == 0) {
			return false;
		}
		return true;
	}
	
	public void validateDuLieuGiaiDoanHai(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanHaiHopLe", true);
	}

	public void thongBaoTreHanGiaiDoanMot(Execution execution) {
		Long duAnId = Long.valueOf(((ExecutionEntity) execution).getVariable("duAnId").toString());
		JPAQuery<DuAn> q = find(DuAn.class).where(QDuAn.duAn.id.eq(duAnId));
		DuAn duAn = q.fetchFirst();
		thongBaoTreCongViec(duAn);
	}
	
	public void thongBaoTreCongViec(DuAn duAn) {
		JPAQuery<NhanVien> q = find(NhanVien.class).where(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_LANH_DAO).or(QNhanVien.nhanVien.vaiTros.any().loaiVaiTro.eq(LoaiVaiTro.VAI_TRO_TRUONG_PHONG)));
		q.fetch().forEach(item -> thongBao(duAn, LoaiThongBao.TRE_CONG_VIEC, item, null, null));
		thongBao(duAn, LoaiThongBao.TRE_CONG_VIEC, duAn.getNguoiPhuTrach(), null, null);
	}

	public void thongBao(DuAn duAn, LoaiThongBao loaiThongBao, NhanVien nguoiNhan, NhanVien nguoiGui, String tenCongViec) {
		ThongBao thongBao = new ThongBao();
		if (LoaiThongBao.TRE_CONG_VIEC.equals(loaiThongBao)) {
			if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(duAn.getGiaiDoanXucTien())) {
				thongBao.setNoiDung("Công văn đề nghị giới thiệu địa điểm đã đến hạn nhận phản hồi của dự án @" + duAn.getTenDuAn());
			}
			if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(duAn.getGiaiDoanXucTien())) {
				thongBao.setNoiDung("Công văn xin chủ trương đã đến hạn nhận phản hồi của dự án @" + duAn.getTenDuAn());
			}
		}
		if (LoaiThongBao.CONG_VIEC_MOI.equals(loaiThongBao)) {
			thongBao.setNoiDung(nguoiNhan.getHoVaTen() + "@ có công việc mới @" + tenCongViec + "@ của dự án @" + duAn.getTenDuAn());
		}
		thongBao.setNguoiNhan(nguoiNhan);
		if (nguoiGui != null) {
			thongBao.setNguoiGui(nguoiGui);
		}
		thongBao.setIdObject(duAn.getId());
		thongBao.setLoaiThongBao(loaiThongBao);
		thongBao.saveNotShowNotification();
	}
	
	public void saveTepTinNotNullAndSetTaiLieu(TepTin tepTin, String tenTaiLieu, boolean luuLichSu) {
		if (tepTin.getNgayTao() == null) {
			tepTin.setNgayTao(new Date());
		}
		tepTin.setTenTaiLieu(tenTaiLieu);
		tepTin.saveNotShowNotification();
		if (luuLichSu) {
			listTepTins.add(tepTin);
		}
	}
	
	public void saveNotShowNotificationTaiLieuGiaiDoan(GiaiDoanDuAn giaiDoanDuAn, GiaiDoanXucTien giaiDoanXucTien, boolean luuLichSu) {
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD1().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD1(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getTaiLieuGD1(), "Công văn đề nghị giới thiệu địa điểm", luuLichSu);
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD2().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD2(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getTaiLieuGD2(), "Khảo sát thực tế", luuLichSu);
			}
			if (giaiDoanDuAn.getCongVanGD2().getNameHash() == null) {
				giaiDoanDuAn.setCongVanGD2(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getCongVanGD2(), "Văn bản đồng ý địa điểm", luuLichSu);
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD3().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD3(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getTaiLieuGD3(), "Công văn xin chủ trương", luuLichSu);
			}
			if (giaiDoanDuAn.getCongVanGD3().getNameHash() == null) {
				giaiDoanDuAn.setCongVanGD3(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getCongVanGD3(), "Ý kiến của UBND thành phố", luuLichSu);
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(giaiDoanXucTien)) {
			if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				luuDuHoSoKhuDat(giaiDoanDuAn, luuLichSu);
				saveTaiLieuDauGia(giaiDoanDuAn, luuLichSu);
				return;
			}
			if (PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				saveTaiLieuDauThau(giaiDoanDuAn, luuLichSu);
				return;
			}
			if (PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				saveTaiLieuNhanChuyenNhuong(giaiDoanDuAn, luuLichSu);
				return;
			}
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getGiayChungNhanDauTu().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanDauTu(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getGiayChungNhanDauTu(), "Giấy chứng nhận đầu tư", luuLichSu);
			}
			if (giaiDoanDuAn.getGiayChungNhanDangKyDoanhNghiep().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanDangKyDoanhNghiep(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getGiayChungNhanDangKyDoanhNghiep(), "Giấy chứng nhận đăng ký doanh nghiệp", luuLichSu);
			}
			if (giaiDoanDuAn.getGiayChungNhanQuyenSuDungDat().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanQuyenSuDungDat(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getGiayChungNhanQuyenSuDungDat(), "Giấy chứng nhận quyền sử dụng đất", luuLichSu);
			}
		}
	}
	
	public void saveTaiLieuNhanChuyenNhuong(GiaiDoanDuAn giaiDoanDuAn, boolean luuLichSu) {
		if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
			giaiDoanDuAn.setHoSoQuyHoachLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoQuyHoachLQH(), "Hồ sơ quy hoạch", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetLQH(), "Quyết định phê duyệt quy hoạch", luuLichSu);
		}
		if (giaiDoanDuAn.getVanBanChuyenMucDichSDD().getNameHash() == null) {
			giaiDoanDuAn.setVanBanChuyenMucDichSDD(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getVanBanChuyenMucDichSDD(), "Văn bản cho phép chuyển mục đích sử dụng đất", luuLichSu);
		}
		if (giaiDoanDuAn.getVanBanDeNghiThuHoiDat().getNameHash() == null) {
			giaiDoanDuAn.setVanBanDeNghiThuHoiDat(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getVanBanDeNghiThuHoiDat(), "Văn bản đề nghị thu hồi đất", luuLichSu);
		}
	}
	
	public void saveTaiLieuDauThau(GiaiDoanDuAn giaiDoanDuAn, boolean luuLichSu) {
		if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetLQH(), "Quyết định phê duyệt quy hoạch chi tiết 1/500", luuLichSu);
		}
		if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
			giaiDoanDuAn.setHoSoQuyHoachLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoQuyHoachLQH(), "Hồ sơ quy hoạch chi tiết 1/500", luuLichSu);
		}
		if (giaiDoanDuAn.getHoSoQuyHoach2000().getNameHash() == null) {
			giaiDoanDuAn.setHoSoQuyHoach2000(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoQuyHoach2000(), "Hồ sơ quy hoạch chi tiết 1/2000", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyet2000().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyet2000(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyet2000(), "Quyết định phê duyệt quy hoạch chi tiết 1/2000", luuLichSu);
		}
		if (giaiDoanDuAn.getNghiQuyetPheDuyetCongTrinh().getNameHash() == null) {
			giaiDoanDuAn.setNghiQuyetPheDuyetCongTrinh(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getNghiQuyetPheDuyetCongTrinh(), "Nghị quyết phê duyệt công trình, dự án cần thu hồi đất và danh mục dự án có sử dụng đất trồng lúa, đất rừng phòng hộ, đất rừng đặc dụng", luuLichSu);
		}
		if (giaiDoanDuAn.getBaoCaoDoDacKhuDat().getNameHash() == null) {
			giaiDoanDuAn.setBaoCaoDoDacKhuDat(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getBaoCaoDoDacKhuDat(), "Báo cáo đo đạc khu đất, kiểm đếm và lâp dự toán kinh phí giải phóng mặt bằng dự án", luuLichSu);
		}
		if (giaiDoanDuAn.getPheDuyetKeHoachSuDungDat().getNameHash() == null) {
			giaiDoanDuAn.setPheDuyetKeHoachSuDungDat(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getPheDuyetKeHoachSuDungDat(), "Phê duyệt kế hoạch sử dụng đất", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhThuHoiDat().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhThuHoiDat(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhThuHoiDat(), "Quyết định thu hồi đất", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetDanhMuc().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetDanhMuc(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetDanhMuc(), "Quyết định phê duyệt danh mục dự án đầu tư có sử dụng đất hằng năm", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetBoSungKinhPhi().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetBoSungKinhPhi(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetBoSungKinhPhi(), "Quyết định phê duyệt bổ sung kinh phí thực hiện đấu thầu", luuLichSu);
		}
		if (giaiDoanDuAn.getPhuongAnTaiDinhCu().getNameHash() == null) {
			giaiDoanDuAn.setPhuongAnTaiDinhCu(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getPhuongAnTaiDinhCu(), "Phương án tái định cư", luuLichSu);
		}
		if (giaiDoanDuAn.getHoSoMoiTuyen().getNameHash() == null) {
			giaiDoanDuAn.setHoSoMoiTuyen(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoMoiTuyen(), "Hồ sơ mời tuyển", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyeHoSoMoiTuyen().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyeHoSoMoiTuyen(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyeHoSoMoiTuyen(), "Quyết định phê duyệt hồ sơ mời tuyển", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetKetQuaTrungTuyen().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetKetQuaTrungTuyen(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetKetQuaTrungTuyen(), "Quyết định phê duyệt kết quả trúng sơ tuyển", luuLichSu);
		}
		if (giaiDoanDuAn.getKeHoachLuaChonNhaDauTu().getNameHash() == null) {
			giaiDoanDuAn.setKeHoachLuaChonNhaDauTu(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getKeHoachLuaChonNhaDauTu(), "Kế hoạch lựa chọn nhà thầu", luuLichSu);
		}
		if (giaiDoanDuAn.getHoSoMoiThau().getNameHash() == null) {
			giaiDoanDuAn.setHoSoMoiThau(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoMoiThau(), "Hồ sơ mời thầu", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetMoiThau().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetMoiThau(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetMoiThau(), "Quyết định phê duyệt Kế hoạch và hồ sơ mời thầu lựa chọn nhà đầu tư", luuLichSu);
		}
	}
	
	public void saveTaiLieuDauGia(GiaiDoanDuAn giaiDoanDuAn, boolean luuLichSu) {
		if (giaiDoanDuAn.getQuyetDinhPheDuyetPADG().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetPADG(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetPADG(), "Quyết định phê duyệt phương án đấu giá", luuLichSu);
		}
		if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
			giaiDoanDuAn.setHoSoQuyHoachLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getHoSoQuyHoachLQH(), "Lập quy hoạch chi tiết 1/500", luuLichSu);
		}
		if (giaiDoanDuAn.getPhuongAnDauGia().getNameHash() == null) {
			giaiDoanDuAn.setPhuongAnDauGia(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getPhuongAnDauGia(), "Phương án đấu giá quyền sử dụng đất", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhDauGiaQSDD().getNameHash() == null ) {
			giaiDoanDuAn.setQuyetDinhDauGiaQSDD(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhDauGiaQSDD(), "Quyết định đấu giá quyền sử dụng đất", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetGiaKhoiDiem().getNameHash() == null ) {
			giaiDoanDuAn.setQuyetDinhPheDuyetGiaKhoiDiem(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetGiaKhoiDiem(), "Quyết định phê duyệt giá đất khởi điểm đấu giá", luuLichSu);
		}
		if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
			giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
		} else {
			saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhPheDuyetLQH(), "Quyết định phê duyệt lập quy hoạch chi tiết 1/500", luuLichSu);
		}
		if (!giaiDoanDuAn.isOption()) {
			if (giaiDoanDuAn.getQuyetDinhBoSungDanhMuc().getNameHash() == null ) {
				giaiDoanDuAn.setQuyetDinhBoSungDanhMuc(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getQuyetDinhBoSungDanhMuc(), "Quyết định bổ sung danh mục quỹ đất đấu giá quyền sử dụng đất", luuLichSu);
			}
			if (giaiDoanDuAn.getVanBanDeNghiBoSung().getNameHash() == null) {
				giaiDoanDuAn.setVanBanDeNghiBoSung(null);
			} else {
				saveTepTinNotNullAndSetTaiLieu(giaiDoanDuAn.getVanBanDeNghiBoSung(), "Văn bản đề nghị bổ sung danh mục quỹ đất đấu giá quyền sử dụng đất", luuLichSu);
			}
		}
	}
	
	public void removeGiaiDoanOld(Long idDuAn) {
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(idDuAn));
		q.fetch().forEach(item -> {
			item.setDaQuaGiaiDoan(false);
			item.saveNotShowNotification();
		});
	}
	
	public List<PvmTransition> getTransitions(Task task) {
		List<PvmTransition> result = new ArrayList<>();
		for (PvmTransition pvmTransition : ((RepositoryServiceImpl) core().getProcess().getRepositoryService())
				.getDeployedProcessDefinition(task.getProcessDefinitionId()).findActivity(task.getTaskDefinitionKey())
				.getOutgoingTransitions()) {
			if (((ActivityImpl) pvmTransition.getDestination())
					.getActivityBehavior() instanceof ExclusiveGatewayActivityBehavior) {
				List<PvmTransition> outgoingTransitions = pvmTransition.getDestination().getOutgoingTransitions();
				if (outgoingTransitions.isEmpty()) {
					result.add(pvmTransition);
				} else {
					result.addAll(outgoingTransitions);
				}
			} else {
				result.add(pvmTransition);
			}
		}
		return result;
	}

	public List<PvmTransition> getTransitions(String taskDefinitionKey, String processDefinitionKey) {
		List<ProcessDefinition> ls = core().getProcess().getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionKeyLike(processDefinitionKey).latestVersion().list();
		String processDefinitionId = ls.get(0).getId();
		List<PvmTransition> result = new ArrayList<>();
		for (PvmTransition pvmTransition : ((RepositoryServiceImpl) core().getProcess().getRepositoryService())
				.getDeployedProcessDefinition(processDefinitionId).findActivity(taskDefinitionKey)
				.getOutgoingTransitions()) {
			if (((ActivityImpl) pvmTransition.getDestination())
					.getActivityBehavior() instanceof ExclusiveGatewayActivityBehavior) {
				List<PvmTransition> outgoingTransitions = pvmTransition.getDestination().getOutgoingTransitions();
				if (outgoingTransitions.isEmpty()) {
					result.add(pvmTransition);
				} else {
					result.addAll(outgoingTransitions);
				}
			} else {
				result.add(pvmTransition);
			}
		}
		return result;
	}
}