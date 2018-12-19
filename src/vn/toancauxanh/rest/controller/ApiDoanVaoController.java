package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.DoanVao;
import vn.toancauxanh.rest.model.DoanVaoModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.DoanVaoModelService;
import vn.toancauxanh.rest.service.KeyApiService;

@RestController
@RequestMapping(path = "/api/v1/doanVaos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDoanVaoController {

	@Autowired
	private DoanVaoModelService doanVaoModelService;
	
	@Autowired
	private KeyApiService keyApiService;
	
	@GetMapping
	public ResponseEntity<PagingObject<DoanVaoModel>> findAll(
			@RequestHeader(value = "authentication", required = true) String authentication, Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String keyWord) {
		boolean checkQuyen = keyApiService.checkKey(authentication);
		if (checkQuyen) {
			return new ResponseEntity<>(doanVaoModelService.doanVaos(pageable, keyWord), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DoanVaoModel> show(@PathVariable("id") Long id,
			@RequestHeader(value = "authentication", required = true) String authentication) {
		boolean checkQuyen = keyApiService.checkKey(authentication);
		if (checkQuyen) {
			DoanVao doanVao = doanVaoModelService.getById(id);
			if (doanVao != null) {
				return new ResponseEntity<>(doanVao.toDoanVaoModel(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
}
