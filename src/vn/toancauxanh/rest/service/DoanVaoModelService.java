package vn.toancauxanh.rest.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.model.QDoanVao;
import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.repository.DoanVaoRepository;

@Service
public class DoanVaoModelService {

	private DoanVaoRepository doanVaoRepository;

	@Autowired
	public DoanVaoModelService(DoanVaoRepository doanVaoRepository) {
		this.doanVaoRepository = doanVaoRepository;
	}
	
	public PagingObject<DoanVaoModel> doanVaos(Pageable pageable, String tenDoanVao){
		if (pageable.getPageSize() > 1000) throw new RuntimeException("Page size too big");
		PagingObject<DoanVaoModel> rs = new PagingObject<>();
		Page<DoanVao> doanVaoPage;
		
		if (tenDoanVao != null && !tenDoanVao.trim().isEmpty()) {
			doanVaoPage = doanVaoRepository.findAll(QDoanVao.doanVao.tenDoanVao.like("%" + tenDoanVao + "%").and(QDoanVao.doanVao.daXoa.isFalse()), pageable);
		} else {
			doanVaoPage = doanVaoRepository.findAll(QDoanVao.doanVao.daXoa.isFalse(), pageable);
		}
		
		rs.setTotal(doanVaoPage.getTotalElements());
		rs.setTotalPage(doanVaoPage.getTotalPages());
		rs.setData(doanVaoPage.getContent().stream().map(DoanVao::toDoanVaoModel).collect(Collectors.toList()));
		
		return rs;
	}

	public DoanVao getById(Long id) {
		return doanVaoRepository.getById(id);
	}
	
}
