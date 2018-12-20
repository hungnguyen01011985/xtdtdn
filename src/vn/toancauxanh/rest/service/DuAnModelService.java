package vn.toancauxanh.rest.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.model.QDuAn;
import vn.toancauxanh.rest.model.DuAnModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.repository.DuAnRepository;

@Service
public class DuAnModelService {

	private DuAnRepository duAnRepository;

	@Autowired
	public DuAnModelService(DuAnRepository duAnRepository) {
		this.duAnRepository = duAnRepository;
	}
	
	public PagingObject<DuAnModel> duAns(Pageable pageable, String tenDuAn) {
		if (pageable.getPageSize() > 1000) throw new RuntimeException("Page size too big");
		PagingObject<DuAnModel> rs = new PagingObject<>();
		Page<DuAn> duAnPage;
		
		if (tenDuAn != null && !tenDuAn.trim().isEmpty()) {
			duAnPage = duAnRepository.findAll(QDuAn.duAn.tenDuAn.like("%" + tenDuAn + "%").and(QDuAn.duAn.daXoa.isFalse()), pageable);
		} else {
			duAnPage = duAnRepository.findAll(QDuAn.duAn.daXoa.isFalse(), pageable);
		}
		
		rs.setTotal(duAnPage.getTotalElements());
		rs.setTotalPage(duAnPage.getTotalPages());
		rs.setData(duAnPage.getContent().stream().map(DuAn::toDuAnModel).collect(Collectors.toList()));
		
		return rs;
	}

	public DuAn getById(Long id) {
		return duAnRepository.getById(id);
	}
	
}
