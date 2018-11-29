package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.rest.model.NotFoundException;
import vn.toancauxanh.rest.service.DoanVaoModelService;

@RestController
@RequestMapping(path = "/api/v1/doanVaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDoanVaoController {

	@Autowired
	private DoanVaoModelService doanVaoModelService;

	@GetMapping("/{id}")
	public DoanVaoModel getById(@PathVariable("id") Long idDoanVao) {
		System.out.println("aaaaaaaaaa" + idDoanVao);
		return this.doanVaoModelService.getById(idDoanVao).map(DoanVao::toDoanVaoModel)
				.orElseThrow(NotFoundException::new);
	}
}
