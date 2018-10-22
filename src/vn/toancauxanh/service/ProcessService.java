package vn.toancauxanh.service;

import java.text.SimpleDateFormat;
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

import vn.toancauxanh.gg.model.enums.GiaiDoanXucTien;
import vn.toancauxanh.model.DonViDuAn;
import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.GiaiDoanDuAn;
import vn.toancauxanh.model.GiaoViec;

public class ProcessService extends BasicService<Object>{
	
	public void validateDuLieuThongTinDuAn(Execution execution) {
		((ExecutionEntity) execution).setVariable("isValidateDuLieuThongTinDuAnHopLe", true);
	}
	
	public void luuDuLieuDuAnVaBatDauXucTien(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.save();
		model.setGiaoViec(new GiaoViec());
		model.getGiaoViec().setDuAn(model);
		model.getGiaoViec().setNgayGiao(new Date());
		model.getGiaoViec().setNguoiGiaoViec(model.getNguoiTao());
		model.getGiaoViec().setNguoiDuocGiao(model.getNguoiPhuTrach());
		model.getGiaoViec().setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		((ExecutionEntity) execution).setVariable("userPopup", null);
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
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
		model.setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_MOT);
		model.save();
		model.getGiaiDoanDuAn().setDuAn(model);
		model.getGiaiDoanDuAn().save();
		luuDuLieuDonVi(model.getGiaiDoanDuAn());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		((ExecutionEntity) execution).setVariable("thoiHanGiaiDoanMot", sdf.format(new Date()));
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
		redirectGiaiDoanDuAnById(model.getId());
		
	}
	
	public void luuDuLieuGiaiDoanMotVaTiepTucGiaiDoanHai(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		model.setGiaiDoanXucTien(GiaiDoanXucTien.GIAI_DOAN_HAI);
		model.save();
		model.getGiaiDoanDuAn().save();
		if (((ExecutionEntity) execution).getBusinessKey() == null || ((ExecutionEntity) execution).getBusinessKey().isEmpty()) {
			((ExecutionEntity) execution).setBusinessKey(model.businessKey());
		}
		redirectGiaiDoanDuAnById(model.getId());
		
	}
	
	public void capNhatNguoiPhuTrach(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		Object object = ((ExecutionEntity) execution).getVariable("list");
		String attr = (String) ((ExecutionEntity) execution).getVariable("attr");
		if (object != null) {
			BindUtils.postNotifyChange(null, null, object, attr);
		}
		model.save();
		if (((ExecutionEntity) execution).getVariable("userPopup") != null) {
			((ExecutionEntity) execution).removeVariable("userPopup");
		}
	}
	
	public void kiemTraDangOGiaiDoanMot(Execution execution) {
		DuAn model = (DuAn) ((ExecutionEntity) execution).getVariable("model");
		if (GiaiDoanXucTien.GIAI_DOAN_MOT.equals(model.getGiaiDoanXucTien())) {
			((ExecutionEntity) execution).setVariable("isDangOGiaiDoannMot", true);
			return;
		}
		((ExecutionEntity) execution).setVariable("isDangOGiaiDoannMot", false);
	}
	
	public void thongBaoTreHannGiaiDoanMot(Execution execution) {
		System.out.println("tre han nha");
	}
	
	public void redirectGiaiDoanDuAnById(Long id) {
		Executions.sendRedirect(Executions.getCurrent().getContextPath()+"/cp/quanlyduan/giaidoan/"+id);
	}
	
	public void luuDuLieuDonVi(GiaiDoanDuAn giaiDoanDuAn) {
		for(DonViDuAn s : giaiDoanDuAn.getDonViDuAn()) {
			s.setGiaiDoanDuAn(giaiDoanDuAn);
			s.save();
			if (s.getDonVi() != null) {

			}
		}
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
