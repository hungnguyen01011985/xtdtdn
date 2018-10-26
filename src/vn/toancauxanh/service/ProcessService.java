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
import vn.toancauxanh.model.DonViDuAn;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.QGiaiDoanDuAn;

public class ProcessService extends BasicService<Object>{
	
	public void validateDuLieuThongTinDuAn(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuThongTinDuAnHopLe", true);
	}
	
	public void luuDuLieuDuAnVaBatDauXucTien(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.saveNotShowNotification();
		model.getGiaoViec().setDuAn(model);
		model.getGiaoViec().setNgayGiao(new Date());
		model.getGiaoViec().setNguoiGiaoViec(model.getNguoiTao());
		model.getGiaoViec().setNguoiDuocGiao(model.getNguoiPhuTrach());
		model.getGiaoViec().setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		model.getGiaoViec().save();
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
		model.getTaiLieu().saveNotShowNotification();
		redirectGiaiDoanDuAnById(model.getId());
	}
	
	public void validateDuLieuGiaiDoanMotVaTiepTucGiaiDoanHai(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanHaiHopLe", true);
	}
	
	public void validateDuLieuGiaiDoanMot(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanMotHopLe", true);
	}
	
	public void luuDuLieuGiaiDoanMot(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_MOT);
		luuDuLieuDonVi(model.getGiaiDoanDuAn());
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
	}
	
	public void luuDuLieuGiaiDoanMotVaTiepTucGiaiDoanHai(Execution execution) {
		luuDuLieuTiepTucAndRedirect(execution,GiaiDoanXucTien.GIAI_DOAN_HAI,GiaiDoanXucTien.GIAI_DOAN_MOT);
	}
	
	public void capNhatNguoiPhuTrach(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		Object object = ((ExecutionEntity) execution).getVariable("list");
		String attr = (String) ((ExecutionEntity) execution).getVariable("attr");
		if (object != null) {
			BindUtils.postNotifyChange(null, null, object, attr);
		}
		model.getNguoiPhuTrach().save();
	}
	
	public void giaoViecDuAn(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.getGiaoViec().setDuAn(model);
		model.getGiaoViec().setGiaiDoanXucTien(model.getGiaiDoanXucTien());
		model.getGiaoViec().setNguoiGiaoViec(core().getNhanVien());
		model.getGiaoViec().setNguoiDuocGiao(model.getNguoiPhuTrach());
		model.getGiaoViec().save();
	}
	
	public void kiemTraDangOGiaiDoanMot(Execution execution) {
		kiemTraGiaiDoan(execution, "thoiHanGiaiDoanMot", GiaiDoanXucTien.GIAI_DOAN_MOT);
	}
	
	public void validateDuLieuGiaiDoanBaVaKetThucDuAn(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanBaVaKetThucDuAn", true);
	}
	
	public void validateDuLieuGiaiDoanHai(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuGiaiDoanHaiHopLe", true);
	}
	
	public void thongBaoTreHannGiaiDoanMot(Execution execution) {
		System.out.println("tre han nhaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	public void validateDuLieuGiaiDoanHaiVaTiepTucGiaiDoanBa(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanBaHopLe", true);
	}
	
	public void luuDuLieuQuayLaiGiaiDoanMot(Execution execution) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		duAn.getGiaiDoanDuAn().setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_HAI);
		duAn.getGiaiDoanDuAn().save();
		JPAQuery<GiaiDoanDuAn> q = find(GiaiDoanDuAn.class)
				.where(QGiaiDoanDuAn.giaiDoanDuAn.duAn.eq(duAn))
				.where(QGiaiDoanDuAn.giaiDoanDuAn.trangThai.ne(core().TT_DA_XOA));
		for(GiaiDoanDuAn s : q.fetch()) {
			s.doDelete(true);
		}
		duAn.setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		duAn.save();
		redirectGiaiDoanDuAnById(duAn.getId());
	}
	
	public void luuDuLieuGIaiDoanHaiVaTiepTucGiaiDoanBa(Execution execution) {
		luuDuLieuTiepTucAndRedirect(execution,GiaiDoanXucTien.GIAI_DOAN_BA,GiaiDoanXucTien.GIAI_DOAN_HAI);
	}
	
	public void validateDuLieuGiaiDoanBaVaTiepTucGiaiDoanBon(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuDeTiepTucGiaiDoanBonHopLe", true);
	}
	
	public void luuDuLieuGIaiDoanBaVaTiepTucGiaiDoanBon(Execution execution) {
		luuDuLieuTiepTucAndRedirect(execution,GiaiDoanXucTien.GIAI_DOAN_BON,GiaiDoanXucTien.GIAI_DOAN_BA);
	}
	
	
	public void luuDuLieuGiaiDoanHai(Execution execution) {
		luuDuLieuAndRedirect(execution, GiaiDoanXucTien.GIAI_DOAN_HAI);
	}
	
	public void luuDuLieuAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien) {
		DuAn duAn = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		duAn.getGiaiDoanDuAn().setDuAn(duAn);
		duAn.getGiaiDoanDuAn().setGiaiDoanXucTien(giaiDoanXucTien);
		duAn.getGiaiDoanDuAn().save();
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(duAn.businessKey());
		}
		redirectGiaiDoanDuAnById(duAn.getId());
	}
	
	public void luuDuLieuTiepTucAndRedirect(Execution execution, GiaiDoanXucTien giaiDoanXucTien,GiaiDoanXucTien giaiDoan) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.setGiaiDoanXucTien(giaiDoanXucTien);
		model.saveNotShowNotification();
		model.getGiaiDoanDuAn().setDuAn(model);
		model.getGiaiDoanDuAn().setGiaiDoanXucTien(giaiDoan);
		model.getGiaiDoanDuAn().save();
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
		redirectGiaiDoanDuAnById(model.getId());
	}
	
	public void redirectGiaiDoanDuAnById(Long id) {
		Executions.sendRedirect("/cp/quanlyduan/"+id);
	}
	
	public void luuDuLieuDonVi(GiaiDoanDuAn giaiDoanDuAn) {
		for (DonViDuAn s : giaiDoanDuAn.getDonViDuAn()) {
			s.setGiaiDoanDuAn(giaiDoanDuAn);
			s.save();
		}
	}
	
	public void kiemTraGiaiDoan(Execution execution, String varriable, GiaiDoanXucTien giaiDoan) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		if (giaiDoan.equals(model.getGiaiDoanXucTien())) {
			((ExecutionEntity) execution).setVariable(varriable, true);
			return;
		}
		((ExecutionEntity) execution).setVariable(varriable, false);
	}
	
	public List<PvmTransition> getTransitions(Task task) {
		List<PvmTransition> result = new ArrayList<>();
		for (PvmTransition pvmTransition : ((RepositoryServiceImpl) core()
				.getProcess().getRepositoryService())
				.getDeployedProcessDefinition(task.getProcessDefinitionId())
				.findActivity(task.getTaskDefinitionKey())
				.getOutgoingTransitions()) {
			if (((ActivityImpl) pvmTransition
					.getDestination()).getActivityBehavior() instanceof ExclusiveGatewayActivityBehavior) {
				List<PvmTransition> outgoingTransitions = pvmTransition
						.getDestination().getOutgoingTransitions();
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
		List<ProcessDefinition> ls = core()
				.getProcess()
				.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionKeyLike(processDefinitionKey)
				.latestVersion()
				.list();
		String processDefinitionId = ls.get(0).getId();
		List<PvmTransition> result = new ArrayList<>();
		for (PvmTransition pvmTransition : ((RepositoryServiceImpl) core()
				.getProcess()
				.getRepositoryService())
				.getDeployedProcessDefinition(processDefinitionId)
				.findActivity(taskDefinitionKey)
				.getOutgoingTransitions()) {
			if (((ActivityImpl) pvmTransition
					.getDestination()).getActivityBehavior() instanceof ExclusiveGatewayActivityBehavior) {
				List<PvmTransition> outgoingTransitions = pvmTransition
						.getDestination().getOutgoingTransitions();
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
