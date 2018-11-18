package vn.toancauxanh.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.TrangThaiCongViec;
import vn.toancauxanh.model.CongViec;
import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QCongViec;

public class CongViecService extends BasicService<CongViec> {

	private CongViec congViec = new CongViec();

	public CongViec getCongViec() {
		return congViec;
	}

	public void setCongViec(CongViec congViec) {
		this.congViec = congViec;
	}

	private List<CongViec> listCongViec = new ArrayList<CongViec>();

	public List<CongViec> getListCongViec() {
		Long idDoanVao = MapUtils.getLongValue(argDeco(), "idDoanVao");
		if (idDoanVao != null && idDoanVao > 0) {
			JPAQuery<CongViec> q = find(CongViec.class).where(QCongViec.congViec.doanVao.id.eq(Long.valueOf(idDoanVao)));
			if (q != null && q.fetchCount() > 0) {
				listCongViec.addAll(q.fetch());
				return listCongViec;
			} else {
				listCongViec.addAll(congViec.getListCongViecKhoiTao());
			}
		} else {
			listCongViec.addAll(congViec.getListCongViecKhoiTao());
		}
		return listCongViec;
	}

	public void setListCongViec(List<CongViec> listCongViec) {
		this.listCongViec = listCongViec;
	}

	public List<TrangThaiCongViec> getListTrangThaiCongViec() {
		List<TrangThaiCongViec> list = new ArrayList<TrangThaiCongViec>();
		list.add(TrangThaiCongViec.CHUA_THUC_HIEN);
		list.add(TrangThaiCongViec.DANG_THUC_HIEN);
		list.add(TrangThaiCongViec.HOAN_THANH);
		return list;
	}
	
	@Command
	public void saveKeHoachLamViec(@BindingParam("doanVao") final DoanVao doanVao, @BindingParam("wdn") final Window wdn){
		for (CongViec item : listCongViec) {
			item.setDoanVao(doanVao);
			item.saveNotShowNotification();
		}
		showNotification("Lưu thành công!", "", "success");
		wdn.detach();
	}
}
