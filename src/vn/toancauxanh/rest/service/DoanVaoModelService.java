package vn.toancauxanh.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.rest.repository.DoanVaoRepository;

@Service
public class DoanVaoModelService {

	private DoanVaoRepository doanVaoRepository;

	@Autowired
	public DoanVaoModelService(DoanVaoRepository doanVaoRepository) {
		this.doanVaoRepository = doanVaoRepository;
	}

	public Optional<DoanVao> getById(Long id) {
		System.out.println(doanVaoRepository.getById(id).toString());
		return doanVaoRepository.getById(id);
	}

}
