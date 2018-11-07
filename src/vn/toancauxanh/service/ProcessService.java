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
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.LoaiVaiTro;
import vn.toancauxanh.gg.model.enums.PhuongThucLuaChonNDT;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.NhanVien;
import vn.toancauxanh.model.QDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;
import vn.toancauxanh.model.QGiaoViec;
import vn.toancauxanh.model.QNhanVien;
import vn.toancauxanh.model.ThongBao;

public class ProcessService extends BasicService<Object> {

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
		model.getGiaiDoanDuAn().getTaiLieuGD1().saveNotShowNotification();
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_MOT, "thoiHanGiaiDoanMot", model.getGiaiDoanDuAn().getNgayNhanPhanHoi(), "thoiHanGiaiDoanMotOld");
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
		model.getGiaiDoanDuAn().getTaiLieuGD1().saveNotShowNotification();
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
		giaoViec.saveNotShowNotification();
		duAn.setIdNguoiLienQuan(duAn.getIdNguoiLienQuan() + giaoViec.getNguoiDuocGiao().getId() + KY_TU);
		duAn.saveNotShowNotification();
		thongBao(duAn, LoaiThongBao.CONG_VIEC_MOI, giaoViec.getNguoiDuocGiao(), giaoViec.getNguoiGiaoViec(), giaoViec.getTenCongViec());
		if (object != null) {
			BindUtils.postNotifyChange(null, null, object, attr);
		}
	}

	public void kiemTraDangOGiaiDoanMot(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		boolean kiemTraGiaiDoan = kiemTraGiaiDoan(execution, GiaiDoanXucTien.GIAI_DOAN_MOT);
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(model.getId()))
				.orderBy(QGiaiDoanDuAn.giaiDoanDuAn.id.desc());
		GiaiDoanDuAn giaiDoanDuAn = q.fetchFirst();
		if (kiemTraNgay(giaiDoanDuAn.getNgayNhanPhanHoi(), giaiDoanDuAn.getNgayThongBaoOld()) && kiemTraGiaiDoan) {
			((ExecutionEntity) execution).setVariable("isDangOGiaiDoanMot", true);
			return;
		}
		((ExecutionEntity) execution).setVariable("isDangOGiaiDoanMot", false);
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
			if (nguoiGui != null) {
				thongBao.setNguoiGui(nguoiGui);
			}
			if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(duAn.getGiaiDoanXucTien())) {
				thongBao.setNoiDung("Công văn đề nghị đã đến hạn nhận phản hồi");
			}
			if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(duAn.getGiaiDoanXucTien())) {
				thongBao.setNoiDung("Công văn xin chủ trương đã đến hạn nhận phản hồi");
			}
			thongBao.setNguoiNhan(nguoiNhan);
			
		}
		if (LoaiThongBao.CONG_VIEC_MOI.equals(loaiThongBao)) {
			thongBao.setNguoiNhan(nguoiNhan);
			thongBao.setNguoiGui(nguoiGui);
			thongBao.setNoiDung("<x:span class='text-bold-notify'>" + nguoiNhan.getHoVaTen()
					+ " </x:span> <x:span class='text-regular-notify'>có công việc mới </x:span><x:span class='text-bold-notify'>"
					+ tenCongViec
					+ " </x:span><x:span class='text-regular-notify'>của dự án</x:span> " + duAn.getTenDuAn());
		}
		thongBao.setIdObject(duAn.getId());
		thongBao.setLoaiThongBao(loaiThongBao);
		thongBao.saveNotShowNotification();
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

	public void luuDuLieuQuayLaiGiaiDoanMot(Execution execution) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		duAn.getGiaiDoanDuAn().setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_BA);
		duAn.getGiaiDoanDuAn().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getTaiLieuGD3().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getCongVanGD3().saveNotShowNotification();
		removeGiaiDoanDuAnList(duAn);
		duAn.setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		duAn.saveNotShowNotification();
		redirectGiaiDoanDuAnById(duAn.getId());
	}

	public void removeGiaiDoanDuAnList(DuAn duAn) {
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.eq(duAn));
		q.fetch().forEach(item -> item.doDelete(true));
	}

	public void luuDuLieuGIaiDoanHaiVaTiepTucGiaiDoanBa(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getGiaiDoanDuAn().getTaiLieuGD2().saveNotShowNotification();
		model.getGiaiDoanDuAn().getCongVanGD2().saveNotShowNotification();
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BA, GiaiDoanXucTien.GIAI_DOAN_HAI);
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
		model.getGiaiDoanDuAn().getTaiLieuGD3().saveNotShowNotification();
		model.getGiaiDoanDuAn().getCongVanGD3().saveNotShowNotification();
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BON, GiaiDoanXucTien.GIAI_DOAN_BA);
	}

	public void luuDuLieuGiaiDoanHai(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getGiaiDoanDuAn().getTaiLieuGD2().saveNotShowNotification();
		model.getGiaiDoanDuAn().getCongVanGD2().saveNotShowNotification();
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_HAI, null, null, null);
	}

	public void validateDuLieuGiaiDoanBon(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBonHopLe", true);
	}

	public void luuDuLieuGiaiDoanBon(Execution execution) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuDauGia(duAn);
		}
		if (PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuDauThau(duAn);
		}
		if (PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuNhanChuyenNhuong(duAn);
		}
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BON, null, null, null);
	}

	private void saveTaiLieuDauGia(DuAn duAn) {
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetPADG().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getHoSoQuyHoachLQH().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getPhuongAnDauGia().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhDauGiaQSDD().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetGiaKhoiDiem().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetLQH().saveNotShowNotification();
		if (!duAn.getGiaiDoanDuAn().isOption()) {
			duAn.getGiaiDoanDuAn().getQuyetDinhBoSungDanhMuc().saveNotShowNotification();
			duAn.getGiaiDoanDuAn().getVanBanDeNghiBoSung().saveNotShowNotification();
		}
	}

	private void saveTaiLieuDauThau(DuAn duAn) {
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetLQH().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getHoSoQuyHoachLQH().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getHoSoQuyHoach2000().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyet2000().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getNghiQuyetPheDuyetCongTrinh().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getBaoCaoDoDacKhuDat().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getPheDuyetKeHoachSuDungDat().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhThuHoiDat().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetDanhMuc().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetBoSungKinhPhi().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getPhuongAnTaiDinhCu().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getHoSoMoiTuyen().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyeHoSoMoiTuyen().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetKetQuaTrungTuyen().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getKeHoachLuaChonNhaDauTu().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getHoSoMoiThau().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetMoiThau().saveNotShowNotification();
	}
	
	private void saveTaiLieuNhanChuyenNhuong(DuAn duAn) {
		duAn.getGiaiDoanDuAn().getHoSoQuyHoachLQH().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getQuyetDinhPheDuyetLQH().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getVanBanChuyenMucDichSDD().saveNotShowNotification();
		duAn.getGiaiDoanDuAn().getVanBanDeNghiThuHoiDat().saveNotShowNotification();
	}

	public void validateDuLieuGiaiDoanBa(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBaHopLe", true);
	}

	public void luuDuLieuGiaiDoanBa(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getGiaiDoanDuAn().getTaiLieuGD3().saveNotShowNotification();
		model.getGiaiDoanDuAn().getCongVanGD3().saveNotShowNotification();
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_BA, "thoiHanGiaiDoanBa", model.getGiaiDoanDuAn().getNgayDuKienNhanPhanHoi(), "thoiHanGiaiDoanBaOld");
	}
	
	public void kiemTraDangOGiaiDoanBa(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		boolean kiemTraGiaiDoan = kiemTraGiaiDoan(execution, GiaiDoanXucTien.GIAI_DOAN_BA);
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.id.eq(model.getId()))
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
			model.getGiaiDoanDuAn().getTaiLieuGD3().saveNotShowNotification();
			model.getGiaiDoanDuAn().getCongVanGD3().saveNotShowNotification();
			model.setGiaiDoanXucTien(GiaiDoanXucTien.CHUA_HOAN_THANH);
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			model.setGiaiDoanXucTien(GiaiDoanXucTien.HOAN_THANH);
			model.getGiaiDoanDuAn().getGiayChungNhanDauTu().saveNotShowNotification();
			model.getGiaiDoanDuAn().getGiayChungNhanDangKyDoanhNghiep().saveNotShowNotification();
			model.getGiaiDoanDuAn().getGiayChungNhanQuyenSuDungDat().saveNotShowNotification();
			model.getGiaiDoanDuAn().getTaiLieuDinhKem().saveNotShowNotification();
		}
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		model.getGiaiDoanDuAn().saveNotShowNotification();
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
		if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuDauGia(duAn);
		}
		if (PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuDauThau(duAn);
		}
		if (PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG.equals(duAn.getGiaiDoanDuAn().getPhuongThucLuaChonNDT())) {
			saveTaiLieuNhanChuyenNhuong(duAn);
		}
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_NAM, GiaiDoanXucTien.GIAI_DOAN_BON);
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
		model.getGiaiDoanDuAn().getGiayChungNhanDauTu().saveNotShowNotification();
		model.getGiaiDoanDuAn().getGiayChungNhanDangKyDoanhNghiep().saveNotShowNotification();
		model.getGiaiDoanDuAn().getGiayChungNhanQuyenSuDungDat().saveNotShowNotification();
		model.getGiaiDoanDuAn().getTaiLieuDinhKem().saveNotShowNotification();
		model.saveNotShowNotification();
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_NAM, null, null, null);
	}
	
	public void luuDuLieuAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien, String thoiHan, Date ngay, String thoiHanOld) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
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
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			if (duAn.getGiaiDoanDuAn().isKiemTraThongBao()) {
				duAn.getGiaiDoanDuAn().setKiemTraThongBao(false);
			} else {
				JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class).where(QGiaiDoanDuAn.giaiDoanDuAn.id.eq(duAn.getGiaiDoanDuAn().getId()));
				duAn.getGiaiDoanDuAn().setNgayThongBaoOld(q.fetchFirst().getNgayDuKienNhanPhanHoi());
			}
		}
		duAn.getGiaiDoanDuAn().saveNotShowNotification();
		if (ngay != null && thoiHan != null && !thoiHan.isEmpty()) {
			 ((ExecutionEntity) execution).setVariable(thoiHan, ngay);			
		}
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(duAn.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			luuDuLieuDonVi(duAn.getGiaiDoanDuAn());
		}
		redirectGiaiDoanDuAnById(duAn.getId());
		showNotification("", "Cập nhật thành công", "success");
	}

	public void luuDuLieuTiepTucAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien, GiaiDoanXucTien giaiDoan) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.setGiaiDoanXucTien(giaiDoanXucTien);
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		model.getGiaiDoanDuAn().setGiaiDoanXucTien(giaiDoan);
		model.getGiaiDoanDuAn().saveNotShowNotification();
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			luuDuLieuDonVi(model.getGiaiDoanDuAn());
		}
		redirectGiaiDoanDuAnById(model.getId());
		showNotification("", "Cập nhật thành công", "success");
	}

	public void redirectGiaiDoanDuAnById(Long id) {
		Executions.sendRedirect("/cp/quanlyduan/" + id);
	}

	public void redirectList() {
		Executions.sendRedirect("/cp/quanlyduan");
	}

	public void luuDuLieuDonVi(GiaiDoanDuAn giaiDoanDuAn) {
		giaiDoanDuAn.getDonViDuAn().forEach(item -> {
			item.setGiaiDoanDuAn(giaiDoanDuAn);
			item.saveNotShowNotification();
			item.getCongVanGiaiThich().saveNotShowNotification();
			item.getCongVanTraLoi().saveNotShowNotification();
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