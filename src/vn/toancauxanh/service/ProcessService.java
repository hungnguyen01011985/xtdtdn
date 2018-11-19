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
import vn.toancauxanh.model.DonViDuAn;
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
			saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_HAI, false);
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(model.getGiaiDoanXucTien())) {
			saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, false);
		}
		luuTaiLieuKhac(duAn.getGiaiDoanDuAn(), false);
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
	
	public void saveNotShowNotificationTaiLieuGiaiDoan(GiaiDoanDuAn giaiDoanDuAn, GiaiDoanXucTien giaiDoanXucTien, boolean luuLichSu) {
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD1().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD1(null);
			} else {
				giaiDoanDuAn.getTaiLieuGD1().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getTaiLieuGD1());
				}
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_HAI.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD2().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD2(null);
			} else {
				giaiDoanDuAn.getTaiLieuGD2().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getTaiLieuGD2());
				}
			}
			if (giaiDoanDuAn.getCongVanGD2().getNameHash() == null) {
				giaiDoanDuAn.setCongVanGD2(null);
			} else {
				giaiDoanDuAn.getCongVanGD2().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getCongVanGD2());
				}
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BA.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getTaiLieuGD3().getNameHash() == null) {
				giaiDoanDuAn.setTaiLieuGD3(null);
			} else {
				giaiDoanDuAn.getTaiLieuGD3().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getTaiLieuGD3());
				}
			}
			if (giaiDoanDuAn.getCongVanGD3().getNameHash() == null) {
				giaiDoanDuAn.setCongVanGD3(null);
			} else {
				giaiDoanDuAn.getCongVanGD3().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getCongVanGD3());
				}
			}
			return;
		}
		if (GiaiDoanXucTien.GIAI_DOAN_BON.equals(giaiDoanXucTien)) {
			if (PhuongThucLuaChonNDT.DAU_GIA_QUYEN_SU_DUNG_DAT.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				luuDuHoSoKhuDat(giaiDoanDuAn, luuLichSu);
				if (giaiDoanDuAn.getQuyetDinhPheDuyetPADG().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetPADG(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetPADG().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetPADG());
					}
				}
				if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
					giaiDoanDuAn.setHoSoQuyHoachLQH(null);
				} else {
					giaiDoanDuAn.getHoSoQuyHoachLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoQuyHoachLQH());
					}
				}
				if (giaiDoanDuAn.getPhuongAnDauGia().getNameHash() == null) {
					giaiDoanDuAn.setPhuongAnDauGia(null);
				} else {
					giaiDoanDuAn.getPhuongAnDauGia().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getPhuongAnDauGia());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhDauGiaQSDD().getNameHash() == null ) {
					giaiDoanDuAn.setQuyetDinhDauGiaQSDD(null);
				} else {
					giaiDoanDuAn.getQuyetDinhDauGiaQSDD().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhDauGiaQSDD());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetGiaKhoiDiem().getNameHash() == null ) {
					giaiDoanDuAn.setQuyetDinhPheDuyetGiaKhoiDiem(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetGiaKhoiDiem().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetGiaKhoiDiem());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetLQH());
					}
				}
				if (!giaiDoanDuAn.isOption()) {
					if (giaiDoanDuAn.getQuyetDinhBoSungDanhMuc().getNameHash() == null ) {
						giaiDoanDuAn.setQuyetDinhBoSungDanhMuc(null);
					} else {
						giaiDoanDuAn.getQuyetDinhBoSungDanhMuc().saveNotShowNotification();
						if (luuLichSu) {
							listTepTins.add(giaiDoanDuAn.getQuyetDinhBoSungDanhMuc());
						}
					}
					if (giaiDoanDuAn.getVanBanDeNghiBoSung().getNameHash() == null) {
						giaiDoanDuAn.setVanBanDeNghiBoSung(null);
					} else {
						giaiDoanDuAn.getVanBanDeNghiBoSung().saveNotShowNotification();
						if (luuLichSu) {
							listTepTins.add(giaiDoanDuAn.getVanBanDeNghiBoSung());
						}
					}
				}
				return;
			}
			if (PhuongThucLuaChonNDT.DAU_THAU_DU_AN_CO_SU_DUNG_DAT.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetLQH());
					}
				}
				if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
					giaiDoanDuAn.setHoSoQuyHoachLQH(null);
				} else {
					giaiDoanDuAn.getHoSoQuyHoachLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoQuyHoachLQH());
					}
				}
				if (giaiDoanDuAn.getHoSoQuyHoach2000().getNameHash() == null) {
					giaiDoanDuAn.setHoSoQuyHoach2000(null);
				} else {
					giaiDoanDuAn.getHoSoQuyHoach2000().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoQuyHoach2000());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyet2000().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyet2000(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyet2000().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyet2000());
					}
				}
				if (giaiDoanDuAn.getNghiQuyetPheDuyetCongTrinh().getNameHash() == null) {
					giaiDoanDuAn.setNghiQuyetPheDuyetCongTrinh(null);
				} else {
					giaiDoanDuAn.getNghiQuyetPheDuyetCongTrinh().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getNghiQuyetPheDuyetCongTrinh());
					}
				}
				if (giaiDoanDuAn.getBaoCaoDoDacKhuDat().getNameHash() == null) {
					giaiDoanDuAn.setBaoCaoDoDacKhuDat(null);
				} else {
					giaiDoanDuAn.getBaoCaoDoDacKhuDat().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getBaoCaoDoDacKhuDat());
					}
				}
				if (giaiDoanDuAn.getPheDuyetKeHoachSuDungDat().getNameHash() == null) {
					giaiDoanDuAn.setPheDuyetKeHoachSuDungDat(null);
				} else {
					giaiDoanDuAn.getPheDuyetKeHoachSuDungDat().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getPheDuyetKeHoachSuDungDat());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhThuHoiDat().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhThuHoiDat(null);
				} else {
					giaiDoanDuAn.getQuyetDinhThuHoiDat().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhThuHoiDat());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetDanhMuc().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetDanhMuc(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetDanhMuc().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetDanhMuc());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetBoSungKinhPhi().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetBoSungKinhPhi(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetBoSungKinhPhi().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetBoSungKinhPhi());
					}
				}
				if (giaiDoanDuAn.getPhuongAnTaiDinhCu().getNameHash() == null) {
					giaiDoanDuAn.setPhuongAnTaiDinhCu(null);
				} else {
					giaiDoanDuAn.getPhuongAnTaiDinhCu().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getPhuongAnTaiDinhCu());
					}
				}
				if (giaiDoanDuAn.getHoSoMoiTuyen().getNameHash() == null) {
					giaiDoanDuAn.setHoSoMoiTuyen(null);
				} else {
					giaiDoanDuAn.getHoSoMoiTuyen().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoMoiTuyen());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyeHoSoMoiTuyen().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyeHoSoMoiTuyen(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyeHoSoMoiTuyen().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyeHoSoMoiTuyen());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetKetQuaTrungTuyen().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetKetQuaTrungTuyen(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetKetQuaTrungTuyen().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetKetQuaTrungTuyen());
					}
				}
				if (giaiDoanDuAn.getKeHoachLuaChonNhaDauTu().getNameHash() == null) {
					giaiDoanDuAn.setKeHoachLuaChonNhaDauTu(null);
				} else {
					giaiDoanDuAn.getKeHoachLuaChonNhaDauTu().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getKeHoachLuaChonNhaDauTu());
					}
				}
				if (giaiDoanDuAn.getHoSoMoiThau().getNameHash() == null) {
					giaiDoanDuAn.setHoSoMoiThau(null);
				} else {
					giaiDoanDuAn.getHoSoMoiThau().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoMoiThau());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetMoiThau().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetMoiThau(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetMoiThau().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetMoiThau());
					}
				}
				return;
			}
			if (PhuongThucLuaChonNDT.NHAN_CHUYEN_NHUONG.equals(giaiDoanDuAn.getPhuongThucLuaChonNDT())) {
				if (giaiDoanDuAn.getHoSoQuyHoachLQH().getNameHash() == null) {
					giaiDoanDuAn.setHoSoQuyHoachLQH(null);
				} else {
					giaiDoanDuAn.getHoSoQuyHoachLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getHoSoQuyHoachLQH());
					}
				}
				if (giaiDoanDuAn.getQuyetDinhPheDuyetLQH().getNameHash() == null) {
					giaiDoanDuAn.setQuyetDinhPheDuyetLQH(null);
				} else {
					giaiDoanDuAn.getQuyetDinhPheDuyetLQH().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getQuyetDinhPheDuyetLQH());
					}
				}
				if (giaiDoanDuAn.getVanBanChuyenMucDichSDD().getNameHash() == null) {
					giaiDoanDuAn.setVanBanChuyenMucDichSDD(null);
				} else {
					giaiDoanDuAn.getVanBanChuyenMucDichSDD().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getVanBanChuyenMucDichSDD());
					}
				}
				if (giaiDoanDuAn.getVanBanDeNghiThuHoiDat().getNameHash() == null) {
					giaiDoanDuAn.setVanBanDeNghiThuHoiDat(null);
				} else {
					giaiDoanDuAn.getVanBanDeNghiThuHoiDat().saveNotShowNotification();
					if (luuLichSu) {
						listTepTins.add(giaiDoanDuAn.getVanBanDeNghiThuHoiDat());
					}
				}
			}
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(giaiDoanXucTien)) {
			if (giaiDoanDuAn.getGiayChungNhanDauTu().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanDauTu(null);
			} else {
				giaiDoanDuAn.getGiayChungNhanDauTu().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getGiayChungNhanDauTu());
				}
			}
			if (giaiDoanDuAn.getGiayChungNhanDangKyDoanhNghiep().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanDangKyDoanhNghiep(null);
			} else {
				giaiDoanDuAn.getGiayChungNhanDangKyDoanhNghiep().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getGiayChungNhanDangKyDoanhNghiep());
				}
			}
			if (giaiDoanDuAn.getGiayChungNhanQuyenSuDungDat().getNameHash() == null) {
				giaiDoanDuAn.setGiayChungNhanQuyenSuDungDat(null);
			} else {
				giaiDoanDuAn.getGiayChungNhanQuyenSuDungDat().saveNotShowNotification();
				if (luuLichSu) {
					listTepTins.add(giaiDoanDuAn.getGiayChungNhanQuyenSuDungDat());
				}
			}
		}
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
			saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BA, false);
		}
		if (GiaiDoanXucTien.GIAI_DOAN_NAM.equals(model.getGiaiDoanDuAn().getGiaiDoanXucTien())) {
			model.setGiaiDoanXucTien(GiaiDoanXucTien.HOAN_THANH);
			saveNotShowNotificationTaiLieuGiaiDoan(model.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_NAM, true);
		}
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		luuTaiLieuKhac(model.getGiaiDoanDuAn(), true);
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
		saveNotShowNotificationTaiLieuGiaiDoan(duAn.getGiaiDoanDuAn(), GiaiDoanXucTien.GIAI_DOAN_BON, true);
		luuDuLieuTiepTucAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_NAM, GiaiDoanXucTien.GIAI_DOAN_BON);
	}
	
	private void luuLichSuVanBan(DuAn duAn) {
		listTepTins.forEach(item -> {
			LichSuVanBan lichSuVanBan = new LichSuVanBan();
			lichSuVanBan.setDuAn(duAn);
			lichSuVanBan.setGiaiDoanDuAn(duAn.getGiaiDoanDuAn());
			lichSuVanBan.setVanBan(item);
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
			item.saveNotShowNotification();
			if (luuLichSu) {
				listTepTins.add(item);
			}
		});
	}
	public void luuDuLieuTiepTucAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien, GiaiDoanXucTien giaiDoan) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
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