package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.DoanVaoModelService;

@RestController
@RequestMapping(path = "/api/v1/doanVaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDoanVaoController {

	@Autowired
	private DoanVaoModelService doanVaoModelService;
	
	@GetMapping
    public PagingObject<DoanVaoModel> findAll(Pageable pageable, @RequestParam(required = false, defaultValue = "") String keyWord) {
        return doanVaoModelService.doanVaos(pageable, keyWord);
    }

	@GetMapping("/{id}")
	public ResponseEntity<DoanVaoModel> show(@PathVariable("id") Long id) {
		DoanVao doanVao = doanVaoModelService.getById(id);
		if (doanVao != null) {
			return new ResponseEntity<>(doanVao.toDoanVaoModel(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
}
