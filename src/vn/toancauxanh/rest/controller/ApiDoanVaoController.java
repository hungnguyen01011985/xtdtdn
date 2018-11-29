package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.DoanVaoModelService;

@RestController
@RequestMapping(path = "/api/v1/doanVaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDoanVaoController {

	@Autowired
	private DoanVaoModelService doanVaoModelService;
	
	@GetMapping
	public PagingObject<DoanVaoModel> doanVaos(Pageable pageable, @RequestParam(required = false, defaultValue = "") String keyWord){
		return this.doanVaoModelService.doanVaos(pageable, keyWord);
	}

}
